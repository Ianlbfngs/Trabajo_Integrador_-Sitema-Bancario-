package negocio;

import entidad.Administrador;

public interface AdministradorNeg {

	public boolean readOne(Administrador admin);

	public boolean usuarioRepetido(Administrador admin);

	public int BuscarId(Administrador admin);

	public Administrador buscarAdminPorid(Administrador admin);

}
