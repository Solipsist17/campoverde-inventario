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
<h1>Productos</h1>
<div class="tabla">
    <p>Desactivar Productos</p>
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
    <table id="employeeTable">
        <thead>
            <tr>
                <th></th>
                <th>Id_Producto</th>
                <th>Nombre</th>
                <th>Stock</th>
                <th>Precion Individual</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><input type="checkbox" name="selectRow"></td>
                <td>-</td>
                <td>Producto 01</td>
                <td>24</td>
                <td>25.00</td>
            </tr>
            <tr>
                <td><input type="checkbox" name="selectRow"></td>
                <td>-</td>
                <td>Producto 02</td>
                <td>32</td>
                <td>27.00</td>
            </tr>
            <tr>
                <td><input type="checkbox" name="selectRow"></td>
                <td>-</td>
                <td>Producto 03</td>
                <td>17</td>
                <td>29.00</td>
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