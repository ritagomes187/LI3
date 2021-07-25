package Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;

import Extras.*;
import Main.*;
import View.*;

/**
 * Classe que possui Controlador
 */
public class ControllerJ{
    private IGestReviews model;
    private IViewJ view; 
    private Scanner sc;

    public ControllerJ(){
        this.model = null;
        this.view = null;
        this.sc = new Scanner(System.in);
    }
    /**
     * Setter do Model 
     * @param model            Modelo que queremos implementar
     */
    public void setModel(IGestReviews model){
        this.model = model;
    }

    /**
     * Setter da View 
     * @param view            View que queremos implementar
     */
    public void setView(IViewJ view){
        this.view = view;
    }

    /**
     * Funçao que atribui as funções as diferentes teclas
     */
    public void start(){
        view.getLoadButton().addActionListener(e -> loadFromFiles());
        view.getExitButton().addActionListener(e -> exitProgram());
        view.getQueryE1Button().addActionListener(e -> queryE1());
        view.getQueryE2Button().addActionListener(e -> queryE2());
        view.getQuery1Button().addActionListener(e -> query1());
        view.getQuery2Button().addActionListener(e -> query2());
        view.getQuery3Button().addActionListener(e -> query3());
        view.getQuery4Button().addActionListener(e -> query4());
        view.getQuery5Button().addActionListener(e -> query5());
        view.getQuery6Button().addActionListener(e -> query6());
        view.getQuery7Button().addActionListener(e -> query7());
        view.getQuery8Button().addActionListener(e -> query8());
        view.getQuery9Button().addActionListener(e -> query9());
        view.getQuery10Button().addActionListener(e -> query10());
        view.getLoadFromDiskButton().addActionListener(e -> loadFromDisk());
        view.getSaveToDiskButton().addActionListener(e -> saveToDisk());
    
        view.setQueryButtonsEnable(false);
        view.getSaveToDiskButton().setEnabled(false);
    }   

    /**
     * Controlador responsável pelo carregamento de ficheiros
     */
    public void loadFromFiles(){
        view.print("PATHs por omissão?[y/n]: ");
        
        boolean b = Character.toLowerCase(sc.nextLine().charAt(0)) != 'n';

        String users, bus, revs;

        if(!b){
            view.print("PATH dos users? ");
            users = sc.nextLine().split(" ")[0];

            view.print("PATH dos businesses? ");
            bus = sc.nextLine().split(" ")[0];

            view.print("PATH das reviews? ");
            revs = sc.nextLine().split(" ")[0];
        }
        else
            users = bus = revs = "";
        
        try{
            model.readUsersFile(users, b);
            model.readBusinessesFile(bus, b);
            model.readReviewFile(revs, b);
        }
        catch (IOException e){
            view.print("IO Exception");
        }
    }

    /**
     * Controlador responsável pela Query Estatística 1
     */
    public void queryE1(){
        
        String s = this.model.queryE1();
        
        view.print("Query Estatística 1: " + s+"\n");
    }


    /**
     * Controlador responsável pela Query Estatística 2
     */
    public void queryE2(){
        double elapsed_time;

        Crono.start();
        
        elapsed_time = Crono.stop();

        
        view.print("Tempo para executar a Query Estatistica 2: " + elapsed_time + " seg\n");
    }

    /**
     * Lista ordenada alfabeticamente com os identificadores dos negócios nunca avaliados e o seu respetivo total
     */
    public void query1(){
        double elapsed_time;

        Crono.start();
        Par<Set<String>, Integer> resultado = model.query1();
        elapsed_time = Crono.stop();
        view.showQuery(new ArrayList<>(resultado.getValue0()), 
                       "Negócios nunca avaliados e o seu respetivo total:" + resultado.getValue1(),
         1);       
        view.print("Tempo para executar a Query 1: " + elapsed_time + " seg\n");
    }

    /**
     * Dado um mês e um ano (válidos), determinar o número total global de reviews realizadas e o número total de users distintos que as realizaram
     */
    public void query2(){
        double elapsed_time;

        try {
            view.print("Ano que pretende estudar: ");
            String ano = "" + sc.nextInt(); 
            sc.nextLine();

            view.print("Mês que pretende estudar: ");
            String mes = "" + sc.nextInt();
            sc.nextLine();

            Crono.start();
            String resultado = model.query2(ano, mes);
            elapsed_time = Crono.stop();

            List<String> printable = new ArrayList<>(); printable.add(resultado);

            view.showQuery(printable,
                "Número total global de reviews realizadas num dado mês e" +
                "ano e o número total de users distintos que as realizaram:", 2); 
            view.print("Tempo para executar a Query 2: " + elapsed_time + " seg\n");
        } 
        catch (NumberFormatException e){
            view.printError("Valor inválido");
        }
    }

    /**
     * Dado um código de utilizador, determinar, para cada mês, quantas reviews fez, quantos negócios distintos avaliou e que nota média atribuiu;
     */
    public void query3(){
        double elapsed_time;

        view.print("Código do utilizador que pretende estudar: ");

        String utilizador = sc.nextLine().split(" ")[0];
        Crono.start();
        List<String> resultado = model.query3(utilizador);
        elapsed_time = Crono.stop();

        view.showQuery(resultado, "Reviews que um utilizador fez num dado mes "+
            "e quantos negócios distintos avaliou e que nota média atribuiu:", 3); 
        view.print("Tempo para executar a Query 3: " + elapsed_time + " seg\n");
    }

