import  java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.TableColumn;

public class ViewAll extends JFrame implements ActionListener{
    javax.swing.JScrollPane scrollPane,scrollPane2;
    JLabel x1,n1,n2;
    Vector columnNames = new Vector();
    Vector data = new Vector();
    JButton back;
    Color c1=new Color(135, 206, 235);
    Color c2=new Color(212, 175, 55);

    ViewAll()
    {
        super("View All Page");
        
        back=new JButton("Back");
        back.setBounds(400,380,80,30);
        back.setBackground(c2);
        add(back);
        back.addActionListener(this);
        
        x1=new JLabel("Viewing Student Details");
        x1.setBounds(370,20,200,100);
        getContentPane().add(x1);
        
        
        n1=new JLabel("");
        n1.setBounds(300,50,100,50);
        getContentPane().add(n1);
        
        getContentPane().setLayout(null);
        getContentPane().setBackground(c1);
        setSize(950,500);
        setVisible(true);

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","manager");

            Statement st1=con.createStatement();
            // String s1="";

            ResultSet rs=st1.executeQuery("select * from student_data");

            ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			for (int i = 1; i <= columns; i++) 
			{
				columnNames.addElement( md.getColumnName(i) );
			}
			while (rs.next())
			 {
				Vector row = new Vector(columns);
				for (int i = 1; i <= columns; i++)
				{
					row.addElement( rs.getObject(i) );
				}
				data.addElement( row );
			}
            rs.close();
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }

        JTable text = new JTable(data, columnNames);
		text.setBounds(100,200,850,250);
		TableColumn col;
		for (int i = 0; i < text.getColumnCount(); i++)
		 {
			col = text.getColumnModel().getColumn(i);
			col.setMaxWidth(300);
		}
	    scrollPane = new javax.swing.JScrollPane( text);
	    getContentPane().add(scrollPane);
	    scrollPane.setBounds(75,120,800,200);


    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==back)
        {
            setVisible(false);
            // main menu;
        }
    }

    public static void main(String [] args)
    {
        new ViewAll();
    }
}
