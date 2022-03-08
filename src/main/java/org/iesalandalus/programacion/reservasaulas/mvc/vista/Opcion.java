package org.iesalandalus.programacion.reservasaulas.mvc.vista;

public enum Opcion {

	SALIR("Salir.") {
		public void ejecutar() {
			vista.salir();
		}
	}
	, INSERTAR_AULA("Insertar aula:") {
		public void ejecutar() {
			vista.insertarAula();
		}
	}
	, BORRAR_AULA("Borrar aula:") {
		public void ejecutar() {
			vista.borrarAula();
		}
	}
	, BUSCAR_AULA("Buscar aula:") {
		public void ejecutar() {
			vista.buscarAula();
		}
	}
	, LISTAR_AULAS("Listar aulas:") {
		public void ejecutar() {
			vista.listarAulas();
		}
	}
	, INSERTAR_PROFESOR("Insertar profesor:") {
		public void ejecutar() {
			vista.insertarProfesor();
		}
	}
	, BORRAR_PROFESOR("Borrar profesor:") {
		public void ejecutar() {
			vista.borrarProfesor();
		}
	}
	, BUSCAR_PROFESOR("Buscar profesor:") {
		public void ejecutar() {
			vista.buscarProfesor();
		}
	}
	, LISTAR_PROFESORES("Listar profesores:") {
		public void ejecutar() {
			vista.listarProfesores();
		}
	}
	, INSERTAR_RESERVA("Insertar reserva:") {
		public void ejecutar() {
			vista.realizarReserva();
		}
	}
	, BORRAR_RESERVA("Borrar reserva:") {
		public void ejecutar() {
			vista.anularReserva();
		}
	}
	, LISTAR_RESERVAS("Listar reservas:") {
		public void ejecutar() {
			vista.listarReservas();
		}
	}
	, LISTAR_RESERVAS_AULA("Listar reservas por aula:") {
		public void ejecutar() {
			vista.listarReservasAula();
		}
	}
	, LISTAR_RESERVAS_PROFESOR("Listar reservas por profesor:") {
		public void ejecutar() {
			vista.listarReservasProfesor();
		}
	}

	, CONSULTAR_DISPONIBILIDAD("Consultar disponibilidad:") {
		public void ejecutar() {
			vista.consultarDisponibilidad();
		}
	};
	private String mensajeMostrado;
	private static Vista vista;

	private Opcion(String mensajeMostrado) {
		this.mensajeMostrado = mensajeMostrado;
	}

	public String getMensaje() {
		return mensajeMostrado;
	}

	public abstract void ejecutar();

	protected static void setVista(Vista vista) {
		Opcion.vista = vista;
	}

	public String toString() {
		return mensajeMostrado;
	}

	public static Opcion getOpcionSegunOrdinal(int ordinal) {
		return Opcion.values()[ordinal];
	}

	public static boolean esOrdinalValido(int ordinal) {
		if(ordinal>=0 && ordinal<Opcion.values().length)
			return true;
		return false;
	}

}

