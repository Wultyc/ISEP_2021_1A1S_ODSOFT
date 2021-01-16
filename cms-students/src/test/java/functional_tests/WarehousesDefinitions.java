package functional_tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gwt.user.cellview.client.AbstractCellTable;
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

	public WarehousesDefinitions() {

		driver = DriverUtil.loadDriver();

		if (driver == null) driver = new HtmlUnitDriver();
	}

	@Given("^Navigate to Warehouse page$")
	public void navigate_to_Warehouse_page() throws Throwable {
		driver.get(url);
		Thread.sleep(1000);
	}

	@When("^I click to add Warehouse$")
	public void click_to_add_Warehouse() throws Throwable {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[1]/button")).click();
		//driver.findElement(By.id("contacts_addButton")).click();
	}

	//Warehouse Name
	//Total Capacity
	@And("^I fill the Warehouse fields$")
	public void fill_Warehouse_fields() throws Throwable {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		//driver.findElement(By.id("contacts_dialog_textbox_firstName")).sendKeys("Warehouse_X");
		//driver.findElement(By.id("contacts_dialog_textbox_lastName")).sendKeys("Capacity_Y");
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[1]/td[2]/input")).sendKeys("Warehouse_X_Testing");
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr[2]/td[2]/input")).sendKeys("Capacity_Y_Testing");
	}

	@And("^I click to save Warehouse$")
	public void click_to_save_Warehouse() throws Throwable {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[6]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody/tr/td[1]/button")).click();
	}

	@Then("^Save Warehouse$")
	public void save_Warehouse() throws Throwable {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		//List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table"));
		List<WebElement> list = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody"));

		Assert.assertTrue(warehousesObj.findWarehouse(list, "Warehouse_X_Testing: Capacity_Y_Testing"));
		//driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[2]/td/table/tbody/tr[4]/td[1]/span")).click();

		warehousesObj.deleteWarehouse(list, "Warehouse_X_Testing: Capacity_Y_Testing");
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[4]/div/div[3]/div/div[2]/div/div[2]/div/div[2]/div/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button")).click();
		driver.close();
	}



}
