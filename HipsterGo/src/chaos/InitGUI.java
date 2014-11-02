package chaos;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class InitGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JCheckBox swGafasPasta;
	private JCheckBox swBarbita;
	private JCheckBox swTupe;
	private JCheckBox swBotonCuello;
	private JRadioButton rbHombre;
	private JRadioButton rbMujer;
	private JRadioButton rbOtros;
	private JTextArea txtComentario;
	private JTextArea txtResult;
	private JLabel nonHipster2;
	private JLabel nonHipster;
	private JLabel imgBigote;
	private JLabel imgTupe;
	private JLabel imgGafas;
	private JLabel imgBotonCuello;

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
		setTitle("Go Hipster Go");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 480);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);

		JMenuItem mntmAccion = new JMenuItem("Accion");
		mnArchivo.add(mntmAccion);

		JMenu mnEditar = new JMenu("Editar");
		menuBar.add(mnEditar);

		JMenuItem mntmCopiar = new JMenuItem("Copiar");
		mnEditar.add(mntmCopiar);

		JMenuItem mntmPegar = new JMenuItem("Pegar");
		mnEditar.add(mntmPegar);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mnAyuda.add(mntmAcercaDe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2, 0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Features", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));

		swGafasPasta = new JCheckBox("Gafas pasta");
		panel_4.add(swGafasPasta);

		swBarbita = new JCheckBox("Barbita");
		panel_4.add(swBarbita);

		swTupe = new JCheckBox("Tupe");
		panel_4.add(swTupe);

		swBotonCuello = new JCheckBox("Boton cuello");
		panel_4.add(swBotonCuello);

		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		JPanel panel_2 = new JPanel();
		panel_2.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_5.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

		rbHombre = new JRadioButton("Hombre");
		rbHombre.setSelected(true);
		panel_2.add(rbHombre);
		buttonGroup.add(rbHombre);

		rbMujer = new JRadioButton("Mujer");
		panel_2.add(rbMujer);
		buttonGroup.add(rbMujer);

		rbOtros = new JRadioButton("Otros");
		panel_2.add(rbOtros);
		buttonGroup.add(rbOtros);

		JPanel panel_1 = new JPanel();
		panel_5.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		JButton button = new JButton("");
		panel_1.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				executeResult();
			}
		});
		button.setIcon(new ImageIcon(InitGUI.class.getResource("/res/Annoying-Hipster.png")));
		button.setMargin(new Insets(0, 0, 0, 0));

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetDatos();
			}
		});
		button_1.setIcon(new ImageIcon(InitGUI.class.getResource("/res/Male-User.png")));
		button_1.setMargin(new Insets(0, 0, 0, 0));
		panel_1.add(button_1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Hipster", null, panel_3, null);
		panel_3.setLayout(null);

		nonHipster = new JLabel("");
		nonHipster.setBounds(87, 37, 100, 100);
		nonHipster.setIcon(new ImageIcon(InitGUI.class.getResource("/res/cara_simple.png")));
		panel_3.add(nonHipster);

		imgBigote = new JLabel("");
		imgBigote.setIcon(new ImageIcon(InitGUI.class.getResource("/res/bigote.png")));
		imgBigote.setBounds(87, 37, 100, 100);
		imgBigote.setVisible(false);
		panel_3.add(imgBigote);

		imgTupe = new JLabel("");
		imgTupe.setIcon(new ImageIcon(InitGUI.class.getResource("/res/tupe.png")));
		imgTupe.setBounds(87, 37, 100, 100);
		imgTupe.setVisible(false);
		panel_3.add(imgTupe);

		imgGafas = new JLabel("");
		imgGafas.setIcon(new ImageIcon(InitGUI.class.getResource("/res/gafas.png")));
		imgGafas.setBounds(87, 37, 100, 100);
		imgGafas.setVisible(false);
		panel_3.add(imgGafas);

		imgBotonCuello = new JLabel("");
		imgBotonCuello.setIcon(new ImageIcon(InitGUI.class.getResource("/res/boton_cuello.png")));
		imgBotonCuello.setBounds(87, 37, 100, 100);
		imgBotonCuello.setVisible(false);
		panel_3.add(imgBotonCuello);

		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Non-Hipster", null, panel_6, null);
		panel_6.setLayout(null);

		nonHipster2 = new JLabel("");
		nonHipster2.setBounds(87, 37, 100, 100);
		nonHipster2.setIcon(new ImageIcon(InitGUI.class.getResource("/res/cara_simple.png")));
		panel_6.add(nonHipster2);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);

		txtComentario = new JTextArea();
		txtComentario.setText("introduca un comentario...");
		txtComentario.setRows(20);
		txtComentario.setColumns(40);
		scrollPane.setViewportView(txtComentario);

		JScrollPane scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1);

		txtResult = new JTextArea();
		txtResult.setBackground(Color.GRAY);
		txtResult.setEditable(false);
		scrollPane_1.setViewportView(txtResult);
	}

	public void executeResult() {
		boolean swGafasPasta = this.swGafasPasta.isSelected();
		boolean swBarbita = this.swBarbita.isSelected();
		boolean swTupe = this.swTupe.isSelected();
		boolean swBotonCuello = this.swBotonCuello.isSelected();

		boolean swHombre = this.rbHombre.isSelected();
		boolean swMujer = this.rbMujer.isSelected();
		boolean swOtros = this.rbOtros.isSelected();

		String comentario = this.txtComentario.getText();

		StringBuilder sb = new StringBuilder();
		if (swGafasPasta)
			sb.append("Con su gafas pasta\r\n");
		if (swBarbita)
			sb.append("Con su barbita\r\n");
		if (swTupe)
			sb.append("Con su tupe\r\n");
		if (swBotonCuello)
			sb.append("Con su boton cuello\r\n");

		sb.append("\r\n");

		if (swHombre)
			sb.append("Es un hombre\r\n");
		else if (swMujer)
			sb.append("Es una mujer\r\n");
		else if (swOtros)
			sb.append("Sexualmente diverso\r\n");

		sb.append("\r\n");

		sb.append(comentario);

		this.txtResult.setText(sb.toString());

		visualizarImagenes(swGafasPasta, swBarbita, swTupe, swBotonCuello);
	}

	public void visualizarImagenes(boolean swGafasPasta, boolean swBarbita, boolean swTupe, boolean swBotonCuello) {
		this.imgGafas.setVisible(swGafasPasta);
		this.imgBigote.setVisible(swBarbita);
		this.imgTupe.setVisible(swTupe);
		this.imgBotonCuello.setVisible(swBotonCuello);
	}

	public void resetDatos() {
		this.swGafasPasta.setSelected(false);
		this.swBarbita.setSelected(false);
		this.swTupe.setSelected(false);
		this.swBotonCuello.setSelected(false);

		this.rbHombre.setSelected(false);
		this.rbMujer.setSelected(false);
		this.rbOtros.setSelected(false);

		this.txtComentario.setText("introduca un comentario...");
		this.txtResult.setText("");

		visualizarImagenes(false, false, false, false);
	}
}
