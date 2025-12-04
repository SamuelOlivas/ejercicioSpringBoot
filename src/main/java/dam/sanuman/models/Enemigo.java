package dam.sanuman.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.annotation.Id;

@Document(collection = "enemigos")
public class Enemigo {
    @Id
    private String id;
    @Indexed(unique = true)
    private String nombre;
    private String pais;
    private String afiliacion_politica;

    public Enemigo() {

    }

    public Enemigo(String id, String nombre, String pais, String afiliacion_politica) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.afiliacion_politica = afiliacion_politica;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getAfiliacion_politica() {
        return afiliacion_politica;
    }

    public void setAfiliacion_politica(String afiliacion_politica) {
        this.afiliacion_politica = afiliacion_politica;
    }

    @Override
    public String toString() {

        return "Enemigo{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", afiliacion_politica='" + afiliacion_politica + '\'' +
                '}';

    }
}
