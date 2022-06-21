package com.example.lab9_grupomagenta.Servlets;

import com.example.lab9_grupomagenta.Beans.BHumano;
import com.example.lab9_grupomagenta.Daos.HumanosDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HumanosServlet", value = "/HumanosServlet")
public class HumanosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HumanosDao humanosDao = new HumanosDao();
        request.setAttribute("listaHumanos", humanosDao.listarHumanos());
        RequestDispatcher view = request.getRequestDispatcher("listaHumanos.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
