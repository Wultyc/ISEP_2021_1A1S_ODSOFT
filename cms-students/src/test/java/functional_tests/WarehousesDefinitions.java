package functional_tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

public class WarehousesDefinitions {

	 WebDriver driver;
	 String url = "http://localhost:8080/showcase/contactsService";
/**
	//URL


	public WarehousesDefinitions() {

		driver = DriverUtil.loadDriver();

		if (driver == null) driver = new HtmlUnitDriver();
	}

    //Create Warehouse

	@Given("^Navigate to Warehouse page$")
	public void navigate_to_Warehouse_page() throws Throwable {
		driver.get(url);
		Thread.sleep(1000);
	}
**/
@Given("^Navigate to Warehouse page$")
public void navigate_to_Warehouse_page() throws Throwable {
	driver = new FirefoxDriver();
	driver.manage().window().maximize();
	driver.get(url);
}



	@When("^I click to add Warehouse$")
	public void click_to_add_Warehouse() throws Throwable {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("contacts_addButton")).click();
	}

	//Warehouse Name
	//Total Capacity
	@And("^I fill the Warehouse fields$")
	public void fill_Warehouse_fields() throws Throwable {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("contacts_dialog_textbox_firstName")).sendKeys("Warehouse_X");
		driver.findElement(By.id("contacts_dialog_textbox_lastName")).sendKeys("Capacity_Y");
	}

	@And("^I click to save Warehouse$")
	public void click_to_save_Warehouse() throws Throwable {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.id("contacts_dialog_saveButton")).click();
	}

	@Then("^Save Warehouse$")
	public void save_Warehouse() throws Throwable {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		List<WebElement> list = driver.findElements(By.id("contacts_contactsTable"));
		Assert.assertTrue(this.findWarehouse(list, "FirstName LastName"));
	}



	private boolean findWarehouse(List<WebElement> list, String text) {
		for (WebElement element : list) {

			List<WebElement> td = element.findElements(By.tagName("td"));
			for (WebElement l : td) {
				System.out.println(l.getText());
				if (l.getText().contains(text)) {
					return true;
				}
			}
		}
		return false;
	}

}
