
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 经费使用
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/mokuaiHuafei")
public class MokuaiHuafeiController {
    private static final Logger logger = LoggerFactory.getLogger(MokuaiHuafeiController.class);

    private static final String TABLE_NAME = "mokuaiHuafei";

    @Autowired
    private MokuaiHuafeiService mokuaiHuafeiService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private JishuService jishuService;//技术
    @Autowired
    private JishuCollectionService jishuCollectionService;//技术收藏
    @Autowired
    private MokuaiService mokuaiService;//模块
    @Autowired
    private SixinService sixinService;//管理员私信
    @Autowired
    private WenxianService wenxianService;//文献
    @Autowired
    private WenxianCollectionService wenxianCollectionService;//文献收藏
    @Autowired
    private XiangmuService xiangmuService;//项目
    @Autowired
    private XiangmujingliService xiangmujingliService;//项目经理
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("项目经理".equals(role))
            params.put("xiangmujingliId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = mokuaiHuafeiService.queryPage(params);

        //字典表数据转换
        List<MokuaiHuafeiView> list =(List<MokuaiHuafeiView>)page.getList();
        for(MokuaiHuafeiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        MokuaiHuafeiEntity mokuaiHuafei = mokuaiHuafeiService.selectById(id);
        if(mokuaiHuafei !=null){
            //entity转view
            MokuaiHuafeiView view = new MokuaiHuafeiView();
            BeanUtils.copyProperties( mokuaiHuafei , view );//把实体数据重构到view中
            //级联表 模块
            //级联表
            MokuaiEntity mokuai = mokuaiService.selectById(mokuaiHuafei.getMokuaiId());
            if(mokuai != null){
            BeanUtils.copyProperties( mokuai , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setMokuaiId(mokuai.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody MokuaiHuafeiEntity mokuaiHuafei, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,mokuaiHuafei:{}",this.getClass().getName(),mokuaiHuafei.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<MokuaiHuafeiEntity> queryWrapper = new EntityWrapper<MokuaiHuafeiEntity>()
            .eq("mokuai_id", mokuaiHuafei.getMokuaiId())
            .eq("mokuai_huafei_name", mokuaiHuafei.getMokuaiHuafeiName())
            .eq("mokuai_huafei_types", mokuaiHuafei.getMokuaiHuafeiTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        MokuaiHuafeiEntity mokuaiHuafeiEntity = mokuaiHuafeiService.selectOne(queryWrapper);
        if(mokuaiHuafeiEntity==null){
            mokuaiHuafei.setInsertTime(new Date());
            mokuaiHuafei.setCreateTime(new Date());
            mokuaiHuafeiService.insert(mokuaiHuafei);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody MokuaiHuafeiEntity mokuaiHuafei, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,mokuaiHuafei:{}",this.getClass().getName(),mokuaiHuafei.toString());
        MokuaiHuafeiEntity oldMokuaiHuafeiEntity = mokuaiHuafeiService.selectById(mokuaiHuafei.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");

            mokuaiHuafeiService.updateById(mokuaiHuafei);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<MokuaiHuafeiEntity> oldMokuaiHuafeiList =mokuaiHuafeiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        mokuaiHuafeiService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<MokuaiHuafeiEntity> mokuaiHuafeiList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            MokuaiHuafeiEntity mokuaiHuafeiEntity = new MokuaiHuafeiEntity();
//                            mokuaiHuafeiEntity.setMokuaiId(Integer.valueOf(data.get(0)));   //模块 要改的
//                            mokuaiHuafeiEntity.setMokuaiHuafeiUuidNumber(data.get(0));                    //花费编号 要改的
//                            mokuaiHuafeiEntity.setMokuaiHuafeiName(data.get(0));                    //花费标题 要改的
//                            mokuaiHuafeiEntity.setMokuaiHuafeiTypes(Integer.valueOf(data.get(0)));   //经费使用类型 要改的
//                            mokuaiHuafeiEntity.setHuafeiTime(sdf.parse(data.get(0)));          //花费时间 要改的
//                            mokuaiHuafeiEntity.setMokuaiHuafeiJine(data.get(0));                    //使用金额 要改的
//                            mokuaiHuafeiEntity.setMokuaiHuafeiContent("");//详情和图片
//                            mokuaiHuafeiEntity.setInsertTime(date);//时间
//                            mokuaiHuafeiEntity.setCreateTime(date);//时间
                            mokuaiHuafeiList.add(mokuaiHuafeiEntity);


                            //把要查询是否重复的字段放入map中
                                //花费编号
                                if(seachFields.containsKey("mokuaiHuafeiUuidNumber")){
                                    List<String> mokuaiHuafeiUuidNumber = seachFields.get("mokuaiHuafeiUuidNumber");
                                    mokuaiHuafeiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> mokuaiHuafeiUuidNumber = new ArrayList<>();
                                    mokuaiHuafeiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("mokuaiHuafeiUuidNumber",mokuaiHuafeiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //花费编号
                        List<MokuaiHuafeiEntity> mokuaiHuafeiEntities_mokuaiHuafeiUuidNumber = mokuaiHuafeiService.selectList(new EntityWrapper<MokuaiHuafeiEntity>().in("mokuai_huafei_uuid_number", seachFields.get("mokuaiHuafeiUuidNumber")));
                        if(mokuaiHuafeiEntities_mokuaiHuafeiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(MokuaiHuafeiEntity s:mokuaiHuafeiEntities_mokuaiHuafeiUuidNumber){
                                repeatFields.add(s.getMokuaiHuafeiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [花费编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        mokuaiHuafeiService.insertBatch(mokuaiHuafeiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = mokuaiHuafeiService.queryPage(params);

        //字典表数据转换
        List<MokuaiHuafeiView> list =(List<MokuaiHuafeiView>)page.getList();
        for(MokuaiHuafeiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        MokuaiHuafeiEntity mokuaiHuafei = mokuaiHuafeiService.selectById(id);
            if(mokuaiHuafei !=null){


                //entity转view
                MokuaiHuafeiView view = new MokuaiHuafeiView();
                BeanUtils.copyProperties( mokuaiHuafei , view );//把实体数据重构到view中

                //级联表
                    MokuaiEntity mokuai = mokuaiService.selectById(mokuaiHuafei.getMokuaiId());
                if(mokuai != null){
                    BeanUtils.copyProperties( mokuai , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setMokuaiId(mokuai.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody MokuaiHuafeiEntity mokuaiHuafei, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,mokuaiHuafei:{}",this.getClass().getName(),mokuaiHuafei.toString());
        Wrapper<MokuaiHuafeiEntity> queryWrapper = new EntityWrapper<MokuaiHuafeiEntity>()
            .eq("mokuai_id", mokuaiHuafei.getMokuaiId())
            .eq("mokuai_huafei_uuid_number", mokuaiHuafei.getMokuaiHuafeiUuidNumber())
            .eq("mokuai_huafei_name", mokuaiHuafei.getMokuaiHuafeiName())
            .eq("mokuai_huafei_types", mokuaiHuafei.getMokuaiHuafeiTypes())
//            .notIn("mokuai_huafei_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        MokuaiHuafeiEntity mokuaiHuafeiEntity = mokuaiHuafeiService.selectOne(queryWrapper);
        if(mokuaiHuafeiEntity==null){
            mokuaiHuafei.setInsertTime(new Date());
            mokuaiHuafei.setCreateTime(new Date());
        mokuaiHuafeiService.insert(mokuaiHuafei);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

