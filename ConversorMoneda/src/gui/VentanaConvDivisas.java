package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.felix.resolver.util.ArrayMap;

import Clases.Moneda;
import Clases.MonedaFunciones;

import java.text.DecimalFormat;

import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.DropMode;

public class VentanaConvDivisas extends JFrame implements ActionListener,DocumentListener, ItemListener{

	private JPanel VentanaDivisas;
	private JTextField txtConversion_2;
	private JTextField txtConversion_1;
	
	private JComboBox cbxDivisas;
	private JLabel lblDivisa,lblTitulo,
				   lblMonExtrangera,lblMonLocal,lblValorCambioLocal,
				   tasaa,convertirr,consolaa,lblValorCambioExtrangero;
	private JButton btnRegresarMenuPrincipal;
	JCheckBox chckbxInvertir;
	
	private boolean activarInvertir=false;
	Double selectDivisa_bkp=1.0;
	
	MonedaFunciones mf = new MonedaFunciones();
	List<Moneda> coins = mf.getDivisas();
	String[] Divisas = coins.stream()
			            .map(Moneda::getNombre)
			            .toArray(String[]::new);
	
	
	
	/*
	//Monedas
	Moneda Dolar = new Moneda("Dolar","USD",0.26533381);
	Moneda Euro = new Moneda("Euro","EUR",0.24349218);
	Moneda Libra = new Moneda("Libra","GBP",0.2137368);
	Moneda Yen = new Moneda("Yen Japones","JPY",35.208536);
	Moneda WonCoreano = new Moneda("Won Surcoreano","KRW",348.54524);
	
	Divisas = { Dolar.getNombre(), Euro.getNombre(),
			Libra.getNombre(), Yen.getNombre(),
			WonCoreano.getNombre()};
	 * */
			
	/**
	 * Create the frame.
	 */
	public VentanaConvDivisas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		iniciarComponentesVCD();
		setTitle("Monedas");
		setLocationRelativeTo(VentanaDivisas);
		VentanaDivisas.setLayout(null);
		
		/////////
		tasaa = new JLabel("New label");
		tasaa.setBounds(10, 170, 46, 14);
		VentanaDivisas.add(tasaa);
		
		convertirr = new JLabel("New label");
		convertirr.setBounds(71, 170, 46, 14);
		VentanaDivisas.add(convertirr);
		
