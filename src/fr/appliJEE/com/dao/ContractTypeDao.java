package fr.papyfinance.com.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fr.papyfinance.com.beans.ContractType;
import fr.papyfinance.com.resources.HibernateUtil;

@Stateless
public class ContractTypeDao {
  private SessionFactory sessionFactory;

  public ContractTypeDao() {
    sessionFactory = HibernateUtil.getSessionFactory();
  }

  public ContractTypeDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public boolean create(ContractType o) {
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

  public ContractType getByName(String name) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ContractType o = (ContractType) session.createQuery("from ContractType where name = :cname").setParameter("cname", name).uniqueResult();
    session.getTransaction().commit();
    session.close();
    return o;
  }

  @SuppressWarnings("unchecked")
  public List<ContractType> getAll() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContractType> contractTypes = session.createQuery("from ContractType").list();
    session.getTransaction().commit();
    session.close();
    return contractTypes;
  }

  public ContractType getById(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ContractType c = (ContractType) session.createQuery("from ContractType where id = :cid").setParameter("cid", id).uniqueResult();
    session.getTransaction().commit();
    session.close();
    return c;
  }
}
