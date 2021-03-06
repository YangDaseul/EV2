package com.genesis.apps.comm.model.constants;

import com.genesis.apps.comm.model.api.developers.EvStatus;
import com.genesis.apps.comm.model.api.gra.CHB_1007;
import com.genesis.apps.comm.model.api.gra.CHB_1008;
import com.genesis.apps.comm.model.api.gra.CHB_1009;
import com.genesis.apps.comm.model.api.gra.CHB_1010;
import com.genesis.apps.comm.model.api.gra.CHB_1011;
import com.genesis.apps.comm.model.api.gra.CHB_1013;
import com.genesis.apps.comm.model.api.gra.CHB_1015;
import com.genesis.apps.comm.model.api.gra.CHB_1016;
import com.genesis.apps.comm.model.api.gra.CHB_1017;
import com.genesis.apps.comm.model.api.gra.CHB_1021;
import com.genesis.apps.comm.model.api.gra.CHB_1022;
import com.genesis.apps.comm.model.api.gra.CHB_1023;
import com.genesis.apps.comm.model.api.gra.CHB_1024;
import com.genesis.apps.comm.model.api.gra.CHB_1025;
import com.genesis.apps.comm.model.api.gra.CHB_1026;
import com.google.gson.Gson;

//
//import com.genesis.apps.comm.model.api.gra.BAR_1001;
//import com.genesis.apps.comm.model.api.gra.BTR_1001;
//import com.genesis.apps.comm.model.api.gra.BTR_1008;
//import com.genesis.apps.comm.model.api.gra.BTR_1009;
//import com.genesis.apps.comm.model.api.gra.BTR_2001;
//import com.genesis.apps.comm.model.api.gra.BTR_2002;
//import com.genesis.apps.comm.model.api.gra.BTR_2003;
//import com.genesis.apps.comm.model.api.gra.CBK_1002;
//import com.genesis.apps.comm.model.api.gra.CBK_1005;
//import com.genesis.apps.comm.model.api.gra.CBK_1006;
//import com.genesis.apps.comm.model.api.gra.CBK_1007;
//import com.genesis.apps.comm.model.api.gra.CBK_1008;
//import com.genesis.apps.comm.model.api.gra.CMN_0001;
//import com.genesis.apps.comm.model.api.gra.CMN_0002;
//import com.genesis.apps.comm.model.api.gra.CMN_0003;
//import com.genesis.apps.comm.model.api.gra.CTT_1002;
//import com.genesis.apps.comm.model.api.gra.CTT_1004;
//import com.genesis.apps.comm.model.api.gra.DDS_1001;
//import com.genesis.apps.comm.model.api.gra.DDS_1002;
//import com.genesis.apps.comm.model.api.gra.DDS_1003;
//import com.genesis.apps.comm.model.api.gra.DDS_1004;
//import com.genesis.apps.comm.model.api.gra.DDS_1005;
//import com.genesis.apps.comm.model.api.gra.DDS_1006;
//import com.genesis.apps.comm.model.api.gra.GNS_1001;
//import com.genesis.apps.comm.model.api.gra.GNS_1002;
//import com.genesis.apps.comm.model.api.gra.GNS_1003;
//import com.genesis.apps.comm.model.api.gra.GNS_1004;
//import com.genesis.apps.comm.model.api.gra.GNS_1005;
//import com.genesis.apps.comm.model.api.gra.GNS_1006;
//import com.genesis.apps.comm.model.api.gra.GNS_1007;
//import com.genesis.apps.comm.model.api.gra.GNS_1008;
//import com.genesis.apps.comm.model.api.gra.GNS_1009;
//import com.genesis.apps.comm.model.api.gra.GNS_1010;
//import com.genesis.apps.comm.model.api.gra.GNS_1011;
//import com.genesis.apps.comm.model.api.gra.GNS_1012;
//import com.genesis.apps.comm.model.api.gra.GNS_1013;
//import com.genesis.apps.comm.model.api.gra.GNS_1014;
//import com.genesis.apps.comm.model.api.gra.GNS_1015;
//import com.genesis.apps.comm.model.api.gra.IST_1002;
//import com.genesis.apps.comm.model.api.gra.IST_1003;
//import com.genesis.apps.comm.model.api.gra.IST_1004;
//import com.genesis.apps.comm.model.api.gra.IST_1005;
//import com.genesis.apps.comm.model.api.gra.LGN_0001;
//import com.genesis.apps.comm.model.api.gra.LGN_0003;
//import com.genesis.apps.comm.model.api.gra.LGN_0005;
//import com.genesis.apps.comm.model.api.gra.MBR_0001;
//import com.genesis.apps.comm.model.api.gra.MYP_0001;
//import com.genesis.apps.comm.model.api.gra.MYP_1005;
//import com.genesis.apps.comm.model.api.gra.MYP_1006;
//import com.genesis.apps.comm.model.api.gra.MYP_2006;
//import com.genesis.apps.comm.model.api.gra.MYP_8001;
//import com.genesis.apps.comm.model.api.gra.MYP_8005;
//import com.genesis.apps.comm.model.api.gra.NOT_0001;
//import com.genesis.apps.comm.model.api.gra.NOT_0003;
//import com.genesis.apps.comm.model.api.gra.PUB_1001;
//import com.genesis.apps.comm.model.api.gra.PUB_1002;
//import com.genesis.apps.comm.model.api.gra.PUB_1003;
//import com.genesis.apps.comm.model.api.gra.REQ_1001;
//import com.genesis.apps.comm.model.api.gra.REQ_1002;
//import com.genesis.apps.comm.model.api.gra.REQ_1003;
//import com.genesis.apps.comm.model.api.gra.REQ_1004;
//import com.genesis.apps.comm.model.api.gra.REQ_1005;
//import com.genesis.apps.comm.model.api.gra.REQ_1007;
//import com.genesis.apps.comm.model.api.gra.REQ_1008;
//import com.genesis.apps.comm.model.api.gra.REQ_1009;
//import com.genesis.apps.comm.model.api.gra.REQ_1010;
//import com.genesis.apps.comm.model.api.gra.REQ_1011;
//import com.genesis.apps.comm.model.api.gra.REQ_1012;
//import com.genesis.apps.comm.model.api.gra.REQ_1013;
//import com.genesis.apps.comm.model.api.gra.REQ_1014;
//import com.genesis.apps.comm.model.api.gra.REQ_1015;
//import com.genesis.apps.comm.model.api.gra.REQ_1017;
//import com.genesis.apps.comm.model.api.gra.REQ_1018;
//import com.genesis.apps.comm.model.api.gra.SOS_1001;
//import com.genesis.apps.comm.model.api.gra.SOS_1002;
//import com.genesis.apps.comm.model.api.gra.SOS_1004;
//import com.genesis.apps.comm.model.api.gra.STO_1001;
//import com.genesis.apps.comm.model.api.gra.STO_1002;
//import com.genesis.apps.comm.model.api.gra.STO_1003;
//import com.genesis.apps.comm.model.api.gra.VOC_1001;
//import com.genesis.apps.comm.model.api.gra.VOC_1002;
//import com.genesis.apps.comm.model.api.gra.VOC_1003;
//import com.genesis.apps.comm.model.api.gra.VOC_1004;
//import com.genesis.apps.comm.model.api.gra.VOC_1005;
//import com.genesis.apps.comm.model.api.gra.WRT_1001;
//import com.genesis.apps.comm.model.api.gra.WSH_1001;
//import com.genesis.apps.comm.model.api.gra.WSH_1002;
//import com.genesis.apps.comm.model.api.gra.WSH_1003;
//import com.genesis.apps.comm.model.api.gra.WSH_1004;
//import com.genesis.apps.comm.model.api.gra.WSH_1005;
//import com.genesis.apps.comm.model.api.gra.WSH_1006;
//import com.genesis.apps.comm.model.api.gra.WSH_1007;
//import com.genesis.apps.comm.model.api.gra.WSH_1008;
//import com.genesis.apps.comm.model.vo.RentStatusVO;
//import com.google.gson.Gson;
//
public class TestCode {
//
//    public static REQ_1017.Response REQ_1017 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"repCostList\": [\n" +
//            "    {\n" +
//            "      \"mdlNm\": \"GV80\",\n" +
//            "      \"costList\": [\n" +
//            "        {\n" +
//            "          \"wkNm\": \"오토미션 오일 (장비 사용)\",\n" +
//            "          \"rprAmt\": \"23,000\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "          \"wkNm\": \"윈드쉴드 와이퍼블레이드\",\n" +
//            "          \"rprAmt\": \"40,000\"\n" +
//            "        }\n" +
//            "      ]\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"mdlNm\": \"G90\",\n" +
//            "      \"costList\": [\n" +
//            "        {\n" +
//            "          \"wkNm\": \"윈드쉴드 와이퍼블레이드\",\n" +
//            "          \"rprAmt\": \"23,000\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "          \"wkNm\": \"와이퍼 블레이드교환 \",\n" +
//            "          \"rprAmt\": \"40,000\"\n" +
//            "        }\n" +
//            "      ]\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",REQ_1017.Response.class);
//
//
//
//    public static VOC_1001.Response VOC_1001 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"vhclList\": [\n" +
//            "    {\n" +
//            "      \"vin\": \"TEST1452154785412\",\n" +
//            "      \"carRgstNO\": \"16조6840\",\n" +
//            "      \"mdlNm\": \"gv80\",\n" +
//            "      \"mdlCd\": \"gv80 2222\",\n" +
//            "      \"recvYmd\": \"20201118\",\n" +
//            "      \"csmrCarRelCd\": \"1\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}", VOC_1001.Response.class);
//
//    public static VOC_1002.Response VOC_1002 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}", VOC_1002.Response.class);
//
//    public static VOC_1003.Response VOC_1003 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"dfctList\": [\n" +
//            "    {\n" +
//            "      \"inpSt\": \"1\",\n" +
//            "      \"inpDate\": \"20201212\",\n" +
//            "      \"inpDesc\": \"울랄라뾰롱뾰롱\",\n" +
//            "      \"csmrNm\": \"홍길동\",\n" +
//            "      \"csmrTymd\": \"18180101\",\n" +
//            "      \"emlAdr\": \"asdfad3n@fenew.com\",\n" +
//            "      \"rdwNmZip\": \"123678\",\n" +
//            "      \"rdwNmAdr\": \"도로명주소\",\n" +
//            "      \"rdwNmDtlAdr\": \"도로명상세주소\",\n" +
//            "      \"regnTn\": \"010\",\n" +
//            "      \"frtDgtTn\": \"3245\",\n" +
//            "      \"realDgtTn\": \"6554\",\n" +
//            "      \"recvDt\": \"20201010\",\n" +
//            "      \"carNm\": \"그니까 차량이름이 도대체 뭔ㄷ데\",\n" +
//            "      \"crnVehlCd\": \"GV30\",\n" +
//            "      \"mdYyyy\": \"20201020\",\n" +
//            "      \"trvgDist\": \"24422\",\n" +
//            "      \"carNo\": \"234구32423\",\n" +
//            "      \"vin\": \"TESTVIN0011111\",\n" +
//            "      \"wpa\": \"서울시\",\n" +
//            "      \"admz\": \"마포구\",\n" +
//            "      \"flawCd\": \"A\",\n" +
//            "      \"wkr1Nm\": \"아이언맨\",\n" +
//            "      \"wk1StrtDt\": \"20201003\",\n" +
//            "      \"wk1Dt\": \"20201004\",\n" +
//            "      \"wk1TrvgDist\": \"9087\",\n" +
//            "      \"wk1Caus\": \"차축부러짐\",\n" +
//            "      \"wk1Dtl\": \"비브라늄으로 교체\",\n" +
//            "      \"wkr2Nm\": \"센트럴도그마\",\n" +
//            "      \"wk2StrtDt\": \"20201101\",\n" +
//            "      \"wk2Dt\": \"20201111\",\n" +
//            "      \"wk2TrvgDist\": \"99999\",\n" +
//            "      \"wk2Caus\": \"완전히침묵\",\n" +
//            "      \"wk2Dtl\": \"엔진오버홀\",\n" +
//            "      \"wkr3Nm\": \"\",\n" +
//            "      \"wk3StrtDt\": \"\",\n" +
//            "      \"wk3Dt\": \"\",\n" +
//            "      \"wk32TrvgDist\": \"\",\n" +
//            "      \"wk3Caus\": \"\",\n" +
//            "      \"wk3Dtl\": \"\",\n" +
//            "      \"wkCntFth\": \"Y\",\n" +
//            "      \"wkCnt\": \"9\",\n" +
//            "      \"wkPeriod\": \"87\",\n" +
//            "      \"prnInfoAgreeFlg\": \"Y\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"inpSt\": \"2\",\n" +
//            "      \"inpDate\": \"20201231\",\n" +
//            "      \"inpDesc\": \"도로명주소싫어\",\n" +
//            "      \"vin\": \"TESTVIN002222\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"inpSt\": \"2\",\n" +
//            "      \"inpDate\": \"20201212\",\n" +
//            "      \"inpDesc\": \"울랄라뾰롱뾰롱\",\n" +
//            "      \"vin\": \"TESTVIN003333\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"inpSt\": \"2\",\n" +
//            "      \"inpDate\": \"20201231\",\n" +
//            "      \"inpDesc\": \"도로명주소싫어\",\n" +
//            "      \"vin\": \"TESTVIN044444\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"inpSt\": \"2\",\n" +
//            "      \"inpDate\": \"20201212\",\n" +
//            "      \"inpDesc\": \"울랄라뾰롱뾰롱\",\n" +
//            "      \"vin\": \"TESTVIN005551\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"inpSt\": \"2\",\n" +
//            "      \"inpDate\": \"20201231\",\n" +
//            "      \"inpDesc\": \"도로명주소싫어\",\n" +
//            "      \"vin\": \"TESTVIN06666\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}", VOC_1003.Response.class);
//
//    public static VOC_1004.Response VOC_1004 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"termList\": [\n" +
//            "    {\n" +
//            "      \"termVer\": \"01.00\",\n" +
//            "      \"termCd\": \"2000\",\n" +
//            "      \"termNm\": \"약관 1호\",\n" +
//            "      \"termEsnAgmtYn\": \"Y\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"termVer\": \"01.00\",\n" +
//            "      \"termCd\": \"3000\",\n" +
//            "      \"termNm\": \"약관 2호\",\n" +
//            "      \"termEsnAgmtYn\": \"Y\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"termVer\": \"01.00\",\n" +
//            "      \"termCd\": \"2000\",\n" +
//            "      \"termNm\": \"약관 3호\",\n" +
//            "      \"termEsnAgmtYn\": \"Y\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}", VOC_1004.Response.class);
//    public static VOC_1005.Response VOC_1005 = new Gson().fromJson("", VOC_1005.Response.class);
//
//    public static WSH_1001.Response WSH_1001 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"godsList\": [\n" +
//            "    {\n" +
//            "      \"godsSeqNo\": \"11112323\",\n" +
//            "      \"cmpyCd\": \"SONAX\",\n" +
//            "      \"godsNm\": \"상품명1\",\n" +
//            "      \"dsctNm\": \"할인명1\",\n" +
//            "      \"godsImgUri\": \"www.naver.com\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"godsSeqNo\": \"22224664\",\n" +
//            "      \"cmpyCd\": \"SONAX\",\n" +
//            "      \"godsNm\": \"상품명2\",\n" +
//            "      \"dsctNm\": \"할인명2\",\n" +
//            "      \"godsImgUri\": \"www.naver.com\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"godsSeqNo\": \"33337477\",\n" +
//            "      \"cmpyCd\": \"SONAX\",\n" +
//            "      \"godsNm\": \"상품명3\",\n" +
//            "      \"dsctNm\": \"할인명3\",\n" +
//            "      \"godsImgUri\": \"www.naver.com\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"godsSeqNo\": \"44443636\",\n" +
//            "      \"cmpyCd\": \"SONAX\",\n" +
//            "      \"godsNm\": \"상품명4\",\n" +
//            "      \"dsctNm\": \"할인명4\",\n" +
//            "      \"godsImgUri\": \"www.naver.com\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}", WSH_1001.Response.class);
//
//    public static WSH_1002.Response WSH_1002 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"brnhList\": [\n" +
//            "    {\n" +
//            "      \"cmpyCd\": \"SONAX\",\n" +
//            "      \"brnhCd\": \"asdf1111\",\n" +
//            "      \"brnhNm\": \"8개 목록 중에 1호지점\",\n" +
//            "      \"brnhAddr\": \"서울시 구로구 디지털동 아날로그 건물 1층 맨 앞자리\",\n" +
//            "      \"telNo\": \"03211118764\",\n" +
//            "      \"brnhX\": \"37.4813022\",\n" +
//            "      \"brnhY\": \"126.8821687\",\n" +
//            "      \"dist\": \"1km\",\n" +
//            "      \"brnhIntro\": \"이 지점은 잘났읍니다.\",\n" +
//            "      \"brnhImgUri1\": \"www.google.com\",\n" +
//            "      \"brnhImgUri2\": \"www.google.com\",\n" +
//            "      \"brnhImgUri3\": \"www.google.com\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cmpyCd\": \"SONAX\",\n" +
//            "      \"brnhCd\": \"jkkj2222\",\n" +
//            "      \"brnhNm\": \"목록 2번째 지점\",\n" +
//            "      \"brnhAddr\": \"평양시 디지털동 아날로그 건물 2층 맨 뒷자리\",\n" +
//            "      \"telNo\": \"03122224856\",\n" +
//            "      \"brnhX\": \"37.5642135\",\n" +
//            "      \"brnhY\": \"127.0016985\",\n" +
//            "      \"dist\": \"99km\",\n" +
//            "      \"brnhIntro\": \"포기하고 서울시청 좌표 꽂았다\",\n" +
//            "      \"brnhImgUri1\": \"www.google.com\",\n" +
//            "      \"brnhImgUri2\": \"www.google.com\",\n" +
//            "      \"brnhImgUri3\": \"www.google.com\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cmpyCd\": \"SONAX\",\n" +
//            "      \"brnhCd\": \"3333emem\",\n" +
//            "      \"brnhNm\": \"3333333333호점인가봐요\",\n" +
//            "      \"brnhAddr\": \"벌써부터 주소드립치기 귀찮아져서 우리 사무실 지하1층\",\n" +
//            "      \"telNo\": \"03233335028\",\n" +
//            "      \"brnhX\": \"37.4813022\",\n" +
//            "      \"brnhY\": \"126.8821687\",\n" +
//            "      \"dist\": \"35km\",\n" +
//            "      \"brnhIntro\": \"이 지점은 어디일까요.\",\n" +
//            "      \"brnhImgUri1\": \"www.google.com\",\n" +
//            "      \"brnhImgUri2\": \"www.google.com\",\n" +
//            "      \"brnhImgUri3\": \"www.google.com\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cmpyCd\": \"SONAX\",\n" +
//            "      \"brnhCd\": \"444dsjeg\",\n" +
//            "      \"brnhNm\": \"4호점차례\",\n" +
//            "      \"brnhAddr\": \"스크롤을 돌리려고 아무동 아무번지\",\n" +
//            "      \"telNo\": \"03244445029\",\n" +
//            "      \"brnhX\": \"37.4813022\",\n" +
//            "      \"brnhY\": \"126.8821687\",\n" +
//            "      \"dist\": \"99km\",\n" +
//            "      \"brnhIntro\": \"이 지점은 우리 사무실이랑 좌표가 같아요.\",\n" +
//            "      \"brnhImgUri1\": \"www.google.com\",\n" +
//            "      \"brnhImgUri2\": \"www.google.com\",\n" +
//            "      \"brnhImgUri3\": \"www.google.com\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cmpyCd\": \"SONAX\",\n" +
//            "      \"brnhCd\": \"55555555\",\n" +
//            "      \"brnhNm\": \"5호점\",\n" +
//            "      \"brnhAddr\": \"서울시 헐값구 월세싼동 자기건물 입구자리\",\n" +
//            "      \"telNo\": \"03255554949\",\n" +
//            "      \"brnhX\": \"37.58885149105208\",\n" +
//            "      \"brnhY\": \"126.9440832693006\",\n" +
//            "      \"dist\": \"19km\",\n" +
//            "      \"brnhIntro\": \"홍제역 좌표 박아둠\",\n" +
//            "      \"brnhImgUri1\": \"www.google.com\",\n" +
//            "      \"brnhImgUri2\": \"www.google.com\",\n" +
//            "      \"brnhImgUri3\": \"www.google.com\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cmpyCd\": \"SONAX\",\n" +
//            "      \"brnhCd\": \"6666rlrl\",\n" +
//            "      \"brnhNm\": \"6호점\",\n" +
//            "      \"brnhAddr\": \"128자리 지점주소\",\n" +
//            "      \"telNo\": \"03266666666\",\n" +
//            "      \"brnhX\": \"36.4813022\",\n" +
//            "      \"brnhY\": \"126.8821687\",\n" +
//            "      \"dist\": \"45km\",\n" +
//            "      \"brnhIntro\": \"이 지점은 아마도 경기 남부 좌표\",\n" +
//            "      \"brnhImgUri1\": \"www.google.com\",\n" +
//            "      \"brnhImgUri2\": \"www.google.com\",\n" +
//            "      \"brnhImgUri3\": \"www.google.com\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cmpyCd\": \"SONAX\",\n" +
//            "      \"brnhCd\": \"77772424\",\n" +
//            "      \"brnhNm\": \"7777777호점\",\n" +
//            "      \"brnhAddr\": \"경기도 성남시 분당구 비싼동네 비싼땅 1번지\",\n" +
//            "      \"telNo\": \"03211777717\",\n" +
//            "      \"brnhX\": \"37.39480184590172\",\n" +
//            "      \"brnhY\": \"127.11115315395405\",\n" +
//            "      \"dist\": \"99km\",\n" +
//            "      \"brnhIntro\": \"이 지점은 갑부인가봐요.\",\n" +
//            "      \"brnhImgUri1\": \"www.google.com\",\n" +
//            "      \"brnhImgUri2\": \"www.google.com\",\n" +
//            "      \"brnhImgUri3\": \"www.google.com\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cmpyCd\": \"SONAX\",\n" +
//            "      \"brnhCd\": \"8자리지점코드\",\n" +
//            "      \"brnhNm\": \"8888마지막 더미다 \",\n" +
//            "      \"brnhAddr\": \"울ㄹ랄라시 저세상구 호러동 깨진구 집에가고싶다\",\n" +
//            "      \"telNo\": \"03200018888\",\n" +
//            "      \"brnhX\": \"33.4813022\",\n" +
//            "      \"brnhY\": \"126.8821687\",\n" +
//            "      \"dist\": \"999km\",\n" +
//            "      \"brnhIntro\": \"좌표 xy가 이상하다는 걸 좌표장난으로 x에 0넣고 눈치챔\",\n" +
//            "      \"brnhImgUri1\": \"www.google.com\",\n" +
//            "      \"brnhImgUri2\": \"www.google.com\",\n" +
//            "      \"brnhImgUri3\": \"www.google.com\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}", WSH_1002.Response.class);
//
//    public static WSH_1003.Response WSH_1003 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}", WSH_1003.Response.class);
//
//    public static WSH_1004.Response WSH_1004 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"rsvtList\":[\n" +
//            "    {\n" +
//            "      \"rsvtSeqNo\": \"16자리일련번호a\",\n" +
//            "      \"rsvtStusCd\": \"1000\",\n" +
//            "      \"rsvtDtm\": \"20200101130000\",\n" +
//            "      \"paymtWayCd\": \"1000\",\n" +
//            "      \"paymtCost\": \"100000\",\n" +
//            "      \"cmpyCd\": \"SONAX\",\n" +
//            "      \"brnhCd\": \"1호점:지구반대\",\n" +
//            "      \"brnhNm\": \"지구반대쪽출장소\",\n" +
//            "      \"telNo\": \"031011114576\",\n" +
//            "      \"godsSeqNo\": \"16자리상품번호\",\n" +
//            "      \"godsNm\": \"서비스 이름1호\",\n" +
//            "      \"godsImgUri\": \"www.naver.com\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtSeqNo\": \"16자리일련번호a11\",\n" +
//            "      \"rsvtStusCd\": \"2000\",\n" +
//            "      \"rsvtDtm\": \"20200101130000\",\n" +
//            "      \"paymtWayCd\": \"1000\",\n" +
//            "      \"paymtCost\": \"100000\",\n" +
//            "      \"cmpyCd\": \"SONAX\",\n" +
//            "      \"brnhCd\": \"2호점:지하깊음\",\n" +
//            "      \"brnhNm\": \"지하10층영업소\",\n" +
//            "      \"telNo\": \"03122224576\",\n" +
//            "      \"godsSeqNo\": \"16자리상품번호\",\n" +
//            "      \"godsNm\": \"서비스 두번째 거\",\n" +
//            "      \"godsImgUri\": \"www.naver.com\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtSeqNo\": \"16자리일련번호13a\",\n" +
//            "      \"rsvtStusCd\": \"9000\",\n" +
//            "      \"rsvtDtm\": \"20200101130000\",\n" +
//            "      \"paymtWayCd\": \"1000\",\n" +
//            "      \"paymtCost\": \"100000\",\n" +
//            "      \"cmpyCd\": \"SONAX\",\n" +
//            "      \"brnhCd\": \"3호점:카드싫어\",\n" +
//            "      \"brnhNm\": \"카드안받는영업점\",\n" +
//            "      \"telNo\": \"0233337195\",\n" +
//            "      \"godsSeqNo\": \"16자리상품번호\",\n" +
//            "      \"godsNm\": \"현금만되는\n초특가풀옵션서비스\",\n" +
//            "      \"godsImgUri\": \"www.naver.com\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtSeqNo\": \"16자리일련번호3113\",\n" +
//            "      \"rsvtStusCd\": \"1000\",\n" +
//            "      \"rsvtDtm\": \"20200101130000\",\n" +
//            "      \"paymtWayCd\": \"1000\",\n" +
//            "      \"paymtCost\": \"100000\",\n" +
//            "      \"cmpyCd\": \"SONAX\",\n" +
//            "      \"brnhCd\": \"4호점:울랄라\",\n" +
//            "      \"brnhNm\": \"울랄라뾰룡뾰룔 지점\",\n" +
//            "      \"telNo\": \"0244442957\",\n" +
//            "      \"godsSeqNo\": \"16자리상품번호\",\n" +
//            "      \"godsNm\": \"울랄라뾰룡뾰룔 서비스\",\n" +
//            "      \"godsImgUri\": \"www.naver.com\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}", WSH_1004.Response.class);
//
//    public static WSH_1005.Response WSH_1005 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}", WSH_1005.Response.class);
//
//    public static WSH_1006.Response WSH_1006 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}", WSH_1006.Response.class);
//
//    public static WSH_1007.Response WSH_1007 = new Gson().fromJson("", WSH_1007.Response.class);
//
//    public static WSH_1008.Response WSH_1008 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}", WSH_1008.Response.class);
//
//    public static DDS_1001.Response DDS_1001 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"transId\": \"서버 맘대로 20글자\",\n" +
//            "  \"vin\": \"차대번호 17자리\",\n" +
//            "  \"carRegNo\": \"차량번호 20자리\",\n" +
//            "  \"mdlNm\": \"차량명 50자리\",\n" +
//            "  \"reqDivCd\": \"RT\",\n" +
//            "  \"svcStusCd\": \"1410\",\n" +
//            "  \"rsvDt\": \"202012312359\",\n" +
//            "  \"driverNm\": \"기사이름 64글자\",\n" +
//            "  \"driverMdn\": \"010-2424-5432\",\n" +
//            "  \"posInfo\": [\n" +
//            "    {\n" +
//            "      \"posDivCd\": \"DEPT\",\n" +
//            "      \"latCoord\": \"37.58885149105208\",\n" +
//            "      \"lonCoord\": \"126.9440832693006\",\n" +
//            "      \"addrLotNo\": \"서울 특별시 무슨구더라 홍제동 홍제역 지번 주소 xxx-yy\",\n" +
//            "      \"addrBldNm\": \"홍제역 도로명 주소\",\n" +
//            "      \"addrDtl\": \"아마도 제일 가까운 유료 주차장 찾아가기 퀘스트 데이터 길이 테스트\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"posDivCd\": \"DEST\",\n" +
//            "      \"latCoord\": \"37.4813022\",\n" +
//            "      \"lonCoord\": \"126.8821687\",\n" +
//            "      \"addrLotNo\": \"서울 특별시 금천구 가산동 440-4\",\n" +
//            "      \"addrBldNm\": \"서울 구로구 라이온스밸리 도로명주소\",\n" +
//            "      \"addrDtl\": \"우림 라이온스 밸리 A동 지하 39층 나락주차장 F구역 53번\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}", DDS_1001.Response.class);
//
//    public static DDS_1002.Response DDS_1002 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"transId\": \"enTwi3ktn3iaoen2i4io\"\n" +
//            "}", DDS_1002.Response.class);
//
//    public static DDS_1003.Response DDS_1003 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"totalCnt\": \"10byte\",\n" +
//            "  \"listCnt\": \"10byte\",\n" +
//            "  \"svcInfo\": [\n" +
//            "    {\n" +
//            "      \"transId\": \"예약번호20byte01\",\n" +
//            "      \"mbrMgmtNo\": \"16byte0001\",\n" +
//            "      \"reqDivCd\": \"RT\",\n" +
//            "      \"svcStusCd\": \"1300\",\n" +
//            "      \"stusNm\": \"운행중\",\n" +
//            "      \"vin\": \"2wert345r098wert7\",\n" +
//            "      \"carRegNo\": \"234고 3543\",\n" +
//            "      \"mdlNm\": \"GV70\",\n" +
//            "      \"expPrice\": \"20000\",\n" +
//            "      \"bluMbrDcPrice\": \"5000\",\n" +
//            "      \"rdwnSpcDcPrice\": \"\",\n" +
//            "      \"payDiv\": \"CARD\",\n" +
//            "      \"payPrice\": \"15000\",\n" +
//            "      \"payDt\": \"202012202200\",\n" +
//            "      \"updtDt\": \"202012202300\",\n" +
//            "      \"rsvDt\": \"202012202200\",\n" +
//            "      \"rgstDt\": \"202012201300\",\n" +
//            "      \"posInfo\": [\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEPT\",\n" +
//            "          \"latCoord\": \"37.58885149105208\",\n" +
//            "          \"lonCoord\": \"126.9440832693006\",\n" +
//            "          \"addrLotNo\": \"홍제역 지번 주소\",\n" +
//            "          \"addrBldNm\": \"홍제역 도로명 주소\",\n" +
//            "          \"addrDtl\": \" 1동 2호\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEST\",\n" +
//            "          \"latCoord\": \"37.4813022\",\n" +
//            "          \"lonCoord\": \"126.8821687\",\n" +
//            "          \"addrLotNo\": \"서울 구로구 라이온스밸리 지번주소\",\n" +
//            "          \"addrBldNm\": \"서울 구로구 라이온스밸리 도로명주소\",\n" +
//            "          \"addrDtl\": \"지하주차장이겠지\"\n" +
//            "        }\n" +
//            "      ]\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"transId\": \"예약번호20byte02\",\n" +
//            "      \"mbrMgmtNo\": \"16byte0001\",\n" +
//            "      \"reqDivCd\": \"RT\",\n" +
//            "      \"svcStusCd\": \"1310\",\n" +
//            "      \"stusNm\": \"완료\",\n" +
//            "      \"vin\": \"2wert345r098wert7\",\n" +
//            "      \"carRegNo\": \"234고 3543\",\n" +
//            "      \"mdlNm\": \"GV72\",\n" +
//            "      \"expPrice\": \"20000\",\n" +
//            "      \"bluMbrDcPrice\": \"5000\",\n" +
//            "      \"rdwnSpcDcPrice\": \"\",\n" +
//            "      \"payDiv\": \"CARD\",\n" +
//            "      \"payPrice\": \"15000\",\n" +
//            "      \"payDt\": \"202012202200\",\n" +
//            "      \"updtDt\": \"202012202300\",\n" +
//            "      \"rsvDt\": \"202012202200\",\n" +
//            "      \"rgstDt\": \"202012201300\",\n" +
//            "      \"posInfo\": [\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEPT\",\n" +
//            "          \"latCoord\": \"37.58885149105208\",\n" +
//            "          \"lonCoord\": \"126.9440832693006\",\n" +
//            "          \"addrLotNo\": \"홍제역 지번 주소\",\n" +
//            "          \"addrBldNm\": \"홍제역 도로명 주소\",\n" +
//            "          \"addrDtl\": \" 1동 2호\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEST\",\n" +
//            "          \"latCoord\": \"37.4813022\",\n" +
//            "          \"lonCoord\": \"126.8821687\",\n" +
//            "          \"addrLotNo\": \"서울 구로구 라이온스밸리 지번주소\",\n" +
//            "          \"addrBldNm\": \"서울 구로구 라이온스밸리 도로명주소\",\n" +
//            "          \"addrDtl\": \"지하주차장이겠지\"\n" +
//            "        }\n" +
//            "      ]\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"transId\": \"예약번호20byte03\",\n" +
//            "      \"mbrMgmtNo\": \"16byte0001\",\n" +
//            "      \"reqDivCd\": \"RT\",\n" +
//            "      \"svcStusCd\": \"1400\",\n" +
//            "      \"stusNm\": \"취소\",\n" +
//            "      \"vin\": \"2wert345r098wert7\",\n" +
//            "      \"carRegNo\": \"234고 3543\",\n" +
//            "      \"mdlNm\": \"GV73\",\n" +
//            "      \"expPrice\": \"20000\",\n" +
//            "      \"bluMbrDcPrice\": \"0\",\n" +
//            "      \"rdwnSpcDcPrice\": \"\",\n" +
//            "      \"payDiv\": \"CARD\",\n" +
//            "      \"payPrice\": \"20000\",\n" +
//            "      \"payDt\": \"202012202200\",\n" +
//            "      \"updtDt\": \"202012202300\",\n" +
//            "      \"rsvDt\": \"202012202200\",\n" +
//            "      \"rgstDt\": \"202012201300\",\n" +
//            "      \"posInfo\": [\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEPT\",\n" +
//            "          \"latCoord\": \"37.58885149105208\",\n" +
//            "          \"lonCoord\": \"126.9440832693006\",\n" +
//            "          \"addrLotNo\": \"홍제역 지번 주소\",\n" +
//            "          \"addrBldNm\": \"홍제역 도로명 주소\",\n" +
//            "          \"addrDtl\": \" 1동 2호\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEST\",\n" +
//            "          \"latCoord\": \"37.4813022\",\n" +
//            "          \"lonCoord\": \"126.8821687\",\n" +
//            "          \"addrLotNo\": \"서울 구로구 라이온스밸리 지번주소\",\n" +
//            "          \"addrBldNm\": \"서울 구로구 라이온스밸리 도로명주소\",\n" +
//            "          \"addrDtl\": \"지하주차장이겠지\"\n" +
//            "        }\n" +
//            "      ]\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"transId\": \"예약번호20byte04\",\n" +
//            "      \"mbrMgmtNo\": \"16byte0001\",\n" +
//            "      \"reqDivCd\": \"RT\",\n" +
//            "      \"svcStusCd\": \"1400\",\n" +
//            "      \"stusNm\": \"취소\",\n" +
//            "      \"vin\": \"2wert345r098wert7\",\n" +
//            "      \"carRegNo\": \"234고 3543\",\n" +
//            "      \"mdlNm\": \"GV74\",\n" +
//            "      \"expPrice\": \"20000\",\n" +
//            "      \"bluMbrDcPrice\": \"5000\",\n" +
//            "      \"rdwnSpcDcPrice\": \"\",\n" +
//            "      \"payDiv\": \"CARD\",\n" +
//            "      \"payPrice\": \"15000\",\n" +
//            "      \"payDt\": \"202012202200\",\n" +
//            "      \"updtDt\": \"202012202300\",\n" +
//            "      \"rsvDt\": \"202012202200\",\n" +
//            "      \"rgstDt\": \"202012201300\",\n" +
//            "      \"posInfo\": [\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEPT\",\n" +
//            "          \"latCoord\": \"37.58885149105208\",\n" +
//            "          \"lonCoord\": \"126.9440832693006\",\n" +
//            "          \"addrLotNo\": \"홍제역 지번 주소\",\n" +
//            "          \"addrBldNm\": \"홍제역 도로명 주소\",\n" +
//            "          \"addrDtl\": \" 1동 2호\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEST\",\n" +
//            "          \"latCoord\": \"37.4813022\",\n" +
//            "          \"lonCoord\": \"126.8821687\",\n" +
//            "          \"addrLotNo\": \"서울 구로구 라이온스밸리 지번주소\",\n" +
//            "          \"addrBldNm\": \"서울 구로구 라이온스밸리 도로명주소\",\n" +
//            "          \"addrDtl\": \"지하주차장이겠지\"\n" +
//            "        }\n" +
//            "      ]\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"transId\": \"예약번호20byte05\",\n" +
//            "      \"mbrMgmtNo\": \"16byte0001\",\n" +
//            "      \"reqDivCd\": \"RT\",\n" +
//            "      \"svcStusCd\": \"1310\",\n" +
//            "      \"stusNm\": \"완료\",\n" +
//            "      \"vin\": \"2wert345r098wert7\",\n" +
//            "      \"carRegNo\": \"234고 3543\",\n" +
//            "      \"mdlNm\": \"GV75\",\n" +
//            "      \"expPrice\": \"20000\",\n" +
//            "      \"bluMbrDcPrice\": \"5000\",\n" +
//            "      \"rdwnSpcDcPrice\": \"\",\n" +
//            "      \"payDiv\": \"CARD\",\n" +
//            "      \"payPrice\": \"15000\",\n" +
//            "      \"payDt\": \"202012202200\",\n" +
//            "      \"updtDt\": \"202012202300\",\n" +
//            "      \"rsvDt\": \"202012202200\",\n" +
//            "      \"rgstDt\": \"202012201300\",\n" +
//            "      \"posInfo\": [\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEPT\",\n" +
//            "          \"latCoord\": \"37.58885149105208\",\n" +
//            "          \"lonCoord\": \"126.9440832693006\",\n" +
//            "          \"addrLotNo\": \"홍제역 지번 주소\",\n" +
//            "          \"addrBldNm\": \"홍제역 도로명 주소\",\n" +
//            "          \"addrDtl\": \" 1동 2호\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEST\",\n" +
//            "          \"latCoord\": \"37.4813022\",\n" +
//            "          \"lonCoord\": \"126.8821687\",\n" +
//            "          \"addrLotNo\": \"서울 구로구 라이온스밸리 지번주소\",\n" +
//            "          \"addrBldNm\": \"서울 구로구 라이온스밸리 도로명주소\",\n" +
//            "          \"addrDtl\": \"지하주차장이겠지\"\n" +
//            "        }\n" +
//            "      ]\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"transId\": \"예약번호20byte06\",\n" +
//            "      \"mbrMgmtNo\": \"16byte0001\",\n" +
//            "      \"reqDivCd\": \"RT\",\n" +
//            "      \"svcStusCd\": \"1310\",\n" +
//            "      \"stusNm\": \"완료\",\n" +
//            "      \"vin\": \"2wert345r098wert7\",\n" +
//            "      \"carRegNo\": \"234고 3543\",\n" +
//            "      \"mdlNm\": \"GV76\",\n" +
//            "      \"expPrice\": \"20000\",\n" +
//            "      \"bluMbrDcPrice\": \"5000\",\n" +
//            "      \"rdwnSpcDcPrice\": \"\",\n" +
//            "      \"payDiv\": \"CARD\",\n" +
//            "      \"payPrice\": \"15000\",\n" +
//            "      \"payDt\": \"202012202200\",\n" +
//            "      \"updtDt\": \"202012202300\",\n" +
//            "      \"rsvDt\": \"202012202200\",\n" +
//            "      \"rgstDt\": \"202012201300\",\n" +
//            "      \"posInfo\": [\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEPT\",\n" +
//            "          \"latCoord\": \"37.58885149105208\",\n" +
//            "          \"lonCoord\": \"126.9440832693006\",\n" +
//            "          \"addrLotNo\": \"홍제역 지번 주소\",\n" +
//            "          \"addrBldNm\": \"홍제역 도로명 주소\",\n" +
//            "          \"addrDtl\": \" 1동 2호\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEST\",\n" +
//            "          \"latCoord\": \"37.4813022\",\n" +
//            "          \"lonCoord\": \"126.8821687\",\n" +
//            "          \"addrLotNo\": \"서울 구로구 라이온스밸리 지번주소\",\n" +
//            "          \"addrBldNm\": \"서울 구로구 라이온스밸리 도로명주소\",\n" +
//            "          \"addrDtl\": \"지하주차장이겠지\"\n" +
//            "        }\n" +
//            "      ]\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"transId\": \"예약번호20byte07\",\n" +
//            "      \"mbrMgmtNo\": \"16byte0001\",\n" +
//            "      \"reqDivCd\": \"RT\",\n" +
//            "      \"svcStusCd\": \"1310\",\n" +
//            "      \"stusNm\": \"완료\",\n" +
//            "      \"vin\": \"2wert345r098wert7\",\n" +
//            "      \"carRegNo\": \"234고 3543\",\n" +
//            "      \"mdlNm\": \"GV77\",\n" +
//            "      \"expPrice\": \"20000\",\n" +
//            "      \"bluMbrDcPrice\": \"5000\",\n" +
//            "      \"rdwnSpcDcPrice\": \"\",\n" +
//            "      \"payDiv\": \"CARD\",\n" +
//            "      \"payPrice\": \"15000\",\n" +
//            "      \"payDt\": \"202012202200\",\n" +
//            "      \"updtDt\": \"202012202300\",\n" +
//            "      \"rsvDt\": \"202012202200\",\n" +
//            "      \"rgstDt\": \"202012201300\",\n" +
//            "      \"posInfo\": [\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEPT\",\n" +
//            "          \"latCoord\": \"37.58885149105208\",\n" +
//            "          \"lonCoord\": \"126.9440832693006\",\n" +
//            "          \"addrLotNo\": \"홍제역 지번 주소\",\n" +
//            "          \"addrBldNm\": \"홍제역 도로명 주소\",\n" +
//            "          \"addrDtl\": \" 1동 2호\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEST\",\n" +
//            "          \"latCoord\": \"37.4813022\",\n" +
//            "          \"lonCoord\": \"126.8821687\",\n" +
//            "          \"addrLotNo\": \"서울 구로구 라이온스밸리 지번주소\",\n" +
//            "          \"addrBldNm\": \"서울 구로구 라이온스밸리 도로명주소\",\n" +
//            "          \"addrDtl\": \"지하주차장이겠지\"\n" +
//            "        }\n" +
//            "      ]\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"transId\": \"예약번호20byte08\",\n" +
//            "      \"mbrMgmtNo\": \"16byte0001\",\n" +
//            "      \"reqDivCd\": \"RT\",\n" +
//            "      \"svcStusCd\": \"1310\",\n" +
//            "      \"stusNm\": \"완료\",\n" +
//            "      \"vin\": \"2wert345r098wert7\",\n" +
//            "      \"carRegNo\": \"234고 3543\",\n" +
//            "      \"mdlNm\": \"GV78\",\n" +
//            "      \"expPrice\": \"20000\",\n" +
//            "      \"bluMbrDcPrice\": \"5000\",\n" +
//            "      \"rdwnSpcDcPrice\": \"\",\n" +
//            "      \"payDiv\": \"CARD\",\n" +
//            "      \"payPrice\": \"15000\",\n" +
//            "      \"payDt\": \"202012202200\",\n" +
//            "      \"updtDt\": \"202012202300\",\n" +
//            "      \"rsvDt\": \"202012202200\",\n" +
//            "      \"rgstDt\": \"202012201300\",\n" +
//            "      \"posInfo\": [\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEPT\",\n" +
//            "          \"latCoord\": \"37.58885149105208\",\n" +
//            "          \"lonCoord\": \"126.9440832693006\",\n" +
//            "          \"addrLotNo\": \"홍제역 지번 주소\",\n" +
//            "          \"addrBldNm\": \"홍제역 도로명 주소\",\n" +
//            "          \"addrDtl\": \" 1동 2호\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEST\",\n" +
//            "          \"latCoord\": \"37.4813022\",\n" +
//            "          \"lonCoord\": \"126.8821687\",\n" +
//            "          \"addrLotNo\": \"서울 구로구 라이온스밸리 지번주소\",\n" +
//            "          \"addrBldNm\": \"서울 구로구 라이온스밸리 도로명주소\",\n" +
//            "          \"addrDtl\": \"지하주차장이겠지\"\n" +
//            "        }\n" +
//            "      ]\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"transId\": \"예약번호20byte09\",\n" +
//            "      \"mbrMgmtNo\": \"16byte0001\",\n" +
//            "      \"reqDivCd\": \"RT\",\n" +
//            "      \"svcStusCd\": \"1400\",\n" +
//            "      \"stusNm\": \"취소\",\n" +
//            "      \"vin\": \"2wert345r098wert7\",\n" +
//            "      \"carRegNo\": \"234고 3543\",\n" +
//            "      \"mdlNm\": \"GV79\",\n" +
//            "      \"expPrice\": \"20000\",\n" +
//            "      \"bluMbrDcPrice\": \"5000\",\n" +
//            "      \"rdwnSpcDcPrice\": \"\",\n" +
//            "      \"payDiv\": \"CARD\",\n" +
//            "      \"payPrice\": \"15000\",\n" +
//            "      \"payDt\": \"202012202200\",\n" +
//            "      \"updtDt\": \"202012202300\",\n" +
//            "      \"rsvDt\": \"202012202200\",\n" +
//            "      \"rgstDt\": \"202012201300\",\n" +
//            "      \"posInfo\": [\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEPT\",\n" +
//            "          \"latCoord\": \"37.58885149105208\",\n" +
//            "          \"lonCoord\": \"126.9440832693006\",\n" +
//            "          \"addrLotNo\": \"홍제역 지번 주소\",\n" +
//            "          \"addrBldNm\": \"홍제역 도로명 주소\",\n" +
//            "          \"addrDtl\": \" 1동 2호\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEST\",\n" +
//            "          \"latCoord\": \"37.4813022\",\n" +
//            "          \"lonCoord\": \"126.8821687\",\n" +
//            "          \"addrLotNo\": \"서울 구로구 라이온스밸리 지번주소\",\n" +
//            "          \"addrBldNm\": \"서울 구로구 라이온스밸리 도로명주소\",\n" +
//            "          \"addrDtl\": \"지하주차장이겠지\"\n" +
//            "        }\n" +
//            "      ]\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"transId\": \"예약번호20byte10\",\n" +
//            "      \"mbrMgmtNo\": \"16byte0001\",\n" +
//            "      \"reqDivCd\": \"RT\",\n" +
//            "      \"svcStusCd\": \"1310\",\n" +
//            "      \"stusNm\": \"상태이름\",\n" +
//            "      \"vin\": \"2wert345r098wert7\",\n" +
//            "      \"carRegNo\": \"234고 3543\",\n" +
//            "      \"mdlNm\": \"GV80\",\n" +
//            "      \"expPrice\": \"20000\",\n" +
//            "      \"bluMbrDcPrice\": \"0\",\n" +
//            "      \"rdwnSpcDcPrice\": \"\",\n" +
//            "      \"payDiv\": \"CARD\",\n" +
//            "      \"payPrice\": \"20000\",\n" +
//            "      \"payDt\": \"202012202200\",\n" +
//            "      \"updtDt\": \"202012202300\",\n" +
//            "      \"rsvDt\": \"202012202200\",\n" +
//            "      \"rgstDt\": \"202012201300\",\n" +
//            "      \"posInfo\": [\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEPT\",\n" +
//            "          \"latCoord\": \"37.58885149105208\",\n" +
//            "          \"lonCoord\": \"126.9440832693006\",\n" +
//            "          \"addrLotNo\": \"홍제역 지번 주소\",\n" +
//            "          \"addrBldNm\": \"홍제역 도로명 주소\",\n" +
//            "          \"addrDtl\": \" 1동 2호\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEST\",\n" +
//            "          \"latCoord\": \"37.4813022\",\n" +
//            "          \"lonCoord\": \"126.8821687\",\n" +
//            "          \"addrLotNo\": \"서울 구로구 라이온스밸리 지번주소\",\n" +
//            "          \"addrBldNm\": \"서울 구로구 라이온스밸리 도로명주소\",\n" +
//            "          \"addrDtl\": \"지하주차장이겠지\"\n" +
//            "        }\n" +
//            "      ]\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"transId\": \"예약번호20byte11\",\n" +
//            "      \"mbrMgmtNo\": \"16byte0001\",\n" +
//            "      \"reqDivCd\": \"RT\",\n" +
//            "      \"svcStusCd\": \"1310\",\n" +
//            "      \"stusNm\": \"상태이름\",\n" +
//            "      \"vin\": \"2wert345r098wert7\",\n" +
//            "      \"carRegNo\": \"234고 3543\",\n" +
//            "      \"mdlNm\": \"GV81\",\n" +
//            "      \"expPrice\": \"20000\",\n" +
//            "      \"bluMbrDcPrice\": \"0\",\n" +
//            "      \"rdwnSpcDcPrice\": \"\",\n" +
//            "      \"payDiv\": \"CARD\",\n" +
//            "      \"payPrice\": \"20000\",\n" +
//            "      \"payDt\": \"202012202200\",\n" +
//            "      \"updtDt\": \"202012202300\",\n" +
//            "      \"rsvDt\": \"202012202200\",\n" +
//            "      \"rgstDt\": \"202012201300\",\n" +
//            "      \"posInfo\": [\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEPT\",\n" +
//            "          \"latCoord\": \"37.58885149105208\",\n" +
//            "          \"lonCoord\": \"126.9440832693006\",\n" +
//            "          \"addrLotNo\": \"홍제역 지번 주소\",\n" +
//            "          \"addrBldNm\": \"홍제역 도로명 주소\",\n" +
//            "          \"addrDtl\": \" 1동 2호\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEST\",\n" +
//            "          \"latCoord\": \"37.4813022\",\n" +
//            "          \"lonCoord\": \"126.8821687\",\n" +
//            "          \"addrLotNo\": \"서울 구로구 라이온스밸리 지번주소\",\n" +
//            "          \"addrBldNm\": \"서울 구로구 라이온스밸리 도로명주소\",\n" +
//            "          \"addrDtl\": \"지하주차장이겠지\"\n" +
//            "        }\n" +
//            "      ]\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"transId\": \"예약번호20byte12\",\n" +
//            "      \"mbrMgmtNo\": \"16byte0001\",\n" +
//            "      \"reqDivCd\": \"RT\",\n" +
//            "      \"svcStusCd\": \"1400\",\n" +
//            "      \"stusNm\": \"상태이름\",\n" +
//            "      \"vin\": \"2wert345r098wert7\",\n" +
//            "      \"carRegNo\": \"234고 3543\",\n" +
//            "      \"mdlNm\": \"GV82\",\n" +
//            "      \"expPrice\": \"20000\",\n" +
//            "      \"bluMbrDcPrice\": \"5000\",\n" +
//            "      \"rdwnSpcDcPrice\": \"\",\n" +
//            "      \"payDiv\": \"CARD\",\n" +
//            "      \"payPrice\": \"15000\",\n" +
//            "      \"payDt\": \"202012202200\",\n" +
//            "      \"updtDt\": \"202012202300\",\n" +
//            "      \"rsvDt\": \"202012202200\",\n" +
//            "      \"rgstDt\": \"202012201300\",\n" +
//            "      \"posInfo\": [\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEPT\",\n" +
//            "          \"latCoord\": \"37.58885149105208\",\n" +
//            "          \"lonCoord\": \"126.9440832693006\",\n" +
//            "          \"addrLotNo\": \"홍제역 지번 주소\",\n" +
//            "          \"addrBldNm\": \"홍제역 도로명 주소\",\n" +
//            "          \"addrDtl\": \" 1동 2호\"\n" +
//            "        },\n" +
//            "        {\n" +
//            "          \"posDivCd\": \"DEST\",\n" +
//            "          \"latCoord\": \"37.4813022\",\n" +
//            "          \"lonCoord\": \"126.8821687\",\n" +
//            "          \"addrLotNo\": \"서울 구로구 라이온스밸리 지번주소\",\n" +
//            "          \"addrBldNm\": \"서울 구로구 라이온스밸리 도로명주소\",\n" +
//            "          \"addrDtl\": \"지하주차장이겠지\"\n" +
//            "        }\n" +
//            "      ]\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}", DDS_1003.Response.class);
//
//    public static DDS_1004.Response DDS_1004 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}", DDS_1004.Response.class);
//
//    public static DDS_1005.Response DDS_1005 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}", DDS_1005.Response.class);
//
//    public static DDS_1006.Response DDS_1006 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}", DDS_1006.Response.class);
//
//
//    public static CMN_0003.Response CMN_0003 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"termList\": [\n" +
//            "    {\n" +
//            "      \"termVer\": \"01.00.00\",\n" +
//            "      \"termCd\": \"GRA0001\",\n" +
//            "      \"termNm\": \"제네시스 앱 이용약관(필수)\",\n" +
//            "      \"termEsnAgmtYn\": \"Y\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"termVer\": \"01.00.00\",\n" +
//            "      \"termCd\": \"GRA0002\",\n" +
//            "      \"termNm\": \"제네시스 앱 개인정보 처리방침\",\n" +
//            "      \"termEsnAgmtYn\": \"Y\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"termVer\": \"01.00.00\",\n" +
//            "      \"termCd\": \"GRA0003\",\n" +
//            "      \"termNm\": \"개인정보 수집/이용에 대한 동의\",\n" +
//            "      \"termEsnAgmtYn\": \"Y\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"termVer\": \"01.00.00\",\n" +
//            "      \"termCd\": \"GRA0004\",\n" +
//            "      \"termNm\": \"제네시스 멤버십 가입 약관\",\n" +
//            "      \"termEsnAgmtYn\": \"Y\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"termVer\": \"01.00.00\",\n" +
//            "      \"termCd\": \"GRA0005\",\n" +
//            "      \"termNm\": \"광고성 정보 수신 동의\",\n" +
//            "      \"termEsnAgmtYn\": \"N\"\n" +
//            "    }\n" +
//            "  ],\n" +
//            "  \"blueTermList\": [\n" +
//            "    {\n" +
//            "      \"termVer\": \"01.00.00\",\n" +
//            "      \"termCd\": \"BLM0001\",\n" +
//            "      \"termNm\": \"블루멤버스 회원약관(필수)\",\n" +
//            "      \"termEsnAgmtYn\": \"Y\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"termVer\": \"01.00.00\",\n" +
//            "      \"termCd\": \"BLM0002\",\n" +
//            "      \"termNm\": \"블루멤버스 개인정보 처리방침\",\n" +
//            "      \"termEsnAgmtYn\": \"Y\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"termVer\": \"01.00.00\",\n" +
//            "      \"termCd\": \"BLM0003\",\n" +
//            "      \"termNm\": \"블루멤버스 광고성 정보 수신에 대한 안내\",\n" +
//            "      \"termEsnAgmtYn\": \"N\"\n" +
//            "    }\n" +
//            "  ],\n" +
//            "  \"mypgTermList\": [\n" +
//            "    {\n" +
//            "      \"termVer\": \"01.00.00\",\n" +
//            "      \"termCd\": \"MYP0001\",\n" +
//            "      \"termNm\": \"개인정보수집및이용(필수)\",\n" +
//            "      \"termEsnAgmtYn\": \"Y\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"termVer\": \"01.00.00\",\n" +
//            "      \"termCd\": \"MYP0002\",\n" +
//            "      \"termNm\": \"개인정보취급위탁에대한동의\",\n" +
//            "      \"termEsnAgmtYn\": \"Y\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"termVer\": \"01.00.00\",\n" +
//            "      \"termCd\": \"MYP0003\",\n" +
//            "      \"termNm\": \"제네시스사이트약관(필수)\",\n" +
//            "      \"termEsnAgmtYn\": \"Y\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"termVer\": \"01.00.00\",\n" +
//            "      \"termCd\": \"MYP0004\",\n" +
//            "      \"termNm\": \"선택적개인정보수집이용에대한안내\",\n" +
//            "      \"termEsnAgmtYn\": \"N\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",CMN_0003.Response.class);
//
//
//    public static MBR_0001.Response MBR_0001 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"custNo\": \"0000\",\n" +
//            "  \"custGbCd\": \"OV\",\n" +
//            "  \"pushIdChgYn\": \"Y\",\n" +
//            "  \"custMgmtNo\": \"12345612341\",\n" +
//            "  \"custNm\": \"박현준\",\n" +
//            "  \"celphNo\": \"01086029612\",\n" +
//            "  \"ownVhclList\": [\n" +
//            "    {\n" +
//            "      \"csmrCarRelCd\": \"1\",\n" +
//            "      \"vin\": \"AWJDIWHD234213KJ\",\n" +
//            "      \"carRgstNo\": \"16조6840\",\n" +
//            "      \"mdlCd\": \"GV80\",\n" +
//            "      \"mdlNm\": \"디젤 3.0 5인승 19인치\",\n" +
//            "      \"saleMdlNm\": \"GV80 디젤 3.0 5인승 19인치 기본디자인 2WD 오토\",\n" +
//            "      \"xrclCtyNo\": \"12345678\",\n" +
//            "      \"xrclCtyNm\": \"WHITE\",\n" +
//            "      \"mainVhclYn\": \"N\",\n" +
//            "      \"vhclImgUri\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"csmrCarRelCd\": \"2\",\n" +
//            "      \"vin\": \"AWJD3332232323KJ\",\n" +
//            "      \"carRgstNo\": \"161조6842\",\n" +
//            "      \"mdlCd\": \"GV90\",\n" +
//            "      \"mdlNm\": \"디젤 3.0 5인승 19인치\",\n" +
//            "      \"saleMdlNm\": \"GV90 디젤 3.0 5인승 19인치 기본디자인 2WD 오토\",\n" +
//            "      \"xrclCtyNo\": \"22245678\",\n" +
//            "      \"xrclCtyNm\": \"BLACK\",\n" +
//            "      \"mainVhclYn\": \"N\",\n" +
//            "      \"vhclImgUri\": \"\"\n" +
//            "    }\n" +
//            "  ],\n" +
//            "  \"ctrctVhclList\": [\n" +
//            "    {\n" +
//            "      \"saleMdlCd\": \"AAAAAA\",\n" +
//            "      \"mdlCd\": \"GV80\",\n" +
//            "      \"mdlNm\": \"디젤 3.0 5인승 19인치\",\n" +
//            "      \"saleMdlNm\": \"GV80 디젤 3.0 5인승 19인치 기본디자인 2WD 오토\",\n" +
//            "      \"xrclCtyNo\": \"12345678\",\n" +
//            "      \"xrclCtyNm\": \"WHITE\",\n" +
//            "      \"ieclCtyNo\": \"12345678\",\n" +
//            "      \"ieclCtyNm\": \"WHITE\",\n" +
//            "      \"vhclImgUri\": \"\",\n" +
//            "      \"ctrctNo\": \"21234\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"saleMdlCd\": \"AAAAAA\",\n" +
//            "      \"mdlCd\": \"GV80\",\n" +
//            "      \"mdlNm\": \"디젤 3.0 5인승 19인치\",\n" +
//            "      \"saleMdlNm\": \"GV80 디젤 3.0 5인승 19인치 기본디자인 2WD 오토\",\n" +
//            "      \"xrclCtyNo\": \"12345678\",\n" +
//            "      \"xrclCtyNm\": \"WHITE\",\n" +
//            "      \"ieclCtyNo\": \"12345678\",\n" +
//            "      \"ieclCtyNm\": \"WHITE\",\n" +
//            "      \"vhclImgUri\": \"\",\n" +
//            "      \"ctrctNo\": \"21234\"\n" +
//            "    }\n" +
//            "  ],\n" +
//            "  \"dftVhclInfo\": \n" +
//            "    {\n" +
//            "      \"mdlCd\": \"GV90\",\n" +
//            "      \"mdlNm\": \"디젤 3.0 5인승 19인치\",\n" +
//            "      \"saleMdlNm\": \"GV90 디젤 3.0 5인승 19인치 기본디자인 2WD 오토\",\n" +
//            "      \"xrclCtyNo\": \"99999999\",\n" +
//            "      \"vhclImgUri\": \"\"\n" +
//            "    }\n" +
//            "}",MBR_0001.Response.class);
//
//
//    public static LGN_0001.Response LGN_0001 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"custNo\": \"0000\",\n" +
//            "  \"custGbCd\": \"OV\",\n" +
//            "  \"pushIdChgYn\": \"N\",\n" +
//            "  \"custMgmtNo\": \"12345612341\",\n" +
//            "  \"custNm\": \"박현준\",\n" +
//            "  \"celphNo\": \"01086029612\",\n" +
//            "  \"ownVhclList\": [\n" +
//            "    {\n" +
//            "      \"csmrCarRelCd\": \"1\",\n" +
//            "      \"vin\": \"AWJDIWHD234213KJ\",\n" +
//            "      \"carRgstNo\": \"16조6840\",\n" +
//            "      \"mdlCd\": \"GV80\",\n" +
//            "      \"mdlNm\": \"디젤 3.0 5인승 19인치\",\n" +
//            "      \"saleMdlNm\": \"GV80 디젤 3.0 5인승 19인치 기본디자인 2WD 오토\",\n" +
//            "      \"xrclCtyNo\": \"12345678\",\n" +
//            "      \"xrclCtyNm\": \"WHITE\",\n" +
//            "      \"mainVhclYn\": \"N\",\n" +
//            "      \"vhclImgUri\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"csmrCarRelCd\": \"2\",\n" +
//            "      \"vin\": \"AWJD3332232323KJ\",\n" +
//            "      \"carRgstNo\": \"161조6842\",\n" +
//            "      \"mdlCd\": \"GV90\",\n" +
//            "      \"mdlNm\": \"디젤 3.0 5인승 19인치\",\n" +
//            "      \"saleMdlNm\": \"GV90 디젤 3.0 5인승 19인치 기본디자인 2WD 오토\",\n" +
//            "      \"xrclCtyNo\": \"22245678\",\n" +
//            "      \"xrclCtyNm\": \"BLACK\",\n" +
//            "      \"mainVhclYn\": \"N\",\n" +
//            "      \"vhclImgUri\": \"\"\n" +
//            "    }\n" +
//            "  ],\n" +
//            "  \"ctrctVhclList\": [\n" +
//            "    {\n" +
//            "      \"saleMdlCd\": \"AAAAAA\",\n" +
//            "      \"mdlCd\": \"GV80\",\n" +
//            "      \"mdlNm\": \"디젤 3.0 5인승 19인치\",\n" +
//            "      \"saleMdlNm\": \"GV80 디젤 3.0 5인승 19인치 기본디자인 2WD 오토\",\n" +
//            "      \"xrclCtyNo\": \"12345678\",\n" +
//            "      \"xrclCtyNm\": \"WHITE\",\n" +
//            "      \"ieclCtyNo\": \"12345678\",\n" +
//            "      \"ieclCtyNm\": \"WHITE\",\n" +
//            "      \"vhclImgUri\": \"\",\n" +
//            "      \"ctrctNo\": \"21234\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"saleMdlCd\": \"AAAAAA\",\n" +
//            "      \"mdlCd\": \"GV80\",\n" +
//            "      \"mdlNm\": \"디젤 3.0 5인승 19인치\",\n" +
//            "      \"saleMdlNm\": \"GV80 디젤 3.0 5인승 19인치 기본디자인 2WD 오토\",\n" +
//            "      \"xrclCtyNo\": \"12345678\",\n" +
//            "      \"xrclCtyNm\": \"WHITE\",\n" +
//            "      \"ieclCtyNo\": \"12345678\",\n" +
//            "      \"ieclCtyNm\": \"WHITE\",\n" +
//            "      \"vhclImgUri\": \"\",\n" +
//            "      \"ctrctNo\": \"21234\"\n" +
//            "    }\n" +
//            "  ],\n" +
//            "  \"dftVhclInfo\":\n"+
//            "    {\n" +
//            "      \"mdlCd\": \"GV90\",\n" +
//            "      \"mdlNm\": \"디젤 3.0 5인승 19인치\",\n" +
//            "      \"saleMdlNm\": \"GV90 디젤 3.0 5인승 19인치 기본디자인 2WD 오토\",\n" +
//            "      \"xrclCtyNo\": \"99999999\",\n" +
//            "      \"vhclImgUri\": \"\"\n" +
//            "    }\n" +
//
//            "}", LGN_0001.Response.class);
//
//    public static LGN_0003.Response LGN_0003 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"acptNo\": \"A1414\",\n" +
//            "  \"asnDt\": \"20201101\",\n" +
//            "  \"asnNm\": \"블루핸즈0호점\",\n" +
//            "  \"asnStatsNm\": \"차량 정비 중\",\n" +
//            "  \"asnStatsCd\": \"1111\",\n" +
//            "  \"asnHistList\": [\n" +
//            "    {\n" +
//            "      \"carRgstNo\": \"16조2841\",\n" +
//            "      \"carGbNm\": \"GV80\",\n" +
//            "      \"drivDist\": \"100000\",\n" +
//            "      \"asnNm\": \"블루핸즈1호점\",\n" +
//            "      \"pbzAddr\": \"도봉로1\",\n" +
//            "      \"repTn\": \"021111111\",\n" +
//            "      \"arrivDt\": \"20201021\",\n" +
//            "      \"asnHist\": \"오일교체1\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"carRgstNo\": \"16조2842\",\n" +
//            "      \"carGbNm\": \"GV81\",\n" +
//            "      \"drivDist\": \"100002\",\n" +
//            "      \"asnNm\": \"블루핸즈2호점\",\n" +
//            "      \"pbzAddr\": \"도봉로2\",\n" +
//            "      \"repTn\": \"0222221222\",\n" +
//            "      \"arrivDt\": \"20201022\",\n" +
//            "      \"asnHist\": \"오일교체2\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"carRgstNo\": \"16조2843\",\n" +
//            "      \"carGbNm\": \"GV82\",\n" +
//            "      \"drivDist\": \"100003\",\n" +
//            "      \"asnNm\": \"블루핸즈3호점\",\n" +
//            "      \"pbzAddr\": \"도봉로3\",\n" +
//            "      \"repTn\": \"023333333\",\n" +
//            "      \"arrivDt\": \"20201023\",\n" +
//            "      \"asnHist\": \"오일교체3\"\n" +
//            "    }\n" +
//            "  ],\n" +
//            "  \"butlSubsCd\": \"3000\",\n" +
//            "  \"virtRecptNo\": \"1111\"\n" +
//            "}", LGN_0003.Response.class);
//
//    public static LGN_0005.Response LGN_0005 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"fcstDtm\": \"20201008220011\",\n" +
//            "  \"nx\": \"37.463936\",\n" +
//            "  \"ny\": \"127.042953\",\n" +
//            "  \"t1h\": \"10\",\n" +
//            "  \"sky\": \"4\",\n" +
//            "  \"reh\": \"50\",\n" +
//            "  \"pty\": \"1\",\n" +
//            "  \"lgt\": \"0\",\n" +
//            "  \"dayCd\": \"1\"\n" +
//            "}", LGN_0005.Response.class);
//
////    public static CMN_0001.Response CMN_0001 = new Gson().fromJson("{\n" +
////            "  \"rtCd\": \"0000\",\n" +
////            "  \"rtMsg\": \"Success\",\n" +
////            "  \"appVer\": \"01.00.00\",\n" +
////            "  \"appUpdType\": \"X\",\n" +
////            "  \"notiDt\": \"20200910\",\n" +
////            "  \"notiList\": [\n" +
////            "    {\n" +
////            "      \"notiCd\": \"ANNC\",\n" +
////            "      \"seqNo\": \"2020091000000015\",\n" +
////            "      \"notiTtl\": \"필독공지1\",\n" +
////            "      \"notiCont\": \"필독공지내용1\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"notiCd\": \"NOTI\",\n" +
////            "      \"seqNo\": \"2020091000000014\",\n" +
////            "      \"notiTtl\": \"일반공지1\",\n" +
////            "      \"notiCont\": \"일반공지내용1\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"notiCd\": \"ANNC\",\n" +
////            "      \"seqNo\": \"2020091000000013\",\n" +
////            "      \"notiTtl\": \"긴급공지1\",\n" +
////            "      \"notiCont\": \"긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용1긴급공지내용2\"\n" +
////            "    }\n" +
////            "  ]\n" +
////            "}", CMN_0001.Response.class);
//
//    public static CMN_0001.Response CMN_0001 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"appVer\": \"01.00.00\",\n" +
//            "  \"appUpdType\": \"x\",\n" +
//            "  \"notiDt\": \"20200910\"" +
//            "}", CMN_0001.Response.class);
//
//    public static CMN_0002.Response CMN_0002 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"menu0000\": {\n" +
//            "    \"menuList\": [\n" +
//            "      {\n" +
//            "        \"menuId\": \"GM04\",\n" +
//            "        \"upMenuId\": \"GM04\",\n" +
//            "        \"menuNm\": \"HOME (비로그인)\",\n" +
//            "        \"menuTypCd\": \"NA\",\n" +
//            "        \"scrnTypCd\": \"PG\"\n" +
//            "      }\n" +
//            "    ],\n" +
//            "    \"qckMenuList\": []\n" +
//            "  },\n" +
//            "  \"menuOV\": {\n" +
//            "    \"menuList\": [\n" +
//            "      {\n" +
//            "        \"menuId\": \"GM01\",\n" +
//            "        \"upMenuId\": \"GM01\",\n" +
//            "        \"menuNm\": \"HOME (로그인/차량보유)\",\n" +
//            "        \"menuTypCd\": \"NA\",\n" +
//            "        \"scrnTypCd\": \"PG\"\n" +
//            "      },\n" +
//            "      {\n" +
//            "        \"menuId\": \"INT01\",\n" +
//            "        \"upMenuId\": \"INT01\",\n" +
//            "        \"menuNm\": \"스플래시\",\n" +
//            "        \"menuTypCd\": \"NA\",\n" +
//            "        \"scrnTypCd\": \"PG\"\n" +
//            "      }\n" +
//            "    ],\n" +
//            "    \"qckMenuList\": [\n" +
//            "      {\n" +
//            "        \"menuId\": \"MENU0001\",\n" +
//            "        \"menuNm\": \"메뉴0001\",\n" +
//            "        \"qckMenuDivCd\": \"IM\",\n" +
//            "        \"wvYn\": \"N\",\n" +
//            "        \"nttOrd\": \"1\"\n" +
//            "      },\n" +
//            "      {\n" +
//            "        \"menuId\": \"MENU0002\",\n" +
//            "        \"menuNm\": \"메뉴0002\",\n" +
//            "        \"qckMenuDivCd\": \"OM\",\n" +
//            "        \"wvYn\": \"Y\",\n" +
//            "        \"lnkUri\": \"http://www.genesis.com/priv?id=G000001\",\n" +
//            "        \"nttOrd\": \"2\"\n" +
//            "      }\n" +
//            "    ]\n" +
//            "  },\n" +
//            "  \"menuCV\": {\n" +
//            "    \"menuList\": [\n" +
//            "      {\n" +
//            "        \"menuId\": \"GM02\",\n" +
//            "        \"upMenuId\": \"GM02\",\n" +
//            "        \"menuNm\": \"HOME (로그인/예약대기)\",\n" +
//            "        \"menuTypCd\": \"NA\",\n" +
//            "        \"scrnTypCd\": \"PG\"\n" +
//            "      }\n" +
//            "    ],\n" +
//            "    \"qckMenuList\": [\n" +
//            "      {\n" +
//            "        \"menuId\": \"MENU0001\",\n" +
//            "        \"menuNm\": \"메뉴0001\",\n" +
//            "        \"qckMenuDivCd\": \"IM\",\n" +
//            "        \"wvYn\": \"N\",\n" +
//            "        \"nttOrd\": \"1\"\n" +
//            "      }\n" +
//            "    ]\n" +
//            "  },\n" +
//            "  \"menuNV\": {\n" +
//            "    \"menuList\": [\n" +
//            "      {\n" +
//            "        \"menuId\": \"GM03\",\n" +
//            "        \"upMenuId\": \"GM03\",\n" +
//            "        \"menuNm\": \"HOME (로그인/차량미보유)\",\n" +
//            "        \"menuTypCd\": \"NA\",\n" +
//            "        \"scrnTypCd\": \"PG\"\n" +
//            "      }\n" +
//            "    ],\n" +
//            "    \"qckMenuList\": [\n" +
//            "      {\n" +
//            "        \"menuId\": \"MENU0001\",\n" +
//            "        \"menuNm\": \"메뉴0001\",\n" +
//            "        \"qckMenuDivCd\": \"IM\",\n" +
//            "        \"wvYn\": \"N\",\n" +
//            "        \"nttOrd\": \"1\"\n" +
//            "      }\n" +
//            "    ]\n" +
//            "  },\n" +
//            "  \"wthrInsgtList\": [\n" +
//            "    {\n" +
//            "      \"wthrCd\": \"SKY_1\",\n" +
//            "      \"msgTypCd\": \"TXT\",\n" +
//            "      \"txtMsg\": \"나들이 가기\n좋은 날씨네요.\",\n" +
//            "      \"lnkUseYn\": \"N\"\n" +
//            "    },\n" +
//
//            "    {\n" +
//            "      \"wthrCd\": \"SKY_1\",\n" +
//            "      \"msgTypCd\": \"TXT\",\n" +
//            "      \"txtMsg\": \"드라이브하기\n좋은 날씨네요.\",\n" +
//            "      \"lnkUseYn\": \"N\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"wthrCd\": \"PTY_145\",\n" +
//            "      \"msgTypCd\": \"TXT\",\n" +
//            "      \"txtMsg\": \"우산은 챙기셨나요?\n빗길 운전 조심하세요\",\n" +
//            "      \"lnkUseYn\": \"N\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"wthrCd\": \"PTY_145\",\n" +
//            "      \"msgTypCd\": \"TXT\",\n" +
//            "      \"txtMsg\": \"차안에서 빗소리를 들을수 있겠네요.\n빗길운전 조심하세요!\",\n" +
//            "      \"lnkUseYn\": \"N\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"wthrCd\": \"PTY_145\",\n" +
//            "      \"msgTypCd\": \"TXT\",\n" +
//            "      \"txtMsg\": \"세차는 다음에 하세요,\n비오는 날 드라이브도 운치 있어요\",\n" +
//            "      \"lnkUseYn\": \"N\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"wthrCd\": \"SKY_1\",\n" +
//            "      \"msgTypCd\": \"TXT\",\n" +
//            "      \"txtMsg\": \"맑고 화창한\n날씨입니다.\",\n" +
//            "      \"lnkUseYn\": \"N\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",CMN_0002.Response.class);
//
//
//    public static GNS_1001.Response GNS_1001 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"actoprRgstYn\": \"Y\",\n" +
//            "  \"ownVhclCnt\": \"3\",\n" +
//            "  \"ownVhclList\": [\n" +
//            "    {\n" +
//            "      \"vin\": \"V141548641564\",\n" +
//            "      \"carRgstNo\": \"16조6840\",\n" +
//            "      \"mainVhclYn\": \"N\",\n" +
//            "      \"delYn\": \"N\",\n" +
//            "      \"csmrCarRelCd\": \"1\",\n" +
//            "      \"mdlCd\": \"GV80\",\n" +
//            "      \"mdlNm\": \"디젤 3.0 5인승 19인치\",\n" +
//            "      \"saleMdlNm\": \"GV80 디젤 3.0 5인승 19인치 기본디자인 2WD 오토\",\n" +
//            "      \"xrclCtyNo\": \"UYH\",\n" +
//            "      \"xrclCtyNm\": \"??\",\n" +
//            "      \"usedCarYn\": \"N\",\n" +
//            "      \"delExpYn\": \"N\",\n" +
//            "      \"delExpDay\": \"\",\n" +
//            "      \"vhclImgUri\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vin\": \"V141548641564\",\n" +
//            "      \"carRgstNo\": \"16조6840\",\n" +
//            "      \"mainVhclYn\": \"Y\",\n" +
//            "      \"delYn\": \"N\",\n" +
//            "      \"csmrCarRelCd\": \"1\",\n" +
//            "      \"mdlCd\": \"GV90\",\n" +
//            "      \"mdlNm\": \"디젤 3.0 5인승 19인치\",\n" +
//            "      \"saleMdlNm\": \"GV82 디젤 3.0 5인승 19인치 기본디자인 2WD 오토\",\n" +
//            "      \"xrclCtyNo\": \"UYH2\",\n" +
//            "      \"xrclCtyNm\": \"??\",\n" +
//            "      \"usedCarYn\": \"N\",\n" +
//            "      \"delExpYn\": \"N\",\n" +
//            "      \"delExpDay\": \"\",\n" +
//            "      \"vhclImgUri\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vin\": \"V141548641534\",\n" +
//            "      \"carRgstNo\": \"16조6843\",\n" +
//            "      \"mainVhclYn\": \"N\",\n" +
//            "      \"delYn\": \"N\",\n" +
//            "      \"csmrCarRelCd\": \"1\",\n" +
//            "      \"mdlCd\": \"GV70\",\n" +
//            "      \"mdlNm\": \"디젤 3.0 5인승 19인치\",\n" +
//            "      \"saleMdlNm\": \"GV83 디젤 3.0 5인승 19인치 기본디자인 2WD 오토\",\n" +
//            "      \"xrclCtyNo\": \"UYH2\",\n" +
//            "      \"xrclCtyNm\": \"??\",\n" +
//            "      \"usedCarYn\": \"N\",\n" +
//            "      \"delExpYn\": \"Y\",\n" +
//            "      \"delExpDay\": \"3\",\n" +
//            "      \"vhclImgUri\": \"\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",GNS_1001.Response.class);
//
//    public static GNS_1004.Response GNS_1004 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}",GNS_1004.Response.class);
//
//    public static GNS_1002.Response GNS_1002 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}",GNS_1002.Response.class);
//
//
//    public static GNS_1003.Response GNS_1003 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}",GNS_1003.Response.class);
//
//
//    public static GNS_1005.Response GNS_1005 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}",GNS_1005.Response.class);
//
//
//    public static GNS_1006.Response GNS_1006 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}",GNS_1006.Response.class);
//
//
//    public static GNS_1007.Response GNS_1007 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"subspList\": [\n" +
//            "    {\n" +
//            "      \"seqNo\": \"1\",\n" +
//            "      \"vin\": \"123456789A1234501\",\n" +
//            "      \"rentPeriod\": \"12\",\n" +
//            "      \"ctrctNo\": \"A01\",\n" +
//            "      \"subspDtm\": \"20201001000000\",\n" +
//            "      \"aprvStusCd\": \"W\",\n" +
//            "      \"aprvDtm\": \"20201001000000\",\n" +
//            "      \"rtnRsnMsg\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"seqNo\": \"2\",\n" +
//            "      \"vin\": \"123456789A1234502\",\n" +
//            "      \"rentPeriod\": \"13\",\n" +
//            "      \"ctrctNo\": \"A02\",\n" +
//            "      \"subspDtm\": \"20201002000000\",\n" +
//            "      \"aprvStusCd\": \"Y\",\n" +
//            "      \"aprvDtm\": \"20201002000000\",\n" +
//            "      \"rtnRsnMsg\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"seqNo\": \"3\",\n" +
//            "      \"vin\": \"123456789A1234503\",\n" +
//            "      \"rentPeriod\": \"13\",\n" +
//            "      \"ctrctNo\": \"A03\",\n" +
//            "      \"subspDtm\": \"20201003000000\",\n" +
//            "      \"aprvStusCd\": \"N\",\n" +
//            "      \"aprvDtm\": \"20201003000000\",\n" +
//            "      \"rtnRsnMsg\": \"계약서 미제출\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"seqNo\": \"4\",\n" +
//            "      \"vin\": \"123456789A1234504\",\n" +
//            "      \"rentPeriod\": \"14\",\n" +
//            "      \"ctrctNo\": \"A04\",\n" +
//            "      \"subspDtm\": \"20201004000000\",\n" +
//            "      \"aprvStusCd\": \"Y\",\n" +
//            "      \"aprvDtm\": \"20201004000000\",\n" +
//            "      \"rtnRsnMsg\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"seqNo\": \"5\",\n" +
//            "      \"vin\": \"123456789A1234505\",\n" +
//            "      \"rentPeriod\": \"15\",\n" +
//            "      \"ctrctNo\": \"A051\",\n" +
//            "      \"subspDtm\": \"20201005000000\",\n" +
//            "      \"aprvStusCd\": \"Y\",\n" +
//            "      \"aprvDtm\": \"20201005000000\",\n" +
//            "      \"rtnRsnMsg\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"seqNo\": \"6\",\n" +
//            "      \"vin\": \"123456789A1234506\",\n" +
//            "      \"rentPeriod\": \"66\",\n" +
//            "      \"ctrctNo\": \"A06\",\n" +
//            "      \"subspDtm\": \"20201006000000\",\n" +
//            "      \"aprvStusCd\": \"Y\",\n" +
//            "      \"aprvDtm\": \"20201006000000\",\n" +
//            "      \"rtnRsnMsg\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"seqNo\": \"7\",\n" +
//            "      \"vin\": \"123456789A1234507\",\n" +
//            "      \"rentPeriod\": \"17\",\n" +
//            "      \"ctrctNo\": \"A07\",\n" +
//            "      \"subspDtm\": \"20201007000000\",\n" +
//            "      \"aprvStusCd\": \"Y\",\n" +
//            "      \"aprvDtm\": \"20201007000000\",\n" +
//            "      \"rtnRsnMsg\": \"\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",GNS_1007.Response.class);
//
//
//    public static GNS_1008.Response GNS_1008 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}",GNS_1008.Response.class);
//
//
//    public static GNS_1009.Response GNS_1009 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}",GNS_1009.Response.class);
//
//    public static GNS_1010.Response GNS_1010 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"cpnList\": [\n" +
//            "    {\n" +
//            "      \"itemDivCd\": \"11\",\n" +
//            "      \"itemNm\": \"\",\n" +
//            "      \"remCnt\": \"1\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"itemDivCd\": \"13\",\n" +
//            "      \"itemNm\": \"\",\n" +
//            "      \"remCnt\": \"2\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"itemDivCd\": \"32\",\n" +
//            "      \"itemNm\": \"\",\n" +
//            "      \"remCnt\": \"3\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"itemDivCd\": \"33\",\n" +
//            "      \"itemNm\": \"\",\n" +
//            "      \"remCnt\": \"4\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"itemDivCd\": \"34\",\n" +
//            "      \"itemNm\": \"브레이크오일343434\",\n" +
//            "      \"remCnt\": \"5\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"itemDivCd\": \"61\",\n" +
//            "      \"itemNm\": \"\",\n" +
//            "      \"remCnt\": \"6\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"itemDivCd\": \"99\",\n" +
//            "      \"itemNm\": \"\",\n" +
//            "      \"remCnt\": \"7\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}\n",GNS_1010.Response.class);
//
//
//    public static GNS_1011.Response GNS_1011 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"rgstPsblYn\": \"Y\"\n" +
//            "}",GNS_1011.Response.class);
//
//
//    public static GNS_1012.Response GNS_1012 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"seqNo\": \"1\",\n" +
//            "  \"vin\": \"TEST1234578965412\",\n" +
//            "  \"csmrScnCd\": \"1\",\n" +
//            "  \"asnNm\": \"현대공업\",\n" +
//            "  \"repTn\": \"021245121\",\n" +
//            "  \"pbzAdr\": \"서울시 구로구 고척동 1450\",\n" +
//            "  \"crdRcvScnCd\": \"3\",\n" +
//            "  \"crdRcvZip\": \"12248\",\n" +
//            "  \"crdRcvAdr\": \"경기도 안산시 성포동\",\n" +
//            "  \"crdRcvDtlAdr\": \"주공 4단지 404동\",\n" +
//            "  \"ctrctNo\": \"12342S\",\n" +
//            "  \"attachFilName\": \"20101248_032.jpg\",\n" +
//            "  \"cnttUrl\": \"www.naver.com\",\n" +
//            "  \"subspDtm\": \"20201024110000\",\n" +
//            "  \"aprvStusCd\": \"W\",\n" +
//            "  \"aprvDtm\": \"20201024110000\",\n" +
//            "  \"rtnRsnMsg\": \"거절\"\n" +
//            "}",GNS_1012.Response.class);
//
//    public static RentStatusVO GNS_1012_REJECT = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"seqNo\": \"1\",\n" +
//            "  \"vin\": \"TEST1234578965412\",\n" +
//            "  \"csmrScnCd\": \"1\",\n" +
//            "  \"asnNm\": \"현대공업\",\n" +
//            "  \"repTn\": \"021245121\",\n" +
//            "  \"pbzAdr\": \"서울시 구로구 고척동 1450\",\n" +
//            "  \"crdRcvScnCd\": \"3\",\n" +
//            "  \"crdRcvZip\": \"12248\",\n" +
//            "  \"crdRcvAdr\": \"경기도 안산시 성포동\",\n" +
//            "  \"crdRcvDtlAdr\": \"주공 4단지 404동\",\n" +
//            "  \"ctrctNo\": \"12342S\",\n" +
//            "  \"attachFilName\": \"20101248_032.jpg\",\n" +
//            "  \"cnttUrl\": \"www.naver.com\",\n" +
//            "  \"subspDtm\": \"20201024110000\",\n" +
//            "  \"aprvStusCd\": \"N\",\n" +
//            "  \"aprvDtm\": \"20201024110000\",\n" +
//            "  \"rtnRsnMsg\": \"거절\"\n" +
//            "}",RentStatusVO.class);
//
//    public static RentStatusVO GNS_1012_WAIT = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"seqNo\": \"1\",\n" +
//            "  \"vin\": \"TEST1234578965412\",\n" +
//            "  \"csmrScnCd\": \"1\",\n" +
//            "  \"asnNm\": \"현대공업\",\n" +
//            "  \"repTn\": \"021245121\",\n" +
//            "  \"pbzAdr\": \"서울시 구로구 고척동 1450\",\n" +
//            "  \"crdRcvScnCd\": \"3\",\n" +
//            "  \"crdRcvZip\": \"12248\",\n" +
//            "  \"crdRcvAdr\": \"경기도 안산시 성포동\",\n" +
//            "  \"crdRcvDtlAdr\": \"주공 4단지 404동\",\n" +
//            "  \"ctrctNo\": \"12342S\",\n" +
//            "  \"attachFilName\": \"20101248_032.jpg\",\n" +
//            "  \"cnttUrl\": \"www.naver.com\",\n" +
//            "  \"subspDtm\": \"20201024110000\",\n" +
//            "  \"aprvStusCd\": \"W\",\n" +
//            "  \"aprvDtm\": \"20201024110000\",\n" +
//            "  \"rtnRsnMsg\": \"거절\"\n" +
//            "}",RentStatusVO.class);
//
//
//    public static GNS_1013.Response GNS_1013 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}",GNS_1013.Response.class);
//
//    public static GNS_1014.Response GNS_1014 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}",GNS_1014.Response.class);
//
//    public static GNS_1015.Response GNS_1015 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}",GNS_1015.Response.class);
//
//
//
//
//
//    public static MYP_1005.Response MYP_1005 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"mbrshJoinYn\": \"Y\",\n" +
//            "  \"pvilList\": [\n" +
//            "    {\n" +
//            "      \"vin\": \"111111111\",\n" +
//            "      \"carRgstNo\": \"16조68404\",\n" +
//            "      \"mdlCd\": \"GV80\",\n" +
//            "      \"mdlNm\": \"가솔린\n1111111\",\n" +
//            "      \"saleMdlNm\": \"디젤가솔린1111111\",\n" +
//            "      \"xrclCtyNo\": \"\",\n" +
//            "      \"xrclCtyNm\": \"\",\n" +
//            "      \"stusCd\": \"0000\",\n" +
//            "      \"stusMsg\": \"정상\",\n" +
//            "      \"serviceUrl\": \"\",\n" +
//            "      \"serviceDetailUrl\": \"\",\n" +
//            "      \"joinPsblCd\": \"0\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vin\": \"2222222\",\n" +
//            "      \"carRgstNo\": \"16조6842\",\n" +
//            "      \"mdlCd\": \"GV80\",\n" +
//            "      \"mdlNm\": \"가솔린\n22222\",\n" +
//            "      \"saleMdlNm\": \"what the..\",\n" +
//            "      \"xrclCtyNo\": \"\",\n" +
//            "      \"xrclCtyNm\": \"\",\n" +
//            "      \"stusCd\": \"0000\",\n" +
//            "      \"stusMsg\": \"정상\",\n" +
//            "      \"serviceUrl\": \"\",\n" +
//            "      \"serviceDetailUrl\": \"\",\n" +
//            "      \"joinPsblCd\": \"1\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}\n", MYP_1005.Response.class);
//
//
//
//
//    public static BTR_1001.Response BTR_1001 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"vin\": \"VIN_000001\",\n" +
//            "  \"custMgmtNo\": \"CSMR_000001\",\n" +
//            "  \"asnCd\": \"블루핸즈 의왕\",\n" +
//            "  \"asnNm\": \"블루핸즈 의왕\",\n" +
//            "  \"repTn\": \"02-112-1124\",\n" +
//            "  \"pbzAdr\": \"인천광역시부평구\",\n" +
//            "  \"mapXcooNm\": \"37.463936\",\n" +
//            "  \"mapYcooNm\": \"127.042953\",\n" +
//            "  \"btlrNm\": \"박문수\",\n" +
//            "  \"celphNo\": \"01022223333\",\n" +
//            "  \"bltrChgYn\": \"C\",\n" +
//            "  \"cnsltBdgYn\": \"N\"\n" +
//            "}", BTR_1001.Response.class);
//
//    public static BTR_2001.Response BTR_2001_1= new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"cdReqCd\": \"CNSL\",\n" +
//            "  \"cdList\": [\n" +
//            "    {\n" +
//            "      \"cdValId\": \"1\",\n" +
//            "      \"cdValNm\": \"문의\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cdValId\": \"2\",\n" +
//            "      \"cdValNm\": \"칭찬\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cdValId\": \"3\",\n" +
//            "      \"cdValNm\": \"불만\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cdValId\": \"4\",\n" +
//            "      \"cdValNm\": \"제안\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",BTR_2001.Response.class);
//
//
//    public static BTR_2001.Response BTR_2001_2= new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"cdReqCd\": \"LGCT\",\n" +
//            "  \"conslCd\": \"1\",\n" +
//            "  \"lgrCatCd\": \"\",\n" +
//            "  \"mdlCatCd\": \"\",\n" +
//            "  \"cdList\": [\n" +
//            "    {\n" +
//            "      \"cdValId\": \"1000\",\n" +
//            "      \"cdValNm\": \"정비\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cdValId\": \"2000\",\n" +
//            "      \"cdValNm\": \"제네시스멤버십\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cdValId\": \"3000\",\n" +
//            "      \"cdValNm\": \"품질\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cdValId\": \"4000\",\n" +
//            "      \"cdValNm\": \"판매\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",BTR_2001.Response.class);
//
//    public static BTR_2001.Response BTR_2001_3= new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"cdReqCd\": \"MDCT\",\n" +
//            "  \"conslCd\": \"\",\n" +
//            "  \"lgrCatCd\": \"1000\",\n" +
//            "  \"mdlCatCd\": \"\",\n" +
//            "  \"cdList\": [\n" +
//            "    {\n" +
//            "      \"cdValId\": \"1001\",\n" +
//            "      \"cdValNm\": \"차량수리/관리\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cdValId\": \"1002\",\n" +
//            "      \"cdValNm\": \"예약\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cdValId\": \"1003\",\n" +
//            "      \"cdValNm\": \"정비시설\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cdValId\": \"1003\",\n" +
//            "      \"cdValNm\": \"리콜/사전점검\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cdValId\": \"1005\",\n" +
//            "      \"cdValNm\": \"서비스네트워크\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",BTR_2001.Response.class);
//
//
//    public static BTR_2001.Response BTR_2001_4= new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"cdReqCd\": \"SMCT\",\n" +
//            "  \"conslCd\": \"\",\n" +
//            "  \"lgrCatCd\": \"\",\n" +
//            "  \"mdlCatCd\": \"1001\",\n" +
//            "  \"cdList\": [\n" +
//            "    {\n" +
//            "      \"cdValId\": \"1101\",\n" +
//            "      \"cdValNm\": \"보증기간\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cdValId\": \"1102\",\n" +
//            "      \"cdValNm\": \"부품관련\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cdValId\": \"1103\",\n" +
//            "      \"cdValNm\": \"수리비 및 수리기간\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cdValId\": \"1104\",\n" +
//            "      \"cdValNm\": \"점검 및 교환주기\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"cdValId\": \"1105\",\n" +
//            "      \"cdValNm\": \"옵션 사용 방법\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",BTR_2001.Response.class);
//
//    public static BTR_2002.Response BTR_2002 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}",BTR_2002.Response.class);
//
//
//    public static BTR_2003.Response BTR_2003 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"conslList\": [\n" +
//            "    {\n" +
//            "      \"seqNo\": \"1\",\n" +
//            "      \"catNm\": \"문의/제네시스멤버십\",\n" +
//            "      \"conslDt\": \"2020.09.23\",\n" +
//            "      \"conslTtl\": \"작성\",\n" +
//            "      \"conslCont\": \"[전화응답요청]이상없습니다.\",\n" +
//            "      \"respDt\": \"2020.10.12\",\n" +
//            "      \"respCont\": \"안녕하세요 고객님\\n테스트입니다.22\\n감사합니다.\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"seqNo\": \"2\",\n" +
//            "      \"catNm\": \"문의/제네시스멤버십\",\n" +
//            "      \"conslDt\": \"2020.09.23\",\n" +
//            "      \"conslTtl\": \"작성\",\n" +
//            "      \"conslCont\": \"[전화응답요청]이상없습니다.\",\n" +
//            "      \"respDt\": \"\",\n" +
//            "      \"respCont\": \"안녕하세요 고객님\\n텍스트를 넣었습니다.1\\n감사합니다.\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"seqNo\": \"3\",\n" +
//            "      \"catNm\": \"문의/제네시스멤버십\",\n" +
//            "      \"conslDt\": \"2020.09.14\",\n" +
//            "      \"conslTtl\": \"작성\",\n" +
//            "      \"conslCont\": \"[APP응답요청]이상없습니다.\",\n" +
//            "      \"respDt\": \"2020.10.12\",\n" +
//            "      \"respCont\": \"안녕하세요 고객님\\n테스트입니다.\\n감사합니다.\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"seqNo\": \"4\",\n" +
//            "      \"catNm\": \"문의/제네시스멤버십\",\n" +
//            "      \"conslDt\": \"2020.09.17\",\n" +
//            "      \"conslTtl\": \"작성\",\n" +
//            "      \"conslCont\": \"[APP응답요청]이상없습니다.\",\n" +
//            "      \"respDt\": \"\",\n" +
//            "      \"respCont\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"seqNo\": \"5\",\n" +
//            "      \"catNm\": \"문의/제네시스멤버십\",\n" +
//            "      \"conslDt\": \"2020.09.14\",\n" +
//            "      \"conslTtl\": \"작성\",\n" +
//            "      \"conslCont\": \"[APP응답요청]이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.이상없습니다.\",\n" +
//            "      \"respDt\": \"\",\n" +
//            "      \"respCont\": \"2020년 09월 14일 에 유선 상담 완료\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"seqNo\": \"6\",\n" +
//            "      \"catNm\": \"문의/제네시스멤버십\",\n" +
//            "      \"conslDt\": \"2020.09.14\",\n" +
//            "      \"conslTtl\": \"작성\",\n" +
//            "      \"conslCont\": \"이상없습니다.\",\n" +
//            "      \"respDt\": \"\",\n" +
//            "      \"respCont\": \"2020년 09월 14일 에 유선 상담 완료\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",BTR_2003.Response.class);
//
//
//
//    public static BTR_1008.Response BTR_1008 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"asnList\": [\n" +
//            "    {\n" +
//            "      \"asnCd\": \"Z14Z48\",\n" +
//            "      \"asnNm\": \"(주)영남중공업\",\n" +
//            "      \"repTn\": \"028559966\",\n" +
//            "      \"pbzAdr\": \"서울특별시 금천구 시흥대로153길 26 (독산동)\",\n" +
//            "      \"mapXcooNm\": \"37.477919\",\n" +
//            "      \"mapYcooNm\": \"126.881762\",\n" +
//            "      \"dist\": \"1.6\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"asnCd\": \"Z14Z47\",\n" +
//            "      \"asnNm\": \"(주)현대중공업\",\n" +
//            "      \"repTn\": \"020000000\",\n" +
//            "      \"pbzAdr\": \"인천광역시 금천구 시흥대로153길 26 (독산동)\",\n" +
//            "      \"mapXcooNm\": \"37.481580\",\n" +
//            "      \"mapYcooNm\": \"126.881537\",\n" +
//            "      \"dist\": \"4.6\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",BTR_1008.Response.class);
//
//
//    public static BTR_1009.Response BTR_1009 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"asnCd\": \"000001\",\n" +
//            "  \"asnNm\": \"케이 정비소\",\n" +
//            "  \"repnTn\": \"01012345678\",\n" +
//            "  \"pbzAdr\": \"서울특별시서초구\"\n" +
//            "}", BTR_1009.Response.class);
//
//    public static PUB_1001.Response PUB_1001 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"totPageNo\": \"\",\n" +
//            "  \"curPageNo\": \"\",\n" +
//            "  \"zipList\": [\n" +
//            "    {\n" +
//            "      \"zipNo\": \"02739\",\n" +
//            "      \"roadAddr\": \"서울특별시 성북구 화랑로 5(하월곡동)\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"zipNo\": \"12345\",\n" +
//            "      \"roadAddr\": \"서울특별시 금천구 가산디지털1로 51(가산동)\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"zipNo\": \"23412\",\n" +
//            "      \"roadAddr\": \"서울특별시 금천구 가산디지털1로 189(가산동)\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"zipNo\": \"23123\",\n" +
//            "      \"roadAddr\": \"서울특별시 서초구 강남대로 275(서초동)\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"zipNo\": \"78554\",\n" +
//            "      \"roadAddr\": \"서울특별시 강남구 밤고개로27길 20(율현동, 강남 한신휴플러스 8단지)\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",PUB_1001.Response.class);
//
//
//
//    public static PUB_1002.Response PUB_1002 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"sidoList\": [\n" +
//            "    {\n" +
//            "      \"sidoCd\": \"1\",\n" +
//            "      \"sidoNm\": \"서울특별시\",\n" +
//            "      \"localNm\": \"??\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"sidoCd\": \"2\",\n" +
//            "      \"sidoNm\": \"인천광역시\",\n" +
//            "      \"localNm\": \"??\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"sidoCd\": \"3\",\n" +
//            "      \"sidoNm\": \"부산광역시\",\n" +
//            "      \"localNm\": \"??\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"sidoCd\": \"4\",\n" +
//            "      \"sidoNm\": \"경기도\",\n" +
//            "      \"localNm\": \"??\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"sidoCd\": \"5\",\n" +
//            "      \"sidoNm\": \"경상북도\",\n" +
//            "      \"localNm\": \"??\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",PUB_1002.Response.class);
//
//    public static PUB_1003.Response PUB_1003 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"gugunList\": [\n" +
//            "    {\n" +
//            "      \"gugunCd\": \"1\",\n" +
//            "      \"gugunNm\": \"강서구\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"gugunCd\": \"2\",\n" +
//            "      \"gugunNm\": \"금천구\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"gugunCd\": \"3\",\n" +
//            "      \"gugunNm\": \"강남구\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"gugunCd\": \"4\",\n" +
//            "      \"gugunNm\": \"송파구\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",PUB_1003.Response.class);
//
//
//
//
//    public static BAR_1001.Response BAR_1001 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"cardList\": [\n" +
//            "    {\n" +
//            "      \"isncCd\": \"BLUE\",\n" +
//            "      \"cardNo\": \"1234567545678777\",\n" +
//            "      \"cardIsncSubspDt\": \"20200121\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"isncCd\": \"BLUE\",\n" +
//            "      \"cardNo\": \"1234567545678999\",\n" +
//            "      \"cardIsncSubspDt\": \"20200111\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"isncCd\": \"GSCT\",\n" +
//            "      \"cardNo\": \"2000111122223333\",\n" +
//            "      \"cardIsncSubspDt\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"isncCd\": \"SOIL\",\n" +
//            "      \"cardNo\": \"2100111122224444\",\n" +
//            "      \"cardIsncSubspDt\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"isncCd\": \"HDOL\",\n" +
//            "      \"cardNo\": \"1000111122223333\",\n" +
//            "      \"cardIsncSubspDt\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"isncCd\": \"SKNO\",\n" +
//            "      \"cardNo\": \"4000111122223333\",\n" +
//            "      \"cardIsncSubspDt\": \"\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",BAR_1001.Response.class);
//
//    public static NOT_0001.Response NOT_0001 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"notiInfoList\": [\n" +
//            "    {\n" +
//            "      \"notiNo\": \"1\",\n" +
//            "      \"cateCd\": \"1000\",\n" +
//            "      \"cateNm\": \"APP\",\n" +
//            "      \"notDt\": \"20201017010000\",\n" +
//            "      \"title\": \"제네시스 전 차종과 전 컬러를 경험할 수 있는 특별한 공간 ‘제네시스 수지’ 개관\",\n" +
//            "      \"contents\": \"제네시스의 모든 것을 경험할 수 있는 특별한 공간이 문을 연다. \n" +
//            "\n" +
//            "제네시스 브랜드(이하 제네시스)가 오는 30일 차량 구매 상담을 비롯해 제네시스 전 차종의 다양한 모델을 직접 보고 시승할 수 있는 ‘제네시스 수지 (경기도 용인시 수지구 소재)’를 개관한다. \n" +
//            "\n" +
//            "\n" +
//            "\n" +
//            "‘제네시스 수지’는 2018년 개관한 ‘제네시스 강남(서울시 강남구 소재)’에 이은 제네시스의 두 번째 독립형 전용 전시관으로, 지상 4층 연면적 4,991m2(약 1,510평) 공간에 총 40대의 전시차를 보유한 국내 최대 규모의 제네시스 차량 전시 거점이다.\n" +
//            "\n" +
//            "\n" +
//            "\n" +
//            "‘제네시스 수지’는 제네시스 차량 디자인에서 보이는 절제미와 간결함, 그리고 고급스러움이 전시 공간 그 자체에서 드러나도록 디자인돼, 일반적인 자동차 전시관 하면 떠오르는 이미지에서 벗어나 제네시스만의 브랜드 감성을 담았다.\n" +
//            "\n\",\n" +
//            "      \"readYn\": \"N\",\n" +
//            "      \"msgLnkCd\": \"I\",\n" +
//            "      \"msgLnkUri\": \"www.naver.com\",\n" +
//            "      \"dtlLnkCd\": \"\",\n" +
//            "      \"dtlLnkUri\": \"www.naver.com\",\n" +
//            "      \"imgFilUri1\": \"\",\n" +
//            "      \"imgFilUri2\": \"\",\n" +
//            "      \"imgFilUri3\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"notiNo\": \"2\",\n" +
//            "      \"cateCd\": \"1000\",\n" +
//            "      \"cateNm\": \"APP\",\n" +
//            "      \"notDt\": \"20201018010000\",\n" +
//            "      \"title\": \"제네시스 에어포트서비스 정식 런칭\",\n" +
//            "      \"contents\": \"- 당일 예약은 불가하며, 이용일 기준 최소 1영업일 전 전화예약이 필요합니다.\n" +
//            "\n" +
//            "※ 토~월요일 이용 희망 시 직전 금요일 예약 필수\n" +
//            "\n" +
//            "- 다음의 경우 예약이 불가할 수 있으므로 반드시 예약센터 상담 후 예약 바랍니다.\n" +
//            "\n" +
//            "* 하루 내 출발 및 도착하는 경우\n" +
//            "\n" +
//            "* 1일 예약 가능대수 초과하여 예약이 마감된 경우 등\n" +
//            "\n" +
//            "- 모빌리티케어 및 블루기본점검이 아닌 일반수리/점검은 제공 불가합니다.\n" +
//            "\n" +
//            "- 기타 문의 : 제네시스 컨시어지센터 전화문의\",\n" +
//            "      \"readYn\": \"Y\",\n" +
//            "      \"msgLnkCd\": \"O\",\n" +
//            "      \"msgLnkUri\": \"www.naver.com\",\n" +
//            "      \"dtlLnkCd\": \"\",\n" +
//            "      \"dtlLnkUri\": \"www.naver.com\",\n" +
//            "      \"imgFilUri1\": \"\",\n" +
//            "      \"imgFilUri2\": \"\",\n" +
//            "      \"imgFilUri3\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"notiNo\": \"3\",\n" +
//            "      \"cateCd\": \"2000\",\n" +
//            "      \"cateNm\": \"이벤트\",\n" +
//            "      \"notDt\": \"20201019010000\",\n" +
//            "      \"title\": \"제네시스 스펙트럼 리뉴얼 오픈 이벤트\",\n" +
//            "      \"contents\": \"다양한 제네시스 차량을 바꿔 타는 즐거움과 함께 새롭게 추가될 구독 전용 혜택을 경험하세요.\n" +
//            "제네시스 스펙트럼 앱을 통해 자유롭게 신청하고 원하는 기간만큼 편리하게 이용하세요.\",\n" +
//            "      \"readYn\": \"N\",\n" +
//            "      \"msgLnkCd\": \"\",\n" +
//            "      \"msgLnkUri\": \"\",\n" +
//            "      \"dtlLnkCd\": \"O\",\n" +
//            "      \"dtlLnkUri\": \"www.naver.com\",\n" +
//            "      \"imgFilUri1\": \"\",\n" +
//            "      \"imgFilUri2\": \"\",\n" +
//            "      \"imgFilUri3\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"notiNo\": \"4\",\n" +
//            "      \"cateCd\": \"3000\",\n" +
//            "      \"cateNm\": \"쿠폰함\",\n" +
//            "      \"notDt\": \"20201011010000\",\n" +
//            "      \"title\": \"제네시스 G90, 미국 IIHS 충돌 평가에서 최고 안전한 차로 선정\",\n" +
//            "      \"contents\": \"IIHS는 1959년 설립된 비영리단체로, 매년 출시된 수 백대의 차량의 충돌 안정 성능과 충돌 예방 성능을 종합적으로 평가해 결과를 발표한다. 그 중 최고 안전성을 나타낸 차량에는 톱 세이프티 픽 플러스 등급을 매긴다.\n" +
//            "\n" +
//            "톱 세이프티 픽 플러스 등급을 받으려면 ▲운전석 스몰 오버랩(driver-side small overlap front) ▲조수석 스몰 오버랩(passenger-side small overlap front) ▲전면 충돌(moderate overlap front) ▲측면 충돌(side) ▲지붕 강성(roof strength) ▲머리지지대(head restraint) 등 6개 충돌 안전 항목 평가에서 모두 최고 등급인 우수(good) 평가를 받아야 한다.\n" +
//            "\n" +
//            "(충돌 안전 평가 등급 : 우수(good) > 양호(acceptable) > 보통(Marginal) > 불량(Poor)) (스몰 오버랩 : 실제 사고에서 가장 빈번하게 발생하는 차량의 25% 안팎을 충돌하는 상황을 가정한 테스트)\n" +
//            "\n" +
//            "또한 전방 충돌방지 시스템 테스트(차량과 차량 / 차량과 보행자)에서 상급(advanced) 이상 등급을, 전조등 평가에서 양호(acceptable) 이상 등급을 받아야 한다.\n" +
//            "(전방 충돌방지 시스템 테스트 등급 : 최우수(Superior)\",\n" +
//            "      \"readYn\": \"N\",\n" +
//            "      \"msgLnkCd\": \"\",\n" +
//            "      \"msgLnkUri\": \"\",\n" +
//            "      \"dtlLnkCd\": \"\",\n" +
//            "      \"dtlLnkUri\": \"\",\n" +
//            "      \"imgFilUri1\": \"\",\n" +
//            "      \"imgFilUri2\": \"\",\n" +
//            "      \"imgFilUri3\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"notiNo\": \"5\",\n" +
//            "      \"cateCd\": \"3000\",\n" +
//            "      \"cateNm\": \"쿠폰함\",\n" +
//            "      \"notDt\": \"20201011010000\",\n" +
//            "      \"title\": \"제네시스 홈투홈서비스 정식 런칭\",\n" +
//            "      \"contents\": \"제네시스 구매 고객 대상으로 차별화된 입고 편의를 위해 제공되는 ‘홈투홈서비스’ 무상 쿠폰(모빌리티케어)이 새로운 모습으로 런칭한다.\n" +
//            "\n" +
//            "\n" +
//            "\n" +
//            "기존 서비스에 비해 한층 더 고급화된 제네시스만의 특화 서비스로 19년 시범운영 당시 고객들의 큰  사랑을 받아, 20년 3월 정식 런칭으로 자리 잡을 예정이다.  \n" +
//            "\n" +
//            "\n" +
//            "\n" +
//            "GPS 기반 실시간 출동 기사 위치 정보 알림톡, 제네시스 전문 출동 기사 등 프리미엄 서비스가 제공된다.  \",\n" +
//            "      \"readYn\": \"Y\",\n" +
//            "      \"msgLnkCd\": \"\",\n" +
//            "      \"msgLnkUri\": \"\",\n" +
//            "      \"dtlLnkCd\": \"O\",\n" +
//            "      \"dtlLnkUri\": \"www.naver.com\",\n" +
//            "      \"imgFilUri1\": \"http://placehold.it/400x200/efa/aae&text=dsa\",\n" +
//            "      \"imgFilUri2\": \"\",\n" +
//            "      \"imgFilUri3\": \"\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}", NOT_0001.Response.class);
//
//    public static NOT_0003.Response NOT_0003 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"newNotiCnt\": \"3\",\n" +
//            "  \"rtMsg\": \"Success\"\n" +
//            "}",NOT_0003.Response.class);
//
//
//    public static STO_1001.Response STO_1001 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"estmVhclList\": {\n" +
//            "    \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "    \"vhclNm\": \"GV80\",\n" +
//            "    \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "    \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "    \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "    \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "    \"celphNo\": \"\",\n" +
//            "    \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "  },\n" +
//            "  \"smlrVhclList\": [\n" +
//            "    {\n" +
//            "      \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "      \"vhclNm\": \"GV801\",\n" +
//            "      \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "      \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "      \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "      \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "      \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "      \"vhclNm\": \"GV802\",\n" +
//            "      \"smlrRto\": \"80\",\n" +
//            "      \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "      \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "      \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "      \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "      \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "      \"vhclNm\": \"GV813\",\n" +
//            "      \"smlrRto\": \"90\",\n" +
//            "      \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "      \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "      \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "      \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "      \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "      \"vhclNm\": \"GV813\",\n" +
//            "      \"smlrRto\": \"90\",\n" +
//            "      \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "      \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "      \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "      \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "      \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "      \"vhclNm\": \"GV813\",\n" +
//            "      \"smlrRto\": \"90\",\n" +
//            "      \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "      \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "      \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "      \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "      \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "      \"vhclNm\": \"GV813\",\n" +
//            "      \"smlrRto\": \"90\",\n" +
//            "      \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "      \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "      \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "      \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "      \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "      \"vhclNm\": \"GV813\",\n" +
//            "      \"smlrRto\": \"90\",\n" +
//            "      \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "      \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "      \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "      \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "      \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "      \"vhclNm\": \"GV813\",\n" +
//            "      \"smlrRto\": \"90\",\n" +
//            "      \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "      \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "      \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "      \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "      \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "      \"vhclNm\": \"GV813\",\n" +
//            "      \"smlrRto\": \"90\",\n" +
//            "      \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "      \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "      \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "      \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "      \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "      \"vhclNm\": \"GV813\",\n" +
//            "      \"smlrRto\": \"90\",\n" +
//            "      \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "      \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "      \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "      \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "      \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "      \"vhclNm\": \"GV813\",\n" +
//            "      \"smlrRto\": \"90\",\n" +
//            "      \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "      \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "      \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "      \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "      \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "      \"vhclNm\": \"GV824\",\n" +
//            "      \"smlrRto\": \"99\",\n" +
//            "      \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "      \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "      \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "      \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "      \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",STO_1001.Response.class);
//
//    public static STO_1002.Response STO_1002 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"estmVhclList\": {\n" +
//            "    \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "    \"vhclNm\": \"GV80\",\n" +
//            "    \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "    \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "    \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "    \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "    \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "  },\n" +
//            "  \"smlrVhclList\": [\n" +
//            "    {\n" +
//            "      \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "      \"vhclNm\": \"GV80\",\n" +
//            "      \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "      \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "      \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "      \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "      \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "      \"vhclNm\": \"GV80\",\n" +
//            "      \"smlrRto\": \"80\",\n" +
//            "      \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "      \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "      \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "      \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "      \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "      \"vhclNm\": \"GV81\",\n" +
//            "      \"smlrRto\": \"90\",\n" +
//            "      \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "      \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "      \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "      \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "      \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vhclCd\": \"JJJS4CAT1B08UYHNNB\",\n" +
//            "      \"vhclNm\": \"GV82\",\n" +
//            "      \"smlrRto\": \"99\",\n" +
//            "      \"mdlNm\": \"2.5 가솔린 2WD 4도어 18인치\",\n" +
//            "      \"etrrClrNm\": \"우유니 화이트\",\n" +
//            "      \"itrrClrNm\": \"블랙모노톤(블랙시트)\",\n" +
//            "      \"otpnNm\": \"파노라마 선루프, 파퓰러 패키지\",\n" +
//            "      \"vhclImgUri\": \"http://placehold.it/400x200/eaa/aee&text=placehold.it\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",STO_1002.Response.class);
//
//
//    public static STO_1003.Response STO_1003 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"saleCnttNo\": \"B0119JJ000009\",\n" +
//            "  \"mnfctNm\": \"㈜현대자동차\",\n" +
//            "  \"cnttYmd\": \"20201228\",\n" +
//            "  \"carnNm\": \"자가용 7인승 디젤 3.0 22인치 기본 디자인 4WD 오토 런칭 파퓰러 패키지\",\n" +
//            "  \"xrclCtyNm\": \" 카본 메탈\",\n" +
//            "  \"ieclCtyNm\": \"브라운/베이지 투톤(베이지시트)\",\n" +
//            "  \"saleMdlOptNm\": \"헤드업 디스플레이,하이테크 패키지,드라이빙 어시스턴트 패키지 2,파노라마썬루프^^드라이빙 어시스턴트 패키지 1\",\n" +
//            "  \"dlgYmd\": \" 카본 메탈\",\n" +
//            "  \"cnttStCd1\": \"3000\",\n" +
//            "  \"cnttStCd2\": \"4000\",\n" +
//            "  \"cnttStCd3\": \"1000\",\n" +
//            "  \"carCtyAmt\": \"99000000\",\n" +
//            "  \"optCpndCtyAmt\": \"5121450\",\n" +
//            "  \"xrclCtyAmt\": \"1248512\",\n" +
//            "  \"ieclCtyAmt\": \"122145\",\n" +
//            "  \"rscc2Amt\": \"123214\",\n" +
//            "  \"csptCpndCtyAmt\": \"2222\",\n" +
//            "  \"carTtlAmt\": \"100000000\",\n" +
//            "  \"dcTtlAmt\": \"123461\",\n" +
//            "  \"saleAmt\": \"1236744356\",\n" +
//            "  \"mfsAmt\": \"123123\",\n" +
//            "  \"cnsgCtyAmt\": \"200000\",\n" +
//            "  \"ttlSaleAmt\": \"222000000\",\n" +
//            "  \"incidExp\": \"50\",\n" +
//            "  \"fncIntAmt\": \"1500000\",\n" +
//            "  \"ttlBuyAmt\": \"15000000000\",\n" +
//            "  \"eeNm\": \"카마스터 성명\",\n" +
//            "  \"eeHpTn\": \"01078848751\"\n" +
//            "}",STO_1003.Response.class);
//
////    public static IST_1002.Response IST_1002 = new Gson().fromJson("{\n" +
////            "  \"rtCd\": \"0000\",\n" +
////            "  \"rtMsg\": \"Success\",\n" +
////            "  \"currMthAmt\": {\n" +
////            "    \"totUseAmt\": \"1250000\",\n" +
////            "    \"oilAmt\": \"500000\",\n" +
////            "    \"rparAmt\": \"500000\",\n" +
////            "    \"carWshAmt\": \"400000\",\n" +
////            "    \"etcAmt\": \"250000\"\n" +
////            "  },\n" +
////            "  \"prvsMthAmt\": {\n" +
////            "    \"totUseAmt\": \"578500\",\n" +
////            "    \"oilAmt\": \"250000\",\n" +
////            "    \"rparAmt\": \"250000\",\n" +
////            "    \"carWshAmt\": \"0\",\n" +
////            "    \"etcAmt\": \"78500\"\n" +
////            "  }\n" +
////            "}", IST_1002.Response.class);
////
////    public static IST_1003.Response IST_1003 = new Gson().fromJson("{\n" +
////        "  \"rtCd\": \"0000\",\n" +
////        "  \"rtMsg\": \"Success\",\n" +
////        "  \"admMsgList\": [\n" +
////        "    {\n" +
////        "      \"msgTypCd\": \"TIL\",\n" +
////        "      \"txtMsg1\": \"1영역테스트메시지입니다.\",\n" +
////        "      \"txtMsg2\": \"테스트 샘플입니다.\",\n" +
////        "      \"imgUri1\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495.png\",\n" +
////        "      \"imgUri2\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_01.png\",\n" +
////        "      \"imgUri3\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_02.png\",\n" +
////        "      \"lnkNm\": \"바로가기\",\n" +
////        "      \"lnkTypCd\": \"O\",\n" +
////        "      \"lnkUri\": \"https://www.genesis.com/kr/ko/main.html\"\n" +
////        "    },\n" +
////        "    {\n" +
////        "      \"msgTypCd\": \"TIL\",\n" +
////        "      \"txtMsg1\": \"2영역테스트메시지입니다.\",\n" +
////        "      \"txtMsg2\": \"테스트 샘플입니다.\",\n" +
////        "      \"imgUri1\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495.png\",\n" +
////        "      \"imgUri2\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_01.png\",\n" +
////        "      \"imgUri3\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_02.png\",\n" +
////        "      \"lnkNm\": \"바로가기\",\n" +
////        "      \"lnkTypCd\": \"O\",\n" +
////        "      \"lnkUri\": \"https://www.genesis.com/kr/ko/main.html\"\n" +
////        "    },\n" +
////        "    {\n" +
////        "      \"msgTypCd\": \"TIL\",\n" +
////        "      \"txtMsg1\": \"3영역테스트메시지입니다.\",\n" +
////        "      \"txtMsg2\": \"테스트 샘플입니다.\",\n" +
////        "      \"imgUri1\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495.png\",\n" +
////        "      \"imgUri2\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_01.png\",\n" +
////        "      \"imgUri3\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_02.png\",\n" +
////        "      \"lnkNm\": \"바로가기\",\n" +
////        "      \"lnkTypCd\": \"O\",\n" +
////        "      \"lnkUri\": \"https://www.genesis.com/kr/ko/main.html\"\n" +
////        "    },\n" +
////        "    {\n" +
////        "      \"msgTypCd\": \"TIL\",\n" +
////        "      \"txtMsg1\": \"4영역테스트메시지입니다.\",\n" +
////        "      \"txtMsg2\": \"테스트 샘플입니다.\",\n" +
////        "      \"imgUri1\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495.png\",\n" +
////        "      \"imgUri2\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_01.png\",\n" +
////        "      \"imgUri3\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_02.png\",\n" +
////        "      \"lnkNm\": \"바로가기\",\n" +
////        "      \"lnkTypCd\": \"O\",\n" +
////        "      \"lnkUri\": \"https://www.genesis.com/kr/ko/main.html\"\n" +
////        "    },\n" +
////        "    {\n" +
////        "      \"msgTypCd\": \"TIL\",\n" +
////        "      \"txtMsg1\": \"5영역테스트메시지입니다.\",\n" +
////        "      \"txtMsg2\": \"테스트 샘플입니다.\",\n" +
////        "      \"imgUri1\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495.png\",\n" +
////        "      \"imgUri2\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_01.png\",\n" +
////        "      \"imgUri3\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_02.png\",\n" +
////        "      \"lnkNm\": \"바로가기\",\n" +
////        "      \"lnkTypCd\": \"O\",\n" +
////        "      \"lnkUri\": \"https://www.genesis.com/kr/ko/main.html\"\n" +
////        "    },\n" +
////        "    {\n" +
////        "      \"msgTypCd\": \"TIL\",\n" +
////        "      \"txtMsg1\": \"6영역테스트메시지입니다.\",\n" +
////        "      \"txtMsg2\": \"테스트 샘플입니다.\",\n" +
////        "      \"imgUri1\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495.png\",\n" +
////        "      \"imgUri2\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_01.png\",\n" +
////        "      \"imgUri3\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_02.png\",\n" +
////        "      \"lnkNm\": \"바로가기\",\n" +
////        "      \"lnkTypCd\": \"O\",\n" +
////        "      \"lnkUri\": \"https://www.genesis.com/kr/ko/main.html\"\n" +
////        "    },\n" +
////        "    {\n" +
////        "      \"msgTypCd\": \"TIL\",\n" +
////        "      \"txtMsg1\": \"7영역테스트메시지입니다.\",\n" +
////        "      \"txtMsg2\": \"테스트 샘플입니다.\",\n" +
////        "      \"imgUri1\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495.png\",\n" +
////        "      \"imgUri2\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_01.png\",\n" +
////        "      \"imgUri3\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_02.png\",\n" +
////        "      \"lnkNm\": \"바로가기\",\n" +
////        "      \"lnkTypCd\": \"O\",\n" +
////        "      \"lnkUri\": \"https://www.genesis.com/kr/ko/main.html\"\n" +
////        "    },\n" +
////        "    {\n" +
////        "      \"msgTypCd\": \"TIL\",\n" +
////        "      \"txtMsg1\": \"8영역테스트메시지입니다.\",\n" +
////        "      \"txtMsg2\": \"테스트 샘플입니다.\",\n" +
////        "      \"imgUri1\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495.png\",\n" +
////        "      \"imgUri2\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_01.png\",\n" +
////        "      \"imgUri3\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_02.png\",\n" +
////        "      \"lnkNm\": \"바로가기\",\n" +
////        "      \"lnkTypCd\": \"O\",\n" +
////        "      \"lnkUri\": \"https://www.genesis.com/kr/ko/main.html\"\n" +
////        "    },\n" +
////        "    {\n" +
////        "      \"msgTypCd\": \"TIL\",\n" +
////        "      \"txtMsg1\": \"9영역테스트메시지입니다.\",\n" +
////        "      \"txtMsg2\": \"테스트 샘플입니다.\",\n" +
////        "      \"imgUri1\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495.png\",\n" +
////        "      \"imgUri2\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_01.png\",\n" +
////        "      \"imgUri3\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_02.png\",\n" +
////        "      \"lnkNm\": \"바로가기\",\n" +
////        "      \"lnkTypCd\": \"O\",\n" +
////        "      \"lnkUri\": \"https://www.genesis.com/kr/ko/main.html\"\n" +
////        "    },\n" +
////        "    {\n" +
////        "      \"msgTypCd\": \"TIL\",\n" +
////        "      \"txtMsg1\": \"10영역테스트메시지입니다.\",\n" +
////        "      \"txtMsg2\": \"테스트 샘플입니다.\",\n" +
////        "      \"imgUri1\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495.png\",\n" +
////        "      \"imgUri2\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_01.png\",\n" +
////        "      \"imgUri3\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_02.png\",\n" +
////        "      \"lnkNm\": \"바로가기\",\n" +
////        "      \"lnkTypCd\": \"O\",\n" +
////        "      \"lnkUri\": \"https://www.genesis.com/kr/ko/main.html\"\n" +
////        "    },\n" +
////        "    {\n" +
////        "      \"msgTypCd\": \"TIL\",\n" +
////        "      \"txtMsg1\": \"11영역테스트메시지입니다.\",\n" +
////        "      \"txtMsg2\": \"테스트 샘플입니다.\",\n" +
////        "      \"imgUri1\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495.png\",\n" +
////        "      \"imgUri2\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_01.png\",\n" +
////        "      \"imgUri3\": \"https://stg-kr-ccapi.genesis.com:8081/api/v1/graapi/nl/granas/insight/admin/KakaoTalk_20201218_092622495_02.png\",\n" +
////        "      \"lnkNm\": \"바로가기\",\n" +
////        "      \"lnkTypCd\": \"O\",\n" +
////        "      \"lnkUri\": \"https://www.genesis.com/kr/ko/main.html\"\n" +
////        "    }\n" +
////        "  ]\n" +
////        "}", IST_1003.Response.class);
////
////
////
////    public static IST_1004.Response IST_1004= new Gson().fromJson("{\n" +
////            "  \"rtCd\": \"0000\",\n" +
////            "  \"rtMsg\": \"성공\",\n" +
////            "  \"sosStatus\": \n" +
////            "    {\n" +
////            "      \"sAllocNm\": \"박현준\",\n" +
////            "      \"controlTel\": \"01086029612\",\n" +
////            "      \"carNo\": \"16조6840\",\n" +
////            "      \"carTypeNm\": \"SANTAFE\",\n" +
////            "      \"receiveDtm\": \"20200801\",\n" +
////            "      \"gXpos\": \"1\",\n" +
////            "      \"gYpos\": \"1\",\n" +
////            "      \"gCustX\": \"1\",\n" +
////            "      \"gCustY\": \"1\",\n" +
////            "      \"startX\": \"1\",\n" +
////            "      \"startY\": \"1\",\n" +
////            "      \"finishX\": \"1\",\n" +
////            "      \"finishY\": \"1\"\n" +
////            "    }\n" +
////            "}",IST_1004.Response.class);
////
////
////    public static IST_1005.Response IST_1005= new Gson().fromJson("{\n" +
////            "  \"rtCd\": \"0000\",\n" +
////            "  \"rtMsg\": \"성공\",\n" +
////            "  \"msgList\": [\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"HAPPY GV80 DAY!\",\n" +
////            "      \"txtMsg\": \"오늘은 GV80 구입하신지\n" +
////            "n년 되는 날이예요!\n \n" +
////            "오래오래 안전하게 운행하세요:)\",\n" +
////            "      \"imgUri\": \"1\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"김포공항 무상 발렛서비스\",\n" +
////            "      \"txtMsg\": \"김포공항을 방문할 일이 \n" +
////            "있으면 한번 사용해보세요!\",\n" +
////            "      \"imgUri\": \"2\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"제네시스 전용 안심 주차\n\n" +
////            "(5천원/일, 5일째부터 1만원/일)\",\n" +
////            "      \"txtMsg\": \"김포공항을 방문할 일이 \n" +
////            "있으면 한번 사용해보세요!\",\n" +
////            "      \"imgUri\": \"2\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"소모품 교환\n\n" +
////            "(모빌리티케어 항목)\",\n" +
////            "      \"txtMsg\": \"김포공항을 방문할 일이 \n" +
////            "있으면 한번 사용해보세요!\",\n" +
////            "      \"imgUri\": \"2\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"기본점검 15종\",\n" +
////            "      \"txtMsg\": \"김포공항을 방문할 일이 \n" +
////            "있으면 한번 사용해보세요!\",\n" +
////            "      \"imgUri\": \"2\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"사전점검\n\n" +
////            "(SW업데이트 항목)\",\n" +
////            "      \"txtMsg\": \"김포공항을 방문할 일이 \n" +
////            "있으면 한번 사용해보세요!\",\n" +
////            "      \"imgUri\": \"2\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"000구간 침수!\",\n" +
////            "      \"txtMsg\": \"도로가 통제되었어요!\n \n" +
////            "오늘은 다른 길을 이용하세요!\",\n" +
////            "      \"imgUri\": \"3\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"픽업서비스\",\n" +
////            "      \"txtMsg\": \"고객님의 서비스 사용 가능\n\n" +
////            "기간은 2020.09.30까지입니다.\",\n" +
////            "      \"imgUri\": \"4\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"딜리버리서비스\",\n" +
////            "      \"txtMsg\": \"고객님의 서비스 사용 가능\n" +
////            "기간은 2020.09.30까지입니다.\",\n" +
////            "      \"imgUri\": \"4\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"픽업/딜리버리서비스\",\n" +
////            "      \"txtMsg\": \"고객님의 서비스 사용 가능\n" +
////            "기간은 2020.09.30까지입니다.\",\n" +
////            "      \"imgUri\": \"4\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"00행사로 도로 통제!\",\n" +
////            "      \"txtMsg\": \"도로가 통제되었어요!\n" +
////            "외출시 참고하세요!\",\n" +
////            "      \"imgUri\": \"3\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"엔진오일교환 서비스\",\n" +
////            "      \"txtMsg\": \"고객님의 서비스 사용 가능\n" +
////            "기간은 2020.09.30까지입니다.\",\n" +
////            "      \"imgUri\": \"5\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"에어컨 필터 교환 서비스\",\n" +
////            "      \"txtMsg\": \"고객님의 서비스 사용 가능\n" +
////            "기간은 2020.09.30까지입니다.\",\n" +
////            "      \"imgUri\": \"5\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"에어컨 필터 교환 서비스\",\n" +
////            "      \"txtMsg\": \"고객님의 서비스 사용 가능\n" +
////            "기간은 2020.09.30까지입니다.\",\n" +
////            "      \"imgUri\": \"5\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"와이퍼 블레이드 서비스\",\n" +
////            "      \"txtMsg\": \"고객님의 서비스 사용 가능\n" +
////            "기간은 2020.09.30까지입니다.\",\n" +
////            "      \"imgUri\": \"5\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"내비게이션 업데이트\",\n" +
////            "      \"txtMsg\": \"고객님의 서비스 사용 가능\n" +
////            "기간은 2020.09.30까지입니다.\",\n" +
////            "      \"imgUri\": \"5\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"프리빌리지 신청을\n" +
////            "안하셨네요!\",\n" +
////            "      \"txtMsg\": \"GV80고객분들이 가장 선택한\n" +
////            "혜택은 000이예요!\",\n" +
////            "      \"imgUri\": \"6\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"정유사를 연동 해보세요!\",\n" +
////            "      \"txtMsg\": \"자동으로 주유 금액이\n" +
////            "입력됩니다. \",\n" +
////            "      \"imgUri\": \"7\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"스노우 타이어 교체를\n" +
////            "준비하세요!\",\n" +
////            "      \"txtMsg\": \"이번 주 첫 눈이 내린다는 \n" +
////            "예보가 있네요.\",\n" +
////            "      \"imgUri\": \"8\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"세차는 다음에 하세요!\",\n" +
////            "      \"txtMsg\": \"이번 주말에는 \n" +
////            "비가 온다는 예보가 있네요.\",\n" +
////            "      \"imgUri\": \"9\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"msgTypCd\": \"TXT\",\n" +
////            "      \"ttl\": \"이번 주말 스노우 타이어\n" +
////            "교체를 준비하세요!\",\n" +
////            "      \"txtMsg\": \"\",\n" +
////            "      \"imgUri\": \"8\",\n" +
////            "      \"lnkNm\": \"\",\n" +
////            "      \"lnkTypCd\": \"O\",\n" +
////            "      \"lnkUri\": \"\"\n" +
////            "    }\n" +
////            "  ]\n" +
////            "}",IST_1005.Response.class);
//
//
//    public static String CTT_1001= "{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"ttlList\": [\n" +
//            "    {\n" +
//            "      \"listSeqNo\": \"1\",\n" +
//            "      \"catCd\": \"1000\",\n" +
//            "      \"ttImgUri\": \"https://dive.hyundaicard.com/web/content/contentView.hdc?contentId=2741\",\n" +
//            "      \"dtlViewCd\": \"1000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"listSeqNo\": \"2\",\n" +
//            "      \"catCd\": \"1000\",\n" +
//            "      \"ttImgUri\": \"https://dive.hyundaicard.com/web/content/contentView.hdc?contentId=2749&cookieDiveWeb=Y\",\n" +
//            "      \"dtlViewCd\": \"1000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"listSeqNo\": \"3\",\n" +
//            "      \"catCd\": \"1000\",\n" +
//            "      \"ttImgUri\": \"https://dive.hyundaicard.com/web/content/contentView.hdc?contentId=2414&cookieDiveWeb=Y\",\n" +
//            "      \"dtlViewCd\": \"1000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"listSeqNo\": \"4\",\n" +
//            "      \"catCd\": \"2000\",\n" +
//            "      \"ttImgUri\": \"https://dive.hyundaicard.com/web/content/contentView.hdc?contentId=2652&cookieDiveWeb=Y\",\n" +
//            "      \"dtlViewCd\": \"1000\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}";
//
//
////    public static CTT_1001.Response CTT_1001= new Gson().fromJson("{\n" +
////            "  \"rtCd\": \"0000\",\n" +
////            "  \"rtMsg\": \"성공\",\n" +
////            "  \"ttlList\": [\n" +
////            "    {\n" +
////            "      \"listSeqNo\": \"1\",\n" +
////            "      \"catCd\": \"1000\",\n" +
////            "      \"ttImgUri\": \"https://dive.hyundaicard.com/web/content/contentView.hdc?contentId=2741\",\n" +
////            "      \"dtlViewCd\": \"1000\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"listSeqNo\": \"2\",\n" +
////            "      \"catCd\": \"1000\",\n" +
////            "      \"ttImgUri\": \"https://dive.hyundaicard.com/web/content/contentView.hdc?contentId=2749&cookieDiveWeb=Y\",\n" +
////            "      \"dtlViewCd\": \"1000\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"listSeqNo\": \"3\",\n" +
////            "      \"catCd\": \"1000\",\n" +
////            "      \"ttImgUri\": \"https://dive.hyundaicard.com/web/content/contentView.hdc?contentId=2414&cookieDiveWeb=Y\",\n" +
////            "      \"dtlViewCd\": \"1000\"\n" +
////            "    },\n" +
////            "    {\n" +
////            "      \"listSeqNo\": \"4\",\n" +
////            "      \"catCd\": \"2000\",\n" +
////            "      \"ttImgUri\": \"https://dive.hyundaicard.com/web/content/contentView.hdc?contentId=2652&cookieDiveWeb=Y\",\n" +
////            "      \"dtlViewCd\": \"1000\"\n" +
////            "    }\n" +
////            "  ]\n" +
////            "}",CTT_1001.Response.class);
//
//    public static CTT_1002.Response CTT_1002 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\"\n" +
//            "}",CTT_1002.Response.class);
//    public static CTT_1004.Response CTT_1004 = new Gson().fromJson("",CTT_1004.Response.class);
//
//
//    public static CBK_1002.Response CBK_1002 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"totCnt\": \"23\",\n" +
//            "  \"delYn\": \"Y\",\n" +
//            "  \"refulSumAmt\": \"430000\",\n" +
//            "  \"rparSumAmt\": \"1103000\",\n" +
//            "  \"carWshSumAmt\": \"183000\",\n" +
//            "  \"etcSumAmt\": \"180000\",\n" +
//            "  \"expnList\": [\n" +
//            "    {\n" +
//            "      \"expnSeqNo\": \"2020101100000020\",\n" +
//            "      \"vin\": \"AWJDIWHD234213KJ\",\n" +
//            "      \"carRgstNo\": \"99호9999\",\n" +
//            "      \"mdlNm\": \"GV80\",\n" +
//            "      \"expnDivNm\": \"통행\",\n" +
//            "      \"expnDtm\": \"20201011180213\",\n" +
//            "      \"expnAmt\": \"80000\",\n" +
//            "      \"accmMilg\": \"3\",\n" +
//            "      \"expnPlc\": \"현대 통행\",\n" +
//            "      \"rgstChnlNm\": \"본인등록\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"expnSeqNo\": \"2020101200000019\",\n" +
//            "      \"vin\": \"AWJDIWHD234213KJ\",\n" +
//            "      \"carRgstNo\": \"99호9999\",\n" +
//            "      \"mdlNm\": \"GV80\",\n" +
//            "      \"expnDivNm\": \"주차\",\n" +
//            "      \"expnDtm\": \"20201013180158\",\n" +
//            "      \"expnAmt\": \"80000\",\n" +
//            "      \"accmMilg\": \"3\",\n" +
//            "      \"expnPlc\": \"현대 주차\",\n" +
//            "      \"rgstChnlNm\": \"본인등록\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"expnSeqNo\": \"2020101300000017\",\n" +
//            "      \"vin\": \"AWJDIWHD234213KJ\",\n" +
//            "      \"carRgstNo\": \"99호9999\",\n" +
//            "      \"mdlNm\": \"GV80\",\n" +
//            "      \"expnDivNm\": \"세차\",\n" +
//            "      \"expnDtm\": \"20201015180134\",\n" +
//            "      \"expnAmt\": \"80000\",\n" +
//            "      \"accmMilg\": \"3\",\n" +
//            "      \"expnPlc\": \"현대 세차\",\n" +
//            "      \"rgstChnlNm\": \"본인등록\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"expnSeqNo\": \"2020101000000016\",\n" +
//            "      \"vin\": \"AWJDIWHD234213KJ\",\n" +
//            "      \"carRgstNo\": \"99호9999\",\n" +
//            "      \"mdlNm\": \"GV80\",\n" +
//            "      \"expnDivNm\": \"세차\",\n" +
//            "      \"expnDtm\": \"20201010180128\",\n" +
//            "      \"expnAmt\": \"80000\",\n" +
//            "      \"accmMilg\": \"3\",\n" +
//            "      \"expnPlc\": \"현대 세차\",\n" +
//            "      \"rgstChnlNm\": \"본인등록\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"expnSeqNo\": \"2020101000000014\",\n" +
//            "      \"vin\": \"AWJDIWHD234213KJ\",\n" +
//            "      \"carRgstNo\": \"99호9999\",\n" +
//            "      \"mdlNm\": \"GV80\",\n" +
//            "      \"expnDivNm\": \"정비\",\n" +
//            "      \"expnDtm\": \"20201005180103\",\n" +
//            "      \"expnAmt\": \"80000\",\n" +
//            "      \"accmMilg\": \"3\",\n" +
//            "      \"expnPlc\": \"현대 정비\",\n" +
//            "      \"rgstChnlNm\": \"본인등록\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"expnSeqNo\": \"2020101000000014\",\n" +
//            "      \"vin\": \"AWJDIWHD234213KJ\",\n" +
//            "      \"carRgstNo\": \"99호9999\",\n" +
//            "      \"mdlNm\": \"GV80\",\n" +
//            "      \"expnDivNm\": \"정비\",\n" +
//            "      \"expnDtm\": \"20201005180103\",\n" +
//            "      \"expnAmt\": \"80000\",\n" +
//            "      \"accmMilg\": \"3\",\n" +
//            "      \"expnPlc\": \"현대 정비\",\n" +
//            "      \"rgstChnlNm\": \"본인등록\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"expnSeqNo\": \"2020101000000014\",\n" +
//            "      \"vin\": \"AWJDIWHD234213KJ\",\n" +
//            "      \"carRgstNo\": \"99호9999\",\n" +
//            "      \"mdlNm\": \"GV80\",\n" +
//            "      \"expnDivNm\": \"정비\",\n" +
//            "      \"expnDtm\": \"20201005180103\",\n" +
//            "      \"expnAmt\": \"80000\",\n" +
//            "      \"accmMilg\": \"3\",\n" +
//            "      \"expnPlc\": \"현대 정비\",\n" +
//            "      \"rgstChnlNm\": \"본인등록\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"expnSeqNo\": \"2020101000000014\",\n" +
//            "      \"vin\": \"AWJDIWHD234213KJ\",\n" +
//            "      \"carRgstNo\": \"99호9999\",\n" +
//            "      \"mdlNm\": \"GV80\",\n" +
//            "      \"expnDivNm\": \"정비\",\n" +
//            "      \"expnDtm\": \"20201005180103\",\n" +
//            "      \"expnAmt\": \"80000\",\n" +
//            "      \"accmMilg\": \"3\",\n" +
//            "      \"expnPlc\": \"현대 정비\",\n" +
//            "      \"rgstChnlNm\": \"본인등록\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"expnSeqNo\": \"2020101000000011\",\n" +
//            "      \"vin\": \"AWJDIWHD234213KJ\",\n" +
//            "      \"carRgstNo\": \"99호9999\",\n" +
//            "      \"mdlNm\": \"GV80\",\n" +
//            "      \"expnDivNm\": \"주유\",\n" +
//            "      \"expnDtm\": \"20201003180025\",\n" +
//            "      \"expnAmt\": \"80000\",\n" +
//            "      \"accmMilg\": \"3\",\n" +
//            "      \"expnPlc\": \"현대 주유소\",\n" +
//            "      \"rgstChnlNm\": \"본인등록\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"expnSeqNo\": \"2020101000000010\",\n" +
//            "      \"vin\": \"AWJDIWHD234213KJ\",\n" +
//            "      \"carRgstNo\": \"99호9999\",\n" +
//            "      \"mdlNm\": \"GV80\",\n" +
//            "      \"expnDivNm\": \"주유\",\n" +
//            "      \"expnDtm\": \"20201010180018\",\n" +
//            "      \"expnAmt\": \"80000\",\n" +
//            "      \"accmMilg\": \"3\",\n" +
//            "      \"expnPlc\": \"현대 주유소\",\n" +
//            "      \"rgstChnlNm\": \"본인등록\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"expnSeqNo\": \"2020101400000026\",\n" +
//            "      \"vin\": \"AWJDIWHD234213KJ\",\n" +
//            "      \"mdlNm\": \"GV80\",\n" +
//            "      \"expnDivNm\": \"주유\",\n" +
//            "      \"expnDtm\": \"20201002171849\",\n" +
//            "      \"expnAmt\": \"20000\",\n" +
//            "      \"accmMilg\": \"100\",\n" +
//            "      \"expnPlc\": \"현대 주유소\",\n" +
//            "      \"rgstChnlNm\": \"본인등록\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}", CBK_1002.Response.class);
//
//
//    public static CBK_1005.Response CBK_1005 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"accmMilg\": \"12045401\"\n" +
//            "}",CBK_1005.Response.class);
//
//
//    public static CBK_1006.Response CBK_1006 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\"\n" +
//            "}",CBK_1006.Response.class);
//
//    public static CBK_1007.Response CBK_1007 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\"\n" +
//            "}",CBK_1007.Response.class);
//
//    public static CBK_1008.Response CBK_1008 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\"\n" +
//            "}",CBK_1008.Response.class);
//
//
//
//    public static WRT_1001.Response WRT_1001 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"contList\": [\n" +
//            "    {\n" +
//            "      \"vin\": \"KMHFF41CBHA584883\",\n" +
//            "      \"type\": \"1010\",\n" +
//            "      \"contents\": \"http://211.54.75.18:7070/genesis/app/html/repair_guidance_G90.html#\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"vin\": \"KMHFF41CBHA584883\",\n" +
//            "      \"type\": \"1011\",\n" +
//            "      \"contents\": \"http://211.54.75.18:7070/genesis/app/html/exhaust_gas_G90.html\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",WRT_1001.Response.class);
//
//    public static MYP_8001.Response MYP_8001_1000 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"termList\": [\n" +
//            "    {\n" +
//            "      \"termCd\": \"1000\",\n" +
//            "      \"termVer\": \"01.03\",\n" +
//            "      \"termNm\": \"앱 이용 약관\",\n" +
//            "      \"termCont\": \"http://211.54.75.18:7070/genesis/app/html/terms_HOB04.html\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}", MYP_8001.Response.class);
//
//    public static MYP_8001.Response MYP_8001_2000 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"termList\": [\n" +
//            "    {\n" +
//            "      \"termCd\": \"1000\",\n" +
//            "      \"termVer\": \"01.03\",\n" +
//            "      \"termNm\": \"개인정보처리방침\",\n" +
//            "      \"termCont\": \"http://211.54.75.18:7070/genesis/app/html/terms_HOB04.html\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}", MYP_8001.Response.class);
//
//    public static MYP_8001.Response MYP_8001_3000 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"termList\": [\n" +
//            "    {\n" +
//            "      \"termCd\": \"1000\",\n" +
//            "      \"termVer\": \"01.03\",\n" +
//            "      \"termNm\": \"개인정보 수집/이용\",\n" +
//            "      \"termCont\": \"http://211.54.75.18:7070/genesis/app/html/terms_HOB04.html\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}", MYP_8001.Response.class);
//
//    public static MYP_8001.Response MYP_8001_4000 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"termList\": [\n" +
//            "    {\n" +
//            "      \"termCd\": \"1000\",\n" +
//            "      \"termVer\": \"01.03\",\n" +
//            "      \"termNm\": \"광고성 정보 수신동의\",\n" +
//            "      \"termCont\": \"http://211.54.75.18:7070/genesis/app/html/terms_HOB04.html\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}", MYP_8001.Response.class);
//
//    public static MYP_8001.Response MYP_8001_5000 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"termList\": [\n" +
//            "    {\n" +
//            "      \"termCd\": \"1000\",\n" +
//            "      \"termVer\": \"01.03\",\n" +
//            "      \"termNm\": \"제네시스 멤버십 가입 약관\",\n" +
//            "      \"termCont\": \"http://211.54.75.18:7070/genesis/app/html/terms_HOB04.html\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}", MYP_8001.Response.class);
//
//
//    public static MYP_8005.Response MYP_8005 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"notiList\": [\n" +
//            "    {\n" +
//            "      \"seqNo\": \"1\",\n" +
//            "      \"notiTtl\": \"제네시스, 『더 뉴 G70』 출시\",\n" +
//            "      \"notiCont\": \"더 뉴 G70는 ‘제네시스의 가장 역동적인 스포츠 세단’이라는 수식어에 걸맞은 디자인을 갖췄다.\\n\\n\\n\\n\\t전면부는 낮게 위치한 ‘크레스트 그릴’과 그릴 양 옆으로 날개처럼 뻗어나가는 두 줄 디자인의 ‘쿼드램프’가 제네시스 엠블럼(emblem)을 형상화하며 금방이라도 앞으로 달려나갈 듯한 긴장감을 연출한다. \\n\\n\\n\\n\\t측면부는 긴 후드(엔진부 덮개)와 짧은 전방 오버행(차량 끝에서 바퀴 중심까지 거리)으로 표현한 기존 G70의 역동적인 비율을 계승하고 공력 효율에 최적화된 사이드 벤트(공기 배출구)와 신규 G70 전용 휠을 적용해 민첩한 주행성능을 시각적으로 구현했다.\\n\\n\\n\\n\\t후면부는 쿼드램프로 제네시스의 정체성을 명확하게 표현했으며 듀얼 머플러(좌ᆞ우 배기구)와 차체와 동일한 색상의 디퓨저(Diffuser)로 고성능 세단의 모습을 완성했다.\\n\\n\\t[※ 디퓨저: 차체 하부 공기 흐름을 개선해 주행시 차체를 아래로 누르는 힘(다운포스)을 증대, 고속 주행시 주행안정성을 확보하는 장치]\\n\\n\\n\\n\\t운전자 중심 구조를 계승한 실내는 10.25인치 인포테인먼트 시스템과 충전속도가 향상된 스마트폰 무선충전 시스템이 새롭게 적용됐다.\\n\\n\\n\\n\\t아울러 기본형 계기반의 화면(클러스터 디스플레이)을 3.5인치에서 8인치로 크기를 키워 각종 주행정보의 시인성을 높였다.\\n\\n\\n\\n\\t또한 앞 유리(윈드실드 글라스)와 1열 창문에 이중접합 차음유리를 기본 적용해 실내 정숙성을 개선했다. \\n\\n\\n\\n\\t한편 제네시스는 더 뉴 G70를 기존 운영 중인 9가지 색상에 5 가지를 더해 총 14 가지 색상으로 운영한다.\\n\\n\\n\\n\\t새롭게 추가되는 색상은 ▲제네시스가 처음 선보이는 본드 실버를 포함해 ▲베르비에 화이트 ▲멜버른 그레이 등 무광 색상 3가지와 ▲세도나 브라운 ▲태즈먼 블루 등 유광 색상 2가지다.\",\n" +
//            "      \"trmsSrtDtm\": \"20200908141014\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"seqNo\": \"2\",\n" +
//            "      \"notiTtl\": \"제네시스 멤버십 제휴 서비스 종료안내\",\n" +
//            "      \"notiCont\": \"항상 제네시스를 아껴주시는 고객 여러분께 감사드립니다.\\n\\n\\n\\n멤버십 제휴 서비스 중 종료되는 서비스를 아래와 같이 안내드립니다.\",\n" +
//            "      \"trmsSrtDtm\": \"20200908132039\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"seqNo\": \"3\",\n" +
//            "      \"notiTtl\": \"Genesis Connected Services 개인정보 처리방침 개정 공지\",\n" +
//            "      \"notiCont\": \"※ 자세한 사항은 개정된 Genesis Connected Services 개인정보 처리방침(’20년 10월 9일)을 참조하시길 바랍니다.\\n\\n변경 약관 적용일 : 2020년 10월 9일\\n\\n감사합니다.\",\n" +
//            "      \"trmsSrtDtm\": \"20200908115134\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"seqNo\": \"4\",\n" +
//            "      \"notiTtl\": \"Genesis Connected Services 일시 중지 안내\",\n" +
//            "      \"notiCont\": \"안녕하세요.\\n제네시스 커넥티드 서비스가 시스템 개선 작업으로 인해 일시 중지됨을 알려드립니다.\\n\\n■ 시스템 개선 일시 : 2020년 8월 21일(금) 01시 00분 ~ 2020년 8월 21일(금) 01시 30분\\n■ 대상 서비스 : 제네시스 커넥티드 서비스 앱 관련 서비스 (앱 로그인, 원격 제어 등)\\n\\n고객님께 불편을 끼쳐드려 죄송합니다.\\n서비스 품질 향상을 위한 불가피한 조치이오니 고객님의 너그러운 양해 부탁드립니다.\",\n" +
//            "      \"trmsSrtDtm\": \"20200908115129\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"seqNo\": \"5\",\n" +
//            "      \"notiTtl\": \"제네시스 2020 레드닷 어워드 4개부문 수상\",\n" +
//            "      \"notiCont\": \"제네시스가 세계적인 디자인 상인 레드 닷 어워드에서 4개의 상을 동시에 받았다. \\n\\n\\n\\n제네시스 브랜드는 독일 노르트하인 베스트팔렌 디자인센터(Design Zentrum Nordhein Westfalen)가 주관하는 ‘2020 레드 닷 어워드(2020 Red Dot Award)’의\\n\\n브랜드ᆞ커뮤니케이션 분야에서 본상(Winner) 4개를 수상했다고 4일(화) 밝혔다. \\n\\n\\n\\n1955년 시작된 레드 닷 어워드는 iF, IDEA 디자인상과 함께 세계 3대 디자인상 중 하나로 평가받고 있으며 매년 제품 디자인, 브랜드ᆞ커뮤니케이션, 디자인 콘셉트\\n\\n3개 분야를 대상으로 공모전을 진행, 각 부문 수상작을 발표하고 있다. \\n\\n\\n\\n제네시스는 G80와 GV80에 적용한 ▲인포테인먼트 시스템의 카퍼(Copper) 디자인으로 인터페이스 디자인(Interface design) 부문 본상을 수상했다.\\n\\n제네시스의 이번 수상은 현대차와 기아차를 포함한 현대차그룹이 인포테인먼트 시스템으로 받은 첫 디자인 상이라 그 의미가 각별하다. \\n\\n\\n\\n제네시스의 컬러 DNA를 계승한 카퍼 디자인은 증강현실 내비게이션, 제네시스 카페이 등 인포테인먼트 시스템 내 다양한 디지털 콘텐츠의 시인성을 높여 운전자에게 최적의 사용 경험을 제공한다.\\n\\n또한 제네시스는 브랜드 체험관인 제네시스 스튜디오 하남으로 ‘리테일 디자인 – 쇼룸 (Retail Design – Showroom)’ 부문 본상을 수상했고 해당 공간에서의 G90 및\\n\\n민트 컨셉카 런칭 기념 특별전시로 ‘Spatial Communication - Light installations 와 Event Design’ 부문 본상을 각각 수상하는 등 공간 크리에이티브 우수성을 입증하게 되었다. \\n\\n\\n\\n2016년 제네시스 브랜드의 첫 번째 몰타입 (Mall-type) 전시장 컨셉으로 오픈한 제네시스 스튜디오 하남은 기존 자동차 전시장과 차별화된 구매부담 없는 오픈형 공간으로 설계되었고 신차 및 컨셉카 런칭 시 특별전시와 고객 소통 프로그램을 통해 혁신적인 자동차 쇼룸 플랫폼으로 평가되고 있다.\",\n" +
//            "      \"trmsSrtDtm\": \"20200908115124\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}", MYP_8005.Response.class);
//
//
//
//    public static MYP_0001.Response MYP_0001 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"mbrStustCd\": \"1000\",\n" +
//            "  \"ccspEmail\": \"kim.genesis@email.com\",\n" +
//            "  \"mbrNm\": \"김수현\",\n" +
//            "  \"brtdy\": \"19800102\",\n" +
//            "  \"sexDiv\": \"M\",\n" +
//            "  \"celphNo\": \"01099990001\",\n" +
//            "  \"mrktYn\": \"Y\",\n" +
//            "  \"mrktDate\": \"20200824155819\",\n" +
//            "  \"mrktCd\": \"1111\"\n" +
//            "}", MYP_0001.Response.class);
//
//
//    public static MYP_1006.Response MYP_1006 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"oilRfnPontList\": [\n" +
//            "    {\n" +
//            "      \"oilRfnCd\": \"HDOL\",\n" +
//            "      \"oilRfnNm\": \"현대오일\",\n" +
//            "      \"rgstYn\": \"Y\",\n" +
//            "      \"cardNo\": \"1111111111111111\",\n" +
//            "      \"pont\": \"12412301\",\n" +
//            "      \"errMsg\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"oilRfnCd\": \"GSCT\",\n" +
//            "      \"oilRfnNm\": \"GS 칼텍스\",\n" +
//            "      \"rgstYn\": \"Y\",\n" +
//            "      \"cardNo\": \"2222222222222222\",\n" +
//            "      \"pont\": \"15471\",\n" +
//            "      \"errMsg\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"oilRfnCd\": \"SOIL\",\n" +
//            "      \"oilRfnNm\": \"S-OIL\",\n" +
//            "      \"rgstYn\": \"Y\",\n" +
//            "      \"cardNo\": \"3333333333333333\",\n" +
//            "      \"pont\": \"511224487\",\n" +
//            "      \"errMsg\": \"\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",MYP_1006.Response.class);
//
//
//
//
//
//    public static MYP_2006.Response MYP_2006 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"blueMbrYn\": \"Y\",\n" +
//            "  \"extncPlanCnt\": \"10\",\n" +
//            "  \"extncPlanList\": [\n" +
//            "    {\n" +
//            "      \"extncPlanDt\": \"20201001\",\n" +
//            "      \"extncPlanPont\": \"123000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"extncPlanDt\": \"20201001\",\n" +
//            "      \"extncPlanPont\": \"123000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"extncPlanDt\": \"20201002\",\n" +
//            "      \"extncPlanPont\": \"123000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"extncPlanDt\": \"20201003\",\n" +
//            "      \"extncPlanPont\": \"123000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"extncPlanDt\": \"20201004\",\n" +
//            "      \"extncPlanPont\": \"245245\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"extncPlanDt\": \"20201005\",\n" +
//            "      \"extncPlanPont\": \"452452\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"extncPlanDt\": \"20201006\",\n" +
//            "      \"extncPlanPont\": \"254254\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"extncPlanDt\": \"20201007\",\n" +
//            "      \"extncPlanPont\": \"12124\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"extncPlanDt\": \"20201008\",\n" +
//            "      \"extncPlanPont\": \"452542\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"extncPlanDt\": \"20201026\",\n" +
//            "      \"extncPlanPont\": \"1242424\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",MYP_2006.Response.class);
//
//
//    public static SOS_1001.Response SOS_1001 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"tmpAcptNo\": \"22222\"\n" +
//            "}",SOS_1001.Response.class);
//
//
//    public static SOS_1002.Response SOS_1002 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"tmpAcptNo\": \"28843\",\n" +
//            "  \"tmpAcptDtm\": \"20200826181423\",\n" +
//            "  \"carRegNo\": \"16조6840\",\n" +
//            "  \"areaClsCd\": \"R\",\n" +
//            "  \"addr\": \"서울시 금천구 가산동 우림라이온스밸리 A동 1207호 케이스마텍\",\n" +
//            "  \"memo\": \"메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용\"\n" +
//            "}", SOS_1002.Response.class);
//
//
//    public static SOS_1004.Response SOS_1004 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"succYn\": \"Y\",\n" +
//            "  \"failMsg\": \"성공\"\n" +
//            "}", SOS_1004.Response.class);
//
//    public static String SOS_1005 = "{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"celphNo\": \"01086029612\",\n" +
//            "  \"fltCd\": \"020110\",\n" +
//            "  \"acptNo\": \"20191125-0002\",\n" +
//            "  \"acptDtm\": \"201911251029\",\n" +
//            "  \"acptCnclYn\": \"Y\",\n" +
//            "  \"pgrsStusCd\": \"R\",\n" +
//            "  \"carRegNo\": \"16조6840\",\n" +
//            "  \"areaClsCd\": \"R\",\n" +
//            "  \"addr\": \"서울시 금천구 가산동 우림라이온스밸리 A동 1207호 케이스마텍\",\n" +
//            "  \"mdlCd\": \"test\",\n" +
//            "  \"memo\": \"메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용메모내용\",\n" +
//            "  \"tmpAcptNo\": \"28843\",\n" +
//            "  \"tmpAcptDtm\": \"201911250908\"\n" +
//            "}";
//
//
//    public static String SOS_1006 = "{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"tmpAcptNo\": \"28843\",\n" +
//            "  \"tmpAcptDtm\": \"20200827112847\",\n" +
//            "  \"areaClsCd\": \"R\",\n" +
//            "  \"addr\": \"서울시 용산구 원효\",\n" +
//            "  \"carRegNo\": \"23가9876\",\n" +
//            "  \"memo\": \"메모내용\",\n" +
//            "  \"controlTel\": \"01011111111\",\n" +
//            "  \"carNo\": \"10라0142\",\n" +
//            "  \"carTypeNm\": \"아이오닉EV\",\n" +
//            "  \"receiveDtm\": \"20180128105929\",\n" +
//            "  \"gCustX\": \"37.347470\",\n" +
//            "  \"startX\": \"45728206\",\n" +
//            "  \"startY\": \"13502901\",\n" +
//            "  \"finishX\": \"45703607\",\n" +
//            "  \"finishY\": \"13510640\",\n" +
//            "  \"gCustY\": \"126.805108\",\n" +
//            "  \"gXpos\": \"37.510865\",\n" +
//            "  \"gYpos\": \"127.020693\",\n" +
//            "  \"sallocNm\": \"출동(하이카)\"\n" +
//            "}";
//
//    public static String SOS_1006_2 = "{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"tmpAcptNo\": \"28843\",\n" +
//            "  \"tmpAcptDtm\": \"20200827112847\",\n" +
//            "  \"areaClsCd\": \"R\",\n" +
//            "  \"addr\": \"서울시 용산구 원효\",\n" +
//            "  \"carRegNo\": \"23가9876\",\n" +
//            "  \"memo\": \"메모내용\",\n" +
//            "  \"controlTel\": \"01011111111\",\n" +
//            "  \"carNo\": \"10라0142\",\n" +
//            "  \"carTypeNm\": \"아이오닉EV\",\n" +
//            "  \"receiveDtm\": \"20180128105929\",\n" +
//            "  \"gCustX\": \"37.347470\",\n" +
//            "  \"startX\": \"45728206\",\n" +
//            "  \"startY\": \"13502901\",\n" +
//            "  \"finishX\": \"45703607\",\n" +
//            "  \"finishY\": \"13510640\",\n" +
//            "  \"gCustY\": \"126.805108\",\n" +
//            "  \"gXpos\": \"37.334851\",\n" +
//            "  \"gYpos\": \"126.807125\",\n" +
//            "  \"sallocNm\": \"출동(하이카)\"\n" +
//            "}";
//
//
//    public static REQ_1001.Response REQ_1001 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"stusCd\": \"4610\",\n" +
//            "  \"pgrsStusCd\": \"R\",\n" +
//            "  \"avlRsrYn\": \"Y\"\n" +
//            "}",REQ_1001.Response.class);
//
//
//    public static REQ_1002.Response REQ_1002 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"asnList\": [\n" +
//            "    {\n" +
//            "      \"asnCd\": \"Z14Z48\",\n" +
//            "      \"asnNm\": \"(주)영남중공업\",\n" +
//            "      \"acps1Cd\": \"C\",\n" +
//            "      \"firmScnCd\": \"Y\",\n" +
//            "      \"repTn\": \"028559966\",\n" +
//            "      \"pbzAdr\": \"서울특별시 금천구 시흥대로153길 26 (독산동)\",\n" +
//            "      \"mapXcooNm\": \"37.477919\",\n" +
//            "      \"mapYcooNm\": \"126.881762\",\n" +
//            "      \"dist\": \"1.6\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"asnCd\": \"Z14Z47\",\n" +
//            "      \"asnNm\": \"(주)현대중공업\",\n" +
//            "      \"acps1Cd\": \"C\",\n" +
//            "      \"firmScnCd\": \"Y\",\n" +
//            "      \"repTn\": \"0200000000\",\n" +
//            "      \"pbzAdr\": \"인천광역시 금천구 시흥대로153길 26 (독산동)\",\n" +
//            "      \"mapXcooNm\": \"37.481580\",\n" +
//            "      \"mapYcooNm\": \"126.881537\",\n" +
//            "      \"dist\": \"4.6\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",REQ_1002.Response.class);
//
//
//    public static REQ_1003.Response REQ_1003 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"prctYn\": \"Y\",\n" +
//            "  \"rparTypList\": [\n" +
//            "    {\n" +
//            "      \"rparTypCd\": \"AC\",\n" +
//            "      \"rparTypNm\": \"냉난방부품\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rparTypCd\": \"EG\",\n" +
//            "      \"rparTypNm\": \"엔진주요부품\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rparTypCd\": \"PT\",\n" +
//            "      \"rparTypNm\": \"동력전달주요부품\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rparTypCd\": \"BA\",\n" +
//            "      \"rparTypNm\": \"일반부품\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rparTypCd\": \"CS\",\n" +
//            "      \"rparTypNm\": \"소모성부품\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rparTypCd\": \"GT\",\n" +
//            "      \"rparTypNm\": \"기타\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",REQ_1003.Response.class);
//
//    public static REQ_1004.Response REQ_1004 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"rparTypList\": [\n" +
//            "    {\n" +
//            "      \"rparTypCd\": \"AC\",\n" +
//            "      \"rparTypNm\": \"냉난방부품\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rparTypCd\": \"EG\",\n" +
//            "      \"rparTypNm\": \"엔진주요부품\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rparTypCd\": \"PT\",\n" +
//            "      \"rparTypNm\": \"동력전달주요부품\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rparTypCd\": \"BA\",\n" +
//            "      \"rparTypNm\": \"일반부품\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rparTypCd\": \"CS\",\n" +
//            "      \"rparTypNm\": \"소모성부품\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rparTypCd\": \"GT\",\n" +
//            "      \"rparTypNm\": \"기타\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",REQ_1004.Response.class);
//
//
//
//    public static REQ_1005.Response REQ_1005 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"carCareCpnList\": [\n" +
//            "    {\n" +
//            "      \"itemDivCd\": \"11\",\n" +
//            "      \"itemNm\": \"HI 엔진오일\",\n" +
//            "      \"remCnt\": \"6\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"itemDivCd\": \"13\",\n" +
//            "      \"itemNm\": \"HI 에어컨필터\",\n" +
//            "      \"remCnt\": \"3\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"itemDivCd\": \"32\",\n" +
//            "      \"itemNm\": \"HI 브레이크패드\",\n" +
//            "      \"remCnt\": \"1\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"itemDivCd\": \"33\",\n" +
//            "      \"itemNm\": \"HI 와이퍼블레이드\",\n" +
//            "      \"remCnt\": \"2\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"itemDivCd\": \"34\",\n" +
//            "      \"itemNm\": \"HI 브레이크오일\",\n" +
//            "      \"remCnt\": \"3\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"itemDivCd\": \"61\",\n" +
//            "      \"itemNm\": \"HI 홈투홈\",\n" +
//            "      \"remCnt\": \"6\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"itemDivCd\": \"99\",\n" +
//            "      \"itemNm\": \"프리미엄 소낙스 세차 이용권\",\n" +
//            "      \"remCnt\": \"1\"\n" +
//            "    }\n" +
//            "  ],\n" +
//            "  \"autoRsvtPsblYn\": \"Y\",\n" +
//            "  \"arptRsvtPsblYn\": \"Y\",\n" +
//            "  \"hthRsvtPsblYn\": \"Y\",\n" +
//            "  \"rpshRsvtPsblYn\": \"Y\",\n" +
//            "  \"avlRsrYn\": \"Y\"\n" +
//            "}", REQ_1005.Response.class);
//
//
//
//    public static REQ_1007.Response REQ_1007 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"rsvtNo\": \"1234567890123\"\n" +
//            "}",REQ_1007.Response.class);
//
//    public static REQ_1008.Response REQ_1008 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"rsvtDtList\": [\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201225\",\n" +
//            "      \"rsvtTm\": \"1000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201116\",\n" +
//            "      \"rsvtTm\": \"1400\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201117\",\n" +
//            "      \"rsvtTm\": \"1000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201117\",\n" +
//            "      \"rsvtTm\": \"1400\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201118\",\n" +
//            "      \"rsvtTm\": \"1000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201118\",\n" +
//            "      \"rsvtTm\": \"1400\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201119\",\n" +
//            "      \"rsvtTm\": \"1000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201119\",\n" +
//            "      \"rsvtTm\": \"1400\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201120\",\n" +
//            "      \"rsvtTm\": \"1000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20210101\",\n" +
//            "      \"rsvtTm\": \"1400\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201226\",\n" +
//            "      \"rsvtTm\": \"1000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201122\",\n" +
//            "      \"rsvtTm\": \"1400\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",REQ_1008.Response.class);
//
//
//    public static REQ_1009.Response REQ_1009 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"rsvtNo\": \"1234567890123\"\n" +
//            "}",REQ_1009.Response.class);
//
//
//    public static REQ_1012.Response REQ_1012 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"rsvtNo\": \"1234567890123\"\n" +
//            "}",REQ_1012.Response.class);
//
//
//    public static REQ_1010.Response REQ_1010 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"rsvtDtList\": [\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201116\",\n" +
//            "      \"rsvtTm\": \"1000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201116\",\n" +
//            "      \"rsvtTm\": \"1400\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201117\",\n" +
//            "      \"rsvtTm\": \"1000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201117\",\n" +
//            "      \"rsvtTm\": \"1400\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201118\",\n" +
//            "      \"rsvtTm\": \"1000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201118\",\n" +
//            "      \"rsvtTm\": \"1400\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201119\",\n" +
//            "      \"rsvtTm\": \"1000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201119\",\n" +
//            "      \"rsvtTm\": \"1400\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201230\",\n" +
//            "      \"rsvtTm\": \"1000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201229\",\n" +
//            "      \"rsvtTm\": \"1400\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201228\",\n" +
//            "      \"rsvtTm\": \"1000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rsvtDt\": \"20201227\",\n" +
//            "      \"rsvtTm\": \"1400\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",REQ_1010.Response.class);
//
//
//
//    public static REQ_1011.Response REQ_1011 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"성공\",\n" +
//            "  \"rpshGrpList\": [\n" +
//            "    {\n" +
//            "      \"rpshGrpCd\": \"codea\",\n" +
//            "      \"rpshGrpNm\": \"정비반A\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rpshGrpCd\": \"codeb\",\n" +
//            "      \"rpshGrpNm\": \"정비반B\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rpshGrpCd\": \"codec\",\n" +
//            "      \"rpshGrpNm\": \"정비반C\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rpshGrpCd\": \"coded\",\n" +
//            "      \"rpshGrpNm\": \"정비반D\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rpshGrpCd\": \"codee\",\n" +
//            "      \"rpshGrpNm\": \"정비반E\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",REQ_1011.Response.class);
//
//
//
//
//    public static REQ_1013.Response REQ_1013 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"stusCd\": \"6800\",\n" +
//            "  \"rparStatus\": {\n" +
//            "    \"asnCd\": \"B08000\",\n" +
//            "    \"asnNm\": \"고양서비스센터\",\n" +
//            "    \"asnAddr\": \"경기도 고양시 일산서구 킨텍스로 217-6(대화동)\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "    \"asnTelNo\": \"18996611\"\n" +
//            "  },\n" +
//            "  \"rsvtStatList\": [\n" +
//            "    {\n" +
//            "      \"rparRsvtSeqNo\": \"20091700000063\",\n" +
//            "      \"mbrNm\": \"댕댕이\",\n" +
//            "      \"hpNo\": \"01099990001\",\n" +
//            "      \"carRgstNo\": \"12가7777\",\n" +
//            "      \"rsvtTypCd\": \"AUTO\",\n" +
//            "      \"rsvtStusCd\": \"3200\",\n" +
//            "      \"rsvtNo\": \"1234567890123\",\n" +
//            "      \"rsvtHopeDt\": \"20200916\",\n" +
//            "      \"pckpAddr\": \"서울특별시 금천구 가산디지털1로 168, A동 1207호 &#40;가산동&#41; 우림라이온스밸리\",\n" +
//            "      \"dlvryAddr\": \"\",\n" +
//            "      \"asnCd\": \"11111\",\n" +
//            "      \"asnNm\": \"\",\n" +
//            "      \"pbzAdr\": \"\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "      \"repTn\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rparRsvtSeqNo\": \"20091800000065\",\n" +
//            "      \"mbrNm\": \"댕댕이\",\n" +
//            "      \"hpNo\": \"01099990001\",\n" +
//            "      \"carRgstNo\": \"12가7777\",\n" +
//            "      \"rsvtTypCd\": \"HTOH\",\n" +
//            "      \"rsvtStusCd\": \"6800\",\n" +
//            "      \"rsvtNo\": \"1234567890123\",\n" +
//            "      \"rsvtHopeDt\": \"20200916\",\n" +
//            "      \"rsvtHopeTm\": \"\",\n" +
//            "      \"pckpAddr\": \"서울특별시 금천구 가산디지털1로 168, A동 1207호 &#40;가산동&#41; 우림라이온스밸리\",\n" +
//            "      \"dlvryAddr\": \"\",\n" +
//            "      \"asnCd\": \"11111\",\n" +
//            "      \"asnNm\": \"\",\n" +
//            "      \"pbzAdr\": \"\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "      \"pckpExtapChkYn\": \"Y\",\n" +
//            "      \"pckpExtapChkUri\": \"https://www.google.com\",\n" +
//            "      \"dlvryExtapChkYn\": \"N\",\n" +
//            "      \"dlvryExtapChkUri\": \"https://www.naver.com\",\n" +
//            "      \"repTn\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rparRsvtSeqNo\": \"20091800000078\",\n" +
//            "      \"mbrNm\": \"댕댕이\",\n" +
//            "      \"hpNo\": \"01099990001\",\n" +
//            "      \"carRgstNo\": \"12가7887\",\n" +
//            "      \"rsvtTypCd\": \"HTOH\",\n" +
//            "      \"rsvtStusCd\": \"6800\",\n" +
//            "      \"rsvtNo\": \"12345678908988\",\n" +
//            "      \"rsvtHopeDt\": \"20201223\",\n" +
//            "      \"rsvtHopeTm\": \"\",\n" +
//            "      \"pckpAddr\": \"서울특별시 금천구 가산디지털1로 168, A동 1207호 &#40;가산동&#41; 우림라이온스밸리\",\n" +
//            "      \"dlvryAddr\": \"\",\n" +
//            "      \"asnCd\": \"232323\",\n" +
//            "      \"asnNm\": \"\",\n" +
//            "      \"pbzAdr\": \"\",\n" +
//            "      \"wrhsNo\": \"232323\",\n" +
//            "      \"vhclInoutNo\": \"232323\",\n" +
//            "      \"repTn\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rparRsvtSeqNo\": \"20091800000066\",\n" +
//            "      \"mbrNm\": \"댕댕이\",\n" +
//            "      \"hpNo\": \"01099990001\",\n" +
//            "      \"carRgstNo\": \"12가7777\",\n" +
//            "      \"rsvtTypCd\": \"HTOH\",\n" +
//            "      \"rsvtStusCd\": \"4610\",\n" +
//            "      \"rsvtNo\": \"1234567890123\",\n" +
//            "      \"rsvtHopeDt\": \"20200916\",\n" +
//            "      \"rsvtHopeTm\": \"\",\n" +
//            "      \"pckpAddr\": \"서울특별시 금천구 가산디지털1로 168, A동 1207호 &#40;가산동&#41; 우림라이온스밸리\",\n" +
//            "      \"dlvryAddr\": \"\",\n" +
//            "      \"asnCd\": \"11111\",\n" +
//            "      \"asnNm\": \"\",\n" +
//            "      \"pbzAdr\": \"\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "      \"pckpExtapChkYn\": \"Y\",\n" +
//            "      \"pckpExtapChkUri\": \"https://www.google.com\",\n" +
//            "      \"dlvryExtapChkYn\": \"N\",\n" +
//            "      \"dlvryExtapChkUri\": \"https://www.naver.com\",\n" +
//            "      \"repTn\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rparRsvtSeqNo\": \"20091800000067\",\n" +
//            "      \"mbrNm\": \"댕댕이\",\n" +
//            "      \"hpNo\": \"01099990001\",\n" +
//            "      \"carRgstNo\": \"12가7777\",\n" +
//            "      \"rsvtTypCd\": \"RPSH\",\n" +
//            "      \"rsvtStusCd\": \"6800\",\n" +
//            "      \"rsvtNo\": \"1234567890123\",\n" +
//            "      \"rsvtHopeDt\": \"20200916\",\n" +
//            "      \"rsvtHopeTm\": \"\",\n" +
//            "      \"pckpAddr\": \"서울특별시 금천구 가산디지털1로 168, A동 1207호 &#40;가산동&#41; 우림라이온스밸리\",\n" +
//            "      \"dlvryAddr\": \"\",\n" +
//            "      \"asnCd\": \"12231\",\n" +
//            "      \"asnNm\": \"현대정비소\",\n" +
//            "      \"pbzAdr\": \"정비소주소2222\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "      \"repTn\": \"020000000\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rparRsvtSeqNo\": \"20091800000068\",\n" +
//            "      \"mbrNm\": \"댕댕이\",\n" +
//            "      \"hpNo\": \"01099990001\",\n" +
//            "      \"carRgstNo\": \"12가7777\",\n" +
//            "      \"rsvtTypCd\": \"HTOH\",\n" +
//            "      \"rsvtStusCd\": \"1300\",\n" +
//            "      \"rsvtNo\": \"1234567890123\",\n" +
//            "      \"rsvtHopeDt\": \"20200916\",\n" +
//            "      \"rsvtHopeTm\": \"\",\n" +
//            "      \"pckpAddr\": \"서울특별시 금천구 가산디지털1로 168, A동 1207호 &#40;가산동&#41; 우림라이온스밸리\",\n" +
//            "      \"dlvryAddr\": \"서울특별시 금천구 가산디지털1로 테스트\",\n" +
//            "      \"asnCd\": \"11111\",\n" +
//            "      \"asnNm\": \"\",\n" +
//            "      \"pbzAdr\": \"\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "      \"pckpExtapChkYn\": \"Y\",\n" +
//            "      \"pckpExtapChkUri\": \"https://www.google.com\",\n" +
//            "      \"dlvryExtapChkYn\": \"Y\",\n" +
//            "      \"dlvryExtapChkUri\": \"https://www.naver.com\",\n" +
//            "      \"repTn\": \"\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"rparRsvtSeqNo\": \"20092400000069\",\n" +
//            "      \"mbrNm\": \"댕댕이\",\n" +
//            "      \"hpNo\": \"01099990001\",\n" +
//            "      \"carRgstNo\": \"12가7777\",\n" +
//            "      \"rsvtTypCd\": \"RPSH\",\n" +
//            "      \"rsvtStusCd\": \"3100\",\n" +
//            "      \"rsvtNo\": \"1234567890123\",\n" +
//            "      \"rsvtHopeDt\": \"20200916\",\n" +
//            "      \"rsvtHopeTm\": \"\",\n" +
//            "      \"pckpAddr\": \"서울특별시 금천구 가산디지털1로 168, A동 1207호 &#40;가산동&#41; 우림라이온스밸리\",\n" +
//            "      \"dlvryAddr\": \"\",\n" +
//            "      \"asnCd\": \"122312\",\n" +
//            "      \"asnNm\": \"제네시스정비소\",\n" +
//            "      \"pbzAdr\": \"정비소주소3\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "      \"repTn\": \"031000000\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",REQ_1013.Response.class);
//
//
//    public static REQ_1014.Response REQ_1014 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"rsvtStatList\": [\n" +
//            "    {\n" +
//            "      \"wrhsDtm\": \"20190926\",\n" +
//            "      \"asnCd\": \"D01000\",\n" +
//            "      \"asnNm\": \"인천서비스센터2\",\n" +
//            "      \"milg\": \"83367\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "      \"rparWorkNm\": \"진단점검 점검2\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"wrhsDtm\": \"20190926\",\n" +
//            "      \"asnCd\": \"D01000\",\n" +
//            "      \"asnNm\": \"인천서비스센터2\",\n" +
//            "      \"milg\": \"83367\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "      \"rparWorkNm\": \"진단점검 점검2\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"wrhsDtm\": \"20190926\",\n" +
//            "      \"asnCd\": \"D01000\",\n" +
//            "      \"asnNm\": \"인천서비스센터2\",\n" +
//            "      \"milg\": \"83367\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "      \"rparWorkNm\": \"진단점검 점검2\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"wrhsDtm\": \"20190926\",\n" +
//            "      \"asnCd\": \"D01000\",\n" +
//            "      \"asnNm\": \"인천서비스센터2\",\n" +
//            "      \"milg\": \"83367\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "      \"rparWorkNm\": \"진단점검 점검2\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"wrhsDtm\": \"20200926\",\n" +
//            "      \"asnCd\": \"D01000\",\n" +
//            "      \"asnNm\": \"인천서비스센터2\",\n" +
//            "      \"milg\": \"83367\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "      \"rparWorkNm\": \"진단점검 점검2\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"wrhsDtm\": \"20191001\",\n" +
//            "      \"asnCd\": \"D01000\",\n" +
//            "      \"asnNm\": \"인천서비스센터2\",\n" +
//            "      \"milg\": \"83367\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "      \"rparWorkNm\": \"진단점검 점검2\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"wrhsDtm\": \"20200924\",\n" +
//            "      \"asnCd\": \"D01000\",\n" +
//            "      \"asnNm\": \"인천서비스센터2\",\n" +
//            "      \"milg\": \"83367\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "      \"rparWorkNm\": \"진단점검 점검2\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"wrhsDtm\": \"20200925\",\n" +
//            "      \"asnCd\": \"D01000\",\n" +
//            "      \"asnNm\": \"인천서비스센터2\",\n" +
//            "      \"milg\": \"83367\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "      \"rparWorkNm\": \"진단점검 점검2\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"wrhsDtm\": \"20201001\",\n" +
//            "      \"asnCd\": \"D01000\",\n" +
//            "      \"asnNm\": \"인천서비스센터2\",\n" +
//            "      \"milg\": \"83367\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "      \"rparWorkNm\": \"진단점검 점검2\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"wrhsDtm\": \"20190826\",\n" +
//            "      \"asnCd\": \"D01000\",\n" +
//            "      \"asnNm\": \"인천서비스센터1\",\n" +
//            "      \"milg\": \"73367\",\n" +
//            "      \"wrhsNo\": \"111111\",\n" +
//            "      \"vhclInoutNo\": \"111111\",\n" +
//            "      \"rparWorkNm\": \"진단점검 점검1\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",REQ_1014.Response.class);
//
//
//    public static REQ_1015.Response REQ_1015 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\"\n" +
//            "}",REQ_1015.Response.class);
//
//    public static REQ_1018.Response REQ_1018 = new Gson().fromJson("{\n" +
//            "  \"rtCd\": \"0000\",\n" +
//            "  \"rtMsg\": \"Success\",\n" +
//            "  \"imgList\": [\n" +
//            "    {\n" +
//            "      \"imgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/B08000B08000AI2017021302901.jpg\",\n" +
//            "      \"thumImgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/thnB08000B08000AI2017021302901.jpg\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"imgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/B08000B08000AI2017021302902.jpg\",\n" +
//            "      \"thumImgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/thnB08000B08000AI2017021302902.jpg\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"imgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/B08000B08000AI2017021302903.jpg\",\n" +
//            "      \"thumImgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/thnB08000B08000AI2017021302903.jpg\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"imgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/B08000B08000AI2017021302904.jpg\",\n" +
//            "      \"thumImgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/thnB08000B08000AI2017021302904.jpg\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"imgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/B08000B08000AI2017021302905.jpg\",\n" +
//            "      \"thumImgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/thnB08000B08000AI2017021302905.jpg\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"imgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/B08000B08000AI2017021302906.jpg\",\n" +
//            "      \"thumImgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/thnB08000B08000AI2017021302906.jpg\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"imgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/B08000B08000AI2017021302907.jpg\",\n" +
//            "      \"thumImgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/thnB08000B08000AI2017021302907.jpg\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"imgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/B08000B08000AI2017021302908.jpg\",\n" +
//            "      \"thumImgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/thnB08000B08000AI2017021302908.jpg\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"imgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/B08000B08000AI2017021302909.jpg\",\n" +
//            "      \"thumImgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/thnB08000B08000AI2017021302909.jpg\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"imgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/B08000B08000AI20170213029010.jpg\",\n" +
//            "      \"thumImgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/thnB08000B08000AI20170213029010.jpg\"\n" +
//            "    },\n" +
//            "    {\n" +
//            "      \"imgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/B08000B08000AI20170213029011.jpg\",\n" +
//            "      \"thumImgUri\": \"http://stg.graapi.genesis.com/wasapp/as/uploadfile/moca/201702/thnB08000B08000AI20170213029011.jpg\"\n" +
//            "    }\n" +
//            "  ]\n" +
//            "}",REQ_1018.Response.class);
//

