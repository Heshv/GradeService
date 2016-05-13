/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradeservice.webservices;

import gradeservice.model.Mark;
import gradeservice.model.Student;
import gradeservice.model.Subject;
import gradeservice.model.SubjectGroup;
import gradeservice.model.helper.MarkHelper;
import gradeservice.model.helper.StudentHelper;
import gradeservice.model.helper.SubjectGroupHelper;
import gradeservice.model.helper.SubjectHelper;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author rsawoniewski
 */
@WebService(serviceName = "NewWebService")
@Stateless()
public class NewWebService {

    /**
     * This is a sample web service operation
     *
     * @param id
     * @return
     */
    //Get by id
    @WebMethod(operationName = "getStudentById")
    public Student getStudentById(@WebParam(name = "id") int id) {
        StudentHelper helper = new StudentHelper();
        return helper.getStudentById(id);
    }

    @WebMethod(operationName = "getSubjectGroupById")
    public SubjectGroup getSubjectGroupById(@WebParam(name = "id") int id) {
        SubjectGroupHelper helper = new SubjectGroupHelper();
        return helper.getSubjectGroupById(id);
    }

    @WebMethod(operationName = "getMarkById")
    public Mark getMarkById(@WebParam(name = "id") int id) {
        MarkHelper helper = new MarkHelper();
        return helper.getMarkById(id);
    }

    @WebMethod(operationName = "getSubjectById")
    public Subject getSubjectById(@WebParam(name = "id") int id) {
        SubjectHelper helper = new SubjectHelper();
        return helper.getSubjectById(id);
    }

    //get all
    @WebMethod(operationName = "getAllMarks")
    public List<Mark> getAllMarks() {
        MarkHelper helper = new MarkHelper();
        return helper.getAllMarks();
    }

    @WebMethod(operationName = "getAllStudents")
    public List<Student> getAllStudents() {
        StudentHelper helper = new StudentHelper();
        return helper.getAllStudents();
    }

    @WebMethod(operationName = "getAllSubjects")
    public List<Subject> getAllSubjects() {
        SubjectHelper helper = new SubjectHelper();
        return helper.getAllSubjects();
    }

    @WebMethod(operationName = "getAllMarksSQL")
    public List<Mark> getAllMarksSQL(@WebParam(name = "student_id") int id) {
        MarkHelper helper = new MarkHelper();
        return helper.getAllMarksByStudentId(id);
    }

    @WebMethod(operationName = "getAllMarksSQLStudentAndSubject")
    public List<Mark> getAllMarksSQLStudentAndSubject(@WebParam(name = "student_id") int student_id, @WebParam(name = "subject_id") int subject_id) {
        MarkHelper helper = new MarkHelper();
        return helper.getAllMarksByStudentAndSubjectId(student_id, subject_id);
    }

    // save
    @WebMethod(operationName = "saveStudent")
    public String saveStudent(@WebParam(name = "firstName") String firstName, @WebParam(name = "lastName") String lastName, @WebParam(name = "address") String address) {

        StudentHelper helper = new StudentHelper();
        helper.save(firstName, lastName, address);

        return "Student " + firstName + " " + lastName + " saved!";
    }

    @WebMethod(operationName = "saveStudentDTO")
    public String saveStudentDTO(@WebParam(name = "firstName") String firstName, @WebParam(name = "lastName") String lastName, @WebParam(name = "address") String address) {

        Student entity = new Student();
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        entity.setAddress(address);

        StudentHelper helper = new StudentHelper();
        helper.save(entity);

        return "Student " + firstName + " " + lastName + " saved!";
    }

    @WebMethod(operationName = "saveStudentDTO2")
    public String saveStudentDTO2(@WebParam(name = "student") Student student) {

        StudentHelper helper = new StudentHelper();
        helper.save(student);

        return "Student " + student.getFirstName() + " " + student.getLastName() + " saved!";
    }

    @WebMethod(operationName = "saveSubject")
    public String saveSubject(@WebParam(name = "name") String name) {

        SubjectHelper helper = new SubjectHelper();
        helper.save(name);

        return "Subject " + name + " saved!";
    }

    @WebMethod(operationName = "saveSubjectDTO")
    public String saveSubjectDTO(@WebParam(name = "name") String name) {

        Subject entity = new Subject();
        entity.setName(name);

        SubjectHelper helper = new SubjectHelper();
        helper.save(entity);

        return "Subject " + name + " saved!";
    }

