/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradeservice.model.helper;

import gradeservice.model.Student;
import java.util.List;
import org.hibernate.Query;
import gradeservice.util.NewHibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author rsawoniewski
 */
public class StudentHelper {

    Session session = null;

    public StudentHelper() {
        this.session = NewHibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List<Student> getAllStudents() {
        List<Student> entityList = null;
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Student");
            entityList = (List<Student>) q.list();
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

    public Student getStudentById(int id) {
        Student entity = new Student();
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Student S where S.id =" + id);
            entity = (Student) q.uniqueResult();

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

    public void save(String firstName, String lastName, String address) {
        Student entity = new Student();

        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        entity.setAddress(address);

        //NewHibernateUtil.getSessionFactory().openSession();
        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        //session.close();
    }

    public void save(Student student) {
        Student entity = student;
        List<Student> entityList = null;
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        try {
            //NewHibernateUtil.getSessionFactory().openSession();
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

    public void update(String firstName, String lastName, String address, int id) {
        Student entity = new Student();
        List<Student> entityList = null;
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        try {
            StudentHelper studentHelper = new StudentHelper();
            entity = studentHelper.getStudentById(id);

            entity.setFirstName(firstName);
            entity.setLastName(lastName);
            entity.setAddress(address);

            //org.hibernate.Transaction tx = session.beginTransaction();
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

    public void update(Student student) {
        List<Student> entityList = null;
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            session.update(student);
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

    public void delete(int id) {
        List<Student> entityList = null;
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            SQLQuery q = session.createSQLQuery("delete from Student where id = :student_id");
            q.setParameter("student_id", id);
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

    public void delete(Student student) {
        if (session.isOpen() == false) {
            NewHibernateUtil.getSessionFactory().openSession();
        }
        if (session.getTransaction().wasCommitted()) {
            session.getTransaction().commit();
        }
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            session.delete(student);
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
