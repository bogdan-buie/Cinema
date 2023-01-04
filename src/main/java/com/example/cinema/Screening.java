package com.example.cinema;


/**
 * Reprezinta o ecranizare
 * @author Buie Bogdan
 */
public class Screening {
    private int id;
    private String dateTime;    // YYYY-MM-DD hh:mm:ss
    private String date;        // DD/MM/YYYY
    private String time;        // hh:mm:ss
    private int id_film;
    private int id_sala;
    private String film;        // numele filmului

    /**
     * Un constructorul al clasei
     * @param id un nr de tip intreg, reprezentand id-ul ecranizarii din baza de date
     * @param dateTime un String, reprezentand data si ora in formatul YYYY-MM-DD hh:mm:ss
     * @param id_film un nr de tip intreg, reprezentand id-ul fimului in baza de date
     * @param id_sala un nr de tip intreg, reprezentand id-ul salii din baza de date
     */
    public Screening(int id, String dateTime, int id_film, int id_sala) {
        this.id = id;
        this.dateTime = dateTime;
        this.id_film = id_film;
        this.id_sala = id_sala;
    }

    /**
     * Un constructorul al clasei
     * @param id un nr de tip intreg, reprezentand id-ul ecranizarii din baza de date
     * @param dateTime un String, reprezentand data si ora in formatul YYYY-MM-DD hh:mm:ss
     * @param film un String, reprezentand numele filmului
     * @param id_sala un nr de tip intreg, reprezentand id-ul salii din baza de date
     */
    public Screening(int id, String dateTime, String film, int id_sala) {
        this.id = id;
        this.dateTime = dateTime;
        this.id_sala = id_sala;
        this.film = film;
    }

    /**
     * Un constructorul al clasei
     * @param date un String, reprezentand data in formatul DD/MM/YYYY
     * @param time un String, reprezentand ora in formatul hh:mm:ss
     * @param film un String, reprezentand numele filmului
     */
    public Screening(String date, String time, String film) {
        this.date = date;
        this.time = time;
        this.film = film;
        toDateTime();
    }

    /**
     * Se seteaza campul dateTime(YYYY-MM-DD hh:mm:ss) format din date(DD/MM/YYYY)
     * si time(HH:MM:SS)
     */
    public void toDateTime() {
        String dateTimeAux = "";
        String[] result = this.date.split("/");
        String DD = result[0];
        String MM = result[1];
        String YYYY = result[2];
        dateTimeAux = dateTimeAux + YYYY + "-" + MM + "-" + DD;
        dateTimeAux = dateTimeAux + " " + this.time;
        setDateTime(dateTimeAux);
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Seteaza dateTime-ul
     * @param dateTime un String, reprezentand data si ora in formatul YYYY-MM-DD hh:mm:ss
     */
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
