<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Empleado, java.util.ArrayList, java.util.List" %>

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
     
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Consultar Empleados</h1>
    <p class="mb-4">A continuación podrá visualizar la lista completa de los empleados</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-success">Empleados</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>ID Empleado</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Correo</th>
                            <th style="width: 210px">Acción</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>ID Empleado</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Correo</th>
                            <th style="width: 210px">Acción</th>
                        </tr>
                    </tfoot>
		    <% 
            		List<Empleado> empleados = (List) request.getSession().getAttribute("listaUsuarios");
        	    %>
                    <tbody>
                        <% if (empleados != null) { %>
			<% for (Empleado empleado : empleados) { %>
                        <tr>
                            <td id="id_usu<%= empleado.getId() %>"><%= empleado.getId() %></td>
                	    <td><%= empleado.getNombre() %></td>
                	    <td><%= empleado.getApellido() %></td>
                	    <td><%= empleado.getCorreo() %></td>
                            
                            <td style="display: flex; width: 230px;">
                                <!-- Botón eliminar -->
                                <form name="eliminar" action="SvElimUsuarios" method="post">
                                    <button type="submit" class="btn btn-primary btn-user btn-block" style="background-color: red; margin-right: 5px">
                                    <i class="fas fa-trash-alt"></i> Eliminar
                                    </button>
                                    <input type="hidden" name="id" value="<%= empleado.getId() %>">
                                </form>
                                <!-- Botón editar -->
                                <form name="editar" action="SvEditUsuarios" method="get">
                                    <button type="submit" class="btn btn-success btn-user btn-block" style="margin-left: 5px">
                                    <i class="fas fa-pencil-alt"></i> Editar
                                    </button>
                                    <input type="hidden" name="id" value="<%= empleado.getId() %>">
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