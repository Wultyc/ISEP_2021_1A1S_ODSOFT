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

public class WarehousesDefinitions implements SeleniumCucumber {

	///Driver
	 WebDriver driver;
	 //URL for Warehouse Page
	 String url = "http://localhost:8091/cms-students-1.0/#!CwWarehouses";

	 //Warehouses Definitions Classe
	public WarehousesDefinitions() {

		driver = DriverUtil.loadDriver();

		if (driver == null) driver = new HtmlUnitDriver();
	}


	//  Scenario: Create Warehouse
	@Given("^Navigate to Warehouse page$")
	public void navigate_to_Warehouse_page() throws Throwable {
		driver.get(url);
		Thread.sleep(1000);
	}

	@When("^I click to add Warehouse$")
	public void click_to_add_Warehouse()  {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[1]/button")).click();
	}

	//Warehouse Name
	//Total Capacity
	@And("^I fill the Warehouse fields$")
	public void fill_Warehouse_fields() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input")).sendKeys("Warehouse_X_Testing");
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input")).sendKeys("Capacity_Y_Testing");
	}

	@And("^I click to save Warehouse$")
	public void click_to_save_Warehouse() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody/tr/td[1]/button")).click();
	}

	@Then("^Save Warehouse$")
	public void save_Warehouse() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table"));
		Assert.assertTrue(definitionObj.find(list, "Warehouse_X_Testing: Capacity_Y_Testing"));
		definitionObj.delete(list, "Warehouse_X_Testing: Capacity_Y_Testing");
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button")).click();
		driver.close();
	}


	//Scenario: Delete Warehouse
	@And("^I click to delete Warehouse$")
	public void i_click_to_delete_Warehouse() {
		List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table"));
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		definitionObj.delete(list, "Warehouse_X_Testing: Capacity_Y_Testing");
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button")).click();
	}


	@Then("^Delete Warehouse$")
	public void delete_Warehouse() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table"));
		Assert.assertFalse(definitionObj.find(list, "Warehouse_X_Testing: Capacity_Y_Testing"));
		driver.close();
	}


	//Scenario: Edit Warehouse
	@When("^I click to edit Warehouse$")
	public void i_click_to_edit_Warehouse() {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table"));
		definitionObj.edit(list, "Warehouse_X_Testing: Capacity_Y_Testing");

	}

	@And("^Edit Warehouse fields$")
	public void edit_Warehouse_fields() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input")).clear();
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input")).sendKeys("Warehouse_X_Testing_Edit");
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input")).clear();
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input")).sendKeys("Capacity_Y_Testing_Edit");
	}

	@Then("^Edit Warehouse$")
	public void edit_Warehouse() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table"));
		Assert.assertTrue(definitionObj.find(list, "Warehouse_X_Testing_Edit: Capacity_Y_Testing_Edit"));
		definitionObj.delete(list, "Warehouse_X_Testing_Edit: Capacity_Y_Testing_Edit");
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button")).click();
		driver.close();
	}
}
