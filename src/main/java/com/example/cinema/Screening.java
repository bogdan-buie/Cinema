package com.example.cinema;

public class Screening {
    private int id;
    private String dateTime;    // YYYY-MM-DD hh:mm:ss
    private String date;        // DD-MM-YYYY
    private String time;        // hh:mm:ss
    private int id_film;

    private int id_sala;

    private String film;        // numele filmului

    public Screening(int id, String dateTime, int id_film, int id_sala) {
        this.id = id;
        this.dateTime = dateTime;
        this.id_film = id_film;
        this.id_sala = id_sala;
    }

    public Screening(String date, String time, String film) {
        this.date = date;
        this.time = time;
        this.film = film;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public int getId() {
        return id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getFilm() {
        return film;
    }
    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }
}
