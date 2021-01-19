package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.AutorDAO;
import com.ipn.mx.modelo.dao.EditorialDAO;
import com.ipn.mx.modelo.dao.GeneroDAO;
import com.ipn.mx.modelo.dao.GraficaDAO;
import com.ipn.mx.modelo.dao.LibroDAO;
import com.ipn.mx.modelo.dao.PrestamoDAO;
import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.dto.AutorDTO;
import com.ipn.mx.modelo.dto.EditorialDTO;
import com.ipn.mx.modelo.dto.GeneroDTO;
import com.ipn.mx.modelo.dto.GraficaDTO;
import com.ipn.mx.modelo.dto.LibroDTO;
import com.ipn.mx.modelo.dto.PrestamoDTO;
import com.ipn.mx.modelo.dto.UsuarioDTO;
import com.ipn.mx.modelo.entidades.Autor;
import com.ipn.mx.modelo.entidades.Auxiliar;
import com.ipn.mx.modelo.entidades.Ayuda;
import com.ipn.mx.modelo.entidades.Editorial;
import com.ipn.mx.modelo.entidades.Libro;
import com.ipn.mx.modelo.entidades.Prestamo;
import com.ipn.mx.modelo.entidades.Usuario;
import com.ipn.mx.utilerias.EnviarMail;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioControlador {

    private List listaLibros;
    private List listaAutores;
    private List listaGeneros;
    private List listaUsuarios;
    private List listaPrestamos;
    private List listaEditoriales;
    
    private int idUsuario;

    private String correo = "";
    private String tipoUsuario = "";
    
    private HttpSession sesion = null;
    private HttpServletRequest request;
    private ModelAndView mav = new ModelAndView();
    
    private UsuarioDTO usuario = new UsuarioDTO();
    private UsuarioDAO daoUsuario = new UsuarioDAO();

    private LibroDTO libro = new LibroDTO();
    private LibroDAO daoLibro = new LibroDAO();

    private AutorDTO autor = new AutorDTO();
    private AutorDAO daoAutor = new AutorDAO();

    private GeneroDTO genero = new GeneroDTO();
    private GeneroDAO daoGenero = new GeneroDAO();

    private PrestamoDTO prestamo = new PrestamoDTO();
    private PrestamoDAO daoPrestamo = new PrestamoDAO();

    private EditorialDTO editorial = new EditorialDTO();
    private EditorialDAO daoEditorial = new EditorialDAO();

    @RequestMapping("index.htm")
    public ModelAndView index() {
        mav.addObject("tipo", tipoUsuario);
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping("listaUsuarios.htm")
    public ModelAndView listaUsuarios() {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        listaUsuarios = daoUsuario.readAll();
        
        mav.addObject("listaUsuarios", listaUsuarios);
        mav.addObject("tipo", tipoUsuario);
        mav.setViewName("listaUsuarios");
        
        return mav;
    }

    @RequestMapping(value = "registrarUsuario.htm", method = RequestMethod.GET)
    public ModelAndView registrarUsuario() {
        mav.addObject(new Usuario());
        mav.addObject("tipo", tipoUsuario);
        mav.setViewName("registrarUsuario");
        
        return mav;
    }

    @RequestMapping(value = "registrarUsuario.htm", method = RequestMethod.POST)
    public ModelAndView registrarUsuario(Ayuda ayuda) throws InterruptedException, IOException {
        this.usuario.getEntidad().setNombre(ayuda.getNombre());
        this.usuario.getEntidad().setPaterno(ayuda.getPaterno());
        this.usuario.getEntidad().setMaterno(ayuda.getMaterno());
        this.usuario.getEntidad().setEmail(ayuda.getEmail());
        this.usuario.getEntidad().setNombreUsuario(ayuda.getNombreUsuario());
        this.usuario.getEntidad().setClaveUsuario(ayuda.getClaveUsuario());
        this.usuario.getEntidad().setTipoUsuario(ayuda.getTipoUsuario());
        this.usuario.getEntidad().setImagen(ayuda.getArchivo().getOriginalFilename());
        
        daoUsuario.create(this.usuario);
        
        EnviarMail email = new EnviarMail();
        String destinatario = this.usuario.getEntidad().getEmail();
        String asunto = "Registro de usuario satisfactorio";
        String texto = "El registro del usuario \"" + this.usuario.getEntidad().getNombreUsuario() + "\" con correo " + this.usuario.getEntidad().getEmail() + " ha sido satisfactorio";
        email.enviarCorreo(destinatario, asunto, texto);
        
        
        Files.copy(ayuda.getArchivo().getInputStream(), new File(getClass().getProtectionDomain().getClassLoader().getResource("../../").getPath().replace("%20", " "), ayuda.getArchivo().getOriginalFilename()).getAbsoluteFile().toPath(), REPLACE_EXISTING);
        
        //Esperar 1.5 seg para mostrar mensaje
        Thread.sleep(1500);
        
        return new ModelAndView("redirect:/listaUsuarios.htm");
    }

    @RequestMapping(value = "actualizarUsuario.htm", method = RequestMethod.GET)
    public ModelAndView actualizarUsuario(HttpServletRequest request) {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        usuario.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
        usuario = daoUsuario.read(usuario);
        
        mav.addObject("dto", usuario);
        mav.addObject("tipo", tipoUsuario);
        mav.setViewName("actualizarUsuario");
        
        return mav;
    }

    @RequestMapping(value = "actualizarUsuario.htm", method = RequestMethod.POST)
    public ModelAndView actualizarUsuario(Ayuda ayuda) throws InterruptedException, IOException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }
        
        this.usuario.getEntidad().setIdUsuario(ayuda.getIdUsuario());
        this.usuario.getEntidad().setNombre(ayuda.getNombre());
        this.usuario.getEntidad().setPaterno(ayuda.getPaterno());
        this.usuario.getEntidad().setMaterno(ayuda.getMaterno());
        this.usuario.getEntidad().setEmail(ayuda.getEmail());
        this.usuario.getEntidad().setNombreUsuario(ayuda.getNombreUsuario());
        this.usuario.getEntidad().setClaveUsuario(ayuda.getClaveUsuario());
        this.usuario.getEntidad().setTipoUsuario(ayuda.getTipoUsuario());
        this.usuario.getEntidad().setImagen(ayuda.getArchivo().getOriginalFilename());
        
        daoUsuario.update(this.usuario);
        
        EnviarMail email = new EnviarMail();
        String destinatario = correo;
        String asunto = "Actualización de usuario satisfactoria";
        String texto = "La actualización del usuario \"" + this.usuario.getEntidad().getNombreUsuario() + "\" con correo " + this.usuario.getEntidad().getEmail() + " ha sido satisfactoria";
        //email.enviarCorreo(destinatario, asunto, texto);
        
        Files.copy(ayuda.getArchivo().getInputStream(), new File(getClass().getProtectionDomain().getClassLoader().getResource("../../").getPath().replace("%20", " "), ayuda.getArchivo().getOriginalFilename()).getAbsoluteFile().toPath(), REPLACE_EXISTING);
        
        //Esperar 1.5 seg para mostrar mensaje
        Thread.sleep(1500);
        
        return new ModelAndView("redirect:/listaUsuarios.htm");
    }

    @RequestMapping("eliminarUsuario.htm")
    public ModelAndView eliminarUsuario(HttpServletRequest request) throws InterruptedException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        usuario.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
        usuario = daoUsuario.read(usuario);
        
        EnviarMail email = new EnviarMail();
        String destinatario = correo;
        String asunto = "Eliminación de usuario satisfactoria";
        String texto = "La eliminación del usuario \"" + usuario.getEntidad().getNombreUsuario() + "\" con correo " + usuario.getEntidad().getEmail() + " ha sido satisfactoria";
        email.enviarCorreo(destinatario, asunto, texto);
        
        daoUsuario.delete(usuario);
        
        //Esperar 1.5 seg para mostrar mensaje
        Thread.sleep(1500);
        
        return new ModelAndView("redirect:/listaUsuarios.htm");
    }

    @RequestMapping("mostrarUsuario.htm")
    public ModelAndView mostrarUsuario(HttpServletRequest request) {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        usuario.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
        usuario = daoUsuario.read(usuario);
        
        listaUsuarios = daoUsuario.readAll();
        listaLibros = daoLibro.readAll();
        
        mav.addObject("listaUsuarios", listaUsuarios);
        mav.addObject("listaLibros", listaLibros);
        mav.addObject("tipo", tipoUsuario);
        mav.addObject("cat", usuario);
        mav.setViewName("verUsuario");
        
        return mav;
    }

    @RequestMapping(value = "login.htm", method = RequestMethod.GET)
    public ModelAndView login() {
        if(sesion == null){
            mav.addObject("tipo", tipoUsuario);
            mav.setViewName("login");
            return mav;
        }
        else
            return new ModelAndView("redirect:/listaUsuarios.htm");
    }

    @RequestMapping(value = "login.htm", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request) {
        usuario.getEntidad().setEmail(request.getParameter("email"));
        usuario.getEntidad().setClaveUsuario(request.getParameter("claveUsuario"));
        usuario = daoUsuario.login(usuario);

        if (usuario != null) {
            this.request = request;
            sesion = request.getSession();
            sesion.setAttribute("user", usuario);
            
            correo = usuario.getEntidad().getEmail();
            idUsuario = usuario.getEntidad().getIdUsuario();
            tipoUsuario = usuario.getEntidad().getTipoUsuario();
            
            return new ModelAndView("redirect:/listaUsuarios.htm");
        } 
        else {
            usuario = new UsuarioDTO();
            return new ModelAndView("redirect:/login.htm");
        }
    }

    @RequestMapping("logout.htm")
    public ModelAndView logout() {
        sesion.invalidate();
        sesion = request.getSession(false);
        correo = "";
        idUsuario = 0;
        tipoUsuario = "";
        return new ModelAndView("redirect:/login.htm");
    }

    /**
     * ****************************FUNCIONES PARA LIBROS*****************************
     */
    @RequestMapping("listaLibros.htm")
    public ModelAndView listaLibros() {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        listaLibros = daoLibro.readAll();
        listaAutores = daoAutor.readAll();
        listaGeneros = daoGenero.readAll();
        listaEditoriales = daoEditorial.readAll();
        
        mav.addObject("tipo", tipoUsuario);
        mav.addObject("listaLibros", listaLibros);
        mav.addObject("listaAutores", listaAutores);
        mav.addObject("listaGeneros", listaGeneros);
        mav.addObject("listaEditoriales", listaEditoriales);
        mav.setViewName("listaLibros");
        
        return mav;
    }

    @RequestMapping(value = "registrarLibro.htm", method = RequestMethod.GET)
    public ModelAndView registrarLibro() {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        listaAutores = daoAutor.readAll();
        listaGeneros = daoGenero.readAll();
        listaEditoriales = daoEditorial.readAll();

        mav.addObject(new Libro());
        mav.addObject("tipo", tipoUsuario);
        mav.addObject("listaAutores", listaAutores);
        mav.addObject("listaGeneros", listaGeneros);
        mav.addObject("listaEditoriales", listaEditoriales);
        mav.setViewName("registrarLibro");
        
        return mav;
    }

    @RequestMapping(value = "registrarLibro.htm", method = RequestMethod.POST)
    public ModelAndView registrarLibro(Libro libro) throws InterruptedException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        this.libro.setEntidad(libro);
        daoLibro.create(this.libro);
        
        EnviarMail email = new EnviarMail();
        String destinatario = correo;
        String asunto = "Registro de libro satisfactorio";
        String texto = "El registro del libro \"" + this.libro.getEntidad().getTitulo() + "\" ha sido satisfactorio";
        email.enviarCorreo(destinatario, asunto, texto);
        
        //Esperar 1.5 seg para mostrar mensaje
        Thread.sleep(1500);
        
        return new ModelAndView("redirect:/listaLibros.htm");
    }

    @RequestMapping(value = "actualizarLibro.htm", method = RequestMethod.GET)
    public ModelAndView actualizarLibro(HttpServletRequest request) {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        listaAutores = daoAutor.readAll();
        listaGeneros = daoGenero.readAll();
        listaEditoriales = daoEditorial.readAll();

        libro.getEntidad().setIdLibro(Integer.parseInt(request.getParameter("id")));
        libro = daoLibro.read(libro);

        mav.addObject("dto", libro);
        mav.addObject("tipo", tipoUsuario);
        mav.addObject("listaAutores", listaAutores);
        mav.addObject("listaGeneros", listaGeneros);
        mav.addObject("listaEditoriales", listaEditoriales);
        mav.setViewName("actualizarLibro");
        
        return mav;
    }

    @RequestMapping(value = "actualizarLibro.htm", method = RequestMethod.POST)
    public ModelAndView actualizarLibro(Libro libro) throws InterruptedException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        this.libro.setEntidad(libro);
        daoLibro.update(this.libro);
        
        EnviarMail email = new EnviarMail();
        String destinatario = correo;
        String asunto = "Actualización de libro satisfactoria";
        String texto = "La actualización del libro \"" + this.libro.getEntidad().getTitulo() + "\" ha sido satisfactoria";
        email.enviarCorreo(destinatario, asunto, texto);
        
        //Esperar 1.5 seg para mostrar mensaje
        Thread.sleep(1500);
        
        return new ModelAndView("redirect:/listaLibros.htm");
    }

    @RequestMapping("eliminarLibro.htm")
    public ModelAndView eliminarLibro(HttpServletRequest request) throws InterruptedException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        libro.getEntidad().setIdLibro(Integer.parseInt(request.getParameter("id")));
        libro = daoLibro.read(libro);
        
        EnviarMail email = new EnviarMail();
        String destinatario = correo;
        String asunto = "Eliminación de libro satisfactoria";
        String texto = "La eliminación del libro \"" + libro.getEntidad().getTitulo() + "\" ha sido satisfactoria";
        email.enviarCorreo(destinatario, asunto, texto);
        
        daoLibro.delete(libro);
        
        //Esperar 1.5 seg para mostrar mensaje
        Thread.sleep(1500);
        
        return new ModelAndView("redirect:/listaLibros.htm");
    }

    @RequestMapping("mostrarLibro.htm")
    public ModelAndView mostrarLibro(HttpServletRequest request) {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        listaAutores = daoAutor.readAll();
        listaGeneros = daoGenero.readAll();
        listaEditoriales = daoEditorial.readAll();

        libro.getEntidad().setIdLibro(Integer.parseInt(request.getParameter("id")));
        libro = daoLibro.read(libro);
        
        mav.addObject("tipo", tipoUsuario);
        mav.addObject("listaAutores", listaAutores);
        mav.addObject("listaGeneros", listaGeneros);
        mav.addObject("listaEditoriales", listaEditoriales);
        mav.addObject("cat", libro);
        mav.setViewName("verLibro");
        
        return mav;
    }

    /**
     * ****************************FUNCIONES PARA AUTORES*****************************
     */
    @RequestMapping("listaAutores.htm")
    public ModelAndView listaAutores() {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        listaAutores = daoAutor.readAll();
        
        mav.addObject("listaAutores", listaAutores);
        mav.addObject("tipo", tipoUsuario);
        mav.setViewName("listaAutores");
        
        return mav;
    }

    @RequestMapping(value = "registrarAutor.htm", method = RequestMethod.GET)
    public ModelAndView registrarAutor() {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        mav.addObject(new Autor());
        mav.addObject("tipo", tipoUsuario);
        mav.setViewName("registrarAutor");
        
        return mav;
    }

    @RequestMapping(value = "registrarAutor.htm", method = RequestMethod.POST)
    public ModelAndView registrarAutor(Autor autor) throws InterruptedException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        this.autor.setEntidad(autor);
        daoAutor.create(this.autor);
        
        EnviarMail email = new EnviarMail();
        String destinatario = correo;
        String asunto = "Registro de autor satisfactorio";
        String texto = "El registro del autor \"" + this.autor.getEntidad().getNombre() + " " + this.autor.getEntidad().getPaterno() + " " + this.autor.getEntidad().getMaterno() + "\" ha sido satisfactorio";
        email.enviarCorreo(destinatario, asunto, texto);
        
        //Esperar 1.5 seg para mostrar mensaje
        Thread.sleep(1500);
        
        return new ModelAndView("redirect:/listaAutores.htm");
    }

    @RequestMapping(value = "actualizarAutor.htm", method = RequestMethod.GET)
    public ModelAndView actualizarAutor(HttpServletRequest request) {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        autor.getEntidad().setIdAutor(Integer.parseInt(request.getParameter("id")));
        autor = daoAutor.read(autor);

        mav.addObject("dto", autor);
        mav.addObject("tipo", tipoUsuario);
        mav.setViewName("actualizarAutor");
        
        return mav;
    }

    @RequestMapping(value = "actualizarAutor.htm", method = RequestMethod.POST)
    public ModelAndView actualizarAutor(Autor autor) throws InterruptedException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        this.autor.setEntidad(autor);
        daoAutor.update(this.autor);
        
        EnviarMail email = new EnviarMail();
        String destinatario = correo;
        String asunto = "Actualización de autor satisfactoria";
        String texto = "La actualización del autor \"" + this.autor.getEntidad().getNombre() + " " + this.autor.getEntidad().getPaterno() + " " + this.autor.getEntidad().getMaterno() + "\" ha sido satisfactoria";
        email.enviarCorreo(destinatario, asunto, texto);
        
        //Esperar 1.5 seg para mostrar mensaje
        Thread.sleep(1500);
        
        return new ModelAndView("redirect:/listaAutores.htm");
    }

    @RequestMapping("eliminarAutor.htm")
    public ModelAndView eliminarAutor(HttpServletRequest request) throws InterruptedException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        autor.getEntidad().setIdAutor(Integer.parseInt(request.getParameter("id")));
        autor = daoAutor.read(autor);
        
        EnviarMail email = new EnviarMail();
        String destinatario = correo;
        String asunto = "Eliminación de autor satisfactoria";
        String texto = "La eliminación del autor \"" + autor.getEntidad().getNombre() + " " + autor.getEntidad().getPaterno() + " " + autor.getEntidad().getMaterno() + "\" ha sido satisfactoria";
        email.enviarCorreo(destinatario, asunto, texto);
        
        daoAutor.delete(autor);
        
        //Esperar 1.5 seg para mostrar mensaje
        Thread.sleep(1500);
        
        return new ModelAndView("redirect:/listaAutores.htm");
    }

    @RequestMapping("mostrarAutor.htm")
    public ModelAndView mostrarAutor(HttpServletRequest request) {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        autor.getEntidad().setIdAutor(Integer.parseInt(request.getParameter("id")));
        autor = daoAutor.read(autor);
        
        mav.addObject("cat", autor);
        mav.addObject("tipo", tipoUsuario);
        mav.setViewName("verAutor");
        
        return mav;
    }

    /**
     * ****************************FUNCIONES PARA EDITORIALES*****************************
     */
    @RequestMapping("listaEditoriales.htm")
    public ModelAndView listaEditoriales() {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        listaEditoriales = daoEditorial.readAll();
        
        mav.addObject("listaEditoriales", listaEditoriales);
        mav.addObject("tipo", tipoUsuario);
        mav.setViewName("listaEditoriales");
        return mav;
    }

    @RequestMapping(value = "registrarEditorial.htm", method = RequestMethod.GET)
    public ModelAndView registrarEditorial() {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        mav.addObject(new Editorial());
        mav.addObject("tipo", tipoUsuario);
        mav.setViewName("registrarEditorial");
        
        return mav;
    }

    @RequestMapping(value = "registrarAutor.htm", method = RequestMethod.POST)
    public ModelAndView registrarEditorial(Editorial editorial) throws InterruptedException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        this.editorial.setEntidad(editorial);
        daoEditorial.create(this.editorial);
        
        EnviarMail email = new EnviarMail();
        String destinatario = correo;
        String asunto = "Registro de editorial satisfactorio";
        String texto = "El registro de la editorial \"" + this.editorial.getEntidad().getNombre() + "\" ha sido satisfactorio";
        email.enviarCorreo(destinatario, asunto, texto);
        
        //Esperar 1.5 seg para mostrar mensaje
        Thread.sleep(1500);
        
        return new ModelAndView("redirect:/listaEditoriales.htm");
    }

    @RequestMapping(value = "actualizarEditorial.htm", method = RequestMethod.GET)
    public ModelAndView actualizarEditorial(HttpServletRequest request) {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        editorial.getEntidad().setIdEditorial(Integer.parseInt(request.getParameter("id")));
        editorial = daoEditorial.read(editorial);

        mav.addObject("dto", editorial);
        mav.addObject("tipo", tipoUsuario);
        mav.setViewName("actualizarEditorial");
        
        return mav;
    }

    @RequestMapping(value = "actualizarEditorial.htm", method = RequestMethod.POST)
    public ModelAndView actualizarEditorial(Editorial editorial) throws InterruptedException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        this.editorial.setEntidad(editorial);
        daoEditorial.update(this.editorial);
        
        EnviarMail email = new EnviarMail();
        String destinatario = correo;
        String asunto = "Actualización de editorial satisfactoria";
        String texto = "La actualización de la editorial \"" + this.editorial.getEntidad().getNombre() + "\" ha sido satisfactoria";
        email.enviarCorreo(destinatario, asunto, texto);
        
        //Esperar 1.5 seg para mostrar mensaje
        Thread.sleep(1500);
        
        return new ModelAndView("redirect:/listaEditoriales.htm");
    }

    @RequestMapping("eliminarEditorial.htm")
    public ModelAndView eliminarEditorial(HttpServletRequest request) throws InterruptedException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        editorial.getEntidad().setIdEditorial(Integer.parseInt(request.getParameter("id")));
        editorial = daoEditorial.read(editorial);
        
        EnviarMail email = new EnviarMail();
        String destinatario = correo;
        String asunto = "Eliminación de editorial satisfactoria";
        String texto = "La eliminación de la editorial \"" + editorial.getEntidad().getNombre() + "\" ha sido satisfactoria";
        email.enviarCorreo(destinatario, asunto, texto);
        
        daoEditorial.delete(editorial);
        
        //Esperar 1.5 seg para mostrar mensaje
        Thread.sleep(1500);
        return new ModelAndView("redirect:/listaEditoriales.htm");
    }

    @RequestMapping("mostrarEditorial.htm")
    public ModelAndView mostrarEditorial(HttpServletRequest request) {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        editorial.getEntidad().setIdEditorial(Integer.parseInt(request.getParameter("id")));
        editorial = daoEditorial.read(editorial);
        
        mav.addObject("tipo", tipoUsuario);
        mav.addObject("cat", editorial);
        mav.setViewName("verEditorial");
        
        return mav;
    }

    /**
     * ****************************FUNCIONES PARA PRESTAMOS*****************************
     */
    @RequestMapping("listaPrestamos.htm")
    public ModelAndView listaPrestamos() {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        listaLibros = daoLibro.readAll();
        listaUsuarios = daoUsuario.readAll();
        listaPrestamos = daoPrestamo.readAll();
        
        mav.addObject("listaPrestamos", listaPrestamos);
        mav.addObject("listaUsuarios", listaUsuarios);
        mav.addObject("listaLibros", listaLibros);
        mav.addObject("tipo", tipoUsuario);
        mav.setViewName("listaPrestamos");
        
        return mav;
    }

    @RequestMapping(value = "registrarPrestamo.htm", method = RequestMethod.GET)
    public ModelAndView registrarPrestamo() {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        listaUsuarios = daoUsuario.readAll();
        listaGeneros = daoGenero.readAll();
        listaLibros = daoLibro.readAll();
        
        mav.addObject(new Prestamo());
        mav.addObject("listaUsuarios", listaUsuarios);
        mav.addObject("listaGeneros", listaGeneros);
        mav.addObject("listaLibros", listaLibros);
        mav.addObject("usuario", idUsuario);
        mav.addObject("tipo", tipoUsuario);
        mav.setViewName("registrarPrestamo");
        
        return mav;
    }

    @RequestMapping(value = "registrarPrestamo.htm", method = RequestMethod.POST)
    public ModelAndView registrarPrestamo(Auxiliar aux) throws InterruptedException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        this.prestamo.getEntidad().setIdUsuario(aux.getIdUsuario());
        this.prestamo.getEntidad().setIdLibro(aux.getIdLibro());
        this.prestamo.getEntidad().setFechaInicio(Date.valueOf(aux.getFechaInicio()));
        this.prestamo.getEntidad().setFechaTermino(Date.valueOf(aux.getFechaTermino()));
        daoPrestamo.create(this.prestamo);
        
        EnviarMail email = new EnviarMail();
        String destinatario = correo;
        String asunto = "Registro de préstamo satisfactorio";
        String texto = "El registro del préstamo del libro con ID " + this.prestamo.getEntidad().getIdLibro() + " al usuario con ID " + this.prestamo.getEntidad().getIdUsuario() + " ha sido satisfactorio";
        email.enviarCorreo(destinatario, asunto, texto);
        
        //Esperar 1.5 seg para mostrar mensaje
        Thread.sleep(1500);
        
        return new ModelAndView("redirect:/listaPrestamos.htm");
    }
    
    @RequestMapping(value = "actualizarPrestamo.htm", method = RequestMethod.GET)
    public ModelAndView actualizarPrestamo(HttpServletRequest request) {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        prestamo.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("idU")));
        prestamo.getEntidad().setIdLibro(Integer.parseInt(request.getParameter("idL")));
        prestamo = daoPrestamo.read(prestamo);
        
        listaLibros = daoLibro.readAll();
        
        mav.addObject("listaLibros", listaLibros);
        mav.addObject("tipo", tipoUsuario);
        mav.addObject("dto", prestamo);
        mav.setViewName("actualizarPrestamo");
        
        return mav;
    }

    @RequestMapping(value = "actualizarPrestamo.htm", method = RequestMethod.POST)
    public ModelAndView actualizaPrestamo(Auxiliar aux) throws InterruptedException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        this.prestamo.getEntidad().setIdUsuario(aux.getIdUsuario());
        this.prestamo.getEntidad().setIdLibro(aux.getIdLibro());
        this.prestamo.getEntidad().setFechaInicio(Date.valueOf(aux.getFechaInicio()));
        this.prestamo.getEntidad().setFechaTermino(Date.valueOf(aux.getFechaTermino()));
        
        System.out.println(prestamo);
        
        //this.prestamo.setEntidad(prestamo);
        daoPrestamo.update(this.prestamo);
        
        EnviarMail email = new EnviarMail();
        String destinatario = correo;
        String asunto = "Actualización de préstamo satisfactoria";
        String texto = "La actualización del préstamo del libro con ID " + this.prestamo.getEntidad().getIdLibro() + "al usuario con ID " + this.prestamo.getEntidad().getIdUsuario() + " ha sido satisfactoria";
        email.enviarCorreo(destinatario, asunto, texto);
        
        //Esperar 1.5 seg para mostrar mensaje
        Thread.sleep(1500);
        
        return new ModelAndView("redirect:/listaPrestamos.htm");
    }

    @RequestMapping("eliminarPrestamo.htm")
    public ModelAndView eliminarPrestamo(HttpServletRequest request) throws InterruptedException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        prestamo.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("idU")));
        prestamo.getEntidad().setIdLibro(Integer.parseInt(request.getParameter("idL")));
        prestamo = daoPrestamo.read(prestamo);
        
        EnviarMail email = new EnviarMail();
        String destinatario = correo;
        String asunto = "Eliminación de préstamo satisfactoria";
        String texto = "La eliminación del préstamo del libro con ID " + this.prestamo.getEntidad().getIdLibro() + "al usuario con ID " + this.prestamo.getEntidad().getIdUsuario() + " ha sido satisfactoria";
        email.enviarCorreo(destinatario, asunto, texto);
        
        daoPrestamo.delete(prestamo);
        
        //Esperar 1.5 seg para mostrar mensaje
        Thread.sleep(1500);
        
        return new ModelAndView("redirect:/listaPrestamos.htm");
    }

    @RequestMapping("mostrarPrestamo.htm")
    public ModelAndView mostrarPrestamo(HttpServletRequest request) {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }

        prestamo.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("idU")));
        prestamo.getEntidad().setIdLibro(Integer.parseInt(request.getParameter("idL")));
        prestamo = daoPrestamo.read(prestamo);
        
        mav.addObject("tipo", tipoUsuario);
        mav.addObject("cat", prestamo);
        mav.setViewName("verPrestamo");
        
        return mav;
    }

    /**
     * ****************************FUNCIONES PARA REPORTES*****************************
     */
    
    @RequestMapping(value = "reporteListaUsuarios.htm", method = RequestMethod.GET)
    public ModelAndView reporteListaUsuarios(HttpServletResponse response) throws JRException, IOException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }
        
        ServletOutputStream sos = response.getOutputStream();
        //File reporte = new File(getClass().getProtectionDomain().getClassLoader().getResource("../../reportes/listaUsuarios.jasper").getPath().replace("%20", " "));
        File reporte = new File(request.getServletContext().getRealPath("/reportes/listaPrestamos.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(reporte.getPath(), null, daoUsuario.conexion());
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        response.setHeader("Content-disposition", "inline; filename=Reporte: Lista de Usuarios.pdf");
        sos.write(bytes, 0, bytes.length);
        sos.flush();
        sos.close();
        
        mav.setViewName("reporteListaUsuarios");
        
        return mav;
    }
    
    @RequestMapping(value = "reporteListaLibros.htm", method = RequestMethod.GET)
    public ModelAndView reporteListaLibros(HttpServletResponse response) throws JRException, IOException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }
        
        ServletOutputStream sos = response.getOutputStream();
        //File reporte = new File(getClass().getProtectionDomain().getClassLoader().getResource("../../reportes/listaLibros.jasper").getPath().replace("%20", " "));
        File reporte = new File(request.getServletContext().getRealPath("/reportes/listaPrestamos.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(reporte.getPath(), null, daoLibro.conexion());
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        response.setHeader("Content-disposition", "inline; filename=Reporte: Lista de Usuarios.pdf");
        sos.write(bytes, 0, bytes.length);
        sos.flush();
        sos.close();
        
        mav.setViewName("reporteListaLibros");
        
        return mav;
    }
    
    @RequestMapping(value = "reporteListaAutores.htm", method = RequestMethod.GET)
    public ModelAndView reporteListaAutores(HttpServletResponse response) throws JRException, IOException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }
        
        ServletOutputStream sos = response.getOutputStream();
        //File reporte = new File(getClass().getProtectionDomain().getClassLoader().getResource("../../reportes/listaAutores.jasper").getPath().replace("%20", " "));
        File reporte = new File(request.getServletContext().getRealPath("/reportes/listaPrestamos.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(reporte.getPath(), null, daoAutor.conexion());
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        response.setHeader("Content-disposition", "inline; filename=Reporte: Lista de Usuarios.pdf");
        sos.write(bytes, 0, bytes.length);
        sos.flush();
        sos.close();
        
        mav.setViewName("reporteListaAutores");
        
        return mav;
    }
    
    @RequestMapping(value = "reporteListaEditoriales.htm", method = RequestMethod.GET)
    public ModelAndView reporteListaEditoriales(HttpServletResponse response) throws JRException, IOException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }
        
        ServletOutputStream sos = response.getOutputStream();
        //File reporte = new File(getClass().getProtectionDomain().getClassLoader().getResource("../../reportes/listaEditoriales.jasper").getPath().replace("%20", " "));
        File reporte = new File(request.getServletContext().getRealPath("/reportes/listaPrestamos.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(reporte.getPath(), null, daoEditorial.conexion());
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        response.setHeader("Content-disposition", "inline; filename=Reporte: Lista de Usuarios.pdf");
        sos.write(bytes, 0, bytes.length);
        sos.flush();
        sos.close();
        
        mav.setViewName("reporteListaEditoriales");
        
        return mav;
    }
    
    @RequestMapping(value = "reporteListaPrestamos.htm", method = RequestMethod.GET)
    public ModelAndView reporteListaPrestamos(HttpServletResponse response) throws JRException, IOException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }
        
        ServletOutputStream sos = response.getOutputStream();
        //File reporte = new File(getClass().getProtectionDomain().getClassLoader().getResource("../../reportes/listaPrestamos.jasper").getPath().replace("%20", " "));
        File reporte = new File(request.getServletContext().getRealPath("/reportes/listaPrestamos.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(reporte.getPath(), null, daoPrestamo.conexion());
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        response.setHeader("Content-disposition", "inline; filename=Reporte: Lista de Usuarios.pdf");
        sos.write(bytes, 0, bytes.length);
        sos.flush();
        sos.close();
        
        mav.setViewName("reporteListaPrestamos");
        
        return mav;
    }
    
    /**
     * ****************************FUNCIONES PARA GRAFICAS*****************************
     */
    
    @RequestMapping("graficaLibrosXAutor.htm")
    public ModelAndView graficarLibrosXAutor() throws IOException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }
        
        JFreeChart grafica = ChartFactory.createPieChart3D("Libros por Autor", getGraficaLibrosXAutor(), true, true, Locale.getDefault());
        //String archivo = new File(getClass().getProtectionDomain().getClassLoader().getResource("../../").getPath().replace("%20", " ")).getPath() + "\\graficaLXA.png";
        String archivo = request.getServletContext().getRealPath("/graficaLXA.png");
        ChartUtils.saveChartAsPNG(new File(archivo), grafica, 500, 500);

        mav.addObject("tipo", tipoUsuario);
        mav.setViewName("graficaLibrosXAutor");
        
        return mav;
    }
    
    @RequestMapping("graficaLibrosXEditorial.htm")
    public ModelAndView graficarLibrosXEditorial() throws IOException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }
        
        JFreeChart grafica = ChartFactory.createPieChart3D("Libros por Editorial", getGraficaLibrosXEditorial(), true, true, Locale.getDefault());
        //String archivo = new File(getClass().getProtectionDomain().getClassLoader().getResource("../../").getPath().replace("%20", " ")).getPath() + "\\graficaLXE.png";
        String archivo = request.getServletContext().getRealPath("/graficaLXE.png");
        ChartUtils.saveChartAsPNG(new File(archivo), grafica, 500, 500);

        mav.addObject("tipo", tipoUsuario);
        mav.setViewName("graficaLibrosXEditorial");
        
        return mav;
    }

    @RequestMapping("graficaLibrosXGenero.htm")
    public ModelAndView graficarLibrosXGenero() throws IOException {
        if (sesion == null) {
            return new ModelAndView("redirect:/login.htm");
        }
        
        JFreeChart grafica = ChartFactory.createPieChart3D("Libros por Género", getGraficaLibrosXGenero(), true, true, Locale.getDefault());
        //String archivo = new File(getClass().getProtectionDomain().getClassLoader().getResource("../../").getPath().replace("%20", " ")).getPath() + "\\graficaLXG.png";
        String archivo = request.getServletContext().getRealPath("/graficaLXG.png");
        ChartUtils.saveChartAsPNG(new File(archivo), grafica, 500, 500);
        
        mav.addObject("tipo", tipoUsuario);
        mav.setViewName("graficaLibrosXGenero");
        
        return mav;
    }

    private PieDataset getGraficaLibrosXAutor() {
        DefaultPieDataset pie3d = new DefaultPieDataset();
        GraficaDAO dao = new GraficaDAO();
        List datos = dao.listaLXA();
        
        for (int i = 0; i < datos.size(); i++) {
            GraficaDTO dto = (GraficaDTO) datos.get(i);
            pie3d.setValue(dto.getEntidad().getNombre() + ": " + (int) dto.getEntidad().getCantidad(), (int) dto.getEntidad().getCantidad());
        }
        
        return pie3d;
    }
    
    private PieDataset getGraficaLibrosXEditorial() {
        DefaultPieDataset pie3d = new DefaultPieDataset();
        GraficaDAO dao = new GraficaDAO();
        List datos = dao.listaLXE();
        
        for (int i = 0; i < datos.size(); i++) {
            GraficaDTO dto = (GraficaDTO) datos.get(i);
            System.out.println(dto);
            pie3d.setValue(dto.getEntidad().getNombre() + ": " + (int) dto.getEntidad().getCantidad(), (int) dto.getEntidad().getCantidad());
        }
        
        return pie3d;
    }

    private PieDataset getGraficaLibrosXGenero() {
        DefaultPieDataset pie3d = new DefaultPieDataset();
        GraficaDAO dao = new GraficaDAO();
        List datos = dao.listaLXG();
        
        for (int i = 0; i < datos.size(); i++) {
            GraficaDTO dto = (GraficaDTO) datos.get(i);
            pie3d.setValue(dto.getEntidad().getNombre() + ": " + (int) dto.getEntidad().getCantidad(), (int) dto.getEntidad().getCantidad());
        }
        
        return pie3d;
    }
}

















