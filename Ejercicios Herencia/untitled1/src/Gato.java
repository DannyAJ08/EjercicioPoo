public class Gato extends Animal {

    private String raza;

    public Gato (String nombreObjeto, byte edadObjeto, String  colorObjeto, String razaObjeto){
        super(nombreObjeto,edadObjeto, colorObjeto);
        raza = razaObjeto;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }


}
