package com.example.IA;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;


/**
 * Created by Marcos on 06/04/2016.
 */
public class main {

    public static double tempoAnnealing = 0;
    public static int Nrocaminhao = 0;
    public static int capacidade = 0;
    public static int deposito = 0;

    public static ArrayList Leitura(){
        ArrayList<Vertice> vetices = new ArrayList<Vertice>();
        Scanner ler = new Scanner(System.in);
        System.out.printf("Informe o nome de arquivo texto:\n");
        String nome = ler.nextLine();
        int contador = 1;
        try {
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine(); //le a primeira linha
            String noca = linha.substring(14,15);
            Nrocaminhao = Integer.parseInt(noca);
            String novt = linha.substring(10,12);
            int NoVertices = Integer.parseInt(novt);


            while (linha != null) {
             //   System.out.printf("%s\n", linha);
                linha = lerArq.readLine();
                contador++;
                if(contador == 6){
                    String capa = linha.substring(11,14);
                    capacidade = Integer.parseInt(capa);

                }
                else if(contador >= 8 && contador < (8 + NoVertices)){
                   //trata leitura sem espaço no começo. VER SE È PRECISO TRATAR O ESPAÇO!!!
                    String[] t = linha.split(" ", 3);
                    String vtcx = t[1];
                    String vtcy = t[2];
                    String idvt = t[0];
                    int valorx = Integer.parseInt(vtcx);
                    int valory = Integer.parseInt(vtcy);
                    int valorid = Integer.parseInt(idvt);
                    Vertice adicionando = new Vertice(valorx,valory,valorid);
                    vetices.add(adicionando);
                    //END Pega X e Y e ID de Vertices
                }
                else if(contador > (8 + NoVertices) && contador <= (8 + (NoVertices*2))){
                   // Pega demanda dos Vertices
                    String[] v = linha.split(" ", 2);
                    String id2 = v[0];
                    String dmd = v[1];
             //       System.out.println(Arrays.toString(v));
                    int valorid2 = Integer.parseInt(id2);
                    int valordmd = Integer.parseInt(dmd);
                    Vertice temp = vetices.get(valorid2 - 1);
                    temp.setdemanda(valordmd);
                    vetices.set(valorid2 - 1,temp);
                    //END pega demanda dos vertices
                }
                else if(contador == (10 + (NoVertices*2))){
                    deposito = Integer.parseInt(linha);
                }
            }
            arq.close();
        }

        catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    return vetices;
    }

    public static void escritaSaida(ArrayList<Rota> Saida) {

        try {
            String path = "C:\\Users\\Marcos\\Documents\\Marquinhos\\EPIA\\EPIA\\escritateste.txt";
            File file = new File(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            String saidaFinal = "";
            //COMEÇO DO ALGORITMO DE FORMATAÇÃO DE ESCRITA DA SAIDA
            for (int i = 0; i<Saida.size(); i++){
                saidaFinal = "Rota #" + Saida.get(i).getId() + " 0 ";
                for(int j = 0; j < Saida.get(j).getNosPercorridos().size();  j++) {
                    saidaFinal += Saida.get(i).getNosPercorridos().get(j).getid() + " ";
                }
                saidaFinal += "0 custo: -1 demanda atendida: " + Saida.get(i).calculaDemanda();
                writer.write(saidaFinal);
                writer.newLine();
            }
            //FIM DO ALGORITMO DE FORMATAÇÃO DE ESCRITA DA SAIDA

            //custo só pode ser obtido em tempo de execução do algoritmo, por isso não conseguimos testar a soma...
            writer.write("Custo: -1");
            writer.newLine();
            writer.write("Tempo: " + tempoAnnealing);
            writer.flush();
            //Fechando conexão e escrita do arquivo.
            writer.close();
        }
        catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }


    //Método para expandir os vizinhos, sem mt segredo.
    //Calcula a distancia do nó atual para todos os seus vizinhos.
    public static ArrayList<Vertice> ExpandirVizinho (Vertice Atual, ArrayList<Vertice> Grafo){

        ArrayList<Vertice> Vizinhos = new ArrayList<Vertice>();

        for (int i = 0; i<Grafo.size(); i++){
            Vertice Vizinho = Grafo.get(i);
            int dist = Atual.DistanceTo(Vizinho);
            Vizinho.setdistancia(dist);
            Vizinhos.add(Vizinho);
        }
        return Vizinhos;
    }

    //esse é o main, prazer.
    public static void main(String[] args) {

        double begin = System.currentTimeMillis();


        //TESTE FINAL LEITURA E ESCRITA
        ArrayList<Rota> rotasFinais = new ArrayList<Rota>();
        ArrayList<Vertice> temp = new ArrayList<Vertice>();

        ArrayList<Vertice> Mapa = Leitura();
        temp.add(Mapa.get(1));
        temp.add(Mapa.get(2));
        temp.add(Mapa.get(3));
        temp.add(Mapa.get(4));

        for (int i = 1; i <= Nrocaminhao; i++) {
            Rota nova = new Rota(i, temp);
            rotasFinais.add(nova);
        }

        double end = System.currentTimeMillis();
        tempoAnnealing = (end - begin);
        escritaSaida(rotasFinais);
        //FIM DO TESTE FINAL DE LEITURA E ESCRITA #FUNFA
    }
}

