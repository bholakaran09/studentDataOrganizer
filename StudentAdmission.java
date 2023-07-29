import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.*;

public class StudentAdmission extends JFrame implements ActionListener{
    JLabel name,father_name,mother_name,mobile_no,email,dob,gender,aadhar,quali,pass;
    JTextField f_name,f_father_name,f_mother_name,f_mobile_no,f_email,f_dob,f_aadhar;
    JRadioButton gen1,gen2;
    JComboBox cb_quali;
    JButton submit,reset,back;
    JPasswordField f_pass;   

    Color c1=new Color(135, 206, 235);
    Color c2=new Color(212, 175, 55);
    
    StudentAdmission()
    {
        super("Admission Page");
        String opt[]={"Matriculation","Higher Secondary"};

        name=new JLabel("Full Name");
        name.setBounds(60,20,100,30);
        add(name);

        father_name=new JLabel("Father's Name");
		father_name.setBounds(60,60,100,30);
        add(father_name);
		
		mother_name=new JLabel("Mother's Name");
		mother_name.setBounds(60,100,100,30);
        add(mother_name);
		
        dob=new JLabel("Date of Birth");
		dob.setBounds(60,140,100,30);
        add(dob);
		
		mobile_no=new JLabel("Mobile Number");
		mobile_no.setBounds(60,180,100,30);
        add(mobile_no);
		
		email=new JLabel("E-mail ID");
		email.setBounds(60,220,100,30);
        add(email);
		
		gender=new JLabel("Gender");
		gender.setBounds(60,260,100,30);
		add(gender);

		aadhar=new JLabel("Aadhaar Number");
		aadhar.setBounds(60,300,100,30);
        add(aadhar);

        quali=new JLabel("Qualifications");
		quali.setBounds(60,340,100,30);
		add(quali);

		pass=new JLabel("Password");
		pass.setBounds(60,380,100,30);
        add(pass);

        ////////////////////////////////////////////////////////

        f_name=new JTextField();
        f_name.setBounds(190,20,150,30);
        add(f_name);

        f_father_name=new JTextField();
        f_father_name.setBounds(190,60,150,30);
        add(f_father_name);

        f_mother_name=new JTextField();
		f_mother_name.setBounds(190,100,150,30);
		add(f_mother_name);

		f_dob=new JTextField();
		f_dob.setBounds(190,140,150,30);
		add(f_dob);

		f_mobile_no=new JTextField();
		f_mobile_no.setBounds(190,180,150,30);
        add(f_mobile_no);
		
		f_email=new JTextField();
		f_email.setBounds(190,220,150,30);
        add(f_email);

		f_aadhar=new JTextField();
		f_aadhar.setBounds(190,300,150,30);
        add(f_aadhar);

        gen1=new JRadioButton("Male");
		gen1.setBounds(190,260,70,30);
		gen1.setBackground(c1);
        add(gen1);
		
		gen2=new JRadioButton("Female");
		gen2.setBounds(260,260,80,30);
		gen2.setBackground(c1);
        add(gen2);
		
		ButtonGroup bg=new ButtonGroup(); 
		bg.add(gen1);
		bg.add(gen2);

        cb_quali=new JComboBox(opt);
		cb_quali.setBounds(190,340,150,30);
		cb_quali.setBackground(c1);
        add(cb_quali);

        f_pass=new JPasswordField();
		f_pass.setBounds(190,380,150,30);
        add(f_pass);

        submit=new JButton("Submit");
		submit.setBounds(50,440,80,30);
		submit.setBackground(c2);
        add(submit);
		
		reset=new JButton("Reset");
		reset.setBounds(160,440,80,30);
		reset.setBackground(c2);
        add(reset);
                
        back=new JButton("Back");
		back.setBounds(270,440,80,30);
		back.setBackground(c2);
		add(back);

        /////////////////////////////////////////////////////////////////

        gen1.addActionListener(this);
		gen2.addActionListener(this);
		submit.addActionListener(this);
		reset.addActionListener(this);
        back.addActionListener(this);


        setSize(420,550);
		setLayout(null);
		setVisible(true);
        getContentPane().setBackground(c1);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==reset)
        {
            f_name.setText("");
			f_father_name.setText("");
			f_mother_name.setText("");
			f_email.setText("");
			f_mobile_no.setText("");
			f_dob.setText("");
			f_aadhar.setText("");
			f_pass.setText("");
        }

        if(e.getSource()==back)
        {
            // code to return main menu
            this.setVisible(false);
        }
        
        if(e.getSource()==submit)
        {
            String n1,n2,n3,n4,n5,n6,n7,n8;
            
            String g="",d="";

            n1=f_name.getText();
            if(n1.length()==0)
            {
                JOptionPane.showMessageDialog(null, "Enter Name!!!");
                return;
            }

            n2=f_father_name.getText();
            if(n2.length()==0)
            {
                JOptionPane.showMessageDialog(null, "Enter Father's Name!!!");
                return;
            }

            n3=f_mother_name.getText();
            if(n3.length()==0)
            {
                JOptionPane.showMessageDialog(null, "Enter Mother's Name!!!");
                return;
            }

            n4=f_email.getText();
            if(n4.length()==0)
            {
                JOptionPane.showMessageDialog(null, "Enter E-mail!!!");
                return;
            }

            n5=f_dob.getText();
            if(n5.length()==0)
            {
                JOptionPane.showMessageDialog(null, "Enter your Date of Birth!!!");
                return;
            }

            n6=f_pass.getText();
            if(n6.length()==0)
            {
                JOptionPane.showMessageDialog(null, "Enter a strong password!!!");
                return;
            }

            n7=f_mobile_no.getText();
            if(n7.length()!=10)
            {
                JOptionPane.showMessageDialog(null, "Enter a valid Mobile Number!!!");
                return;
            }
            
            n8=f_aadhar.getText();
            if(n8.length()!=12)
            {
                JOptionPane.showMessageDialog(null, "Enter your Aadhar Number!!!");
                return;
            }

            if(gen1.isSelected())
				g="Male";
			if(gen2.isSelected())
				g="Female";
            if(g.length()==0)
            {
                JOptionPane.showMessageDialog(null,"Please Select Gender!!!");
                return;        
            }  
            
            d=cb_quali.getSelectedItem().toString();

            //connection with database

            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","manager");

                Statement statement=con.createStatement();

                statement.executeUpdate("insert into student_data (name,father_name,mother_name,email,dob,password,mobile,aadhar,gender,qualification)values('"+n1+"','"+n2+"','"+n3+"','"+n4+"','"+n5+"','"+n6+"','"+n7+"',+'"+n8+"','"+g+"','"+d+"')");
                JOptionPane.showMessageDialog(null,"Your Data has been saved successfully.");

                f_name.setText("");
                f_father_name.setText("");
                f_mother_name.setText("");
                f_email.setText("");
                f_mobile_no.setText("");
                f_dob.setText("");
                f_aadhar.setText("");
                f_pass.setText("");

                statement.close();

            }
            catch(Exception ee)
            {
                ee.printStackTrace();
            }

        }
    }

    public static void main(String [] args)
    {
        new StudentAdmission();
    }
}
