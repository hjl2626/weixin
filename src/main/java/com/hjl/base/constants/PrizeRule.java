package com.hjl.base.constants;

/**
 * Created by hjl on 2017/3/3.
 */
public enum PrizeRule {
	
	RULE_1("1", "1"),
	RULE_2("1", "2");
	
	PrizeRule(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	private String key;
	
	public String getValue() {
		return value;
	}
	
	private String value;
	
	
	public String getKey() {
		return key;
	}
	
	@Override
	public String toString() {
		return "PrizeRule{" +
				"key='" + key + '\'' +
				", value='" + value + '\'' +
				"} " + super.toString();
	}
}
