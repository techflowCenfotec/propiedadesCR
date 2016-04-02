/**
* <h1>POJO del Usuario</h1>
*Esta clase enfatiza el uso de la clase simple "usuario".
*
* @author  Jorge Arguedas Arrieta
*
* @version 1.0
*
* @since 2/25/2016
*/
package com.techflow.propiedadesCR.pojo;

import java.util.Date;
import java.util.List;




public class UserPOJO {
	
	/**
     * Identificador del usuario.
     */
	private int idUser;
	/**
     * Nombre de usuario.
     */
	private String userName;
	/**
     * Primer apellido del usuario.
     */
	private String firstName;
	/**
     * Segundo apellido del usuario.
     */
	private String lastName;
	/**
     * Correo del usuario.
     */
	private String email;
	/**
     * Contraseña del usuario.
     */
	private String password;
	/**
     * Teléfono del usuario.
     */
	private String phone1;
	/**
     * Teléfono del usuario.
     */
	private String phone2;
	/**
     * Ruta de la imagen.
     */
	private String userImage;
	/**
     * Indica si el usuario esta o no esta activo.
     */
	private byte active;
	/**
     * Indica si es la primer vez que el usuario entra a la aplicación.
     */
	private byte firstTime;
	/**
     * Indica el cumpleaños del usuario.
     */
	private Date birthday;
	/**
     * Género del usuario.
     */
	private String gender;
	/**
     * Rol asignado al usuario.
     */
	private RolePOJO role;
	/**

     * Calificaciones del vendedor.
     */
	private List<UserRatingPOJO> vendorRatings;
	/**
	 * Lista de favoritos del usuario.
	 */
	private List<PropertyPOJO> tproperties2;


	public UserPOJO() {
		super();
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int pidUser) {
		this.idUser = pidUser;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String puserName) {
		this.userName = puserName;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String pfirstName) {
		this.firstName = pfirstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String plastname) {
		this.lastName = plastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String pemail) {
		this.email = pemail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String ppassword) {
		this.password = ppassword;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String pphone1) {
		this.phone1 = pphone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String pphone2) {
		this.phone2 = pphone2;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String puserImage) {
		this.userImage = puserImage;
	}
	public byte getActive() {
		return active;
	}
	public void setActive(byte pactive) {
		this.active = pactive;
	}
	public byte getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(byte pfirstTime) {
		this.firstTime = pfirstTime;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public RolePOJO getRole() {
		return role;
	}
	public void setRole(RolePOJO trole) {
		this.role = trole;
	}

	public List<UserRatingPOJO> getVendorRatings() {
		return vendorRatings;
	}
	public void setVendorRatings(List<UserRatingPOJO> vendorRatings) {
		this.vendorRatings = vendorRatings;
	}
	                       
	public List<PropertyPOJO> getTproperties2() {
		return tproperties2;
	}
	public void setTproperties2(List<PropertyPOJO> tproperties2) {
		this.tproperties2 = tproperties2;
	}

}
