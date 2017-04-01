package repositories.sessions;

import java.util.List;

import com.avaje.ebean.PagingList;

import models.course.Session;

public interface SessionRepository {
	
	public void save(Session session);
	
	public void delete(Session session);
	
	public void update(Session session);
	
	public List<Session> findAll();
	
	public Session findById(Long id);
	
	public PagingList<Session> page(int page, int pageSize, String sortBy, String order, String filter);

}
