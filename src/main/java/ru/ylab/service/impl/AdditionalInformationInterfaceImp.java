package ru.ylab.service.impl;

import ru.ylab.exception.InputParameterException;
import ru.ylab.logger.CustomLogger;
import ru.ylab.service.AdditionalInformationInterface;

public class AdditionalInformationInterfaceImp implements AdditionalInformationInterface {
    @Override
    public boolean checkCountExercise(int countExercise) {
        if (countExercise > 0) {
            return true;
        } else {
            CustomLogger.addMessageLogger("Пользователь ввел некорректное количество упражнений");
            new InputParameterException("Введенное количество упражнений должно быть больше 0");
        }
        return false ;
    }

    @Override
    public boolean checkCountDistance(int distance) {
        if (distance > 0) {
            return true;
        } else {
            CustomLogger.addMessageLogger("Пользователь ввел некорректное пройденное расстояние");
            new InputParameterException("Введенное количество упражнений должно быть больше 0");
        }
        return false ;
    }

    @Override
    public boolean checkCountSportNutrition(String sportsNutrition) {
        if (sportsNutrition != null && sportsNutrition.length() > 3) {
            return true;
        } else {
            CustomLogger.addMessageLogger("Пользователь ввел некорретное название спортивного питания");
            new InputParameterException("Длина названия спортивного питания должна быть более 3 символов");
        }
        return false ;
    }


//    @Override
//    public AdditionalInformation createAdditionalInformation(int countExercise, int distance, String sportsNutrition) {
//        if (countExercise > 0 && distance > 0 && (sportsNutrition != null && sportsNutrition.length() > 0) ) {
//            return AdditionalInformation.builder()
//                    .countExercise(countExercise)
//                    .distance(distance)
//                    .sportsNutrition(sportsNutrition)
//                    .build();
//        } else if (countExercise > 0  && (sportsNutrition != null && sportsNutrition.length() > 0))  {
//            return AdditionalInformation.builder()
//                    .countExercise(countExercise)
//                    .sportsNutrition(sportsNutrition)
//                    .build();
//        } else if (distance > 0 && (sportsNutrition != null && sportsNutrition.length() > 0))  {
//            return AdditionalInformation.builder()
//                    .distance(distance)
//                    .sportsNutrition(sportsNutrition)
//                    .build();
//        } else if (countExercise > 0 && distance > 0 ) {
//            return AdditionalInformation.builder()
//                    .countExercise(countExercise)
//                    .distance(distance)
//                    .build();
//        } else if (sportsNutrition != null && sportsNutrition.length() > 0) {
//            return AdditionalInformation.builder()
//                    .sportsNutrition(sportsNutrition)
//                    .build();
//        } else if (countExercise > 0) {
//            return AdditionalInformation.builder()
//                    .countExercise(countExercise)
//                    .build();
//        } else if (distance > 0) {
//            return AdditionalInformation.builder()
//                    .distance(distance)
//                    .build();
//        }
//        return null;
//    }
}
