package com.example.cinema;

import javax.net.ssl.SSLException;
import java.sql.*;

public class DataBase {

    final String DB_URL = "jdbc:mysql://localhost/proiect_cinema?serverTimezone=UTC";
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
    public boolean checkUser(String email, String password){
        // SCOASA DIN UZ
        // verifica daca un user se afla in baza de date
        try{
            String sql = "SELECT * FROM users WHERE email = ? and password = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1,email);
            prepare.setString(2,password);
            result = prepare.executeQuery();
            if(result.next())
                return true;       // user prezent in DB

        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;           // user inexistent
    }
    public String login(String email, String password){
        // functie care verifica daca un user/admin se afla in baza de date
        try{
            String sql = "{CALL login(?,?)}";
            cstmt = connect.prepareCall(sql);

            cstmt.setString(1,email);
            cstmt.setString(2,password);

            cstmt.execute();

            result = cstmt.getResultSet();
            String outputMessage = "" ;
            while(result.next()){
                outputMessage = result.getString(1);
            }
            cstmt.close();
            return outputMessage;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return "Eroare DB login";
    }
    public String register(String email, String password){
        try{
            String sql = "{CALL inregistrare(?,?)}";
            cstmt = connect.prepareCall(sql);

            cstmt.setString(1,email);
            cstmt.setString(2,password);
            cstmt.execute();
            result = cstmt.getResultSet();
            String outputMessage = "" ;
            while(result.next()){
                outputMessage = result.getString(1);
            }
            cstmt.close();
            return outputMessage;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return "Eroare DB register";
    }
    public String getDB_URL() {
        return DB_URL;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
}
