<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    
<%@include file="components/head.jsp" %>
<%@include file="components/body1.jsp" %>

<h1 class="h3 mb-2 text-gray-800">Registrar Compra</h1>
<p class="mb-4">A continuación podrá realizar el registro de la compra</p>

<form class="user" action="SvUsuarios" method="post">
    <p>${message}</p>

    <div class="form-group">
        <input type="text" class="form-control form-control-user" id="proveedor" name="proveedor"
               placeholder="Proveedor" required>
    </div>

    <div class="form-group">
        <input type="text" class="form-control form-control-user" id="empleado" name="empleado"
               placeholder="Empleado" required>
    </div>

    <div class="form-group">
        <input type="text" class="form-control form-control-user" id="producto" name="producto"
               placeholder="Producto" required>
    </div>

    <div class="form-group">
        <input type="text" class="form-control form-control-user" id="precio" name="precio"
               placeholder="Precio" required>
    </div>
    
    <div class="form-group">
        <input type="text" class="form-control form-control-user" id="cantidad" name="cantidad"
               placeholder="Cantidad" required>
    </div>
    
    <div class="form-group">
        <input type="date" class="form-control form-control-user" id="fecha" name="fecha"
               placeholder="Fecha" required>
    </div>

    <button class="btn btn-success btn-user btn-block" type="submit">Registrar Compra</button>
    <hr>
</form>

<%@include file="components/body2.jsp" %>   
    
</html>