    public static CHB_1007.Response CHB_1007 = new Gson().fromJson("{\n" +
            "  \"rtCd\": \"0000\",\n" +
            "  \"rtMsg\": \"Success\",\n" +
            "  \"useYn\": \"Y\"\n" +
            "}", CHB_1007.Response.class);

    public static CHB_1008.Response CHB_1008 = new Gson().fromJson("{\n" +
            "  \"rtCd\": \"0000\",\n" +
            "  \"rtMsg\": \"Success\",\n" +
            "  \"dailyBookingSlotList\": [\n" +
            "    {\n" +
            "      \"bookingDate\": \"20210429\",\n" +
            "      \"slotList\": [\n" +
            "           {\n" +
            "           \"bookingTime\": \"1400\",\n" +
            "           \"remainCount\": 3\n" +
            "           }\n" +
            "       ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"bookingDate\": \"20210430\",\n" +
            "      \"slotList\": [\n" +
            "           {\n" +
            "           \"bookingTime\": \"1100\",\n" +
            "           \"remainCount\": 4\n" +
            "           },\n" +
            "           {\n" +
            "           \"bookingTime\": \"1130\",\n" +
            "           \"remainCount\": 2\n" +
            "           },\n" +
            "           {\n" +
            "           \"bookingTime\": \"1300\",\n" +
            "           \"remainCount\": 1\n" +
            "           }\n" +
            "       ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"bookingDate\": \"20210501\",\n" +
            "      \"slotList\": [\n" +
            "       ]\n" +
            "    }\n" +
            "  ]\n" +
            "}", CHB_1008.Response.class);

