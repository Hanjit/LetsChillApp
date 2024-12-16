package com.example.letschill.models;

import java.io.Serializable;
import java.util.ArrayList;

public class MoviesData implements Serializable {
    private String Poster, Title, Time, Trailer, Description, Year, Age;
    private float Imdb;
    private ArrayList<String> Genre;

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public float getImdb() {
        return Imdb;
    }

    public void setImdb(float imdb) {
        Imdb = imdb;
    }

    public MoviesData() {
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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

    public String getTrailer() {
        return Trailer;
    }

    public void setTrailer(String trailer) {
        Trailer = trailer;
    }

    public ArrayList<String> getGenre() {
        return Genre;
    }

    public void setGenre(ArrayList<String> genre) {
        Genre = genre;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

}
