package repository;

import Queries.RequestTypeQueries;
import entity.Request;
import entity.RequestType;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class RequestTypeRepo {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public RequestType findRequestTypeById(String id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        RequestType foundRequestType = em.find(RequestType.class, id);
        em.getTransaction().commit();
        em.close();
        return foundRequestType;
    }

    public void insertRequestType(RequestType requestType){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(requestType);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteRequestType(RequestType requestType) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        if(em.contains(requestType))
            em.remove(requestType);
        else
            em.remove(em.merge(requestType));
        em.getTransaction().commit();
        em.close();
    }

    public List<RequestType> findAllRequestTypes(){
        EntityManager em=entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<RequestType> query=em.createQuery(RequestTypeQueries.FIND_ALL_REQUEST_TYPES,RequestType.class);
        List<RequestType> requestTypes= query.getResultList();
        em.getTransaction().commit();
        em.close();
        return requestTypes;
    }
}
