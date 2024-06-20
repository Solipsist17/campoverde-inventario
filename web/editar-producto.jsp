<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Producto, model.Categoria, model.Ubicacion, java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="components/head.jsp" %>
<%@include file="components/body1.jsp" %>

    <h1 class="h3 mb-2 text-gray-800">Editar Producto</h1>
    <p class="mb-4">A continuación podrá realizar la edición del producto</p>

<% 
    Producto producto = (Producto) request.getSession().getAttribute("prodEditar");

    List<Categoria> categorias = (List) request.getSession().getAttribute("listaCategorias");
    List<Ubicacion> ubicaciones = (List) request.getSession().getAttribute("listaUbicaciones");
%>

<form class="user" action="SvEditProductos" method="post">
    <p>${message}</p>

    <div class="form-group">
        <input type="text" class="form-control form-control-user" id="nombre" name="nombre"
               placeholder="Nombre" required value="<%= producto.getNombre() %>">
    </div>

    <div class="form-group">
        <select class="custom-select custom-select-user form-control form-control-sm" name="categoria" title="categoria" required>
            <option value="" disabled selected>Categoría</option>
            <% for (Categoria categoria : categorias) { %>
            
            <option value="<%= categoria.getId() %>" 
            <% 
                if (categoria.getId() == producto.getCategoria().getId()) { 
            %>
            selected
            <% } %>>
            <%= categoria.getNombre() %>
            </option>
            
            <% } %>
        </select>
    </div>
    
    <div class="form-group">
        <select class="custom-select custom-select-user form-control form-control-sm" name="ubicacion" title="ubicacion" required value="<%= producto.getUbicacion().getId() %>">
            <option value="" disabled selected>Ubicación</option>
            <% for (Ubicacion ubicacion : ubicaciones) { %>
            
            <option value="<%= ubicacion.getId() %>" 
            <% 
                if (ubicacion.getId() == producto.getUbicacion().getId()) { 
            %>
            selected
            <% } %>>
            <%= ubicacion.getDireccion() %>
            </option>
            
            <% } %>
        </select>
    </div>

    <div class="form-group">
        <input type="number" min="0" step="1" class="form-control form-control-user" id="stock" name="stock"
               placeholder="Stock" required value="<%= producto.getStock() %>">
    </div>
    
    <div class="form-group">
        <input type="number" min="0" step="0.01" class="form-control form-control-user" id="precio" name="precio"
               placeholder="Precio" required value="<%= producto.getPrecio() %>">
    </div>

    <button class="btn btn-success btn-user btn-block" type="submit">Editar Producto</button>
    <hr>
</form>


<%@include file="components/body2.jsp" %>  
</html>
