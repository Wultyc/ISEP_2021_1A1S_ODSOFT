package functional_tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.ErrorHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * Created by tom on 24/02/17.
 */
public class DriverUtil {

	public static long DEFAULT_WAIT = 20;
	protected static WebDriver driver;

	public static WebDriver loadDriver() {
		Map<String, HashMap<Double, String>> config = DriverUtil.loadConfig();
		String path = buildPath();

		if (config.isEmpty()) {
			String browser = System.getProperty("browser");
			switch (browser) {
				case "firefox":
					return loadFirefoxDriver(path);
				case "chrome":
					return loadChromeDriver(path);
				default:
					return new HtmlUnitDriver();
			}

		}

		for (String key : config.keySet()) {
			//firefox
			if (key.equals("chrome")) {
				return loadChromeDriver(path);
			}
		}
		return new HtmlUnitDriver();
	}

	private static Map<String, HashMap<Double, String>> loadConfig() {
		JSONParser jsonParser = new JSONParser();
		Map<String, HashMap<Double, String>> result = new HashMap<>();

		try (FileReader reader = new FileReader("src/test/java/functional_tests/browers_drivers/config.json")) {
			//Read JSON file
			Object obj = jsonParser.parse(reader);

			JSONObject config = (JSONObject) obj;
			System.out.println(config);

			JSONArray browsers = (JSONArray) config.get("browsers");
			for (Object browser : browsers) {
				JSONObject b = (JSONObject) browser;
				String name = (String) b.get("name");
				JSONArray versions = (JSONArray) b.get("versions");
				HashMap<Double, String> ver = new HashMap<>();
				for (Object v : versions) {
					JSONObject version = (JSONObject) v;
					Double number = (Double) version.get("version");
					String driver = (String) version.get("driver");
					ver.put(number, driver);
				}
				result.put(name, ver);
			}
			return result;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static WebDriver loadChromeDriver(String relativePath) {

		String path;

		if(relativePath.endsWith("win")) {
			path = new File("").getAbsolutePath() + relativePath + "/chromedriver.exe";
		} else {
			path = new File("").getAbsolutePath() + relativePath + "/chromedriver";
		}

		File f = new File(path);
		if (f.exists() && !f.isDirectory()) {
			System.setProperty("webdriver.chrome.driver", path);
			return new ChromeDriver();
		}

		return null;
	}

	private static WebDriver loadFirefoxDriver(String relativePath) {

		String path;

		if(relativePath.endsWith("win")) {
			path = new File("").getAbsolutePath() + relativePath + "/geckodriver.exe";
		} else {
			path = new File("").getAbsolutePath() + relativePath + "/geckodriver";
		}

		File f = new File(path);
		if (f.exists()) {
			System.setProperty("webdriver.gecko.driver", path);
			return new FirefoxDriver();
		}
		return null;
	}

	private static String buildPath() {
		String os = System.getProperty("os.name");

		if (os.startsWith("Mac")) {
			return "/src/test/java/functional_tests/browers_drivers/mac";
		}

		if (os.startsWith("Linux")) {
			return "/src/test/java/functional_tests/browers_drivers/linux";
		}

		if(os.startsWith("Win")) {
			return "/src/test/java/functional_tests/browers_drivers/win";
		}

		return null;
	}



	public static WebDriver getDefaultDriver() {
		if (driver != null) {
			return driver;
		}
		//System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver.exe");
		//System.setProperty("webdriver.gecko.driver", "./geckodriver");
		DesiredCapabilities capabilities = null;
		capabilities = DesiredCapabilities.firefox();
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("takesScreenshot", true);
		driver = chooseDriver(capabilities);
		driver.manage().timeouts().setScriptTimeout(DEFAULT_WAIT,
				TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	/**
	 * By default to web driver will be PhantomJS
	 *
	 * Override it by passing -DWebDriver=Chrome to the command line arguments
	 * @param capabilities
	 * @return
	 */
	private static WebDriver chooseDriver(DesiredCapabilities capabilities) {
		// ATB: To select the driver comment/uncomment the following lines
		String preferredDriver = System.getProperty("browser", "Firefox");
		// String preferredDriver = System.getProperty("browser", "Chrome");
		// String preferredDriver = System.getProperty("browser", "phantomjs");

		boolean headless = System.getProperty("Headless", "true").equals("true");

		switch (preferredDriver.toLowerCase()) {
			case "chrome":
				final ChromeOptions chromeOptions = new ChromeOptions();
				if (headless) {
					chromeOptions.addArguments("--headless");
				}
				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
				System.out.println("********************* before driver created");
				ChromeDriver driver = new ChromeDriver();
				System.out.println("********************* after driver created");
				ErrorHandler handler = new ErrorHandler();
				handler.setIncludeServerErrors(false);
				driver.setErrorHandler(handler);
				return driver;
			case "phantomjs":
				// ATB - To disable logging
				String[] phantomArgs = new  String[] { "--webdriver-loglevel=ERROR", "--webdriver-logfile=/dev/null" };
				capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);

				return new PhantomJSDriver(capabilities);
			default:
				//return new PhantomJSDriver(capabilities);
				FirefoxOptions options = new FirefoxOptions();
				//capabilities.s
				if (headless) {
					// System.out.println("*********** ATB ********** Firefox Headless ");
					options.addArguments("-headless", "-safe-mode");
					//options.addArguments("--headless");    // ATB
					//options.addArguments("--hide-scrollbars");    // ATB
					//options.addArguments("--disable-gpu");    // ATB

					options.setHeadless(true);

				}
				// ATB - To disable logging
				System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null"); // ATB

				capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
				final FirefoxDriver firefox = new FirefoxDriver();
				return firefox;
		}
	}

	public static void closeDriver() {
		if (driver != null) {
			try {
				driver.close();
				//driver.quit(); // fails in current geckodriver! TODO: Fixme
			} catch (NoSuchMethodError nsme) { // in case quit fails
			} catch (NoSuchSessionException nsse) { // in case close fails
			} catch (SessionNotCreatedException snce) {} // in case close fails
			driver = null;
		}
	}

}
