package managedBeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import modelo.IntefaceGestion;

@ManagedBean
@RequestScoped
public class RegistrarNuevoCuso {

    @ManagedProperty("#{gestion}")
    IntefaceGestion g;

    ////Añadido////
    String idCurso;
    String nombre;
    String descripcion;
    String contenido;

    private static final long serialVersionUID = 1L;
    private Part file1;

    private String message;

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ///Añadido////
    public IntefaceGestion getG() {
        return g;
    }

    public void setG(IntefaceGestion g) {
        this.g = g;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public RegistrarNuevoCuso() {

    }

    public String registrarCurso() throws IOException {
        String s = uploadFile();
        if (g.introducirCurso(idCurso, nombre, descripcion, contenido)) {
            return "showAltaCompleto";
        } else {
            return "error";
        }
    }

    public String uploadFile() throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context
                .getExternalContext().getContext();
        String path = servletContext.getRealPath("");
        //path ="C:\\Proyectos\\plataformaAdmin\\web\\resources\\";
        boolean file1Success = false;
        if (file1.getSize() > 0) {
            String fileName = Utils.getFileNameFromPart(file1);
            contenido = ("http://localhost:8080/plataforma/" + fileName);

            File outputFile = new File(path + File.separator
                    + File.separator + fileName);
            inputStream = file1.getInputStream();
            outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[Constants.BUFFER_SIZE];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            file1Success = true;
        }

        if (file1Success /*|| file2Success*/) {
            System.out.println("Fichero subido a : " + path);

        } else {

            setMessage("Error, seleccione al menos un fichero!");
        }
        return null;
    }
}
