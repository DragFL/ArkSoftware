package Modelo;

public class Usuario {
   
	private String mail;
	private String psw;
	
	public Usuario(String mail, String psw) {
	
		this.mail = mail;
		this.psw = psw;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}
	
	@Override
	public String toString() {
		return "El usuario es {" + mail + "}";
	}
	
	
}
