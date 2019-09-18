package net.klostergaard.basketservice.exceptions;

public class BasketNotExistException extends IndexOutOfBoundsException {
    public BasketNotExistException(String s) {
        super(s);
    }
}
