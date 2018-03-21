package com.pizzashop.order.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.pizzashop.order.transferobjects.OrderTO;



/**
 * Hello world!
 *
 */
public class PizzaApp 
{	
	public static final String CLASS_NAME = PizzaApp.class.getName();
	public String inputFile;
	public String outputFile;
	
    public static void main( String[] args )
    {
	    	PizzaApp obj = new PizzaApp();
	    	obj.pizzaShopApp();
    }
    
    public Boolean pizzaShopApp() {
    	final String METHOD_NAME = "pizzaShopApp";
        System.out.println( "Enter input file name with location" );
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Boolean isSuccess=false;
        try {
			inputFile = br.readLine();
			//System.out.println(inputFile);
		    System.out.println( "Enter output file name with location" );
		    outputFile = br.readLine();
		    if(PizzaOrderService.validateUserInput(inputFile,outputFile)) {
			List<String> orders =PizzaOrderService.readTheFileFromLocation(inputFile);
			List<OrderTO> formattedOrders=PizzaOrderService.sortTheListOfOrders(orders);
		    isSuccess= PizzaOrderService.writeDestinationFile(formattedOrders, outputFile);
		    }
        } catch (IOException e) {
			System.out.println(CLASS_NAME+":"+METHOD_NAME);
			e.printStackTrace();
			return isSuccess;
		}
		return isSuccess;
    }
}
