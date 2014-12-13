package chaos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Principal class of Bikelane
 * 
 * @author david.sancho
 * @version 0.5
 * @see ImgPanel dinamyc redimension of image
 *
 */
public class InitGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel action1;
	private JLabel action2;
	private JTable table;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel action3;
	private JLabel action4;
	private JLabel action5;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 *            initial arguments
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 500);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Buy");
		menuBar.add(mnNewMenu);

		JMenuItem mntmSave = new JMenuItem("Bonus");
		mnNewMenu.add(mntmSave);

		JMenuItem mntmInfo = new JMenuItem("Single day");
		mnNewMenu.add(mntmInfo);

		JMenu mnExplore = new JMenu("Explore");
		menuBar.add(mnExplore);

		JMenu mnInfo = new JMenu("Info");
		menuBar.add(mnInfo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setMinimumSize(new Dimension(135, 10));
		panel_3.setMaximumSize(new Dimension(135, 32767));

		JButton button = new JButton("");
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deselectLabels();
				buttonGroup.clearSelection();
			}
		});
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setToolTipText("Back home");
		button.setMnemonic('H');
		button.setMnemonic(KeyEvent.VK_ALT);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setIcon(new ImageIcon(InitGUI.class.getResource("/res/blackbike.png")));

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Choose day", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JRadioButton rdbtnMonday = new JRadioButton("Monday");
		buttonGroup.add(rdbtnMonday);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Tuesday");
		buttonGroup.add(rdbtnNewRadioButton);

		JRadioButton rdbtnWednesday = new JRadioButton("Wednesday");
		buttonGroup.add(rdbtnWednesday);

		JRadioButton rdbtnThurday = new JRadioButton("Thurday");
		buttonGroup.add(rdbtnThurday);

		JRadioButton rdbtnFriday = new JRadioButton("Friday");
		buttonGroup.add(rdbtnFriday);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_4
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_panel_4.createParallelGroup(Alignment.LEADING).addComponent(rdbtnMonday).addComponent(rdbtnNewRadioButton)
										.addComponent(rdbtnWednesday).addComponent(rdbtnThurday).addComponent(rdbtnFriday)).addContainerGap(72, Short.MAX_VALUE)));
		gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_4.createSequentialGroup().addComponent(rdbtnMonday).addPreferredGap(ComponentPlacement.RELATED).addComponent(rdbtnNewRadioButton)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(rdbtnWednesday).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(rdbtnThurday).addPreferredGap(ComponentPlacement.RELATED).addComponent(rdbtnFriday)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_4.setLayout(gl_panel_4);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(new BorderLayout(0, 0));

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Go", "Return", "Go&Return" }));
		panel_5.add(comboBox, BorderLayout.NORTH);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
				.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE).addComponent(button));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel_3.createSequentialGroup().addComponent(button).addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)));
		panel_3.setLayout(gl_panel_3);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
				{ null, null, null }, }, new String[] { "Parking 1", "Parking 2", "Parking 3" }));
		scrollPane.setViewportView(table);

		JPanel panel_6 = new ImgPanel(InitGUI.class.getResource("/res/map.png"));
		panel_2.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);

		action1 = new JLabel("");
		action1.setHorizontalAlignment(SwingConstants.CENTER);
		action1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		action1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deselectLabels();
				selectLabel(action1);
			}
		});
		panel_1.setLayout(new GridLayout(0, 5, 0, 0));
		panel_1.add(action1);
		action1.setOpaque(true);
		action1.setBackground(Color.BLUE);
		action1.setIcon(new ImageIcon(InitGUI.class.getResource("/res/fork.png")));

		action2 = new JLabel("");
		action2.setHorizontalAlignment(SwingConstants.CENTER);
		action2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		action2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deselectLabels();
				selectLabel(action2);
			}
		});
		panel_1.add(action2);
		action2.setOpaque(true);
		action2.setBackground(Color.BLUE);
		action2.setIcon(new ImageIcon(InitGUI.class.getResource("/res/key.png")));

		action3 = new JLabel("");
		action3.setHorizontalAlignment(SwingConstants.CENTER);
		action3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		action3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deselectLabels();
				selectLabel(action3);
			}
		});
		panel_1.add(action3);
		action3.setOpaque(true);
		action3.setBackground(Color.BLUE);
		action3.setIcon(new ImageIcon(InitGUI.class.getResource("/res/pin.png")));

		action4 = new JLabel("");
		action4.setHorizontalAlignment(SwingConstants.CENTER);
		action4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		action4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deselectLabels();
				selectLabel(action4);
			}
		});
		panel_1.add(action4);
		action4.setOpaque(true);
		action4.setBackground(Color.BLUE);
		action4.setIcon(new ImageIcon(InitGUI.class.getResource("/res/tag.png")));

		action5 = new JLabel("");
		action5.setHorizontalAlignment(SwingConstants.CENTER);
		action5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		action5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deselectLabels();
				selectLabel(action5);
			}
		});
		panel_1.add(action5);
		action5.setOpaque(true);
		action5.setBackground(Color.BLUE);
		action5.setIcon(new ImageIcon(InitGUI.class.getResource("/res/paper-roll.png")));
	}

	public void deselectLabels() {
		action1.setBackground(Color.BLUE);
		action2.setBackground(Color.BLUE);
		action3.setBackground(Color.BLUE);
		action4.setBackground(Color.BLUE);
		action5.setBackground(Color.BLUE);
	}

	public void selectLabel(JLabel label) {
		Color color = new Color(250, 100, 0);
		label.setBackground(color);
	}
}
