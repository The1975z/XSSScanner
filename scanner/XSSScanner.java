package scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.NoAlertPresentException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class XSSScanner 
{

    public static void entry() throws InterruptedException 
    {
        System.out.println(Colors.CGREEN + "      XSS Scanner           ");
        System.out.println(Colors.CGREEN + "      Coded by The1975");
        System.out.println(Colors.CGREEN + "      Instagram ==> The1975");
        TimeUnit.MILLISECONDS.sleep(45);
    }

    public static void xssInj(String targetUrl) 
    {
        System.out.println("Trying payloads list, Please wait...");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        Random random = new Random();

        try (BufferedReader br = new BufferedReader(new FileReader("myfile.txt"))) {
            String payload;
            while ((payload = br.readLine()) != null) {
                String urlToVisit = targetUrl.trim() + payload.trim();
                System.out.println("Testing: " + urlToVisit);

                try 
                {
                    driver.get(urlToVisit);
                    TimeUnit.SECONDS.sleep(random.nextInt(3) + 1);
                } catch (NoAlertPresentException ignored) {
                    
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                    break;
                }
            }
        } catch (IOException | InterruptedException e) 
        {
            System.err.println("Error reading file or interrupted: " + e.getMessage());
        } finally 
        {
            driver.quit();
        }
    }
}
