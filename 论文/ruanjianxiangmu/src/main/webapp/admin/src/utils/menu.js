const menu = {
    list() {
        return [
    {
        "backMenu":[
            {
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"管理员管理",
                        "menuJump":"列表",
                        "tableName":"users"
                    }
                ],
                "menu":"管理员管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "删除",
                            "修改"
                        ],
                        "menu":"帖子类型管理",
                        "menuJump":"列表",
                        "tableName":"dictionaryForum"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "删除",
                            "修改"
                        ],
                        "menu":"公告类型管理",
                        "menuJump":"列表",
                        "tableName":"dictionaryGonggao"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "删除",
                            "修改"
                        ],
                        "menu":"技术类型管理",
                        "menuJump":"列表",
                        "tableName":"dictionaryJishu"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "删除",
                            "修改"
                        ],
                        "menu":"模块类型管理",
                        "menuJump":"列表",
                        "tableName":"dictionaryMokuai"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "删除",
                            "修改"
                        ],
                        "menu":"经费使用类型管理",
                        "menuJump":"列表",
                        "tableName":"dictionaryMokuaiHuafei"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "删除",
                            "修改"
                        ],
                        "menu":"文献类型管理",
                        "menuJump":"列表",
                        "tableName":"dictionaryWenxian"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "删除",
                            "修改"
                        ],
                        "menu":"项目类型管理",
                        "menuJump":"列表",
                        "tableName":"dictionaryXiangmu"
                    }
                ],
                "menu":"基础数据管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"论坛管理",
                        "menuJump":"列表",
                        "tableName":"forum"
                    }
                ],
                "menu":"论坛管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"公告管理",
                        "menuJump":"列表",
                        "tableName":"gonggao"
                    }
                ],
                "menu":"公告管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"技术管理",
                        "menuJump":"列表",
                        "tableName":"jishu"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "删除"
                        ],
                        "menu":"技术收藏管理",
                        "menuJump":"列表",
                        "tableName":"jishuCollection"
                    }
                ],
                "menu":"技术管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"文献管理",
                        "menuJump":"列表",
                        "tableName":"wenxian"
                    }
                    ,
                    {
                        "buttons":[
                            "查看",
                            "删除"
                        ],
                        "menu":"文献收藏管理",
                        "menuJump":"列表",
                        "tableName":"wenxianCollection"
                    }
                ],
                "menu":"文献管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"管理员私信管理",
                        "menuJump":"列表",
                        "tableName":"sixin"
                    }
                ],
                "menu":"管理员私信管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"项目管理",
                        "menuJump":"列表",
                        "tableName":"xiangmu"
                    }
                ],
                "menu":"项目管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"模块管理",
                        "menuJump":"列表",
                        "tableName":"mokuai"
                    }
					,
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"经费使用管理",
                        "menuJump":"列表",
                        "tableName":"mokuaiHuafei"
                    }
                ],
                "menu":"模块管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"项目经理管理",
                        "menuJump":"列表",
                        "tableName":"xiangmujingli"
                    }
                ],
                "menu":"项目经理管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"用户管理",
                        "menuJump":"列表",
                        "tableName":"yonghu"
                    }
                ],
                "menu":"用户管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"轮播图管理",
                        "menuJump":"列表",
                        "tableName":"config"
                    }
                ],
                "menu":"轮播图信息"
            }
        ],
        "frontMenu":[],
        "hasBackLogin":"是",
        "hasBackRegister":"否",
        "hasFrontLogin":"否",
        "hasFrontRegister":"否",
        "roleName":"管理员",
        "tableName":"users"
    }
	,
	{
        "backMenu":[
            {
                "child":[
                    {
                        "buttons":[
                            "查看"
                        ],
                        "menu":"论坛管理",
                        "menuJump":"列表",
                        "tableName":"forum"
                    }
                ],
                "menu":"论坛管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看"
                        ],
                        "menu":"公告管理",
                        "menuJump":"列表",
                        "tableName":"gonggao"
                    }
                ],
                "menu":"公告管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看"
                        ],
                        "menu":"技术管理",
                        "menuJump":"列表",
                        "tableName":"jishu"
                    }
                ],
                "menu":"技术管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看"
                        ],
                        "menu":"文献管理",
                        "menuJump":"列表",
                        "tableName":"wenxian"
                    }
                ],
                "menu":"文献管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看"
                        ],
                        "menu":"项目管理",
                        "menuJump":"列表",
                        "tableName":"xiangmu"
                    }
                ],
                "menu":"项目管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看",
                            "新增",
                            "修改",
                            "删除"
                        ],
                        "menu":"模块管理",
                        "menuJump":"列表",
                        "tableName":"mokuai"
                    }
					,
                    {
                        "buttons":[
                            "查看"
                        ],
                        "menu":"经费使用管理",
                        "menuJump":"列表",
                        "tableName":"mokuaiHuafei"
                    }
                ],
                "menu":"模块管理"
            }
            ,{
                "child":[
                    {
                        "buttons":[
                            "查看"
                        ],
                        "menu":"用户管理",
                        "menuJump":"列表",
                        "tableName":"yonghu"
                    }
                ],
                "menu":"用户管理"
            }
        ],
        "frontMenu":[],
        "hasBackLogin":"是",
        "hasBackRegister":"否",
        "hasFrontLogin":"否",
        "hasFrontRegister":"否",
        "roleName":"项目经理",
        "tableName":"xiangmujingli"
    }
]
    }
}
export default menu;
