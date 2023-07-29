import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class SearchStudent extends JFrame implements ActionListener{
    JLabel l, name, id;
    JTextField f_name, f_id;
    JRadioButton g1, g2;
    JButton back,show;
    TextArea ta;

    Color c1=new Color(135, 206, 235);
    Color c2=new Color(212, 175, 55);

    SearchStudent()
    {
        super("Search Student");

        l = new JLabel("Search by ");
        l.setBounds(30, 15, 70, 30);
        add(l);

        name = new JLabel("Name");
        name.setBounds(50, 55, 70, 30);
        add(name);
        
        id = new JLabel("ID");
        id.setBounds(50, 95, 70, 30);
        add(id);
        
        f_name = new JTextField();
        f_name.setBounds(140, 55, 90, 30);
        add(f_name);

        f_id = new JTextField();
        f_id.setBounds(140, 95, 90, 30);
        add(f_id);

        g1 = new JRadioButton("Name");
        g1.setBounds(110, 15, 70, 30);
        g1.setBackground(c1);
        add(g1);
        
        g2 = new JRadioButton("ID");
        g2.setBounds(190, 15, 70, 30);
        g2.setBackground(c1);
        add(g2);

        ButtonGroup bg = new ButtonGroup();
        bg.add(g1);
        bg.add(g2);

        show=new JButton("Show");
        show.setBounds(50,150,80,30);
        show.setBackground(c2);
        add(show);
        
        back=new JButton("Back");
        back.setBounds(235,150,80,30);
        back.setBackground(c2);
        add(back);
        
        ta=new TextArea();
        ta.setBounds(30,230,300,230);
        add(ta);

        g1.addActionListener(this);
        g2.addActionListener(this);
        back.addActionListener(this);
        show.addActionListener(this);

        setSize(380, 550);
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(c1);
        
        name.setVisible(false);
        f_name.setVisible(false);
        id.setVisible(false);
        f_id.setVisible(false);

    }

    public void actionPerformed(ActionEvent e)
    {
        if (g1.isSelected()) 
        {
            // System.out.println("Helloqqqqq");
            name.setVisible(true);
            f_name.setVisible(true);
            id.setVisible(false);
            f_id.setVisible(false);
        }

        if (g2.isSelected()) 
        {   
            name.setVisible(false);
            f_name.setVisible(false);
            id.setVisible(true);
            f_id.setVisible(true);
        }

        if(e.getSource()==show)
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","manager");

                Statement st1=con.createStatement();
                ResultSet rs=st1.executeQuery("select * from student_data");

                String a,b;
                if(g1.isSelected())
                {
                    a=f_name.getText();
                    if(a.length()==0 )            
                    {
                        JOptionPane.showMessageDialog(null,"Enter Name !");
                        return;
                    }
                    else
                    {
                        rs=st1.executeQuery("select * from student_data where name='"+a+"'");
                    }
                }

                if(g2.isSelected())
                {
                    b=f_id.getText();
                    if(b.length()==0)            
                    {
                        JOptionPane.showMessageDialog(null,"Enter Aadhar Number !");
                        return;
                    }
                    else
                    {
                        rs=st1.executeQuery("select * from student_data where id='"+b+"'");
                    }
                }

                String x="";
                int f=0;

                while (rs.next())
                {
                    x=x+"\n Name of Student : "+rs.getString(1);
                    x=x+"\n Father's Name : "+rs.getString(2);
                    x=x+"\n Mothers Name : "+rs.getString(3);
                    x=x+"\n E-mail: "+rs.getString(4);
                    x=x+"\n Date of Birth : "+rs.getString(5);
                    x=x+"\n Password : "+rs.getString(6);
                    x=x+"\n Mobile Number : "+rs.getString(7);
                    x=x+"\n Aadhar Number: "+rs.getString(8);
                    x=x+"\n Gender : "+rs.getString(9);
                    x=x+"\n Qualifications : "+rs.getString(10);
                    x=x+"\n ID : "+rs.getString(11);
            
                    ta.setText(x);
                    ta.setEditable(false);
                    f=1;
                }
                if(f==0)
                    ta.setText("");

            }
            
            catch(Exception ee){
                ee.printStackTrace();
            }

        }

        if(e.getSource()==back)
        {
            setVisible(false);
            //main menu new mainmenu
        }
    }

    public static void main(String [] args)
    {
        new SearchStudent();
    }

}
