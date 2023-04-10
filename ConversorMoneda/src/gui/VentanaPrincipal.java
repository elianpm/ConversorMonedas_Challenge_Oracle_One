package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class VentanaPrincipal extends JFrame implements ActionListener{

	private JPanel Ventana_1;
	
	private JLabel lblTituloMenu;
	private JComboBox cbxOpcionesMenu;
	
	String[] OpcionesMenu1 = {"Conversion de Moneda", "Conversor de Temperatura"}; //opciones del combobox

	/**
	 * Create the frame.VENTANA_1
	 */
	public VentanaPrincipal() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/org/eclipse/jface/dialogs/images/title_banner.png")));
		setTitle("MENU");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		iniciarComponentes();
		
		setLocationRelativeTo(Ventana_1);
		
	}

	private void iniciarComponentes() {
		
		setBounds(100, 100, 327, 164);
		Ventana_1 = new JPanel();
		Ventana_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Ventana_1);
		Ventana_1.setLayout(null);
		
		lblTituloMenu = new JLabel("Seleccione una opción de conversión");
		lblTituloMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloMenu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTituloMenu.setBounds(10, 27, 291, 14);
		Ventana_1.add(lblTituloMenu);
		
		cbxOpcionesMenu = new JComboBox();
		cbxOpcionesMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		//String[] OpcionesMenu1 =  {"Conversion de Moneda", "Conversor de Temperatura","otro"};
		cbxOpcionesMenu.setModel(new DefaultComboBoxModel(OpcionesMenu1));
		cbxOpcionesMenu.setBounds(10, 49, 291, 32);
		cbxOpcionesMenu.addActionListener(this); 
		Ventana_1.add(cbxOpcionesMenu);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String selectOptionMenu = (String) cbxOpcionesMenu.getSelectedItem(); 
		if( selectOptionMenu == OpcionesMenu1[0] ) {
			VentanaConvDivisas VCD = new VentanaConvDivisas();
			VCD.setVisible(true);
			this.dispose();
			
		}
		else if(selectOptionMenu == OpcionesMenu1[1]) {
			VentanaTemperatura VCT = new VentanaTemperatura();
			VCT.setVisible(true);
			this.dispose();
			//JOptionPane.showMessageDialog(null, "Seleccionaste: " + selectOptionMenu);
		}
			
	}
}























