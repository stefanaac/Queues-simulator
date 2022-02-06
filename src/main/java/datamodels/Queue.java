package datamodels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue  extends Thread{
    public List<Client> clients= Collections.synchronizedList(new ArrayList<Client>());//folosesc o lista sincrona pentru lucrul cu threaduri
    public AtomicInteger timeToWait;//am pus timpul de asteptare AtomicInteger pentru a putea fi folosit de mai multe threaduri,concurent
    public int nrOfClients;
    public int stop;
    public int timer;
    public Thread thread;

    //constructor pentru creearea unei noi cozi
    public Queue(int closingTime) {
        this.stop = closingTime;
        this.timeToWait = new AtomicInteger(0);
    }


    //metoda care incepe o noua coada si porneste threadul acesteia
    public void startNewQueue() {
        timer = 0;
        thread = new Thread(this);
        thread.start();
    }

    //metoda care adauga un client in lista de clienti
    public void addClientToList(Client c) {
        this.nrOfClients++;
        if(this.nrOfClients>1)
            c.settService(c.gettService()-1);
        this.clients.add(c);
        timeToWait.addAndGet(c.gettService());
    }


    //metoda care scoate clientul din lista
    public synchronized void removeClientFromList() throws InterruptedException {
        while ( clients.size() == 0) {
            wait();
        }

        this.clients.remove(0);
        this.nrOfClients--;
        notifyAll();
    }

    //porneste Thread-ul cozii
    public synchronized void run() {
        while((timer < stop) || clients.size() != 0) {
            try {
                Thread.sleep(1000);
                if(this.clients.size() != 0) {
                    Client c = this.clients.get(0);
                    int n = c.gettService();
                    for(int i = 0; i < n; i++) {
                        int n1 = c.gettService()-1;
                        c.settService(n1);
                        Thread.sleep(997);
                        timer++;
                        this.timeToWait.decrementAndGet();
                    }
                    this.removeClientFromList();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    //metoda care returneaza dimensiunea listei de clienti
    public int clientsListsSize() {
        return this.clients.size();
    }

    //metoda care returneaza coada de clienti
    public List<Client> getQueue() {
        return this.clients;
    }

}
