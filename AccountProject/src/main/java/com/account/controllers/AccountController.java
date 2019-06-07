package com.account.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.account.entity.Account;
import com.account.services.AccountService;

@RestController("/")
public class AccountController {

	
	
	  @Autowired AccountService accountService;
	  
	  @GetMapping(value="hello")
	    public String getHello() {
		  		
	        return "Hello!";
	    }

	  @GetMapping(value="listAll")
	  public ResponseEntity<Map<String, Object>>
	  getAccount() {
	  Map<String, Object> hmp=new HashMap<String, Object>();
	  hmp.put("accounts", accountService.findAll());
	  return ResponseEntity.ok(hmp); }
	  
	  @PutMapping(value="save") 
	  public ResponseEntity<Map<String, Object>> putAccount(@RequestBody Account act)
	  { 
		  act=accountService.save(act); 
		  Map<String, Object> hmp=new HashMap<String, Object>();
		  hmp.put("account_number", act.getAccount_number());
		  
		  return ResponseEntity.ok(hmp); 
		  }
	  
}
