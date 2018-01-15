package com.ibm.clientvantage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.groovy.runtime.StringGroovyMethods;

/*
 * 用@Entity 注解，这个类User 就表示数据库中的一个表，User 中的属性就会映射成数据库表中的字段。
 * */
@Entity
@Table(name="user")
public class User {
	@Id  
    @GeneratedValue(strategy = GenerationType.AUTO)  
    private int id;  
	@Column(name = "name")
	private String name;
	 @Column( name= "password")
	private String password;
	 @Column( name= "email")
    private String email;
	 @Column( name= "state")
    private int state;
	 @Column( name= "code")
    private String code;
	 public User() {//必须要选择一个无参数的构造方法
		 
	 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	
	 

}
