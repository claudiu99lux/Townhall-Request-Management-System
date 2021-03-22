package repository;

import Queries.UserQueries;
import entity.Address;
import entity.User;

import javax.persistence.*;

public class UserRepo {
	
	private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
	
	public void insertNewUser(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	public void updateUser(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();
	}

	public void deleteUser(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		if(em.contains(user))
			em.remove(user);
		else
			em.remove(em.merge(user));
		em.getTransaction().commit();
		em.close();
	}

	public User findUserById(String id){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		User foundUser = em.find(User.class, id);
		em.getTransaction().commit();
		em.close();
		return foundUser;
	}

	public User findUserByEmail(String email){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<User> query = em.createQuery(UserQueries.FIND_USER_BY_EMAIL, User.class);
		query.setParameter("user_email", email);
		User user;
		try {
			user = query.getSingleResult();
		}catch(Exception e){
			user = null;
		}
		em.close();
		return user;
	}
}
