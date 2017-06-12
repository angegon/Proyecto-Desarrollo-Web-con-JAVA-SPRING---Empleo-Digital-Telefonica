

package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import persistencia.Cursos;
import persistencia.Cursosedicion;
import persistencia.Matricula;
import persistencia.Preguntas;
import persistencia.Respuestas;
import persistencia.Usuarios;

@Component(value = "gestion")

public class GestionRecursos implements IntefaceGestion {

    @PersistenceContext(name = "plataformaPU")
    EntityManager em;

    @Override
    public String obtenerUsuario(String dni, String password) {
        Usuarios us = em.find(Usuarios.class, dni);
        if (us != null) {
            if (us.getDni().equals(dni) && us.getContraseña().equals(password)) {
                //Inicia la sesion
                if (us.getFuncion().equals("alumno")) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                    return "indexAlumno";
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("administrador", us);
                    return "indexAdmin";
                }
            } else {
                return "error";
            }
        } else {
            return "error";
        }
    }

    //Introduce un objeto curso
    @Transactional
    @Override
    public boolean introducirCurso(String idcurso, String nombre, String descripcion, String contenido) {
        Cursos cu = new Cursos();
        cu.setNombre(nombre);
        cu.setIdcurso(idcurso);
        cu.setContenido(contenido);
        cu.setDescripcion(descripcion);
        em.persist(cu);
        return true;
    }

    @Override
    public List<Cursos> listarCursos() {
        Query sql = em.createNamedQuery("Cursos.findAll");
        ArrayList<Cursos> cur = (ArrayList<Cursos>) sql.getResultList();
        return cur;
    }

    @Override
    public List<Usuarios> listarProfesores() {

        Query q = em.createNamedQuery("Usuarios.findByFuncion").setParameter("funcion", "profesor");
        ArrayList<Usuarios> profesores = (ArrayList<Usuarios>) q.getResultList();
        return profesores;
    }

    public Usuarios obtenerUsuDni(String dni) {
        Usuarios us = em.find(Usuarios.class, dni);
        return us;
    }

    public Cursos obtenerPorId(String id) {
        Cursos cu = em.find(Cursos.class, id);
        return cu;
    }

    @Transactional
    @Override
    public boolean introEdicion(Date fIni, Date fFin, String idCurso, String idProfesor) {
        Cursosedicion ce = new Cursosedicion();
        Usuarios us = obtenerUsuDni(idProfesor);
        Cursos cu = obtenerPorId(idCurso);
        ce.setFechainicio(fIni);
        ce.setFechafin(fFin);
        ce.setIdprofesor(us);
        ce.setIdcurso(cu);
        Query q = em.createNamedQuery("Cursosedicion.contarEdiciones").setParameter("idcurso", cu);
        Object num = q.getSingleResult();
        long numero = (long) num;
        numero++;
        ce.setIdcursoedicion(idCurso + numero);
        ce.setNombre(idCurso);
        em.persist(ce);
        return true;
    }

    @Override
    public List<Cursosedicion> listarCursosActivos() {

        Query query = em.createNamedQuery("Cursosedicion.findAll");
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = null;
        try {
            fechaActual = sd.parse(sd.format(new Date()));
        } catch (ParseException ex) {
            ex.getMessage();
        }
        List<Cursosedicion> aux = query.getResultList();
        List<Cursosedicion> lista = new ArrayList<>();
        for (Cursosedicion ce : aux) {
            if (ce.getFechafin().compareTo(fechaActual) > 0) {
                lista.add(ce);
            }
        }
        return lista;
    }

    @Override
    public boolean existeUsuario(String dni) {
        Usuarios usu = null;
        usu = em.find(Usuarios.class, dni);
        if (usu != null) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    @Override
    public boolean matriUsuario(String dni, String idcursoedicion) {
        Usuarios us = em.find(Usuarios.class, dni);
        Cursosedicion ce = em.find(Cursosedicion.class, idcursoedicion);
        Matricula m = new Matricula();
        m.setIdalumno(us);
        m.setIdedicion(ce);
        em.persist(m);
        return true;
    }

    @Override
    public List<Matricula> listadoAlumnosCursosActivos(String idcursoedicion) {
        List<Cursosedicion> CursosActivos = listarCursosActivos();

        List<Matricula> usuariosCursosActivos = null;
        Cursosedicion ce = em.find(Cursosedicion.class, idcursoedicion);
        //Inserción de objeto ce en consulta
        Query q = em.createNamedQuery("Matricula.findEdicion").setParameter("idedicion", ce);
        usuariosCursosActivos = q.getResultList();
        return usuariosCursosActivos;
    }

    @Override
    public List<Cursosedicion> listarCursosBuscados(String idcursoedicion) {
        List<Cursosedicion> cursos;
        Query q = em.createNamedQuery("Cursosedicion.buscarEdiciones").setParameter("idcursoedicion", "%" + idcursoedicion + "%");
        cursos = q.getResultList();
        return cursos;
    }

    @Transactional
    @Override
    public String darDeBajaAlumno(int idalumnocurso) {
        Matricula us = em.find(Matricula.class, idalumnocurso);
        em.remove(us);
        return "showBajaAlumno";
    }

    @Transactional
    @Override
    public boolean darDeAltaUsuarioNuevo(String dni, String nombre, String apellidos, String password, String email) {
        Usuarios nuevo = new Usuarios();
        nuevo.setDni(dni);
        nuevo.setNombre(nombre);
        nuevo.setApellidos(apellidos);
        nuevo.setFuncion("alumno");
        nuevo.setContraseña(password);
        nuevo.setEmail(email);
        em.persist(nuevo);
        return true;
    }

    @Override
    public List<Matricula> listarCursosAlumno(Usuarios us) {
        Query q = em.createNamedQuery("Matricula.buscarPorIdAlumno");
        q.setParameter("idalumno", us);
        List<Matricula> listarMisCursos = q.getResultList();
        return listarMisCursos;
    }

    @Override
    public List<Cursos> listarCursos(Cursos cu) {
        Query sql = em.createNamedQuery("Cursos.findAll");
        ArrayList<Cursos> cur = (ArrayList<Cursos>) sql.getResultList();
        return cur;
    }

    @Override
    public Usuarios obtenerNombre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    @Override
    public boolean insertarPregunta(String pregunta, String[] respuestas, int correcta, String idcurso) {
        Cursos curso = em.find(Cursos.class, idcurso);
        Preguntas pr = new Preguntas();
        pr.setIdcurso(curso);
        pr.setPregunta(pregunta);
        em.persist(pr);
        Query q = em.createNamedQuery("Preguntas.findByPregunta").setParameter("pregunta", pregunta);
        Preguntas idPr = (Preguntas) q.getSingleResult();
        Respuestas rp1 = new Respuestas();
        Respuestas rp2 = new Respuestas();
        Respuestas rp3 = new Respuestas();
        Respuestas rp4 = new Respuestas();
        rp1.setTextorespuesta(respuestas[0]);
        rp2.setTextorespuesta(respuestas[1]);
        rp3.setTextorespuesta(respuestas[2]);
        rp4.setTextorespuesta(respuestas[3]);
        rp1.setCorrecta(false);
        rp2.setCorrecta(false);
        rp3.setCorrecta(false);
        rp4.setCorrecta(false);
        rp1.setIdpregunta(idPr);
        rp2.setIdpregunta(idPr);
        rp3.setIdpregunta(idPr);
        rp4.setIdpregunta(idPr);
        switch (correcta) {
            case 1:
                rp1.setCorrecta(true);
                break;
            case 2:
                rp2.setCorrecta(true);
                break;
            case 3:
                rp3.setCorrecta(true);
                break;
            case 4:
                rp4.setCorrecta(true);
                break;
        }
        em.persist(rp1);
        em.persist(rp2);
        em.persist(rp3);
        em.persist(rp4);
        return true;
    }

    @Override
    public List<Preguntas> listarPreguntas(Cursos idcurso) {
        Query q = em.createNamedQuery("Preguntas.findByIdCurso").setParameter("idcurso", idcurso);
        List<Preguntas> listado = q.getResultList();
        return listado;
    }

    @Override
    public boolean enviar(String destino, String asunto, String mensaje) {
        String usuario = "plataformativa@gmail.com";
        String password = "plataformativa2016";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usuario));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destino));
            message.setSubject(asunto);
            message.setText(mensaje);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Transactional
    @Override
    public boolean insertarNota(int idalumnocurso, double nota) {
        Matricula us = em.find(Matricula.class, idalumnocurso);
        if (us.getNota() == null) {
            us.setNota(nota);
            em.merge(us);
            return true;
        } else {
            return false;
        }
    }
}
