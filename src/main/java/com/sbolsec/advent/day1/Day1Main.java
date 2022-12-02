package com.sbolsec.advent.day1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Day1Main {

    public static void main(String[] args) {
        final Day1Main app = new Day1Main();

        final List<Elf> elves = app.loadElves();

        final Optional<Elf> optionalElfWithMostCalories = app.getElfWithMostCalories(elves);

        optionalElfWithMostCalories.ifPresent(System.out::println);
        optionalElfWithMostCalories.ifPresent(elf -> System.out.println("Total calories: " + elf.getTotalCalories()));
    }

    public Optional<Elf> getElfWithMostCalories(List<Elf> elves) {
        return elves.stream()
                .sorted(Comparator.comparing(Elf::getTotalCalories).reversed())
                .findFirst();
    }

    public List<Elf> loadElves() {
        final List<Elf> elves = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("day1-task1.txt")))) {
            int id = 1;
            String line = null;
            List<Food> foodList = new ArrayList<>();

            while ((line = bufferedReader.readLine()) != null) {
                if (line.isBlank()) {
                    if (foodList.size() == 0) {
                        continue;
                    }

                    final Elf elf = new Elf(id, foodList);
                    elves.add(elf);
                    foodList = new ArrayList<>();
                    id++;
                    continue;
                }

                final Food food = new Food(Integer.parseInt(line));
                foodList.add(food);
            }

            if (!foodList.isEmpty()) {
                elves.add(new Elf(id, foodList));
            }
        } catch (Exception ignored) {
            System.err.println(ignored.getMessage());
        }

        return elves;
    }

}
