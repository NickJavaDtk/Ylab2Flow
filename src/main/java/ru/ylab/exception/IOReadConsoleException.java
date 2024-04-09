package ru.ylab.exception;

/**
 * Класс собственного исключения для вывода информации о ошибке понятной пользователю
 */

public class IOReadConsoleException extends RuntimeException{

    /**
     *
     * @param message текст ошибки для вывода на консоль
     */
    public IOReadConsoleException(String message) {
        super(message);
    }
}
