/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;
import java.util.List;
import persistencia.Cursos;
import persistencia.Cursosedicion;
import persistencia.Usuarios;
import persistencia.Matricula;
import persistencia.Preguntas;


public interface IntefaceGestion {

    String obtenerUsuario(String dni, String password);

    boolean introducirCurso(String idcurso, String nombre, String descripcion, String contenido);

    List<Cursos> listarCursos();

    List<Usuarios> listarProfesores();

    boolean introEdicion(Date fIni, Date fFin, String idCurso, String idProfesor);

    List<Cursosedicion> listarCursosActivos();

    boolean existeUsuario(String dni);

    boolean matriUsuario(String dni, String idcursoedicion);

    List<Matricula> listadoAlumnosCursosActivos(String idcursoedicion);

    List<Cursosedicion> listarCursosBuscados(String idcursoedicion);

    String darDeBajaAlumno(int idalumnocurso);

    boolean darDeAltaUsuarioNuevo(String dni, String nombre, String apellidos, String password, String email);

    Usuarios obtenerNombre();

    List<Matricula> listarCursosAlumno(Usuarios us);

    List<Cursos> listarCursos(Cursos cu);

    boolean insertarPregunta(String pregunta, String[] respuestas, int correcta, String idcurso);

    List<Preguntas> listarPreguntas(Cursos idcurso);

    boolean enviar(String destino, String asunto, String mensaje);

    boolean insertarNota(int idalumnocurso, double nota);
}
