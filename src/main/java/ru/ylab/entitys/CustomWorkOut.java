package ru.ylab.entitys;


import lombok.*;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder

public class CustomWorkOut extends WorkOut {
    private LocalDate dataTraining;
    private String typeTraining;
    private int durationTraining;
    private int countCalorie;
    private AdditionalInformation information;


    public CustomWorkOut(LocalDate dataTraining, String typeTraining, int durationTraining, int countCalorie) {
        super(dataTraining, typeTraining, durationTraining, countCalorie);
    }

    public CustomWorkOut(LocalDate dataTraining, String typeTraining,
                         int durationTraining, int countCalorie, AdditionalInformation information) {
        super(dataTraining, typeTraining, durationTraining, countCalorie);
        this.information = information;
    }

    @Override
    public String toString() {
        return "Ваша тренировка{" +
                "от=" + dataTraining +
                ", с типом тренировки='" + typeTraining + '\'' +
                ", длительностью тренировки=" + durationTraining +
                ", количеством потраченных калорий=" + countCalorie +
                '}';
    }


    /**
     * Метод для вывода тренировок доступных в этом зале
     * @return возращает строковое предствление объекта WorkOut
     */
    public String toStringListTraining() {
        return "Доступные тренировки" +
                "на " + dataTraining +
                ", Тип тренировки - '" + typeTraining + '\'' +
                ", Длительность тренировки - " + durationTraining +
                ", Минимальное количество потраченных калорий - " + countCalorie;
    }

}
