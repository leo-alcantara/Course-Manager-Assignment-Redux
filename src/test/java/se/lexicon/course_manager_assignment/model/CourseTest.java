package se.lexicon.course_manager_assignment.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


public class CourseTest {

    Course newCourse = new Course(1,"Java", LocalDate.parse("1970-01-01"), 1);

    @Test
    void enrollStudent_Test() {
        //Arrange
        Student newStudent = new Student(1, "Leo", "leo@mail.com", "Some Street");

        //Act
        boolean response = newCourse.enrollStudent(newStudent);
        boolean contains = newCourse.getStudents().contains(newStudent);

        //Assert
        assertTrue(response);
        assertTrue(contains);

    }

    @Test
    void unrollStudent_Test() {
        //Arrange
        Student newStudent = new Student(1, "Leo", "leo@mail.com", "Some Street");
        //Act
        newCourse.enrollStudent(newStudent);
        boolean unrolled = newCourse.unrollStudent(newStudent);

        //Assert
        assertTrue(unrolled);
    }
}