    public static CHB_1009.Response CHB_1009 = new Gson().fromJson("{\n" +
            "\"rtCd\" : \"0000\",\n" +
            "\"rtMst\" : \"정상\",\n" +
            "\"txid\" : \"GRA\",\n" +
            "\"expireDtm\" : \"20210414160300\",\n" +
            "\"productCode\" : \"string\",\n" +
            "\"productName\" : \"string\",\n" +
            "\"productPrice\" : 0,\n" +
            "\"optionList\" : [\n" +
            "      {\n" +
            "        \"optionCode\": \"DV\",\n" +
            "        \"optionType\": \"OT01\",\n" +
            "        \"optionName\": \"탁송\",\n" +
            "        \"optionPrice\": 20000,\n" +
            "        \"optionApplyCount\": 1\n" +
            "      },\n" +
            "      {\n" +
            "        \"optionCode\": \"DV\",\n" +
            "        \"optionType\": \"OT02\",\n" +
            "        \"optionName\": \"세차\",\n" +
            "        \"optionPrice\": 5000,\n" +
            "        \"optionApplyCount\": 1\n" +
            "      }\n" +
            "],\n" +
            "\"strafficInfo\" : \n" +
            "   {\n" +
            "      \"cardNo\" : \"\",\n" +
            "      \"cardName\" : \"\",\n" +
            "      \"availableYN\" : \"\",\n" +
            "      \"balance\" : 0\n" +
            "   },\n" +
            "\"blueInfo\" : \n" +
            "   {\n" +
            "      \"blueSignInYN\" : \"\",\n" +
            "      \"blueBalance\" : 0\n" +
            "   },\n" +
            "\"signInYN\" : \"Y\",\n" +
            "\"cardList\" : [\n" +
            "   {\n" +
            "      \"cardType\" : \"C\",\n" +
            "      \"cardId\" : \"cardId1\",\n" +
            "      \"cardCoCode\" : \"C001\",\n" +
            "      \"cardNo\" : \"123454321\",\n" +
            "      \"cardName\" : \"BC카드_1\",\n" +
            "      \"mainCardYN\" : \"N\",\n" +
            "      \"registerDt\" : \"20210103110000\",\n" +
            "      \"cardImageUrl\" : \"\",\n" +
            "      \"lastUsedCardYN\" : \"Y\",\n" +
            "      \"oneCardCode\" : \"N\",\n" +
            "      \"plccYN\" : \"N\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"cardType\" : \"H\",\n" +
            "      \"cardId\" : \"cardId2\",\n" +
            "      \"cardCoCode\" : \"PHON\",\n" +
            "      \"cardNo\" : \"123454321\",\n" +
            "      \"cardName\" : \"휴대폰\",\n" +
            "      \"mainCardYN\" : \"N\",\n" +
            "      \"registerDt\" : \"20210103110000\",\n" +
            "      \"cardImageUrl\" : \"\",\n" +
            "      \"lastUsedCardYN\" : \"Y\",\n" +
            "      \"oneCardCode\" : \"N\",\n" +
            "      \"plccYN\" : \"N\"\n" +
            "   },\n" +
            "   {\n" +
            "      \"cardType\" : \"C\",\n" +
            "      \"cardId\" : \"cardId3\",\n" +
            "      \"cardCoCode\" : \"C004\",\n" +
            "      \"cardNo\" : \"123454321\",\n" +
            "      \"cardName\" : \"삼성카드_1\",\n" +
            "      \"mainCardYN\" : \"Y\",\n" +
            "      \"registerDt\" : \"20210103110000\",\n" +
            "      \"cardImageUrl\" : \"\",\n" +
            "      \"lastUsedCardYN\" : \"Y\",\n" +
            "      \"oneCardCode\" : \"N\",\n" +
            "      \"plccYN\" : \"N\"\n" +
            "   }\n" +
            "]\n" +
            "}", CHB_1009.Response.class);

