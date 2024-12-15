package com.example.letschill.models;

import java.io.Serializable;
import java.util.ArrayList;

public class MoviesData implements Serializable {
    private String Poster, Title, Time, Trailer;
//    private int Year, Imdb;
    private ArrayList<String> Genre;

    public MoviesData() {
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

//    public int getYear() {
//        return Year;
//    }
//
//    public void setYear(int year) {
//        Year = year;
//    }

    public String getTrailer() {
        return Trailer;
    }

    public void setTrailer(String trailer) {
        Trailer = trailer;
    }

//    public int getImdb() {
//        return Imdb;
//    }
//
//    public void setImdb(int imdb) {
//        Imdb = imdb;
//    }

    public ArrayList<String> getGenre() {
        return Genre;
    }

    public void setGenre(ArrayList<String> genre) {
        Genre = genre;
    }
}
