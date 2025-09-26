public class Comida {

    private String ensalada;
    private String sopa;
    private String espagetti;
    private String arrozPollo;


    public Comida(String ensaladaObjeto, String sopaObjeto, String espagettiObjeto, String arrozPolloObjeto) {
        ensalada = ensaladaObjeto;
        sopa = sopaObjeto;
        espagetti = espagettiObjeto;
        arrozPollo = arrozPolloObjeto;
    }

    public String getEnsalada() {
        return ensalada;
    }

    public String getSopa() {
        return sopa;
    }

    public String getEspagetti() {
        return espagetti;
    }

    public String getArrozPollo() {
        return arrozPollo;
    }

    public void setEnsalada(String ensalada) {
        this.ensalada = ensalada;
    }

    public void setSopa(String sopa) {
        this.sopa = sopa;
    }

    public void setEspagetti(String espagetti) {
        this.espagetti = espagetti;
    }

    public void setArrozPollo(String arrozPollo) {
        this.arrozPollo = arrozPollo;
    }
}
