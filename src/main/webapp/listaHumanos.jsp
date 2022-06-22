<%@ page import="com.example.lab9_grupomagenta.Beans.BHumano" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9_grupomagenta.Beans.BHumano>" scope="request" id="listaHumanos"/>
<html>
<jsp:include page="/static/head.jsp">
    <jsp:param name="title" value="Lista de Humanos"/>
</jsp:include>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="page" value="humanos"/>
    </jsp:include>
    <div class="pb-5 pt-4 px-3 titlecolor">
        <div class="col-lg-6">
            <h1 class='text-light'>Lista de Humanos</h1>
        </div>
    </div>
    <div class="tabla">
        <table class="table table-dark table-transparent table-hover">
            <thead>
            <th>IDENTIFICACION</th>
            <th>NOMBRE Y APELLIDO</th>
            <th>SEXO</th>
            <th>ESTADO</th>
            </thead>
            <% for (BHumano humano : listaHumanos) { %>
            <%  if(humano.isHabilitado()){ %>
            <tr>
                <td><%=humano.getNumIdentificacion()%></td>
                <td><%=humano.getNombre()%> <%=humano.getApellido()%></td>
                <td><%=humano.getSexo()%></td>
                <td><%=humano.getEstado()%></td>
            </tr>
            <% }} %>
        </table>
    </div>

</div>
<jsp:include page="/static/scripts.jsp"/>
</body>
</html>
