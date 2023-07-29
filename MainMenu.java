import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame implements ActionListener{
    JLabel l;
    JButton b1,b2,b3,b4,b5,b6;
    Color c1=new Color(135, 206, 235);
    Color c2=new Color(212, 175, 55);

    MainMenu()
    {
        super("Admin Panel");

        l=new JLabel("Admin Panel");
        l.setBounds(160,15,70,30);
        add(l);

        b1=new JButton("Admission form");
        b1.setBounds(40,80,120,35);
        b1.setBackground(c2);
        add(b1);

        b2=new JButton("Search Details");
        b2.setBounds(220,80,120,35);
        b2.setBackground(c2);
        add(b2);

        b3=new JButton("Update Details");
        b3.setBounds(40,160,120,35);
        b3.setBackground(c2);
        add(b3);

        b4=new JButton("View All");
        b4.setBounds(220,160,120,35);
        b4.setBackground(c2);
        add(b4);

        b5=new JButton("Delete Detail");
        b5.setBounds(40,240,120,35);
        b5.setBackground(c2);
        add(b5);

        b6=new JButton("Exit Panel");
        b6.setBounds(220,240,120,35);
        b6.setBackground(c2);
        add(b6);


        setSize(420,400);
		setLayout(null);
		setVisible(true);
        getContentPane().setBackground(c1);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        new StudentAdmission();
        if(e.getSource()==b2)
        new SearchStudent();
        if(e.getSource()==b3)
        new UpdateDetail();
        if(e.getSource()==b4)
        new ViewAll();
        if(e.getSource()==b5)
        new DeleteData();
        if(e.getSource()==b6)
        setVisible(false);
        
    }

    public static void main(String [] args)
    {
        new MainMenu();
    }
}
