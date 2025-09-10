package by.javaguru.je.jdbc.servlet;

import by.javaguru.je.jdbc.service.MenuService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    MenuService menuService = MenuService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        Long pizzeriaId = Long.valueOf(req.getParameter("pizzeriaId"));

        try (var writer = resp.getWriter()) {
            writer.write("<h1>Пицца в пиццериях:</h1>");
            writer.write("<ul>");
            menuService.findAllByPizzeriaId(pizzeriaId)
                    .stream().forEach(menuDto ->
                    writer.write("""
                            <li>%s - %s</li>
                            """.formatted(menuDto.pizzaName(), menuDto.price())));
            writer.write("</ul>");
        }

    }
}
