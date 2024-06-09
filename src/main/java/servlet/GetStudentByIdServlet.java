package servlet;

import dto.StudentDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StudentService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/students/*")
public class GetStudentByIdServlet extends HttpServlet {

    private final StudentService studentService = StudentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing student ID");
            return;
        }

        try {
            int id = Integer.parseInt(pathInfo.substring(1));
            StudentDto studentDto = studentService.findStudentById(id);
            if (studentDto == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Student not found");
                return;
            }
            resp.setContentType("application/json");
            resp.getWriter().write("{ \"name\": \"" + studentDto.getName() + "\", \"email\": " + studentDto.getEmail() + " }");
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid student ID");
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve student");
        }
    }

}
