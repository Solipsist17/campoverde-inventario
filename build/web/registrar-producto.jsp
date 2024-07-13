<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Categoria, model.Ubicacion, java.util.ArrayList, java.util.List" %>

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


<h1 class="h3 mb-2 text-gray-800">Registrar Producto</h1>
<p class="mb-4">A continuación podrá realizar el registro del producto</p>

<% 
    List<Categoria> categorias = (List) request.getSession().getAttribute("listaCategorias");
    List<Ubicacion> ubicaciones = (List) request.getSession().getAttribute("listaUbicaciones");
%>

<form class="user" action="SvProductos" method="post">
    <p>${message}</p>

    <div class="form-group">
        <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
               placeholder="Nombre" required>
    </div>

    <div class="form-group">
        <select class="custom-select custom-select-user form-control form-control-sm" name="categoria" title="categoria" required>
            <option value="" disabled selected>Categoría</option>
            <% if (categorias != null) { %>
            <% for (Categoria categoria : categorias) { %>
            <option value="<%= categoria.getId() %>"><%= categoria.getNombre() %></option>
            <% } %>
            <% } %>
        </select>
    </div>
    
    <div class="form-group">
        <select class="custom-select custom-select-user form-control form-control-sm" name="ubicacion" title="ubicacion" required>
            <option value="" disabled selected>Ubicación</option>
            <% if (ubicaciones != null) { %>
            <% for (Ubicacion ubicacion : ubicaciones) { %>
            <option value="<%= ubicacion.getId() %>"><%= ubicacion.getDireccion() %></option>
            <% } %>
            <% } %>
        </select>
    </div>

    <div class="form-group">
        <input type="number" min="0" step="1" class="form-control form-control-user" id="stock" name="stock"
               placeholder="Stock" required>
    </div>
    
    <div class="form-group">
        <input type="number" min="0" step="0.01" class="form-control form-control-user" id="precio" name="precio"
               placeholder="Precio" required>
    </div>

    <button class="btn btn-success btn-user btn-block" type="submit">Registrar Producto</button>
    <hr>
</form>

<%@include file="components/body2.jsp" %>   
    
</html>

