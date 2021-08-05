package se.lexicon.course_manager_assignment.data.dao;


import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.*;


public class CourseCollectionRepository implements CourseDao {

    private Collection<Course> courses;

    public CourseCollectionRepository(Collection<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
        int COURSEID = CourseSequencer.nextCourseId();
        Course course = new Course(COURSEID, courseName, startDate, weekDuration);
        courses.add(course);
        return course;
    }

    @Override
    public Course findById(int id) {
        for (Course course : courses) {
            if (id == course.getCOURSEID()) {
                return course;
            }
        }
        return null;
    }

    @Override
    public Collection<Course> findByNameContains(String name) {
        HashSet<Course> allCoursesNamesMatched = new HashSet<>();
        for (Course course : courses) {
            if (course.getCourseName().contains(name)) {
                allCoursesNamesMatched.add(course);
            }
        }
        return allCoursesNamesMatched;
    }

    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {
        HashSet<Course> allCoursesBeforeDate = new HashSet<>();
        for (Course course : courses) {
            if (course.getStartDate().isBefore(end)) {
                allCoursesBeforeDate.add(course);
            }
        }
        return allCoursesBeforeDate;
    }

    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        HashSet<Course> allCoursesAfterDate = new HashSet<>();
        for (Course course : courses) {
            if (course.getStartDate().isAfter(start)) {
                allCoursesAfterDate.add(course);
            }
        }
        return allCoursesAfterDate;
    }

    @Override
    public Collection<Course> findAll() {
        HashSet<Course> allCourses = new HashSet<>();
        allCourses.addAll(courses);
        return allCourses;
    }

    @Override
    public Collection<Course> findByStudentId(int studentId) {
        Collection<Course> coursesFoundByStudentId = new HashSet<>();
        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                if (student.getSTUDENTID() == studentId) {
                    coursesFoundByStudentId.add(course);
                }
            }
        }
        return coursesFoundByStudentId;
    }

    @Override
    public boolean removeCourse(Course course) {
        return courses.remove(course);
    }

    @Override
    public void clear() {
        this.courses = new HashSet<>();
    }
}
