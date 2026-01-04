package com.qa.opencart.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVUtils {

	private static final String CSV_PATH=".//src//test//resources//testdata//";
	
	public static Object[][] cvsData(String csvName){
		//String csvFile="C:\\Users\\kverma\\eclipse-workspace\\seleniumcore\\src\\test\\resources\\TestData\\TestData.csv" "";
		String csvFile=CSV_PATH+csvName+".csv";
		Object[][] data=null;
		try {
			CSVReader reader=new CSVReader(new FileReader(csvFile));
			try {
				List<String[]>rows=reader.readAll();
				reader.close();
				 data=new Object[rows.size()][];
				 
				for(int i=0;i<rows.size();i++) {
					data[i]=rows.get(i);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CsvException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
		
	}
	
}
