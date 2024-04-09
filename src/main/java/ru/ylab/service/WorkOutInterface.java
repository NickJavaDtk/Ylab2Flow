package ru.ylab.service;

import ru.ylab.entitys.WorkOut;
import sun.util.resources.LocaleData;

public interface WorkOutInterface<T extends WorkOut> {

    T createWorkOut(String name, LocaleData dataTraining, String typeTraining, int durationTraining, int countCalorie);

    Boolean addListWorkOut(T workout);




}
