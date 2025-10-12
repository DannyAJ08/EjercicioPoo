public class Persona {

    private String nombre;
    private String apellidos;
    private boolean tienePasaporte;

    public Persona(String nombreObjeto, String apellidosObjeto, boolean tienePasaporteObjeto){
        nombre = nombreObjeto;
        apellidos = apellidosObjeto;
        tienePasaporte = tienePasaporteObjeto;
    }


    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public boolean getTienePasaporte(boolean b) {
        return tienePasaporte;
    }

    public void setNombre(String nombreObjeto){
        nombre = nombreObjeto;
    }

    public void setApellidos(String apellidosObjeto) {
        apellidos = apellidosObjeto;
    }

    public void setTienePasaporte(boolean tienePasaporte) {
        this.tienePasaporte = tienePasaporte;
    }

    void sacarPasaporte(){

    }

    void tomarVuelo(Pasaporte estaVigente, Main destino){
        if (true) {
            System.out.println(nombre + " tomo un vuelo hacia" + destino);
        }else {
            System.out.println(nombre + "no pudo tomar el vuelo debido a que su pasaporte no está vigente");
        }

    }



}
