<%@ page import="com.example.lab9_grupomagenta.Beans.BHumano" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.lang.String" scope="request" id="tipo"/>
<html>
    <jsp:include page="/static/head.jsp">
        <jsp:param name="title" value="Lista de Humanos"/>
    </jsp:include>
    <body>
        <div class='container'>
            <jsp:include page="/includes/navbar.jsp">
                <jsp:param name="page" value="humanos"/>
            </jsp:include>

            <%if(tipo.equalsIgnoreCase("normal")){%>
            <div class='container'>
                <h1 class="mb-3" style="color: white">Crear un nuevo objeto: normal</h1>
                <form method="POST" action="<%=request.getContextPath()%>/ObjetosServlet?a=guardarNormal">
                    <div class="mb-3" style="color: dodgerblue">
                        <label for="nombreNormal" class="form-label">Nombre</label>
                        <input type="text" class="form-control" name="nombre1" id="nombreNormal">
                    </div>
                    <div class="mb-3" style="color: dodgerblue">
                        <label for="pesoNormal" class="form-label">Peso (kg)</label>
                        <input type="number" class="form-control" name="peso1" id="pesoNormal" min="0" step="0.001">
                    </div>
                    <a href="<%=request.getContextPath()%>/ObjetosServlet" class="btn btn-danger">Regresar</a>
                    <button type="submit" class="btn btn-primary">Crear Objeto</button>
                </form>
            </div>

            <%}else{%>
            <div class='container'>
                <h1 class="mb-3" style="color: white">Crear un nuevo objeto: vacuna</h1>
                <form method="POST" action="<%=request.getContextPath()%>/ObjetosServlet?a=guardarVacuna">
                    <div class="mb-3" style="color: dodgerblue">
                        <label for="nombreVacuna" class="form-label">Nombre</label>
                        <input type="text" class="form-control" name="nombre" id="nombreVacuna">
                    </div>
                    <div class="mb-3" style="color: dodgerblue">
                        <label for="pesoVacuna" class="form-label">Peso (kg)</label>
                        <input type="number" placeholder="0.000" name="peso" id="pesoVacuna" min="0" max="0.5" step="0.001">
                    </div>
                    <div class="mb-3" style="color: dodgerblue">
                        <label for="efect1" class="form-label">Efectividad en "Demoledor" (%)</label>
                        <input type="number" placeholder="porcentaje" name="ef1" id="efect1" min="0" max="100" step="0.001">
                    </div>
                    <div class="mb-3" style="color: dodgerblue">
                        <label for="efect2" class="form-label">Efectividad en "Rapido" (%)</label>
                        <input type="number" placeholder="porcentaje" name="ef2" id="efect2" min="0" max="100" step="0.001">
                    </div>
                    <div class="mb-3" style="color: dodgerblue">
                        <label for="efect3" class="form-label">Efectividad en "Niño" (%)</label>
                        <input type="number" placeholder="porcentaje" name="ef3" id="efect3" min="0" max="100" step="0.001">
                    </div>
                    <div class="mb-3" style="color: dodgerblue">
                        <label for="efect4" class="form-label">Efectividad en "Normal" (%)</label>
                        <input type="number" placeholder="porcentaje" name="ef4" id="efect4" min="0" max="100" step="0.001">
                    </div>
                    <div class="mb-3" style="color: dodgerblue">
                        <label for="efect5" class="form-label">Efectividad en "Otro" (%)</label>
                        <input type="number" placeholder="porcentaje" name="ef5" id="efect5" min="0" max="100" step="0.001">
                    </div>
                    <a href="<%=request.getContextPath()%>/ObjetosServlet" class="btn btn-danger">Regresar</a>
                    <button type="submit" class="btn btn-primary">Crear Objeto</button>
                </form>
            </div>
            <%}%>

        </div>
        <jsp:include page="/static/scripts.jsp"/>
    </body>
</html>

