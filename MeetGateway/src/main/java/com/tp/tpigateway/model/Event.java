package com.tp.tpigateway.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bot呼叫TPI回覆內容
 */
@Entity
@Table(name = "event")
public class Event implements Serializable {

	private static final long serialVersionUID = 5561399971100686552L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_name", length = 100)
	private String eventName;
    
    // yyyy/MM/dd HH:mm
    @Column(name = "event_time", length = 16)
	private String eventTime;
    
    @Column(name = "event_type", length = 100)
    private String eventType;
    
    @Column(name = "insert_time")
    private Timestamp insertTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public Timestamp getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}
