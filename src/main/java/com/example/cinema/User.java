package com.example.cinema;

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
    public User(String email, String password){
        this.email = email;
        this.password = password;
        this.isAdmin = 0;
    }
    public String doLogin(){
        // functie care comunica cu clasa Database si apoi chiar cu baza de date
        DataBase DB = new DataBase();
        DB.connectToDB();
        String message = DB.login(email,password);
        return message;
    }
    public String doRegister(){
        DataBase DB = new DataBase();
        DB.connectToDB();
        String message = DB.register(email,password);
        return message;
    }
}
