package ru.ylab.entitys;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;
import java.util.Objects;


@AllArgsConstructor
public abstract class WorkOut {
    private LocalDate dataTraining;
    private String typeTraining;
    private int durationTraining;
    private int countCalorie;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        WorkOut workOut = (WorkOut) o;
//        return Objects.equals(dataTraining, workOut.dataTraining) && Objects.equals(typeTraining, workOut.typeTraining);
//    }


}
