package com.tp.tpigateway.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.tp.tpigateway.model.Event;
import com.tp.tpigateway.util.AbstractDAO;

public class EventDaoImpl extends AbstractDAO<Long, Event> {
	
	public boolean addEvent(Event e) {
		persist(e);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public Event queryLastestEvent(String eventType) {
		List<Event> e = getSession().createQuery("FROM Event e WHERE e.eventType = :eventType ORDER BY e.insertTime DESC LIMIT 1").setString("eventType", eventType).list();
		if (CollectionUtils.isNotEmpty(e)) {
			return e.get(0);
		}
		return null;
	}
	
}
