package com.example.cinema;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.net.ssl.SSLException;
import java.sql.*;
import java.util.DoubleSummaryStatistics;

/**
 * Clasa care comunica cu baza de date
 * @author Buie Bogdan
 * @author Vancea Bogdan
 */
public class DataBase {

    final String DB_URL = "jdbc:mysql://localhost/proiect_cinema?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASSWORD = "";
    private Connection connect;
    private PreparedStatement prepare;
    private PreparedStatement prepare2;
    private ResultSet result;
    private CallableStatement cstmt;
    private Statement statement;

    public void connectToDB() {
        // functie care conecteaza la baza de date
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifica daca uitlizatorul are cont
     * @param email un String, reprezentand email-ul utilizatorului
     * @param password un String, reprezentand parola utilizatoruli
     * @return un String, reprezentand mesajul venit din baza de date
     */
    public String login(String email, String password) {
        // functie care verifica daca un user/admin se afla in baza de date
        try {
            String sql = "{CALL login(?,?)}";
            cstmt = connect.prepareCall(sql);

            cstmt.setString(1, email);
            cstmt.setString(2, password);

            cstmt.execute();

            result = cstmt.getResultSet();
            String outputMessage = "";
            while (result.next()) {
                outputMessage = result.getString(1);
            }
            cstmt.close();
            return outputMessage;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Eroare DB login";
    }

    /**
     * Înregistrează un nou utilizator in baza de date
     * @param email un String, reprezentand emailul noului utilizator
     * @param password un String, reprezentand parola noului utilizator
     * @return un String, reprezentand measjul venit din baza de date
     */
    public String register(String email, String password) {
        try {
            String sql = "{CALL inregistrare(?,?)}";
            cstmt = connect.prepareCall(sql);

            cstmt.setString(1, email);
            cstmt.setString(2, password);
            cstmt.execute();
            result = cstmt.getResultSet();
            String outputMessage = "";
            while (result.next()) {
                outputMessage = result.getString(1);
            }
            cstmt.close();
            return outputMessage;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Eroare DB register";
    }

    /**
     * Preia lista de filme din baza de date
     * @return un obiect de tipul ObservableList, continand toate filmele furnizate de baza de date
     */
    public ObservableList<Movie> getListOfMoviesDB() {
        ObservableList<Movie> list = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM film";
            // sau prepareCall
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Movie movie;
            while (result.next()) {
                movie = new Movie(result.getString("titlu"),
                        result.getString("descriere"),
                        result.getString("durata"),
                        result.getString("gen"),
                        result.getString("clasificare"),
                        result.getString("limba_dublare")
                );
                list.add(movie);
            }
            // vezi daca trebuie prepare close
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Preia lista de ecranizari din baza de date
     * @return un obiect de tipul ObservableList, continand toate ecranizarile furnizate de baza de date
     */
    public ObservableList<Screening> getListOfScreeningDB() {
        ObservableList<Screening> list = FXCollections.observableArrayList();
        try {
            String sql = "SELECT ecranizare.id, ecranizare.data_rulare, film.titlu AS titlu, ecranizare.idsala FROM ecranizare RIGHT JOIN film ON ecranizare.idfilm = film.id;";
            // sau prepareCall
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Screening screening;
            while (result.next()) {
                screening = new Screening(result.getInt("id"),
                                          result.getString("data_rulare"),
                                          result.getString("titlu"),
                                          result.getInt("idsala")
                                         );
                System.out.println(result.getString("titlu"));
                list.add(screening);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Insereaza un film în baza de date
     * @param movie un obiect de tip Movie
     * @return un String, reprezentand measjul venit din baza de date
     */
    public int insertMovieDB(Movie movie) {
        int code = -1;
        /*
            code = 0  => filmul exista in baza de date
            code = 1  => filmul a fost introdus in BD cu succes
        */
        try {
            String sql1 = "SELECT titlu FROM film WHERE titlu = (?)";
            String sql2 = "INSERT INTO film (titlu, descriere, durata, gen, clasificare, limba_dublare) VALUES (?,?,?,?,?,?)";

            // se verifica daca nu cumva exista un film in BD cu acest nume
            prepare = connect.prepareStatement(sql1);
            prepare.setString(1, movie.getTitle());
            result = prepare.executeQuery();
            if (result.next()) {

                code = 0;
            } else {
                //se introduce filmul in BD
                prepare2 = connect.prepareStatement(sql2);
                prepare2.setString(1, movie.getTitle());
                prepare2.setString(2, movie.getDescription());
                prepare2.setString(3, movie.getRuntime());
                prepare2.setString(4, movie.getGenre());
                prepare2.setString(5, movie.getAgeRestrictions());
                prepare2.setString(6, movie.getLanguage());
                int nrRowAffected = prepare2.executeUpdate();
                if (nrRowAffected != 0) {
                    code = 1;   // filmul a fost introdus in BD cu succes
                }
                System.out.println(nrRowAffected);
                prepare2.close();// atentie aici. Nu inchidem ceva ce nu am initializat
            }
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * Sterge din baza de date filmul trimis ca parametru
     * @param movie un obiect de tip Movie
     * @return un String, reprezentand measjul venit din baza de date
     */
    public int deleteMovieBD(Movie movie) {
        int code = -1;
        /*
            code = -1 => erori
            code = 0  => cod de eroare liber
            code = 1  => filmul a fost sters din BD cu succes
        */
        try {
            String sql = "DELETE FROM film WHERE titlu = ?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, movie.getTitle());
            int nrRowAffected = prepare.executeUpdate();
            if (nrRowAffected != 0) {
                code = 1;
            }
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * Numara filmele din baza de date
     * @return un numar de tip intreg, reprezentand nr de filme sau o posibila eroare
     */
    public int countAvailableFilmsDB() {
        int code = -1;
        /*
            code = -1 => erori
            code = count = nr de filme
        */
        try {
            String sql = "SELECT COUNT(titlu) FROM film;";
            statement = connect.createStatement();
            result = statement.executeQuery(sql);
            int count;
            while (result.next()) {
                count = result.getInt(1);
                code = count;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * Face update in baza de date la filmul trimis ca parametru
     * @param movie un obiect de tip film
     * @return un nr de tip intreg, reprezentand un cod legat de status-ul acestei operatii
     */
    public int updateMovieDB(Movie movie) {
        int code = -1;
        /*
            code = -1 => erori
            code = 0  => ceva nu merge bine la update. Sunt afectate mai mult de un rand in BD
            code = 1  => update cu succes
        */
        try {
            String sql = "UPDATE film SET titlu = ?, descriere = ?, durata = ?, gen = ?, clasificare = ?, limba_dublare = ? WHERE titlu=?";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, movie.getTitle());
            prepare.setString(2, movie.getDescription());
            prepare.setString(3, movie.getRuntime());
            prepare.setString(4, movie.getGenre());
            prepare.setString(5, movie.getAgeRestrictions());
            prepare.setString(6, movie.getLanguage());
            prepare.setString(7, movie.getTitle());
            int nrRowAffected = prepare.executeUpdate();
            if (nrRowAffected == 1) {
                code = 1;
            } else {
                code = 0;
            }
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * Insereaza in baza de date locuri pentru o anumita sala
     * @param nrLocuri un numar intreg, reprezentand numarul de locuri
     * @param idSala un numar intreg, reprezentand numarul salii
     * @author Vancea Bogdan
     */
    public void InsertLoc(int nrLocuri, String idSala) {
        try {
            int i = 1;
            int nrRand = 1;
            while (i <= nrLocuri) {
                CallableStatement stm = connect.prepareCall("INSERT INTO loc (idsala,rand,numar_loc) VALUES (?,?,?);");
                if (i % 11 == 0) nrRand++;
                stm.setString(1, idSala);
                stm.setString(2, String.valueOf(nrRand));
                stm.setString(3, String.valueOf(i));
                stm.execute();
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Identifica id-ul din baza de date al filmului dat ca parametru
     * @param titlu un String, reprezentand numele filmului dat
     * @return un numar intreg reprezentand id-ul filmului
     */
    public String getIDFilm(String titlu) {
        String id = new String();
        try {
            //Connection conectare = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect_cinema?" + "user=root&serverTimezone=UTC");
            CallableStatement stm = connect.prepareCall("SELECT id FROM film WHERE titlu LIKE ? ");
            stm.setString(1, titlu);
            stm.execute();
            ResultSet resultSet = stm.getResultSet();
            while (resultSet.next()) {
                id = resultSet.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Insereaza o ecranizare in baza de date
     * @param data_rulare un String, reprezentand data de rulare al filmului dat
     * @param idFilm un numar intreg reprezentand, id-ul filmului selectat
     * @param idSala un numar intreg reprezentand, id-ul sali in care se tine sala
     */
    public void InsertEcranizare(String data_rulare, String idFilm, int idSala) {
        try {
            CallableStatement stm = connect.prepareCall("INSERT INTO ecranizare (data_rulare,idfilm,idsala) VALUES(?,?,?)");
            stm.setString(1, data_rulare);
            stm.setString(2, idFilm);
            stm.setString(3, String.valueOf(idSala));
            stm.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
