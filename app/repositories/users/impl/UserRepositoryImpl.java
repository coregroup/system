/**
 * 
 */
package repositories.users.impl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import models.users.User;
import repositories.users.UserRepository;
import play.db.jpa.JPA;

/**
 * @author priscylla
 *
 */
public class UserRepositoryImpl implements UserRepository {

	@Override
	public User findByEmail(String email) {
		TypedQuery<User> query = JPA.em().createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
		try{
			return query.setParameter("email", email).getSingleResult();
		} catch (NoResultException e){
			return null;
		}
	}

	@Override
	public void update(User user) {
		JPA.em().merge(user);
		JPA.em().flush();
	}

}
