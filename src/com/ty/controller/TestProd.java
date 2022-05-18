package com.ty.controller;

import com.ty.dao.Productsdao;
import com.ty.dto.Products;

public class TestProd {
	public static void main(String[] args) {
//		Products p=new Products();
//		p.setPid(12);
//		p.setPname("shoes");
//		p.setPrice(550);
//		Productsdao pa=new Productsdao();
//		pa.addProduct(p);
		Productsdao p=new Productsdao();
		Products p1=p.getProductById();
		System.out.println(p1);
	}
}
