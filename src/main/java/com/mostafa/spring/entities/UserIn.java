package com.mostafa.spring.entities;



import com.opencsv.bean.CsvBindByName;

public class UserIn {
	
 @CsvBindByName
 private String client;
 @CsvBindByName
 private String deal; 
 @CsvBindByName
 private String hour;
 @CsvBindByName
 private int accepted;
 @CsvBindByName
 private int refused;
 
 
public UserIn() {
	super();
}

public UserIn(String client, String deal, String hour, int accepted, int refused) {
	this.client = client;
	this.deal = deal;
	this.hour = hour;
	this.accepted = accepted;
	this.refused = refused;
}

public String getClient() {
	return client;
}
public void setClient(String client) {
	this.client = client;
}
public String getDeal() {
	return deal;
}
public void setDeal(String deal) {
	this.deal = deal;
}
public String getHour() {
	return hour;
}
public void setHour(String hour) {
	this.hour = hour;
}
public int getAccepted() {
	return accepted;
}
public void setAccepted(int accepted) {
	this.accepted = accepted;
}
public int getRefused() {
	return refused;
}
public void setRefused(int refused) {
	this.refused = refused;
}

 


}
