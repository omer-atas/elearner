package com.turkcell.elearner.application.features.accountTypes.commands.create;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

//-> Bu bizim requestimizdir....
@Data

@Builder
public class CreateAccountTypeCommand {

	@TargetAggregateIdentifier
	private String accountTypeId;

	private String accountName;

	private double price;

	private String description;
}
