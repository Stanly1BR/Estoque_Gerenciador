package org.example.exceptions;

public class ProdutoInvalidoException extends RuntimeException {
    public ProdutoInvalidoException(String message) {
        super(message);
    }
}
