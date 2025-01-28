package entidad;

import java.sql.Date;

public class Cliente {
	private String dni_cli; // varchar[8]
	private Sexo genero_cli = new Sexo(); // id-int
	private Localidad localidad_cli = new Localidad(); // id-int
	private String cuil_cli; // varchar[11]
	private String apellido_cli; // varchar[40]
	private String nombre_cli; // varchar[40]
	private String nacionalidad_cli; // varchar[40]
	private Date fechaNacimiento_cli; // date
	private String direccion_cli; // varchar[40]
	private String correo_cli; // varchar[40]
	private String telefono_cli; // varchar[40]
	private String usuario_cli; // varchar[40]
	private String contrasena_cli; // varchar[40]
	private boolean estado_cli; // bool

	// Constructor sin parámetros
	public Cliente() {
		// Inicialización predeterminada si es necesario
	}

	public Cliente(String dni_cli, int genero_cli, int localidad_cli, int provincia_cli, String cuil_cli,
			String apellido_cli, String nombre_cli, String nacionalidad_cli, Date fechaNacimiento_cli,
			String direccion_cli, String correo_cli, String telefono_cli, String usuario_cli, String contrasena_cli,
			boolean estado_cli) {
		this.dni_cli = dni_cli;
		this.genero_cli.setIDSexo(genero_cli);
		this.localidad_cli.setIdProvincia_Loc(provincia_cli);
		this.localidad_cli.setIdLocalidad_Loc(localidad_cli);
		this.cuil_cli = cuil_cli;
		this.apellido_cli = apellido_cli;
		this.nombre_cli = nombre_cli;
		this.nacionalidad_cli = nacionalidad_cli;
		this.fechaNacimiento_cli = fechaNacimiento_cli;
		this.direccion_cli = direccion_cli;
		this.correo_cli = correo_cli;
		this.telefono_cli = telefono_cli;
		this.usuario_cli = usuario_cli;
		this.contrasena_cli = contrasena_cli;
		this.estado_cli = estado_cli;
	}

	public void setGenero_cli(Sexo genero) {
		genero_cli = genero;
	}

	public void setLocalidad_cli(Localidad localidad) {
		localidad_cli = localidad;
	}

	public String getDni_cli() {
		return dni_cli;
	}

	public void setDni_cli(String dni_cli) {
		this.dni_cli = dni_cli;
	}

	public int getIdGenero_cli() {
		return genero_cli.getIDSexo();
	}

	public void setIdGenero_cli(int idGenero_cli) {
		this.genero_cli.setIDSexo(idGenero_cli);
	}

	public String getDescripcionGenero_cli() {
		return genero_cli.getDescripcion();
	}

	public void setDescripcionGenero_cli(String desGenero_cli) {
		this.genero_cli.setDescripcionSex(desGenero_cli);
	}

	public int getIdLocalidad_cli() {
		return localidad_cli.getIdLocalidad_Loc();
	}

	public void setIdLocalidad_cli(int idLocalidad_cli) {
		this.localidad_cli.setIdLocalidad_Loc(idLocalidad_cli);
	}

	public int getIdProvincia_loc_cli() {
		return localidad_cli.getIdProvincia_Loc();
	}

	public void setIdProvincia_loc_cli(int idProv_loc_cli) {
		this.localidad_cli.setIdProvincia_Loc(idProv_loc_cli);
	}

	public String getDescProvincia_loc_cli() {
		return localidad_cli.getDescProv_Loc();
	}

	public String getDescLoc_cli() {
		return localidad_cli.getDescripcion_Loc();
	}

	public String getCuil_cli() {
		return cuil_cli;
	}

	public void setCuil_cli(String cuil_cli) {
		this.cuil_cli = cuil_cli;
	}

	public String getApellido_cli() {
		return apellido_cli;
	}

	public void setApellido_cli(String apellido_cli) {
		this.apellido_cli = apellido_cli;
	}

	public String getNombre_cli() {
		return nombre_cli;
	}

	public void setNombre_cli(String nombre_cli) {
		this.nombre_cli = nombre_cli;
	}

	public String getNacionalidad_cli() {
		return nacionalidad_cli;
	}

	public void setNacionalidad_cli(String nacionalidad_cli) {
		this.nacionalidad_cli = nacionalidad_cli;
	}

	public Date getFechaNacimiento_cli() {
		return fechaNacimiento_cli;
	}

	public void setFechaNacimiento_cli(Date fechaNacimiento_cli) {
		this.fechaNacimiento_cli = fechaNacimiento_cli;
	}

	public String getDireccion_cli() {
		return direccion_cli;
	}

	public void setDireccion_cli(String direccion_cli) {
		this.direccion_cli = direccion_cli;
	}

	public String getCorreo_cli() {
		return correo_cli;
	}

	public void setCorreo_cli(String correo_cli) {
		this.correo_cli = correo_cli;
	}

	public String getTelefono_cli() {
		return telefono_cli;
	}

	public void setTelefono_cli(String telefono_cli) {
		this.telefono_cli = telefono_cli;
	}

	public String getUsuario_cli() {
		return usuario_cli;
	}

	public void setUsuario_cli(String usuario_cli) {
		this.usuario_cli = usuario_cli;
	}

	public String getContrasena_cli() {
		return contrasena_cli;
	}

	public void setContrasena_cli(String contrasena_cli) {
		this.contrasena_cli = contrasena_cli;
	}

	public boolean isEstado_cli() {
		return estado_cli;
	}

	public void setEstado_cli(boolean estado_cli) {
		this.estado_cli = estado_cli;
	}

	public String getDescGenero_cli() {
		return genero_cli.getDescripcion();
	}

	@Override
	public String toString() {
		return "Cliente [dni_cli=" + dni_cli + ", genero_cli=" + genero_cli + ", localidad_cli=" + localidad_cli
				+ ", cuil_cli=" + cuil_cli + ", apellido_cli=" + apellido_cli + ", nombre_cli=" + nombre_cli
				+ ", nacionalidad_cli=" + nacionalidad_cli + ", fechaNacimiento_cli=" + fechaNacimiento_cli
				+ ", direccion_cli=" + direccion_cli + ", correo_cli=" + correo_cli + ", telefono_cli=" + telefono_cli
				+ ", usuario_cli=" + usuario_cli + ", contrasena_cli=" + contrasena_cli + ", estado_cli=" + estado_cli
				+ "]";
	}

}
