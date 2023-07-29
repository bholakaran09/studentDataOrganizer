import  java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateDetail extends JFrame implements ActionListener{
    
    JLabel l1,l2,l3,l4;
    JComboBox cb;
    JTextField text1,text2;
    JButton update,back;
    Color c1=new Color(135, 206, 235);
    Color c2=new Color(212, 175, 55);
    

    UpdateDetail()
    {
        super("Update Student Details");
        String opt[]={"name","father_name","mother_name","email","dob","password","mobile","aadhar","gender","qualification"};

        l1=new JLabel("Update Details");
        l1.setBounds(140,15,100,30);
        add(l1);

        l2=new JLabel("Enter ID");
        l2.setBounds(50,60,170,30);
        add(l2);

        l3=new JLabel("Select field to update");
        l3.setBounds(50,130,130,30);
        add(l3);

        l4=new JLabel("Enter updated Value");
        l4.setBounds(50,200,130,30);
        add(l4);

        text1=new JTextField();
        text1.setBounds(200,60,140,30);
        add(text1);

        text2=new JTextField();
        text2.setBounds(200,200,140,30);
        add(text2);

        cb=new JComboBox(opt);
		cb.setBackground(c1);
		cb.setBounds(200,130,150,30);
        add(cb);

        update=new JButton("Update");
        update.setBounds(70,310,80,30);
        update.setBackground(c2);
        add(update);
        update.addActionListener(this);

        back=new JButton("Back");
        back.setBounds(200,310,80,30);
        back.setBackground(c2);
        add(back);
        back.addActionListener(this);

        setSize(390, 450);
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(c1);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==update)
        {
            String t1,t2,t3;
            t1=cb.getSelectedItem().toString();//field
            t2=text2.getText();//value
            if(t2.length()==0)
            {
                JOptionPane.showMessageDialog(null,"Please enter Value!!!");
                return;
            }
            t3=text1.getText();//id
            if(t3.length()==0)
            {
                JOptionPane.showMessageDialog(null,"Please enter ID!!!");
                return;
            }
           
            // System.out.println(((Object) t1).getClass().getSimpleName());
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","manager");
                // String query = "update student_data set '"+t1+"'='"+t2+"' where id='"+t3+"' ";  
                String query = "update student_data set "+t1+" = " + "'"+t2+"' where id="+t3 ;  
                PreparedStatement ps = con.prepareStatement(query);
                // ps.setString(1, t1);
                // ps.setString(2, t2);
                // ps.setString(3, t3);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Updated Successfully");
                text1.setText("");
                text2.setText("");

            }
            catch(Exception eee)
            {
                eee.printStackTrace();
            }
        }

        if(e.getSource()==back)
        {
            setVisible(false);
            // main menu
        }
    }

    public static void main(String [] args)
    {
        new UpdateDetail();
    }
}
