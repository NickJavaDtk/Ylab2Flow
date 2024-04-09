package ru.ylab.service;

import ru.ylab.entitys.AdditionalInformation;

public interface AdditionalInformationInterface {

    AdditionalInformation createAdditionalInformation(int countExercise, int distance, String sportsNutrition);
}
