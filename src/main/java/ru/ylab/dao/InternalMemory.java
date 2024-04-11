package ru.ylab.dao;

import ru.ylab.entitys.AdditionalInformation;
import ru.ylab.entitys.CustomWorkOut;
import ru.ylab.entitys.Users;
import ru.ylab.entitys.WorkOut;


import java.time.LocalDate;
import java.util.*;


public class InternalMemory {
    public static List<Users> usersList = new ArrayList<>();
    public static List<WorkOut> workOutsList = new ArrayList<>();

    public static Map<String, List<WorkOut>> workOutsMap = new HashMap<>();

    static {
        LocalDate date = LocalDate.of(2024, 1, 15);


        CustomWorkOut cardio = CustomWorkOut.builder()
                .dataTraining(date)
                .typeTraining("Кардио")
                .durationTraining(30)
                .countCalorie(60)
                .build();

        CustomWorkOut hard = CustomWorkOut.builder()
                .dataTraining(date)
                .typeTraining("Силовая тренировка")
                .durationTraining(20)
                .countCalorie(90)
                .build();

        CustomWorkOut yuga = CustomWorkOut.builder()
                .dataTraining(date)
                .typeTraining("Йога")
                .durationTraining(60)
                .countCalorie(20)
                .build();

        AdditionalInformation information = AdditionalInformation.builder()
                .countExercise(12)
                .distance(5000)
                .sportsNutrition("Жиросжигатель")
                .build();

        CustomWorkOut weight = CustomWorkOut.builder()
                .dataTraining(date)
                .typeTraining("Похудение")
                .durationTraining(30)
                .countCalorie(80)
                .information(information)
                .build();


        workOutsList.add(cardio);
        workOutsList.add(hard);
        workOutsList.add(yuga);
        workOutsList.add(weight);

    }

}
