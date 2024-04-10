package ru.ylab.service;

import ru.ylab.console.UIclass;
import ru.ylab.dao.InternalMemory;
import ru.ylab.entitys.AdditionalInformation;
import ru.ylab.entitys.CustomWorkOut;
import ru.ylab.entitys.Users;
import ru.ylab.in.ReadConsole;
import ru.ylab.service.impl.WorkOutInterfaceImpl;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class EnterTraining {
    private static final String START = "Для создания тренировки вам будет необходимо ввести:\n" +
            "Дату тренировки в формате dd.MM.yyyy например 10.04.2024\n" +
            "Тип тренировки (кардио, силовая тренировка, йога)\n" +
            "Длительность тренировки в минутах\n" +
            "Количество потраченных калорий" +
            "После ввода количества калорий вам будет предложено ввести новую тренировку или добавить\n" +
            "дополнительные параметры тренировки или выйти из этапа внесения информации о тренировке";
    private final String BEFOREDATE = "Введите дату тренировки";
    private final String BEFORETYPE = "Введите тип тренировки";
    private final String BEFORETIME = "Введите длительность тренировки в минутах";
    private final String BEFORECOUNT = "Введите количество потраченных калорий";
    private final String BEFOREEXIT = "Введите 1 для того чтобы ввести новую тренировку 2 чтобы ввести дополнительные\n" +
            "параметры тренировки 0 чтобы выйти из этапа внесения информации о тренировке";
    private final String BEFOREADD = "Для дополнительных параметров тренировки вам необходимо будет ввести\n" +
            "Количество выполненных упражнений\n" +
            "Пройденное расстояние\n" +
            "Спортивное питание во время тренировки";
    private final String BEFOREADDCOUNT = "Введите количество упражнений";
    private final String BEFOREADDMOVE = "Введите пройденное расстояние";
    private final String BEFOREADDFOOD = "Введите марку спортивного питание или введите - питание не использовалось";

    ReadConsole readConsole = new ReadConsole();
    WorkOutInterfaceImpl work = new WorkOutInterfaceImpl();

    public void createTraining(Users users) {
        while (true) {
            if (InternalMemory.workOutsList.size() == 0) {
                UIclass.sendConsole(START);
            }
            UIclass.sendConsole(BEFOREDATE);
            LocalDateTime localDateTime = readConsole.readDate();
            LocalDate data = localDateTime.toLocalDate();
            UIclass.sendConsole(BEFORETYPE);
            String type = readConsole.readString();
            work.checkTrainingDone(data, type, users);
            UIclass.sendConsole(BEFORETIME);
            int time = (int) readConsole.readValue();
            work.checkInputParameters(time, "Пользователь " + users.getUsername() + " ввел некорретную" +
                    " длительность тренировки", "Введеное значение длительности тренировки должно быть больше нуля");
            UIclass.sendConsole(BEFORECOUNT);
            int count = (int) readConsole.readValue();
            work.checkInputParameters(count, "Пользователь " + users.getUsername() + " ввел некорретное" +
                    " количество потраченных калорий", "Введеное значение количества потраченных калорий должно быть больше нуля");
            CustomWorkOut workOut = work.createWorkOut(data, type, time, count);
            UIclass.sendConsole(BEFOREEXIT);
            int value = (int) readConsole.readValue();
            switch (value) {
                case 1 -> {
                    work.addListWorkOut(workOut);
                    work.addMapWorkOut(workOut, users);
                }
                case 2 -> {
                    UIclass.sendConsole(BEFOREADD);
                    UIclass.sendConsole(BEFOREADDCOUNT);
                    int countAdd = (int) readConsole.readValue();
                    work.checkInputParameters(countAdd, "Пользователь " + users.getUsername() + " ввел некорретное" +
                            " количество упражнений", "Введеное значение количества упражнений должно быть больше нуля");
                    UIclass.sendConsole(BEFOREADDMOVE);
                    int move = (int) readConsole.readValue();
                    work.checkInputParameters(move, "Пользователь " + users.getUsername() + " ввел некорретную" +
                            " длину пройденного расстояния", "Введеное значение пройденного расстояния должно быть больше нуля");
                    UIclass.sendConsole(BEFOREADDFOOD);
                    String food = readConsole.readString();
                    workOut.setInformation(new AdditionalInformation(countAdd, move, food));
                    work.addListWorkOut(workOut);
                    work.addMapWorkOut(workOut, users);
                }
                case 0 -> {
                    work.addListWorkOut(workOut);
                    work.addMapWorkOut(workOut, users);
                    break;
                }
            }


        }
    }


}
