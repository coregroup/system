/**
 * 
 */
package repositories.users.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import models.users.Teacher;
import models.users.User;
import play.db.jpa.JPA;
import repositories.users.TeacherRepository;

/**
 * @author priscylla
 *
 */
public class TeacherRepositoryImpl implements TeacherRepository {

	@Override
	public void save(Teacher teacher) {
		JPA.em().persist(teacher);
	}

	@Override
	public void delete(Teacher teacher) {
//		teacher.delete();
	}

	@Override
	public void update(Teacher teacher) {
		JPA.em().merge(teacher);
		JPA.em().flush();
	}

	@Override
	public Teacher exists(String email, String password) {
		TypedQuery<User> query = JPA.em().createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
		try{
			User user = query.setParameter("email", email).getSingleResult(); 
			if(user instanceof Teacher)
			return (Teacher) user;
		} catch (NoResultException e){
			return null;
		}
		return null;
	}

}
