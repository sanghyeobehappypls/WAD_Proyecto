<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
        <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Inicio</title>
                <link rel="icon" type="image/png" href="imagenes/icono.png">
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
                <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">
                <style>
                        #fondo{
                            width: 100%;
                            height:100%;
                            vertical-align: middle;
                            backdrop-filter: blur(5px);
                            background-size: 100% 100%;
                            background-image: url('imagenes/biblioteca.jpg');
                        }
                </style>
        </head>
        <body id="fondo">
                <div class="container-fluid">
                        <div class='sticky-top'>
                                <nav class="navbar navbar-expand-lg bg-warning text-monospace">
                                        <a class="navbar-brand bg-primary text-monospace text-center text-light" href="#">
                                                        <img src="imagenes/ESCOM.png" width="40" height="30" class="d-inline-block align-middle" alt="">
                                        </a>
                                        <nav class="navbar navbar-dark bg-warning">
                                                <div class="btn-group">
                                                        <button class="btn btn-success dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">Libros <span class="caret"></span></button>
                                                        <ul class="dropdown-menu bg-success text-light">
                                                                <li><a class="dropdown-item" href="registrarLibro.htm">Registrar libro</a></li>
                                                                <li><a class="dropdown-item" href="listaLibros.htm">Lista de libros</a></li>
                                                                <li><hr class="dropdown-divider"></li>
                                                                <li><a class="dropdown-item" href="graficaLibrosXGenero.htm">Gráfica: Libros X Género</a></li>
                                                                <li><hr class="dropdown-divider"></li>
                                                                <li><a class="dropdown-item" href="reporteListaLibros.htm" target="_blank">Reporte: Lista de libros</a></li>
                                                        </ul>
                                                </div>
                                        </nav>
                                        <nav class="navbar navbar-dark bg-warning">
                                                <div class="btn-group">
                                                        <button class="btn btn-danger dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">Editoriales <span class="caret"></span></button>
                                                        <ul class="dropdown-menu bg-danger text-light">
                                                                <li><a class="dropdown-item" href="registrarEditorial.htm">Registrar editorial</a></li>
                                                                <li><a class="dropdown-item" href="listaEditoriales.htm">Lista de editoriales</a></li>
                                                                <li><hr class="dropdown-divider"></li>
                                                                <li><a class="dropdown-item" href="graficaLibrosXEditorial.htm">Gráfica: Libros X Editorial</a></li>
                                                                <li><hr class="dropdown-divider"></li>
                                                                <li><a class="dropdown-item" href="reporteListaEditoriales.htm" target="_blank">Reporte: Lista de editoriales</a></li>
                                                        </ul>
                                                </div>
                                        </nav>
                                        <nav class="navbar navbar-dark bg-warning">
                                                <div class="btn-group">
                                                        <button class="btn btn-info dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">Autores <span class="caret"></span></button>
                                                        <ul class="dropdown-menu bg-info text-light">
                                                                <li><a class="dropdown-item" href="registrarAutor.htm">Registrar autor</a></li>
                                                                <li><a class="dropdown-item" href="listaAutores.htm">Lista de autores</a></li>
                                                                <li><hr class="dropdown-divider"></li>
                                                                <li><a class="dropdown-item" href="graficaLibrosXAutor.htm">Gráfica: Libros X Autor</a></li>
                                                                <li><hr class="dropdown-divider"></li>
                                                                <li><a class="dropdown-item" href="reporteListaAutores.htm" target="_blank">Reporte: Lista de autores</a></li>
                                                        </ul>
                                                </div>
                                        </nav>
                                        <nav class="navbar navbar-dark bg-warning">
                                                <div class="btn-group">
                                                        <button class="btn btn-dark dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">Usuarios <span class="caret"></span></button>
                                                        <ul class="dropdown-menu bg-dark text-light">
                                                                <li><a class="dropdown-item" href="registrarUsuario.htm">Registrar usuario</a></li>
                                                                <li><a class="dropdown-item" href="listaUsuarios.htm">Lista de usuarios</a></li>
                                                                <li><hr class="dropdown-divider"></li>
                                                                <li><a class="dropdown-item" href="reporteListaUsuarios.htm" target="_blank">Reporte: Lista de usuarios</a></li>
                                                        </ul>
                                                </div>
                                        </nav>
                                        <nav class="navbar navbar-dark bg-warning">
                                                <div class="btn-group">
                                                        <button class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">Préstamos <span class="caret"></span></button>
                                                        <ul class="dropdown-menu bg-secondary text-light">
                                                                <li><a class="dropdown-item" href="registrarPrestamo.htm">Registrar préstamo</a></li>
                                                                <li><a class="dropdown-item" href="listaPrestamos.htm">Lista de préstamos</a></li>
                                                                <li><hr class="dropdown-divider"></li>
                                                                <li><a class="dropdown-item" href="reporteListaPrestamos.htm" target="_blank">Reporte: Lista de préstamos</a></li>
                                                        </ul>
                                                </div>
                                        </nav>
                                        <nav class="navbar text-right bg-warning"></nav>
                                        <nav class="navbar text-right bg-warning"></nav>
                                        <nav class="navbar text-right bg-warning"></nav>
                                        <nav class="navbar text-right bg-warning"></nav>
                                        <nav class="navbar text-right bg-warning"></nav>
                                        <nav class="navbar text-right bg-warning"></nav>
                                        <nav class="navbar text-right bg-warning">
                                                <c:if test="${tipo == ''}">
                                                <a class="btn btn-primary navbar-brand text-light" href="login.htm">Iniciar sesión</a>
                                                </c:if>
                                                <c:if test="${tipo != ''}">
                                                <a class="btn btn-primary navbar-brand text-light" href="logout.htm">Cerrar sesión</a>
                                                </c:if>
                                        </nav>
                                </nav>
                        </div>
                        
                        <div class="d-inline-block"></div>
                        <div class="d-inline-block"></div>
                        
                        <div class="row justify-content-center h-100">
                                <div class="col-md-12 align-self-center">
                                        <div class="card" style="background-color: #FFAD51">
                                                <div class="card-header text-monospace text-light text-center" style="background-color: #FFAD51"><h1>Datos de los alumnos</h1></div>
                                                <div class="card-body text-monospace" style="background-color: #FFDF97">
                                                        <form class="needs-validation" novalidate method="POST" action="EventoServlet?accion=guardar" name="formulario">
                                                                <table class="table table-hover table-striped table-light">
                                                                        <tr>
                                                                                <td colspan="2">
                                                                                        <h2><b><label for="ipn" class="col-md-12 col-form-label text-center text-danger">INSTITUTO POLITÉCNICO NACIONAL</label></b></h2>
                                                                                </td>
                                                                        </tr>
                                                                        <tr>
                                                                                <td colspan="2">
                                                                                        <h3><b><label for="escom" class="col-md-12 col-form-label text-md-center text-primary">ESCUELA SUPERIOR DE CÓMPUTO</label></b></h3>
                                                                                </td>
                                                                        </tr>
                                                                        <tr>
                                                                                <td class="align-middle">
                                                                                        <h4><b><label for="materia" class="col-md-12 col-form-label text-right">Materia</label></b></h4>
                                                                                </td>
                                                                                <td class="align-middle">
                                                                                        <h4><b><label for="materia" class="col-md-12 col-form-label text-center text-info">WEB APPLICATION DEVELOPMENT</label></b></h4>
                                                                                </td>
                                                                        </tr>
                                                                        <tr>
                                                                                <td class="align-middle">
                                                                                        <h4><b><label for="nombre" class="col-md-12 col-form-label text-right">Alumnos</label></b></h4>
                                                                                </td>
                                                                                <td class="align-middle">
                                                                                        <h4><b><label for="nombre" class="col-md-12 col-form-label text-center text-info">MAYÉN GARCÍA MARCO ANTONIO</label></b></h4>
                                                                                        <h4><b><label for="nombre" class="col-md-12 col-form-label text-center text-info">RODRÍGUEZ GARCÍA DANIELA</label></b></h4>
                                                                                </td>
                                                                        </tr>
                                                                        <tr>
                                                                                <td class="align-middle text-md-center">
                                                                                        <h4><b><label for="profesor" class="col-md-12 col-form-label text-right">Profesor</label></b></h4>
                                                                                </td>
                                                                                <td class="align-middle">
                                                                                        <h4><b><label for="profesor" class="col-md-12 col-form-label text-center text-info">JOSÉ ASUNCIÓN ENRÍQUEZ ZÁRATE</label></b></h4>
                                                                                </td>
                                                                        </tr>
                                                                        <tr>
                                                                                <td class="align-middle">
                                                                                        <h4><b><label for="grupo" class="col-md-12 col-form-label text-right">Grupo</label></b></h4>
                                                                                </td>
                                                                                <td class="align-middle">
                                                                                        <h4><b><label for="grupo" class="col-md-12 col-form-label text-center text-info">3CM9</label></b></h4>
                                                                                </td>
                                                                        </tr>
                                                                        <tr>
                                                                                <td class="align-middle">
                                                                                        <h4><b><label for="practica" class="col-md-12 col-form-label text-right">Proyecto WAD</label></b></h4>
                                                                                </td>
                                                                                <td class="align-middle text-md-center">
                                                                                        <h4><b><label for="practica" class="col-md-12 col-form-label text-center text-info">BIBLIOTECA</label></b></h4>
                                                                                </td>
                                                                        </tr>
                                                                        <tr>
                                                                                <td class="align-middle">
                                                                                        <h4><b><label for="fecha" class="col-md-12 col-form-label text-right">Fecha</label></b></h4>
                                                                                </td>
                                                                                <td class="align-middle">
                                                                                        <h4><b><label for="fecha" class="col-md-12 col-form-label text-center text-info">22 DE ENERO DE 2020</label></b></h4>
                                                                                </td>
                                                                        </tr>
                                                                </table>
                                                        </form>
                                                </div>
                                        </div>
                                </div>
                        </div>
                </div>
                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-hover-dropdown/2.2.1/bootstrap-hover-dropdown.min.js"></script>
                <script>
                    $('.dropdown-toggle').dropdownHover(options);
                </script>
        </body>
</html>




