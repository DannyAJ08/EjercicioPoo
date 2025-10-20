public class Animal {

    protected String nombre;
    protected byte edad;
    protected String color;

    public Animal (String nombreObjeto, byte edadObjeto, String colorObjeto){
        nombre = nombreObjeto;
        edad = edadObjeto;
        color = colorObjeto;
    }

    public String getNombre() {
        return nombre;
    }

    public byte getEdad() {
        return edad;
    }

    public String getColor() {
        return color;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public void setColor(String color) {
        this.color = color;
    }

    void hacerSonido(){

        System.out.println("El gato hace Miau");

    }
}


