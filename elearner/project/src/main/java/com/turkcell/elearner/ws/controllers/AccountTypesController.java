package com.turkcell.elearner.ws.controllers;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.elearner.application.features.accountTypes.commands.create.CreateAccountTypeCommand;
import com.turkcell.elearner.application.features.accountTypes.commands.update.UpdateAccountTypeCommand;
import com.turkcell.elearner.ws.models.accountType.CreateAccountTypeModel;
import com.turkcell.elearner.ws.models.accountType.UpdateAccountTypeModel;

@RestController
@RequestMapping("/accountTypes")
public class AccountTypesController {

	private CommandGateway commandGateway;

	public AccountTypesController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@PostMapping()
	public void CreateAccountType(@RequestBody CreateAccountTypeModel createAccountTypeModel) {

		CreateAccountTypeCommand createAccountTypeCommand = CreateAccountTypeCommand.builder()
				.price(createAccountTypeModel.getPrice()).accountName(createAccountTypeModel.getAccountName())
				.description(createAccountTypeModel.getDescription()).build();

		createAccountTypeCommand.setAccountTypeId(UUID.randomUUID().toString());

		this.commandGateway.sendAndWait(createAccountTypeCommand);
		// Bu requestten istek geldiğinde gider aggregate olan sınıfta @CommandaHandler
		// anotasyonu
		// ve createAccountTypeCommand parametresi
		// alan metodu arar ve o kısmı çalıştırır...
	}

	@PutMapping("/update")
	public void UpdateAccountType(@RequestBody UpdateAccountTypeModel updateAccountTypeModel) {

		UpdateAccountTypeCommand updateAccountTypeCommand = UpdateAccountTypeCommand.builder()
				.accountTypeId(updateAccountTypeModel.getAccountTypeId())
				.accountName(updateAccountTypeModel.getAccountName()).price(updateAccountTypeModel.getPrice())
				.description(updateAccountTypeModel.getDescription()).build();

		this.commandGateway.sendAndWait(updateAccountTypeCommand);
	}

}
