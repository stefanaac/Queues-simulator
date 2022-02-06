package gui;



import simulation.MyQueueSimulation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//aceasta clasa tine de interfata grafica a aplicatiei, este instantiata in momentul in care utilizatorul apasa start,
//pentru a porni simularea
//afisam in fereastra acestei clase evolutia cozilor in timp real, iar la sfarsitul simularii media timpului de asteptare
public class Sim extends Component {
    JFrame frame;
    JPanel myPanel;
    public JTextField patrat;
    public Sim() {
        frame = new JFrame("Queue Simulator");
        myPanel = new JPanel();
        myPanel.setBackground(new Color(239, 222, 205));
        myPanel.setBounds(0, 0, 1000, 200);
        myPanel.setLayout(null);
        Border border=BorderFactory.createLineBorder(new Color(239, 222, 205),50);

        patrat = new JTextField();
        patrat.setBounds(0, 0, 1000, 200);
        patrat.setFont(new Font("MV Boly", Font.BOLD, 20));
        patrat.setForeground(Color.WHITE);
        patrat.setBackground(new Color(186, 138, 199));
        myPanel.setBorder(border);
        myPanel.add(patrat);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 200);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.add(myPanel);
        frame.setVisible(true);
    }


}
