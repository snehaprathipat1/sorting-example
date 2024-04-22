package org.mercator.steps;

import org.junit.Assert;
import org.mercator.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mercator.Driver.driver;

public class Methods {

    String userName = ConfigReader.getProperty("username");
    String Password = ConfigReader.getProperty("password");


    public void getSauceDemoLoginPage() {
        driver.get("https://www.saucedemo.com");
    }

    public void enterValidCredentials(String username, String password) {
        driver.findElement(By.id(username)).sendKeys(userName);
        driver.findElement(By.id(password)).sendKeys(Password);
    }

    public void clickById(String id) {
        driver.findElement(By.id(id));
    }

    public void clickByCssSelector(String name) {
        driver.findElement(By.cssSelector(name)).click();
    }

    public void clickByClassName(String name) {
        driver.findElement(By.className(name)).click();
    }

    public void isElementPresent(String name, String text) {
        WebElement element = driver.findElement(By.className(name));
        String nameOfTheElement = element.getText();
        Assert.assertEquals("element is present on the page", text, nameOfTheElement);
    }

    public void assertText(String text) {
        assertTrue(driver.findElement(By.cssSelector(text)).isDisplayed());
    }


    public String selectHighestPriceItem() {
        List<WebElement> allProductPrices = driver.findElements(By.className("inventory_item_price"));

        // Create a list to store the prices
        List<String> priceList = new ArrayList<>();

        // Extract the prices from each element and add them to the list
        for (WebElement priceElement : allProductPrices) {
            String productPrice = priceElement.getText().replace("$", "");
            priceList.add(productPrice);
        }
        System.out.println("Prices of the products:");
        for (String price : priceList) {
            System.out.println(price);
        }
        double highestPrice = priceList.stream()
                .mapToDouble(Double::parseDouble)
                .max()
                .orElse(Double.NaN);
        String highestPriceString = String.format("$%.2f", highestPrice);

        System.out.println("Highest price: $" + highestPrice);
        return highestPriceString;
    }

    public void matchHighestProductWithItem(String highestPriceString) {

        // Locate the inventory items
        List<WebElement> inventoryItems = driver.findElements(By.xpath("//div[@class='inventory_item']"));

        WebElement inventoryItemWithHighestPrice = null;

        // Iterate through each inventory item to get its price
        for (WebElement inventoryItem : inventoryItems) {
            // Find the price element within the current inventory item
            WebElement priceElement = inventoryItem.findElement(By.xpath(".//div[contains(@class, 'inventory_item_price')]"));

            // Get the text of the price element
            String priceText = priceElement.getText();

            // Output the price for the current inventory item
            System.out.println("Price text: " + priceText);
            System.out.println("Highest price string: " + highestPriceString);

            if (priceText.equals(highestPriceString)) {
                // Set the current inventory item as the one with the highest price
                inventoryItemWithHighestPrice = inventoryItem;
                System.out.println(inventoryItem);
                // Exit the loop once the highest price item is found
                break;
            }
        }
        if (inventoryItemWithHighestPrice != null) {
            // Output the details of the inventory item with the highest price
            System.out.println("Inventory item with highest price:");

            // Find the name element within the inventory item
            WebElement nameOfTheHighestProduct = inventoryItemWithHighestPrice.findElement(By.xpath(".//div[contains(@class, 'inventory_item_name')]"));

            // Click on the name element to view the details
            nameOfTheHighestProduct.click();
            // Add code to output or perform actions on the inventoryItemWithHighestPrice
        } else {
            System.out.println("No inventory item found with the highest price.");
        }
    }
}
