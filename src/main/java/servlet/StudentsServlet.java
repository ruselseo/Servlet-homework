package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.StudentService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {

    private final StudentService studentService = StudentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var groupId = Integer.valueOf(req.getParameter("groupId"));

        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>В группе учатся:</h1>");
            printWriter.write("<ul>");
            try {
                studentService.findAllByGroupId(groupId).forEach(studentDto -> printWriter.write("""
                        <li>
                           <a href="/students?groupId=%d">%s%s%s</a>
                        </li>
                        """.formatted(groupId, studentDto.getName(),"  Email: ", studentDto.getEmail())));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            printWriter.write("</ul>");
        }
    }
}
