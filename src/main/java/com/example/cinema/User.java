package com.example.cinema;
/**
 * @author Buie Bogdan
 * Reprezinta un user al aplicatiei fie admin, fie user obisnuit
 */
public class User {
    private String email;
    private String password;
    /*
    isAdmin = 0 => user obisnuit
    isAdmin = 1 => user de tip admin
     */
    private int isAdmin;

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public int getIsAdmin() {
        return isAdmin;
    }
    public void setIsAdmin(int type) {
        this.isAdmin = type;
    }

    /**
     * Constructorul clasei
     * @param email emaiulul user-ului
     * @param password parola useru-ului
     */
    public User(String email, String password){
        this.email = email;
        this.password = password;
        this.isAdmin = 0;
    }

    /**
     * Metoda care comunica cu baza de date prin intermediul unei metode
     * din clasa DataBase.
     * @return message mesajul returnat de metoda clasei DataBase referitor la logare
     */
    public String doLogin(){
        DataBase DB = new DataBase();
        DB.connectToDB();
        String message = DB.login(email,password);
        return message;
    }
    /**
     * Metoda care comunica cu baza de date prin intermediul unei metode
     * din clasa DataBase
     * @return mesajul returnat de metoda clasei Database referitor la crearea unui nou user
     */
    public String doRegister(){
        DataBase DB = new DataBase();
        DB.connectToDB();
        String message = DB.register(email,password);
        return message;
    }
}
