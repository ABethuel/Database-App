package com.example.databaseapp.Model;

public class Drivers
{
    public int id;
    public String year, name, nationality, team;

    public Drivers(int id, String year, String name, String nationality, String team)
    {
        this.id = id;
        this.year = year;
        this.name = name;
        this.nationality = nationality;
        this.team = team;
    }

    public Drivers() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
