/**
 * 
 */
package repositories.users.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import models.users.Student;
import models.users.User;
import play.db.jpa.JPA;
import repositories.users.StudentRepository;

/**
 * @author priscylla
 *
 */
public class StudentRepositoryImpl implements StudentRepository {

	
	@Override
	public void save(Student student) {
//		student.save();
		JPA.em().persist(student);
	}
	
	@Override
	public void delete(Student student) {
//		student.delete();
	}

	@Override
	public void update(Student student) {
		JPA.em().merge(student);
		JPA.em().flush();
	}


	@Override
	public Student exists(String email, String password) {
		TypedQuery<User> query = JPA.em().createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
		try{
			User user = query.setParameter("email", email).getSingleResult(); 
			if(user instanceof Student)
			return (Student) user;
		} catch (NoResultException e){
			return null;
		}
		return null;
	}

}
