package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.GroupService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/groups")
public class GroupServlet extends HttpServlet {

    private final GroupService groupService = GroupService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var printWriter = resp.getWriter()) {
            printWriter.write("<h1>Список групп:</h1>");
            printWriter.write("<ul>");
            groupService.findAllGroups().forEach(groupDto -> {
                printWriter.write("""
                        <li>
                            <a href="/students?groupId=%d">%s%d</a>
                        </li>
                        """.formatted(groupDto.getGroupId(),"Номер группы: ", groupDto.getGroupNumber()));
            });
            printWriter.write("</ul>");
        }
    }
}
