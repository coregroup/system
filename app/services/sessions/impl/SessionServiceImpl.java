/**
 * 
 */
package services.sessions.impl;

import java.util.List;

import com.avaje.ebean.PagingList;

import models.course.Session;
import models.users.Student;
import repositories.sessions.SessionRepository;
import repositories.sessions.impl.SessionRepositoryImpl;
import services.sessions.SessionService;

/**
 * @author priscylla
 *
 */
public class SessionServiceImpl implements SessionService {
	
	private SessionRepository repository;
	
	public SessionServiceImpl() {
		repository = new SessionRepositoryImpl();
	}

	/* (non-Javadoc)
	 * @see services.sessions.SessionService#save(models.course.Session)
	 */
	@Override
	public void save(Session session) {
		repository.save(session);
	}

	/* (non-Javadoc)
	 * @see services.sessions.SessionService#delete(models.course.Session)
	 */
	@Override
	public void delete(Session session) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see services.sessions.SessionService#update(models.course.Session)
	 */
	@Override
	public void update(Session session) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see services.sessions.SessionService#findAll()
	 */
	@Override
	public List<Session> findAll() {
		return repository.findAll();
	}

	/* (non-Javadoc)
	 * @see services.sessions.SessionService#findById(java.lang.Long)
	 */
	@Override
	public Session findById(Long id) {
		return repository.findById(id);
	}

	/* (non-Javadoc)
	 * @see services.sessions.SessionService#findByStudent(models.users.Student)
	 */
	@Override
	public Session findByStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingList<Session> page(int page, int pageSize, String sortBy, String order, String filter) {
		return repository.page(page, pageSize, sortBy, order, filter);
	}

}
