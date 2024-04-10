package ru.ylab.service.impl;

import ru.ylab.dao.InternalMemory;
import ru.ylab.entitys.CustomWorkOut;
import ru.ylab.entitys.Users;
import ru.ylab.entitys.WorkOut;
import ru.ylab.exception.InputParameterException;
import ru.ylab.logger.CustomLogger;
import ru.ylab.service.WorkOutInterface;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkOutInterfaceImpl implements WorkOutInterface<CustomWorkOut> {


    @Override
    public CustomWorkOut createWorkOut(LocalDate dataTraining, String typeTraining, int durationTraining, int countCalorie) {
        return CustomWorkOut.builder()
                .dataTraining(dataTraining)
                .typeTraining(typeTraining)
                .durationTraining(durationTraining)
                .countCalorie(countCalorie)
                .build();
    }

    @Override
    public Boolean addListWorkOut(CustomWorkOut workout) {
        return InternalMemory.workOutsList.add(workout);
    }

    @Override
    public void addMapWorkOut(CustomWorkOut workout, Users users) {
        String login = users.getUsername();
        List<WorkOut> list = new ArrayList<>();
        list.add(workout);
        if (InternalMemory.workOutsMap.containsKey(login)) {
            List<WorkOut> listMap = InternalMemory.workOutsMap.get(login);
            listMap.add(workout);
        } else {
            InternalMemory.workOutsMap.put(login, list);
        }
    }

    @Override
    public Boolean checkTrainingDone(LocalDate dataTraining, String typeTraining, Users users) {
        if (InternalMemory.workOutsList.size() == 0) {
            return true;
        } else {
            CustomWorkOut custom = (CustomWorkOut) InternalMemory.workOutsList.stream()
                    .forEach(da -> da.)
                    .filter(data -> data.).findAny().orElse(null);
            if (custom == null) {
                return true;
            } else {
                if (custom.getTypeTraining().equals(typeTraining)) {
                    CustomLogger.addMessageLogger("Пользователь " + users.getUsername() + " пытается ввести тренировку с одним типом" +
                            "в один день");
                    new InputParameterException("Нельзя ввести в один день две тренировки с одинм типом");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkInputParameters(int value, String messageLogger, String messageConsole) {
        if (value <= 0) {
            CustomLogger.addMessageLogger(messageLogger);
            new InputParameterException(messageConsole);
        }
        return true;
    }
}
