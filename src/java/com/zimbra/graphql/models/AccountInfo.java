package com.zimbra.graphql.models;

public class AccountInfo {
	private final String name;

	public AccountInfo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
