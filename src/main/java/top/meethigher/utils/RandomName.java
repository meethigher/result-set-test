package top.meethigher.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomName {

    public static String create() {
        String[] name = new String[]{
                "常琛",
                "窦荷源",
                "喻巧",
                "尤妹姣",
                "平莉莹",
                "卫聪",
                "孔莲",
                "田思珍",
                "凌秀固",
                "孙珍",
                "祝怡之",
                "胡维士",
                "胡珍琴",
                "穆英",
                "和泰怡",
                "常冠",
                "于朋欢",
                "傅楠",
                "周真瑶",
                "柏嫣",
                "张珍",
                "骆悦",
                "梁雄毓",
                "滕秋飘",
                "余颖",
                "胡淇",
                "云环嫣",
                "昝瑗之",
                "伏馨朋",
                "汤宜",
                "江秋艺",
                "沈毓",
                "元娅憧",
                "魏可",
                "凤惠",
                "危凡姣",
                "谈琛栋",
                "雷叶先",
                "汪辰时",
                "唐依美",
                "卞悦美",
                "廉鹏珠",
                "赵凝",
                "林盛琦",
                "姚美钧",
                "杜可",
                "屈文辰",
                "罗泽蓉",
                "汪宜漩",
                "邹晨倩",
                "葛谦莲",
                "祁盛妤",
                "周冰勤",
                "彭浩静",
                "支雁澜",
                "姚巧行",
                "伏瑶德",
                "戴宏弘",
                "纪萍霄",
                "冯慧黛",
                "米琴芳",
                "任文希",
                "闵鹏冠",
                "董滢",
                "徐泰翰",
                "麻思秀",
                "邱若以",
                "董聪",
                "梁柏可",
                "周荣蓉",
                "余怡欣",
                "贝晴",
                "尹君璧",
                "童叶筠",
                "时漩宁",
                "苗娅韵",
                "岑影",
                "林宁",
                "夏芳玲",
                "汪卿奇",
                "田风瑶",
                "孔惠雄",
                "臧纨秀",
                "柳梦",
                "严荣",
                "赵行",
                "齐乐云",
                "昝欢亮",
                "霍月馨",
                "葛莹珊",
                "张宇",
                "康嫣倩",
                "常树",
                "喻莹以",
                "水融婷",
                "钱滢行",
                "蒋思",
                "戚筠",
                "纪风铭",
                "姜泽婉",
                "章晨",
                "方时媛",
                "霍枝",
                "王盛荷",
                "蔡启琳",
                "马怡珠",
                "贾家轮",
                "俞莉露",
                "茅乐一",
                "许琴爽",
                "殷霄",
                "张凝珍",
                "刁思荔",
                "廉兰",
                "田瑶柏",
                "秦辰镇",
                "贾怡蕊",
                "方铭",
                "安伦婷",
                "王先仪",
                "葛婕朗",
                "朱悦雨",
                "麻奕",
                "管卿晴",
                "尹璧柔",
                "蒋莉",
                "杨钧榕",
                "方斌娅",
                "邱希霭",
                "伏莹媛",
                "卫枝泓",
                "昌雄",
                "梅娟",
                "时先",
                "陶枝可",
                "樊文钊",
                "郑子园",
                "凌希",
                "席兰雅",
                "支英源",
                "蒋秋",
                "杨嘉",
                "伏淑",
                "席憧晨",
                "赵舒",
                "危欢乐",
                "狄霞乐",
                "倪宏璧",
                "柳婵",
                "麻怡荣",
                "汤瑶洁",
                "郝琴和",
                "薛欢一",
                "余钧固",
                "苗琴锦",
                "闵德",
                "柳怡朋",
                "葛茹琴",
                "彭伊莎",
                "殷婉丽",
                "贺素竹",
                "苗函",
                "锺盛",
                "齐钊琰",
                "昝柔克",
                "霍霄",
                "时雅宇",
                "舒倩",
                "强树瑗",
                "舒馨怡",
                "汤彤",
                "成夕",
                "谈悦淳",
                "袁薇娅",
                "花以琴",
                "平琛",
                "蓝琰树",
                "薛和盛",
                "岑凤",
                "席希枝",
                "时琬媛",
                "毕娅",
                "华亮晓",
                "姜黛莹",
                "汪嘉姬",
                "祝茗泓",
                "孙香希",
                "韩宜晶",
                "方滢弘",
                "傅锦夕",
                "云茜树",
                "成希颖",
                "麻雪希",
                "薛雨建",
                "韦辰倩",
                "殷璐",
                "管霞莲",
                "严时皑",
                "宋树",
                "严轮瑶",
                "陈蕊",
                "狄函香",
                "窦贞莉",
                "李苑淇",
                "范铎瑶",
                "毛荔晗",
                "萧泓若",
                "卞策希",
                "曹锦",
                "臧钧媛",
                "和欣香",
                "殷先雄",
                "湛悦娟",
                "余函依",
                "秦瑶",
                "汪丽慧",
                "金珍",
                "张憧咏",
                "章寒",
                "安子倩",
                "季菁风",
                "谢珍",
                "殷娟",
                "水枫",
                "童元茜",
                "韦萍",
                "茅萱",
                "计翰嫣",
                "水钧倩",
                "姚琛媛",
                "康鹏奇",
                "庞清翰",
                "苗怡叶",
                "刁悦文",
                "姜瑛克",
                "舒舒",
                "尤瑾影",
                "昝泽栋",
                "徐惠",
                "童芳姬",
                "锺宁",
                "凤晨",
                "闵莲",
                "杨若",
                "窦娟梦",
                "邬真芳",
                "方爽允",
                "周婉倩",
                "朱函寒",
                "项伯策",
                "云艳欣",
                "屈羽纨",
                "毛希",
                "杨建",
                "蒋雪",
                "卜黛珊",
                "滕芳莹",
                "马蓓",
                "鲁瑶静",
                "贺嘉怡",
                "周姬",
                "梁莹",
                "庞雅岚",
                "喻全霞",
                "俞寒彤",
                "危颖佳",
                "褚之",
                "刁美允",
                "汪腾",
                "伏佳冰",
                "湛芳晗",
                "汪轮",
                "高元",
                "王伯旭",
                "臧怡凡",
                "姜以",
                "奚锦言",
                "赵朋霄",
                "姜玲霞",
                "胡妤雪",
                "路仪致",
                "范勤丹",
                "陈晨瑶",
                "危英晗",
                "郭月芳",
                "支泰",
                "昝茜",
                "祁叶爽",
                "袁启枫",
                "唐玲",
                "余艺婕",
                "章娅云",
                "傅莉莹",
                "魏弘建",
                "朱姬",
                "虞夕榕",
                "鲁丽",
                "卢茹雅",
                "滕行",
                "乐琛",
                "漩奇",
                "梁美美",
                "戴全",
                "安灵影",
                "任惠",
                "席璧全",
                "滕颖贞",
                "麻时佳",
                "季依妍",
                "邱瑶淑",
                "胡荣艳",
                "尹腾影",
                "苏洁盛",
                "张一钊",
                "宋萍月",
                "于瑶婉",
                "柯雪源",
                "罗航淳",
                "屈倩",
                "纪瑶",
                "吴琰梁"

        };
        return name[ThreadLocalRandom.current().nextInt(0, name.length)];
    }
}
