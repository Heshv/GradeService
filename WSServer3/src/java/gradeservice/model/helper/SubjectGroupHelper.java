/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradeservice.model.helper;

import gradeservice.model.Student;
import gradeservice.model.Subject;
import gradeservice.model.SubjectGroup;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import gradeservice.util.NewHibernateUtil;

/**
 *
 * @author rsawoniewski
 */
public class SubjectGroupHelper {

    Session session = null;

    public SubjectGroupHelper() {
        this.session = NewHibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List<SubjectGroup> getAllSubjectGroups() {
        List<SubjectGroup> entityList = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from SubjectGroup");
            entityList = (List<SubjectGroup>) q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entityList;
    }

    public SubjectGroup getSubjectGroupById(int id) {
        SubjectGroup entity = null;
        try {
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from SubjectGroup S where S.id =" + id);
            entity = (SubjectGroup) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public void save(String name, int subjectId, int studentId) {
        SubjectGroup entity = null;

        StudentHelper studentHelper = new StudentHelper();
        Student student = studentHelper.getStudentById(studentId);
        SubjectHelper subjectHelper = new SubjectHelper();
        Subject subject = subjectHelper.getSubjectById(subjectId);

        entity.setName(name);
        entity.setStudent(student);
        entity.setSubject(subject);

        org.hibernate.Transaction tx = session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void save(SubjectGroup subjectGroup) {
        session.save(subjectGroup);
        session.getTransaction().commit();
        session.close();
    }

    public void update(String name, int subjectId, int studentId) {
        SubjectGroup entity = null;

        StudentHelper studentHelper = new StudentHelper();
        Student student = studentHelper.getStudentById(studentId);
        SubjectHelper subjectHelper = new SubjectHelper();
        Subject subject = subjectHelper.getSubjectById(subjectId);

        entity.setName(name);
        entity.setStudent(student);
        entity.setSubject(subject);

        org.hibernate.Transaction tx = session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void update(SubjectGroup subjectGroup) {
        session.update(subjectGroup);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(int subjectGroupId) {
        SubjectGroup entity = null;

        SubjectGroupHelper subjectGroupHelper = new SubjectGroupHelper();
        entity = subjectGroupHelper.getSubjectGroupById(subjectGroupId);

        org.hibernate.Transaction tx = session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(SubjectGroup subjectGroup) {
        session.delete(subjectGroup);
        session.getTransaction().commit();
        session.close();
    }
}
