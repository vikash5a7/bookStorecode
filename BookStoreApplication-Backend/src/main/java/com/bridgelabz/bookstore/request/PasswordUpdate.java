/**
 * 
 */
package com.bridgelabz.bookstore.request;

/**
 * @author HP
 *
 */
public class PasswordUpdate {

	String email;
	String newPassword;
	String confirmPassword;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "PasswordUpdate [email=" + email + ", newPassword=" + newPassword + ", confirmPassword="
				+ confirmPassword + "]";
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
