package service;

import dao.StudentDao;
import dto.CreateStudentDto;
import dto.StudentDto;
import entity.Student;
import mapper.CreateStudentDtoMapper;
import mapper.CreateStudentMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


public class StudentService {

    private static final StudentService INSTANCE = new StudentService();

    private final StudentDao studentDao = StudentDao.getInstance();
    private final CreateStudentMapper createStudentMapper = CreateStudentMapper.getInstance();
    private final CreateStudentDtoMapper createStudentDtoMapper = CreateStudentDtoMapper.getInstance();


    public Integer create(CreateStudentDto studentDto) throws SQLException {

        var studentEntity = createStudentMapper.mapFrom(studentDto);
        studentDao.save(studentEntity);
        return studentEntity.getId();
    }



    public StudentDto findStudentById(int id) throws SQLException {
        StudentDto studentDto;
        Student entity = studentDao.findById(id);
        if (entity == null)
            return null;

        studentDto = createStudentDtoMapper.mapFrom(entity);
        return studentDto;
    }



    public List<StudentDto> findAllByGroupId(Integer GroupId) throws SQLException {

        return studentDao.findAllByGroupId(GroupId).stream().
                map(createStudentDtoMapper::mapFrom).collect(Collectors.toList());
    }



    public static StudentService getInstance() {
        return INSTANCE;
    }



    private StudentService() {
    }
}