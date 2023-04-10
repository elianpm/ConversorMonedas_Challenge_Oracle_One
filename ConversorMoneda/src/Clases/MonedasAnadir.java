package Clases;

import java.util.ArrayList;
import java.util.List;

public class MonedasAnadir {

	List<Moneda> divisas = new ArrayList<>();
	
	public MonedasAnadir() {
		//divisas.add(new Moneda("Selecciona","NAN",1.0));
		divisas.add(new Moneda("Dolar","USD",0.26533381));
		divisas.add(new Moneda("Euro","EUR",0.24349218));
		divisas.add(new Moneda("Libra","GBP",0.2137368));
		divisas.add(new Moneda("Yen Japones","JPY",35.208536));
		divisas.add(new Moneda("Won Surcoreano","KRW",348.54524));				
	}
	
	public List<Moneda> getDivisas(){
		return divisas;
	}
	
	public String validarNumero(String texto) {
		texto = texto.replaceAll("[^\\d-]", "");

		// Verificar si '-' está al inicio del String
		if (texto.startsWith("-")) {
			texto = "-" + texto.replaceAll("-", "");
		}
		return texto;
	}
	
	
}
