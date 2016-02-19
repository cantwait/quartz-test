package com.sc.data;

import java.io.Serializable;

public class JobRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private String cronExp;
	
	private String command;
	
	
	public JobRequest(){
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCronExp() {
		return cronExp;
	}


	public void setCronExp(String cronExp) {
		this.cronExp = cronExp;
	}


	public String getCommand() {
		return command;
	}


	public void setCommand(String command) {
		this.command = command;
	}
	
	
	
	
	

}
