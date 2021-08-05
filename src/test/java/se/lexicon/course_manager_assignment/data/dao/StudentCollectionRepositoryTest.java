package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = {StudentCollectionRepository.class})
public class StudentCollectionRepositoryTest {

    @Autowired
    private StudentDao testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here

    @Test
    void createStudent() {
        //Arrange
        Student student = new Student(1, "Leo", "leo@email.com", "Street");

        int expectedStudentId = 1;
        String expectedStudentName = "Leo";
        String expectedStudentEmail = "leo@email.com";
        String expectedStudentAddress = "Street";

        //Act
        int actualStudentId = student.getSTUDENTID();
        String actualStudentName = student.getName();
        String actualStudentEmail = student.getEmail();
        String actualStudentAddress = student.getAddress();

        //Assert
        assertEquals(expectedStudentId, actualStudentId);
        assertEquals(expectedStudentName, actualStudentName);
        assertEquals(expectedStudentEmail, actualStudentEmail);
        assertEquals(expectedStudentAddress, actualStudentAddress);
    }

    @Test
    void findByEmailIgnoreCase() {
        //Arrange
        Student student = new Student(1, "Leo", "leo@email.com", "Street");

        //Act
        String expectedStudentEmail = "leo@email.com";
        String actualStudentEmail = student.getEmail();

        //Assert
        assertEquals(expectedStudentEmail, actualStudentEmail);

    }

    @Test
    void findByNameContains() {
        //Arrange
        Student student = new Student(1, "Leo", "leo@email.com", "Street");

        //Act
        String expectedStudentName = "Leo";
        String actualStudentName = student.getName();

        //Assert
        assertEquals(expectedStudentName, actualStudentName);
    }

    @Test
    void findById() {
        //Arrange
        Student student = new Student(1, "Leo", "leo@email.com", "Street");

        //Act
        int expectedStudentId = 1;
        int actualStudentId = student.getSTUDENTID();

        //Assert
        assertEquals(expectedStudentId, actualStudentId);
    }

    @Test
    void findAll() {
        //Arrange
        Student student = new Student(1, "Leo", "leo@email.com", "Street");
        Collection<Student> allStudents = new ArrayList<>();

        //Act
        allStudents.add(student);
        int expectedNumberOfStudents = 1;

        //Assert
        assertEquals(expectedNumberOfStudents, allStudents.size());

    }

    @Test
    void removeStudent() {
        //Arrange
        Student student = new Student(1, "Leo", "leo@email.com", "Street");
        Collection<Student> allStudents = new ArrayList<>();
        allStudents.add(student);

        //Act
        allStudents.remove(student);
        int expectedNumberOfStudents = 0;

        //Assert
        assertEquals(expectedNumberOfStudents, allStudents.size());
    }

    @Test
    void clear() {
        //Arrange
        Student student = new Student(1, "Leo", "leo@email.com", "Street");
        Collection<Student> allStudents = new ArrayList<>();
        allStudents.add(student);

        //Act
        allStudents.clear();
        int expectedNumberOfStudents = 0;

        //Assert
        assertEquals(expectedNumberOfStudents,allStudents.size());
    }


    @AfterEach
    void tearDown() {
        testObject.clear();
        StudentSequencer.setStudentSequencer(0);
    }


}
