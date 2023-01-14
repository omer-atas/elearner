package com.turkcell.elearner.application.features.accountTypes.commands.create;
// Manager gibi ama sadece bir metod için yapıyo ( her metot alt sınıfa ayrılmış gibi)

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turkcell.elearner.domain.AccountType;
import com.turkcell.elearner.persistence.AccountTypeRepository;

@Component
public class AccountTypeCreatedEventsHandler {

	private AccountTypeRepository accountTypeRepository;

	@Autowired
	public AccountTypeCreatedEventsHandler(AccountTypeRepository accountTypeRepository) {
		this.accountTypeRepository = accountTypeRepository;
	}

	@EventHandler
	public void on(AccountTypeCreatedEvent accountTypeCreatedEvent) {
		
		// business codes - database calculate

		AccountType accountType = new AccountType();
		BeanUtils.copyProperties(accountTypeCreatedEvent, accountType);

		this.accountTypeRepository.save(accountType);
	}
}
