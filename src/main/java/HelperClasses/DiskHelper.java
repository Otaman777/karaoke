package HelperClasses;

import entity.Disk;
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

public class DiskHelper {

    private SessionFactory sessionFactory;

    public DiskHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Disk> getDiskList(){
        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();
        session.get(Disk.class, 1L); // получение объекта по id
        // этап подготовки запроса
        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
        CriteriaQuery cq = cb.createQuery(Disk.class);
        Root<Disk> root = cq.from(Disk.class);// первостепенный, корневой entity (в sql запросе - from)
        cq.select(root);// необязательный оператор, если просто нужно получить все значения
        // этап выполнения запроса
        Query query = session.createQuery(cq);
        List<Disk> diskList = query.getResultList();
        session.close();
        return diskList;
    }
    public Disk getDisk(String name){
        return null;
    }

}
