package com.hjl.activity;

/**
 * Created by hjl on 2017/3/3.
 */
public class Prize {
	
	private String level;
	
	private String activityId;
	
	private String rule;
	
	public String getLevel() {
		return level;
	}
	
	public Prize setLevel(String level) {
		this.level = level;
		return this;
	}
	
	public String getActivityId() {
		return activityId;
	}
	
	public Prize setActivityId(String activityId) {
		this.activityId = activityId;
		return this;
	}
	
	public String getRule() {
		return rule;
	}
	
	public Prize setRule(String rule) {
		this.rule = rule;
		return this;
	}
}
