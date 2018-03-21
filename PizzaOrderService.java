package com.pizzashop.order.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import com.pizzashop.order.transferobjects.OrderTO;

public class PizzaOrderService {
	public static final String CLASS_NAME = PizzaOrderService.class.getName();

	public static List<String> readTheFileFromLocation(String loc) {
		final String METHOD_NAME = "readTheFileFromLocation";
		List<String> orders = Collections.emptyList();
		try {
			orders = Files.readAllLines(Paths.get(loc), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println(CLASS_NAME + ":" + METHOD_NAME);
			System.out.println(
					"Error occurred while reading the file,please check the file exist in given location and its proper.");
			e.printStackTrace();
		}
		return orders;
	}

	public static List<OrderTO> sortTheListOfOrders(List<String> orders) {
		final String METHOD_NAME = "sortTheListOfOrders";
		List<OrderTO> unsortMap = new ArrayList<OrderTO>();
		orders.remove(0);
		for (String order : orders) {
			if (!order.isEmpty()) {
				String[] st = order.split("\\t");
				try {
				Integer dateValue=new Integer(st[st.length - 1]);
				unsortMap.add(new OrderTO(st[0],dateValue ));
				}catch(NumberFormatException ne) {
					OrderTO ord = new OrderTO(st[0],0);
					ord.setFormattedOrderDate("Invalid Data");
					unsortMap.add(ord);
				}
				
			}
		}
		//System.out.println("Unsort data......");
		//printunsortedData(unsortMap);

		//System.out.println("\nSorted data......");
		List<OrderTO> sortedMap = sortByOrderTime(unsortMap);

		//printSortedMap(sortedMap);
		return sortedMap;

	}

	private static void printunsortedData(List<OrderTO> unsortMap) {
		for (OrderTO entry : unsortMap) {
			System.out.println(
					"order item : " + entry.getItemName() + " Unformatted Order Time : " + entry.getOrderTime());
		}
	}

	private static void printSortedMap(List<OrderTO> map) {
		for (OrderTO entry : map) {
			System.out
					.println("order item : " + entry.getItemName() + " Order Time : " + entry.getFormattedOrderDate());
		}
	}

	private static List<OrderTO> sortByOrderTime(List<OrderTO> unsortMap) {
		// Sort list with Collections.sort(), provide a custom Comparator
		Collections.sort(unsortMap, new Comparator<OrderTO>() {
			public int compare(OrderTO arg0, OrderTO arg1) {
				int timeCompare = (arg0.getOrderTime()).compareTo(arg1.getOrderTime());
				if (timeCompare != 0) {
					return timeCompare;
				} else {
					return (arg0.getItemName()).compareToIgnoreCase(arg1.getItemName());
				}
			}
		});

		// format the order time
		for (OrderTO entry : unsortMap) {
			if(null == entry.getFormattedOrderDate()) {
			LocalDateTime dateTime = LocalDateTime.ofEpochSecond(entry.getOrderTime(), 0, ZoneOffset.UTC);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d,yyyy h:mm,a", Locale.ENGLISH);
			String formattedDate = dateTime.format(formatter);
			if(dateTime.isBefore(LocalDateTime.now())){
			entry.setFormattedOrderDate(formattedDate);
			}else {
			entry.setFormattedOrderDate("Future Order:"+formattedDate);	
			}
			
		 }
		}
		return unsortMap;
	}

	public static Boolean writeDestinationFile(List<OrderTO> formattedOrders, String loc) {
		final String METHOD_NAME = "writeDestinationFile";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMMddyyyyHHmmss", Locale.ENGLISH);
		LocalDateTime dateTime = LocalDateTime.now();
		//
		String filename = "HumanReadableFile_";
		File f2 = new File(loc);
		if(!f2.isDirectory() && f2.exists() && loc.substring(loc.indexOf(".")+1, (loc.length())).equals("txt")) {
    			filename=loc.substring(0, (loc.indexOf(".")));
		}else {
			filename=loc + "/" + filename;
		}
    		filename=filename+dateTime.format(formatter);
    		System.out.println("Destination File:" +filename);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter( filename);

			writer.print("Order Name");
			writer.print("\t\t\t");
			writer.println("Date & Time of Order");
			writer.println();
			for (OrderTO order : formattedOrders) {
				writer.print(order.getItemName());
				writer.print("\t\t\t");
				writer.println(order.getFormattedOrderDate());
				writer.println();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println(CLASS_NAME + ":" + METHOD_NAME);
			System.out.println(
					"Error occurred while writing the destination file");
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public static Boolean validateUserInput(String srcFile, String destFile) {
		final String METHOD_NAME = "validateUserInput";
	 	 Boolean isValid = false; 
	    	File f1 = new File(srcFile);
	    	File f2 = new File(destFile);
	    	if(srcFile != null && !srcFile.trim().isEmpty() && destFile != null && !destFile.trim().isEmpty()) {
	    	if(!f1.exists()) { 
	    		System.out.println("Source file doesn't exist or given location is invalid,Please try again.");
	    		return isValid;
	   
	    	}else if(!srcFile.substring(srcFile.indexOf(".")+1, (srcFile.length())).equals("txt")) {
	    		System.out.println("Source file format is invalid ,Please try again.");
    			return isValid;
	    	}
	    	if(!f2.isDirectory()) {
	    		if(!f2.exists()) {
	    		System.out.println("Destination file doesn't exist or given location is invalid or file,Please try again.");
	    		return isValid;
	    		}else if(!destFile.substring(destFile.indexOf(".")+1, (destFile.length())).equals("txt")) {
	    			System.out.println("Destination file format is invalid,Please try again.");
	    			return isValid;
	    		} 
	    	}
	    	isValid=true;
	    	}else {
	    		System.out.println("Source/Destination file can not be empty or null,Please try again.");
	    	}
	    	return isValid;
	}

}
