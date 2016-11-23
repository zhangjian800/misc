var indexdata = 
[
    { text: '上访管理',isexpand:false, children: [ 
		{url:"/dolphin/listAccessResult.do",text:"上访记录查询"}
	  ]
    }
    ,
    {isexpand:"false",text:"规则管理",children:[ 
	    {url:"/dolphin/listAllRules.do",text:"所有规则查询"},
	    {url:"/dolphin/createRule.do?type=rule4city",text:"增加新规则"},
	    {url:"/dolphin/createRule.do?type=rule4fstprioritmulticity",text:"增加多城市
	    {url:"/dolphin/createRule.do?type=rule4fstpriority",text:"增加系统规则"} 		
    ]}
    ,
    {isexpand:"false",text:"应用管理",children:[ 
	    {url:"/dolphin/listAllApps.do",text:"系统应用管理"},
	    {url:"/dolphin/createApp.do",text:"增加新应用"}
    ]}
    ,
    {isexpand:"false",text:"系统管理",children
	    {url:"/dolphin/listDailyCharge.do",text:"计费每日累计结果"},
	    {url:"/dolphin/listDailyDtlCharge.do",text:"计费上访明细结果"},
	    {url:"/dolphin/listchargeconfirm.do",text:"计费确认结果"},
	    {url:"/dolphin/listPhonesRpt.do",text:"手机统计信息"}
    ]}
    ,
];

    ",text:"系统管理",children:[ 
	    {url:"/dolphin//enterSystemRule.do",text:"系统参数配置"},
	    {url:"/dolphin/reload?type=app",text:"清空应用程序缓存"},
	    {url:"/dolphin/reload?type=all",text:"清空系统�rule",text:"清空所有规则缓存"},
	    {url:"/dolphin/reload?type=all",text:"清空系统全部缓存"}
    ]}
	,
];

