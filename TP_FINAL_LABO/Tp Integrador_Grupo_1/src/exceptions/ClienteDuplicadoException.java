package exceptions;

import java.io.IOException;

public class ClienteDuplicadoException extends IOException {

	private static final long serialVersionUID = 1L;

	public ClienteDuplicadoException() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "El dni del cliente ya esta registrado";
	}
}