    /**
     *  Dado o código de um negócio, determinar, mês a mês, quantas vezes foi avaliado, por quantos users diferentes e a média de classificação
     */
    public void query4(){
        double elapsed_time;


        view.print("Código do negócio que pretende estudar: ");
        String bus = sc.nextLine().split(" ")[0];
        Crono.start();
        List<String> resultado = model.query4(bus);
        elapsed_time = Crono.stop();
        view.showQuery(resultado, "Vezes que um negócio foi avaliado, "+
        "por quantos users diferentes e a média de classificação:", 4); 
        view.print("Tempo para executar a Query 4: " + elapsed_time + " seg\n");
    }
    

    /**
     * Dado o código de um utilizador determinar a lista de nomes de negócios que mais
     * avaliou (e quantos), ordenada por ordem decrescente de quantidade e, para
     * quantidades iguais, por ordem alfabética dos negócios
     */
    public void query5(){
        double elapsed_time;

        System.out.println("Código do utilizador que pretende estudar: ");
        String utilizador = sc.nextLine().split(" ")[0];
        Crono.start();
        List<String> resultado = model.query5(utilizador);
        elapsed_time = Crono.stop();
        view.showQuery(resultado, "Negócios que um utilizador mais avaliou (e quantos):", 5);
        view.print("Tempo para executar a Query 5: " + elapsed_time + " seg\n");
    }
   

    /**
     * Determinar o conjunto dos X negócios mais avaliados (com mais reviews) em cada
     * ano, indicando o número total de distintos utilizadores que o avaliaram (X é um
     * inteiro dado pelo utilizador)
     */
    public void query6(){
        double elapsed_time;

        try {
            view.print("Quantos negocios pretende estudar: ");
            int x = sc.nextInt(); sc.nextLine();
            Crono.start();
            List<String> resultado = model.query6(x);
            elapsed_time = Crono.stop();
            view.showQuery(resultado, "Negócios mais avaliados (com mais reviews) em cada ano " +
            "e o número total de distintos utilizadores que o avaliaram:", 6);
            view.print("Tempo para executar a Query 6: " + elapsed_time + " seg\n");
        } 
        catch (NumberFormatException e){
            view.printError("Valor inválido");
        }
    }


    /**
     * Determinar, para cada cidade, a lista dos três mais famosos negócios em termos de número de reviews
     */
    public void query7(){
        double elapsed_time;

        Crono.start();
        List<String> resultado = model.query7();
        elapsed_time = Crono.stop();
        view.showQuery(resultado, "Lista dos três mais famosos negócios em termos de"+
        " número de reviews, para cada cidade:", 7);
        view.print("Tempo para executar a Query 7: " + elapsed_time + " seg\n");
    }


    /**
     * Determinar os códigos dos X utilizadores (sendo X dado pelo utilizador) que 
     * avaliaram mais negócios diferentes, indicando quantos, sendo o critério de
     * ordenação a ordem decrescente do número de negócios
     */
    public void query8(){
        double elapsed_time;

        try {
            view.print("Quantos negocios pretende estudar: ");
            int x = sc.nextInt(); sc.nextLine();
            Crono.start();
            List<String> resultado = model.query8(x);
            elapsed_time = Crono.stop();
            view.showQuery(resultado, "Utilizadores que avaliaram mais negócios diferentes,"+
            " indicando quantos:", 8);
            view.print("Tempo para executar a Query 8: " + elapsed_time + " seg\n");
        } catch (NumberFormatException e){
            view.printError("Valor inválido");
        }
    }
    
  

    /**
     * Dado o código de um negócio, determinar o conjunto dos X users que mais o avaliaram e, para cada um, qual o valor médio de classificação (ordenação cf. 5);
     */
    public void query9(){
        double elapsed_time;

        try {
            view.print("Codigo do negocio pretende estudar: ");
            String id = sc.nextLine().split(" ")[0];
            view.print("Quantos users pretende estudar: ");
            int x = sc.nextInt(); sc.nextLine();
            Crono.start();
            List<String> resultado = model.query9(id, x);
            elapsed_time = Crono.stop();
            view.showQuery(resultado, "Conjunto dos users que mais avaliaram um negócio e,"+
            " para cada um dos users apresenta o valor médio de classificação:", 9);
            view.print("Tempo para executar a Query 9: " + elapsed_time + " seg\n");
        } 
        catch (NumberFormatException e){
            view.printError("Valor inválido");
        }
    }

    /**
     * Determinar para cada estado, cidade a cidade, a média de classificação de cada negócio.
     */
    public void query10(){
        double elapsed_time;

        Crono.start();
        List<String> resultado = model.query10();
        elapsed_time = Crono.stop();
        view.showQuery(resultado, "Média de classificação de cada negócio para cada estado,"+
        " cidade a cidade:", 10);
        view.print("Tempo para executar a Query 10: " + elapsed_time + " seg\n");
    }


    private void loadFromDisk(){
        IGestReviews new_gestRev = null;
        String filename = JOptionPane.showInputDialog("Nome do ficheiro de onde quer ler (*.dat)");

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename + ".dat"));
            new_gestRev = (IGestReviews) in.readObject();
            this.model = new_gestRev;
            view.setQueryButtonsEnable(true);
            view.setLoadedStatus(true);
            view.print("Model carregado com sucesso");
            in.close();
        } 
        catch (IOException | ClassNotFoundException e) {
            if(e instanceof IOException)
                view.printError("Impossível carregar ficheiro");
            else
                e.printStackTrace();
        }
    }

    private void saveToDisk(){
        String filename = JOptionPane.showInputDialog("Nome do ficheiro onde quer guardar (*.dat)");

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename + ".dat"));
            out.writeObject(this.model);
            view.print("Model guardado com sucesso");
            out.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Controlador responsável por sair do programa
     */
    private void exitProgram(){
        System.exit(0);
    }
}