<%@ page import="com.example.lab9_grupomagenta.Beans.BHumano" %>
<%@ page import="com.example.lab9_grupomagenta.Beans.BVirus" %>
<%@ page import="com.example.lab9_grupomagenta.Beans.BVariante" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9_grupomagenta.Beans.BVirus>" class="java.util.ArrayList" scope="request" id="listaVirus"/>
<jsp:useBean type="java.util.ArrayList<com.example.lab9_grupomagenta.Beans.BVariante>" class="java.util.ArrayList" scope="request" id="listaVari"/>
<jsp:useBean type="java.lang.String" scope="request" id="cambio"/>
<jsp:useBean type="java.lang.String" class="java.lang.String" scope="request" id="idVirus"/>

<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Lista Virus"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="virus"/>
            </jsp:include>

            <div class="pb-5 pt-4 px-3 titlecolor">
                <div class="row gx-lg-5">
                    <%if(cambio.equals("noMuestraVariante")){%>
                    <div class="col-lg-8">
                        <h1 class='text-light'>Lista Virus</h1>
                    </div>
                    <div class="col-lg-3">
                        <p class="btn btn-warning">Virus activos: <%=listaVirus.size()%> </p>
                    </div>
                    <%}else{%>
                    <div class="col-lg-8">
                        <h1 class='text-light'>Lista Variantes</h1>
                    </div>
                    <div>
                        <a href="<%=request.getContextPath()%>/VirusServlet" class="btn btn-primary">Regresar</a>
                    </div>
                    <%}%>
                </div>
            </div>


            <%if(cambio.equals("noMuestraVariante")){%>



            <div class="container" style=" width: 75%">
                <form method="post" action="<%=request.getContextPath()%>/VirusServlet?a=crearNuevoVirus">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="Ingrese nombre de nuevo virus"
                               aria-label="Ingrese nombre de nuevo virus" aria-describedby="button-addon2"
                               name="nameVirus">
                        <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Crear nuevo virus</button>
                    </div>
                </form>
            </div>

            </hr>

            <div class="tabla">
                <table class="table table-dark table-transparent table-hover">
                    <thead>
                        <th>ID VIRUS</th>
                        <th>VIRUS</th>
                        <th>CASOS ENCONTRADOS</th>
                        <th>VARIANTES</th>
                    </thead>

                    <%
                        for (BVirus virus : listaVirus) {
                    %>
                    <tr>
                        <td><%=virus.getIdVirus()%></td>
                        <td><%=virus.getNombre()%></td>
                        <td><%=virus.getCasosEncontrados()%></td>
                        <td><a href="<%=request.getContextPath()%>/VirusServlet?a=variante&id=<%=virus.getIdVirus()%>" class="btn btn-primary">Ver Variantes</a></td>
                    <tr>
                    <%
                        }
                    %>
                </table>
            </div>

            <%}else{%>

            <div class="container" style=" width: 75%">
                <form method="post" action="<%=request.getContextPath()%>/VirusServlet?a=crearNuevaVariante&id=<%=idVirus%>">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="Ingrese nombre de nueva variante"
                               aria-label="Ingrese nombre de nueva variante" aria-describedby="button-addon2"
                               name="nameVariante">
                        <button class="btn btn-outline-secondary" type="submit" id="button-addon3">Crear nueva variante</button>
                    </div>
                </form>
            </div>



            </hr>

            <div class="tabla">
                <table class="table table-dark table-transparent table-hover">
                    <thead>
                        <th>ID VARIANTE</th>
                        <th>VARIANTE</th>-
                        <th>ELIMINAR</th>
                    </thead>

                    <%
                        for (BVariante variante : listaVari) {
                            if(variante.getIdVirus() == Integer.parseInt(idVirus)){
                    %>
                    <tr>
                        <td><%=variante.getIdVarianteVirus()%></td>
                        <td><%=variante.getNombre()%></td>
                        <td><a href="<%=request.getContextPath()%>/VirusServlet?a=eliminarVariante&id=<%=variante.getIdVarianteVirus()%>" class="btn btn-danger">Elimnar</a></td>
                    <tr>
                        <%}%>
                    <%}%>
                </table>
            </div>
            <%}%>

        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>
