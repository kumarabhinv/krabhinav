package com.av;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

public class base extends utils {

    private static WebDriver driver;
    private String screenshotName;

    protected WebDriver launch (String browser) throws MalformedURLException {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*");
                String gridUrl = System.getProperty("grid.url");
                WebDriverManager.chromedriver().setup();
                if (gridUrl != null && !gridUrl.isEmpty()) {
                    driver = new RemoteWebDriver(new URL(gridUrl), options);
                } else {
                    driver = new ChromeDriver(options);
                }
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
        String fileName;
        if(screenshotName == null) {
            fileName = "image_" + dateTime().replace(":", ".");
        } else {
            fileName = screenshotName + dateTime().replace(":", ".");
        }
        try {
            FileUtils.copyFile(screenshot.getScreenshotAs(OutputType.FILE), new File("src/img/" + fileName + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    protected base screenshot (String screenshotName) {
        this.screenshotName = screenshotName;
        screenshot();
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
