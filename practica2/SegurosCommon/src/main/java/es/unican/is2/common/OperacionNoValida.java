package es.unican.is2.common;

public class OperacionNoValida extends RuntimeException {

    public OperacionNoValida(String message) {
        super(message);
    }

}
