package com.fabiangabor.porszivo.data;

import java.util.List;

import com.fabiangabor.porszivo.service.VacuumController;

public class Points {
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void increasePoints(int amount) {
        this.amount += amount;
    }

    public void decreasePoints(int amount) {
        this.amount -= amount;
    }

    public static double calcAveragePoints(final List<VacuumController> controllers) {
        double averagePoints = 0;

        for (VacuumController controller : controllers) {
            averagePoints += controller.getPoints();
        }

        return averagePoints / controllers.size();
    }
}
