package com.account.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ACCOUNT")

public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	public Long account_number;
	public String first_name;
	public String last_name;
	public Long ssn;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date birth_date;
	public String account_type;
	public BigDecimal ammount;
	protected Account() {}
	public Account(Long account_number, String first_name, String last_name, Long ssn, Date birth_date,
			String account_type, BigDecimal ammount) {
		super();
		this.account_number = account_number;
		this.first_name = first_name;
		this.last_name = last_name;
		this.ssn = ssn;
		this.birth_date = birth_date;
		this.account_type = account_type;
		this.ammount = ammount;
	}
	//: first name, last name, ssn, birthdate, account type, amount.
}
