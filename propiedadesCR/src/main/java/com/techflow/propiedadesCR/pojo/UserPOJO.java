package com.techflow.propiedadesCR.pojo;

import com.techflow.propiedadesCR.ejb.Trole;
/*
 * UserPOJO
 * Esta clase es 
 */
public class UserPOJO {

	private int idUsuario;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone1;
	private String phone2;
	private String userImage;
	private byte active;
	private byte firstTime;
	private Trole role;
	

	public UserPOJO() {
		super();
	}
	public int getIdUsuario() {
		/*
		 * Este metodo es usado para traer ell id del usuario
		 */
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastname) {
		this.lastName = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public byte getActive() {
		return active;
	}
	public void setActive(byte active) {
		this.active = active;
	}
	public byte getFirstTime() {
		return firstTime;
	}
	public void setFirstTime(byte firstTime) {
		this.firstTime = firstTime;
	}
	public Trole getRole() {
		return role;
	}
	public void setRole(Trole role) {
		this.role = role;
	}                        
}
