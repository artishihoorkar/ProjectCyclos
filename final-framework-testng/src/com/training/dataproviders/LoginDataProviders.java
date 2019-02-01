package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.LoginBean;
import com.training.bean.MemberDetailsBean;
import com.training.dao.CyclosDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;

public class LoginDataProviders {

	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<LoginBean> list = new CyclosDAO().getLogins(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(LoginBean temp : list){
			Object[]  obj = new Object[2]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.getPassword();
			
			result[count ++] = obj; 
		}
		
		
		return result;
	}
	
	@DataProvider(name = "db-inputsforpermissiongrp") //Test data for CYTC_067
	public Object [][] getDBDataPermissionGrp() {

		List<MemberDetailsBean> list = new CyclosDAO().getMemberDetails(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(MemberDetailsBean temp : list){
			Object[]  obj = new Object[3]; 
			obj[0] = temp.getMemberLogin(); 
			obj[1] = temp.getPermissionGroup();
			obj[2] = temp.getComments();
			result[count ++] = obj; 
		}
		
		
		return result;
	}
	
	@DataProvider(name = "member-input") //Test data for CYTC_066
	public Object[][] getExcelDataMemberInput(){
		String fileName ="C:/Users/IBM_ADMIN/Desktop/Cucumber Framework/SeleniumTraining/Project Cyclos/TestWorkSheet-Week3.xlsx"; 
		String sheetName = "CYCTD_004";
		return new ApachePOIExcelRead().getExcelContent(fileName,sheetName); 
	}
	
	@DataProvider(name = "advert-list") //Test data for CYTC_068
	public Object[][] getExcelDataAvertList(){
		String fileName ="C:/Users/IBM_ADMIN/Desktop/Cucumber Framework/SeleniumTraining/Project Cyclos/TestWorkSheet-Week3.xlsx";
		String sheetName = "CYCTD_005";
		return new ApachePOIExcelRead().getExcelContent(fileName,sheetName); 
	}
	@DataProvider(name = "advert-invalid-test") //Test data for CYTC_070
	public Object[][] getExcelDataInvalidAvertList(){
		String fileName ="C:/Users/IBM_ADMIN/Desktop/Cucumber Framework/SeleniumTraining/Project Cyclos/TestWorkSheet-Week3.xlsx";
		String sheetName = "CYCTD_006";
		return new ApachePOIExcelRead().getExcelContent(fileName,sheetName); 
	}
	
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:/Users/IBM_ADMIN/Desktop/Cucumber Framework/SeleniumTraining/Project Cyclos/TestSheetWeek3.xls", "CYCTD_005"); 
	}
}
