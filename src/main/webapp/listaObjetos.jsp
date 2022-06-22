<%@ page import="com.example.lab9_grupomagenta.Beans.BHumano" %>
<%@ page import="com.example.lab9_grupomagenta.Beans.BObjeto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9_grupomagenta.Beans.BObjeto>" class="java.util.ArrayList" scope="request" id="listaObjetos"/>
<jsp:useBean type="java.util.ArrayList<com.example.lab9_grupomagenta.Beans.BObjeto>" class="java.util.ArrayList" scope="request" id="listaEfectividad"/>
<jsp:useBean type="java.util.ArrayList<java.lang.Integer>" class="java.util.ArrayList" scope="request" id="objetosEnInventario"/>
<jsp:useBean type="java.lang.String" scope="request" id="cambio"/>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Lista de Objetos"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="objetos"/>
            </jsp:include>

            <%if(cambio.equalsIgnoreCase("noMostrarEfectividad")){%>
            <div class="pb-5 pt-4 px-3 titlecolor">
                <div class="row gx-lg-5">
                    <div class="col-lg-6">
                        <h1 class='text-light'>Lista de Objetos</h1>
                    </div>
                    <div class="col-lg-3">
                        <a href="<%=request.getContextPath()%>/ObjetosServlet?a=agregar&tipo=normal" class="btn btn-primary">Agregar Objeto Normal</a>
                    </div>
                    <div class="col-lg-3">
                        <a href="<%=request.getContextPath()%>/ObjetosServlet?a=agregar&tipo=vacuna" class="btn btn-primary">Agregar Objeto Vacuna</a>
                    </div>
                </div>
            </div>
            <div class="tabla">
                <table class="table table-dark table-transparent table-hover">
                    <thead>
                        <th>NOMBRE</th>
                        <th>PESO (kg)</th>
                        <th>TIPO</th>
                        <th>EFECTIVIDAD</th>
                        <th>EDITAR</th>
                    </thead>
                    <%
                        for (BObjeto bObjeto : listaObjetos) {
                    %>
                    <tr>
                        <td><%=bObjeto.getNombre()%></td>
                        <td><%=bObjeto.getMasa()%></td>
                        <td><%=bObjeto.getVacuna() == 1? "VACUNA": "NORMAL"%></td>
                        <%if(bObjeto.getVacuna() == 1){%>
                        <td><a href="<%=request.getContextPath()%>/ObjetosServlet?a=efectividad&id=<%=bObjeto.getIdObjeto()%>" class="btn btn-primary">Ver Efectividad</a></td>
                        <%}else{%>
                        <td>No aplica</td>
                        <%}%>

                        <%int a = 0;
                            for(int i=0;i<objetosEnInventario.size();i++){%>
                        <%if(bObjeto.getIdObjeto() == objetosEnInventario.get(i)){
                            a = 1;
                            break;
                        }else{
                            a = 0;
                            }%>
                        <%}%>

                        <%if(a == 1){%>
                        <td><a href="<%=request.getContextPath()%>/ObjetosServlet?a=editar&esta=EstaEnInventario&id=<%=bObjeto.getIdObjeto()%>" class="btn btn-primary">Editar</a></td>
                        <%}else{%>
                        <td><a href="<%=request.getContextPath()%>/ObjetosServlet?a=editar&esta=noEstaEnInventario&id=<%=bObjeto.getIdObjeto()%>" class="btn btn-primary">Editar</a></td>
                        <%}%>


                    </tr>
                    <%}%>
                </table>
            </div>

            <%}else{%>
            <div class="pb-5 pt-4 px-3 titlecolor">
                <div class="col-lg-8">
                    <h1 class='text-light'>Efectividad por tipo de Zombie</h1>
                </div>
                <div class="col-lg-2">
                    <a href="<%=request.getContextPath()%>/ObjetosServlet" class="btn btn-danger">Regresar</a>
                </div>
            </div>
            <div class="tabla">
                <table class="table table-dark table-transparent table-hover">
                    <thead>
                        <th>EFECTIVIDAD</th>
                        <th>TIPO DE ZOMBIE</th>
                    </thead>
                    <%
                        for (BObjeto bObjeto : listaEfectividad) {
                    %>
                    <tr>
                        <td><%=bObjeto.getMasa()%></td>  <!-- Efectividad -->
                        <td><%=bObjeto.getNombre()%></td> <!-- Tipo Zombie -->
                    </tr>
                    <%}%>
                </table>
            </div>
            <%}%>

        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>
