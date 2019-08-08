package Panel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CommunicationPanel extends JPanel {
	JButton phoneBtn;
	JTextArea textArea;
	JTextField typingBar;
	JButton plusBtn;
	JButton picBtn;
	JButton screenshotBtn;

	Socket client;
	PrintWriter writer;
	CommunicationPanel communicationPanel = this;
	
	public CommunicationPanel() {
		setLayout(null);
		
		JLabel name = new JLabel();
		name.setBounds(25,25,300,30);
		add(name);
		
		phoneBtn = new JButton();
		phoneBtn.setContentAreaFilled(false);
		phoneBtn.setBorderPainted(false);
		ImageIcon phone = new ImageIcon("phone.png");
		phoneBtn.setIcon(phone);
		phoneBtn.setBounds(335, 25, 30, 30);
		add(phoneBtn);
		
		textArea = new JTextArea(100,10);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(25, 70, 340, 400);
		add(scrollPane);
		
		typingBar = new JTextField();
		typingBar.setBounds(25, 475, 340, 25);
		add(typingBar);

		plusBtn = new JButton();
		plusBtn.setContentAreaFilled(false);
		plusBtn.setBorderPainted(false);
		ImageIcon plus = new ImageIcon("plus.png");
		plusBtn.setIcon(plus);
		plusBtn.setBounds(25, 505, 25, 25);
		add(plusBtn);

		picBtn = new JButton();
		picBtn.setContentAreaFilled(false);
		picBtn.setBorderPainted(false);
		ImageIcon pic = new ImageIcon("picture.png");
		picBtn.setIcon(pic);
		picBtn.setBounds(75, 505, 30, 30);
		add(picBtn);

		screenshotBtn = new JButton();
		screenshotBtn.setContentAreaFilled(false);
		screenshotBtn.setBorderPainted(false);
		ImageIcon screenshot = new ImageIcon("screenshot.png");
		screenshotBtn.setIcon(screenshot);
		screenshotBtn.setBounds(125, 505, 30, 30);
		add(screenshotBtn);

		

		initial();
	}

	private void initial() {
		client = new Socket();
/*		try {
			client.connect(new InetSocketAddress("140.123.224.108", 30000));
			writer = new PrintWriter(client.getOutputStream());
			new Receiver(client, communicationPanel).start();
		} catch (IOException e) {
			e.printStackTrace(); */
		}
		
/*		enterBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writer.println(typingBar.getText());
				writer.flush();
				
				//清空輸入
				typingBar.setText("");

				
			}
		});
*/
	}