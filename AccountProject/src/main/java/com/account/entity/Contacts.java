package com.account.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Contacts {

	private String name;
	private String phone;
}
