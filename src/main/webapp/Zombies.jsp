<%@ page import="com.example.lab9_grupomagenta.Beans.BZombie" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9_grupomagenta.Beans.BZombie>" scope="request" id="listaZombies"/>
<jsp:useBean type="java.lang.String" scope="request" id="comunVariante"/>
<jsp:useBean type="java.util.HashMap" scope="request" id="porcentajes"/>
<jsp:useBean type="java.util.HashMap" scope="request" id="promedios"/>


<html>
<jsp:include page="/static/head.jsp">
    <jsp:param name="title" value="Zombies"/>
</jsp:include>
<body>
<div class='container'>
    <jsp:include page="/includes/navbar.jsp">
        <jsp:param name="page" value="zombies"/>
    </jsp:include>
    <div class="pb-5 pt-4 px-3 titlecolor">
        <div class="col-lg-6">
            <h1 class='text-light'>Lista de Zombies
                <a href="<%=request.getContextPath()%>/ZombiesServlet?a=agregar"><button type="button" class="btn btn-outline-success" >Agregar Zombie</button>
                </a>
            </h1>
        </div>
    </div>
    <div class="tabla">
        <table class="table table-dark table-transparent table-hover">
            <thead>
            <th>NOMBRE Y APELLIDO</th>
            <th>SEXO</th>
            <th>NUMERO IDENTIFICACIÓN</th>
            <th>HORAS INFECTADO</th>
            <th>VARIANTE VIRUS</th>
            <th>VICTIMAS</th>
            <th>TIPO ZOMBIE</th>
            </thead>
            <%
                for (BZombie zombie : listaZombies) {
            %>
            <tr>
                <td><%=zombie.getNombre()+" "+zombie.getApellido()%></td>
                <td><%=zombie.getSexo()%></td>
                <td><%=zombie.getNumIdentificacion()%></td>
                <td><%=zombie.getTiempoInfectado()%></td>
                <td><%=zombie.getVarianteVirus()%></td>
                <td><%=zombie.getVictimas()%></td>
                <td><%=zombie.getTipoZombie()%></td>
            </tr>
            <%
                }
            %>
        </table>
        <h1 class='text-light'>Datos Estadisticos:</h1>
        <h5 class='text-light'>Variante más común:</h5>
        <p1 class='text-light'>La variate de virus más común entre los zoombies es: <%=comunVariante%> </p1>
        </br>
        <h5 class='text-light'>Porcentajes:</h5>
        <p1 class='text-light'>Porcentaje de zombies hombre: <%=Double.parseDouble((String) porcentajes.get("masculino"))*100 +"%"%> </p1>
        </br>
        <p1 class='text-light'>Porcentaje de zombies mujeres: <%=Double.parseDouble((String) porcentajes.get("femenino"))*100 +"%"%> </p1>
        </br>
         <p1 class='text-light'>Porcentaje de zombies otros: <%=Double.parseDouble((String) porcentajes.get("otro"))*100 +"%"%> </p1>
        </br>
        <h5 class='text-light'>Promedios:</h5>
        <p1 class='text-light'>Promedio de zombies Demoledores: <%=Double.parseDouble((String) promedios.get("Demoledor"))%> </p1>
        </br>
        <p1 class='text-light'>Promedio de zombies Rápido:
        <%=Double.parseDouble((String) promedios.get("Rápido"))%>
        </p1>
        </br>
        <p1 class='text-light'>Promedio de zombies Niño :<%=Double.parseDouble((String) promedios.get("Niño"))%>
        </p1>
        </br>
</div>
<jsp:include page="/static/scripts.jsp"/>
</body>
</html>
