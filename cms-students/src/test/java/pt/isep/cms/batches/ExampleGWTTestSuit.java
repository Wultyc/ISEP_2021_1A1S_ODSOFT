package pt.isep.cms.batches;

import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;
import pt.isep.cms.batches.client.ExampleGWTTest;

public class ExampleGWTTestSuit extends GWTTestSuite {
	  public static Test suite() {
		    TestSuite suite = new TestSuite("Test for the Batches Application");
		    suite.addTestSuite(ExampleGWTTest.class); 
		    return suite;
		  }
		} 