    public static CHB_1010.Response CHB_1010 = new Gson().fromJson("{\n" +
            "  \"rtCd\": \"0000\",\n" +
            "  \"rtMsg\": \"Success\",\n" +
            "  \"paymentFormData\" : \n" +
            "   {\n" +
            "       \"chnlCd\": \"CLPF\",\n" +
            "       \"svrEncKey\": \"enckey\",\n" +
            "       \"chnlMbrIdfKey\": \"string\",\n" +
            "       \"bpayCardId\": \"string\",\n" +
            "       \"srcCoCd\": \"C001\",\n" +
            "       \"chnlMid\": \"string\",\n" +
            "       \"mOid\": \"string\",\n" +
            "       \"prdtNm\": \"버틀러서비스\",\n" +
            "       \"stlmAmt\": 0,\n" +
            "       \"vlsp\": 0,\n" +
            "       \"srtx\": 0,\n" +
            "       \"srfe\": 0,\n" +
            "       \"nonMptx\": 0,\n" +
            "       \"isMth\": 3,\n" +
            "       \"closeUrl\": \"string\",\n" +
            "       \"redirectUrl\": \"https://myhyundai.com/card/list\",\n" +
            "       \"userAgent\": \"Android 6.0/Mozillar\",\n" +
            "       \"dvceCd\": \"string\",\n" +
            "       \"deceUuid\": \"string\",\n" +
            "       \"ediDate\": \"20210318141600\",\n" +
            "       \"filler\": \"string\",\n" +
            "       \"hashVal\": \"string\",\n" +
            "       \"formUrl\": \"https://devhpay.bluewalnut.co.kr/mem/registMemberCi.do/\"\n" +
            "   }\n" +
            "}", CHB_1010.Response.class);

