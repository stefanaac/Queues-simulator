package gui;

import simulation.MyQueueSimulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//aceasta clasa este partea principala a GUI, in care utilizatorul introduce datele de intare :numarul de clienti,numarul de cozi,
//timoul minim/maxim de sosire,timpul minim/maxim de asteptare si durata intregii simulari
//fereastra mai contine un timer si un buton "Start" care da drumul simularii
public class View extends Component {

    MyQueueSimulation simulation;
    JFrame frame;
    JPanel myPanel;
    JLabel title;
    JLabel nrOfClientsLabel;
    JLabel nrOfQueuesLabel;
    //JLabel simulationIntervalLabel;
    JLabel minArrivalTimeLabel;
    JLabel maxArrivalTimeLabel;
    JLabel minServiceTimeLabel;
    JLabel maxServiceTimeLabel;
    JLabel simulationResultsLabel;
    JLabel simulationTimeLabel;
    JPanel timerPanel;


    JLabel timerLabel;
    JLabel t;
    JLabel i;
    JLabel m;
    JLabel e;
    JLabel r;

    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    //int hours = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    //String hours_string = String.format("%02d", hours);
    JTextField nrOfClientsTestField;
    JTextField nrOfQueuesTextField;
    // private JTextField simulationIntervalTextField;
    JTextField minArrivalTimeTextField;
    JTextField maxArrivalTimeTextField;
    JTextField minServiceTimeTextField;
    JTextField maxServiceTimeTextField;
    JTextField simulationTimeTextField;


    JButton startButton;
    JButton stopButton;
    JButton resetButton;
    JButton simulationResultsButton;

