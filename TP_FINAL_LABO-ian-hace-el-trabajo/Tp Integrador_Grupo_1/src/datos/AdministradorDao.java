package datos;

import entidad.Administrador;

public interface AdministradorDao {

	public boolean readOne(Administrador admin); //intenta leer el admin y contrase�a del login
	public boolean usuarioRepetido(Administrador admin);
	public int BuscarId(Administrador admin);
	public Administrador buscarAdminPorid(Administrador admin);
}
