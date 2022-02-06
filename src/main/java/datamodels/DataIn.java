package datamodels;

import gui.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataIn {
    private  int nrOfClients;
    private  int nrOfQueues;
    private  int simulationTime;
    private  int minimumArrivalTime;
    private  int maximumArrivalTime;
    private  int minimumWaitingTime;
    private  int maximumWaitingTime;
    public int emptyQueueTime;
    public ArrayList<Client> waitingClients=new ArrayList<Client>();
    public List<Queue> queues= Collections.synchronizedList(new ArrayList<Queue>());

    //CONSTRUCTOR pt citire date de intrare din fisier text
    public DataIn() throws FileNotFoundException {
        File file = new File("C:\\\\Users\\\\Stefana\\\\IdeaProjects\\\\PT2021_30221_Chelemen_Stefana_Assignment_2\\src\\test3In.txt");
        Scanner myReader= new Scanner(file);
        this.nrOfClients=myReader.nextInt();
        this.nrOfQueues=myReader.nextInt();
        this.simulationTime=myReader.nextInt();
        String time=myReader.next();
        String[] stringArrival=time.split(",");
        System.out.println(time);
        this.minimumArrivalTime=Integer.parseInt(stringArrival[0]);
        this.maximumArrivalTime=Integer.parseInt(stringArrival[1]);
        time=myReader.next();
        String[] stringWaiting=time.split(",");
        this.minimumWaitingTime=Integer.parseInt(stringWaiting[0]);
        this.maximumWaitingTime=Integer.parseInt(stringWaiting[1]);
        myReader.close();
        this.waitingClients=generateClients();
        initQueues(queues,nrOfQueues,simulationTime);
    }
    //construcor pt citire datede intrare  intrare din GUI
    public DataIn(int nrC,int nrQ,int simT,int minA,int maxA,int minW,int maxW){
        this.nrOfClients=nrC;
        this.nrOfQueues=nrQ;
        this.simulationTime=simT;
        this.minimumArrivalTime=minA;
        this.maximumArrivalTime=maxA;
        this.minimumWaitingTime=minW;
        this.maximumWaitingTime=maxW;
        this.waitingClients=generateClients();
        initQueues(queues,nrOfQueues,simulationTime);
    }
    //metoda care genereaza random clienti
    public ArrayList<Client> generateClients() {
        for(int i=0;i<nrOfClients;i++) {
            Client c=new Client();
            c.setID(i+1);
            //generez timpul de sosire a unui client random si sa apartina intervalului[0,timpul maxim de sosire-timp minim de sosire+1],
            //la care adauga timpul minim de sosire(ca sa fie intodeauna mai mare decat timpul minim de sosire)
            Random r=new Random();
            int tA=this.minimumArrivalTime+r.nextInt(this.maximumArrivalTime-this.minimumArrivalTime+1);
            c.settArrival(tA);
            //generez timpul de servire tot random, care apartine intervalului[0,timp maxim de asteptare-timp minim de asteptare+1],
            //la care adaug timpul minim  de asteptare(ca sa fie mai mare decat timpul minim de asteptare)
            Random r1=new Random();
            int n1=this.minimumWaitingTime+r1.nextInt(this.maximumWaitingTime-this.minimumWaitingTime+1);
            c.settService(n1);
            //adaug clientul generat la lista de clienti care asteapta la coada
            this.waitingClients.add(c);
        }
        //sortez clientii in functie de timpul de sosire si asteptare
        Collections.sort(this.waitingClients,new SortingByArrival());
        Collections.sort(this.waitingClients,new SortingByWaiting());
        return waitingClients;
    }


    //metoda folosita pentru a adauga o coada in lista de cozi si care porneste thread-ul acelei cozi
    public void initQueues(List<Queue> queues,int nrOfQ,int time) {
        for(int i = 0; i < nrOfQ; i++) {
            Queue q = new Queue(time);
            this.queues.add(q);
        }
        for(int i = 0; i < nrOfQ; i++) {
            this.queues.get(i).startNewQueue();
        }
    }


    //metoda care cauta in lista de cozi coada cea mai scurta
    public synchronized int findShortestQueue()
    {
        int q=0;//retine numarul cozii
        int time1=0;
        int time2 = 0;

        int minim =this.queues.get(0).clientsListsSize();//initializez minimul cu prima coada din lista de cozi
        int minService=0; //initializez timpul minim pentru servire cu 0
        //mergem prin lista de cozi si daca gasim o coada goala o returnam ca si coada minima
        for(int j=0;j<this.nrOfQueues;j++)
        {
            if(this.queues.get(j).clientsListsSize()==0)
                return j;
        }

        for(int j = 0 ; j < this.queues.get(0).clients.size() ; j++)
        {
            minService = minService + this.queues.get(0).clients.get(j).gettService();
        }

        List<Client> minim2 = this.queues.get(0).clients;
        for(int i=1 ; i<this.nrOfQueues ; i++)
        {
            if(this.queues.get(i).clientsListsSize() < minim)
            {
                minim = this.queues.get(i).clientsListsSize();
                minim2 = this.queues.get(i).clients;
                for(int j = 0 ; j < this.queues.get(i).clients.size() ; j++)
                {
                    minService = minService + this.queues.get(i).clients.get(j).gettService();
                }
                q = i;
            }
            //daca mai multe cozi sunt minime, returnam coada cu timpul de astepatre cel mai mic
            if(this.queues.get(i).clientsListsSize() == minim)
            {
                time1=0;
                for(int j = 0 ; j < this.queues.get(i).clients.size() ; j++)
                {
                    time1 = time1 + this.queues.get(i).clients.get(j).gettService();
                }
                if(time1 < minService) {
                    minim = this.queues.get(i).clientsListsSize();
                    minService=time1;
                    q = i;
                }
            }
        }
        return q;
    }


    //getteri si setteri pentru accesarea atributelor private
    public int getNrOfClients() {
        return nrOfClients;
    }

    public void setNrOfClients(int nrOfClients) {
        this.nrOfClients = nrOfClients;
    }

    public int getNrOfQueues() {
        return nrOfQueues;
    }

    public void setNrOfQueues(int nrOfQueues) {
        this.nrOfQueues = nrOfQueues;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public void setSimulationTime(int simulationTime) {
        this.simulationTime = simulationTime;
    }

    public int getMinimumArrivalTime() {
        return minimumArrivalTime;
    }

    public void setMinimumArrivalTime(int minimumArrivalTime) {
        this.minimumArrivalTime = minimumArrivalTime;
    }

    public int getMaximumArrivalTime() {
        return maximumArrivalTime;
    }

    public void setMaximumArrivalTime(int maximumArrivalTime) {
        this.maximumArrivalTime = maximumArrivalTime;
    }

    public int getMinimumWaitingTime() {
        return minimumWaitingTime;
    }

    public void setMinimumWaitingTime(int minimumWaitingTime) {
        this.minimumWaitingTime = minimumWaitingTime;
    }

    public int getMaximumWaitingTime() {
        return maximumWaitingTime;
    }

    public void setMaximumWaitingTime(int maximumWaitingTime) {
        this.maximumWaitingTime = maximumWaitingTime;
    }

    public int getEmptyQueueTime() {
        return emptyQueueTime;
    }

    public void setEmptyQueueTime(int emptyQueueTime) {
        this.emptyQueueTime = emptyQueueTime;
    }

    public ArrayList<Client> getWaitingClients() {
        return waitingClients;
    }

    public void setWaitingClients(ArrayList<Client> waitingClients) {
        this.waitingClients = waitingClients;
    }

    public List<Queue> getQueues() {
        return queues;
    }

    public void setQueues(List<Queue> queues) {
        this.queues = queues;
    }
}
