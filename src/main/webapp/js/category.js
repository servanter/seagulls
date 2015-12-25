function Dsy(){
    this.Items = {};
};
Dsy.prototype.add = function(id,iArray){
    this.Items[id] = iArray;
};
Dsy.prototype.Exists = function(id){
    if(typeof(this.Items[id]) == "undefined") return false;
    return true;
};
var dsy = new Dsy();
dsy.add("0",[{"id":1, "name":"水果"},{"id":2, "name":"蔬菜"},{"id":3, "name":"禽畜牧蛋肉"},{"id":4, "name":"水产"},{"id":900, "name":"其他"}]);
dsy.add("0_0",[{"id":1001, "name":"苹果"},{"id":1002, "name":"梨"},{"id":1007, "name":"柚子"},{"id":1010, "name":"西瓜"},{"id":1011, "name":"芒果"},{"id":1019, "name":"香蕉"},{"id":1020, "name":"葡萄&提子"},{"id":1021, "name":"石榴"},{"id":1024, "name":"哈密瓜"},{"id":1025, "name":"柠檬"},{"id":1027, "name":"火龙果"},{"id":1028, "name":"菠萝"},{"id":1030, "name":"樱桃&车厘子"},{"id":1033, "name":"菠萝蜜"},{"id":1034, "name":"杨桃"},{"id":1036, "name":"木瓜"},{"id":1045, "name":"榴莲"},{"id":1047, "name":"龙眼"},{"id":1048, "name":"荔枝"},{"id":1049, "name":"莲雾"},{"id":1050, "name":"牛油果"},{"id":1051, "name":"山竹"},{"id":1052, "name":"椰子"},{"id":1053, "name":"橙子"},{"id":1054, "name":"桔子"}]);
dsy.add("0_1",[{"id":2001, "name":"南瓜"},{"id":2002, "name":"冬瓜"},{"id":2003, "name":"丝瓜"},{"id":2004, "name":"苦瓜"},{"id":2005, "name":"黄瓜"},{"id":2006, "name":"葫芦"},{"id":2007, "name":"辣椒"},{"id":2008, "name":"青椒"},{"id":2009, "name":"茄子"},{"id":2010, "name":"西红柿"},{"id":2011, "name":"玉米"},{"id":2012, "name":"豆芽"},{"id":2013, "name":"生姜"},{"id":2014, "name":"大蒜"},{"id":2015, "name":"洋葱"},{"id":2016, "name":"小葱"},{"id":2017, "name":"大葱"},{"id":2018, "name":"花生"},{"id":2019, "name":"竹笋"},{"id":2020, "name":"莴笋"},{"id":2021, "name":"西兰花"},{"id":2022, "name":"芥兰"},{"id":2023, "name":"油菜"},{"id":2024, "name":"油麦菜"},{"id":2025, "name":"芹菜"},{"id":2026, "name":"香菜"},{"id":2027, "name":"白菜"},{"id":2028, "name":"菠菜"},{"id":2029, "name":"空心菜"},{"id":2030, "name":"生菜"},{"id":2031, "name":"茼蒿"},{"id":2032, "name":"地瓜叶"}]);
dsy.add("0_2",[{"id":3001, "name":"肉猪"},{"id":3002, "name":"肉牛"},{"id":3003, "name":"肉羊"},{"id":3004, "name":"肉兔"},{"id":3005, "name":"肉鸡"},{"id":3006, "name":"肉鸭"},{"id":3007, "name":"肉鹅"},{"id":3008, "name":"肉鸽"},{"id":3009, "name":"鸡蛋"},{"id":3010, "name":"鸭蛋"},{"id":3011, "name":"鹅蛋"},{"id":3012, "name":"鹌鹑蛋"}]);
dsy.add("0_3",[{"id":4001, "name":"海水鱼"},{"id":4002, "name":"淡水鱼"}]);
dsy.add("0_0_0",[{"id":-1, "name":"全部"},{"id":12, "name":"黄元帅"}]);
dsy.add("0_0_1",[{"id":-1, "name":"全部"},{"id":13, "name":"品种梨"}]);
dsy.add("0_0_2",[{"id":-1, "name":"全部"},{"id":14, "name":"品种柚子"}]);
dsy.add("0_0_3",[{"id":-1, "name":"全部"},{"id":15, "name":"品种西瓜"}]);
dsy.add("0_0_4",[{"id":-1, "name":"全部"},{"id":2, "name":"台农一号"},{"id":3, "name":"青皮芒"},{"id":4, "name":"金煌芒"},{"id":5, "name":"凯特芒"}]);
dsy.add("0_0_5",[{"id":-1, "name":"全部"},{"id":6, "name":"仙人蕉"},{"id":7, "name":"齐尾"},{"id":8, "name":"大种高把"},{"id":9, "name":"香牙蕉"},{"id":10, "name":"大蕉"},{"id":11, "name":"粉蕉"}]);
dsy.add("0_0_6",[{"id":-1, "name":"全部"},{"id":16, "name":"品种葡萄&提子"}]);
dsy.add("0_0_7",[{"id":-1, "name":"全部"},{"id":17, "name":"品种石榴"}]);
dsy.add("0_0_8",[{"id":-1, "name":"全部"},{"id":18, "name":"品种哈密瓜"}]);
dsy.add("0_0_9",[{"id":-1, "name":"全部"},{"id":19, "name":"品种柠檬"}]);
dsy.add("0_0_10",[{"id":-1, "name":"全部"},{"id":20, "name":"品种火龙果"}]);
dsy.add("0_0_11",[{"id":-1, "name":"全部"},{"id":21, "name":"品种菠萝"}]);
dsy.add("0_0_12",[{"id":-1, "name":"全部"},{"id":22, "name":"品种樱桃&车厘子"}]);
dsy.add("0_0_13",[{"id":-1, "name":"全部"},{"id":23, "name":"品种菠萝蜜"}]);
dsy.add("0_0_14",[{"id":-1, "name":"全部"},{"id":24, "name":"品种杨桃"}]);
dsy.add("0_0_15",[{"id":-1, "name":"全部"},{"id":25, "name":"品种木瓜"}]);
dsy.add("0_0_16",[{"id":-1, "name":"全部"},{"id":26, "name":"品种榴莲"}]);
dsy.add("0_0_17",[{"id":-1, "name":"全部"},{"id":27, "name":"品种龙眼"}]);
dsy.add("0_0_18",[{"id":-1, "name":"全部"},{"id":28, "name":"品种荔枝"}]);
dsy.add("0_0_19",[{"id":-1, "name":"全部"},{"id":29, "name":"品种莲雾"}]);
dsy.add("0_0_20",[{"id":-1, "name":"全部"},{"id":30, "name":"品种牛油果"}]);
dsy.add("0_0_21",[{"id":-1, "name":"全部"},{"id":31, "name":"品种山竹"}]);
dsy.add("0_0_22",[{"id":-1, "name":"全部"},{"id":32, "name":"品种椰子"}]);
dsy.add("0_0_23",[{"id":-1, "name":"全部"},{"id":33, "name":"品种橙子"}]);
dsy.add("0_0_24",[{"id":-1, "name":"全部"},{"id":34, "name":"品种桔子"}]);
dsy.add("0_1_0",[{"id":-1, "name":"全部"},{"id":2001001, "name":"品种南瓜"}]);
dsy.add("0_1_1",[{"id":-1, "name":"全部"},{"id":2002001, "name":"品种冬瓜"}]);
