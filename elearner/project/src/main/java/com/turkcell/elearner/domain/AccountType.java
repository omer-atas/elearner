package com.turkcell.elearner.domain;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Entity

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account_types")
public class AccountType {

	@Id
	@Column(name = "id")
	private String accountTypeId;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "price")
	private double price;

	@Column(name = "description")
	private String description;

}
