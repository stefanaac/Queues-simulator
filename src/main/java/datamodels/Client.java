package datamodels;

import java.util.Comparator;

//clasa client defineste un client prin atributul clientId si defineste informatiile de care avem nevoie sa le
//stim despre un client: tArrival(timpul la care clientul a ajuns la servire) si tService(durata in care a fost clientul servit)
public class Client {
    private int clientId;
    private int tArrival;
    private int tService;

    //CONSTRUCTORI
    public Client() {
    }
    public Client(int clientId, int tArrival, int tService) {
        this.clientId = clientId;
        this.tArrival = tArrival;
        this.tService = tService;
    }

    //getters si setters pentru a accesa atributele clasei care sunt private
    public int getID() {
        return clientId;
    }

    public void setID(int ID) {
        this.clientId = ID;
    }

    public int gettArrival() {
        return tArrival;
    }

    public void settArrival(int tArrival) {
        this.tArrival = tArrival;
    }

    public int gettService() {
        return tService;
    }

    public void settService(int tService) {
        this.tService = tService;
    }



/*
    //metoda toString suprascrisa pentru afisarea unui client --am folosit-o pentru testare
    @Override
    public String toString() {
        return "Client(" +
                "clientId=" + clientId +
                ", tArrival=" + tArrival +
                ", tService=" + tService +
                ')';
    }*/
}
