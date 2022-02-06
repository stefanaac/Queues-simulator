package simulation;

import datamodels.Client;
import datamodels.DataIn;
import datamodels.Queue;
import gui.Sim;
import gui.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//clasa in care se face simularea propriu-zisa
public class MyQueueSimulation implements Runnable {
    public int currentTime = 0;
    public Thread thread;
    public int avgWaitingTime;
    public int avgClients=0;
    public int sem = 1;

    //atribute pe care le folosesc la siimularea in interfata grafica
    public int nrCl;
    public int nrQ;
    public int timeS;
    public int minAvg;
    public int maxAvg;
    public int minServ;
    public int maxServ;

    //getteri si setteri pentru atributele care le iau din interfata grafica
    public void setNrCl(int nrCl) {
        this.nrCl = nrCl;
    }

    public void setNrQ(int nrQ) {
        this.nrQ = nrQ;
    }

    public void setTimeS(int timeS) {
        this.timeS = timeS;
    }

    public void setMinAvg(int minAvg) {
        this.minAvg = minAvg;
    }

    public void setMaxAvg(int maxAvg) {
        this.maxAvg = maxAvg;
    }

    public void setMinServ(int minServ) {
        this.minServ = minServ;
    }

    public void setMaxServ(int maxServ) {
        this.maxServ = maxServ;
    }

    //metoda care cauta coada minima si adauga clientul in ea
    public void addTask(List<Queue> queues, Client c, DataIn comp) throws InterruptedException {
        int q = 0;
        q=comp.findShortestQueue();
        queues.get(q).addClientToList(c);
    }
    //metoda care scrie rezultatele in interfata grafica
    public String printResultInGui(List<Queue> queues, int nrOfQueues, int time, ArrayList<Client> waitingClients, int nrOfClients)
    {
        String s="Time:"+time+"\n";
        System.out.println("Time:"+time+"\n");

        s=s+"Waiting clients: ";
        System.out.println("Waiting clients: ");
        for(Client c:waitingClients) {
            System.out.println("("+c.getID()+" "+c.gettArrival()+" "+c.gettService()+")");
            s=s+"("+c.getID()+" "+c.gettArrival()+" "+c.gettService()+")";
        }
        s=s+"\n";
        System.out.println("\n");
        for(int i=1;i<=nrOfQueues;i++) {
            s=s+"Queue "+i+":";
            System.out.println("Queue "+i+":");
            if(queues.get(i-1).clientsListsSize()==0) {
                s=s+"closed";
                System.out.println("closed");
            }
            else {
                for(Client c: queues.get(i-1).clients) {
                    int n=c.gettService()+1;
                    s=s+"("+c.getID()+" "+c.gettArrival()+" "+n+")";
                    System.out.println("("+c.getID()+" "+c.gettArrival()+" "+n+")");
                }
            }
            s=s+"\n\n";
            System.out.println("\n\n");
        }
        return s;
    }
/*
    //metoda care printeaza rezultatele simularii intr-un fisier txt
    public void printResultInFile(List<Queue> queues, int nrOfQueues, int time, ArrayList<Client> waitingClients, int nrOfClients, FileWriter myWriter) throws IOException {
        myWriter.write("Time:"+time+"\n");
        System.out.println("Time:"+time+"\n");
        myWriter.write("Waiting clients: ");
        System.out.println("Waiting clients: ");
        for(Client c:waitingClients) {
            myWriter.write("("+c.getID()+" "+c.gettArrival()+" "+c.gettService()+")");
            System.out.println("("+c.getID()+" "+c.gettArrival()+" "+c.gettService()+")");
        }
        myWriter.write("\n");
        System.out.println("\n");
        for(int i=1;i<=nrOfQueues;i++) {
            myWriter.write("Queue "+i+":");
            System.out.println("Queue "+i+":");
            if(queues.get(i-1).clientsListsSize()==0) {
                myWriter.write("closed");
                System.out.println("closed");
            }
            else {
                for(Client c: queues.get(i-1).clients) {
                    int n=c.gettService()+1;
                    myWriter.write("("+c.getID()+" "+c.gettArrival()+" "+n+")");
                    System.out.println("("+c.getID()+" "+c.gettArrival()+" "+n+")");
                }
            }
            myWriter.write("\n\n");
            System.out.println("\n\n");
        }
    }*/

