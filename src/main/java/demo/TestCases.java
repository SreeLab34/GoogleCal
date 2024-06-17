package demo;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCases {

    ChromeDriver driver;

    public TestCases() throws MalformedURLException {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");
        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();
    }

    public void testCase01() {

        System.out.println("Start Test case: testCase01");
        driver.get("https://calendar.google.com/");
        String currentURL = driver.getCurrentUrl();
        if (currentURL.contains("calendar")) {
            System.out.println("Test case passed: URL contains 'calendar'");
        } else {
            System.out.println("Test case failed: URL does not contain 'calendar'");
        }
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");
        driver.get("https://calendar.google.com/");
        WebElement sign = driver.findElement(By.xpath("//span[@class='button__label'][text()='Sign in ']"));
        sign.click();
        Thread.sleep(5000);
        WebElement user = driver.findElement(By.xpath("(//input[@class='whsOnd zHQkBf'])[1]"));
        user.sendKeys("t63945208@gmail.com");
        WebElement next = driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d'][text()='Next']"));
        next.click();
        Thread.sleep(5000);

        WebElement pwd = driver.findElement(By.xpath("//input[@type='password']"));
        pwd.sendKeys("Test@123");
        WebElement pnext = driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d'][text()='Next']"));
        pnext.click();
        Thread.sleep(5000);

        //span[@class='VfPpkd-vQzf8d'][text()='Got it']
        WebElement mv = driver.findElement(By.xpath("(//span[@class='VfPpkd-vQzf8d'])[5]"));
        mv.click();
        Thread.sleep(2000);
        WebElement month = driver.findElement(By.xpath("//span[@class='VfPpkd-StrnGf-rymPhb-b9t22c'][text()='Month']"));
      //  WebElement month = driver.findElement(By.xpath("//*[@id=\'ucc-1\']/ul/li[3]/span[2]"));
        month.click();
       // Thread.sleep(2000);
       // driver.get("https://calendar.google.com/calendar/u/0/r/month");
       // Thread.sleep(2000);
       // String currentURL = driver.getCurrentUrl();
       // String updatedURL = currentURL.replaceAll("(?i)month", "Month");
       if (month.getText().equals("Month")) {
            System.out.println("Test case passed: URL contains 'Month'");
        } else {
            System.out.println("Test case failed: URL does not contain 'month'");
        }
        Thread.sleep(5000);
        WebElement createButton = driver.findElement(By.xpath("//div[text()='Create']"));
        createButton.click();
        Thread.sleep(1000);
        WebElement task = driver.findElement(By.xpath("//div[@class='jO7h3c'][text()='Task']"));
        task.click();

        WebElement titleInput = driver.findElement(By.xpath("//input[@aria-label='Add title']"));
        titleInput.sendKeys("Crio INTV Task Automation");

        WebElement descriptionInput = driver.findElement(By.xpath("//textarea[@aria-label='Add description']"));
        descriptionInput.sendKeys("Crio INTV Calendar Task Automation");
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement saveButton = driver.findElement(By.xpath("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc LQeN7 pEVtpe']"));
        // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            WebElement popUp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='VYTiVb'][text()='Task created']")));
            popUp.click();
            if (popUp.getText().contains("Task created")) {
                System.out.println("Task created successfully.");
            }
        } catch (TimeoutException e) {
            // Handle the case when the pop-up doesn't appear within the timeout
            System.out.println("Pop-up for task created did not appear within the expected time.");
            e.printStackTrace();
        }
        // descriptionInput.sendKeys(Keys.TAB);
        // descriptionInput.sendKeys(Keys.TAB);
        // descriptionInput.sendKeys(Keys.ENTER);       
        Thread.sleep(2000);
        // WebElement tasksTab = driver.findElement(By.xpath("//div[@title='Tasks']"));
        // tasksTab.click();
        // Thread.sleep(2000); 

        System.out.println("End Test case: testCase02");
    }

    public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
 driver.get("https://calendar.google.com/calendar/u/0/r/month");
        Thread.sleep(5000);
        WebElement mod = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='WBi6vc'][text()='Crio INTV Task Automation']")));
        if (mod.getText().contains("Crio INTV Task Automation")) {
            System.out.println("done");

        }
        mod.click();
        WebElement edit = driver.findElement(By.xpath("(//span[@class='VfPpkd-kBDsod meh4fc KU3dEf'])[3]"));
        edit.click();

        WebElement descriptionInput = driver.findElement(By.xpath("//textarea[@class='VfPpkd-fmcmS-wGMbrd vRGQ0d']"));
        descriptionInput.clear();
        descriptionInput.sendKeys("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application");
        WebElement saveButton = driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d'][text()='Save']"));
        saveButton.click();
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement popUp1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='VYTiVb'][text()='Task updated']")));
            popUp1.click();
            if (popUp1.getText().contains("Task updated")) {
                System.out.println("Task updated successfully.");
            }
        } catch (TimeoutException e) {
            // Handle the case when the pop-up doesn't appear within the timeout
            System.out.println("Pop-up for task updated did not appear within the expected time.");
           // e.printStackTrace();
        }
        Thread.sleep(2000);
       // WebElement mod1 = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='WBi6vc'][text()='Crio INTV Task Automation']")));
       WebElement mod1 = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='nHqeVd'])[2]")));
        mod1.click();
        WebElement descriptionInput1 = driver.findElement(By.xpath("//div[@class='toUqff vfzv']"));
        System.out.println("***********Text is"+descriptionInput1.getText());
        if (descriptionInput1.getText().contains("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application")) {
            System.out.println("Successfully edited");
        }
        // WebElement mod1 = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='WBi6vc'][text()='Crio INTV Task Automation']")));
        // mod1.click();
        
       

        //  WebElement edit1 = driver.findElement(By.xpath("(//span[@class='VfPpkd-kBDsod meh4fc KU3dEf'])[3]"));
        // edit1.click();
        // WebElement des = driver.findElement(By.xpath("//div[@class='toUqff vfzv']"));
        System.out.println("End Test case: testCase03");
        Thread.sleep(5000);

    }

    public void testCase04() throws InterruptedException {
        System.out.println("Start Test case: testCase04");
        driver.get("https://calendar.google.com/calendar/u/0/r/month");
        Thread.sleep(5000);
        WebElement mod1 = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='WBi6vc'][text()='Crio INTV Task Automation']")));
        mod1.click();
        if (mod1.getText().contains("Crio INTV Task Automation")) {
            System.out.println("done");

        }
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement del = driver.findElement(By.xpath("(//button[@class='VfPpkd-Bz112c-LgbsSe yHy1rc eT1oJ mN1ivc m2yD4b HfYfLe'])[2]"));
        del.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Wait until the pop-up element is clickable
            WebElement popUp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='VYTiVb'][text()='Task deleted']")));
            popUp.click();
            if (popUp.getText().contains("Task Deleted")) {
                System.out.println("Task deleted successfully.");
            }
        } catch (TimeoutException e) {
            // Handle the case when the pop-up doesn't appear within the timeout
            System.out.println("Pop-up for task deletion did not appear within the expected time.");
            e.printStackTrace();
        }

        System.out.println("End Test case: testCase04");

    }

}
