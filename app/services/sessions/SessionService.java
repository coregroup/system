package services.sessions;

import java.util.List;

import models.course.Session;
import models.users.Student;

public interface SessionService {
	
	public void save(Session session);
	
	public void delete(Session session);
	
	public void update(Session session);
	
	public List<Session> findAll();
	
	public Session findById(Long id);
	
	public Session findByStudent(Student student);

}