    //timerul care afiseaza minutele si secundele
    Timer timer = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            elapsedTime = elapsedTime + 1000;
            minutes = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            timerLabel.setText( minutes_string + ":" + seconds_string);

        }

    });
    ;

    public View() {

        frame = new JFrame("Queue ");
        myPanel = new JPanel();
        myPanel.setBackground(new Color(239, 222, 205));
        myPanel.setBounds(0, 0, 800, 600);
        myPanel.setLayout(null);

        title = new JLabel("QUEUE SIMULATOR");
        title.setBounds(100, 20, 600, 70);
        title.setFont(new Font("Verdana", Font.BOLD, 50));
        title.setForeground(new Color(178, 132, 190));
        myPanel.add(title);

        //LABELS



        nrOfClientsLabel = new JLabel("Clients");
        nrOfClientsLabel.setBounds(25, 170, 200, 30);
        nrOfClientsLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        nrOfClientsLabel.setForeground(new Color(178, 132, 190));
        myPanel.add(nrOfClientsLabel);

        nrOfQueuesLabel = new JLabel("Queues");
        nrOfQueuesLabel.setBounds(225, 170, 200, 30);
        nrOfQueuesLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        nrOfQueuesLabel.setForeground(new Color(178, 132, 190));
        myPanel.add(nrOfQueuesLabel);


        minArrivalTimeLabel = new JLabel("Min arrival");
        minArrivalTimeLabel.setBounds(25, 240, 200, 30);
        minArrivalTimeLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        minArrivalTimeLabel.setForeground(new Color(178, 132, 190));
        myPanel.add(minArrivalTimeLabel);

        maxArrivalTimeLabel = new JLabel("Max arrival");
        maxArrivalTimeLabel.setBounds(225, 240, 200, 30);
        maxArrivalTimeLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        maxArrivalTimeLabel.setForeground(new Color(178, 132, 190));
        myPanel.add(maxArrivalTimeLabel);


        minServiceTimeLabel = new JLabel("Min sevice");
        minServiceTimeLabel.setBounds(25, 320, 200, 30);
        minServiceTimeLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        minServiceTimeLabel.setForeground(new Color(178, 132, 190));
        myPanel.add(minServiceTimeLabel);

        maxServiceTimeLabel = new JLabel("Max service");
        maxServiceTimeLabel.setBounds(225, 320, 200, 30);
        maxServiceTimeLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        maxServiceTimeLabel.setForeground(new Color(178, 132, 190));
        myPanel.add(maxServiceTimeLabel);


        simulationTimeLabel = new JLabel(" Simulation time");
        simulationTimeLabel.setBounds(100, 400, 300, 30);
        simulationTimeLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        simulationTimeLabel.setForeground(new Color(178, 132, 190));
        myPanel.add(simulationTimeLabel);

        //TEXT-FIELD-uri
        nrOfClientsTestField = new JTextField();
        nrOfClientsTestField.setBounds(25, 200, 130, 30);
        nrOfClientsTestField.setFont(new Font("MV Boly", Font.PLAIN, 20));
        nrOfClientsTestField.setBackground(new Color(178, 132, 190));
        nrOfClientsTestField.setForeground(Color.WHITE);
        myPanel.add(nrOfClientsTestField);

        nrOfQueuesTextField = new JTextField();
        nrOfQueuesTextField.setBounds(225, 200, 130, 30);
        nrOfQueuesTextField.setFont(new Font("MV Boly", Font.PLAIN, 20));
        nrOfQueuesTextField.setForeground(Color.WHITE);
        nrOfQueuesTextField.setBackground(new Color(178, 132, 190));
        myPanel.add(nrOfQueuesTextField);

        minArrivalTimeTextField = new JTextField();
        minArrivalTimeTextField.setBounds(25, 270, 130, 30);
        minArrivalTimeTextField.setFont(new Font("MV Boly", Font.PLAIN, 20));
        minArrivalTimeTextField.setForeground(Color.WHITE);
        minArrivalTimeTextField.setBackground(new Color(178, 132, 190));
        myPanel.add(minArrivalTimeTextField);

        maxArrivalTimeTextField = new JTextField();
        maxArrivalTimeTextField.setBounds(225, 270, 130, 30);
        maxArrivalTimeTextField.setFont(new Font("MV Boly", Font.PLAIN, 20));
        maxArrivalTimeTextField.setForeground(Color.WHITE);
        maxArrivalTimeTextField.setBackground(new Color(178, 132, 190));
        myPanel.add(maxArrivalTimeTextField);


        minServiceTimeTextField = new JTextField();
        minServiceTimeTextField.setBounds(25, 350, 130, 30);
        minServiceTimeTextField.setFont(new Font("MV Boly", Font.PLAIN, 20));
        minServiceTimeTextField.setForeground(Color.WHITE);
        minServiceTimeTextField.setBackground(new Color(178, 132, 190));
        myPanel.add(minServiceTimeTextField);

        maxServiceTimeTextField = new JTextField();
        maxServiceTimeTextField.setBounds(225, 350, 130, 30);
        maxServiceTimeTextField.setFont(new Font("MV Boly", Font.PLAIN, 20));
        maxServiceTimeTextField.setForeground(Color.WHITE);
        maxServiceTimeTextField.setBackground(new Color(178, 132, 190));
        myPanel.add(maxServiceTimeTextField);


        simulationTimeTextField = new JTextField();
        simulationTimeTextField.setBounds(130, 430, 130, 30);
        simulationTimeTextField.setFont(new Font("MV Boly", Font.PLAIN, 20));
        simulationTimeTextField.setForeground(Color.WHITE);
        simulationTimeTextField.setBackground(new Color(178, 132, 190));
        myPanel.add(simulationTimeTextField);

        //BUTTONS
        startButton = new JButton("START");
        startButton.setBounds(500, 390, 100, 25);
        startButton.setBackground(new Color(178, 132, 190));
        startButton.setFont(new Font("Verdana", Font.BOLD, 15));
        startButton.setForeground(Color.white);
        startButton.setFocusable(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==startButton) {
                    if(started==false) {
                        started=true;

                        //Sim vv=new Sim();
                        int nrCl=getNrOfClients();
                        int nrQ=getNrOfQueues();
                        int sim=getSimulationTime();
                        int minA=getMinArrivalTime();
                        int maxA=getMaxArrivalTime();
                        int minS=getMinServiceTime();
                        int maxS=getMaxServiceTime();
                        MyQueueSimulation s=new MyQueueSimulation();
                        s.setNrCl(nrCl);
                        s.setTimeS(sim);
                        s.setNrQ(nrQ);
                        s.setMinAvg(minA);
                        s.setMaxAvg(maxA);
                        s.setMinServ(minS);
                        s.setMaxServ(maxS);
                        Thread t=new Thread(s);
                        t.start(); start();
                    }
                    else {
                        started=false;
                        stop();
                    }

                }
            }
        });
        myPanel.add(startButton);


        stopButton = new JButton("STOP");
        stopButton.setBounds(140, 530, 100, 25);
        stopButton.setBackground(new Color(178, 132, 190));
        stopButton.setFont(new Font("Verdana", Font.BOLD, 15));
        stopButton.setForeground(Color.white);
        stopButton.setFocusable(false);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==stopButton) {
                    started=false;
                    stop();
                }
            }
        });
        //myPanel.add(stopButton);



        resetButton = new JButton("RESET");
        resetButton.setBounds(250, 530, 100, 25);
        resetButton.setBackground(new Color(178, 132, 190));
        resetButton.setFont(new Font("Verdana", Font.BOLD, 15));
        resetButton.setForeground(Color.white);
        resetButton.setFocusable(false);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==resetButton) {
                    started=false;
                    reset();
                }
            }
        });
        //myPanel.add(resetButton);

        simulationResultsButton = new JButton("SEE SIMULATION RESULTS");
        simulationResultsButton.setBounds(30, 565, 320, 25);
        simulationResultsButton.setBackground(new Color(178, 132, 190));
        simulationResultsButton.setFont(new Font("Verdana", Font.BOLD, 15));
        simulationResultsButton.setForeground(Color.white);
        simulationResultsButton.setFocusable(false);
        //myPanel.add(simulationResultsButton);


        t = new JLabel("T");
        t.setBounds(400, 200, 30, 25);
        t.setFont(new Font("Monospaced", Font.BOLD, 25));
        t.setForeground(new Color(178, 132, 190));
        myPanel.add(t);


        i = new JLabel("I");
        i.setBounds(400, 230, 30, 25);
        i.setFont(new Font("Monospaced", Font.BOLD, 25));
        i.setForeground(new Color(178, 132, 190));
        myPanel.add(i);


        m = new JLabel("M");
        m.setBounds(400, 260, 30, 25);
        m.setFont(new Font("Monospaced", Font.BOLD, 25));
        m.setForeground(new Color(178, 132, 190));
        myPanel.add(m);

        e = new JLabel("E");
        e.setBounds(400, 290, 30, 25);
        e.setFont(new Font("Monospaced", Font.BOLD, 25));
        e.setForeground(new Color(178, 132, 190));
        myPanel.add(e);

        r = new JLabel("R");
        r.setBounds(400, 320, 30, 25);
        r.setFont(new Font("Monospaced", Font.BOLD, 25));
        r.setForeground(new Color(178, 132, 190));
        myPanel.add(r);


        timerLabel = new JLabel();
        timerLabel.setBounds(450, 230, 250, 100);
        timerLabel.setFont(new Font("Verdana", Font.BOLD, 70));
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setBackground(new Color(178, 132, 190));
        timerLabel.setHorizontalAlignment(JTextField.CENTER);
        timerLabel.setText(minutes_string+":"+seconds_string);



        timerPanel = new JPanel();
        timerPanel.setBounds(450, 230, 250, 100);
        timerPanel.setFont(new Font("MV Boly", Font.BOLD, 15));
        timerPanel.setForeground(Color.WHITE);
        timerPanel.setBackground(new Color(178, 132, 190));
        timerPanel.add(timerLabel);
        myPanel.add(timerPanel);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.add(myPanel);
        frame.setVisible(true);
    }

    void start() {
        timer.start();
    }

    void stop() {
        timer.stop();
    }

    void reset() {
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        timerLabel.setText(minutes_string+":"+seconds_string);
    }


    //metode care extrag string-ul introdus de la tastatura si il transforma in numar intreg
    public int getNrOfClients()
    {
        return Integer.parseInt(nrOfClientsTestField.getText().toString());
    }


    public int getNrOfQueues()
    {

        return Integer.parseInt(nrOfQueuesTextField.getText().toString());
    }

    public int getMinArrivalTime()
    {

        return Integer.parseInt(minArrivalTimeTextField.getText().toString());
    }

    public int getMaxArrivalTime()
    {
        return Integer.parseInt(maxArrivalTimeTextField.getText().toString());
    }


    public int getMinServiceTime()
    {

        return Integer.parseInt(minServiceTimeTextField.getText().toString());
    }

    public int getMaxServiceTime()
    {

        return  Integer.parseInt(maxServiceTimeTextField.getText().toString());
    }
    public int getSimulationTime()
    {
        return Integer.parseInt(simulationTimeTextField.getText().toString());
    }
}
