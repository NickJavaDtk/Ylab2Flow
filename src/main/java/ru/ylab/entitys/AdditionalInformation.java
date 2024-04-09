package ru.ylab.entitys;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalInformation {
    private int countExercise;
    private int distance;
    private String sportsNutrition;

    public AdditionalInformation(int countExercise, int distance) {
        this.countExercise = countExercise;
        this.distance = distance;
    }

    public AdditionalInformation(int countExercise, String sportsNutrition) {
        this.countExercise = countExercise;
        this.sportsNutrition = sportsNutrition;
    }

    public AdditionalInformation(int countExercise) {
        this.countExercise = countExercise;
    }


}
