/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradeservice.model.helper;

import gradeservice.model.Subject;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import gradeservice.util.NewHibernateUtil;
import org.hibernate.SQLQuery;

/**
 *
 * @author rsawoniewski
 */
public class SubjectHelper {

    Session session = null;

    public SubjectHelper() {
        this.session = NewHibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List<Subject> getAllSubjects() {
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        List<Subject> entityList = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Subject");
            entityList = (List<Subject>) q.list();
        } catch (Exception e) {
            if (session.isOpen() == true) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session.isOpen() == true) {
                session.close();
            }
        }
        return entityList;
    }

    public Subject getSubjectById(int id) {
        Subject entity = null;
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Subject S where S.id =" + id);
            entity = (Subject) q.uniqueResult();
            //session.close();
        } catch (Exception e) {
            if (session.isOpen() == true) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session.isOpen() == true) {
                session.close();
            }
        }
        return entity;
    }

    public void save(String name) {
        Subject entity = null;
        entity.setName(name);

        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void save(Subject subject) {
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            session.save(subject);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.isOpen() == true) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session.isOpen() == true) {
                session.close();
            }
        }
    }

    public void update(String name, int id) {
        Subject entity = null;

        SubjectHelper subjectHelper = new SubjectHelper();
        entity = subjectHelper.getSubjectById(id);

        entity.setName(name);

        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Subject subject) {
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            session.update(subject);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.isOpen() == true) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session.isOpen() == true) {
                session.close();
            }
        }
    }

    public void delete(int entityId) {
        org.hibernate.Transaction tx = session.beginTransaction();
        SQLQuery q = session.createSQLQuery("delete from Subject where id = :entity_id");
        q.setParameter("entity_id", entityId);
        q.executeUpdate();
        session.getTransaction().commit();
    }

    public void delete(Subject subject) {
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            session.delete(subject);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.isOpen() == true) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session.isOpen() == true) {
                session.close();
            }
        }

    }

}
