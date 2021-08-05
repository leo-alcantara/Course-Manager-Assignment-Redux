package se.lexicon.course_manager_assignment.data.service.converter;

import org.springframework.stereotype.Component;

import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.*;

@Component
public class ModelToDto implements Converters {

    @Override
    public StudentView studentToStudentView(Student student) {
        return new StudentView(student.getSTUDENTID(), student.getName(), student.getEmail(), student.getAddress());
    }


    @Override
    public CourseView courseToCourseView(Course course) {
        List<StudentView> allStudentsView = new ArrayList<>();
        for (Student student :
               course.getStudents()) {
                allStudentsView.add(studentToStudentView(student));
        }
        return new CourseView(course.getCOURSEID(), course.getCourseName(), course.getStartDate(),
                course.getWeekDuration(), allStudentsView);
    }


    @Override
    public List<CourseView> coursesToCourseViews(Collection<Course> courses) {
        List<CourseView> allCoursesViews = new ArrayList<>();
        for (Course course :
                courses) {
                allCoursesViews.add(courseToCourseView(course));
        }
        return allCoursesViews;
    }


    @Override
    public List<StudentView> studentsToStudentViews(Collection<Student> students) {
        List<StudentView> allStudentsView = new ArrayList<>();
        for (Student student :
                students) {
                allStudentsView.add(studentToStudentView(student));
        }
        return allStudentsView;
    }
}
