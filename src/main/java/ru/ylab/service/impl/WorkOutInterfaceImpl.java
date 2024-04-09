package ru.ylab.service.impl;

import ru.ylab.dao.InternalMemory;
import ru.ylab.entitys.CustomWorkOut;
import ru.ylab.entitys.WorkOut;
import ru.ylab.service.WorkOutInterface;
import sun.util.resources.LocaleData;

public class WorkOutInterfaceImpl implements WorkOutInterface<CustomWorkOut> {


    @Override
    public CustomWorkOut createWorkOut(String name, LocaleData dataTraining, String typeTraining, int durationTraining, int countCalorie) {
        return CustomWorkOut.builder()
                .name(name)
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
}
