import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI implements ActionListener{

	private static JPanel panel;
	private static JFrame frame;
	private static JButton login;
	private static JLabel userLabel;
	private static JLabel passLabel;
	private static JTextField userText;
	private static JPasswordField passText;
	
	public static void main(String[] args) {
		
		panel =  new JPanel();
		frame = new JFrame();
		
		frame.setSize(300, 175);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		userLabel = new JLabel("Username: ");
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);
		
		userText = new JTextField(20);
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);
		
		passLabel = new JLabel("Password: ");
		passLabel.setBounds(10, 50, 80, 25);
		panel.add(passLabel);
		
		passText = new JPasswordField();
		passText.setBounds(100, 50, 165, 25);
		panel.add(passText);
		
		login = new JButton("Login");
		login.setBounds(10, 80, 80, 25);
		login.addActionListener(new GUI());
		panel.add(login);
		
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String user = userText.getText();
		String password = passText.getText();
		
		if (user.equals("e") && password.equals("s")) {
			JPanel panelTwo = new JPanel();
			
			frame.getContentPane().removeAll();
			frame.repaint();
			frame.setSize(300, 175);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(panelTwo);
			
			panelTwo.setLayout(null);
			JLabel secondScreen = new JLabel("Login successful!");
			secondScreen.setBounds(10, 20, 80, 25);
			panelTwo.add(secondScreen);
		}
		System.out.println(user + " - " + password);
	}

}
