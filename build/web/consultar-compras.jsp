<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@include file="components/head.jsp" %>
<%@include file="components/body1.jsp" %>

<!-- Begin Page Content -->
<div class="container-fluid">
     <p>${message}</p>
     
    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Consultar Compras</h1>
    <p class="mb-4">A continuación podrá visualizar la lista completa de las compras</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-success">Compras</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>ID Compra</th>
                            <th>ID Proveedor</th>
                            <th>ID Empleado</th>
                            <th>ID Producto</th>
                            <th>Precio</th>
                            <th>Cantidad</th>
                            <th>Fecha</th>
                            <th style="width: 210px">Acción</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>ID Compra</th>
                            <th>ID Proveedor</th>
                            <th>ID Empleado</th>
                            <th>ID Producto</th>
                            <th>Precio</th>
                            <th>Cantidad</th>
                            <th>Fecha</th>
                            <th style="width: 210px">Acción</th>
                        </tr>
                    </tfoot>
		    
                    <tbody>
			
                        <tr>
                            <td id="id_comp">1</td>
                	    <td>2</td>
                	    <td>1</td>
                            <td>2</td>
                	    <td>$2000.00</td>
                            <td>45</td>
                            <td>2024-05-22</td>
                            
                            <td style="display: flex; width: 230px;">
                                <!-- Botón eliminar -->
                                <form name="eliminar" action="SvElimUsuarios" method="post">
                                    <button type="submit" class="btn btn-primary btn-user btn-block" style="background-color: red; margin-right: 5px">
                                    <i class="fas fa-trash-alt"></i> Eliminar
                                    </button>
                                    <input type="hidden" name="id" value="">
                                </form>
                                <!-- Botón editar -->
                                <form name="editar" action="SvEditUsuarios" method="get">
                                    <button type="submit" class="btn btn-success btn-user btn-block" style="margin-left: 5px">
                                    <i class="fas fa-pencil-alt"></i> Editar
                                    </button>
                                    <input type="hidden" name="id" value="">
                                </form>
                            </td>
                        </tr>
			
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid -->

<%@include file="components/body2.jsp" %>
</html>