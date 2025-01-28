package entidad;

public class Localidad {
	private int IdLocalidad_Loc;
	private Provincia provincia_loc = new Provincia();
	private String Descripcion_Loc;

	public Localidad() {
	};

	public Localidad(int idLocalidad_Loc, int idProvincia_Loc, String descripcion_Loc) {
		super();
		IdLocalidad_Loc = idLocalidad_Loc;
		provincia_loc.setIdProvincia(idProvincia_Loc);
		Descripcion_Loc = descripcion_Loc;
	}

	public int getIdLocalidad_Loc() {
		return IdLocalidad_Loc;
	}

	public void setIdLocalidad_Loc(int idLocalidad_Loc) {
		IdLocalidad_Loc = idLocalidad_Loc;
	}

	public int getIdProvincia_Loc() {
		return provincia_loc.getIdProvincia();
	}

	public void setIdProvincia_Loc(int idProvincia_Loc) {
		provincia_loc.setIdProvincia(idProvincia_Loc);
		;
	}

	public String getDescripcion_Loc() {
		return Descripcion_Loc;
	}

	public void setDescripcion_Loc(String descripcion_Loc) {
		Descripcion_Loc = descripcion_Loc;
	}

	public String getDescProv_Loc() {
		return provincia_loc.getDescripcion();
	}

	public void setProvincia_loc(Provincia provincia) {
		provincia_loc = provincia;
	}
}
