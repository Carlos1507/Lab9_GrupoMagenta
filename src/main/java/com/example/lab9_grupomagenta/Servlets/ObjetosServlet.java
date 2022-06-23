package com.example.lab9_grupomagenta.Servlets;

import com.example.lab9_grupomagenta.Beans.BEfectividad;
import com.example.lab9_grupomagenta.Beans.BObjeto;
import com.example.lab9_grupomagenta.Beans.BVacuna;
import com.example.lab9_grupomagenta.Daos.ObjetosDao;
import com.example.lab9_grupomagenta.Daos.VirusDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ObjetosServlet", value = "/ObjetosServlet")
public class ObjetosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("a") == null? "inicio" : request.getParameter("a");
        ObjetosDao objetosDao = new ObjetosDao();
        switch (action){
            case "inicio" -> {
                request.setAttribute("listaObjetos", objetosDao.listarObjetos());
                request.setAttribute("objetosEnInventario",objetosDao.objetosEnInventarSuper());
                request.setAttribute("cambio","noMostrarEfectividad");
                RequestDispatcher view = request.getRequestDispatcher("listaObjetos.jsp");
                view.forward(request, response);
            }
            case "efectividad" -> {
                String idObjeto = request.getParameter("id");
                request.setAttribute("listaEfectividad", objetosDao.efectividadTipoZombie(idObjeto));
                request.setAttribute("cambio","mostrarEfectividad");
                RequestDispatcher view = request.getRequestDispatcher("listaObjetos.jsp");
                view.forward(request, response);
            }
            case "agregar" -> {
                String tipo = request.getParameter("tipo");
                request.setAttribute("tipo",tipo);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("agregarObjeto.jsp");
                requestDispatcher.forward(request,response);
            }
            case "editar" -> {
                String estaEninven = request.getParameter("esta");
                String idObjeto = request.getParameter("id");

                BObjeto bObjeto = objetosDao.buscarObjeto(idObjeto);
                ArrayList<BEfectividad> bEfectividad = objetosDao.buscarVacuna(String.valueOf(objetosDao.buscarObjeto(idObjeto).getIdVacuna()));

                if(bObjeto != null){
                    request.setAttribute("bObjeto",bObjeto);
                    request.setAttribute("inven",estaEninven);
                    request.setAttribute("tipo",bObjeto.getVacuna());
                    request.setAttribute("efectividad",bEfectividad);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("editarObjeto.jsp");
                    requestDispatcher.forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/ObjetosServlet");  //lo mano a la pagina principal
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("a") == null? "inicio" : request.getParameter("a");
        ObjetosDao objetosDao = new ObjetosDao();
        switch (action){
            case "guardarNormal" -> {
                String nombre = request.getParameter("nombre1");
                String peso = request.getParameter("peso1");
                objetosDao.guardarObjetoN(nombre,Double.parseDouble(peso),0,1);

                response.sendRedirect(request.getContextPath() + "/ObjetosServlet");
            }
            case "guardarVacuna" -> {
                String nombre = request.getParameter("nombre");
                String peso = request.getParameter("peso");
                String ef1 = request.getParameter("ef1");
                String ef2 = request.getParameter("ef2");
                String ef3 = request.getParameter("ef3");
                String ef4 = request.getParameter("ef4");
                String ef5 = request.getParameter("ef5");

                objetosDao.crearVacuna(Double.parseDouble(peso));
                int idUltimoVacuna = objetosDao.listarIdVacuna().get(objetosDao.listarIdVacuna().size()-1);

                objetosDao.guardarObjetoV(nombre,Double.parseDouble(peso),1,1,idUltimoVacuna);

                objetosDao.guardarEfectividad(idUltimoVacuna,1,Double.parseDouble(ef1));
                objetosDao.guardarEfectividad(idUltimoVacuna,2,Double.parseDouble(ef2));
                objetosDao.guardarEfectividad(idUltimoVacuna,3,Double.parseDouble(ef3));
                objetosDao.guardarEfectividad(idUltimoVacuna,4,Double.parseDouble(ef4));
                objetosDao.guardarEfectividad(idUltimoVacuna,5,Double.parseDouble(ef5));

                response.sendRedirect(request.getContextPath() + "/ObjetosServlet");
            }
            case "actualizarNormal" -> {
                String nombre = request.getParameter("nombre1");
                String peso = request.getParameter("peso1");
                String idObjeto = request.getParameter("id");
                objetosDao.actualizar(nombre,peso,idObjeto);

                response.sendRedirect(request.getContextPath() + "/ObjetosServlet");
            }
            case "actualizarVacuna" -> {
                String nombre = request.getParameter("nombre");
                String peso = request.getParameter("peso");
                String ef1 = request.getParameter("ef1");
                String ef2 = request.getParameter("ef2");
                String ef3 = request.getParameter("ef3");
                String ef4 = request.getParameter("ef4");
                String ef5 = request.getParameter("ef5");

                String idObjeto = request.getParameter("id");
                String idVacuna = request.getParameter("id2");

                objetosDao.actualizar(nombre,peso,idObjeto);
                objetosDao.actualizarVacuna(idVacuna,1,ef1);
                objetosDao.actualizarVacuna(idVacuna,2,ef2);
                objetosDao.actualizarVacuna(idVacuna,3,ef3);
                objetosDao.actualizarVacuna(idVacuna,4,ef4);
                objetosDao.actualizarVacuna(idVacuna,5,ef5);

                response.sendRedirect(request.getContextPath() + "/ObjetosServlet");
            }
        }
    }
}
