package repository;

import Queries.AddressQueries;
import Queries.RequestQueries;
import Queries.RequestTypeQueries;
import entity.Address;
import entity.Request;
import entity.RequestType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class RequestRepo {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void insertRequest(Request request){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(request);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteRequest(Request request) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Request r = em.find(Request.class, request.getId());
        em.remove(r);
        em.getTransaction().commit();
        em.close();
    }

    public void updateRequest(Request request){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(request);
        em.getTransaction().commit();
        em.close();
    }


    public List<Request> findRequestsByUserId(String user_id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Request> query = em.createQuery(RequestQueries.FIND_REQUESTS_BY_USER_ID, Request.class);
        query.setParameter("id", user_id);
        List<Request> foundRequests = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return foundRequests;
    }

    public List<Request> findRequestsByDate(LocalDate date){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Request> query = em.createQuery(RequestQueries.FIND_REQUESTS_BY_DATE, Request.class);
        query.setParameter("date", date);
        List<Request> foundRequests = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return foundRequests;
    }

    public List<Request> findAllRequests(){
        EntityManager em=entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Request> query=em.createQuery(RequestQueries.FIND_ALL_REQUESTS,Request.class);
        List<Request> requests= query.getResultList();
        em.getTransaction().commit();
        em.close();
        return requests;
    }

    public List<Request> findRequestsByAddressAndType(String addressID, String typeID){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Request> query = em.createQuery(RequestQueries.FIND_REQUESTS_BY_ADDR_AND_TYPE, Request.class);
        query.setParameter("addressID", addressID);
        query.setParameter("typeID", typeID);
        List<Request> foundRequests = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return foundRequests;
    }
}
