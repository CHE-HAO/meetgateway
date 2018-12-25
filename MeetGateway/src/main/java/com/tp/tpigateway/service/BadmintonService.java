package com.tp.tpigateway.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.tpigateway.dao.EventDaoImpl;
import com.tp.tpigateway.dao.MemberDaoImpl;
import com.tp.tpigateway.model.Event;
import com.tp.tpigateway.model.Member;
import com.tp.tpigateway.util.PropUtils;

@Service
@Transactional
public class BadmintonService {

	private static final Logger log = LoggerFactory.getLogger(BadmintonService.class);
	
	private static final String badmintonEventType = PropUtils.getProperty("event.type.badminton");
	
	@Autowired
	private EventDaoImpl eventDao;
	
	@Autowired
	private MemberDaoImpl memberDao;

	public boolean createEvent(String name, String time) {
		try {
			Event e = new Event();
			e.setEventName(name);
			e.setEventTime(time);
			e.setEventType(badmintonEventType);
			e.setInsertTime(new Timestamp(System.currentTimeMillis()));
			return eventDao.addEvent(e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}
	
	public boolean deleteEvent(String name, String time) {
		try {
			Event e = eventDao.queryLastestEvent(badmintonEventType);
			memberDao.deleteMembers(e.getId());
			eventDao.delete(e);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}
	
	public JSONObject queryEvent() {
		JSONObject j = new JSONObject();
		Event e = eventDao.queryLastestEvent(badmintonEventType);
		List<Member> members = memberDao.queryMembers(e.getId());
		j.put("event", e);
		j.put("member", members);
		return j;
	}
	
	public boolean joinMember(String name) {
		Event e = eventDao.queryLastestEvent(badmintonEventType);
		Member m = new Member();
		m.setMemberName(name);
		m.setJoinEvent(e.getId());
		m.setInsertTime(new Timestamp(System.currentTimeMillis()));
		return memberDao.addMember(m);
	}
	
	public boolean exitMember(String name, Long joinEvent) {
		Member m = memberDao.getMember(name, joinEvent);
		memberDao.delete(m);
		return true;
	}
	
}
