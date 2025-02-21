package gradeservice.model;
// Generated May 11, 2016 1:03:45 PM by Hibernate Tools 4.3.1

import com.sun.xml.ws.transport.tcp.util.WSTCPURI;
import gradeservice.model.helper.WSIntegerAdapter;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Student generated by hbm2java
 */
@Entity
@Table(name = "Student", schema = "dbo", catalog = "WSAppDB2"
)
@XmlAccessorType(XmlAccessType.FIELD)
public class Student implements java.io.Serializable {

    @XmlJavaTypeAdapter(type=int.class, value = WSIntegerAdapter.class)
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    @XmlTransient
    private Set<Mark> marks = new HashSet<>(0);
    @XmlTransient
    private Set<SubjectGroup> subjectGroups = new HashSet<>(0);

    public Student() {
    }

    public Student(int id) {
        this.id = id;
    }

    public Student(int id, String firstName, String lastName, String address, Set<Mark> marks, Set<SubjectGroup> subjectGroups) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.marks = marks;
        this.subjectGroups = subjectGroups;
    }

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "firstName", length = 100)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastName", length = 100)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "address", length = 200)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlTransient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    public Set<Mark> getMarks() {
        return this.marks;
    }

    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }

    @XmlTransient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    public Set<SubjectGroup> getSubjectGroups() {
        return this.subjectGroups;
    }

    public void setSubjectGroups(Set<SubjectGroup> subjectGroups) {
        this.subjectGroups = subjectGroups;
    }

}
