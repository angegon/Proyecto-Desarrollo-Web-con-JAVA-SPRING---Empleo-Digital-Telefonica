package managedBeans;

import java.util.ArrayList;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;


@Named(value = "preguntaExamen")
@RequestScoped
public class PreguntaExamen {

    boolean respuesta;

    public boolean isRespuesta() {

        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        ArrayList respuestas = new ArrayList<>();
        respuestas.add(respuesta);
        this.respuesta = respuesta;
    }

    public PreguntaExamen() {
    }

    public void corregir() {

    }

}
