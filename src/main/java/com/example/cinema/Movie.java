package com.example.cinema;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Reprezinta un film
 * @author Buie Bogdan
 */
public class Movie {
    private String title;
    private String description;
    private String runtime;
    private String genre;
    private String ageRestrictions;
    private String language;

    /**
     * Constructorul clasei
     * @param title titlul filmului
     * @param description descrierea filmului
     * @param runtime durata filmului
     * @param genre genul filmului
     * @param ageRestrictions restrictia referitoare la varsta minima pentru vizionarea filmului
     * @param language limba in care este regizat filmul
     */
    public Movie(String title, String description, String runtime, String genre, String ageRestrictions, String language) {
        this.title = title;
        this.description = description;
        this.runtime = runtime;
        this.genre = genre;
        this.ageRestrictions = ageRestrictions;
        this.language = language;
    }

    /**
     * Preia titlul filmului
     * @return un string reprezentand titltul filmului
     */
    public String getTitle() {
        return title;
    }

    /**
     * Preia descrierea filmului
     * @return un string reprezentand descrierea filmului
     */
    public String getDescription() {
        return description;
    }
    /**
     * Preia durata filmului
     * @return un string reprezentand durata filmului
     */
    public String getRuntime() {
        return runtime;
    }
    /**
     * Preia genul filmului
     * @return un string reprezentand genul filmului
     */
    public String getGenre() {
        return genre;
    }
    /**
     * Preia restrictia de varsta a filmului
     * @return un string reprezentand restrictia de varsta a filmului
     */
    public String getAgeRestrictions() {
        return ageRestrictions;
    }
    /**
     * Preia limba filmului
     * @return un string reprezentand limba filmului
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Seteaza un titlu filmului
     * @param title titlul setat filmului
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAgeRestrictions(String ageRestrictions) {
        this.ageRestrictions = ageRestrictions;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
