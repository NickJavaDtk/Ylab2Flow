package ru.ylab.exception;


/**
 * Класс собственного исключения для вывода информации о ошибке понятной пользователю
 */
public class InputParameterException extends RuntimeException{
    /**
     *
     * @param message текст ошибки для вывода на консоль при введение ошибочных параметров тренировки
     */
    public InputParameterException(String message) {
        super(message);
    }
}
