package com.turkcell.elearner.application.features.accountTypes.commands.create;

import org.axonframework.commandhandling.CommandHandler;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

// Veri tabanına kaydetme eventini yakalama..... örnek
@Aggregate // command oluşturulduğu anda devreye girilir.. ( @CommandHandler devreye
		   // girer....)
public class CreateAccountTypeAggregate {
	// Buraya gelecek alanları tanımlıyoruz. - Sana söyle alanlar gelecek -
	@AggregateIdentifier
	private String accountTypeId;

	private String accountName;

	private double price;

	private String description;

	public CreateAccountTypeAggregate() {
	}

	@CommandHandler // Aggregate oluşturulduğunda burayı arar buradaki kodları çalıştırır.
	public CreateAccountTypeAggregate(CreateAccountTypeCommand createAccountTypeCommand) {

		// evrensel business codes - loglama ...

		AccountTypeCreatedEvent accountTypeCreatedEvent = new AccountTypeCreatedEvent();

		BeanUtils.copyProperties(createAccountTypeCommand, accountTypeCreatedEvent);

		AggregateLifecycle.apply(accountTypeCreatedEvent); // 1- eventhandler aranılıp bulunup oranın içerisindeki
															// işlemi gerçekleştiriyor..
		// 2- service buss gateway
		// Her iki durumda da aynı eventHandler ve aynı parametre alana bakar..
	}

	@EventSourcingHandler
	public void on(AccountTypeCreatedEvent accountTypeCreatedEvent) {

		// event sourceing code

		this.accountTypeId = accountTypeCreatedEvent.getAccountTypeId();
		// ve diğer attribüteler burda set edilir istediklerimiz service bus'a
		// gönderir...
		this.accountName = accountTypeCreatedEvent.getAccountName();
		this.description = accountTypeCreatedEvent.getDescription();
		this.price = accountTypeCreatedEvent.getPrice();
	}

}
