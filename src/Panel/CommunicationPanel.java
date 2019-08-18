package Panel;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultCaret;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

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

		name = new JLabel("(名字)");
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
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane scrollPane1 = new JScrollPane(textArea);
		scrollPane1.setBounds(25, 70, 340, 400);
		add(scrollPane1);

		typingBar = new JTextArea();
		typingBar.setFont(new Font("新細明體", 0, 15));
		typingBar.setLineWrap(true);
		InputMap inputMap = typingBar.getInputMap(WHEN_FOCUSED);
		KeyStroke enterStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		inputMap.put(enterStroke, enterStroke.toString());
		JScrollPane scrollPane2 = new JScrollPane(typingBar);
		scrollPane2.setBounds(120, 480, 240, 30);
		add(scrollPane2);

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

		final Set<Integer> pressed = new HashSet<Integer>();
		typingBar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				pressed.add(e.getKeyCode());

				if (pressed.size() == 2 && pressed.contains(KeyEvent.VK_ENTER) && pressed.contains(KeyEvent.VK_SHIFT)) {
					typingBar.append("\n");
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// if there is message on "textArea", give it a new line 
					if (textArea.getText().length() > 0) {
						textArea.append("\n");
					}
					textArea.append(typingBar.getText());
					
					// send by socket
					writer.println(typingBar.getText());
					writer.flush();

					// clear the typing area
					typingBar.setText("");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				pressed.remove(e.getKeyCode());
			}
		});

	}

}