package com.example.demo;

import java.sql.*;
import java.util.ArrayList;

public class MovieModel {
    private int id;
    private String titlu;
    private String descriere;
    private int durata;
    private String gen;
    private String clasificare;
    private String limbaDublare;
    private Connection connect;
    final String DB_URL = "jdbc:mysql://localhost:3306/world?" + "autoReconnect=true&useSSL=false";
    final String USERNAME = "root";
    final String PASSWORD = "";

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getClasificare() {
        return clasificare;
    }

    public void setClasificare(String clasificare) {
        this.clasificare = clasificare;
    }

    public String getDescriere() { 
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public void setLimbaDublare(String limbaDublare) {
        this.limbaDublare = limbaDublare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLimbaDublare() {
        return limbaDublare;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public void connectToDB(){
        // functie care conecteaza la baza de date
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connect = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public ArrayList<MovieModel> Movie() {
        try {
            connect = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            CallableStatement cstmt = connect.prepareCall("SELECT * FROM film");

            boolean existaRezultat = cstmt.execute();

            ArrayList<MovieModel> listaFilme = new ArrayList<>();

            if (existaRezultat) {

                try (ResultSet resultSet = cstmt.getResultSet()) {

                    while (resultSet.next()) {
                        String titlu = resultSet.getString("titlu");
                        String durata = resultSet.getString("durata");
                        String descriere = resultSet.getString("descriere");
                        String clasificare = resultSet.getString("clasificare");
                        String limbaDublare = resultSet.getString("limba_dublare");
                        String gen = resultSet.getString("gen");
                        String id = resultSet.getString("id");
                        MovieModel film = new MovieModel();
                        film.setClasificare(clasificare);
                        film.setGen(gen);
                        film.setTitlu(titlu);
                        film.setDescriere(descriere);
                        film.setLimbaDublare(limbaDublare);
                        film.setDurata(Integer.parseInt(durata));
                        film.setId(Integer.parseInt(id));
                        listaFilme.add(film);
                        // System.out.println(titlu + " " + durata + "minute " + "Data lansare:" +  " " + "Clasificare:" + clasificare );

                        // System.out.println(titluri);
                    }
                    return listaFilme;
                }
            }


        } catch (Exception exception) {
            ExceptionHandler.handleException(exception);

        }
        return null;
    }

    public ArrayList<String> data(String id) {
        try {

            connect = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            CallableStatement cstmt = connect.prepareCall("CALL ora_rulare(?)");
            cstmt.setString(1, id);
            boolean existaRezultat = cstmt.execute();

            ArrayList<String> data = new ArrayList<>();

            if (existaRezultat) {

                try (ResultSet resultSet = cstmt.getResultSet()) {
                    while (resultSet.next()) {
                        data.add(resultSet.getString("data"));

                    }
                    return data;
                }

            }
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
        return null;
    }

    public ArrayList<String> ora(String id) {
        try {
            Connection conectare = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            CallableStatement cstmt = conectare.prepareCall("CALL ora_rulare(?)");
            cstmt.setString(1, id);
            boolean existaRezultat = cstmt.execute();

            ArrayList<String> ora = new ArrayList<>();
            if (existaRezultat) {

                try (ResultSet resultSet = cstmt.getResultSet()) {
                    while (resultSet.next()) {

                        ora.add(resultSet.getString("ora"));
                    }

                    return ora;

                }

            }
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
        return null;
    }
}


