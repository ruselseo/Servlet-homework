package servlet;

import dto.CreateStudentDto;
import entity.Gender;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StudentService;
import util.JspHelper;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final StudentService studentService = StudentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("genders", Gender.values());

        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        CreateStudentDto createStudentDto = new CreateStudentDto();
        createStudentDto.setName(req.getParameter("name"));
        createStudentDto.setEmail(req.getParameter("email"));
        createStudentDto.setGender(req.getParameter("gender"));
        createStudentDto.setGroup(Integer.valueOf(req.getParameter("group")));
        createStudentDto.setClasses(Integer.valueOf(req.getParameter("class")));

        try {
            studentService.create(createStudentDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            resp.sendRedirect("/students?groupId=" + createStudentDto.getGroup());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


