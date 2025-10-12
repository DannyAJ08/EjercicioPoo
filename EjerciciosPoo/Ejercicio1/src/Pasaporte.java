public class Pasaporte {
    private int numero;
    private String nacionalidad;
    private String fechaEmision;
    private String fechaVencimiento;
    private boolean estaVigente;

    public Pasaporte(int numero, String nacionalidad, String fechaEmision, String fechaVencimiento, boolean estaVigente){
        this.numero = numero;
        this.nacionalidad = nacionalidad;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.estaVigente = estaVigente;
    }

    public int getNumero() {
        return numero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public boolean isEstaVigente() {
        return estaVigente;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setEstaVigente(boolean estaVigente) {
        this.estaVigente = estaVigente;
    }
}
