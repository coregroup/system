package services.sessions;

import java.util.List;

import com.avaje.ebean.PagingList;

import models.course.Session;
import models.users.Student;

public interface SessionService {
	
	public void save(Session session);
	
	public void delete(Session session);
	
	public void update(Session session);
	
	public List<Session> findAll();
	
	public Session findById(Long id);
	
	public Session findByStudent(Student student);
	
	public PagingList<Session> page(int page, int pageSize, String sortBy, String order, String filter);

}
