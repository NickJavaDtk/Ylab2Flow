package ru.ylab.entitys;


import lombok.*;
import lombok.experimental.SuperBuilder;
import sun.util.resources.LocaleData;

@Getter
@Setter
@Builder

public class CustomWorkOut extends WorkOut {
    private String name;
    private LocaleData dataTraining;
    private String typeTraining;
    private int durationTraining;
    private int countCalorie;
    private AdditionalInformation information;


    public CustomWorkOut(String name, LocaleData dataTraining, String typeTraining,
                         int durationTraining, int countCalorie, AdditionalInformation information) {
        super(name, dataTraining, typeTraining, durationTraining, countCalorie);
        this.information = information;

    }

}
