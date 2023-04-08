package Clases;

public class Moneda {
	private Double valCamb;
	private String abrev;
	private String nombre;
	
	public Moneda(String nombre,String abreviatura,Double valorCambiario) {
		this.valCamb = valorCambiario;
		this.nombre = nombre;
		this.abrev = abreviatura;
		
	}

	public Double getValCamb() {
		return valCamb;
	}

	public String getAbrev() {
		return abrev;
	}

	public String getNombre() {
		return nombre;
	}

	
}
