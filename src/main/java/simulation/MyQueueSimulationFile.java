package simulation;



import java.io.*;
import java.util.ArrayList;
import java.util.List;
import datamodels.Client;
import datamodels.DataIn;
import datamodels.Queue;


public class MyQueueSimulationFile implements Runnable {
    public int currentTime = 0;
    public Thread thread;
    public int avgWaitingTime;
    public int avgClients=0;
    public int sem = 1;


    public void addTask(List<Queue> queues, Client c, DataIn comp) throws InterruptedException {
        int q = 0;
        q=comp.findShortestQueue();
        queues.get(q).addClientToList(c);
    }

    public void printResult(List<Queue> queues,int nrOfQueues,int time,ArrayList<Client> waitingClients,int nrOfClients,FileWriter myWriter) throws IOException {
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

    }

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
    public void printAverageTime(DataIn c, FileWriter myWriter) throws IOException {
        for(int i = 0; i <0; i++) {
            if( c.getQueues().get(i).nrOfClients != 0 ) {
                sem = 0;}}
        if( sem == 1) {
            double raport = (double)avgWaitingTime/avgClients;
            myWriter.write("\n Average Waiting Time: " + Double.toString(raport));
            System.out.println("Average waiting time:"+Double.toString(raport));
        }
    }
    public void run() {
        try {
            File file = new File("C:\\\\Users\\\\Stefana\\\\IdeaProjects\\\\PT2021_30221_Chelemen_Stefana_Assignment_2\\src\\test3Out.txt");
            // creates the file
            file.createNewFile();
            FileWriter myWriter = new FileWriter(file);
            DataIn input = new DataIn();
            while ( currentTime <= input.getSimulationTime() ) {
                try {
                    if ( (input.getWaitingClients().size() != 0) && (input.getWaitingClients().get(0).gettArrival() == currentTime)) {
                        while (input.getWaitingClients().get(0).gettArrival() == currentTime ) {
                            addTask(input.queues, input.waitingClients.get(0),input);
                            input.getWaitingClients().remove(0);
                            if(input.getWaitingClients().size() == 0) break;
                        }}
                    Thread.sleep(1000);
                    printResult(input.getQueues(),input.getNrOfQueues(),currentTime,input.getWaitingClients(),input.getNrOfClients(),myWriter);
                    currentTime++;
                    calcAverageTime(input,currentTime);
                }catch(InterruptedException e) {
                }
            }
            printAverageTime(input,myWriter);
            myWriter.close();
        } catch (FileNotFoundException e1) {} catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
