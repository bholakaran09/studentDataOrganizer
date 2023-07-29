import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DeleteData extends JFrame implements ActionListener{
    JLabel l1,l2;
    JButton delete,back;
    JTextField tf;

    Color c1=new Color(135, 206, 235);
    Color c2=new Color(212, 175, 55);
    
    DeleteData()
    {
        super("Delete Data");

        l1=new JLabel("Delete Data");
        l1.setBounds(120,15,70,30);
        add(l1);

        l2=new JLabel("Enter ID of Student");
        l2.setBounds(30,70,120,30);
        add(l2);

        tf=new JTextField();
        tf.setBounds(180,70,100,30);
        add(tf);

        delete=new JButton("Delete");
        delete.setBounds(50,150,90,30);
        delete.setBackground(c2);
        add(delete);
        delete.addActionListener(this);

        back=new JButton("Back");
        back.setBounds(170,150,90,30);
        back.setBackground(c2);
        add(back);
        back.addActionListener(this);
        
        setSize(350,300);
		setLayout(null);
		setVisible(true);
        getContentPane().setBackground(c1);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==back)
        {
            setVisible(false);
            // main menu
        }
        if(e.getSource()==delete)
        {

            int res=JOptionPane.showConfirmDialog(null, "Do you want to Delete Data?","Confirmation",JOptionPane.YES_NO_OPTION);
            
            if(res==JOptionPane.YES_OPTION)
            {
                String str=tf.getText();
                int id=Integer.parseInt(str);
                if(id==0)
                {
                    JOptionPane.showMessageDialog(null,"Please enter ID!!!");
                    return;
                }
                try
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","manager"); 
                    String query = "delete from student_data where id=?";  
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setInt(1, id);
                    // ps.setString(2, t2);
                    // ps.setString(3, t3);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Deleted Successfully");
                    tf.setText("");

                }
                catch(Exception ee)
                {
                    ee.printStackTrace();
                }
            }
            if(res==JOptionPane.NO_OPTION)
            {
                return;
            }
        
        }
    }
    public static void main(String [] args)
    {
        new DeleteData();
    }
}
