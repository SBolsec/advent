package com.sbolsec.advent.day1;

import lombok.Data;

import java.util.List;

@Data
public class Elf {

    private final Integer id;

    private final List<Food> foodList;

    public Integer getTotalCalories() {
        return foodList.stream()
                .mapToInt(Food::getCalories)
                .sum();
    }

}
