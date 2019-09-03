import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.beans.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BloodMain extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField bGroup;
	private JTextField adress;
	private JTextField phone;
	JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BloodMain frame = new BloodMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection con = null;
	private JTextField bloodgroup;
	private JTable searchtable;
	private JTextField na;
	private JTextField bg;
	private JTextField ad;
	private JTextField pn;
	private JTextField wi;
	private JTable updatetable;
	private JPanel panel;
	private JTextField willing;
	private JTextField ref;
	private JTextField rf;
	private JTextField last;
	private JTextField lastdon;
	
	public BloodMain() throws SQLException {
		setBackground(Color.WHITE);
		setResizable(false);
		con = DriverManager.getConnection("JDBC:sqlite:D:\\Java-Workspace\\Bloodbank\\Blood.sqlite");
		JOptionPane.showMessageDialog(null, "Connect Successfully");
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(90, 0, 1200, 730);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		mntmNew.setIcon(new ImageIcon(BloodMain.class.getResource("/Img/images (9).jpg")));
		mnFile.add(mntmNew);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mntmExit.setIcon(new ImageIcon(BloodMain.class.getResource("/Img/images (12).png")));
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 99, 71));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBloodDonation = new JLabel("BLOOD DONATION");
		lblBloodDonation.setIcon(new ImageIcon(BloodMain.class.getResource("/Img/images (1).png")));
		lblBloodDonation.setForeground(Color.DARK_GRAY);
		lblBloodDonation.setBackground(Color.LIGHT_GRAY);
		lblBloodDonation.setHorizontalAlignment(SwingConstants.CENTER);
		lblBloodDonation.setFont(new Font("Algerian", Font.BOLD, 40));
		lblBloodDonation.setBounds(20, 0, 1149, 117);
		contentPane.add(lblBloodDonation);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(0, 0, 0));
		tabbedPane.setBackground(Color.GRAY);
		tabbedPane.setFont(new Font("Palatino Linotype", Font.BOLD, 25));
		tabbedPane.setBounds(20, 112, 1149, 517);
		contentPane.add(tabbedPane);
		
		panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setForeground(Color.GRAY);
		tabbedPane.addTab("ADD DONOR", new ImageIcon(BloodMain.class.getResource("/Img/images.png")), panel, null);
		panel.setLayout(null);
		
		JLabel lblAddDonationPerson = new JLabel("ADD DONATION PERSON INFORMATION");
		lblAddDonationPerson.setForeground(Color.BLUE);
		lblAddDonationPerson.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddDonationPerson.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		lblAddDonationPerson.setBounds(0, 11, 1134, 63);
		panel.add(lblAddDonationPerson);
		
		JLabel name1 = new JLabel("Name");
		name1.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		name1.setBounds(10, 85, 143, 46);
		panel.add(name1);
		
		name = new JTextField();
		name.setBackground(new Color(255, 240, 245));
		name.setBounds(163, 85, 326, 46);
		panel.add(name);
		name.setColumns(10);
		
		bGroup = new JTextField();
		bGroup.setBackground(new Color(255, 240, 245));
		bGroup.setColumns(10);
		bGroup.setBounds(163, 154, 326, 46);
		panel.add(bGroup);
		
		JLabel bGroup1 = new JLabel("Blood Group");
		bGroup1.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		bGroup1.setBounds(10, 154, 143, 46);
		panel.add(bGroup1);
		
		adress = new JTextField();
		adress.setBackground(new Color(255, 240, 245));
		adress.setColumns(10);
		adress.setBounds(163, 227, 326, 46);
		panel.add(adress);
		
		JLabel adress1 = new JLabel("Adress");
		adress1.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		adress1.setBounds(10, 227, 143, 46);
		panel.add(adress1);
		
		phone = new JTextField();
		phone.setBackground(new Color(255, 240, 245));
		phone.setColumns(10);
		phone.setBounds(163, 301, 326, 46);
		panel.add(phone);
		
		JLabel phone1 = new JLabel("Phone Number");
		phone1.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		phone1.setBounds(10, 301, 143, 46);
		panel.add(phone1);
		
		JButton addDoner = new JButton("");
		addDoner.setIcon(new ImageIcon(BloodMain.class.getResource("/Img/images (7).png")));
		addDoner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nam = name.getText();
				String blood = bGroup.getText();
				String adr = adress.getText();
				String phn = phone.getText();
				String Reference = ref.getText();
				String wil = willing.getText();
				String lastDate = last.getText();
				String query = "insert into BloodDoner (Name,BloodGroup,Adress,Phone,Reference,Willing,LastDonate) Values ('"+nam+"','"+blood+"','"+adr+"','"+phn+"','"+Reference+"','"+wil+"','"+lastDate+"')";
				
				try {
					java.sql.Statement st = con.createStatement();
					st.execute(query);
					JOptionPane.showMessageDialog(null, "Donor added successfully");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		addDoner.setBackground(Color.GRAY);
		addDoner.setForeground(Color.WHITE);
		addDoner.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		addDoner.setBounds(970, 301, 96, 39);
		panel.add(addDoner);
		
		JLabel label_7 = new JLabel("Willing");
		label_7.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		label_7.setBounds(655, 153, 143, 46);
		panel.add(label_7);
		
		willing = new JTextField();
		willing.setBackground(new Color(255, 240, 245));
		willing.setColumns(10);
		willing.setBounds(788, 154, 315, 46);
		panel.add(willing);
		
		ref = new JTextField();
		ref.setBackground(new Color(255, 240, 245));
		ref.setColumns(10);
		ref.setBounds(788, 86, 315, 46);
		panel.add(ref);
		
		JLabel label_8 = new JLabel("Reference");
		label_8.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		label_8.setBounds(655, 85, 143, 46);
		panel.add(label_8);
		
		last = new JTextField();
		last.setColumns(10);
		last.setBackground(new Color(255, 240, 245));
		last.setBounds(788, 227, 315, 46);
		panel.add(last);
		
		JLabel lblLastDonate = new JLabel("Last Donate");
		lblLastDonate.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		lblLastDonate.setBounds(655, 226, 143, 46);
		panel.add(lblLastDonate);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 196, 222));
		panel_1.setForeground(Color.GRAY);
		tabbedPane.addTab("      SEARCH", new ImageIcon(BloodMain.class.getResource("/Img/images79.png")), panel_1, null);
		panel_1.setLayout(null);
		
		bloodgroup = new JTextField();
		bloodgroup.setBackground(new Color(255, 240, 245));
		bloodgroup.setColumns(10);
		bloodgroup.setBounds(185, 32, 307, 46);
		panel_1.add(bloodgroup);
		
		JLabel label = new JLabel("Blood Group");
		label.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		label.setBounds(32, 32, 143, 46);
		panel_1.add(label);
		
		JButton btnSearch = new JButton("");
		btnSearch.setIcon(new ImageIcon(BloodMain.class.getResource("/Img/images (6).png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bloodgrp = bloodgroup.getText();
				
				String query1 = "select * from BloodDoner where BloodGroup = '"+bloodgrp+"'" ;
						
						
						try {
							java.sql.Statement st1 = con.createStatement();
							
							ResultSet rs = st1.executeQuery(query1);
							searchtable.setModel(DbUtils.resultSetToTableModel(rs));
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			}
		});
		btnSearch.setBackground(Color.GRAY);
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		btnSearch.setBounds(513, 32, 95, 46);
		panel_1.add(btnSearch);
		
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setBounds(10, 133, 1124, 299);
		panel_1.add(scrollPanel);
		
		searchtable = new JTable();
		searchtable.setBackground(new Color(32, 178, 170));
		scrollPanel.setViewportView(searchtable);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(176, 196, 222));
		panel_2.setForeground(Color.GRAY);
		tabbedPane.addTab("     UPDATE", new ImageIcon(BloodMain.class.getResource("/Img/images67.png")), panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		label_1.setBounds(26, 11, 143, 46);
		panel_2.add(label_1);
		
		na = new JTextField();
		na.setBackground(new Color(255, 248, 220));
		na.setColumns(10);
		na.setBounds(179, 11, 341, 46);
		panel_2.add(na);
		
		JLabel label_2 = new JLabel("Blood Group");
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		label_2.setBounds(26, 68, 143, 46);
		panel_2.add(label_2);
		
		bg = new JTextField();
		bg.setBackground(new Color(255, 248, 220));
		bg.setEditable(false);
		bg.setColumns(10);
		bg.setBounds(179, 68, 341, 46);
		panel_2.add(bg);
		
		JLabel label_3 = new JLabel("Adress");
		label_3.setForeground(Color.DARK_GRAY);
		label_3.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		label_3.setBounds(26, 125, 143, 46);
		panel_2.add(label_3);
		
		ad = new JTextField();
		ad.setBackground(new Color(255, 248, 220));
		ad.setColumns(10);
		ad.setBounds(179, 125, 341, 46);
		panel_2.add(ad);
		
		JLabel label_4 = new JLabel("Phone Number");
		label_4.setForeground(Color.DARK_GRAY);
		label_4.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		label_4.setBounds(26, 182, 143, 46);
		panel_2.add(label_4);
		
		pn = new JTextField();
		pn.setBackground(new Color(255, 248, 220));
		pn.setColumns(10);
		pn.setBounds(179, 182, 341, 46);
		panel_2.add(pn);
		
		JLabel label_6 = new JLabel("Willing");
		label_6.setForeground(Color.DARK_GRAY);
		label_6.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		label_6.setBounds(678, 11, 109, 46);
		panel_2.add(label_6);
		
		wi = new JTextField();
		wi.setBackground(new Color(255, 248, 220));
		wi.setColumns(10);
		wi.setBounds(797, 12, 305, 46);
		panel_2.add(wi);
		
		JButton btnSave = new JButton("");
		btnSave.setIcon(new ImageIcon(BloodMain.class.getResource("/Img/images (5).png")));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				
				try {
					String query2 = "select * from BloodDoner";
					java.sql.Statement st2 = con.createStatement();
					
					ResultSet rs2 = st2.executeQuery(query2);
					updatetable.setModel(DbUtils.resultSetToTableModel(rs2));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		btnSave.setBackground(Color.GRAY);
		btnSave.setBounds(697, 200, 90, 43);
		panel_2.add(btnSave);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 254, 1124, 178);
		panel_2.add(scrollPane);
		
		updatetable = new JTable();
		updatetable.setBackground(new Color(102, 205, 170));
		updatetable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = updatetable.getSelectedRow();
				String n = updatetable.getModel().getValueAt(row, 0).toString(); 
				String query3 = "select * from BloodDoner where Name= '"+n+"'"; 
				try {
					java.sql.Statement st3 = con.createStatement();
					ResultSet rs3 = st3.executeQuery(query3);
					
					while(rs3.next()){
						na.setText(rs3.getString("Name"));
						bg.setText(rs3.getString("BloodGroup"));
						ad.setText(rs3.getString("Adress"));
						pn.setText(rs3.getString("Phone"));
						rf.setText(rs3.getString("Reference"));
						wi.setText(rs3.getString("Willing"));
						lastdon.setText(rs3.getString("LastDonate"));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		scrollPane.setViewportView(updatetable);
		
		JButton btnUpdate = new JButton("");
		btnUpdate.setIcon(new ImageIcon(BloodMain.class.getResource("/Img/images14.png")));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nam2 = na.getText();
				String blood2 = bg.getText();
				String adr2 = ad.getText();
				String phn2 = pn.getText();
				String Reference2 = rf.getText();
				String wil2 = wi.getText();
				String lastd = lastdon.getText();
				
				String query4 = "Update BloodDoner Set Name='"+nam2+"',BloodGroup='"+blood2+"',Adress='"+adr2+"',Phone='"+phn2+"',Reference='"+Reference2+"',Willing='"+wil2+"',LastDonate=='"+lastd+"' where Name='"+nam2+"'";
				try {
					java.sql.Statement st4 = con.createStatement();
					st4.execute(query4);
					JOptionPane.showMessageDialog(null, "Donor Updated");
				} catch (SQLException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		btnUpdate.setBackground(Color.GRAY);
		btnUpdate.setBounds(1012, 200, 90, 43);
		panel_2.add(btnUpdate);
		
		JLabel label_5 = new JLabel("Reference");
		label_5.setBackground(new Color(169, 169, 169));
		label_5.setForeground(Color.DARK_GRAY);
		label_5.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		label_5.setBounds(678, 80, 143, 46);
		panel_2.add(label_5);
		
		rf = new JTextField();
		rf.setBackground(new Color(255, 248, 220));
		rf.setColumns(10);
		rf.setBounds(798, 81, 304, 46);
		panel_2.add(rf);
		
		JLabel lblLastdonate = new JLabel("LastDonate");
		lblLastdonate.setForeground(Color.DARK_GRAY);
		lblLastdonate.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		lblLastdonate.setBackground(new Color(169, 169, 169));
		lblLastdonate.setBounds(678, 142, 143, 46);
		panel_2.add(lblLastdonate);
		
		lastdon = new JTextField();
		lastdon.setColumns(10);
		lastdon.setBackground(new Color(255, 248, 220));
		lastdon.setBounds(798, 143, 304, 46);
		panel_2.add(lastdon);
	}
}
