package pt.isep.cms.warehouses;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;
import pt.isep.cms.warehouses.client.ExampleGWTTest;

public class ExampleGWTTestSuit extends GWTTestSuite {
	  public static Test suite() {
		    TestSuite suite = new TestSuite("Test for the Warehouses Application");
		    suite.addTestSuite(ExampleGWTTest.class); 
		    return suite;
		  }
		} 
