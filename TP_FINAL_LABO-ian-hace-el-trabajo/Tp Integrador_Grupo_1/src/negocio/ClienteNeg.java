package negocio;

import java.util.List;

import entidad.Cliente;

public interface ClienteNeg {

	public int insert(Cliente cliente);

	public boolean delete(Cliente cliente);

	public boolean modificar(Cliente cliente);

	public List<Cliente> readAll();

	public boolean readOne(Cliente cliente);

	public boolean DniRepetido(Cliente cliente);

	public String BuscarDni(Cliente cliente);

	public Cliente buscarClientePorDni(Cliente cliente);

	public boolean usuarioRepetido(Cliente cliente);

	public boolean usuarioRepetidoModificar(Cliente cliente);

	public boolean CUILrepetidoModificar(Cliente cliente);
	
	public List<Cliente> readFiltrado(String mayor, String menor);

}