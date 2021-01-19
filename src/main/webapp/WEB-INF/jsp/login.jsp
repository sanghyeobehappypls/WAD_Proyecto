<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html class="h-100">
        <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Login</title>
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
                <link rel="stylesheet" href = "https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
                <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"/>
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
        <body class="h-100" id="fondo">
                <div class="container-fluid h-100">
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
                        
                        <div class="row col-md-12 justify-content-center">
                                <div class="card">
                                        <div class="card-header bg-primary text-light">
                                                <h1 class="text-center text-monospace align-middle">Iniciar Sesión</h1>
                                        </div>
                                        <div class="card-body col-md-12 bg-warning">
                                                <form class="needs-validation px-6 py-2 text-monospace" novalidate method="POST" name="formLogin" id="formLogin">
                                                        <div class="form-group text-center">
                                                                <img src="imagenes/Usuario.png" width="100" height="100"/>
                                                        </div>
                                                        <div class="form-group">
                                                                <span class="fas fa-envelope"><label for="email" class="text-monospace">&nbsp;Correo eletrónico</label></span>
                                                                <input type="email" class="form-control" id="email" name="email" placeholder="email@ejemplo.com" required="required">
                                                                <div class="invalid-feedback">Debe ingresar un correo válido</div>
                                                        </div>
                                                        <div class="form-group has-feedback">
                                                                <span class="fas fa-lock"><label for="password" class="text-monospace">&nbsp;Contraseña</label></span>
                                                                <input type="password" class="form-control" id="claveUsuario" name="claveUsuario" placeholder="Password" required="required">
                                                                <div class="invalid-feedback">Debe ingresar una contraseña</div>
                                                        </div>
                                                        <center><button type="submit" class="btn btn-primary text-center" onclick="return comprobar()">Iniciar sesión</button></center>
                                                </form>
                                                <div class="text-monospace">
                                                        <br><center><p class="align-middle">¿Nuevo por aquí? <a href="registrarUsuario.htm">Regístrate</a></p></center>
                                                </div>
                                        </div>
                                </div>
                        </div>
                </div>
                <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
                <script src = "https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
                <script>
                    (function() {
				'use strict';
				window.addEventListener('load', function() {
					// Fetch all the forms we want to apply custom Bootstrap validation styles to
					var forms = document.getElementsByClassName('needs-validation');
					// Loop over them and prevent submission
					var validation = Array.prototype.filter.call(forms, function(form) {
						form.addEventListener('submit', function(event)
						{
							if (form.checkValidity() === false) 
							{
								event.preventDefault();
								event.stopPropagation();
							}
							form.classList.add('was-validated');
						}, false);
					});
				}, false);
			})();
                </script>
        </body>
</html>