    public static CHB_1011.Response CHB_1011 = new Gson().fromJson("{\n" +
            "  \"rtCd\": \"0000\",\n" +
            "  \"rtMsg\": \"Success\",\n" +
            "  \"chnlCd\": \"CLPF\",\n" +
            "  \"svrEncKey\": \"enckey\",\n" +
            "  \"chnlMbrIdfKey\": \"string\",\n" +
            "  \"mbrCi\": \"userciciciciciicicici\",\n" +
            "  \"mbrNm\": \"김현대\",\n" +
            "  \"mbPhNo\": \"01022223333\",\n" +
            "  \"mvmtCtCoCd\": \"SKT\",\n" +
            "  \"rsdtNo\": \"enc_rgst_no\",\n" +
            "  \"closeUrl\": \"string\",\n" +
            "  \"redirectUrl\": \"https://myhyundai.com/card/list\",\n" +
            "  \"userAgent\": \"Android 6.0/Mozillar\",\n" +
            "  \"dvceCd\": \"string\",\n" +
            "  \"deceUuid\": \"string\",\n" +
            "  \"ediDate\": \"20210318141600\",\n" +
            "  \"filler\": \"string\",\n" +
            "  \"hashVal\": \"string\",\n" +
            "  \"formUrl\": \"https://devhpay.bluewalnut.co.kr/mem/registMemberCi.do/\"\n" +
            "}", CHB_1011.Response.class);

