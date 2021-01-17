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

public class BatchesDefinitions implements SeleniumCucumber {

    ///Driver
    WebDriver driver;
    //URL for Batch Page
    String url = "http://127.0.0.1:8091/cms-students-1.0/#!CwBatches";

    //Batch Definitions Classe
    public BatchesDefinitions() {

        driver = DriverUtil.loadDriver();

        if (driver == null) driver = new HtmlUnitDriver();
    }


    //  Scenario: Create Batch
    @Given("^Navigate to Batch page$")
    public void navigate_to_Batch_page() throws Throwable {
        driver.get(url);
        Thread.sleep(2000);
    }

    @When("^I click to add Batch$")
    public void click_to_add_Batch()  {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[1]/button")).click();
    }


    //Batche Name
    //Description
    //Manufacturing date
    @And("^I fill the Batch fields$")
    public void fill_Batch_fields() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input")).sendKeys("Batch_Name_X_Testing");
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input")).sendKeys("Description_Y_Testing");
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[3]/td[2]/input")).sendKeys("Date_Z_Testing");
    }

    @And("^I click to save Batch$")
    public void click_to_save_Batch() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody/tr/td[1]/button")).click();
    }

    @Then("^Save Batch$")
    public void save_Batch() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table"));
        Assert.assertTrue(definitionObj.find(list, "Batch_Name_X_Testing: Description_Y_Testing"));
        definitionObj.delete(list, "Batch_Name_X_Testing: Description_Y_Testing");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button")).click();
        driver.close();
    }


    //Scenario: Delete Batch
    @And("^I click to delete Batch$")
    public void i_click_to_delete_Batch() {
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table"));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        definitionObj.delete(list, "Batch_Name_X_Testing: Description_Y_Testing");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button")).click();
    }


    @Then("^Delete Batch$")
    public void delete_Batch() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table"));
        Assert.assertFalse(definitionObj.find(list, "Batch_Name_X_Testing: Description_Y_Testing"));
        driver.close();
    }


    //Scenario: Edit Batch
    @When("^I click to edit Batch$")
    public void i_click_to_edit_Batch() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table"));
        definitionObj.edit(list, "Batch_Name_X_Testing: Description_Y_Testing");

    }

    @And("^Edit Batch fields$")
    public void edit_Batch_fields() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input")).sendKeys("Batch_Name_X_Testing_Edit");
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input")).sendKeys("Description_Y_Testing_Edit");
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[3]/td[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[3]/td[2]/input")).sendKeys("Date_Z_Testing_Edit");
    }

    @Then("^Edit Batch$")
    public void edit_Batch() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table"));
        Assert.assertTrue(definitionObj.find(list, "Batch_Name_X_Testing_Edit: Description_Y_Testing_Edit"));
        definitionObj.delete(list, "Batch_Name_X_Testing_Edit: Description_Y_Testing_Edit");
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button")).click();
        driver.close();
    }

}
