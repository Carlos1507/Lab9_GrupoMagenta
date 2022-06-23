<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="com.example.lab9_grupomagenta.Beans.BSuperviviente" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9_grupomagenta.Beans.BSuperviviente>" scope="request" id="listaSupervivientes"/>
<jsp:useBean id="supervSelect" type="com.example.lab9_grupomagenta.Beans.BSuperviviente" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="estilos.css">
    <title>Nuevo Superviviente</title>
</head>
<body>

<form method="POST" action="<%=request.getContextPath()%>/SupervivientesServlet?action=actualizar">
    <h3>Editar datos del sobreviviente</h3>

    <label>Nombre:</label>
    <input type="text" name="nombre" value="<%=supervSelect.getNombre()%>"/>
    <label>Apellido:</label>
    <input type="text" name="apellido" value="<%=supervSelect.getApellido()%>"/>
    <label>Peso:</label>
    <input name="peso" type="number" value="<%=supervSelect.getPeso()%>" step="0.01" min="0" max="10000"/>
    <label>Fuerza:</label>
    <input name="fuerza" type="number" value="<%=supervSelect.getFuerza()%>" step="0.01" min="0" max="10000"/>
    <BR>
    <label>Pareja:</label>
    <select name="pareja" class="form-control">
        <option selected="true"><%=supervSelect.getPareja().getNombre()%> <%=supervSelect.getPareja().getApellido()%></option>
        <% for (BSuperviviente superviviente : listaSupervivientes){%>
        <% if (superviviente.getPareja() == null && superviviente.isHabilitado()){ %>
        <option value="<%=superviviente.getNumIdentificacion()%>">
            <%=superviviente.getNombre()%> <%=superviviente.getApellido()%>
        </option>
        <%}%>
        <%}%>
    </select>
    <BR>
    <tr>
        <input type="submit" value="Actualizar"/>
        <a style="border-radius: 20px;" class="btn btn-danger col-6"  href="<%=request.getContextPath()%>/SupervivientesServlet" class="btn btn-danger">Cancelar</a>
    </tr>
</form>
</body>
</html>
