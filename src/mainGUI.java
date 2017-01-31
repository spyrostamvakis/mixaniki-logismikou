import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

public class mainGUI {

	private JFrame frame;
	private JTextField nameFld;
	private JTextField dayFld;
	private JTextField monthFld;
	private JTextField yearFld;
	private JTextField priceFld;
	private Integer[] reservationSeats = new Integer[60];
	queryDB query = new queryDB();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainGUI window = new mainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Concert Manager v0.1");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Integer kappa=0;
		kappa=1;
		
		if (kappa.equals(null))
		{
			
		}
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		JPanel home_panel = new JPanel();
		panel.add(home_panel, "home_panel");
		home_panel.setLayout(null);
		
		JButton user_btn = new JButton("User");
		user_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    CardLayout card = (CardLayout)panel.getLayout();
			    card.show(panel, "user_panel");
			}
		});
		user_btn.setBounds(92, 115, 82, 48);
		home_panel.add(user_btn);
		
		JButton admin_btn = new JButton("Admin");
		admin_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    CardLayout card = (CardLayout)panel.getLayout();
			    card.show(panel, "admin_panel");
			}
		});
		admin_btn.setBounds(252, 115, 82, 48);
		home_panel.add(admin_btn);
		
		JLabel lblPleaseSelectType = new JLabel("Please select type of user privileges");
		lblPleaseSelectType.setBounds(107, 66, 215, 14);
		home_panel.add(lblPleaseSelectType);
		
		JPanel user_panel = new JPanel();
		panel.add(user_panel, "user_panel");
		user_panel.setLayout(null);
		
		JLabel lblPleaseSelectThe = new JLabel("Please select the concert ");
		lblPleaseSelectThe.setBounds(141, 47, 159, 14);
		user_panel.add(lblPleaseSelectThe);
						
		JLabel lblUser = new JLabel("USER");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUser.setBounds(193, 0, 89, 50);
		user_panel.add(lblUser);
		
		JComboBox<String> userCb = new JComboBox<String>();
		userCb.setBounds(78, 72, 274, 20);
		userCb.setModel(new DefaultComboBoxModel<String>(query.selectAllConcerts()));
		user_panel.add(userCb);
		
		
		// Create gate-seats as checkboxes dynamically
		javax.swing.JCheckBox[] jCheckboxArray = new javax.swing.JCheckBox[60];

        int pos=78;
        int height=110;

        for(int x = 0; x < 60 ; x++) {
            jCheckboxArray[x] = new javax.swing.JCheckBox();
            jCheckboxArray[x].setToolTipText(""+(x+1));
            jCheckboxArray[x].setBounds(pos, height, 21, 21);
            pos+=23;
            if ((((x+1) % 12) == 0))
            {
            	pos=78;
            	height +=21;            	
            }            
            user_panel.add(jCheckboxArray[x]);
        }  
        		
		userCb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] concert = new String[2];
				concert  = userCb.getSelectedItem().toString().split("\\)");
				int concert_id = Integer.parseInt(concert[0]);
				reservationSeats = query.getSeats(concert_id);

				for(int x = 0; x < 60 ; x++) {
					jCheckboxArray[x].setSelected(false);	
					jCheckboxArray[x].setEnabled(true);	 
				}
				
				for (int i=0;i<reservationSeats.length;i++)
			    {
		        	jCheckboxArray[reservationSeats[i]-1].setSelected(true);
		        	jCheckboxArray[reservationSeats[i]-1].setEnabled(false);
				}
			}
		});					
		userCb.setSelectedIndex(0);
		
		JButton back_user = new JButton("Back");
		back_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    CardLayout card = (CardLayout)panel.getLayout();
			    card.show(panel, "home_panel");  
			}
		});
		back_user.setBounds(10, 227, 89, 23);
		user_panel.add(back_user);
		
		JButton printBtn = new JButton("Print");
		printBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String[] concert = new String[2];
				concert  = userCb.getSelectedItem().toString().split("\\)");
				int concert_id = Integer.parseInt(concert[0]);
				boolean flag = false;
				for(int x = 0; x < 60 ; x++) 
				{
					if (jCheckboxArray[x].isEnabled() && jCheckboxArray[x].isSelected())
					{
						flag=true;
						int seat_id;
						seat_id = Integer.parseInt(jCheckboxArray[x].getToolTipText());	
						query.reserveSeats(concert_id,seat_id);
					}
						
				}
				if (flag)
				{
					JOptionPane.showMessageDialog(frame,
						    "Tickets printed successfully.");
					CardLayout card = (CardLayout)panel.getLayout();
				    card.show(panel, "home_panel");
				    //------- refresh checkboxes
					reservationSeats = query.getSeats(concert_id);

					for(int x = 0; x < 60 ; x++) {
						jCheckboxArray[x].setSelected(false);	
						jCheckboxArray[x].setEnabled(true);	 
					}
					
					for (int i=0;i<reservationSeats.length;i++)
				    {
			        	jCheckboxArray[reservationSeats[i]-1].setSelected(true);
			        	jCheckboxArray[reservationSeats[i]-1].setEnabled(false);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(frame,
						    "Please select a seat or more to print",
						    "Select seat",
						    JOptionPane.WARNING_MESSAGE);
				}				
				//--------
			}
		});
		printBtn.setBounds(331, 227, 89, 23);
		user_panel.add(printBtn);
		
		JPanel admin_panel = new JPanel();
		panel.add(admin_panel, "admin_panel");
		admin_panel.setLayout(null);
		
		JButton back_admin = new JButton("Back");
		back_admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    CardLayout card = (CardLayout)panel.getLayout();
			    card.show(panel, "home_panel");
			}
		});
		back_admin.setBounds(10, 227, 89, 23);
		admin_panel.add(back_admin);
		
		JButton add_concert = new JButton("Add new concert");
		add_concert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    CardLayout card = (CardLayout)panel.getLayout();
			    card.show(panel, "newConc_panel");
			}
		});
		add_concert.setBounds(64, 98, 138, 50);
		admin_panel.add(add_concert);
		
		JButton stats = new JButton("Statistics");
		stats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    CardLayout card = (CardLayout)panel.getLayout();
			    card.show(panel, "stats_panel");
			}
		});
		stats.setBounds(236, 98, 138, 50);
		admin_panel.add(stats);
		
		JLabel lblAdministrator = new JLabel("ADMINISTRATOR");
		lblAdministrator.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdministrator.setBounds(151, 37, 161, 50);
		admin_panel.add(lblAdministrator);
		
		JPanel newConc_panel = new JPanel();
		newConc_panel.setLayout(null);
		panel.add(newConc_panel, "newConc_panel");
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    CardLayout card = (CardLayout)panel.getLayout();
			    card.show(panel, "admin_panel");
			    nameFld.setText("");
			    priceFld.setText("");
			    dayFld.setText("");
			    monthFld.setText("");
			    yearFld.setText("");
			}
		});
		
		button.setBounds(10, 227, 89, 23);
		newConc_panel.add(button);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(22, 67, 46, 14);
		newConc_panel.add(lblName);
		
		JLabel lblStadium = new JLabel("Stadium :");
		lblStadium.setBounds(22, 92, 57, 14);
		newConc_panel.add(lblStadium);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(22, 117, 46, 14);
		newConc_panel.add(lblDate);
		
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setBounds(22, 142, 46, 14);
		newConc_panel.add(lblPrice);
		
		JComboBox<String> stadCb = new JComboBox<String>();
				
		nameFld = new JTextField();
		nameFld.setBounds(104, 67, 116, 20);
		newConc_panel.add(nameFld);
		nameFld.setColumns(10);
		
		dayFld = new JTextField();
		dayFld.setBounds(104, 117, 25, 20);
		newConc_panel.add(dayFld);
		dayFld.setColumns(10);
		
		monthFld = new JTextField();
		monthFld.setColumns(10);
		monthFld.setBounds(139, 117, 25, 20);
		newConc_panel.add(monthFld);
		
		yearFld = new JTextField();
		yearFld.setColumns(10);
		yearFld.setBounds(174, 117, 46, 20);
		newConc_panel.add(yearFld);
		
		priceFld = new JTextField();
		priceFld.setBounds(104, 142, 25, 20);
		newConc_panel.add(priceFld);
		priceFld.setColumns(10);
				
		stadCb.setBounds(104, 91, 116, 20);
		stadCb.setModel(new DefaultComboBoxModel<String>(query.selectStadium()));
		newConc_panel.add(stadCb);
		
		JLabel lblAddTheNecessary = new JLabel("Add the necessary details for the new concert");
		lblAddTheNecessary.setBounds(23, 30, 324, 14);
		newConc_panel.add(lblAddTheNecessary);
		
		JPanel stats_panel = new JPanel();
		stats_panel.setLayout(null);
		panel.add(stats_panel, "stats_panel");
				
		JTextArea statsTxtFld = new JTextArea();
		statsTxtFld.setEditable(false);
		statsTxtFld.setBounds(29, 120, 367, 96);
		stats_panel.add(statsTxtFld);
		
		JComboBox<String> statsCb = new JComboBox<String>();
		statsCb.setModel(new DefaultComboBoxModel<String>(query.selectAllConcerts()));
		statsCb.setBounds(29, 53, 367, 23);
		stats_panel.add(statsCb);		
		
		statsCb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String[] concert = new String[2];
				concert  = statsCb.getSelectedItem().toString().split("\\)");
				int concert_id = Integer.parseInt(concert[0]);
				
				String stats = query.getStatistics(concert_id);
				statsTxtFld.setText(stats);				
			}
		});
		statsCb.setSelectedIndex(0);
		
		JButton button_3 = new JButton("Back");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    CardLayout card = (CardLayout)panel.getLayout();
			    card.show(panel, "admin_panel");
			}
		});
		button_3.setBounds(10, 227, 89, 23);
		stats_panel.add(button_3);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name,stadium;
				int day=0,month=0,year=0,price;								
				
				if ( checkDateTime(dayFld.getText(),monthFld.getText(),yearFld.getText(),priceFld.getText()) )
				{	

					
					name = nameFld.getText();
					stadium = stadCb.getSelectedItem().toString();				
					day = Integer.parseInt(dayFld.getText());
					month = Integer.parseInt(monthFld.getText());
					year = Integer.parseInt(yearFld.getText());
					price = Integer.parseInt(priceFld.getText());
					
					String date = "" + year + "-" + month + "-" + day; 
					query.insertConcert(name,stadium,date,price);
					userCb.setModel(new DefaultComboBoxModel<String>(query.selectAllConcerts()));
					statsCb.setModel(new DefaultComboBoxModel<String>(query.selectAllConcerts()));
					JOptionPane.showMessageDialog(frame,
						    "Concert added successfully.");
					
				}				
			}
		});
		btnAdd.setBounds(335, 227, 89, 23);
		newConc_panel.add(btnAdd);

		JLabel lblSelectTheConcert = new JLabel("Select the concert to view details and statistics");
		lblSelectTheConcert.setBounds(29, 28, 367, 14);
		stats_panel.add(lblSelectTheConcert);
			
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{home_panel, user_panel, admin_panel, newConc_panel}));
		
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{home_panel, user_panel, admin_panel}));
	}	
	
	@SuppressWarnings("unused")
	public boolean checkDateTime (String dayStr, String monthStr, String yearStr, String priceStr)
	{
		int day,month,year,price;
		try {
		    day = Integer.parseInt(dayStr);
		    month = Integer.parseInt(monthStr);
		    year = Integer.parseInt(yearStr);
		    price = Integer.parseInt(priceStr);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(frame,
				    "Please insert a NUMBER for date or price",
				    "Data error",
				    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if ( (day>31) ||(day<=0) || (month<=0) || (month>12) || (price<0) )
		{
			JOptionPane.showMessageDialog(frame,
				    "Please insert a correct date or price for concert",
				    "Data error",
				    JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else
		{
			return true;
		}
	}
}
