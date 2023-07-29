package br.com.sosDocs.sosDocs.exception;

public class NomeExistenteException extends RuntimeException {
    public NomeExistenteException(Throwable throwable, String message) {
        super(message, throwable);
    }

    public NomeExistenteException(String message) {
        super(message);
    }
}
