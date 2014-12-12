package chaos;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**
 * Principal class of garden care GardenCare
 * 
 * @author david.sancho
 * @version 0.5
 * @see ImgPanel dinamyc redimension of image
 *
 */
public class InitGUI extends JFrame {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Principal Content Pane
	 */
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 *            Execution parameters
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
		setTitle("Estrabismificador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		contentPane.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.X_AXIS));
		
		JPanel panel_7 = new JPanel();
		panel_8.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));

		JPanel panel_4 = new JPanel();
		panel_7.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 1, 0, 0));

		JToolBar toolBar = new JToolBar();
		toolBar.setOrientation(SwingConstants.VERTICAL);
		panel_4.add(toolBar);
		toolBar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		toolBar.setAlignmentY(Component.CENTER_ALIGNMENT);

		JLabel lblPerfectSun = new JLabel("Games");
		lblPerfectSun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(contentPane, "Beutiful sunshine!");
			}
		});
		
		Component verticalGlue = Box.createVerticalGlue();
		toolBar.add(verticalGlue);
		lblPerfectSun.setIcon(new ImageIcon(InitGUI.class.getResource("/res/Controller.png")));
		lblPerfectSun.setIconTextGap(3);
		toolBar.add(lblPerfectSun);
		
		Component verticalStrut = Box.createVerticalStrut(7);
		toolBar.add(verticalStrut);

		JSeparator separator = new JSeparator();
		toolBar.add(separator);

		JLabel lblCloudy = new JLabel("Movies");
		lblCloudy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(contentPane, "Owwww its cold");
			}
		});
		
		Component verticalStrut_1 = Box.createVerticalStrut(7);
		toolBar.add(verticalStrut_1);
		lblCloudy.setIcon(new ImageIcon(InitGUI.class.getResource("/res/Digital-Video-Camera.png")));
		lblCloudy.setIconTextGap(3);
		toolBar.add(lblCloudy);
		
		Component verticalStrut_2 = Box.createVerticalStrut(7);
		toolBar.add(verticalStrut_2);

		JSeparator separator_1 = new JSeparator();
		toolBar.add(separator_1);

		JLabel lblRains = new JLabel("Music");
		lblRains.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(contentPane, "Im wet");
			}
		});
		
		Component verticalStrut_3 = Box.createVerticalStrut(7);
		toolBar.add(verticalStrut_3);
		lblRains.setIcon(new ImageIcon(InitGUI.class.getResource("/res/Music-note-2.png")));
		lblRains.setIconTextGap(3);
		toolBar.add(lblRains);
		
		Component verticalStrut_4 = Box.createVerticalStrut(7);
		toolBar.add(verticalStrut_4);

		JSeparator separator_2 = new JSeparator();
		toolBar.add(separator_2);

		JLabel lblWindy = new JLabel("Settings");
		lblWindy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(contentPane, "Wiiiiiiiind");
			}
		});
		
		Component verticalStrut_5 = Box.createVerticalStrut(7);
		toolBar.add(verticalStrut_5);
		lblWindy.setIcon(new ImageIcon(InitGUI.class.getResource("/res/Preferences.png")));
		lblWindy.setIconTextGap(3);
		toolBar.add(lblWindy);
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		toolBar.add(verticalGlue_1);

		JPanel panel_1 = new JPanel();
		panel_8.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{"john", "ee", null, null},
						{null, "e", "ee", null},
						{null, "e", null, null},
						{"ae", null, "ee", null},
						{null, null, null, null},
					},
					new String[] {
						"New column", "New column", "New column", "New column"
					}
				));
				scrollPane.setViewportView(table);
				
				JPanel panel = new JPanel();
				panel_1.add(panel);
				panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
				
				JPanel panel_2 = new JPanel();
				panel.add(panel_2);
				panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
				
				JPanel panel_5 = new JPanel();
				panel_5.setAlignmentX(Component.RIGHT_ALIGNMENT);
				panel_2.add(panel_5);
				panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
				
				JRadioButton rdbtnNewRadioButton = new JRadioButton("mami");
				panel_5.add(rdbtnNewRadioButton);
				
				JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("papi");
				panel_5.add(rdbtnNewRadioButton_3);
				
				JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("jander");
				panel_5.add(rdbtnNewRadioButton_1);
				
				JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("clander");
				panel_5.add(rdbtnNewRadioButton_2);
				
				JPanel panel_9 = new JPanel();
				panel_2.add(panel_9);
				panel_9.setLayout(new BorderLayout(0, 0));
				
				JComboBox comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] {"one", "two", "three", "one hundred"}));
				panel_9.add(comboBox, BorderLayout.NORTH);
				
				JPanel panel_3 = new JPanel();
				panel.add(panel_3);
				panel_3.setLayout(new BorderLayout(0, 0));
				
				JPanel panel_6 = new JPanel();
				panel_3.add(panel_6, BorderLayout.SOUTH);
				panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
				
				Component horizontalGlue = Box.createHorizontalGlue();
				panel_6.add(horizontalGlue);
				
				JButton btnNewButton_1 = new JButton("New button");
				panel_6.add(btnNewButton_1);
				
				JButton btnNewButton = new JButton("New button");
				panel_6.add(btnNewButton);
	}

}
