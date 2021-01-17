package functional_tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import pt.isep.cms.seleniumcucumber.SeleniumCucumber;

public class ShippingLocationsDefinitions implements SeleniumCucumber {

    ////Driver
    WebDriver driver;
    //URL for Shipping Location Page
    String url = "http://127.0.0.1:8091/cms-students-1.0/#!CwShippingLocations";

    //Shipping Location Definitions Classe
    public ShippingLocationsDefinitions() {

        driver = DriverUtil.loadDriver();

        if (driver == null) driver = new HtmlUnitDriver();
    }


    //  Scenario: Create Shipping Location
    @Given("^Navigate to Shipping Location page$")
    public void navigate_to_Shipping_Location_page() throws Throwable {
        driver.get(url);
        Thread.sleep(1000);
    }

    @When("^I click to add Shipping Location$")
    public void click_to_add_Shipping_Location()  {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[1]/button")).click();
    }

    //ShippingLocation Name
    @And("^I fill the Shipping Location fields$")
    public void fill_Shipping_Location_fields() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input")).sendKeys("Shipping_Location_X_Testing");
    }

    @And("^I click to save Shipping Location$")
    public void click_to_save_Shipping_Location() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody/tr/td[1]/button")).click();
    }

    @Then("^Save Shipping Location$")
    public void save_Shipping_Location() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody"));
        Assert.assertTrue(definitionObj.find(list, "Shipping_Location_X_Testing"));
        definitionObj.delete(list, "Shipping_Location_X_Testing");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button")).click();
        driver.close();
    }


    //Scenario: Delete Shipping Location
    @And("^I click to delete Shipping Location$")
    public void i_click_to_delete_Shipping_Location() {
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody"));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        definitionObj.delete(list, "Shipping_Location_X_Testing");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button")).click();
    }


    @Then("^Delete Shipping Location$")
    public void delete_Shipping_Location() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody"));
        Assert.assertFalse(definitionObj.find(list, "Shipping_Location_X_Testing"));
        driver.close();
    }


    //Scenario: Edit Shipping Location
    @When("^I click to edit Shipping Location$")
    public void i_click_to_edit_Shipping_Location() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody"));
        definitionObj.edit(list, "Shipping_Location_X_Testing");

    }

    @And("^Edit Shipping Location fields$")
    public void edit_Shipping_Location_fields() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/input")).sendKeys("Shipping_Location_X_Testing_Edit");

    }

    @Then("^Edit Shipping Location$")
    public void edit_Shipping_Location() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table"));
        Assert.assertTrue(definitionObj.find(list, "Shipping_Location_X_Testing_Edit"));
        definitionObj.delete(list, "Shipping_Location_X_Testing_Edit");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button")).click();
        driver.close();
    }

}
