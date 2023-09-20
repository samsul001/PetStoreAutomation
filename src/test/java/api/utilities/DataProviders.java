package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//This dataprovideer metho returning all data from the excel sheet1.
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException{
		String xlPath = System.getProperty("user.dir")+"//testData//UserData.xlsx";
		XLUtility xl = new XLUtility(xlPath);
		
		int rowCount = xl.getRowCount("Sheet1");
		int colCount = xl.getColCount("Sheet1", 1);
		
		String[][] apiData = new String[rowCount][colCount];
		
		for(int i=1; i<=rowCount; i++) {
			for(int j=0; j<colCount; j++) {
				apiData[i-1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		return apiData;
	}
	
	//this dataprovider method returning only usernames column data from sheet1
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException {
		String xlPath = System.getProperty("user.dir")+"//testData//UserData.xlsx";
		XLUtility xl = new XLUtility(xlPath);
		
		int rowCount = xl.getRowCount("Sheet1");
		
		String[] getUserNames = new String[rowCount];
		
		for(int i=1; i<=rowCount; i++) {
			getUserNames[i-1] = xl.getCellData("sheet1", i, 1);
		}
		return getUserNames;
	}

}
