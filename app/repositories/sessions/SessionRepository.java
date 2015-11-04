package repositories.sessions;

import java.util.List;

import models.course.Session;

public interface SessionRepository {
	
	public void save(Session session);
	
	public void delete(Session session);
	
	public void update(Session session);
	
	public List<Session> findAll();
	
	public Session findById(Long id);

}
