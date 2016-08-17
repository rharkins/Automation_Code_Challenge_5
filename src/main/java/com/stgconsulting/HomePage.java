package com.stgconsulting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Created by Richard Harkins on 7/15/2016.
 */
public class HomePage extends SeleniumWebdriverBaseClass
{
    public HomePage() throws IOException {
    }

    public static void getResortMileageFromAirport(String resortName)
    {
        // Find the Compare Resorts WebElement and click on it
        WebElement compareResorts = driver.findElement(By.xpath(".//*[@id='ski-utah-welcome-map']/div/div[2]/div[4]/label/span[1]"));
        compareResorts.click();

        // Find the Miles from Airport menu item popout and click on it
        WebElement milesFromAirport = driver.findElement(By.xpath("//*[@id=\"ski-utah-welcome-map\"]/div/div[2]/div[4]/div/label[1]"));
        milesFromAirport.click();

        // Check if passed in resortName is empty or null
        if (isBlank(resortName))
        {
            // Print that no resort name was entered
            System.out.println("No Resort Name entered");
            // Exit from method
            return;
        }

        // Convert passed in resortName to all lowercase
        String resortNameLowercase = resortName.toLowerCase();
        // Declare resortValueName for holding value from HashMap
        String resortValueName = null;
        // Get resortValueName from HashMap
        resortValueName = resortList.get(resortNameLowercase);

        // Check if value returned from HashMap does not exist - returns null from HashMap
        if (isBlank(resortValueName))
        {
            System.out.println("Resort Name not found");
        }

        // Resort Name found in HashMap
        else
        {
            // Print passed in resortName
            // System.out.println("Entered Resort Name = " + resortName);
            // Print resortValueName from HashMap
            // System.out.println("Resort Value = " + resortValueName);
            // Get WebElement (first span tag) using resortValueName
            WebElement resortMileageFromAirport = driver.findElement(By.xpath("//span[@class='map-Area-shortName'][.='" + resortValueName + "']"));
            // Print class value of WebElement
            // System.out.println(resortMileageFromAirport.getAttribute("class"));
            // Print text value of WebElement
            // System.out.println(resortMileageFromAirport.getText());
            // Get WebElement (span tag with distance value) using resortValueName
            WebElement resortMileageFromAirportElement = resortMileageFromAirport.findElement(By.xpath("//span[@class='map-Area-shortName'][.='" + resortValueName + "']" + "/following-sibling::span[contains(@class,'distance')]"));
            // Print class value of WebElement
            // System.out.println(resortMileageFromAirportElement.getAttribute("class"));
            // Print innerHtml value of WebElement
            // System.out.println(resortMileageFromAirportElement.getAttribute("innerHTML"));
            // Create a variable to hold the resortMileage value
            String resortMileage = resortMileageFromAirportElement.getAttribute("innerHTML");
            // Print the mileage from the airport for the passed in resort name
            System.out.println(resortValueName + " is " + resortMileage + " miles from the airport");

        }
    }
}
