package com.tp.tpigateway.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member implements Serializable {
	
	private static final long serialVersionUID = -9222028906940865196L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_name", length = 100)
	private String memberName;

    @Column(name = "join_event")
    private Long joinEvent;
    
    @Column(name = "insert_time")
    private Timestamp insertTime;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Long getJoinEvent() {
		return joinEvent;
	}

	public void setJoinEvent(Long joinEvent) {
		this.joinEvent = joinEvent;
	}
    
	public Timestamp getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}
	
}
