package by.javaguru.je.jdbc.servlet;

import by.javaguru.je.jdbc.service.PizzeriaService;
import by.javaguru.je.jdbc.utils.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {

    private final PizzeriaService pizzeriaService = PizzeriaService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var pizzerias = pizzeriaService.findAll();
        req.setAttribute("pizzerias", pizzerias);

        req.getRequestDispatcher(JspHelper.getPath("content")).forward(req, resp);
    }
}
