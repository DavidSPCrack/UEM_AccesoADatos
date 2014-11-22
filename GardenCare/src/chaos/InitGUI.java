package chaos;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class InitGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton btnNewButton;
	private JList<String> listElements;
	private JSlider slider;
	private JLabel lblNewLabel;

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

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue_1);

		JLabel lblPerfectSun = new JLabel("Perfect sun");
		lblPerfectSun.setIcon(new ImageIcon(InitGUI.class.getResource("/res/Sunshine.png")));
		lblPerfectSun.setIconTextGap(3);
		toolBar.add(lblPerfectSun);

		Component horizontalStrut = Box.createHorizontalStrut(7);
		toolBar.add(horizontalStrut);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);

		Component horizontalStrut_1 = Box.createHorizontalStrut(7);
		toolBar.add(horizontalStrut_1);

		JLabel lblCloudy = new JLabel("Cloudy");
		lblCloudy.setIcon(new ImageIcon(InitGUI.class.getResource("/res/Cloudy.png")));
		lblCloudy.setIconTextGap(3);
		toolBar.add(lblCloudy);

		Component horizontalStrut_2 = Box.createHorizontalStrut(7);
		toolBar.add(horizontalStrut_2);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_1);

		Component horizontalStrut_3 = Box.createHorizontalStrut(7);
		toolBar.add(horizontalStrut_3);

		JLabel lblRains = new JLabel("Rains");
		lblRains.setIcon(new ImageIcon(InitGUI.class.getResource("/res/Cloud-Download.png")));
		lblRains.setIconTextGap(3);
		toolBar.add(lblRains);

		Component horizontalStrut_4 = Box.createHorizontalStrut(7);
		toolBar.add(horizontalStrut_4);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_2);

		Component horizontalStrut_5 = Box.createHorizontalStrut(7);
		toolBar.add(horizontalStrut_5);

		JLabel lblWindy = new JLabel("Windy");
		lblWindy.setIcon(new ImageIcon(InitGUI.class.getResource("/res/Refresh.png")));
		lblWindy.setIconTextGap(3);
		toolBar.add(lblWindy);

		Component horizontalGlue = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel_1.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		listElements = new JList<String>();
		listElements.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(listElements);
		listElements.setModel(new AbstractListModel<String>() {
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
		listElements.setSelectedIndex(0);

		btnNewButton = new JButton("");
		btnNewButton.setRolloverIcon(new ImageIcon(InitGUI.class.getResource("/res/Green.png")));
		btnNewButton.setToolTipText("Go?");
		btnNewButton.setMnemonic('O');
		btnNewButton.setMnemonic(KeyEvent.VK_ALT);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selOpt = JOptionPane.showConfirmDialog(null, "Are you 100% sure?", "Seleccione una opción", JOptionPane.YES_NO_CANCEL_OPTION);
				if (selOpt == JOptionPane.CANCEL_OPTION) {

				} else if (selOpt == JOptionPane.NO_OPTION) {
					lblNewLabel.setText("Dropping... nothing yet");
				} else if (selOpt == JOptionPane.YES_OPTION) {
					List<String> values = listElements.getSelectedValuesList();
					StringBuilder sb = new StringBuilder();
					sb.append("Dropping... ");
					for (String value : values) {
						sb.append(value);
						sb.append(" ");
					}
					sb.append("at ");
					sb.append(slider.getValue());
					sb.append("%");
					lblNewLabel.setText(sb.toString());
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(InitGUI.class.getResource("/res/Water-Drop.png")));
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		panel_1.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));

		slider = new JSlider();
		slider.setValue(25);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setPaintTrack(true);
		panel_5.add(slider);

		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));

		lblNewLabel = new JLabel("Dropping...");
		panel_6.add(lblNewLabel);
		lblNewLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

		JPanel panel_3 = new ImgPanel(InitGUI.class.getResource("/res/hierba-footer.png"));
		contentPane.add(panel_3);
	}

}
