package com.calculator;

public class Token {
	private String value;
	private TokenTypes type;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public TokenTypes getType() {
		return type;
	}
	public void setType(TokenTypes type) {
		this.type = type;
	}
}
