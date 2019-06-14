package com.org.cap.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.cap.dto.Account;

public class AccountRegistrationReflection {
	@Test
	public void whenNonPublicField_thenReflectionTestUtilsSetField() {
	    Account act = new Account();
	    ReflectionTestUtils.setField(act, "account_num", 1);
	  
	    assertTrue(act.getAccount_num()==(1));
	}
}
