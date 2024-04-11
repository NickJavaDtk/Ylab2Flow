package ru.ylab.entitys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;
import java.util.Objects;


@Getter
@Setter
@SuperBuilder
public abstract class WorkOut {
    private LocalDate dataTraining;
    private String typeTraining;
    private int durationTraining;
    private int countCalorie;

    public WorkOut(LocalDate dataTraining, String typeTraining, int durationTraining, int countCalorie) {
    }


    @Override
    public String toString() {
        return "Доступные тренировки{" +
                "на=" + dataTraining +
                ", Тип тренировки='" + typeTraining + '\'' +
                ", Длительность тренировки=" + durationTraining +
                ", Минимальное количество потраченных калорий=" + countCalorie +
                '}';
    }
}
