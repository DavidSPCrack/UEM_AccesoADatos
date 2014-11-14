package chaos;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JSlider;

public class InitGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitGUI frame = new InitGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InitGUI() {
		setTitle("Garden Care");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 1, 0, 0));

		JToolBar toolBar = new JToolBar();
		panel_4.add(toolBar);
		toolBar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		toolBar.setAlignmentY(Component.CENTER_ALIGNMENT);

		JLabel lblPerfectSun = new JLabel("Perfect sun");
		lblPerfectSun.setIcon(new ImageIcon(InitGUI.class.getResource("/res/Sunshine.png")));
		lblPerfectSun.setIconTextGap(3);
		toolBar.add(lblPerfectSun);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);

		JLabel lblCloudy = new JLabel("Cloudy");
		lblCloudy.setIcon(new ImageIcon(InitGUI.class.getResource("/res/Cloudy.png")));
		lblCloudy.setIconTextGap(3);
		toolBar.add(lblCloudy);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_1);

		JLabel lblRains = new JLabel("Rains");
		lblRains.setIcon(new ImageIcon(InitGUI.class.getResource("/res/Cloud-Download.png")));
		lblRains.setIconTextGap(3);
		toolBar.add(lblRains);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_2);

		JLabel lblWindy = new JLabel("Windy");
		lblWindy.setIcon(new ImageIcon(InitGUI.class.getResource("/res/Refresh.png")));
		lblWindy.setIconTextGap(3);
		toolBar.add(lblWindy);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel_1.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JList<String> list = new JList<String>();
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(list);
		list.setModel(new AbstractListModel<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] values = new String[] { "Water", "Petrol", "Milk" };

			public int getSize() {
				return values.length;
			}

			public String getElementAt(int index) {
				return values[index];
			}
		});

		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(InitGUI.class.getResource("/res/Water-Drop.png")));
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JSlider slider = new JSlider();
		panel_5.add(slider);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Dropping...");
		panel_6.add(lblNewLabel);
		lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(InitGUI.class.getResource("/res/hierba-footer.png")));
		panel_3.add(lblNewLabel_1);
	}

}
