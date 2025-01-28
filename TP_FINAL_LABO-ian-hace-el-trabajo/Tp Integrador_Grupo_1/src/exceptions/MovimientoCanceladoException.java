package exceptions;

import java.io.IOException;

public class MovimientoCanceladoException extends IOException {
	private static final long serialVersionUID = 1L;

	public MovimientoCanceladoException() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "No fue posible realizar el movimiento";
	}
}
