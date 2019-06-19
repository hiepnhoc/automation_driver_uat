package com.tpf.automation.tpf_automation;

import com.tpf.automation.tpf_automation.entity.FptCustomer;
import com.tpf.automation.tpf_automation.entity.vin.MomoDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutomationConstant {
    public static String driverProperty = "webdriver.chrome.driver";
    public static String driverPath = "c:\\driver\\chromedriver.exe";

    public static String cicUsername = "h01358001trinh";
    public static String cicPassword = "Datacheck2018";

    public static String finnOneUAT = "http://10.1.66.20:4141/finnone-webapp/app/auth/login";
    public static String driverWindowSize = "window-size=1366x768";
    public static String driverWindowSizeFpt = "window-size=1800x3000";

    public static int globalWait = 10;

    public static String pathCapture = "C:/logpic/error_time_.png";

    public static List<List> userId = new ArrayList<>();
    private static List<String> user1 = Arrays.asList("auto_1","Hcm@12345","false");
//    private static List<String> user2 = Arrays.asList("auto_2","Hcm@12345","false");
//    private static List<String> user3 = Arrays.asList("auto_3","Hcm@12345","false");
//    private static List<String> user4 = Arrays.asList("auto_4","Hcm@12345","false");
//    private static List<String> user5 = Arrays.asList("auto_5","Hcm@12345","false");

    public static List<FptCustomer> customerQueue = new ArrayList<>();
    public static List<MomoDTO> customerMomoQueue = new ArrayList<>();
    public static List<List> userIdMomo = new ArrayList<>();

    private static List<String> userMomo1 = Arrays.asList("momo_auto1","Hcm@12345","false");

    public static void initUser () {
        userId.add(user1);
//        userId.add(user2);
//        userId.add(user3);
//        userId.add(user4);
//        userId.add(user5);
    }

    public static void initUserMomo () {
        userIdMomo.add(userMomo1);
//        userId.add(user2);
//        userId.add(user3);
//        userId.add(user4);
//        userId.add(user5);
    }



    public static List<List> userId1 = new ArrayList<>();
    private static List<String> user11 = Arrays.asList("auto_11","Hcm@12345","false");
    /*private static List<String> user21 = Arrays.asList("auto_21","Hcm@12345","false");
    private static List<String> user31 = Arrays.asList("auto_31","Hcm@12345","false");
    private static List<String> user41 = Arrays.asList("auto_41","Hcm@12345","false");
    private static List<String> user51 = Arrays.asList("auto_51","Hcm@12345","false");*/

    //public static List<FptCustomer> customerQueue = new ArrayList<>();

    public static void initUser1 () {
        userId1.add(user11);
       /* userId1.add(user21);
        userId1.add(user31);
        userId1.add(user41);
        userId1.add(user51);*/
    }






}
