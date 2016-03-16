package com.techflow.propiedadesCR.pojo;
/**
* <h1>POJO de la guia</h1>
* 
* Enfatiza el uso de la clase simple "guia"
* 
* @author  Jimmi Vila
* @version 1.0
* @since 15/03/2016
*/
public class GuidePOJO {
	/**
     * Id de la guia
     */
	private int idGuides;
	
	/**
     * Ruta de el archivo
     */
	private String url;
	
	/**
     * Id del banco al que pertenece
     */
	private BankPOJO tbank;

	public GuidePOJO() {
		super();
	}

	public int getIdGuides() {
		return idGuides;
	}

	public void setIdGuides(int idGuides) {
		this.idGuides = idGuides;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BankPOJO getTbank() {
		return tbank;
	}

	public void setTbank(BankPOJO tbank) {
		this.tbank = tbank;
	}

}
