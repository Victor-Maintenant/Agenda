/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

import agenda.RepetitiveEvent;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author becke
 */
public class main {
    public static void main(String[] args) {
        // November 1st, 2020
    LocalDate nov_1_2020 = LocalDate.of(2020, 11, 1);

    // November 1st, 2020, 22:30
    LocalDateTime nov_1__2020_22_30 = LocalDateTime.of(2020, 11, 1, 22, 30);

    // 120 minutes
    Duration min_120 = Duration.ofMinutes(120);

    // A daily repetitive event, never ending
    // November 1st, 2020, 22:30, 120 minutes
    RepetitiveEvent neverEnding = new RepetitiveEvent("Never Ending", nov_1__2020_22_30, min_120, ChronoUnit.DAYS);
    
    neverEnding.addException(nov_1_2020.plus(2, ChronoUnit.DAYS)); // ne se produit pas à J+2
    neverEnding.addException(nov_1_2020.plus(4, ChronoUnit.DAYS)); // ne se produit pas à J+4
    System.out.println(neverEnding.isInDay(nov_1_2020.plus(1, ChronoUnit.DAYS)));
    System.out.println(neverEnding.isInDay(nov_1_2020.plus(2, ChronoUnit.DAYS)));
    System.out.println(neverEnding.isInDay(nov_1_2020.plus(3, ChronoUnit.DAYS)));
    System.out.println(neverEnding.isInDay(nov_1_2020.plus(4, ChronoUnit.DAYS)));
    }
}
