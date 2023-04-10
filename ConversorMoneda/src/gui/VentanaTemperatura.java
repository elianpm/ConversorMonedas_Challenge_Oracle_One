package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Clases.Temperatura;
import Clases.TemperaturaAnadir;

public class VentanaTemperatura extends JFrame implements ActionListener,DocumentListener, ItemListener {
	
	private JPanel VentanaTemper;
	private JTextField txtConversion_2;
	private JTextField txtConversion_1;
	
	private JComboBox cbxTemperaturas;
	private JLabel lblTemperatura,lblTitulo,
				   lblTempOtros,lblTempLocal,lblValTemperCelsius,lblValTemperOtros;
	private JButton btnRegresarMenuPrincipal;
	JCheckBox chckbxInvertir;
	
	private boolean activarInvertir=false;
	Double selectTemper_bkp=1.0;
	
	TemperaturaAnadir tempAnadir = new TemperaturaAnadir();
	List<Temperatura> tempers = tempAnadir.getTemperaturas();
	String[] Divisas = tempers.stream()
			            .map(Temperatura::getNombre)
			            .toArray(String[]::new);

	/**
	 * Create the frame.
	 */
	public VentanaTemperatura() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		iniciarComponentesVCT();
		setTitle("Temperaturas");
		setLocationRelativeTo(VentanaTemper);
		VentanaTemper.setLayout(null);
	}

	private void iniciarComponentesVCT() {
		setBounds(100, 100, 420, 239);
		VentanaTemper = new JPanel();
		VentanaTemper.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(VentanaTemper);
		
		cbxTemperaturas = new JComboBox();
		cbxTemperaturas.setBounds(127, 36, 117, 26);
		cbxTemperaturas.setModel(new DefaultComboBoxModel(Divisas));
		cbxTemperaturas.setSelectedItem(cbxTemperaturas.getItemAt(0));
		cbxTemperaturas.addActionListener(this); 
		VentanaTemper.add(cbxTemperaturas);
				
		lblTemperatura = new JLabel("Elegir Moneda:");
		lblTemperatura.setBounds(33, 42, 84, 14);
		VentanaTemper.add(lblTemperatura);

		txtConversion_1 = new JTextField("");
		txtConversion_1.setColumns(10);
		txtConversion_1.setBounds(33, 84, 128, 26);
		txtConversion_1.getDocument().addDocumentListener (this); //Escuchara en tiempo real las acciones
		VentanaTemper.add(txtConversion_1);
		
		
		txtConversion_2 = new JTextField("");
		txtConversion_2.setColumns(10);
		txtConversion_2.setBounds(33, 126, 128, 26);
		VentanaTemper.add(txtConversion_2);
		
		lblTitulo = new JLabel("Elije la temperatura que deseas convertir");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 384, 14);
		VentanaTemper.add(lblTitulo);
		
		btnRegresarMenuPrincipal = new JButton("REGRESAR");
		btnRegresarMenuPrincipal.setBounds(286, 166, 108, 23);
		btnRegresarMenuPrincipal.addActionListener(this); 
		VentanaTemper.add(btnRegresarMenuPrincipal);
		
		lblTempOtros = new JLabel("FAHRENHEIT");
		lblTempOtros.setBounds(33, 112, 128, 14);
		VentanaTemper.add(lblTempOtros);
		
		lblTempLocal = new JLabel("CELSIUS");
		lblTempLocal.setBounds(33, 67, 128, 14);
		VentanaTemper.add(lblTempLocal);
		
		lblValTemperCelsius = new JLabel("-");
		lblValTemperCelsius.setHorizontalAlignment(SwingConstants.LEFT);
		lblValTemperCelsius.setBounds(253, 36, 141, 14);
		VentanaTemper.add(lblValTemperCelsius);
		
		lblValTemperOtros = new JLabel("-");
		lblValTemperOtros.setHorizontalAlignment(SwingConstants.LEFT);
		lblValTemperOtros.setBounds(253, 52, 141, 14);
		VentanaTemper.add(lblValTemperOtros);
		
		chckbxInvertir = new JCheckBox("Invertir");
		chckbxInvertir.setBackground(new Color(255, 255, 0));
		chckbxInvertir.setSelected(false);
		chckbxInvertir.setBounds(167, 108, 97, 23);
		chckbxInvertir.addItemListener(this); 
		VentanaTemper.add(chckbxInvertir);
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			 activarInvertir = true;
		 }else if (e.getStateChange() == ItemEvent.DESELECTED) {
			 activarInvertir = false;
		 }
		cbxTemperaturas.setSelectedItem(cbxTemperaturas.getSelectedItem());
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(btnRegresarMenuPrincipal == e.getSource()) {
			VentanaPrincipal VP = new VentanaPrincipal();
			VP.setVisible(true);
			this.dispose();
		}
		cambiarLabelValorTemperaturas();	
		
	}

	private void cambiarLabelValorTemperaturas() {
		String selectDivisa = (String) cbxTemperaturas.getSelectedItem();	
		Integer i=0;
		while(selectDivisa != tempers.get(i).getNombre())
			i++;
		ValordeCambio(tempers.get(i));
		actualizarLBLbox(tempers.get(i)); //label de los txtbox
		actualizarConversion(tempers.get(i).getValCamb());  //valor de conversion de los txtbox	
		
		//uso de valor mas adelante en el calculo de conversion de tiempo  real
		selectTemper_bkp = tempers.get(i).getValCamb();
		
	}
	
	private void ValordeCambio(Temperatura temperatura) {
		Double valorLocal,valorExtr;
		valorLocal = redondearDecimales(temperatura.getValCamb());
		valorExtr = redondearDecimales(1/temperatura.getValCamb());
		
		String labelLocal,labelExtrangero;
		labelLocal = "0 °C = "+ valorLocal.toString() + " "+ temperatura.getAbrev();
		labelExtrangero = "1 " + temperatura.getAbrev().toString() + " = " + 
						  valorExtr.toString() + " °C";
		
		lblValTemperCelsius.setText(labelLocal);
		lblValTemperOtros.setText(labelExtrangero);
	}
	
	private void actualizarLBLbox(Temperatura temperatura) {
		if(activarInvertir == false) {
			lblTempOtros.setText(temperatura.getNombre().toUpperCase());
			lblTempLocal.setText("CELSIUS");
		}else {
			lblTempLocal.setText(temperatura.getNombre().toUpperCase());
			lblTempOtros.setText("CELSIUS");
		}	
		
	}
	
	private void actualizarConversion(Double valTempSelect) {
		String texto = txtConversion_1.getText();
		
		txtConversion_1.setEditable(true);
		txtConversion_2.setText("");
		txtConversion_2.setEditable(false);
		
		texto = tempAnadir.validarNumero(texto);
		
		if(!texto.isEmpty() && !texto.equals("-")) {
			String resultado = tempAnadir.Convertir( (String)cbxTemperaturas.getSelectedItem(), texto, activarInvertir);
			txtConversion_2.setText(resultado);
		}
		
	}

	

	@Override
	public void insertUpdate(DocumentEvent e) {
		updateText();
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		updateText();
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		updateText();
		
	}
	
	private void updateText() {
		cbxTemperaturas.setSelectedItem(cbxTemperaturas.getSelectedItem()); //iniciar el tipo de cambio con el primer item de combobox
		
		String txtbox1 = txtConversion_1.getText();
		txtbox1 = tempAnadir.validarNumero(txtbox1);
		
		if(!txtbox1.isEmpty() && !txtbox1.equals("-")) {
		    try {
		        String resultado = tempAnadir.Convertir((String) cbxTemperaturas.getSelectedItem(), txtbox1, activarInvertir);
		        txtConversion_2.setText(resultado);
		    } catch (Exception ex) {
		        txtConversion_2.setText("");
		    }
		}
		
	}

	private void convertirTemperatura(String texto, Double valTempSelect) {
		Double resultado,resultRedon,valorTexto;
		valorTexto = Double.parseDouble(texto);
		if(activarInvertir==false) {
			resultado = valorTexto * valTempSelect;
		}else {
			resultado = valorTexto / valTempSelect;
		}
		resultRedon = redondearDecimales(resultado);
		txtConversion_2.setText(resultRedon.toString());
		
	}

	private Double redondearDecimales(Double valCamb) {
		DecimalFormat df = new DecimalFormat("#.####");
        String formatted = df.format(valCamb);
        return Double.parseDouble(formatted);
	}

	

}
