public class Subscripcion {

    private String tipo;
    private double costo;
    private String periodoPago;

    Subscripcion(String tipoObjeto, double costoObjeto, String periodoPagoObjeto){
        tipo = tipoObjeto;
        costo = costoObjeto;
        periodoPago = periodoPagoObjeto;
    }

    public String getTipo(){
        return tipo;
    }

    public double getCosto() {
        return costo;
    }

    public String getPeriodoPago() {
        return periodoPago;
    }

    public void setTipo(String nuevoTipo) {
        tipo = nuevoTipo;
    }

    public void setCosto(double nuevoCosto) {
        costo = nuevoCosto;
    }

    public void setPeriodoPago(String nuevoPeriodoPago) {
        periodoPago = nuevoPeriodoPago;
    }

    //equals
    public boolean equals(Subscripcion suscripcionComparar ) {
        return tipo.equals(suscripcionComparar.getTipo()) &&
                periodoPago.equals(suscripcionComparar.getPeriodoPago());
    }


    //toString

    public String toString(){
        return "Tipo: " + tipo + "\nCosto: " + costo + "\nPeriodo de pago: " + periodoPago + "\n";   }

}

