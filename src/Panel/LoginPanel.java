package Panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	JTextField account;
	JPasswordField password;
	JButton registerBtn;
	JButton loginBtn;
	JButton forgetPassBtn;

	Socket client;
	PrintWriter writer;
	
	public LoginPanel() {
		// 取消FlowLayout
		setLayout(null);

		JLabel accountLabel = new JLabel("帳號");
		accountLabel.setBounds(50, 180, 30, 30);
		add(accountLabel);

		account = new JTextField();
		account.setBounds(100, 180, 220, 30);
		add(account);

		JLabel passwordLabel = new JLabel("密碼");
		passwordLabel.setBounds(50, 250, 30, 30);
		add(passwordLabel);

		password = new JPasswordField();
		password.setBounds(100, 250, 220, 30);
		add(password);

		registerBtn = new JButton("註冊");
		registerBtn.setBounds(70, 350, 100, 30);
		add(registerBtn);

		loginBtn = new JButton("登入");
		loginBtn.setBounds(220, 350, 100, 30);
		add(loginBtn);

		forgetPassBtn = new JButton("忘記密碼?");
		forgetPassBtn.setContentAreaFilled(false);
		forgetPassBtn.setBorderPainted(false);
		forgetPassBtn.setBounds(200, 400, 100, 30);
		add(forgetPassBtn);

		initial();
	}

	void initial() {
		client = new Socket();
		try {
			client.connect(new InetSocketAddress("140.123.224.108", 30000));
			writer = new PrintWriter(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String account_Str = account.getText();
				String password_Str = new String(password.getPassword());

				// 這邊可以再連接 Server，請 Server 確認帳密對不對

				if (account_Str.equals("123")) {
					if (password_Str.contentEquals("123")) {
					}
				}
			}
		});
	}
}
