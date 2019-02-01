package com.training.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.training.bean.LoginBean;
import com.training.bean.MemberDetailsBean;
import com.training.connection.GetConnection;
import com.training.utility.LoadDBDetails;

// Data Access Object 
public class CyclosDAO {
	
	Properties properties; 
	
	public CyclosDAO() {
		 try {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/sql.properties");
			properties.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<LoginBean> getLogins(){
		String sql = properties.getProperty("get.logins"); 
		GetConnection gc  = new GetConnection(); 
		List<LoginBean> list = null;
		try {
			gc.ps1 = GetConnection.getMySqlConnection(LoadDBDetails.getDBDetails()).prepareStatement(sql); 
			list = new ArrayList<LoginBean>(); 
			
			gc.rs1 = gc.ps1.executeQuery(); 
			
			while(gc.rs1.next()) {
			
				LoginBean temp = new LoginBean(); 
				temp.setUserName(gc.rs1.getString(1));
				temp.setPassword(gc.rs1.getString(2));

				list.add(temp); 
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list; 
	}
	
	/*Added new method to connect to MySQL database package named "arti" using getter setter methods from MemberDetailsBean.java*/
	public List<MemberDetailsBean> getMemberDetails(){
		String sql = properties.getProperty("get.permissiondetails"); 
		GetConnection gc  = new GetConnection(); 
		List<MemberDetailsBean> list = null;
		try {
			gc.ps1 = GetConnection.getMySqlConnection(LoadDBDetails.getDBDetails()).prepareStatement(sql); 
			list = new ArrayList<MemberDetailsBean>(); 
			gc.rs1 = gc.ps1.executeQuery();
			while(gc.rs1.next()){
				MemberDetailsBean temp = new MemberDetailsBean(); 
				temp.setMemberLogin(gc.rs1.getString(1));
				temp.setPermissionGroup(gc.rs1.getString(2));
				temp.setComments(gc.rs1.getString(3));
				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list; 
	}
	public static void main(String[] args) {
		new CyclosDAO().getLogins().forEach(System.out :: println);
	}
	
	
}
