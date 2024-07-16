<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Producto, java.util.ArrayList, java.util.List" %>

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
    <%= (message == 1) ? "¡Acción exitosa!" : "¡Acción fallida!" %>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%
    miSession.setAttribute("message", null);
}
%>

<!-- Begin Page Content -->
<div class="container-fluid">
     <p>${message}</p>
     
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Consultar Productos</h1>
    <p class="mb-4">A continuación podrá visualizar la lista completa de los productos</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-success">Productos</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>ID Producto</th>
                            <th>Categoria</th>
                            <th>Ubicación</th>
                            <th>Nombre</th>
                            <th>Stock</th>
                            <th>Precio</th>
                            <th style="width: 210px">Acción</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>ID Producto</th>
                            <th>Categoría</th>
                            <th>Ubicación</th>
                            <th>Nombre</th>
                            <th>Stock</th>
                            <th>Precio</th>
                            <th style="width: 210px">Acción</th>
                        </tr>
                    </tfoot>
		    <% 
            		List<Producto> productos = (List) request.getSession().getAttribute("listaProductos");
        	    %>
                    <tbody>
                        <% if (productos != null) { %>                        
			<% for (Producto producto : productos) { %>
                        <tr>
                            <td id="id_prod<%= producto.getId() %>"><%= producto.getId() %></td>
                	    <td><%= producto.getCategoria().getNombre() %></td>
                	    <td><%= producto.getUbicacion().getDireccion() %></td>
                	    <td><%= producto.getNombre() %></td>
                            <td><%= producto.getStock() %></td>
                            <td><%= producto.getPrecio() %></td>
                            
                            <td style="display: flex; width: 230px;">
                                
                                <!-- Botón eliminar -->
                                
                                <!-- Button trigger modal -->
                                <form>
                                    <button type="button" class="btn btn-primary btn-user btn-block" data-toggle="modal" data-target="#eliminarProductoModal_<%= producto.getId() %>" 
                                            style="background-color: red; margin-right: 5px">
                                        <i class="fas fa-trash-alt"></i>
                                        Eliminar
                                    </button>
                                </form>
                                
                                <!-- Modal -->
                                <div class="modal fade" id="eliminarProductoModal_<%= producto.getId() %>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">¿Seguro que quiere desactivar este producto?</h5>
                                                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">×</span>
                                                </button>
                                            </div>
                                            <!--<div class="modal-body">Seleccione "Aceptar" si quieere eliminar</div>-->
                                            <div class="modal-footer">
                                                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>

                                                <form name="eliminar" action="SvElimProductos" method="post">
                                                    <button class="btn btn-primary"" type="submit"> 
                                                        Aceptar
                                                    </button>
                                                    <input type="hidden" name="id" value="<%= producto.getId() %>">
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--
                                <form name="eliminar" action="SvElimProductos" method="post">
                                    <button type="submit" class="btn btn-primary btn-user btn-block" style="background-color: red; margin-right: 5px">
                                    <i class="fas fa-trash-alt"></i> Eliminar
                                    </button>
                                    <input type="hidden" name="id" value="<%= producto.getId() %>">
                                </form>
                                -->
                                
                                <!-- Botón editar -->
                                <form name="editar" action="SvEditProductos" method="get">
                                    <button type="submit" class="btn btn-success btn-user btn-block" style="margin-left: 5px">
                                    <i class="fas fa-pencil-alt"></i> Editar
                                    </button>
                                    <input type="hidden" name="id" value="<%= producto.getId() %>">
                                </form>
                            </td>
                        </tr>
			<% } %>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid -->

<%@include file="components/body2.jsp" %>
</html>
