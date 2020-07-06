package com.mostafa.spring.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User {
 
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long clientDealId;
	private long clientId;
	private String clientName;
	private long dealId;
	private String dealName;
	private LocalDateTime date;
	private int accepted;
	private int refused;
	
	
	public User() {
	}

	public User( long clientId, String clientName, long dealId, String dealName, LocalDateTime date,
			int accepted, int refused) {
		this.clientId = clientId;
		this.clientName = clientName;
		this.dealId = dealId;
		this.dealName = dealName;
		this.date = date;
		this.accepted = accepted;
		this.refused = refused;
	}
	
	public long getClientDealId() {
		return clientDealId;
	}
	public void setClientDealId(long clientDealId) {
		this.clientDealId = clientDealId;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public long getDealId() {
		return dealId;
	}
	public void setDealId(long dealId) {
		this.dealId = dealId;
	}
	public String getDealName() {
		return dealName;
	}
	public void setDealName(String dealName) {
		this.dealName = dealName;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
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
