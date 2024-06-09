package mapper;

import dto.CreateStudentDto;
import entity.Gender;
import entity.Student;

public class CreateStudentMapper implements Mapper<CreateStudentDto, Student> {

    private CreateStudentMapper() {
    }

    private static final CreateStudentMapper INSTANCE = new CreateStudentMapper();

    @Override
    public Student mapFrom(CreateStudentDto object) {
        Student student = new Student();
        student.setName(object.getName());
        student.setEmail(object.getEmail());
        student.setGender(Gender.valueOf(object.getGender()));
        student.setGroup(object.getGroup());
        student.setClasses(object.getClasses());
        return student;
    }



    public static CreateStudentMapper getInstance() {
        return INSTANCE;
    }
}
