<%@ page import="com.example.lab9_grupomagenta.Beans.BVariante" %>
<%@ page import="com.example.lab9_grupomagenta.Beans.BTipoZombie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean type="java.util.ArrayList<com.example.lab9_grupomagenta.Beans.BTipoZombie>" scope="request" id="listaTipoZombie"/>
<jsp:useBean type="java.util.ArrayList<com.example.lab9_grupomagenta.Beans.BVariante>" scope="request" id="listaVariantes"/>
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
            <h1 class='text-light'><button type="button" class="btn btn-outline-success ">regresar</button>Agregar un Zombie
            </h1>
        </div>
        <form class="row g-3 needs-validation" novalidate method="POST" action="<%=request.getContextPath()%>/ZombiesServlet?a=agregar">

            <div>
                <div class="row g-2">
                    <div class="col-6">
                        <div class="p-3">
                            <div class="mb-3">
                                <label class="form-label text-light">Nombre</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" required>
                            </div>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="p-3"> <div class="mb-3">
                            <label class="form-label text-light">Apellido</label>
                            <input type="text" class="form-control" id="Apellido" name="apellido"required>
                        </div>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="p-3"> <div class="mb-3">
                            <label class="form-label text-light">Numero Identificacion</label>
                            <input type="number"  class="form-control" id="NIdentificacion" name="nIdentificacion" required>
                        </div>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="p-3">
                            <div class="mb-3">
                                <label class="form-label text-light">Selecione tipo de Zombie</label >
                                <select class="form-control" aria-label="Default select example" name="tipoZombie" required>
                                    <option selected disabled value>----------</option>
                                    <%for(BTipoZombie tipoZombie : listaTipoZombie){%>
                                    <option value="<%=tipoZombie.getIdTipoZombie()%>"><%=tipoZombie.getNombre()%></option>
                                    <%}%>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="p-3">
                            <div class="mb-3">
                                <label for="validation1" class="form-label text-light">Selecione variante de virus:</label>
                                <select class="form-control"  name="variante" id="validation1" required>
                                    <option selected disabled value>----------</option>
                                    <%for(BVariante variante : listaVariantes){%>
                                    <option value="<%=variante.getIdVarianteVirus()%>"><%=variante.getNombre()%></option>
                                    <%}%>
                                </select>
                            </div>
                        </div>
                        </div>
                    <div class="col-6">
                        <div class="p-3">
                            <div class="mb-3">
                                <label class="form-label text-light">Selecione sexo:</label>
                                <select class="form-control" aria-label="Default select example" name="sexo" required>
                                    <option selected disabled value>----------</option>
                                    <option value="hombre">Hombre</option>
                                    <option value="mujer">Mujer</option>
                                    <option value="otro">Otro</option>
                                </select>
                            </div>
                        </div>
                    </div>

                </div>

                </div>

        <button type="submit" class="btn btn-primary">Agregar</button>
        </form>
    </div>

    </div>
    <div>
</div>
<jsp:include page="/static/scripts.jsp"/>
<script>
    var forms = document.querySelectorAll('.needs-validation');

    Array.prototype.slice.call(forms).forEach(function (form) {
        form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }

            form.classList.add('was-validated');
        }, false);
    });

</script>

</body>



</html>
