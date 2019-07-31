import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		Socket client = new Socket();
		try {
			client.connect(new InetSocketAddress("140.123.224.108", 30000));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
