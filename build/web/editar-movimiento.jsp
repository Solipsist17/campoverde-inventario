<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Movimiento, model.Contacto, model.Empleado, model.Producto, model.TipoMovimiento, java.util.List, java.time.LocalDateTime, java.time.ZoneId, java.time.ZonedDateTime, java.time.format.DateTimeFormatter, java.util.Locale"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="components/head.jsp" %>
<%@include file="components/body1.jsp" %>

    <h1 class="h3 mb-2 text-gray-800">Editar Movimiento</h1>
    <p class="mb-4">A continuación podrá realizar la edición del movimiento</p>

<% 
    Movimiento movimiento = (Movimiento) request.getSession().getAttribute("movEditar");

    List<Contacto> contactos = (List) request.getSession().getAttribute("listaContactos");
    List<Empleado> empleados = (List) request.getSession().getAttribute("listaEmpleados");
    List<Producto> productos = (List) request.getSession().getAttribute("listaProductos");
%>

<% if (contactos != null && empleados != null && productos != null) { %>
<form class="user" action="SvEditMovimientos" method="post">
    <p>${message}</p>

    <div class="form-group">
        <select class="custom-select custom-select-user form-control form-control-sm" name="contacto" title="contacto" required>
            <option value="" disabled selected>Contacto</option>
            
            <% for (Contacto contacto : contactos) { %>
            <option value="<%= contacto.getId() %>" 
            <% // seleccionamos los datos anteriores
                if (contacto.getId() == movimiento.getContacto().getId()) { 
            %>
            selected
            <% } %>>
            <%= contacto.getNombre() %> - <%= contacto.getTipoContacto() %>
            </option>
            
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
            <option value="<%= usuarioActual.getId() %>" 
            <% // seleccionamos los datos anteriores
                if (usuarioActual.getId() == movimiento.getEmpleado().getId()) {
            %>
            selected
            <%  } %>>
            <%=usuarioActual.getNombre()%> <%=usuarioActual.getApellido()%>
            </option>
            
            <% } %>
        </select>
    </div>    
        
    <div class="form-group">
        <select class="custom-select custom-select-user form-control form-control-sm" name="producto" title="producto" required>
            <option value="" disabled selected>Producto</option>            
            <% for (Producto producto : productos) { %>
            
            <option value="<%= producto.getId() %>" 
            <% // seleccionamos los datos anteriores
                if (producto.getId() == movimiento.getProducto().getId()) { 
            %>
            selected
            <% } %>>
            <%= producto.getNombre() %>
            </option>
            
            <% } %>
        </select>
    </div>  
        
    <div class="form-group">
        <input type="number" min="0" step="0.01" class="form-control form-control-user" id="precio" name="precio"
               placeholder="Precio" required value="<%= movimiento.getPrecio() %>">
    </div>
    
    <div class="form-group">
        <input type="number" min="0" step="1" class="form-control form-control-user" id="cantidad" name="cantidad"
               placeholder="Cantidad" required value="<%= movimiento.getCantidad() %>">
    </div>
    
    <div class="form-group">
        <% 
        // FORMATEAR LA FECHA
        
        // La fecha en formato inicial
        String dateStr = movimiento.getFechaMovimiento().toString();

        // Definir el formateador para el formato inicial
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        // Parsear la fecha inicial a un objeto ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateStr, formatter);

        // Convertir a LocalDateTime
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

        // Definir el formateador para el formato final
        DateTimeFormatter finalFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Formatear la fecha a YYYY-MM-DD
        String formattedDate = localDateTime.format(finalFormatter);
        %>
        
        <input type="date" class="form-control form-control-user" id="fecha" name="fecha"
               placeholder="Fecha" required value="<%= formattedDate %>">
        
    </div>
    
    <div class="form-group">
        <select class="custom-select custom-select-user form-control form-control-sm" name="tipo" title="tipo" required>
            <option value="" disabled selected>Tipo</option>
            <% for (TipoMovimiento tipo : TipoMovimiento.values()) { %>
            
            <option value="<%= tipo.toString().toLowerCase() %>"
            <% 
                if (tipo == movimiento.getTipoMovimiento()) {
            %>
            selected
            <% } %>>
            <%= tipo.toString() %>
            </option>
            <% } %>
        </select>
    </div>
    
    

    <button class="btn btn-success btn-user btn-block" type="submit">Editar Movimiento</button>
    <hr>
</form>
<% } %>

<%@include file="components/body2.jsp" %>  
</html>
