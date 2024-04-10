package ru.ylab.service;

import ru.ylab.entitys.Users;
import ru.ylab.entitys.WorkOut;


import java.time.LocalDate;

public interface WorkOutInterface<T extends WorkOut> {

    T createWorkOut(LocalDate dataTraining, String typeTraining, int durationTraining, int countCalorie);

    Boolean addListWorkOut(T workout);

    void addMapWorkOut(T workout, Users users);

    /**
     * Метод проверяющий есть ли в памяти приложения тренировка с данным типом в один день
     * @param dataTraining передается дата тренировки
     * @param typeTraining передается тип тренировки
     * @return true если данной тренировки в данный день еще не было иначе false
     */
    Boolean checkTrainingDone(LocalDate dataTraining, String typeTraining, Users users);

    /**
     * Метод для проверки полей (чтобы нельзя было ввести нулевые или отрицательные параметры)
     * @param value передается введенное значение с консоли
     * @param messageLogger информация для внесения в логгер
     * @param messageConsole информация для пользователя
     * @return true если параметры валидны
     */
    boolean checkInputParameters(int value, String messageLogger, String messageConsole);




}