		consolaa = new JLabel("New label");
		consolaa.setBounds(158, 170, 46, 14);
		VentanaDivisas.add(consolaa);
		
		
	}
	
	private void iniciarComponentesVCD() {
		setBounds(100, 100, 420, 239);
		VentanaDivisas = new JPanel();
		VentanaDivisas.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(VentanaDivisas);
		
		cbxDivisas = new JComboBox();
		cbxDivisas.setBounds(127, 36, 117, 26);
		cbxDivisas.setModel(new DefaultComboBoxModel(Divisas));
		cbxDivisas.setSelectedItem(cbxDivisas.getItemAt(0));
		cbxDivisas.addActionListener(this); 
		VentanaDivisas.add(cbxDivisas);
				
		lblDivisa = new JLabel("Elegir Moneda:");
		lblDivisa.setBounds(33, 42, 84, 14);
		VentanaDivisas.add(lblDivisa);

		txtConversion_1 = new JTextField("");
		txtConversion_1.setColumns(10);
		txtConversion_1.setBounds(33, 84, 128, 26);
		txtConversion_1.getDocument().addDocumentListener (this); //Escuchara en tiempo real las acciones
		VentanaDivisas.add(txtConversion_1);
		
		
		txtConversion_2 = new JTextField("");
		txtConversion_2.setColumns(10);
		txtConversion_2.setBounds(33, 126, 128, 26);
		VentanaDivisas.add(txtConversion_2);
		
		lblTitulo = new JLabel("Elije la moneda que deseas convertir a tu dinero");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 384, 14);
		VentanaDivisas.add(lblTitulo);
		
		btnRegresarMenuPrincipal = new JButton("REGRESAR");
		btnRegresarMenuPrincipal.setBounds(286, 166, 108, 23);
		btnRegresarMenuPrincipal.addActionListener(this); 
		VentanaDivisas.add(btnRegresarMenuPrincipal);
		
		lblMonExtrangera = new JLabel("DOLARES");
		lblMonExtrangera.setBounds(33, 112, 128, 14);
		VentanaDivisas.add(lblMonExtrangera);
		
		lblMonLocal = new JLabel("NUEVO SOL");
		lblMonLocal.setBounds(33, 67, 84, 14);
		VentanaDivisas.add(lblMonLocal);
		
		lblValorCambioLocal = new JLabel("-");
		lblValorCambioLocal.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorCambioLocal.setBounds(253, 36, 141, 14);
		VentanaDivisas.add(lblValorCambioLocal);
		
		lblValorCambioExtrangero = new JLabel("-");
		lblValorCambioExtrangero.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorCambioExtrangero.setBounds(253, 52, 141, 14);
		VentanaDivisas.add(lblValorCambioExtrangero);
		
		chckbxInvertir = new JCheckBox("Invertir");
		chckbxInvertir.setBackground(new Color(255, 255, 0));
		chckbxInvertir.setSelected(false);
		chckbxInvertir.setBounds(167, 108, 97, 23);
		chckbxInvertir.addItemListener(this); 
		VentanaDivisas.add(chckbxInvertir);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		 if (e.getStateChange() == ItemEvent.SELECTED) {
			 activarInvertir = true;
			 //System.out.println(activarInvertir); 
		 }else if (e.getStateChange() == ItemEvent.DESELECTED) {
			 activarInvertir = false;	
			//System.out.println(activarInvertir);			 
		 }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		cambiarLabelValorMonedas();
		
	}
	
	private void cambiarLabelValorMonedas() {
		String selectDivisa = (String) cbxDivisas.getSelectedItem();	
		Integer i=0;
		while(selectDivisa != coins.get(i).getNombre())
			i++;
		ValordeCambio(coins.get(i));
		actualizarLBLbox(coins.get(i)); //label de los txtbox
		actualizarConversion(coins.get(i).getValCamb());  //valor de conversion de los txtbox	
		
		//uso de valor mas adelante en el calculo de conversion de tiempo  real
		selectDivisa_bkp = coins.get(i).getValCamb();
	}
	
	//Actualizacion de los Label de equivalencia
	private void ValordeCambio(Moneda moneda) {
		Double valorLocal,valorExtr;
		valorLocal = redondearDecimales(moneda.getValCamb());
		valorExtr = redondearDecimales(1/moneda.getValCamb());
		
		String labelLocal,labelExtrangero;
		labelLocal = "1 PEN = "+ valorLocal.toString() + " "+ moneda.getAbrev();
		labelExtrangero = "1 " + moneda.getAbrev().toString() + " = " + 
						  valorExtr.toString() + " PEN";
		
		lblValorCambioLocal.setText(labelLocal);
		lblValorCambioExtrangero.setText(labelExtrangero);
		tasaa.setText(valorLocal.toString());///////////////////////	
	}
	
	//Actualizacion de los Label encima de los txtbox
	private void actualizarLBLbox(Moneda moneda) {
		if(activarInvertir == false) {
			lblMonExtrangera.setText(moneda.getNombre().toUpperCase());
			lblMonLocal.setText("NUEVO SOL");
		}else {
			lblMonLocal.setText(moneda.getNombre().toUpperCase());
			lblMonExtrangera.setText("NUEVO SOL");
		}	
		
	}
	//Actualizacion de valores de txtbox2 
	private void actualizarConversion(Double monedaSelect) {
		String texto = txtConversion_1.getText();
		if(!texto.isEmpty()) {
			Double resultado,resultRedon,valorTexto;
			valorTexto = Double.parseDouble(texto);
			//System.out.println("txt: " + valorTexto + " monSelc: "+  monedaSelect);	
			resultado = valorTexto*monedaSelect;
			resultRedon = redondearDecimales(resultado);
			txtConversion_2.setText(resultRedon.toString());		
		}
	}

	
	private Double convertirDivisa(Double convertir,Double monto) {
		
		Double temp = monto;
		if(temp < 1) {
			System.out.println("Divide");
			return convertir * temp;
			
		}
		else {
			System.out.println("Multiplica");
			return convertir / temp;
			
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
		//updateText();
		
	}
	
	private void updateText() {
		
		//txtConversion_2.setText("");
		String txtbox1 = txtConversion_1.getText();
		String txtbox2 = txtConversion_2.getText();
		Double resultado=0.0,num_txtConv=0.0;
		
		if(activarInvertir==false && !txtbox1.isEmpty()){
			txtConversion_1.setEditable(true);
			txtConversion_2.setText("");
			txtConversion_2.setEditable(false);
			try {
				num_txtConv = Double.parseDouble(txtbox1);
				resultado  = redondearDecimales(num_txtConv * selectDivisa_bkp);					
				txtConversion_2.setText(resultado.toString());
			}catch (Exception ex){
				txtConversion_2.setText("");
			}
			
		}
		if(activarInvertir==true && !txtbox2.isEmpty()) {
			txtConversion_2.setEditable(true);
			txtConversion_1.setText("");
			txtConversion_1.setEditable(false);
			try {
				num_txtConv = Double.parseDouble(txtbox2);
				resultado  = redondearDecimales(num_txtConv * selectDivisa_bkp);					
				txtConversion_1.setText(resultado.toString());
			}catch (Exception ex){
				txtConversion_1.setText("");
			}
		}
	}

	private Double convertirDivisa(Double convertir) {
		
		Double temp = Double.parseDouble(tasaa.getText());
		if(temp < 1) {
			System.out.println("Divide");
			return convertir * temp;
			
		}
		else {
			System.out.println("Multiplica");
			return convertir / temp;
			
		}
	}
	private Double redondearDecimales(Double valorCambiario) {
		DecimalFormat df = new DecimalFormat("#.####");
        String formatted = df.format(valorCambiario);
        return Double.parseDouble(formatted);
	}

	
}