    @WebMethod(operationName = "saveSubjectDTO2")
    public String saveSubjectDTO2(@WebParam(name = "subject") Subject subject) {

        SubjectHelper helper = new SubjectHelper();
        helper.save(subject);

        return "Subject " + subject.getName() + " saved!";
    }

    @WebMethod(operationName = "saveMark")
    public String saveMark(@WebParam(name = "name") String name, @WebParam(name = "subjectId") int subjectId, @WebParam(name = "studentId") int studentId, @WebParam(name = "grade") float grade) {

        MarkHelper helper = new MarkHelper();
        helper.save(name, subjectId, studentId, grade);

        return "Mark " + name + " saved!";
    }

    @WebMethod(operationName = "saveMarkDTO")
    public String saveMarkDTO(@WebParam(name = "name") String name, @WebParam(name = "subjectId") int subjectId, @WebParam(name = "studentId") int studentId, @WebParam(name = "grade") double grade) {

        Mark entity = new Mark();
        entity.setName(name);
        entity.setMark(grade);

        StudentHelper studentHelper = new StudentHelper();
        Student student = studentHelper.getStudentById(studentId);
        SubjectHelper subjectHelper = new SubjectHelper();
        Subject subject = subjectHelper.getSubjectById(subjectId);

        entity.setSubject(subject);
        entity.setStudent(student);

        MarkHelper helper = new MarkHelper();
        helper.save(entity);

        return "Mark " + name + " saved!";
    }

    @WebMethod(operationName = "saveMarkDTO2")
    public String saveMarkDTO2(@WebParam(name = "mark") Mark mark) {

        MarkHelper helper = new MarkHelper();
        helper.save(mark);

        return "Mark for" + mark.getName() + " saved!";
    }

    //update
    @WebMethod(operationName = "updateStudent")
    public String updateStudent(@WebParam(name = "firstName") String firstName, @WebParam(name = "lastName") String lastName, @WebParam(name = "address") String address, @WebParam(name = "id") int id) {

        StudentHelper helper = new StudentHelper();
        helper.update(firstName, lastName, address, id);

        return "Student " + firstName + " " + lastName + " updated!";
    }

    @WebMethod(operationName = "updateStudentDTO")
    public String updateStudentDTO(@WebParam(name = "firstName") String firstName, @WebParam(name = "lastName") String lastName, @WebParam(name = "address") String address, @WebParam(name = "id") int id) {

        StudentHelper helper = new StudentHelper();
        Student entity = helper.getStudentById(id);
        helper = new StudentHelper();
        helper.update(entity);

        return "Student " + firstName + " " + lastName + " updated!";
    }

    @WebMethod(operationName = "updateStudentDTO2")
    public String updateStudentDTO2(@WebParam(name = "student") Student student) {

        StudentHelper helper = new StudentHelper();
        helper.update(student);

        return "Student " + student.getFirstName() + " " + student.getLastName() + " updated!";
    }

    @WebMethod(operationName = "updateSubject")
    public String updateSubject(@WebParam(name = "name") String name, @WebParam(name = "id") int id) {

        SubjectHelper helper = new SubjectHelper();
        helper.update(name, id);

        return "Subject " + name + " updated!";
    }

    @WebMethod(operationName = "updateSubjectDTO")
    public String updateSubjectDTO(@WebParam(name = "name") String name, @WebParam(name = "id") int id) {

        SubjectHelper helper = new SubjectHelper();
        Subject entity = helper.getSubjectById(id);
        entity.setName(name);
        helper.update(entity);

        return "Subject " + name + " updated!";
    }

    @WebMethod(operationName = "updateSubjectDTO2")
    public String updateSubjectDTO2(@WebParam(name = "entity") Subject entity) {

        SubjectHelper helper = new SubjectHelper();
        helper.update(entity);

        return "Subject " + entity.getName() + " updated!";
    }

    @WebMethod(operationName = "updateMark")
    public String updateMark(@WebParam(name = "name") String name, @WebParam(name = "subjectId") int subjectId, @WebParam(name = "studentId") int studentId, @WebParam(name = "grade") float grade, @WebParam(name = "id") int id) {

        MarkHelper helper = new MarkHelper();
        helper.update(name, subjectId, studentId, grade, id);

        return "Mark " + name + " updated!";
    }

