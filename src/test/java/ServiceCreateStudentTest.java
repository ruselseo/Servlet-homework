import static org.junit.jupiter.api.Assertions.assertEquals;

import dto.CreateStudentDto;
import entity.Student;
import entity.Gender;
import mapper.CreateStudentDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import service.StudentService;
import dao.StudentDao;

import java.sql.SQLException;

public class ServiceCreateStudentTest {

    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private StudentService studentService;

    private CreateStudentDto createStudentDto;

    private CreateStudentDtoMapper createStudentMapper;

    @BeforeEach
    void prepare() {
        this.studentDao = Mockito.mock(StudentDao.class);
    }

    @Test
    void testCreate() throws SQLException {
        CreateStudentDto dto = new CreateStudentDto();
        dto.setName("John Doe");
        dto.setEmail("john.doe1@example.com");
        dto.setGender("MALE");
        dto.setGroup(1);
        dto.setClasses(2);

        Student student1 = new Student();
        student1.setId(1);
        student1.setName("John Doe");
        student1.setEmail("john.doe@example.com");
        student1.setGender(Gender.MALE);
        student1.setGroup(1);
        student1.setClasses(2);

//        Mockito.when(studentDao.save(student1)).thenReturn(student1);
//        StudentService.getInstance().create(dto);

//        assertEquals(1, student1.getId());
    }
}