    public static CHB_1013.Response CHB_1013 = new Gson().fromJson("{\n" +
            "  \"rtCd\": \"0000\",\n" +
            "  \"rtMsg\": \"Success\",\n" +
            "  \"chnlCd\": \"CLPF\",\n" +
            "  \"svrEncKey\": \"enckey\",\n" +
            "  \"chnlMbrIdfKey\": \"string\",\n" +
            "  \"closeUrl\": \"string\",\n" +
            "  \"redirectUrl\": \"https://myhyundai.com/card/list\",\n" +
            "  \"ediDate\": \"20210318141600\",\n" +
            "  \"filler\": \"string\",\n" +
            "  \"hashVal\": \"string\",\n" +
            "  \"formUrl\": \"https://devhpay.bluewalnut.co.kr/myp/selectCardCompany.do\"\n" +
            "}", CHB_1013.Response.class);

    public static CHB_1015.Response CHB_1015 = new Gson().fromJson("{\"rtCd\":\"0000\",\"rtMsg\":\"\",\"signInYN\":\"Y\",\"cardCount\":4,\"cardList\":[{\"cardType\":\"C\",\"cardId\":\"testid00\",\"cardCoCode\":\"C001\",\"cardNo\":\"0000 0000 0000 0000\",\"cardName\":\"BC카드\",\"mainCardYN\":\"N\",\"registerDt\":\"\",\"cardImageUrl\":\"\",\"lastUsedCardYN\":\"Y\",\"oneCardCode\":\"Y\",\"plccYN\":\"Y\"},{\"cardType\":\"C\",\"cardId\":\"testid01\",\"cardCoCode\":\"C002\",\"cardNo\":\"0000 0000 0000 0000\",\"cardName\":\"KB카드\",\"mainCardYN\":\"N\",\"registerDt\":\"\",\"cardImageUrl\":\"\",\"lastUsedCardYN\":\"Y\",\"oneCardCode\":\"Y\",\"plccYN\":\"Y\"},{\"cardType\":\"C\",\"cardId\":\"testid02\",\"cardCoCode\":\"C003\",\"cardNo\":\"0000 0000 0000 0000\",\"cardName\":\"하나카드\",\"mainCardYN\":\"N\",\"registerDt\":\"\",\"cardImageUrl\":\"\",\"lastUsedCardYN\":\"Y\",\"oneCardCode\":\"Y\",\"plccYN\":\"Y\"},{\"cardType\":\"C\",\"cardId\":\"testid03\",\"cardCoCode\":\"C004\",\"cardNo\":\"0000 0000 0000 0000\",\"cardName\":\"삼성카드\",\"mainCardYN\":\"Y\",\"registerDt\":\"\",\"cardImageUrl\":\"\",\"lastUsedCardYN\":\"Y\",\"oneCardCode\":\"Y\",\"plccYN\":\"Y\"},{\"cardType\":\"C\",\"cardId\":\"testid00\",\"cardCoCode\":\"C001\",\"cardNo\":\"0000 0000 0000 0000\",\"cardName\":\"BC카드\",\"mainCardYN\":\"N\",\"registerDt\":\"\",\"cardImageUrl\":\"\",\"lastUsedCardYN\":\"Y\",\"oneCardCode\":\"Y\",\"plccYN\":\"Y\"},{\"cardType\":\"C\",\"cardId\":\"testid00\",\"cardCoCode\":\"C001\",\"cardNo\":\"0000 0000 0000 0000\",\"cardName\":\"BC카드\",\"mainCardYN\":\"N\",\"registerDt\":\"\",\"cardImageUrl\":\"\",\"lastUsedCardYN\":\"Y\",\"oneCardCode\":\"Y\",\"plccYN\":\"Y\"},{\"cardType\":\"C\",\"cardId\":\"testid00\",\"cardCoCode\":\"C001\",\"cardNo\":\"0000 0000 0000 0000\",\"cardName\":\"BC카드\",\"mainCardYN\":\"N\",\"registerDt\":\"\",\"cardImageUrl\":\"\",\"lastUsedCardYN\":\"Y\",\"oneCardCode\":\"Y\",\"plccYN\":\"Y\"},{\"cardType\":\"C\",\"cardId\":\"testid00\",\"cardCoCode\":\"C001\",\"cardNo\":\"0000 0000 0000 0000\",\"cardName\":\"BC카드\",\"mainCardYN\":\"N\",\"registerDt\":\"\",\"cardImageUrl\":\"\",\"lastUsedCardYN\":\"Y\",\"oneCardCode\":\"Y\",\"plccYN\":\"Y\"},{\"cardType\":\"C\",\"cardId\":\"testid00\",\"cardCoCode\":\"C001\",\"cardNo\":\"0000 0000 0000 0000\",\"cardName\":\"BC카드\",\"mainCardYN\":\"N\",\"registerDt\":\"\",\"cardImageUrl\":\"\",\"lastUsedCardYN\":\"Y\",\"oneCardCode\":\"Y\",\"plccYN\":\"Y\"},{\"cardType\":\"C\",\"cardId\":\"testid00\",\"cardCoCode\":\"C001\",\"cardNo\":\"0000 0000 0000 0000\",\"cardName\":\"BC카드\",\"mainCardYN\":\"N\",\"registerDt\":\"\",\"cardImageUrl\":\"\",\"lastUsedCardYN\":\"Y\",\"oneCardCode\":\"Y\",\"plccYN\":\"Y\"}]}", CHB_1015.Response.class);

