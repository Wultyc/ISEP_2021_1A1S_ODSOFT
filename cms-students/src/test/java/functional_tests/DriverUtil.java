package functional_tests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by tom on 24/02/17.
 */
public class DriverUtil {


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
}
