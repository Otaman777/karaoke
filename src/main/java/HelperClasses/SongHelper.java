package HelperClasses;

import entity.Group;
import entity.Song;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class SongHelper {
    private SessionFactory sessionFactory;

    public SongHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Song> getSongList(){
        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();
        session.get(Song.class, 1L); // получение объекта по id
        // этап подготовки запроса
        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
        CriteriaQuery cq = cb.createQuery(Song.class);
        Root<Song> root = cq.from(Song.class);// первостепенный, корневой entity (в sql запросе - from)
        cq.select(root);// необязательный оператор, если просто нужно получить все значения
        // этап выполнения запроса
        Query query = session.createQuery(cq);
        List<Song> songList = query.getResultList();
        session.close();
        return songList;
    }
    public Song getSong(String name){
        return null;
    }
}
