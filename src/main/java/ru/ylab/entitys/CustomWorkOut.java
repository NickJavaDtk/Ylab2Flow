package ru.ylab.entitys;


import lombok.*;


import java.time.LocalDate;

@Getter
@Setter
@Builder

public class CustomWorkOut extends WorkOut {
    private LocalDate dataTraining;
    private String typeTraining;
    private int durationTraining;
    private int countCalorie;
    private AdditionalInformation information;


    public CustomWorkOut(LocalDate dataTraining, String typeTraining,
                         int durationTraining, int countCalorie, AdditionalInformation information) {
        super(dataTraining, typeTraining, durationTraining, countCalorie);
        this.information = information;

    }

}