    public static CHB_1016.Response CHB_1016 = new Gson().fromJson("{\"rtCd\":\"0000\",\"rtMsg\":\"test message\"}", CHB_1016.Response.class);
    public static CHB_1017.Response CHB_1017 = new Gson().fromJson("{\"rtCd\":\"0000\",\"rtMsg\":\"test message\"}", CHB_1017.Response.class);

    public static CHB_1021.Response CHB_1021 = new Gson().fromJson("{\n" +
            "  \"rtCd\": \"0000\",\n" +
            "  \"rtMsg\": \"Success\",\n" +
            "  \"orderId\": \"string\",\n" +
            "  \"status\": \"2000\",\n" +
            "  \"statusNm\": \"픽업 중\",\n" +
            "  \"requestDt\": \"20210407042507\",\n" +
            "  \"bookingDt\": \"20210415151600\",\n" +
            "  \"cancelableYN\": \"Y\",\n" +
            "  \"keyTransferType\": \"KT_DKC\",\n" +
            "  \"workerAssignYn\": \"string\",\n" +
            "  \"carInfo\": {\n" +
            "    \"vin\": \"string\",\n" +
            "    \"carNo\": \"27저 1234\",\n" +
            "    \"carCode\": \"string\"\n" +
            "  },\n" +
            "  \"sameLocationYN\": \"string\",\n" +
            "  \"locationCount\": 1,\n" +
            "  \"locationList\": [\n" +
            "    {\n" +
            "      \"locationType\": \"STRT\",\n" +
            "      \"inOutType\": \"I\",\n" +
            "      \"latitude\": 0,\n" +
            "      \"longitude\": 0,\n" +
            "      \"address\": \"서울특별시 금천구 가산디지털1로\",\n" +
            "      \"addressDetail\": \"string\",\n" +
            "      \"buildingName\": \"string\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"orderInfo\": {\n" +
            "    \"estimatedPaymentAmount\": 0,\n" +
            "    \"paymentAmount\": 16000,\n" +
            "    \"productCode\": \"string\",\n" +
            "    \"productName\": \"string\",\n" +
            "    \"productPrice\": 16000,\n" +
            "    \"productSalePrice\": 0,\n" +
            "    \"optionCount\": 1,\n" +
            "    \"optionList\": [\n" +
            "      {\n" +
            "        \"optionCode\": \"string\",\n" +
            "        \"optionType\": \"string\",\n" +
            "        \"optionName\": \"탁송\",\n" +
            "        \"optionPrice\": 20000,\n" +
            "        \"optionApplyCount\": 0\n" +
            "      }\n" +
            "    ],\n" +
            "    \"membershipCount\": 0,\n" +
            "    \"membershipList\": [\n" +
            "      {\n" +
            "        \"membershipCode\": \"STRFF\",\n" +
            "        \"membershipNo\": \"string\",\n" +
            "        \"membershipUsePoint\": 0\n" +
            "      }\n" +
            "    ],\n" +
            "    \"totalServiceCost\": 0,\n" +
            "    \"totalDcAmount\": 0,\n" +
            "    \"totalUsePoint\": 0\n" +
            "  },\n" +
            "  \"paymentInfo\": {\n" +
            "    \"cardNo\": \"string\",\n" +
            "    \"cardCoCode\": \"C001\"\n" +
            "  },\n" +
            "  \"workerCount\": 0,\n" +
            "  \"workerList\": [\n" +
            "    {\n" +
            "      \"workerName\": \"string\",\n" +
            "      \"workerHpNo\": \"string\",\n" +
            "      \"workerType\": \"PKUP\",\n" +
            "      \"serviceViewLink\": \"string\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"vendorInfo\": {\n" +
            "    \"vendorCode\": \"string\",\n" +
            "    \"vendorName\": \"로드윈 주식회사 0000-0000\",\n" +
            "    \"vendorTelNo\": \"string\",\n" +
            "    \"vendorAddress\": \"string\",\n" +
            "    \"vendorHomePageUrl\": \"string\",\n" +
            "    \"vendorCSTelNo\": \"01012341234\",\n" +
            "    \"vendorCSHours\": \"평일 09:00-19:00 (공휴일 휴무)\"\n" +
            "  }\n" +
            "}", CHB_1021.Response.class);

