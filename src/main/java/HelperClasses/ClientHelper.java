package HelperClasses;

import entity.Client;
import entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

public class ClientHelper {
    private SessionFactory sessionFactory;

    public ClientHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Client> getClientList(){

        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();
        session.get(Client.class, 1L); // получение объекта по id
        // этап подготовки запроса
        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
        CriteriaQuery cq = cb.createQuery(Client.class);
        Root<Client> root = cq.from(Client.class);// первостепенный, корневой entity (в sql запросе - from)

        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();

        cq.select(root);// необязательный оператор, если просто нужно получить все значения
        // этап выполнения запроса
        Query query = session.createQuery(cq);
        List<Client> clientList = query.getResultList();
        session.close();
        return clientList;

    }

    public Client getClient(String name){
        return null;
    }

    public Client addClient(Client client){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        session.close();
        return client;
    }
}
