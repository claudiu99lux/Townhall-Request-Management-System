package repository;

import Queries.AddressQueries;
import Queries.UserQueries;
import entity.Address;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class AddressRepo {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertNewAddress(Address address) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(address);
        em.getTransaction().commit();
        em.close();
    }

    public void updateAddress(Address address){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(address);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteAddress(Address address){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        if(em.contains(address))
            em.remove(address);
        else
            em.remove(em.merge(address));
        em.getTransaction().commit();
        em.close();
    }

    public Address findAddressById(String id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Address foundAddress = em.find(Address.class, id);
        em.getTransaction().commit();
        em.close();
        return foundAddress;
    }

    public List<Address> findAddressByUserId(String userID){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Address> query = em.createQuery(AddressQueries.FIND_ADDRESS_BY_USER_ID, Address.class);
        query.setParameter("id", userID);
        List<Address> foundAddresses = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return foundAddresses;
    }
}
