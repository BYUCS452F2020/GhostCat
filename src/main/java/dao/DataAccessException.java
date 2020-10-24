package dao;

/**
 * Class used for throwing exceptions when SQL errors occur while accessing our database
 */
public class DataAccessException extends Exception {
    DataAccessException(String message)
    {
        super(message);
    }

    DataAccessException()
    {
        super();
    }
}
