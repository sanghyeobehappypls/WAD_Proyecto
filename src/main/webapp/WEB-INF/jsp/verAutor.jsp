<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
        <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Mostrar autor</title>
                <link rel="icon" type="image/png" href="imagenes/icono.png" />
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
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
                <div class="container-fluid text-monospace">
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
                        
                        <div class="card">
                                <div class="card-header bg-primary">
                                        <h1 class='text-center font-weight-bolder text-light'>Datos del autor</h1>
                                </div>
                                <div class="card-body col-md-12 bg-warning">
                                <div class='row justify-content-center'>
                                        <div class='col-md-12 align-self-center'>
                                                        <table class='table table-hover table-striped table-dark'>
                                                                <tr>
                                                                        <td class='align-middle text-md-center'>
                                                                                <h5><label for='id' class='offset-md-3 col-form-label'>ID del autor</label></h5>
                                                                        </td>
                                                                        <td class='align-middle text-md-center'>
                                                                                <h5><b><i><label for='id' class='col-xl-8 col-form-label text-warning text-center text-monospace'><c:out value="${cat.entidad.idAutor}" /></label></i></b></h5>
                                                                        </td>
                                                                </tr>
                                                                <tr>
                                                                        <td class='align-middle text-md-center'>
                                                                                <h5><label for='nombre' class='offset-md-3 col-form-label'>Nombre(s) del Autor</label></h5>
                                                                        </td>
                                                                        <td class='align-middle text-md-center'>
                                                                                <h5><b><i><label for='nombre' class='col-md-8 col-form-label text-warning text-center text-monospace'><c:out value="${cat.entidad.nombre}" /></label></i></b></h5>
                                                                        </td>
                                                                </tr>
                                                                <tr>
                                                                        <td class='align-middle text-md-center'>
                                                                                <h5><label for='apPaterno' class='offset-md-3 col-form-label text-right'>Apellido Paterno</label></h5>
                                                                        </td>
                                                                        <td class='align-middle text-md-center'>
                                                                                <h5><b><i><label for='apPaterno' class='col-md-8 col-form-label text-warning text-center text-monospace'><c:out value="${cat.entidad.paterno}" /></label></i></b></h5>
                                                                        </td>
                                                                </tr>
                                                                <tr>
                                                                        <td class='align-middle text-md-center'>
                                                                                <h5><label for='apMaterno' class='offset-md-3 col-form-label text-right'>Apellido Materno</label></h5>
                                                                        </td>
                                                                        <td class='align-middle text-md-center'>
                                                                                <h5><b><i><label for='apMaterno' class='col-md-8 col-form-label text-warning text-center text-monospace'><c:out value="${cat.entidad.materno}" /></label></i></b></h5>
                                                                        </td>
                                                                </tr>
                                                                <tr>
                                                                        <td class='align-middle text-md-center'>
                                                                                <h5><label for='email' class='offset-md-3 col-form-label text-right'>País</label></h5>
                                                                        </td>
                                                                        <td class='align-middle text-md-center'>
                                                                                <h5><b><i><label for='email' class='col-md-8 col-form-label text-warning text-center text-monospace'><c:out value="${cat.entidad.pais}" /></label></i></b></h5>
                                                                        </td>
                                                                </tr>
                                                                <tr>
                                                                        <td colspan='2'>
                                                                                <div class='d-flex flex-xl-row'>
                                                                                        <a href='listaAutores.htm' class='btn btn-lg btn-block btn-outline-primary'>Aceptar</a>
                                                                                </div>
                                                                        </td>
                                                                </tr>
                                                        </table>
                                                </div>
                                        </div>
                                </div>
                        </div>
                </div>
                
                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
        </body>
</html>
