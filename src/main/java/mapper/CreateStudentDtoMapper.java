package mapper;

import dto.StudentDto;
import entity.Student;

public class CreateStudentDtoMapper implements Mapper<Student, StudentDto> {

    private static final CreateStudentDtoMapper INSTANCE = new CreateStudentDtoMapper();

    public CreateStudentDtoMapper() {
    }

    public StudentDto mapFrom(Student entity) {
        StudentDto studentDto = new StudentDto();
        studentDto.setName(entity.getName());
        studentDto.setEmail(entity.getEmail());
        return studentDto;

    }

    public static CreateStudentDtoMapper getInstance() {
        return INSTANCE;
    }
}
