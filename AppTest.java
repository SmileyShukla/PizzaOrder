package com.pizzashop.order;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

import com.pizzashop.order.services.PizzaApp;
import com.pizzashop.order.services.PizzaOrderService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
 */
public class AppTest extends TestCase {

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * 
	 */

	public void testApp() {
		PizzaApp app = new PizzaApp();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String srcFile = null;
		String destFile = null;
		try {
			srcFile = br.readLine();
			destFile = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (testUserInput(srcFile, destFile, System.out) && testFileData(srcFile)) {
			assertTrue(app.pizzaShopApp());
		}

	}

	public static Boolean testUserInput(String srcFile, String destFile, PrintStream out) {
		Boolean isValid = false;
		File f1 = new File(srcFile);
		File f2 = new File(destFile);
		if (srcFile != null && !srcFile.trim().isEmpty() && destFile != null && !destFile.trim().isEmpty()) {
			if (!f1.exists()) {
				System.out.println("Source file doesn't exist or given location is invalid,Please try again.");
				return isValid;

			} else if (!srcFile.substring(srcFile.indexOf(".") + 1, (srcFile.length())).equals("txt")) {
				System.out.println("Source file format is invalid ,Please try again.");
				return isValid;
			}
			if (!f2.isDirectory()) {
				out.println("Destination file doesn't exist or given location is invalid or file,Please try again.");
				return isValid;
			} else if (!destFile.substring(destFile.indexOf(".") + 1, (destFile.length())).equals("txt")) {
				System.out.println("Destination file format is invalid,Please try again.");
				return isValid;
			}
			isValid = true;
		} else {
			System.out.println("Source/Destination file can not be empty or null,Please try again.");
		}
		return true;

	}
	
	public static Boolean testFileData(String srcFile) {
		List<String> orders=PizzaOrderService.readTheFileFromLocation(srcFile);
		for (String order : orders) {
			if (!order.isEmpty()) {
				String[] st = order.split("\\t");
				try {
					new Integer(st[st.length - 1]);
					return true;
				}catch(NumberFormatException ne) {
					System.out.println("File contains invalid data.");
				}
				
			}
		}
		return false;
		
	}
	

}
