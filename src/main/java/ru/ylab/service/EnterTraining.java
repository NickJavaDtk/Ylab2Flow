package ru.ylab.service;

import ru.ylab.entitys.Users;

public interface EnterTraining {
    /**
     * Метод позволяет пользователю создавать новую тренировку под себя
     * @param users передается текущий пользователь
     */
    void createTraining(Users users);
}
