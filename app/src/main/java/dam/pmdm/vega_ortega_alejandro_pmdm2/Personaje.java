package dam.pmdm.vega_ortega_alejandro_pmdm2;

/**
 * Esta clase representa al Personaje de nuestra aplicación.
 * Contiene atributos relacionados con la información del personaje y sus habilidades.
 */
public class Personaje {
    private String nombre;
    private String descripcion;
    private int imagenId;
    private String habilidad;

    /**
     * Crea un nuevo personaje con el nombre, descripcion, imagen y habilidad especificados.
     *
     * @param nombre El nombre del personaje.
     * @param descripcion Descripción del personaje.
     * @param imagenId Imagen del personaje.
     * @param habilidad Habilidad del personaje.
     */
    public Personaje(String nombre, String descripcion, int imagenId, String habilidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenId = imagenId;
        this.habilidad = habilidad;
    }

    /**
     * Nombre del personaje.
     * Este campo se utiliza para almacenar el nombre de un personaje.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Descripción del personaje.
     * Este campo se utiliza para almacenar la descripción de un personaje.
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Imagen del personaje.
     * Este campo se utiliza para almacenar la imagen de un personaje.
     */
    public int getImagenId() {
        return imagenId;
    }
    /**
     * Habilidad del personaje.
     * Este campo se utiliza para almacenar la habilidad de un personaje.
     */
    public String getHabilidad() {
        return habilidad;
    }
}
