package ru.ylab.service;

import ru.ylab.entitys.AdditionalInformation;

public interface AdditionalInformationInterface {


   boolean checkCountExercise(int countExercise);
   boolean checkCountDistance(int distance);
   boolean checkCountSportNutrition(String sportsNutrition);
}
