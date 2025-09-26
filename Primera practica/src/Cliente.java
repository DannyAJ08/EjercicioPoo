public class Cliente {

    private String nombre;
    private String apellido1;
    private String apellido2;
    private String sexo;
    private String ubicacion;
    private String cedula;

    public Cliente(String nombreObjeto, String apellido1Objeto, String apellido2Objeto,
            String sexoObjeto, String ubicacionObjeto, String cedulaObjeto) {
        nombre = nombreObjeto;
        apellido1 = apellido1Objeto;
        apellido2 = apellido2Objeto;
        sexo = sexoObjeto;
        ubicacion = ubicacionObjeto;
        cedula = cedulaObjeto;
    }

    public Cliente(String nombreObjeto, String apellido1Objeto, String apellido2Objeto, String ubicacionObjeto, String cedulaObjeto) {
        nombre = nombreObjeto;
        apellido1 = apellido1Objeto;
        apellido2 = apellido2Objeto;
        ubicacion = ubicacionObjeto;
        cedula = cedulaObjeto;

    }

    public Cliente() {
    }



    void suscribirse(Subscripcion sub){
        System.out.println(nombre + " "+ apellido1+ " "+ apellido2+ " adquirio una suscripcion "+ sub.getTipo());
    }

    //getters
    public String getNombre(){
        return nombre;
    }

    public String getApellido1(){
        return apellido1;
    }

    public String getApellido2(){
        return apellido2;
    }

    public String getSexo(){
        return sexo;
    }

    public String getUbicacion(){
        return ubicacion;
    }

    public String getCedula() {
        return cedula;
    }

    //setters

    public void setNombre(String nuevoNombre){
        nombre = nuevoNombre;
    }

    public void setApellido1(String nuevoApellido1){
        nombre = nuevoApellido1;
    }

    public void setApellido2(String nuevoApellido2){
        nombre = nuevoApellido2;
    }

    public void setSexo(String nuevoSexo){
        nombre = nuevoSexo;
    }

    public void setUbicacion(String nuevoUbicacion){
        nombre = nuevoUbicacion;
    }

    public void setCedula(String nuevoCedula){
        nombre = nuevoCedula;
    }


    //equals()
    public boolean equals(Cliente clienteComparar){
        return  cedula.equals(clienteComparar.cedula);
    }

    // toString
    public String toString(){
        return "Nombre: " +nombre + "\nApellido1: " +apellido1 + "\nApellido2: " +apellido2 + "\nSexo: " +sexo + "\nUbicacion: " +ubicacion + "\nApellido1: " +cedula + "\n";   }

}