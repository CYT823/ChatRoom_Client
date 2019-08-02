import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JFrame;

public class Client {

	public static void main(String[] args) {
		JFrame frame = new MainFrame();
		frame.setSize(400, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

//		Socket client = new Socket();
//		try {
//			client.connect(new InetSocketAddress("140.123.224.108", 30000));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		Socket client = new Socket();
		try {
			client.connect(new InetSocketAddress("127.0.0.1", 30000));

//			成功
//			OutputStream writer = client.getOutputStream();
//			writer.write("test".getBytes(), 0, 4);
//			writer.flush();
			
//			失敗
			PrintWriter writer = new PrintWriter(client.getOutputStream());
//			writer.write("testWriter");
			writer.println("test2");
			writer.flush();
			
//			DataOutputStream writer = new DataOutputStream(client.getOutputStream());
//			writer.writeUTF("testData");
//			writer.flush();
			
//			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
//			writer.write("TESTBuffered");
//			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
