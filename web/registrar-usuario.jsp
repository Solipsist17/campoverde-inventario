<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    
<%@include file="components/head.jsp" %>
<%@include file="components/body1.jsp" %>

<!-- Altert Message -->
<%
Object messageObj = miSession.getAttribute("message");
if (messageObj != null) {
    int message = Integer.parseInt(messageObj.toString());
%>
<div class="alert alert-<%= (message == 1) ? "success" : "danger" %> alert-dismissible fade show" role="alert">
    <%= (message == 1) ? "¡Registro exitoso!" : "¡Registro fallido!" %>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%
    miSession.setAttribute("message", null);
}
%>
    
    <h1 class="h3 mb-2 text-gray-800">Registrar Empleado</h1>
    <p class="mb-4">A continuación podrá realizar el registro del empleado</p>

    <form class="user" action="SvUsuarios" method="post">

        <div class="form-group">
            <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
                       placeholder="Nombre del empleado" required>
        </div>

        <div class="form-group">
            <input type="text" class="form-control form-control-user" id="apellido" name="apellido"
                       placeholder="Apellido del empleado" required>
        </div>

        <div class="form-group">
            <input type="email" class="form-control form-control-user" id="correo" name="correo"
                       placeholder="Correo electrónico" required>
        </div>

        <div class="form-group">
            <input type="password" class="form-control form-control-user" id="clave" name="clave"
                       placeholder="Contraseña" required>
        </div>

        <button class="btn btn-success btn-user btn-block" type="submit">Registrar Usuario</button> <!-- btn-info-->
        <hr>
    </form>


        
<!--</div> -->

<%@include file="components/body2.jsp" %>   
    
</html>

