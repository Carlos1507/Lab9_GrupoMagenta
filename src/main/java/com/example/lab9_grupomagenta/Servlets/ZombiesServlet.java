package com.example.lab9_grupomagenta.Servlets;

import com.example.lab9_grupomagenta.Daos.ZombiesDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ZombiesServlet", value = "/ZombiesServlet")
public class ZombiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("a") == null ? "listar" : request.getParameter("a");
        ZombiesDao zombiesDao = new ZombiesDao();

        switch (action) {
            case"listar"->{
                request.setAttribute("listaZombies", zombiesDao.listarZombies());
                request.setAttribute("comunVariante",zombiesDao.varianteMasComun());
                request.setAttribute("porcentajes",zombiesDao.porcentajeZombie());
                request.setAttribute("promedios",zombiesDao.promedioVictimas());
                RequestDispatcher view = request.getRequestDispatcher("Zombies.jsp");
                view.forward(request, response);
            }
            case"agregar"->{
                request.setAttribute("listaTipoZombie", zombiesDao.listarTipoZombies());
                request.setAttribute("listaVariantes",zombiesDao.listarVariantes());
                RequestDispatcher view = request.getRequestDispatcher("AgregarZombie.jsp");
                view.forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("a") == null ? "listar" : request.getParameter("a");
        ZombiesDao zombiesDao = new ZombiesDao();
        switch (action) {
            case "agregar" -> {
                String nIdentificacion = request.getParameter("nIdentificacion");
                String nombre = request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                String idTipoZombie = request.getParameter("tipoZombie");
                String idVariante = request.getParameter("variante");
                String sexo = request.getParameter("sexo");
                zombiesDao.crearZombie(nIdentificacion,nombre,apellido,sexo,idTipoZombie,idVariante);
                response.sendRedirect(request.getContextPath() + "/ZombiesServlet");
            }
        }
    }
}
