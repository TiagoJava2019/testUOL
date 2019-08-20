package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author t.almeida
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tb_client")
public class Client extends AbstractEntity<Long> {

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "age", nullable = false)
	private int age;

	@Column(name = "address", nullable = false)
	private String address;

	/**
	 * construtor sem parâmetros
	 */
	public Client() {
		super();
	}

	/**
	 * construtor com parâmetros
	 * 
	 * @param name
	 * @param age
	 * @param address
	 */
	public Client(String name, int age, String address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}