package se.lexicon.course_manager_assignment.model;

import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;

import java.util.Objects;

public class Student {

    private int STUDENTID;
    String name;
    String email;
    String address;

    public Student(int STUDENTID, String name, String email, String address) {
        this.STUDENTID = STUDENTID;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public Student(){}

    public int getSTUDENTID() {
        return STUDENTID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return STUDENTID == student.STUDENTID && name.equals(student.name) && email.equals(student.email) && address.equals(student.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(STUDENTID, name, email, address);
    }

    @Override
    public String toString() {
        return "Student{" +
                "STUDENTID=" + STUDENTID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
