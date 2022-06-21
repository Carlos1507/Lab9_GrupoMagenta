package com.example.lab9_grupomagenta.Servlets;

import com.example.lab9_grupomagenta.Daos.VirusDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "VirusServlet", value = "/VirusServlet")
public class VirusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("a") == null? "inicio" : request.getParameter("a");
        VirusDao virusDao = new VirusDao();
        switch (action){
            case "inicio" -> {
                virusDao.eliminarSinVariante();  //Eliminar virus sin variantes
                request.setAttribute("cambio","noMuestraVariante");
                request.setAttribute("listaVirus", virusDao.listarVirus());
                RequestDispatcher view = request.getRequestDispatcher("listaVirus.jsp");
                view.forward(request, response);
            }
            case "variante" -> {
                String idVirus = request.getParameter("id");
                request.setAttribute("idVirus",idVirus);
                request.setAttribute("cambio","muestraVariante");
                request.setAttribute("listaVari",virusDao.listarVariante());
                RequestDispatcher view = request.getRequestDispatcher("listaVirus.jsp");
                view.forward(request, response);
            }
            case "eliminarVariante" -> {
                String idVariante = request.getParameter("id");
                virusDao.borrarZombies(idVariante); // Es primero borrar el zombie luego la Variante
                virusDao.borrarVariante(idVariante);
                response.sendRedirect(request.getContextPath() + "/VirusServlet");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("a") == null? "inicio" : request.getParameter("a");
        VirusDao virusDao = new VirusDao();
        switch (action){
            case "crearNuevoVirus" -> {
                String nameVirus = request.getParameter("nameVirus");

                if(virusDao.buscarVirus(nameVirus).equalsIgnoreCase("noExiste")){
                    virusDao.crearVirus(nameVirus);
                    int idUltimo  = virusDao.listarVirus().size() - 1;
                    virusDao.crearVariante(nameVirus+"v1",virusDao.listarVirus().get(idUltimo).getIdVirus()); //Variante por defecto
                }

                response.sendRedirect(request.getContextPath() + "/VirusServlet");
            }
            case "crearNuevaVariante" -> {
                String nameVariante = request.getParameter("nameVariante");
                String idVirus = request.getParameter("id");

                if(virusDao.buscarVariante(nameVariante).equalsIgnoreCase("noExiste")){
                    virusDao.crearVariante(nameVariante,Integer.parseInt(idVirus));
                }

                response.sendRedirect(request.getContextPath() + "/VirusServlet");
            }
        }
    }
}
