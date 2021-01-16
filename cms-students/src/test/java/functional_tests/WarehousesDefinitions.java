package functional_tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gwt.user.cellview.client.AbstractCellTable;
import cucumber.api.TestCase;
import cucumber.api.java.eo.Se;
import org.eclipse.jetty.util.component.ContainerLifeCycle;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import pt.isep.cms.seleniumcucumber.SeleniumCucumber;

import javax.swing.*;

public class WarehousesDefinitions implements SeleniumCucumber {

	////Driver
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
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[1]/button")).click();
		//driver.findElement(By.id("contacts_addButton")).click();
	}

	//Warehouse Name
	//Total Capacity
	@And("^I fill the Warehouse fields$")
	public void fill_Warehouse_fields() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input")).sendKeys("Warehouse_X_Testing");
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input")).sendKeys("Capacity_Y_Testing");
	}

	@And("^I click to save Warehouse$")
	public void click_to_save_Warehouse() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody/tr/td[1]/button")).click();
	}

	@Then("^Save Warehouse$")
	public void save_Warehouse() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody"));
		Assert.assertTrue(warehousesObj.findWarehouse(list, "Warehouse_X_Testing: Capacity_Y_Testing"));
		warehousesObj.deleteWarehouse(list, "Warehouse_X_Testing: Capacity_Y_Testing");
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button")).click();
		driver.close();
	}


	//Scenario: Delete Warehouse
	@And("^I click to delete Warehouse$")
	public void i_click_to_delete_Warehouse() {
		List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody"));
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		warehousesObj.deleteWarehouse(list, "Warehouse_X_Testing: Capacity_Y_Testing");
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button")).click();
	}


	@Then("^Delete Warehouse$")
	public void delete_Warehouse() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody"));
		Assert.assertFalse(warehousesObj.findWarehouse(list, "Warehouse_X_Testing: Capacity_Y_Testing"));
		driver.close();
	}


	//Scenario: Edit Warehouse
	@When("^I click to edit Warehouse$")
	public void i_click_to_edit_Warehouse() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody"));
		warehousesObj.editWarehouse(list, "Warehouse_X_Testing: Capacity_Y_Testing");

	}

	@And("^Edit Warehouse fields$")
	public void editWarehousefields() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input")).clear();
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input")).sendKeys("Warehouse_X_Testing_Edit");
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input")).clear();
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input")).sendKeys("Capacity_Y_Testing_Edit");
	}

	@Then("^Edit Warehouse$")
	public void editWarehouse() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody"));
		Assert.assertTrue(warehousesObj.findWarehouse(list, "Warehouse_X_Testing_Edit: Capacity_Y_Testing_Edit"));
		warehousesObj.deleteWarehouse(list, "Warehouse_X_Testing_Edit: Capacity_Y_Testing_Edit");
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button")).click();
		driver.close();
	}
}
