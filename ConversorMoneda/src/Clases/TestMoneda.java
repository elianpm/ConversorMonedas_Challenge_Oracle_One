package Clases;

import java.util.ArrayList;
import java.util.List;

public class TestMoneda {
	public static void main(String[] args) {
		MonedaFunciones mf = new MonedaFunciones();
		List<Moneda> coins = mf.getDivisas();
		System.out.println(coins.size());
		
		mf.sumar2monedas(coins.get(0), coins.get(1));
		
		coins.size();
	}
	
	
}
