package HelperClasses;

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

public class OrderHelper {
    private SessionFactory sessionFactory;

    public OrderHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Order> getOrderList(){

        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();
        session.get(Order.class, 1L); // получение объекта по id
        // этап подготовки запроса
        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
        CriteriaQuery cq = cb.createQuery(Order.class);
        Root<Order> root = cq.from(Order.class);// первостепенный, корневой entity (в sql запросе - from)

        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();

        cq.select(root);// необязательный оператор, если просто нужно получить все значения
        // этап выполнения запроса
        Query query = session.createQuery(cq);
        List<Order> authorList = query.getResultList();
        session.close();
        return authorList;

    }

    public Order getOrder(String name){
        return null;
    }
}
