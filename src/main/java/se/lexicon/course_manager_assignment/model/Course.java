package se.lexicon.course_manager_assignment.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class Course {

    private int COURSEID;
    String courseName;
    LocalDate startDate;
    int weekDuration;
    Collection<Student> students = new HashSet<>();

    public Course(int COURSEID, String courseName, LocalDate startDate, int weekDuration) {
        this.COURSEID = COURSEID;
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
    }

    public Course(){}

    public int getCOURSEID() {
        return COURSEID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(int weekDuration) {
        this.weekDuration = weekDuration;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    public boolean enrollStudent(Student student) {
        return students.add(student);

    }

    public boolean unrollStudent(Student student) {
        return students.remove(student);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return COURSEID == course.COURSEID && weekDuration == course.weekDuration && courseName.equals(course.courseName) && startDate.equals(course.startDate) && students.equals(course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(COURSEID, courseName, startDate, weekDuration, students);
    }

    @Override
    public String toString() {
        return "Course{" +
                "COURSEID=" + COURSEID +
                ", courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", weekDuration=" + weekDuration +
                ", students=" + students +
                '}';
    }


}