    @WebMethod(operationName = "updateMarkDTO")
    public String updateMarkDTO(@WebParam(name = "name") String name, @WebParam(name = "subjectId") int subjectId, @WebParam(name = "studentId") int studentId, @WebParam(name = "grade") double grade, @WebParam(name = "id") int id) {

        MarkHelper helper = new MarkHelper();
        Mark entity = helper.getMarkById(id);

        StudentHelper studentHelper = new StudentHelper();
        Student student = studentHelper.getStudentById(studentId);
        SubjectHelper subjectHelper = new SubjectHelper();
        Subject subject = subjectHelper.getSubjectById(subjectId);

        entity.setName(name);
        entity.setStudent(student);
        entity.setSubject(subject);
        entity.setMark(grade);

        helper.update(entity);

        return "Mark " + name + " updated!";
    }

    @WebMethod(operationName = "updateMarkDTO2")
    public String updateMarkDTO2(@WebParam(name = "entity") Mark entity) {

        MarkHelper helper = new MarkHelper();
        helper.update(entity);

        return "Mark for " + entity.getName() + " updated!";
    }

    //delete
    @WebMethod(operationName = "deleteStudent")
    public String deleteStudent(@WebParam(name = "id") int id) {

        StudentHelper helper = new StudentHelper();
        helper.delete(id);

        return "Student deleted!";
    }

    @WebMethod(operationName = "deleteStudentDTO")
    public String deleteStudentDTO(@WebParam(name = "id") int id) {

        StudentHelper helper = new StudentHelper();
        Student entity = helper.getStudentById(id);
        String firstName = entity.getFirstName();
        String lastName = entity.getLastName();
        helper.delete(entity);

        return "Student " + firstName + " " + lastName + " deleted!";
    }

    @WebMethod(operationName = "deleteStudentDTO2")
    public String deleteStudentDTO2(@WebParam(name = "entity") Student entity) {

        StudentHelper helper = new StudentHelper();
        helper.delete(entity);

        return "Student " + entity.getFirstName() + " " + entity.getLastName() + " deleted!";
    }

    @WebMethod(operationName = "deleteSubject")
    public String deleteSubject(@WebParam(name = "id") int id) {

        SubjectHelper helper = new SubjectHelper();
        String name = helper.getSubjectById(id).getName();

        helper.delete(id);

        return "Subject " + name + " deleted!";
    }

    @WebMethod(operationName = "deleteSubjectDTO")
    public String deleteSubjectDTO(@WebParam(name = "id") int id) {

        SubjectHelper helper = new SubjectHelper();
        Subject entity = helper.getSubjectById(id);
        String name = entity.getName();
        helper.delete(entity);

        return "Subject " + name + " deleted!";
    }

    @WebMethod(operationName = "deleteSubjectDTO2")
    public String deleteSubjectDTO2(@WebParam(name = "entity") Subject entity) {

        SubjectHelper helper = new SubjectHelper();
        helper.delete(entity);

        return "Subject " + entity.getName() + " deleted!";
    }

    @WebMethod(operationName = "deleteMark")
    public String deleteMark(@WebParam(name = "id") int id) {

        MarkHelper helper = new MarkHelper();
        String name = helper.getMarkById(id).getName();
        helper.delete(id);

        return "Mark " + name + " deleted!";
    }

    @WebMethod(operationName = "deleteMarkDTO")
    public String deleteMarkDTO(@WebParam(name = "id") int id) {

        MarkHelper helper = new MarkHelper();
        Mark entity = helper.getMarkById(id);
        String name = entity.getName();
        helper.delete(entity);

        return "Mark " + name + " deleted!";
    }

    @WebMethod(operationName = "deleteMarkDTO2")
    public String deleteMarkDTO2(@WebParam(name = "entity") Mark entity) {

        MarkHelper helper = new MarkHelper();
        helper.delete(entity);

        return "Mark " + entity.getName() + " deleted!";
    }

    //Statistics
    
    @WebMethod(operationName = "getAverageGrade")
    public Double getAverageGrade(@WebParam(name = "marklist") List<Mark> list) {

        double sum = 0.0;
        
        for (Mark m : list) {
            sum += m.getMark();
        }
        
        return sum/list.size();
    }

}