    public static CHB_1022.Response CHB_1022 = new Gson().fromJson("{\n" +
            "    \"rtCd\": \"0000\",\n" +
            "    \"rtMsg\": \"Success\",\n" +
            "    \"workerName\": \"김제니\",\n" +
            "    \"workerHpNo\": \"01012341234\",\n" +
            "    \"latitude\": 37.4813022,\n" +
            "    \"longitude\": 126.8821687,\n" +
            "    \"address\": \"서울특별시 금천구 가산디지털1로\"\n" +
            "}", CHB_1022.Response.class);

    public static CHB_1023.Response CHB_1023 = new Gson().fromJson("{\n" +
            "  \"rtCd\": \"0000\",\n" +
            "  \"rtMsg\": \"Success\",\n" +
            "  \"totalCount\": 0,\n" +
            "  \"totalPage\": 3,\n" +
            "  \"page\": 0,\n" +
            "  \"pageSize\": 0,\n" +
            "  \"currentSize\": 0,\n" +
            "  \"firstYN\": \"Y\",\n" +
            "  \"lastYN\": \"N\",\n" +
            "  \"bookingList\": [\n" +
            "    {\n" +
            "      \"orderId\": \"KST123\",\n" +
            "      \"category\": \"string\",\n" +
            "      \"status\": \"1000\",\n" +
            "      \"statusNm\": \"예약완료\",\n" +
            "      \"bookingDt\": \"20210416113000\",\n" +
            "      \"finishDt\": \"20210416124000\",\n" +
            "      \"carNo\": \"123가1201\",\n" +
            "      \"address\": \"서울 금천구 가산동 우림라이온스밸리 B동\",\n" +
            "      \"addressDetail\": \"105호\",\n" +
            "      \"buildingName\": \"string\",\n" +
            "      \"productName\": \"string\",\n" +
            "      \"optionCount\": 1,\n" +
            "      \"optionNameList\": [\n" +
            "        \"탁송\"\n" +
            "      ],\n" +
            "      \"serviceViewLink\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"orderId\": \"string\",\n" +
            "      \"category\": \"string\",\n" +
            "      \"status\": \"1000\",\n" +
            "      \"statusNm\": \"예약완료\",\n" +
            "      \"bookingDt\": \"20210416113000\",\n" +
            "      \"finishDt\": \"20210416124000\",\n" +
            "      \"carNo\": \"123가1202\",\n" +
            "      \"address\": \"서울 금천구 가산동 우림라이온스밸리 B동\",\n" +
            "      \"addressDetail\": \"105호\",\n" +
            "      \"buildingName\": \"string\",\n" +
            "      \"productName\": \"string\",\n" +
            "      \"optionCount\": 1,\n" +
            "      \"optionNameList\": [\n" +
            "        \"탁송\"\n" +
            "      ],\n" +
            "      \"serviceViewLink\": \"https://www.google.com/\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"orderId\": \"string\",\n" +
            "      \"category\": \"string\",\n" +
            "      \"status\": \"1000\",\n" +
            "      \"statusNm\": \"예약완료\",\n" +
            "      \"bookingDt\": \"20210416113000\",\n" +
            "      \"finishDt\": \"20210416124000\",\n" +
            "      \"carNo\": \"123가1203\",\n" +
            "      \"address\": \"서울 금천구 가산동 우림라이온스밸리 B동\",\n" +
            "      \"addressDetail\": \"105호\",\n" +
            "      \"buildingName\": \"string\",\n" +
            "      \"productName\": \"string\",\n" +
            "      \"optionCount\": 1,\n" +
            "      \"optionNameList\": [\n" +
            "        \"탁송\"\n" +
            "      ],\n" +
            "      \"serviceViewLink\": \"https://www.google.com/\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"orderId\": \"string\",\n" +
            "      \"category\": \"string\",\n" +
            "      \"status\": \"1000\",\n" +
            "      \"statusNm\": \"예약완료\",\n" +
            "      \"bookingDt\": \"20210416113000\",\n" +
            "      \"finishDt\": \"20210416124000\",\n" +
            "      \"carNo\": \"123가1204\",\n" +
            "      \"address\": \"서울 금천구 가산동 우림라이온스밸리 B동\",\n" +
            "      \"addressDetail\": \"105호\",\n" +
            "      \"buildingName\": \"string\",\n" +
            "      \"productName\": \"string\",\n" +
            "      \"optionCount\": 1,\n" +
            "      \"optionNameList\": [\n" +
            "        \"탁송\"\n" +
            "      ],\n" +
            "      \"serviceViewLink\": \"\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"orderId\": \"string\",\n" +
            "      \"category\": \"string\",\n" +
            "      \"status\": \"6000\",\n" +
            "      \"statusNm\": \"예약취소\",\n" +
            "      \"bookingDt\": \"20210416113000\",\n" +
            "      \"finishDt\": \"20210416124000\",\n" +
            "      \"carNo\": \"123가1205\",\n" +
            "      \"address\": \"서울 금천구 가산동 우림라이온스밸리 B동\",\n" +
            "      \"addressDetail\": \"105호\",\n" +
            "      \"buildingName\": \"string\",\n" +
            "      \"productName\": \"string\",\n" +
            "      \"optionCount\": 1,\n" +
            "      \"optionNameList\": [\n" +
            "        \"탁송\"\n" +
            "      ],\n" +
            "      \"serviceViewLink\": \"\"\n" +
            "    }\n" +
            "  ]\n" +
            "}", CHB_1023.Response.class);

    public static CHB_1024.Response CHB_1024 = new Gson().fromJson("{\n" +
            "  \"rtCd\": \"0000\",\n" +
            "  \"rtMsg\": \"Success\"\n" +
            "}", CHB_1024.Response.class);

    public static CHB_1025.Response CHB_1025 = new Gson().fromJson("{\n" +
            "  \"rtCd\": \"0000\",\n" +
            "  \"rtMsg\": \"Success\"\n" +
            "}", CHB_1025.Response.class);

    public static CHB_1026.Response CHB_1026 = new Gson().fromJson("{\n" +
            "  \"rtCd\": \"0000\",\n" +
            "  \"rtMsg\": \"Success\",\n" +
            "  \"orderId\": \"string\",\n" +
            "  \"status\": \"1000\",\n" +
            "  \"statusNm\": \"예약완료\",\n" +
            "  \"requestDt\": \"20210407162507\",\n" +
            "  \"finishDt\": \"20210407180007\",\n" +
            "  \"carInfo\": {\n" +
            "    \"vin\": \"string\",\n" +
            "    \"carNo\": \"27저 1234\"\n" +
            "  },\n" +
            "  \"locationCount\": 0,\n" +
            "  \"locationList\": [\n" +
            "    {\n" +
            "      \"locationType\": \"STRT\",\n" +
            "      \"inOutType\": \"I\",\n" +
            "      \"latitude\": 0,\n" +
            "      \"longitude\": 0,\n" +
            "      \"address\": \"서울특별시 금천구 가산디지털1로\",\n" +
            "      \"addressDetail\": \"string\",\n" +
            "      \"buildingName\": \"string\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"orderInfo\": {\n" +
            "    \"estimatedPaymentAmount\": 25000,\n" +
            "    \"paymentAmount\": 25000,\n" +
            "    \"productCode\": \"string\",\n" +
            "    \"productName\": \"string\",\n" +
            "    \"productPrice\": 0,\n" +
            "    \"productSalePrice\": 16000,\n" +
            "    \"optionCount\": 1,\n" +
            "    \"optionList\": [\n" +
            "      {\n" +
            "        \"optionCode\": \"DV\",\n" +
            "        \"optionType\": \"OT01\",\n" +
            "        \"optionName\": \"탁송\",\n" +
            "        \"optionPrice\": 20000,\n" +
            "        \"optionApplyCount\": 1\n" +
            "      }\n" +
            "    ],\n" +
            "    \"membershipCount\": 1,\n" +
            "    \"membershipList\": [\n" +
            "      {\n" +
            "        \"membershipCode\": \"STRFF\",\n" +
            "        \"membershipNo\": \"string\",\n" +
            "        \"membershipUsePoint\": 0\n" +
            "      }\n" +
            "    ],\n" +
            "    \"totalServiceCost\": 16000,\n" +
            "    \"totalDcAmount\": 0,\n" +
            "    \"totalUsePoint\": 0\n" +
            "  },\n" +
            "  \"workerCount\": 1,\n" +
            "  \"workerList\": [\n" +
            "    {\n" +
            "      \"workerName\": \"김제니\",\n" +
            "      \"workerHpNo\": \"01012344321\",\n" +
            "      \"workerType\": \"PKUP\",\n" +
            "      \"serviceViewLink\": \"https://www.google.com/\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"vendorInfo\": {\n" +
            "    \"vendorCode\": \"string\",\n" +
            "    \"vendorName\": \"string\",\n" +
            "    \"vendorTelNo\": \"string\",\n" +
            "    \"vendorAddress\": \"string\",\n" +
            "    \"vendorHomePageUrl\": \"string\",\n" +
            "    \"vendorCSTelNo\": \"string\"\n" +
            "  }\n" +
            "}", CHB_1026.Response.class);

    public static EvStatus.Response EvStatus = new Gson().fromJson("{\n" +
            "  \"batteryPlugin\": 0,\n" +
            "  \"batteryCharge\": true,\n" +
            "  \"soc\": 50,\n" +
            "  \"dte\": {\n" +
            "    \"type\": 0,\n" +
            "    \"distance\": {\n" +
            "      \"value\": 20000,\n" +
            "      \"unit\": 1\n" +
            "    }\n" +
            "  },\n" +
            "  \"targetSOC\": {\n" +
            "    \"plugType\": 0,\n" +
            "    \"targetSOClevel\": 50\n" +
            "  },\n" +
            "  \"remainTime\": {\n" +
            "    \"value\": 3800,\n" +
            "    \"unit\": 3\n" +
            "  },\n" +
            "  \"chargePortDoorOpenStatus\": 1,\n" +
            "  \"engine\": true,\n" +
            "  \"ign3\": true,\n" +
            "  \"airCtrl\": true,\n" +
            "  \"acc\": true,\n" +
            "  \"timestamp\": \"111111\",\n" +
            "  \"msgId\": \"success\"\n" +
            "}", EvStatus.Response.class);

//    public static EPT_1001.Response EPT_1001 = new Gson().fromJson("{\"rtCd\":\"0000\",\"rtMsg\":\"test message\",\"chgList\":[{\"spid\":\"11\",\"csid\":\"22222222\",\"espid\":\"333\",\"ecsid\":\"1234567890123456789012345678901234560\",\"csnm\":\"현대EV스테이션 현대EV스테이션 현대EV스테이션 강남\",\"dist\":\"1.1\",\"lat\":\"37.480511\",\"lot\":\"126.883668\",\"daddr\":\"서울특별시 금천구 가산동 602-13\",\"addrDtl\":\"우림라이온스밸리 12층\",\"spnm\":\"운영사업자명\",\"useYn\":\"Y\",\"useTime\":\"12:00 ~ 13:00\",\"spcall\":\"00-0000-0000\",\"reservYn\":\"Y\",\"gcpYn\":\"Y\",\"genYn\":\"Y\",\"superSpeedCnt\":\"11\",\"highSpeedCnt\":\"2\",\"slowSpeedCnt\":\"1\"},{\"spid\":\"11\",\"csid\":\"22222223\",\"espid\":\"333\",\"ecsid\":\"1234567890123456789012345678901234561\",\"csnm\":\"현대EV스테이션 1\",\"dist\":\"1.1\",\"lat\":\"37.482991\",\"lot\":\"126.881369\",\"daddr\":\"서울특별시 금천구 470-5\",\"addrDtl\":\"에이스테크\",\"spnm\":\"운영사업자명\",\"useYn\":\"Y\",\"useTime\":\"12:00 ~ 13:00\",\"spcall\":\"00-0000-0000\",\"reservYn\":\"Y\",\"gcpYn\":\"Y\",\"genYn\":\"Y\",\"superSpeedCnt\":\"11\",\"highSpeedCnt\":\"2\",\"slowSpeedCnt\":\"0\"},{\"spid\":\"11\",\"csid\":\"22222223\",\"espid\":\"333\",\"ecsid\":\"1234567890123456789012345678901234562\",\"csnm\":\"현대EV스테이션 2\",\"dist\":\"11.1\",\"lat\":\"37.479533\",\"lot\":\"126.879282\",\"daddr\":\"서울특별시 금천구 가산동\",\"addrDtl\":\"\",\"spnm\":\"운영사업자명\",\"useYn\":\"Y\",\"useTime\":\"12:00 ~ 13:00\",\"spcall\":\"00-0000-0000\",\"reservYn\":\"Y\",\"gcpYn\":\"Y\",\"genYn\":\"Y\",\"superSpeedCnt\":\"11\",\"highSpeedCnt\":\"0\",\"slowSpeedCnt\":\"1\"},{\"spid\":\"11\",\"csid\":\"22222223\",\"espid\":\"333\",\"ecsid\":\"1234567890123456789012345678901234563\",\"csnm\":\"현대EV스테이션 3\",\"dist\":\"11.1\",\"lat\":\"37.477494\",\"lot\":\"126.885952\",\"daddr\":\"서울특별시 금천구 가산동\",\"addrDtl\":\"\",\"spnm\":\"운영사업자명\",\"useYn\":\"Y\",\"useTime\":\"12:00 ~ 13:00\",\"spcall\":\"00-0000-0000\",\"reservYn\":\"N\",\"gcpYn\":\"Y\",\"genYn\":\"Y\",\"superSpeedCnt\":\"11\",\"highSpeedCnt\":\"0\",\"slowSpeedCnt\":\"1\"}]}", EPT_1001.Response.class);
}