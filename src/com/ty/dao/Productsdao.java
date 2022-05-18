package com.ty.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.ty.dto.Products;
import com.ty.util.AES;
import com.ty.util.AppConstants;
import com.ty.util.ConnectionObject;

public class Productsdao {
	Scanner sc=new Scanner(System.in);
	public void addProduct(Products p) {
		Connection con=ConnectionObject.getConnection();
		try {
			PreparedStatement ps=con.prepareStatement(sc.nextLine());
			ps.setInt(1, p.getPid());
			ps.setString(2, AES.encrypt(p.getPname(), AppConstants.SECRET));
			ps.setInt(3, p.getPrice());
			ps.execute();
			System.out.println("data inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public Products getProductById() {
		Connection con=ConnectionObject.getConnection();
		try {
			PreparedStatement ps=con.prepareStatement("select * from products where pid="+sc.nextInt());
			ResultSet res=ps.executeQuery();
			if(res.next())
			{
				Products p=new Products();
				p.setPid(res.getInt(1));
				p.setPname(AES.decrypt(res.getString(2), AppConstants.SECRET));
				p.setPrice(res.getInt(3));
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
