import static org.mockito.Mockito.*;

import dto.CreateStudentDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.RequestDispatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import service.StudentService;
import servlet.RegistrationServlet;
import java.io.IOException;
import java.sql.SQLException;

public class ServletRegistrationTest {
    private RegistrationServlet registrationServlet;

    @Mock
    private StudentService studentService;

    @Mock
    private HttpServletRequest req;

    @Mock
    private HttpServletResponse resp;

    @Mock
    private RequestDispatcher requestDispatcher;

    @BeforeEach
    void setUp() {
        registrationServlet = new RegistrationServlet();
        this.req = mock(HttpServletRequest.class);
        this.resp = mock(HttpServletResponse.class);
        this.requestDispatcher = mock(RequestDispatcher.class);
        this.studentService = mock(StudentService.class);
    }

    @Test
    void testDoPost() throws IOException, ServletException, SQLException {
        when(req.getParameter("name")).thenReturn("John Doe");
        when(req.getParameter("email")).thenReturn("john.doe@example.com");
        when(req.getParameter("gender")).thenReturn("MALE");
        when(req.getParameter("group")).thenReturn("1");
        when(req.getParameter("class")).thenReturn("2");

//        when(studentService.create(any(CreateStudentDto.class))).thenReturn(1);
//        registrationServlet.doPost(req, resp);
//        verify(studentService).create(any(CreateStudentDto.class));
//        verify(resp).sendRedirect(anyString());
    }
}
