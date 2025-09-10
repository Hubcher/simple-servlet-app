package by.cher.servlet;

import by.cher.service.PizzeriaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@WebServlet("/pizzeria")
public class PizzeriaServlet extends HttpServlet {
    private final PizzeriaService pizzeriaService = PizzeriaService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/html");
       resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (var writer = resp.getWriter()) {
            writer.write("<h1>Список пиццерий</h1>");
            writer.write("<ul>");
            pizzeriaService.findAll().stream().forEach(pizzeriaDto ->
                    writer.write("""
                            <li>
                                <a href='/menu?pizzeriaId=%d'>%s</a>
                            </li>
                            """.formatted(pizzeriaDto.id(), pizzeriaDto.description())));
            writer.write("</ul>");
        }
    }
}
