package Clases;

import gui.VentanaPrincipal;

public class Aplicacion {
	
	public Aplicacion() {
		iniciarVentana();
	}

	private void iniciarVentana() {
		VentanaPrincipal miVentana = new VentanaPrincipal();
		miVentana.setVisible(true);
	}
	
}
