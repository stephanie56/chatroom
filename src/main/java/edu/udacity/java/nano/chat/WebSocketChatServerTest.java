package edu.udacity.java.nano.chat;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WebSocketChatServerTest {

    private WebDriver browser;



    @Test
    public void testChatRoom() throws  Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/stephaniezeng/node_modules/chromedriver/lib/chromedriver/chromedriver");
        WebDriver browser = new ChromeDriver();
        try {
            browser.get("http://localhost:8080/");
            browser.findElement(By.id("username")).sendKeys("Stephanie");
            browser.findElement(By.className("submit")).click();
            // Test case: login works
            WebElement title = browser.findElement(By.id("page-title"));
            Assert.assertEquals(title.getText(), "Chat Room");
            // Test case: update online session number to 1 when a new user enters the chat room
            WebElement userName = browser.findElement(By.id("username"));
            WebElement count = browser.findElement(By.id("count"));
            Assert.assertEquals(userName.getText(), "Stephanie");
            Assert.assertEquals(count.getText(), "1");
        }
        finally {
            browser.quit();
        }
    }

}