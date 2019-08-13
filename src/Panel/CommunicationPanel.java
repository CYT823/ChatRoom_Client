package Panel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class CommunicationPanel extends JPanel {
	JLabel name;
	JButton phoneBtn;
	JTextArea textArea;
	JTextArea typingBar;
	JButton plusBtn;
	JButton picBtn;
	JButton screenshotBtn;

	Socket client;
	PrintWriter writer;
	CommunicationPanel communicationPanel = this;

	public CommunicationPanel() {
		setLayout(null);

		name = new JLabel("樹懶");
		name.setBounds(30, 25, 300, 30);
		name.setFont(new java.awt.Font("新細明體", 1, 18));
		add(name);

		phoneBtn = new JButton();
		phoneBtn.setContentAreaFilled(false);
		phoneBtn.setBorderPainted(false);
		ImageIcon phone = new ImageIcon("icon/phone.png");
		phoneBtn.setIcon(phone);
		phoneBtn.setBounds(330, 25, 30, 30);
		add(phoneBtn);

		textArea = new JTextArea();
		textArea.setFont(new Font("新細明體", 0, 15));
		textArea.setForeground(Color.BLUE);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(25, 70, 340, 400);
		add(scrollPane);
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		typingBar = new JTextArea();
		typingBar.setBounds(120, 485, 240, 25);
		typingBar.setFont(new Font("新細明體", 0, 15));
		typingBar.setLineWrap(true);
		add(typingBar);

		plusBtn = new JButton();
		plusBtn.setContentAreaFilled(false);
		plusBtn.setBorderPainted(false);
		ImageIcon plus = new ImageIcon("icon/plus.png");
		plusBtn.setIcon(plus);
		plusBtn.setBounds(20, 480, 30, 30);
		add(plusBtn);

		picBtn = new JButton();
		picBtn.setContentAreaFilled(false);
		picBtn.setBorderPainted(false);
		ImageIcon pic = new ImageIcon("icon/picture.png");
		picBtn.setIcon(pic);
		picBtn.setBounds(52, 480, 30, 30);
		add(picBtn);

		screenshotBtn = new JButton();
		screenshotBtn.setContentAreaFilled(false);
		screenshotBtn.setBorderPainted(false);
		ImageIcon screenshot = new ImageIcon("icon/screenshot.png");
		screenshotBtn.setIcon(screenshot);
		screenshotBtn.setBounds(84, 480, 30, 30);
		add(screenshotBtn);

		initial();
	}

	private void initial() {
		client = new Socket();
		try {
			client.connect(new InetSocketAddress("140.123.101.67", 30000));

			writer = new PrintWriter(client.getOutputStream());
			new Receiver(client, communicationPanel).start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		typingBar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					writer.println(typingBar.getText());
					writer.flush();

					// 清空輸入
					typingBar.setText("");
				}
			}

		});

	}

}