package com.tpf.automation.tpf_automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Utils {
    public static String SCREENSHOT_PRE_PATH = "C:/selenium/momo/finnone_";
    public static String SCREENSHOT_PRE_PATH_DOCKER = "/images/";
    public static String SCREENSHOT_EXTENSION = ".png";

    public static void captureScreenShot(WebDriver driver) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File(SCREENSHOT_PRE_PATH_DOCKER + System.currentTimeMillis() + SCREENSHOT_EXTENSION));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
