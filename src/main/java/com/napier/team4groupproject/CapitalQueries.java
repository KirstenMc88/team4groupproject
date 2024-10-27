package com.napier.team4groupproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
public class CapitalQueries {

    public static void allInWorld() {

        System.out.println("All Capital Cities in World");
        LocalDate today = LocalDate.now();
        System.out.println("Now is: " + today);
    }
}
