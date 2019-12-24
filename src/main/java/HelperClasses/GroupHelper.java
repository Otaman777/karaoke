package HelperClasses;

import entity.Disk;
import entity.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GroupHelper {
    private SessionFactory sessionFactory;

    public GroupHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Group> getGroupList(){
        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();
        session.get(Group.class, 1L); // получение объекта по id
        // этап подготовки запроса
        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
        CriteriaQuery cq = cb.createQuery(Group.class);
        Root<Group> root = cq.from(Group.class);// первостепенный, корневой entity (в sql запросе - from)
        cq.select(root);// необязательный оператор, если просто нужно получить все значения
        // этап выполнения запроса
        Query query = session.createQuery(cq);
        List<Group> groupList = query.getResultList();
        session.close();
        return groupList;
    }
    public Group getGroup(String name){
        return null;
    }

}
