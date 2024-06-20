<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@include file="components/head.jsp" %>
<%@include file="components/body1.jsp" %>
<style>
    .tabla {
        border: 1px solid #ccc;
        padding: 20px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        background-color: #fff;
        margin: 20px 30px;
    }
    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        border: 1px solid #000;
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
    .options{
        margin-bottom: 15px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .SelectCant, .SearchContainer {
        display: flex;
        align-items: center;
    }
    .SelectCant label, .SearchContainer label {
        margin-right: 5px;
    }
    .SelectCant select{
        margin-top: -5px;
        margin-right: 4px;
    }
    .SearchContainer input {
        padding: 5px;
        margin-top: -5px;
    }
    .pagination {
        display: flex;
        justify-content: right;
        margin-top: 20px;
    }
    .pagination a {
        margin: 0 5px;
        padding: 8px 16px;
        text-decoration: none;
        border: 1px solid #ccc;
        color: #000;
        background-color: #f2f2f2;
        cursor: pointer;
    }
    .pagination a:hover {
        background-color: #ddd;
    }
    .action-buttons {
        margin-top: 20px;
        display: flex;
        justify-content: flex-start;
    }
    .action-buttons button {
        padding: 8px 16px;
        margin-right: 10px;
        border: none;
        background-color: #007bff;
        color: #fff;
        cursor: pointer;
        border-radius: 4px;
    }
    .action-buttons button:hover {
        background-color: #0056b3;
    }
    .action-buttons .deactivate-button {
        background-color: #dc3545;
    }
    .action-buttons .deactivate-button:hover {
        background-color: #c82333;
    }
</style>
<h1>Compras</h1>
<div class="tabla">
    <p>Eliminar Compras</p>
    <div class="options">
        <div class="SelectCant">
            <label for="numEntries">Mostrar</label>
            <select id="numEntries" name="numEntries">
                <option value="10">10</option>
                <option value="15">15</option>
                <option value="20">20</option>
            </select>
            <label for="numEntries">entradas</label>
        </div>
        <div class="SearchContainer">
            <label for="search">Buscar:</label>
            <input type="text" id="search" name="search" placeholder="Buscar...">
        </div>
    </div>
    <table>
            <thead>
                <tr>
                    <th>Id Compra</th>
                    <th>Proveedor</th>
                    <th>Empleado</th>
                    <th>Id_Producto</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Fecha de compra</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Juan</td>
                    <td>Pérez</td>
                    <td>juan.perez@example.com</td>
                    <td>cell</td>
                    <td>cell</td>
                    <td>cell</td>
                    <td>cell</td>
                </tr>
                <tr>
                    <td>María</td>
                    <td>López</td>
                    <td>maria.lopez@example.com</td>
                    <td>cell</td>
                    <td>cell</td>
                    <td>cell</td>
                    <td>cell</td>
                </tr>
                <tr>
                    <td>Carlos</td>
                    <td>Gómez</td>
                    <td>carlos.gomez@example.com</td>
                    <td>cell</td>
                    <td>cell</td>
                    <td>cell</td>
                    <td>cell</td>
                </tr>
            </tbody>
    </table>
    <div class="pagination">
        <a href="#">&laquo;</a>
        <a href="#">&lt;</a>
        <a href="#">1</a>
        <a href="#">2</a>
        <a href="#">&gt;</a>
        <a href="#">&raquo;</a>
    </div>
    <div class="action-buttons">
        <button class="deactivate-button" onclick="deleteSelectedRows()">Eliminar Seleccionados</button>
    </div>
</div>    
<%@include file="Modal.jsp" %>
<%@include file="components/body2.jsp" %>

<script>
function deleteSelectedRows() {
    const table = document.getElementById('employeeTable');
    const checkboxes = table.querySelectorAll('input[name="selectRow"]:checked');
    if (checkboxes.length > 0) {
        checkboxes.forEach(checkbox => {
            const row = checkbox.closest('tr');
            row.remove();
        });
        showModal();
    }
}
</script>

</html>