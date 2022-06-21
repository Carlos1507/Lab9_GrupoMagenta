<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar sticky-top navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="#">Apocalipsis zombie</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("humanos")? "active": "" %>" href="<%=request.getContextPath()%>/HumanosServlet">Humanos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("supervivientes")? "active": "" %>" href="<%=request.getContextPath()%>/SupervivientesServlet">Supervivientes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("virus")? "active": "" %>" href="<%=request.getContextPath()%>/VirusServlet">Virus</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("zombies")? "active": "" %>" href="<%=request.getContextPath()%>/ZombiesServlet">Zombies</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("objetos")? "active": "" %>" href="<%=request.getContextPath()%>/ObjetosServlet">Objetos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <%=request.getParameter("page").equals("caceria")? "active": "" %>" href="<%=request.getContextPath()%>/CaceriaServlet">Cacería</a>
            </li>
        </ul>
    </div>
</nav>

