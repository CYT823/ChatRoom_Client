import javax.swing.JFrame;
import Panel.CommunicationPanel;
import Panel.LoginPanel;

public class MainFrame extends JFrame {
	int state;

	MainFrame() {
		state = 0; // 這邊到時候可以從資料庫取
		
		switch (state) {
		case 0:
			add(new LoginPanel());
			break;
		case 1:
			add(new CommunicationPanel());
			break;
		}

	}
}