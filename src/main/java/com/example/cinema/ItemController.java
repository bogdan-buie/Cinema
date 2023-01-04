package com.example.cinema;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


import java.util.ArrayList;
import java.util.Objects;

public class ItemController{
    @FXML
    private Label classification;

    @FXML
    private Label genre;

    @FXML
    private ImageView img;

    @FXML
    private Label title;

    @FXML
    private void click(MouseEvent mouseEvent){
        myListener.onClickListener(movieView);
    }

    private MovieView movieView;
    private MyListener myListener;

    public void setData(MovieView movieView, MyListener myListener){
        this.movieView = movieView;
        this.myListener = myListener;
        title.setText(movieView.getTitlu());
        genre.setText(movieView.getGen());
        classification.setText(movieView.getClasificare());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(movieView.getImgsrc())));
        img.setImage(image);
    }


}
