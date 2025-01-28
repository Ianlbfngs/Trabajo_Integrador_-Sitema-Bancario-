package datos;

import java.util.List;

import entidad.Cliente;

public interface ClienteDao {

	public int insert(Cliente cliente);
	public boolean delete(Cliente cliente);
	public boolean modificar(Cliente cliente);
	public List<Cliente> readAll();
	public boolean readOne(Cliente cliente); //para buscar un cliente en especifico (login)
	public boolean dniRepetido(Cliente cliente);
	public boolean usuarioRepetido(Cliente cliente);
	public String BuscarDni(Cliente cliente);
	public Cliente buscarClientePorDni(Cliente cliente);
	public List<Cliente> readFiltrado(String mayor, String menor);
}
