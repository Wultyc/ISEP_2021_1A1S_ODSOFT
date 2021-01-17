package pt.isep.cms.products;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;
import pt.isep.cms.products.client.ExampleGWTTest;

public class ExampleGWTTestSuit extends GWTTestSuite {
	  public static Test suite() {
		    TestSuite suite = new TestSuite("Test for the Products Application");
		    suite.addTestSuite(ExampleGWTTest.class); 
		    return suite;
		  }
		} 
