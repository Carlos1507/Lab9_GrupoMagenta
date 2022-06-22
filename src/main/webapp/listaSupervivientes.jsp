<%@ page import="com.example.lab9_grupomagenta.Beans.BSuperviviente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9_grupomagenta.Beans.BSuperviviente>" scope="request" id="listaSupervivientes"/>
<html>
<jsp:include page="/static/head.jsp">
    <jsp:param name="title" value="Lista de Supervivientes"/>
</jsp:include>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="page" value="supervivientes"/>
    </jsp:include>
    <div class="pb-5 pt-4 px-3 titlecolor">
        <div class="col-lg-6">
            <h1 class='text-light'>Lista de Supervivientes</h1>
            <table>
                <tr>
                    <td>
                        <a href="<%= request.getContextPath()%>/SupervivientesServlet?action=agregar" type="button" class="btn btn-success">Agregar</a>
                    </td>
                    <td>
                        <h5 style="color:#fff;">Filtrar por sexo</h5>
                    </td>
                    <td>
                        <form class="btn-group" method="post" action="<%=request.getContextPath()%>/SupervivientesServlet?action=filtrar">
                            <div class="form-group">
                                <table>
                                    <tr>
                                        <td>
                                            <select name="filtro" id="filtro" class="form-control">
                                                <option disabled="disabled">Seleccionar</option>
                                                <option value="femenino">Femenino</option>
                                                <option value="masculino">Masculino</option>
                                                <option value="otro">Otro</option>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="submit" value="Filtrar" class="btn btn-primary"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </form>
                    </td>

                </tr>

            </table>
        </div>
    </div>
    <div class="tabla">
        <table class="table table-dark table-transparent table-hover">
            <thead>
            <th>IDENTIFICACION</th>
            <th>NOMBRE Y APELLIDO</th>
            <th>SEXO</th>
            <th>PESO</th>
            <th>FUERZA</th>
            <th>PAREJA</th>
            <th>PESO CARGADO</th>
            <th>ACCIONES</th>
            </thead>
            <%  for (BSuperviviente superviviente : listaSupervivientes) { %>
            <%  if(superviviente.isHabilitado()){ %>
            <tr>
                <td><%=superviviente.getNumIdentificacion()%></td>
                <td><%=superviviente.getNombre()%> <%=superviviente.getApellido()%></td>
                <td><%=superviviente.getSexo()%></td>
                <td><%=superviviente.getPeso()%></td>
                <td><%=superviviente.getFuerza()%></td>
                <% if (superviviente.getPareja()==null){ %>
                    <td>Sin pareja</td>
                <%} else {%>
                    <td><%=superviviente.getPareja().getNombre()%> <%=superviviente.getPareja().getApellido()%></td>
                <%}%>
                <td><%=superviviente.getPesoCargado()%></td>
                <td>
                    <a href="<%= request.getContextPath()%>/SupervivientesServlet?action=editar&id=<%=superviviente.getIdSuperviviente()%>">
                        <img ml="30" height="30" width="30" src="https://cdn-icons-png.flaticon.com/512/1083/1083099.png">
                    </a>
                    <a href="<%= request.getContextPath()%>/SupervivientesServlet?action=eliminar&id=<%=superviviente.getIdSuperviviente()%>">
                        <img height="30" width="30" src="https://cdn-icons-png.flaticon.com/512/458/458594.png">
                    </a>
                </td>
            </tr>
            <% }}   %>
        </table>
    </div>

</div>
<jsp:include page="/static/scripts.jsp"/>
</body>
</html>
