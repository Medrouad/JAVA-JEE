package fr.papyfinance.com.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fr.papyfinance.com.beans.User;
import fr.papyfinance.com.resources.HibernateUtil;

@Stateless
public class UserDao {
  private SessionFactory sessionFactory;

  public UserDao() {
    sessionFactory = HibernateUtil.getSessionFactory();
  }

  public UserDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public boolean create(User o) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      session.save(o);
      session.getTransaction().commit();
    } catch (Exception e) {
      return false;
    } finally {
      session.close();
    }
    return true;
  }

  public User getByEmail(String email) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    User o = (User) session.createQuery("from User where email = :semail").setParameter("semail", email).uniqueResult();
    session.getTransaction().commit();
    session.close();
    return o;
  }

  public User getByLogin(String login) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    User o = (User) session.createQuery("from User where login = :slogin").setParameter("slogin", login).uniqueResult();
    session.getTransaction().commit();
    session.close();
    return o;
  }

  public User getById(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    User o = (User) session.createQuery("from User where id = :sid").setParameter("sid", id).uniqueResult();
    session.getTransaction().commit();
    session.close();
    return o;
  }

  @SuppressWarnings("unchecked")
  public ArrayList<User> getAllInvest() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ArrayList<User> o = (ArrayList<User>) session.createQuery("from User where role.name = 'Investisseur'").list();
    session.getTransaction().commit();
    session.close();
    return o;
  }

  @SuppressWarnings("unchecked")
  public ArrayList<User> getAllCompanyMember() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ArrayList<User> o = (ArrayList<User>) session.createQuery("from User where role.name = 'Membre soci�t�'").list();
    session.getTransaction().commit();
    session.close();
    return o;
  }

  public boolean update(User newUser) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      session.update(newUser);
      session.getTransaction().commit();
    } catch (Exception e) {
      return false;
    } finally {
      session.close();
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  public List<User> getAll() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<User> users = session.createQuery("from User").list();
    session.getTransaction().commit();
    session.close();
    return users;
  }

  @SuppressWarnings("unchecked")
  public ArrayList<User> getAllWithAttribute(String attribute) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ArrayList<User> c = (ArrayList<User>) session
        .createQuery("from User where fname like :uattribute OR lname like :uattribute OR email like :uattribute OR role.name like :uattribute OR company.name like :uattribute")
        .setParameter("uattribute", "%" + attribute + "%").list();
    session.getTransaction().commit();
    session.close();
    return c;
  }
}
