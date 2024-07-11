<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Movimiento, model.Contacto, model.Empleado, model.Producto, model.TipoMovimiento, java.util.List, java.text.SimpleDateFormat, java.text.ParseException, java.util.Date"%>
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

<form class="user" action="SvEditProductos" method="post">
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
            <%= contacto.getNombre() %>
            </option>
            
            <% } %>
        </select>
    </div>
        
    <div class="form-group">
        <select class="custom-select custom-select-user form-control form-control-sm" name="empleado" title="empleado" required>
            <option value="" disabled selected>Empleado</option>
            <!-- CAMBIAR ESTO POR SOLO EL EMPLEADO ACTUAL -->
            
            <% for (Empleado empleado : empleados) { %>
            
            <option value="<%= empleado.getId() %>" 
            <% // seleccionamos los datos anteriores
                if (empleado.getId() == movimiento.getEmpleado().getId()) { 
            %>
            selected
            <% } %>>
            <%= empleado.getNombre() %>
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
        <input type="date" class="form-control form-control-user" id="fecha" name="fecha"
               placeholder="Fecha" required value="<%= movimiento.getFechaMovimiento().toString() %>">
        <% 
        
        String fechaOriginal = "Sun Jul 05 00:00:00 PET 2020";
        SimpleDateFormat formatoOriginal = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

        try {
            Date fecha = formatoOriginal.parse(fechaOriginal);
            SimpleDateFormat formatoDeseado = new SimpleDateFormat("yyyy-MM-dd");
            String fechaFormateada = formatoDeseado.format(fecha);

            System.out.println("Fecha formateada: " + fechaFormateada);
        } catch (ParseException e) {
            System.err.println("Error al analizar la fecha: " + e.getMessage());
        }
                    
        %>
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


<%@include file="components/body2.jsp" %>  
</html>
