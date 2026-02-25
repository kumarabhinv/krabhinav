package com.av;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class base extends utils {

    private static WebDriver driver;

    protected WebDriver launch (String browser) {
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                driver.get("https://www.google.com");
        }
        return driver;
    }

    protected base get (String url) {
        if (url.contains("https://")) {
            driver.get(url);
        } else {
            driver.get("https://" + url);
        }
        return this;
    }

    protected WebElement findByXpath (String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    protected List<WebElement> findsByXpath (String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    protected base screenshot () {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        String fileName = "image_" + dateTime().replace(":", ".");
        try {
            FileUtils.copyFile(screenshot.getScreenshotAs(OutputType.FILE), new File("src/img/" + fileName + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    protected base sleep (int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return this;
    }

    protected void close () {
        driver.close();
    }

    protected void quit () {
        driver.quit();
    }

    private String dateTime () {
        return LocalDateTime.now().toLocalDate() + "_" + LocalDateTime.now().toLocalTime();
    }

}
