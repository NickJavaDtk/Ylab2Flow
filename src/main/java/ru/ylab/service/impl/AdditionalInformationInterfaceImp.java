package ru.ylab.service.impl;

import ru.ylab.entitys.AdditionalInformation;
import ru.ylab.service.AdditionalInformationInterface;

public class AdditionalInformationInterfaceImp implements AdditionalInformationInterface {
    @Override
    public AdditionalInformation createAdditionalInformation(int countExercise, int distance, String sportsNutrition) {
        if (countExercise > 0 && distance > 0 && (sportsNutrition != null && sportsNutrition.length() > 0) ) {
            return AdditionalInformation.builder()
                    .countExercise(countExercise)
                    .distance(distance)
                    .sportsNutrition(sportsNutrition)
                    .build();
        } else if (countExercise > 0  && (sportsNutrition != null && sportsNutrition.length() > 0))  {
            return AdditionalInformation.builder()
                    .countExercise(countExercise)
                    .sportsNutrition(sportsNutrition)
                    .build();
        } else if (distance > 0 && (sportsNutrition != null && sportsNutrition.length() > 0))  {
            return AdditionalInformation.builder()
                    .distance(distance)
                    .sportsNutrition(sportsNutrition)
                    .build();
        } else if (countExercise > 0 && distance > 0 ) {
            return AdditionalInformation.builder()
                    .countExercise(countExercise)
                    .distance(distance)
                    .build();
        } else if (sportsNutrition != null && sportsNutrition.length() > 0) {
            return AdditionalInformation.builder()
                    .sportsNutrition(sportsNutrition)
                    .build();
        } else if (countExercise > 0) {
            return AdditionalInformation.builder()
                    .countExercise(countExercise)
                    .build();
        } else if (distance > 0) {
            return AdditionalInformation.builder()
                    .distance(distance)
                    .build();
        }
        return null;
    }
}
