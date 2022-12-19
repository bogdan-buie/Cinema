package com.example.demo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class database {
    final String DB_URL = "jdbc:mysql://localhost:3306/world?" + "autoReconnect=true&useSSL=false";
    final String USERNAME = "root";
    final String PASSWORD = "";
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private CallableStatement cstmt;
    public void connectToDB(){
        // functie care conecteaza la baza de date
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public ObservableList<String> FiltruGen(String gen) {
        try {
            Connection conectare = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            CallableStatement cstmt = conectare.prepareCall("CALL filtru_gen(?)");

            cstmt.setString(1, gen);

            boolean existaRezultat = cstmt.execute();

            ObservableList<String> titluri= FXCollections.observableArrayList();

            if (existaRezultat) {

                try (ResultSet resultSet = cstmt.getResultSet()) {

                    while (resultSet.next()) {
                        String titlu = resultSet.getString("titlu");
                        String durata = resultSet.getString("durata");
                        String descriere = resultSet.getString("descriere");
                        String clasificare = resultSet.getString("clasificare");

                       // System.out.println(titlu + " " + durata + "minute " + "Data lansare:" +  " " + "Clasificare:" + clasificare );
                        titluri.add(titlu);
                       // System.out.println(titluri);
                    }
                }
            }

            return titluri;
        } catch (Exception exception) {
            ExceptionHandler.handleException(exception);
        }

        return null;
    }

}

