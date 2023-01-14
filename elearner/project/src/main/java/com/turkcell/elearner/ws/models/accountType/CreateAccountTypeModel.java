package com.turkcell.elearner.ws.models.accountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountTypeModel {

	private String accountName;

	private double price;

	private String description;
}
