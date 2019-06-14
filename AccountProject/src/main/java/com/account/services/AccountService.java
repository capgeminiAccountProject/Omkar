package com.account.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.entity.Account;
import com.account.repository.AccountRepository;

@Service
public class AccountService {


	
	  @Autowired AccountRepository accountRepository;
	  
	  public Iterable<Account> findAll()
	  {
		 return accountRepository.findAll();
	  }
	  
	  public Account save(Account act)
	  {
		  return accountRepository.save(act);
	  }
}
