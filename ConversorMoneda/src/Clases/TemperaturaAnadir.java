package Clases;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TemperaturaAnadir {
	List<Temperatura> temper = new ArrayList<>();
	
	public TemperaturaAnadir() {
		//temper.add(new Temperatura("Celsius","°C",0));
		temper.add(new Temperatura("Fahrenheit","°F",32.0));
		temper.add(new Temperatura("Kelvin","K",273.15));
			
	}
	
	public String Convertir(String cbxSelect, String txtValor, Boolean activarInvertir) {
		Double resultado = 0.0,resultRedon,valorTexto;
		
		valorTexto = Double.parseDouble(txtValor);
		
		// Formula C a F -> ºF = (ºC · 1,8) + 32  | ºC = (ºF -32) / 1,8
		// Formula C a K ->  Kelvin = Celsius + 273.15 | 
		
		switch (cbxSelect) {
		case "Fahrenheit":
			resultado = activarInvertir ? (valorTexto - 32) / 1.8 : (valorTexto * 1.8) + 32;
			break;
		case "Kelvin":
			resultado = activarInvertir ? valorTexto - 273.15 : valorTexto + 273.15;
			break;

		}
		resultRedon = redondearDecimales(resultado);
		return resultRedon.toString();
	}
	
	public String validarNumero(String texto) {
		texto = texto.replaceAll("[^\\d-]", "");

		// Verificar si '-' está al inicio del String
		if (texto.startsWith("-")) {
			texto = "-" + texto.replaceAll("-", "");
		}
		return texto;
	}
	
	public List<Temperatura> getTemperaturas(){
		return temper;
	}
	
	public Double redondearDecimales(Double valCamb) {
		DecimalFormat df = new DecimalFormat("#.##");
        String formatted = df.format(valCamb);
        return Double.parseDouble(formatted);
	}
}
