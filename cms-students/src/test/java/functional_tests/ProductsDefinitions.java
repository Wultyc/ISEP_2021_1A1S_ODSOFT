package functional_tests;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import pt.isep.cms.seleniumcucumber.SeleniumCucumber;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductsDefinitions implements SeleniumCucumber {

    ///Driver
    WebDriver driver;
    //URL for Product Page
    String url = "http://127.0.0.1:8091/cms-students-1.0/#!CwProducts";

    //Product Definitions Classe
    public ProductsDefinitions() {

        driver = DriverUtil.loadDriver();

        if (driver == null) driver = new HtmlUnitDriver();
    }


    //  Scenario: Create Product
    @Given("^Navigate to Product page$")
    public void navigate_to_Product_page() throws Throwable {
        driver.get(url);
        Thread.sleep(1000);
    }

    @When("^I click to add Product$")
    public void click_to_add_Product()  {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[1]/button")).click();
    }


    //Product Name
    //Description
    //Price
    @And("^I fill the Product fields$")
    public void fill_Product_fields() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input")).sendKeys("Product_Name_X_Testing");
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input")).sendKeys("Description_Y_Testing");
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[3]/td[2]/input")).sendKeys("Price_Z_Testing");
    }

    @And("^I click to save Product$")
    public void click_to_save_Product() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody/tr/td[1]/button")).click();
    }

    @Then("^Save Product$")
    public void save_Product() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody"));
        Assert.assertTrue(definitionObj.find(list, "Product_Name_X_Testing: Description_Y_Testing"));
        definitionObj.delete(list, "Product_Name_X_Testing: Description_Y_Testing");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button")).click();
        driver.close();
    }


    //Scenario: Delete Product
    @And("^I click to delete Product$")
    public void i_click_to_delete_Product() {
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody"));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        definitionObj.delete(list, "Product_Name_X_Testing: Description_Y_Testing");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button")).click();
    }


    @Then("^Delete Product$")
    public void delete_Product() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody"));
        Assert.assertFalse(definitionObj.find(list, "Product_Name_X_Testing: Description_Y_Testing"));
        driver.close();
    }


    //Scenario: Edit Product
    @When("^I click to edit Product$")
    public void i_click_to_edit_Product() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody"));
        definitionObj.edit(list, "Product_Name_X_Testing: Description_Y_Testing");

    }

    @And("^Edit Product fields$")
    public void edit_Product_fields() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input")).sendKeys("Product_Name_X_Testing_Edit");
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input")).sendKeys("Description_Y_Testing_Edit");
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[3]/td[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[3]/td[2]/input")).sendKeys("Price_Z_Testing_Edit");
    }

    @Then("^Edit Product$")
    public void edit_Product() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table"));
        Assert.assertTrue(definitionObj.find(list, "Product_Name_X_Testing_Edit: Description_Y_Testing_Edit"));
        definitionObj.delete(list, "Product_Name_X_Testing_Edit: Description_Y_Testing_Edit");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button")).click();
        driver.close();
    }

}
