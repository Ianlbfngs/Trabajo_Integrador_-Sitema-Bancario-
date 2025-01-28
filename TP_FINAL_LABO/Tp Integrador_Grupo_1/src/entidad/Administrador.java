package entidad;

public class Administrador {
	private int IdAdministraodr_Admin;
	private String Usuario_Admin;
	private String Contrasenia_Admin;

	public Administrador() {
	}

	public Administrador(int idAdministraodr_Admin, String usuario_Admin, String contrasenia_Admin) {
		IdAdministraodr_Admin = idAdministraodr_Admin;
		Usuario_Admin = usuario_Admin;
		Contrasenia_Admin = contrasenia_Admin;
	}

	public int getIdAdministraodr_Admin() {
		return IdAdministraodr_Admin;
	}

	public void setIdAdministraodr_Admin(int idAdministraodr_Admin) {
		IdAdministraodr_Admin = idAdministraodr_Admin;
	}

	public String getUsuario_Admin() {
		return Usuario_Admin;
	}

	public void setUsuario_Admin(String usuario_Admin) {
		Usuario_Admin = usuario_Admin;
	}

	public String getContrasenia_Admin() {
		return Contrasenia_Admin;
	}

	public void setContrasenia_Admin(String contrasenia_Admin) {
		Contrasenia_Admin = contrasenia_Admin;
	}

}
