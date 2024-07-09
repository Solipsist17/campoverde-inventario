<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Contacto, model.Empleado, model.Producto, java.util.ArrayList, java.util.List" %>

<!DOCTYPE html>
<html lang="en">
    
<%@include file="components/head.jsp" %>
<%@include file="components/body1.jsp" %>

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


        
<h1 class="h3 mb-2 text-gray-800">Registrar Movimiento</h1>
<p class="mb-4">A continuación podrá realizar el registro del movimiento</p>

<% 
    List<Contacto> contactos = (List) request.getSession().getAttribute("listaContactos");
    List<Empleado> empleados = (List) request.getSession().getAttribute("listaEmpleados");
    List<Producto> productos = (List) request.getSession().getAttribute("listaProductos");
%>

<form class="user" action="SvMovimientos" method="post">
    <p>${message}</p>

    <div class="form-group">
        <select class="custom-select custom-select-user form-control form-control-sm" name="contacto" title="contacto" required>
            <option value="" disabled selected>Contacto</option>
            <% if (contactos != null) { %>
            <% for (Contacto contacto : contactos) { %>
            <option value="<%= contacto.getId() %>"><%= contacto.getNombre() %> - <%= contacto.getTipoContacto() %></option>
            <% } %>
            <% } %>
        </select>
    </div>
    
    <div class="form-group">
        <select class="custom-select custom-select-user form-control form-control-sm" name="empleado" title="empleado" required>
            <option value="" disabled selected>Empleado</option>
            <% 
                Empleado usuarioActual = (Empleado) request.getSession().getAttribute("usuario");
                if (usuarioActual != null) { 
            %>
            <option value="<%=usuarioActual.getId()%>" ><%=usuarioActual.getNombre()%> <%=usuarioActual.getApellido()%></option>
            <% } %>
            
            <!--
            <% if (empleados != null) { %>
            <% for (Empleado empleado : empleados) { %>
            <option value="<%= empleado.getId() %>"><%= empleado.getNombre() %> <%= empleado.getApellido() %></option>
            <% } %>
            <% } %>
            -->
        </select>
    </div>
        
    <div class="form-group">
        <select class="custom-select custom-select-user form-control form-control-sm" name="producto" title="producto" required>
            <option value="" disabled selected>Producto</option>
            <% if (productos != null) { %>
            <% for (Producto producto : productos) { %>
            <option value="<%= producto.getId() %>"><%= producto.getNombre() %></option>
            <% } %>
            <% } %>
        </select>
    </div>    
    
    <div class="form-group">
        <input type="number" min="0" step="0.01" class="form-control form-control-user" id="precio" name="precio"
               placeholder="Precio" required>
    </div>
        
    <div class="form-group">
        <input type="number" min="0" step="1" class="form-control form-control-user" id="cantidad" name="cantidad"
               placeholder="Cantidad" required>
    </div>
        
    <div class="form-group">
        <input type="date" class="form-control form-control-user" id="fecha" name="fecha"
               placeholder="Fecha" required>
    </div>
        
    <div class="form-group">
        <select class="custom-select custom-select-user form-control form-control-sm" name="tipo" title="tipo" required>
            <option value="" disabled selected>Tipo</option>
            <option value="compra">COMPRA</option>
            <option value="venta">VENTA</option>
        </select>
    </div>  

    <button class="btn btn-success btn-user btn-block" type="submit">Registrar Producto</button>
    <hr>
</form>

<%@include file="components/body2.jsp" %>   
    
</html>

