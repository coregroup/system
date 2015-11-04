package repositories.sessions.impl;

import java.util.List;

import models.course.Session;
import repositories.sessions.SessionRepository;

import com.avaje.ebean.Ebean;

public class SessionRepositoryImpl implements SessionRepository {

	@Override
	public void save(Session session) {
		Ebean.save(session);
	}

	@Override
	public void delete(Session session) {
		Ebean.delete(session);
	}

	@Override
	public void update(Session session) {
		Ebean.update(session);
	}

	@Override
	public List<Session> findAll() {
		List<Session> sessions =  Ebean.find(Session.class).findList();
		return sessions;
	}

	@Override
	public Session findById(Long id) {
		Session session = Ebean.find(Session.class).where().eq("id", id.toString()).findUnique();
		return session;
	}

}
