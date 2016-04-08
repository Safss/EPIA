package com.example.IA;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcos on 06/04/2016.
 */
public class Vertice {

    private int x;
    private int y;
    private int id;
    private int demanda;
    private int distancia;

    public Vertice (int id,int distancia){
        this.distancia = distancia;
        this.id = id;
    }

    public Vertice (int x, int y, int id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getx(){
        return this.x;
    }
    public void setx(int x){
        this.x = x;
    }

    public int gety(){
        return this.y;
    }
    public void sety(int y){
        this.y = y;
    }

    public int getid(){
        return this.id;
    }
    public void setid(int id){
        this.id = id;
    }

    public int getdemanda(){
        return this.demanda;
    }
    public void setdemanda(int demanda){
        this.demanda = demanda;
    }

    public int getdistancia(){
        return this.distancia;
    }
    public void setdistancia(int distancia){
        this.distancia = distancia;
    }

    public int DistanceTo(Vertice outro) {

        int dx = outro.getx() - this.x;
        int dy = outro.gety() - this.y;
        double Distblablu = Math.sqrt(dx * dx + dy * dy);
        return  (int) Math.round(Distblablu);
    }
}
