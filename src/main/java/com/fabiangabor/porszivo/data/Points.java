package com.fabiangabor.porszivo.data;

import com.fabiangabor.porszivo.service.VacuumController;

import java.util.List;

public class Points {
    private int amount;

    public Points() {
    }

    public int getAmount() {
        return amount;
    }

    public void increasePoints(int amount) {
        this.amount += amount;
    }

    public void decreasePoints(int amount) {
        this.amount -= amount;
    }

    public static double calcAveragePoints(List<VacuumController> controllers) {
        double averagePoints = 0;

        for (VacuumController controller : controllers) {
            averagePoints += controller.getPoints();
        }

        return averagePoints / controllers.size();
    }
}
