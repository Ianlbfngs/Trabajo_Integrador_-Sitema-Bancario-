package negocioImpl;

import java.util.List;

import datosimpl.ClienteDaoImpl;
import entidad.Cliente;
import negocio.ClienteNeg;

public class ClienteNegImpl implements ClienteNeg {

	ClienteDaoImpl cliDao = new ClienteDaoImpl();

	@Override
	public int insert(Cliente cliente) {
		int resultado = 0;

		if (cliente.getDni_cli().length() == 8 && cliente.getCuil_cli().length() == 11
				&& cliente.getApellido_cli().length() > 0 && cliente.getNombre_cli().length() > 0
				&& cliente.getNacionalidad_cli().length() > 0 && cliente.getDireccion_cli().length() > 0
				&& cliente.getCorreo_cli().length() > 0 && cliente.getTelefono_cli().length() > 0
				&& cliente.getUsuario_cli().length() > 0 && cliente.getContrasena_cli().length() > 0) {
			resultado = cliDao.insert(cliente);
		}
		return resultado;
	}

	@Override
	public boolean delete(Cliente cliente) {
		return cliDao.delete(cliente);
	}

	@Override
	public boolean modificar(Cliente cliente) {
		boolean resultado = false;

		if (cliente.getCuil_cli().length() == 11 && cliente.getApellido_cli().length() > 0
				&& cliente.getNombre_cli().length() > 0 && cliente.getNacionalidad_cli().length() > 0
				&& cliente.getDireccion_cli().length() > 0 && cliente.getCorreo_cli().length() > 0
				&& cliente.getTelefono_cli().length() > 0 && cliente.getUsuario_cli().length() > 0
				&& cliente.getContrasena_cli().length() > 0) {
			resultado = cliDao.modificar(cliente);
		}

		return resultado;
	}

	@Override
	public List<Cliente> readAll() {
		return cliDao.readAll();
	}

	@Override
	public String BuscarDni(Cliente cliente) {
		return cliDao.BuscarDni(cliente);
	}

	@Override
	public Cliente buscarClientePorDni(Cliente cliente) {
		return cliDao.buscarClientePorDni(cliente);
	}

	@Override
	public boolean readOne(Cliente cliente) {
		boolean resultado = false;
		;
		if (cliente.getUsuario_cli().length() > 0 && cliente.getContrasena_cli().length() > 0) {
			resultado = cliDao.readOne(cliente);
		}
		return resultado;
	}

	@Override
	public boolean DniRepetido(Cliente cliente) {
		return cliDao.dniRepetido(cliente);
	}

	@Override
	public boolean usuarioRepetido(Cliente cliente) {
		return cliDao.usuarioRepetido(cliente);

	}

	@Override
	public boolean usuarioRepetidoModificar(Cliente cliente) {
		return cliDao.usuarioRepetidoModificacion(cliente);
	}

	@Override
	public boolean CUILrepetidoModificar(Cliente cliente) {
		return cliDao.CUILrepetidoModificacion(cliente);
	}

	@Override
	public List<Cliente> readFiltrado(String mayor, String menor) {
		return cliDao.readFiltrado( mayor,  menor);
	}

}
