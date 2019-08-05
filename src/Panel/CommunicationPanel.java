package Panel;

import javax.swing.JButton;
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
	JTextField typingBar;
	JButton enterBtn;
	JButton micBtn;
	JButton picBtn;
	JButton phoneBtn;
	JTextArea textArea;

	Socket client;
	PrintWriter writer;
	CommunicationPanel communicationPanel = this;
	
	public CommunicationPanel() {
		setLayout(null);

		typingBar = new JTextField();
		typingBar.setBounds(10, 480, 300, 30);
		add(typingBar);

		enterBtn = new JButton("發送");
		enterBtn.setBounds(310, 480, 70, 30);
		add(enterBtn);

		micBtn = new JButton("錄音");
		micBtn.setBounds(10, 520, 70, 30);
		add(micBtn);

		picBtn = new JButton("照片");
		picBtn.setBounds(110, 520, 70, 30);
		add(picBtn);

		phoneBtn = new JButton("通話");
		phoneBtn.setBounds(205, 520, 70, 30);
		add(phoneBtn);

		textArea = new JTextArea(100, 10);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(25, 50, 350, 400);
		add(scrollPane);

		initial();
	}

	private void initial() {
		client = new Socket();
		try {
			client.connect(new InetSocketAddress("140.123.224.108", 30000));
			writer = new PrintWriter(client.getOutputStream());
			new Receiver(client, communicationPanel).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		enterBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writer.println(typingBar.getText());
				writer.flush();
				
				//清空輸入
				typingBar.setText("");
			}
		});
	}
}
