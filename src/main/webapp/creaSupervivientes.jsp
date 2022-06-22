<%@ page import="com.example.lab9_grupomagenta.Beans.BSuperviviente" %>
<%@ page import="com.example.lab9_grupomagenta.Beans.BHumano" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9_grupomagenta.Beans.BSuperviviente>" scope="request" id="listaSupervivientes"/>
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

    <form method="POST" action="<%=request.getContextPath()%>/SupervivientesServlet?action=guardar">
        <h3>Registrar datos del sobreviviente</h3>

        <label>Nombre:</label>
        <input type="text" name="nombre" placeholder="Ingrese su nombre"/>
        <label>Apellido:</label>
        <input type="text" name="apellido" placeholder="Ingrese su apellido"/>
        <label>Peso:</label>
        <input name="peso" type="number" placeholder="Ingrese su peso" step="0.01" min="0" max="10000"/>
        <label>Fuerza:</label>
        <input name="fuerza" type="number" placeholder="Ingrese su fuerza (N)" step="0.01" min="0" max="10000"/>
        <label>Sexo:</label>
        <select name="sexo" class="form-control">
            <option selected="true" disabled="disabled">Elija una opción</option>
            <option value="masculino">Masculino</option>
            <option value="femenino">Femenino</option>
            <option value="otro">Otro</option>
        </select>
        <BR>
        <label>Pareja:</label>
        <select name="pareja" class="form-control">
            <option selected="true" disabled="disabled">Elija una opción</option>
            <% for (BSuperviviente superviviente : listaSupervivientes){%>
                <% if (superviviente.getPareja() == null){ %>
                    <option value="<%=superviviente.getNumIdentificacion()%>">
                        <%=superviviente.getNombre()%> <%=superviviente.getApellido()%>
                    </option>
                <%}%>
            <%}%>
        </select>
        <BR>
        <tr>
            <input type="submit" value="Guardar"/>
            <a style="border-radius: 20px;" class="btn btn-danger col-6"  href="<%=request.getContextPath()%>/SupervivientesServlet" class="btn btn-danger">Cancelar</a>
        </tr>
    </form>
    </body>
</html>