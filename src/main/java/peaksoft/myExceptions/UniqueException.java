package peaksoft.myExceptions;

import org.hibernate.NonUniqueObjectException;

/**
 * The golden boy
 */
public class UniqueException extends Exception {

    public UniqueException(String message) {
        super(message);
    }
}
