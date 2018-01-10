package fr.papyfinance.com.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fr.papyfinance.com.beans.Sector;
import fr.papyfinance.com.resources.HibernateUtil;

@Stateless
public class SectorDao {
  private SessionFactory sessionFactory;

  public SectorDao() {
    sessionFactory = HibernateUtil.getSessionFactory();
  }

  public SectorDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public boolean create(Sector s) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      session.save(s);
      session.getTransaction().commit();
    } catch (Exception e) {
      return false;
    } finally {
      session.close();
    }
    return true;
  }

  public Sector getById(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Sector s = (Sector) session.createQuery("from Sector where id = :sid").setParameter("sid", id).uniqueResult();
    session.close();
    return s;
  }

  public Sector getByName(String name) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Sector s = (Sector) session.createQuery("from Sector where name = :sname").setParameter("sname", name).uniqueResult();
    session.close();
    return s;
  }

  @SuppressWarnings("unchecked")
  public List<Sector> getAll() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<Sector> sectors = session.createQuery("from Sector").list();
    session.close();
    return sectors;
  }
}