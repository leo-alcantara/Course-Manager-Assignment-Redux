package se.lexicon.course_manager_assignment.data.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.List;

@Service
public class StudentManager implements StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final Converters converters;

    @Autowired
    public StudentManager(StudentDao studentDao, CourseDao courseDao, Converters converters) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.converters = converters;
    }

    @Override
    public StudentView create(CreateStudentForm form) {
        return converters.studentToStudentView(studentDao.createStudent(form.getName(), form.getEmail(), form.getAddress()));
    }


    @Override
    public StudentView update(UpdateStudentForm form) {
        Student student = studentDao.findById(form.getId());
        if (student != null) {
            student.setName(form.getName());
            student.setEmail(form.getEmail());
            student.setAddress(form.getAddress());
        }
        return converters.studentToStudentView(student);
    }

    @Override
    public StudentView findById(int id) {
        Student student = studentDao.findById(id);
        if(student != null){
            return converters.studentToStudentView(studentDao.findById(id));
        }
        return null;
    }

    @Override
    public StudentView searchByEmail(String email) {
        Student student = studentDao.findByEmailIgnoreCase(email);
        if (student != null) {
            return converters.studentToStudentView(student);
        }
        return null;
    }

    @Override
    public List<StudentView> searchByName(String name) {
        return converters.studentsToStudentViews(studentDao.findByNameContains(name));

    }

    @Override
    public List<StudentView> findAll() {
        return converters.studentsToStudentViews(studentDao.findAll());
    }

    @Override
    public boolean deleteStudent(int id) {
        Student student = studentDao.findById(id);
        for (Course course : courseDao.findByStudentId(id)) {
            course.unrollStudent(student);
        }
        return studentDao.removeStudent(studentDao.findById(id));
    }


}
