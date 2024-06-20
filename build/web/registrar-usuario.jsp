<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    
<%@include file="components/head.jsp" %>
<%@include file="components/body1.jsp" %>

<!-- 
<style>
    .formulario-contenedor {
        display: flex;
        align-items: flex-start;
    }
    .form-group {
        flex: 1;
        margin-right: 550px; /* Espacio entre formulario y cuadro de imagen */
    }
    .form-control-user {
        width: 900%; /* Asegura que los inputs ocupen todo el ancho disponible */
    }
    .image-box {
        width: 300px;
        height: 300px;
        border: 1px solid #ccc;
        display: flex;
        align-items: center;
        justify-content: center;
        text-align: center;
    }
    .btn-container {
        display: flex;
        justify-content: flex-start;
        
    }
    .btn-user {
        width: 100%; /* Ajusta el ancho del botón */
        padding: 10px; /* Ajusta el padding del botón para hacerlo más grande */
    }
</style> -->

<!--<div class="formulario-contenedor"> -->
    <!--<h1 class="h3 mb-2 text-gray-800">Registrar Empleado</h1>
    <p class="mb-4">A continuación podrá registrar la cuenta de un Empleado</p>-->
    
    <!--<form class="user" action="SvUsuarios" method="post">
        <p></p>
        
        <div class="form-group col">
            <div class="col-sm-6 mb-3">
                <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
                       placeholder="Nombre del empleado">
            </div>
            <div class="col-sm-6 mb-3">
                <input type="text" class="form-control form-control-user" id="apellido" name="apellido"
                       placeholder="Apellido del empleado">
            </div>
            <div class="col-sm-6 mb-3">
                <input type="email" class="form-control form-control-user" id="correo" name="correo"
                       placeholder="Correo electrónico">
            </div>
            <div class="col-sm-6 mb-3">
                <input type="password" class="form-control form-control-user" id="clave" name="clave"
                       placeholder="Contraseña">
            </div>
        </div>
        
        <button class="btn btn-primary btn-user btn-block" type="submit">Registrar Usuario</button>
        
        <hr>
    </form> -->
    
    <h1 class="h3 mb-2 text-gray-800">Registrar Empleado</h1>
    <p class="mb-4">A continuación podrá realizar el registro del empleado</p>

    <form class="user" action="SvUsuarios" method="post">
        <p>${message}</p>

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

