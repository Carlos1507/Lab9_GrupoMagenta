package com.example.lab9_grupomagenta.Servlets;

import com.example.lab9_grupomagenta.Beans.BSuperviviente;
import com.example.lab9_grupomagenta.Daos.SupervivienteDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "SupervivientesServlet", value = "/SupervivientesServlet")
public class SupervivientesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action") == null ? "listar": request.getParameter("action");
        SupervivienteDao supervivienteDao = new SupervivienteDao();
        RequestDispatcher view;
        switch (action){
            case "listar":
                request.setAttribute("listaSupervivientes", supervivienteDao.listarSupervivientes(null));
                view = request.getRequestDispatcher("listaSupervivientes.jsp");
                view.forward(request, response);
                break;
            case "agregar":
                request.setAttribute("listaSupervivientes", supervivienteDao.listarSupervivientes(null));
                view = request.getRequestDispatcher("creaSupervivientes.jsp");
                view.forward(request, response);
                break;
            case "editar":
                try {
                    int idSuperv = Integer.parseInt(request.getParameter("id"));
                    BSuperviviente superviviente = supervivienteDao.obtenerSuperviviente(idSuperv);
                    if (superviviente!=null){
                        request.setAttribute("supervSelect", superviviente);
                        request.setAttribute("listaSupervivientes", supervivienteDao.listarSupervivientes(null));
                        view = request.getRequestDispatcher("modificaSupervivientes.jsp");
                        view.forward(request, response);
                    }
                }
                catch (NumberFormatException ne){
                    ne.printStackTrace();
                }finally {
                    response.sendRedirect("SupervivientesServlet");
                }
                break;
            case "eliminar":
                String numID = request.getParameter("id");
                supervivienteDao.eliminarSuperviviente(numID);
                response.sendRedirect("SupervivientesServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action")==null? "listar":request.getParameter("action");
        SupervivienteDao supervivienteDao = new SupervivienteDao();
        RequestDispatcher view;
        switch (action){
            case "listar":
                response.sendRedirect("SupervivientesServlet");
                break;
            case "filtrar":
                String filtro = request.getParameter("filtro");
                request.setAttribute("listaSupervivientes", supervivienteDao.listarSupervivientes(filtro));
                view = request.getRequestDispatcher("listaSupervivientes.jsp");
                view.forward(request, response);
                break;
            case "guardar":
                try{
                    String nombre = request.getParameter("nombre");
                    String apellido = request.getParameter("apellido");
                    double peso = Math.round(Float.parseFloat(request.getParameter("peso"))*100.0)/100.0;
                    double fuerza = Math.round(Float.parseFloat(request.getParameter("fuerza"))*100.0)/100.0;
                    String sexo = request.getParameter("sexo");
                    String pareja = request.getParameter("pareja");
                    String numIdentificacion = generarIDAlatoria();
                    int idPareja = supervivienteDao.obtenerIDSuperviviente(pareja);
                    supervivienteDao.crearSuperviviente(nombre, apellido, peso, fuerza, sexo, idPareja, numIdentificacion);
                    response.sendRedirect("SupervivientesServlet");
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                break;
            case "actualizar":
                String nombre=request.getParameter("nombre");
                String apellido = request.getParameter("apellido");
                double peso = Math.round(Float.parseFloat(request.getParameter("peso"))*100.0)/100.0;
                double fuerza = Math.round(Float.parseFloat(request.getParameter("fuerza"))*100.0)/100.0;
                String pareja = request.getParameter("pareja");


                break;
        }
    }
    public String generarIDAlatoria(){
        SupervivienteDao supDao = new SupervivienteDao();
        String codigo = "";
        Random ran = new Random();
        for(int i=0;i<11;i++){
            int x = ran.nextInt(10);
            codigo+=""+x+"";
        }
        if (supDao.identificacionUsada(codigo)) {
            generarIDAlatoria();
        }
        return codigo;
    }
}
