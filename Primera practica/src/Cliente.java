public class Cliente {

    String nombre;
    String apellido1;
    String apellido2;
    String sexo;
    String ubicacion;
    long cedula;

    Cliente(String nombreObjeto, String apellido1Objeto, String apellido2Objeto,
            String sexoObjeto, String ubicacionObjeto, long cedulaObjeto) {
        nombre = nombreObjeto;
        apellido1 = apellido1Objeto;
        apellido2 = apellido2Objeto;
        sexo = sexoObjeto;
        ubicacion = ubicacionObjeto;
        cedula = cedulaObjeto;
    }

    Cliente(String nombreObjeto, String apellido1Objeto, String apellido2Objeto, String ubicacionObjeto, long cedulaObjeto) {
        nombre = nombreObjeto;
        apellido1 = apellido1Objeto;
        apellido2 = apellido2Objeto;
        ubicacion = ubicacionObjeto;
        cedula = cedulaObjeto;

    }

    Cliente() {
    }



    public void suscribirse(Subscripcion sub){
        System.out.println(nombre + " "+ apellido1+ " "+ apellido2+ " adquirio una suscripcion "+ sub.tipo);
    }

}