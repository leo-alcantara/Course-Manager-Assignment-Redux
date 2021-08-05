package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = {CourseCollectionRepository.class})
public class CourseCollectionRepositoryTest {

    @Autowired
    private CourseDao testObject;

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here

    Course course;
    Collection<Course> courses;

    @BeforeEach
    void before (){
         CourseSequencer.resetCourseSequencer();
         course = testObject.createCourse("Java Intensive", LocalDate.parse("1970-01-01"), 5);
    }

    /*@Test
    void createCourse() {
        //Arrange
        Course course;
        String expectedCourseName = "Java Advanced";
        LocalDate expectedStartingDate = LocalDate.parse("1970-01-01");
        int expectedWeekDuration = 5;

        //Act
        course = new Course(CourseSequencer.nextCourseId(), "Java Advanced", LocalDate.parse("1970-01-01"), 5);
        String actualCourseName = course.getCourseName();
        LocalDate actualStartDate = course.getStartDate();
        int actualWeekDuration = course.getWeekDuration();
        int actualCourseId = course.getCOURSEID();
        //Assert

        assertEquals(1, actualCourseId);
        assertEquals(expectedCourseName, actualCourseName);
        assertEquals(expectedStartingDate, actualStartDate);
        assertEquals(expectedWeekDuration, actualWeekDuration);
    }*/

    @Test
    void findById() {
        //Arrange
        //Course course = testObject.createCourse("Java Advanced", LocalDate.parse("1970-01-01"), 5);
        //Act
        Course actualCourse = testObject.findById(course.getCOURSEID());
        int expectedId = 1;
        //Assert
       assertEquals(expectedId, actualCourse.getCOURSEID());


    }

    @Test
    @DisplayName("find by name size 1")
    void test_find_by_name_size() {
        //Arrange
        //Course course = testObject.createCourse("Java Advanced", LocalDate.parse("1970-01-01"), 5);

        //Act
        List<Course> actualCoursesList = new ArrayList<>(testObject.findByNameContains(course.getCourseName()));

        //Assert
        assertEquals(1, actualCoursesList.size());
    }

    @Test
    void findByDateBefore() {
        //Arrange
        Course course = new Course(1, "Java Advanced", LocalDate.parse("1970-01-01"), 5);

        //Act
        boolean actualStartDate = course.getStartDate().isBefore(LocalDate.parse("1980-01-01"));

        //Assert
        assertTrue(actualStartDate);
    }

    @Test
    void findByDateAfter() {
        //Arrange
        Course course = new Course(1, "Java Advanced", LocalDate.parse("1970-01-01"), 5);

        //Act
        boolean actualStartDate = course.getStartDate().isAfter(LocalDate.parse("1960-01-01"));

        //Assert
        assertTrue(actualStartDate);
    }

    @Test
    void findAll() {
        //Arrange
        Course course = new Course(1, "Java Advanced", LocalDate.parse("1970-01-01"), 5);
        Collection<Course> allCourses = new ArrayList<>();
        //Act
        allCourses.add(course);

        //Assert
        assertEquals(1, allCourses.size());
    }

    @Test
    void findByStudentId() {
        //Arrange
        Student student = new Student(1, "Leo", "leo@email.com", "Street");
        Course course = new Course(1, "Java Advanced", LocalDate.parse("1970-01-01"), 5);
        Collection<Course> allCourses = new ArrayList<>();

        //Act
        course.unrollStudent(student);
        allCourses.add(course);
        int actualStudentId = student.getSTUDENTID();

        //Assert
        assertEquals(1, actualStudentId);
    }

    @Test
    void removeCourse() {
        //Arrange
        Course course = new Course(1, "Java Advanced", LocalDate.parse("1970-01-01"), 5);
        Collection<Course> allCourses = new ArrayList<>();
        allCourses.add(course);

        //Act
        allCourses.remove(course);

        //Assert
        assertEquals(0, allCourses.size());
    }

    @Test
    void clear() {
        //Arrange
        Course course = new Course(1, "Java Advanced", LocalDate.parse("1970-01-01"), 5);
        Collection<Course> allCourses = new ArrayList<>();
        allCourses.add(course);

        //Act
        allCourses.clear();

        //Assert
        assertEquals(0, allCourses.size());
    }


    @AfterEach
    void tearDown() {
        testObject.clear();
        CourseSequencer.setCourseSequencer(0);

    }

}