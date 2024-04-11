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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if (InternalMemory.workOutsMap.size() == 0) {
            return true;
        } else {
            List<WorkOut> list = InternalMemory.workOutsMap.get(users.getUsername());
            CustomWorkOut custom = (CustomWorkOut) list.stream().filter(date -> date.getDataTraining().equals(dataTraining)).findAny().orElse(null);
            if (custom == null) {
                return true;
            } else {
                if (custom.getTypeTraining().equals(typeTraining)) {
                    CustomLogger.addMessageLogger("Пользователь " + users.getUsername() + " пытается ввести тренировку с одним типом" +
                            "в один день");
                    throw  new InputParameterException("Нельзя ввести в один день две тренировки с одинм типом");
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

    @Override
    public Map<Integer, CustomWorkOut> viewRecordDiary(Users user) {
        Map<Integer, CustomWorkOut> map = new HashMap<>();
        List<WorkOut> list = InternalMemory.workOutsMap.get(user.getUsername());
        int count = 0;
        for (WorkOut work : list) {
            System.out.println(++count + " " +  work);
            map.put(count, (CustomWorkOut) work);
        }
        return map;
    }

    @Override
    public CustomWorkOut getCustomWork(LocalDate date, String typeTraining, Users user) {
        List<WorkOut> list = InternalMemory.workOutsMap.get(user.getUsername());
        List<WorkOut> listWorkOut = list.stream()
                .filter(localDate -> localDate.getDataTraining().equals(date))
                .collect(Collectors.toList());
        return (CustomWorkOut) listWorkOut.stream()
                .filter(type -> type.getTypeTraining().equals(typeTraining)).findAny().get();
    }

//    @Override
//    public void viewRecordDiary(Users user) {
//        List<WorkOut> list = InternalMemory.workOutsMap.get(user.getUsername());
//        int count = 0;
//        for (WorkOut work : list) {
//            System.out.println(++count + " " + work);
//        }
//    }


}
