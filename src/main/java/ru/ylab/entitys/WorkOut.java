package ru.ylab.entitys;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import sun.util.resources.LocaleData;


@AllArgsConstructor
public abstract class WorkOut {
    private String name;
    private LocaleData dataTraining;
    private String typeTraining;
    private int durationTraining;
    private int countCalorie;




}
