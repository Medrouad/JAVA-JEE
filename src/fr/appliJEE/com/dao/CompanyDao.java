package fr.papyfinance.com.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import fr.papyfinance.com.beans.Company;
import fr.papyfinance.com.resources.HibernateUtil;

@Stateless
public class CompanyDao {
  private SessionFactory sessionFactory;

  public CompanyDao() {
    sessionFactory = HibernateUtil.getSessionFactory();
  }

  public CompanyDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public boolean create(Company c) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      session.save(c);
      session.getTransaction().commit();
    } catch (ConstraintViolationException e) {
      return false;
    } finally {
      session.close();
    }
    return true;
  }

  public boolean update(Company c) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    try {
      session.update(c);
      session.getTransaction().commit();
    } catch (Exception e) {
      return false;
    } finally {
      session.close();
    }
    return true;
  }

  public Company getByName(String name) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Company c = (Company) session.createQuery("from Company where name = :cname").setParameter("cname", name).uniqueResult();
    session.getTransaction().commit();
    session.close();
    return c;
  }

  public Company getById(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Company c = (Company) session.createQuery("from Company where id = :cid").setParameter("cid", id).uniqueResult();
    session.getTransaction().commit();
    session.close();
    return c;
  }

  @SuppressWarnings("unchecked")
  public List<Company> getAll() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<Company> companies = session.createQuery("from Company").list();
    session.getTransaction().commit();
    session.close();
    return companies;
  }

  @SuppressWarnings("unchecked")
  public List<Company> getAllWithoutNone() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<Company> companies = session.createQuery("from Company c where c.name <> 'Aucune soci�t�'").list();
    session.getTransaction().commit();
    session.close();
    return companies;
  }

  @SuppressWarnings("unchecked")
  public ArrayList<Company> getAllCompanyNotAccredit() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ArrayList<Company> c = (ArrayList<Company>) session.createQuery("from Company where is_confirmed = false").list();
    session.getTransaction().commit();
    session.close();
    return c;
  }

  @SuppressWarnings("unchecked")
  public ArrayList<Company> getAllWithAttribute(String attribute) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ArrayList<Company> c = (ArrayList<Company>) session.createQuery("from Company where name like :cattribute OR website like :cattribute ").setParameter("cattribute", "%" + attribute + "%").list();
    session.getTransaction().commit();
    session.close();
    return c;
  }

  @SuppressWarnings("unchecked")
  public ArrayList<Company> getAllForInvestor(String name, String sector, long revenue, long workforce) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ArrayList<Company> c = (ArrayList<Company>) session
        .createQuery("from Company where name like :cname and sector.name like :csector and cast(revenue as long) >= :crevenue and cast(workforce as long) >= :cworkforce")
        .setParameter("cname", "%" + name + "%").setParameter("csector", "%" + sector + "%").setParameter("crevenue", revenue).setParameter("cworkforce", workforce).list();
    session.getTransaction().commit();
    session.close();
    return c;
  }

}
