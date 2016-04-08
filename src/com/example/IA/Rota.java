package com.example.IA;

import java.util.ArrayList;

/**
 * Created by Marcos on 06/04/2016.
 */
public class Rota {

    private int id;
    private ArrayList<Vertice> nosPercorridos;



    public Rota (int id, ArrayList<Vertice> nosPercorridos){
        this.id = id;
        this.nosPercorridos = nosPercorridos;
    }

    //ID de cada rota, derp.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    //cada nó percorrido em uma rota é salvo nesse ArrayList de vértices.
    public ArrayList<Vertice> getNosPercorridos() {
        return nosPercorridos;
    }

    public void setNosPercorridos(ArrayList<Vertice> nosPercorridos) {
        this.nosPercorridos = nosPercorridos;
    }


    //Calcula a demanda por rota.
    public int calculaDemanda(){
        int soma = 0;
        for(int i = 0; i<this.nosPercorridos.size();i++){
            soma += nosPercorridos.get(i).getdemanda();
        }
        return soma;
    }

}
