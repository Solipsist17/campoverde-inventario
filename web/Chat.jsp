<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@include file="components/head.jsp" %>
<%@include file="components/body1.jsp" %>

<!-- Begin Page Content -->
<div class="container">
    <div class="row">
        <!-- Cuadro de búsqueda de empleado -->
        <div class="col-md-4">
            <div class="card mb-4">
                <div class="card-header">
                    <h5>Buscar empleado:</h5>
                </div>
                <div class="card-body">
                    <input type="text" class="form-control" id="searchEmployee" placeholder="Escribir Id o nombre del empleado">
                </div>
            </div>
            <!-- Resultados de búsqueda -->
            <div class="card mb-4" id="searchResults" style="display: none; position: absolute; width: 100%; z-index: 999;">
                <div class="card-header">
                    <h5>Resultados de búsqueda:</h5>
                </div>
                <div class="card-body p-0">
                    <ul class="list-group list-group-flush chat-list" style="max-height: 300px; overflow-y: auto;">
                        <!-- Aquí se mostrarán los resultados de búsqueda dinámicamente -->
                    </ul>
                </div>
            </div>
            <!-- Cuadro de empleados chateados -->
            <div class="card mb-4">
                <div class="card-header">
                    <h5>Chats:</h5>
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="filterButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Todos los mensajes
                        </button>
                        <div class="dropdown-menu" aria-labelledby="filterButton">
                            <a class="dropdown-item" href="#">Todos los mensajes</a>
                            <a class="dropdown-item" href="#">Mensajes leídos</a>
                            <a class="dropdown-item" href="#">Mensajes no leídos</a>
                        </div>
                    </div>
                </div>
                <div class="card-body chat-list">
                    <!-- Aquí se mostrarán dinámicamente los empleados chateados -->
                </div>
            </div>
        </div>
        <!-- Cuadro de chat -->
        <div class="col-md-8">
            <div class="card chat-box">
                <div class="card-header" id="chatHeader">
                    <h5>Chat con el empleado</h5>
                </div>
                <div class="card-body chat-content">
                    <!-- Aquí se mostrará el chat con el empleado seleccionado -->
                    <p>Seleccione un empleado para empezar a chatear.</p>
                </div>
                <div class="card-footer">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Escribir mensaje">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="button">
                                <i class="fas fa-paperclip"></i>
                            </button>
                            <button class="btn btn-primary" type="button">
                                <i class="fas fa-paper-plane"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /.container-fluid -->

<style>
    .chat-list {
        position: relative;
        z-index: 999;
        display: none;
        width: 100%;
        max-height: 300px;
        overflow-y: auto;
    }

    .list-group-flush {
        position: absolute;
        background-color: #fff;
        width: 100%;
        border: 1px solid rgba(0,0,0,.125);
        border-top: 0;
    }

    .chat-box {
        height: 550px;
    }

    .chat-content {
        height: calc(100% - 70px); /* Adjust based on header and footer height */
        overflow-y: auto;
    }

    .employee-chat:hover, .employee-chat.selected {
        background-color: #f0f0f0;
        cursor: pointer;
    }

    .dropdown-toggle::after {
        margin-left: 0.5rem;
    }
</style>

<script>
    document.getElementById('searchEmployee').addEventListener('input', function() {
        var searchQuery = this.value.trim();
        var searchResults = document.getElementById('searchResults');
        var chatList = document.querySelector('.chat-list');

        if (searchQuery.length === 0) {
            // Clear search results if input is empty
            searchResults.style.display = 'none';
            return;
        }

        // Perform AJAX request to search servlet
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'BuscarEmpleadoServlet?searchTerm=' + encodeURIComponent(searchQuery), true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                // Process response data
                var employees = JSON.parse(xhr.responseText);
                searchResults.style.display = 'block';
                var searchList = searchResults.querySelector('.chat-list');
                searchList.innerHTML = ''; // Clear previous results

                employees.forEach(function(employee) {
                    var employeeHtml = `
                        <li class="list-group-item employee-chat" onclick="selectEmployee(this)">
                            <img src="imagenes/Perfil_Empleado.jpg" class="mr-3" alt="Profile Picture" width="50">
                            <div>
                                <h6 class="mt-0">${employee.nombre} ${employee.apellido}</h6>
                            </div>
                        </li>
                    `;
                    searchList.insertAdjacentHTML('beforeend', employeeHtml);
                });
            }
        };
        xhr.send();
    });

    function selectEmployee(element) {
        var employees = document.querySelectorAll('.employee-chat');
        employees.forEach(function(emp) {
            emp.classList.remove('selected');
        });
        element.classList.add('selected');
        var employeeName = element.querySelector('h6').textContent;
        document.getElementById('chatHeader').innerHTML = '<h5>' + employeeName + '</h5>';
        document.querySelector('.chat-content').innerHTML = '<p>Aquí se mostrará el chat con ' + employeeName + '.</p>';
    }
</script>

<%@include file="components/body2.jsp" %>
</html>
