<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
        <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Actualizar autor</title>
                <link rel="icon" type="image/png" href="imagenes/icono.png" />
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
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
                                        <h1 class='text-center font-weight-bolder text-light'>Actualizar usuario</h1>
                                </div>
                                <div class="card-body col-md-12 bg-warning">
                                <div class='row justify-content-center'>
                                        <div class='col-md-12 align-self-center'>
                                                        <form class="needs-validation" novalidate method="post" name="formUsuario" id="formUsuario">
                                                                <table class='table table-hover table-striped table-dark'>
                                                                        <input type="hidden" name="idAutor" id="id" value="${dto.entidad.idAutor}">
                                                                        <tr>
                                                                                <td class='align-middle text-md-center'>
                                                                                        <h5><label for='nombre' class='offset-md-3 col-xl-6 col-form-label text-center'>Nombre(s)</label></h5>
                                                                                </td>
                                                                                <td class='align-middle text-md-left'>
                                                                                        <input type="text" name="nombre" id="txtNombre" maxlength="50" required="required" placeholder="Nombre(s)" class="form-control col-xl-8" value="${dto.entidad.nombre}" pattern="[A-Za-zÁÉÍÓÚáéíóú ]+"/>
                                                                                        <div class="invalid-feedback">Debe ingresar un(os) nombre(s) válido(s)</div>
                                                                                </td>
                                                                        </tr>
                                                                        <tr>
                                                                                <td class='align-middle text-md-center'>
                                                                                        <h5><label for='apPaterno' class='offset-md-3 col-xl-6 col-form-label text-center'>Apellido Paterno</label></h5>
                                                                                </td>
                                                                                <td class='align-middle text-md-left'>
                                                                                        <input type="text" name="paterno" id="txtPaterno" maxlength="50" placeholder="Apellido Paterno" class="form-control col-xl-8" value="${dto.entidad.paterno}" pattern="[A-Za-zÁÉÍÓÚáéíóú]+"/>
                                                                                        <div class="invalid-feedback">Debe ingresar un apellido válido</div>
                                                                                </td>
                                                                        </tr>
                                                                        <tr>
                                                                                <td class='align-middle text-md-center'>
                                                                                        <h5><label for='apMaterno' class='offset-md-3 col-xl-6 col-form-label text-center'>Apellido Materno</label></h5>
                                                                                </td>
                                                                                <td class='align-middle text-md-left'>
                                                                                        <input type="text" name="materno" id="txtMaterno" maxlength="50" placeholder="Apellido Materno" class="form-control col-xl-8" value="${dto.entidad.materno}" pattern="[A-Za-zÁÉÍÓÚáéíóú]+"/>
                                                                                        <div class="invalid-feedback">Debe ingresar un apellido válido</div>
                                                                                </td>
                                                                        </tr>
                                                                        <tr>
                                                                                <td class='align-middle text-md-center'>
                                                                                        <h5><label for='email' class='offset-md-3 col-xl-6 col-form-label text-center'>País</label></h5>
                                                                                </td>
                                                                                <td class='align-middle text-md-left'>
                                                                                        <input type="text" name="pais" id="txtCorreo" maxlength="50" required="required" placeholder="México" class="form-control col-xl-8" value="${dto.entidad.pais}"/>
                                                                                        <div class="invalid-feedback">Debe ingresar un país válido</div>
                                                                                </td>
                                                                        </tr>
                                                                        <tr>
                                                                                <td colspan="2">
                                                                                        <div class="sticky-top">
                                                                                                <div class="d-flex flex-xl-row">
                                                                                                        <div class="p-2 col-xl-6">
                                                                                                                <button type="submit" id="btnReg" class="btn btn-lg btn-block btn-outline-success" onclick="comprobar()">
                                                                                                                        Actualizar 
                                                                                                                        <i class="fa fa-check"></i>
                                                                                                                </button>
                                                                                                        </div>
                                                                                                        <div class="p-2 col-xl-6">
                                                                                                                <a href ='listaUsuarios.htm' class='btn btn-lg btn-block btn-outline-danger'>Regresar <i class="fas fa-undo"></i></a>
                                                                                                        </div>

                                                                                                </div>
                                                                                        </div>
                                                                                </td>
                                                                        </tr>
                                                                </table>
                                                        </form>
                                                </div>
                                        </div>
                                </div>
                        </div>
                </div>
                <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
                <script>
                    (function()
                    {
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
                    function comprobar()
                    {
                        var nombre = document.getElementById("nombre");
                        var paterno = document.getElementById("paterno");
                        var materno = document.getElementById("materno");
                        var email = document.getElementById("email");
                        var usuario = document.getElementById("tipoUsuario");
                        var password = document.getElementById("claveUsuario");
                        
                        alert(nombre.value);
                        alert(paterno.value);;
                        alert(materno.value);
                        alert(email.value);
                        alert(usuario.value);
                        alert(password.value);
                        
                        if(nombre.value !== "" && paterno.value !== "" && materno.value !== "" && email.value !== "" && usuario.value !== "" && password.value !== "")
                        {
                            toastr.success('Usuario actualizado correctamente.', 'Actualizar Usuario', {
                                progressBar: true
                            });
                            return true;
                        }
                        else
                            return false;
                    }
                    $('.dropdown-toggle').dropdownHover(options);
                </script>
        </body>
</html>
