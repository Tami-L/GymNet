/**
 * 
 */
package application;

import java.io.Serializable;

/**
 * @author L Tami Class to store profile information to be used by the
 *         application
 *
 */
public class Profile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8004396241483822360L;
	/**
	 * 
	 */
	
	private String name;
	private String surname;
	private String email;
	private String password;
	private int weight;
	private int height;
	private double physicalAbility;
	// private PersonalData data;

	/**
	 * This function takes in input from the user interface and stores it in an
	 * object
	 * 
	 * @param name
	 * @param surname
	 * @param email
	 * @param password
	 * @param weight
	 * @param height
	 */

	public Profile(String name, String surname, String email, String password, int weight, int height,
			double physicalAbility) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	
		this.password = password;
		this.weight = weight;
		this.height = height;
		this.physicalAbility = physicalAbility;

		// TODO Auto-generated constructor stub
	}



	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getSurname() {
		return surname;
	}

	public int getHeight() {
		return height;
	}

	public int getWeight() {
		return weight;
	}

	public double getPhysicalAbility() {
		return physicalAbility;
	}

	public void setPhysicalAbility(double physicalAbility) {
		this.physicalAbility = physicalAbility;
	}

}
