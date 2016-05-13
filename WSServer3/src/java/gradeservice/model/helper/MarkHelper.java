/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradeservice.model.helper;

import gradeservice.model.Mark;
import gradeservice.model.Student;
import gradeservice.model.Subject;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import gradeservice.util.NewHibernateUtil;
import java.util.ArrayList;
import org.hibernate.SQLQuery;

/**
 *
 * @author rsawoniewski
 */
public class MarkHelper {

    Session session = null;

    public MarkHelper() {
        this.session = NewHibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List<Mark> getAllMarks() {
        List<Mark> entityList = null;
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        try {
            if (session.isOpen() == false) {
                NewHibernateUtil.getSessionFactory().openSession();
            }
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Mark");
            entityList = (List<Mark>) q.list();
            tx.commit();
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

    public List<Mark> getAllMarksByStudentId(int id) {
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Mark S where S.student =" + id);
            //tx.commit();
            return (List<Mark>) q.list();
        } catch (Exception e) {
            if (session.isOpen() == true) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session.isOpen() == true) {
                session.close();
            }
        }
        return new ArrayList<Mark>();
    }

    public List<Mark> getAllMarksByStudentAndSubjectId(int student_id, int subject_id) {
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Mark S where S.student = " + student_id + " and S.subject = " + subject_id);
            return (List<Mark>) q.list();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return new ArrayList<Mark>();
    }

    public Mark getMarkById(int id) {
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        Mark entity = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Mark S where S.id =" + id);
            entity = (Mark) q.uniqueResult();
            tx.commit();
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

    public void save(String name, int subjectId, int studentId, double grade) {
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        Mark entity = null;
        try {
            StudentHelper studentHelper = new StudentHelper();
            Student student = studentHelper.getStudentById(studentId);
            SubjectHelper subjectHelper = new SubjectHelper();
            Subject subject = subjectHelper.getSubjectById(subjectId);

            entity.setName(name);
            entity.setStudent(student);
            entity.setSubject(subject);
            entity.setMark(grade);

            org.hibernate.Transaction tx = session.beginTransaction();
            session.save(entity);
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

    public void save(Mark mark) {
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            session.save(mark);
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

    public void update(String name, int subjectId, int studentId, double grade, int id) {
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        Mark entity = this.getMarkById(id);
        try {
            StudentHelper studentHelper = new StudentHelper();
            Student student = studentHelper.getStudentById(studentId);
            SubjectHelper subjectHelper = new SubjectHelper();
            Subject subject = subjectHelper.getSubjectById(subjectId);

            entity.setName(name);
            entity.setStudent(student);
            entity.setSubject(subject);
            entity.setMark(grade);

            org.hibernate.Transaction tx = session.beginTransaction();
            session.update(entity);
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

    public void update(Mark mark) {
        if (!session.isOpen()) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            session.update(mark);
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
        if (!session.isOpen()) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("delete from Mark where id = :entity_id");
            q.setParameter("entity_id", entityId);
            q.executeUpdate();
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

    public void delete(Mark mark) {
        if (!session.isOpen()) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            session.delete(mark);
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
