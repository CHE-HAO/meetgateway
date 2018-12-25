package com.tp.tpigateway.dao;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.tp.tpigateway.model.Member;
import com.tp.tpigateway.util.AbstractDAO;

public class MemberDaoImpl extends AbstractDAO<Long, Member> {
	
	public boolean addMember(Member member) {
		persist(member);
		return true;
	}
	
	public boolean deleteMembers(Long eventId) {
		getSession().createQuery("DELETE FROM Member m WHERE m.joinEvent = :joinEvent").setLong("joinEvent", eventId).executeUpdate();
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public Member getMember(String name, Long eventId) {
		List<Member> member = getSession().createQuery("FROM Member m WHERE m.joinEvent = :joinEvent").setLong("joinEvent", eventId).list();
		if (CollectionUtils.isNotEmpty(member)) {
			return member.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Member> queryMembers(Long eventId) {
		return getSession().createQuery("FROM Member m WHERE m.joinEvent = :joinEvent").setLong("joinEvent", eventId).list();
	}
}