    //metoda care calculeaza timpul mediu de asteptare
    public void calcAverageTime(DataIn c,int currentTime) {
        int waitingCurrentTime = 0;
        for (int i = 0; i < c.getQueues().size(); i++) {
            if ( c.getQueues().get(i).nrOfClients != 0){
                avgClients++;
                waitingCurrentTime += c.getQueues().get(i).timeToWait.get();
            }}
        avgWaitingTime =avgWaitingTime+ waitingCurrentTime;
        if( currentTime == c.getSimulationTime() ) {
            for(int i = 0; i < c.getNrOfQueues(); i++) {
                c.getQueues().get(i).timer = c.getSimulationTime();
            }
        }
    }
/*
    //metoda care printeaza timpul mediu de asteptare in fisier
    public void printAverageTimeInFile(DataIn c,FileWriter myWriter) throws IOException {
        for(int i = 0; i <0; i++) {
            if( c.getQueues().get(i).nrOfClients != 0 ) {
                sem = 0;}}
        if( sem == 1) {
            double raport = (double)avgWaitingTime/avgClients;
            myWriter.write("\n Average Waiting Time: " + Double.toString(raport));
            System.out.println("Average waiting time:"+Double.toString(raport));
        }
    }*/
    //metoda care printeaza timpul mediu de asteptare in GUI
    public String printAverageTimeInGUI(DataIn c) throws IOException {
        String s="";
        for(int i = 0; i <0; i++) {
            if( c.getQueues().get(i).nrOfClients != 0 ) {
                sem = 0;}}
        if( sem == 1) {
            double raport = (double)avgWaitingTime/avgClients;
            s=s+"\n Average Waiting Time: " + Double.toString(raport);
            System.out.println("Average waiting time:"+Double.toString(raport));
        }
        return s;
    }

    public void run() {
        String s = "";
        String s2 = "Average time: ";
        Sim gui = new Sim();
        try {
            // File file = new File("C:\\\\Users\\\\Stefana\\\\IdeaProjects\\\\PT2021_30221_Chelemen_Stefana_Assignment_2\\src\\test1Out.txt");
            // creates the file
            //file.createNewFile();
            // FileWriter myWriter = new FileWriter(file);

            DataIn input = new DataIn(nrCl, nrQ, timeS, minAvg, maxAvg, minServ, maxServ);
            while (currentTime <= input.getSimulationTime()) {
                try {
                    if ((input.getWaitingClients().size() != 0) && (input.getWaitingClients().get(0).gettArrival() == currentTime)) {
                        while (input.getWaitingClients().get(0).gettArrival() == currentTime) {
                            addTask(input.queues, input.waitingClients.get(0), input);
                            input.getWaitingClients().remove(0);
                            if (input.getWaitingClients().size() == 0) break;
                        }
                    }
                    Thread.sleep(1000);
                    //printResultInFile(input.getQueues(),input.getNrOfQueues(),currentTime,input.getWaitingClients(),input.getNrOfClients(),myWriter);
                    s = printResultInGui(input.getQueues(), input.getNrOfQueues(), currentTime, input.getWaitingClients(), input.getNrOfClients());
                    gui.patrat.setText(s);
                    currentTime++;
                    calcAverageTime(input, currentTime);
                } catch (InterruptedException e) {
                }
            }
            // printAverageTimeInFile(input,myWriter);
            s2 = printAverageTimeInGUI(input);
            gui.patrat.setText(s2);
            //myWriter.close();
        } catch (FileNotFoundException e1) {
        } catch (IOException e1) {
           e1.printStackTrace();
        }

    }
}
