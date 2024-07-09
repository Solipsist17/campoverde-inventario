<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Movimiento, java.util.ArrayList, java.util.List" %>

<!DOCTYPE html>
<html lang="en">

<%@include file="components/head.jsp" %>
<%@include file="components/body1.jsp" %>

<!-- Begin Page Content -->
<div class="container-fluid">
     <p>${message}</p>
     
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Consultar Movimientos</h1>
    <p class="mb-4">A continuación podrá visualizar la lista completa de los movimientos</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-success">Movimientos</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>ID Movimiento</th>
                            <th>Contacto</th>
                            <th>Empleado</th>
                            <th>Producto</th>
                            <th>Precio</th>
                            <th>Cantidad</th>
                            <th>Fecha</th>
                            <th>Tipo</th>
                            <th style="width: 210px">Acción</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>ID Movimiento</th>
                            <th>Contacto</th>
                            <th>Empleado</th>
                            <th>Producto</th>
                            <th>Precio</th>
                            <th>Cantidad</th>
                            <th>Fecha</th>
                            <th>Tipo</th>
                            <th style="width: 210px">Acción</th>
                        </tr>
                    </tfoot>
		    <% 
            		List<Movimiento> movimientos = (List) request.getSession().getAttribute("listaMovimientos");
        	    %>
                    <tbody>
                        <% if (movimientos != null) { %>
			<% for (Movimiento movimiento : movimientos) { %>
                        <tr>
                            <td id="id_mov<%= movimiento.getId() %>"><%= movimiento.getId() %></td>
                	    <td><%= movimiento.getContacto().getNombre() %></td>
                	    <td><%= movimiento.getEmpleado().getNombre() %></td>
                	    <td><%= movimiento.getProducto().getNombre() %></td>
                            <td><%= movimiento.getPrecio() %></td>
                            <td><%= movimiento.getCantidad() %></td>
                            <td><%= movimiento.getFechaMovimiento() %></td>
                            <td><%= movimiento.getTipoMovimiento() %></td>
                            
                            <td style="display: flex; width: 230px;">
                                <!-- Botón eliminar -->
                                <form name="eliminar" action="SvElimMovimientos" method="post">
                                    <button type="submit" class="btn btn-primary btn-user btn-block" style="background-color: red; margin-right: 5px">
                                    <i class="fas fa-trash-alt"></i> Eliminar
                                    </button>
                                    <input type="hidden" name="id" value="<%= movimiento.getId() %>">
                                </form>
                                <!-- Botón editar -->
                                <form name="editar" action="SvEditMovimientos" method="get">
                                    <button type="submit" class="btn btn-success btn-user btn-block" style="margin-left: 5px">
                                    <i class="fas fa-pencil-alt"></i> Editar
                                    </button>
                                    <input type="hidden" name="id" value="<%= movimiento.getId() %>">
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
