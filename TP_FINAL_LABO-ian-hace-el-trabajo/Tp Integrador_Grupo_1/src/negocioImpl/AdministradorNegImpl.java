package negocioImpl;

import datosimpl.AdministradorDaoImpl;
import entidad.Administrador;
import entidad.Cliente;
import negocio.AdministradorNeg;

public class AdministradorNegImpl implements AdministradorNeg {

	AdministradorDaoImpl adminDao = new AdministradorDaoImpl();

	@Override
	public boolean readOne(Administrador admin) {
		boolean resultado = false;

		if (admin.getUsuario_Admin().trim().length() > 0 && admin.getContrasenia_Admin().trim().length() > 0) {
			resultado = adminDao.readOne(admin);
		}
		return resultado;
	}

	@Override
	public boolean usuarioRepetido(Administrador admin) {
		boolean resultado = false;
		if (admin.getUsuario_Admin().trim().length() > 0) {
			resultado = adminDao.usuarioRepetido(admin);
		}
		return resultado;
	}

	@Override
	public int BuscarId(Administrador admin) {

		return adminDao.BuscarId(admin);
	}

	@Override
	public Administrador buscarAdminPorid(Administrador admin) {
		return adminDao.buscarAdminPorid(admin);
	}

}
