package com.ty.controller;

import java.util.List;

import com.ty.dao.Userdao;
import com.ty.dto.User;

public class Test {
	public static void main(String[] args) {
		/*User u=new User();
		u.setId(3);
		u.setName("pradeep");
		u.setEmail("pradeep@gmail.com");
		u.setPassword("p123kumar");
		u.setMobile(7311589562l);*/
		Userdao ud=new Userdao();
		/*int i=ud.saveUser(u);
		if(i!=0)
			System.out.println("inserted");
		else
			System.out.println("not inserted");*/
		/*User u1=ud.getDetailsById(3);
		System.out.println(u1.getId()+" "+u1.getName()+" "+u1.getEmail()+" "+u1.getPassword()+" "+u1.getMobile());
		*/
		/*
		 * List<User> al=ud.showAll(); for (User l : al) {
		 * System.out.println(l.getId()); System.out.println(l.getName());
		 * System.out.println(l.getEmail()); System.out.println(l.getPassword());
		 * System.out.println(l.getMobile()); System.out.println(); }
		 */
		User l = ud.validateUser("pradeep@gmail.com", "p123kumar");
		System.out.println(l.getId());
		System.out.println(l.getName());
		System.out.println(l.getEmail());
		System.out.println(l.getPassword());
		System.out.println(l.getMobile());
	}
}
