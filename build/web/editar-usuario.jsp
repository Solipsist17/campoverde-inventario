<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Empleado"%>
<!DOCTYPE html>
<html lang="en">
    
<%@include file="components/head.jsp" %>
<%@include file="components/body1.jsp" %>

    <h1 class="h3 mb-2 text-gray-800">Editar Empleado</h1>
    <p class="mb-4">A continuación podrá realizar la edición del empleado</p>

    <% 
        Empleado empleado = (Empleado) request.getSession().getAttribute("usuEditar");
    %>
    <form class="user" action="SvEditUsuarios" method="post">
        <p>${message}</p>

        <div class="form-group">
            <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
                       placeholder="Nombre del empleado" required value="<%= empleado.getNombre() %>">
        </div>

        <div class="form-group">
            <input type="text" class="form-control form-control-user" id="apellido" name="apellido"
                       placeholder="Apellido del empleado" required value="<%= empleado.getApellido() %>">
        </div>

        <div class="form-group">
            <input type="email" class="form-control form-control-user" id="correo" name="correo"
                       placeholder="Correo electrónico" required value="<%= empleado.getCorreo() %>">
        </div>

        <div class="form-group">
            <input type="password" class="form-control form-control-user" id="clave" name="clave"
                       placeholder="Contraseña" required value="<%= empleado.getClave() %>">
        </div>

        <button class="btn btn-success btn-user btn-block" type="submit">Guardar Modificación</button>
        <hr>
    </form>


<%@include file="components/body2.jsp" %>   
    
</html>

