package org.iesalandalus.programacion.reservasaulas.mvc.vista;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.memoria.Reservas;

public class Vista implements IVista {

/*
	private static final String ERROR = "ERROR: ";
	private static final String NOMBRE_VALIDO = "Alex";
	private static final String CORREO_VALIDO = "aia-lalex@hotmail.es"; */
	private IControlador controlador;


	public Vista() {

		Opcion.setVista(this);
	}
	public void setControlador(Controlador controlador) {
		if (controlador == null) {
			throw new NullPointerException("ERROR: El controlador no puede ser nulo.");
		}
		this.controlador = controlador;
	}

	public void comenzar() {
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	public void salir() {
		System.out.println("Aplicación finalizada");;
	}

	public void insertarAula() {
		Consola.mostrarCabecera("Insertar aula");
		try {
			Aula aula = Consola.leerAula();			
			controlador.insertarAula(aula);
			System.out.println("Aula insertado correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAula() {
		Consola.mostrarCabecera("Borrar aula");
		try {
			controlador.borrarAula(Consola.leerAula());
			System.out.println("Aula borrada correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarAula() {
		Consola.mostrarCabecera("Buscar aula");
		Aula aula = null;
		try {
			aula = controlador.buscarAula(Consola.leerAula());
			if (aula != null) {
				System.out.println("El aula buscado es: " + aula);
			} else {
				System.out.println("ERROR: No existe ningún aula con dicho nombre.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAulas() {
		Consola.mostrarCabecera("LISTADO DE AULAS");
		List<String> listaAulas = controlador.representarAulas();
		if (!listaAulas.isEmpty()) {
			Iterator<String> it = listaAulas.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} else 
			System.out.println("ERROR: No hay aulas que listar.");
		}
	
      
	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar profesor");
		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor insertado correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarProfesor() {
		String nombre="";
		Consola.mostrarCabecera("Borrar profesor");
		try {
			controlador.borrarProfesor(Consola.leerProfesor());
			System.out.println("Profesor borrado correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar profesor");
		Profesor profesor = null;
		String nombre="";
		try {
			profesor = controlador.buscarProfesor(Consola.leerProfesorFicticio());
			String mensaje = (profesor != null) ? profesor.toString() : "ERROR: No existe dicho profesor.";
			System.out.println(mensaje);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarProfesores() {
		Consola.mostrarCabecera("Listar profesores");;
		List<String> listaProfesores = controlador.representarProfesores();
		if (!listaProfesores.isEmpty()) {
			Iterator<String> it = listaProfesores.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} else {
			System.out.println("ERROR: No hay profesores que listar.");
		}
	}

	private Reserva leerReserva() {
		Reserva reserva = null;
		boolean centinela = false;
		boolean centinelaDisponibilidad = false;
		Aula aula = null;
		Permanencia permanencia = null;
		
		do {
			do {
				aula = Consola.leerAula();
				permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
				if (controlador.consultarDisponibilidad(aula, permanencia)) {
					centinelaDisponibilidad = true;
				}
			} while (!centinelaDisponibilidad);
			try {
				reserva = new Reserva(Consola.leerProfesor(), aula, permanencia);
				centinela = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (!centinela);
		return reserva;
	}


	public void realizarReserva() {
		try {
		System.out.println("Relizar reserva");
		Reserva reserva = leerReserva();
		Boolean centinela = true;
		if (controlador.buscarAula(reserva.getAula()) == null) {
			System.out.println("ERROR: El aula no esta en listado");
			centinela = false;
		}
		if (controlador.buscarProfesor(reserva.getProfesor()) == null) {
			System.out.println("ERROR: El profesor no esta en listado");
			centinela = false;
		}
		if (centinela) {
			controlador.realizarReserva(reserva);
			System.out.println("Reserva realizada con exito.");
		}
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
}
	public void anularReserva() {
		try {
			System.out.println("Anular reserva");
			controlador.anularReserva(leerReserva());
			System.out.println("Reserva eliminda con éxito");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public void listarReservas() {
		Consola.mostrarCabecera("Listar reservas");
		List<String> reservas = controlador.representarReservas();
		if (!reservas.isEmpty()) {
			Iterator<String> it = reservas.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} else {
			System.out.println("ERROR: No hay reservas para mostrar");
		}
	}
	public void listarReservasAula() {
		Consola.mostrarCabecera("Listar reservas por aula");
		List<Reserva> reservas = controlador.getReservasAula(Consola.leerAula());
		if (!reservas.isEmpty()) {
			Iterator<Reserva> it = reservas.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
		} else {
			System.out.println("ERROR: No hay reservas para mostrar");
		}
	}
	public void listarReservasProfesor() {
		Consola.mostrarCabecera("Listado de Reservas por Profesor");
		List<Reserva> reservas = controlador.getReservasProfesor(Consola.leerProfesorFicticio());
		if (!reservas.isEmpty()) {
			for (Reserva reserva : reservas) {
				if (reserva != null) 
					System.out.println(reserva);
			}
		} else {
			System.out.println("ERROR: No hay reservas, para dicho profesor, que mostrar.");
		}
	}
	
	public void consultarDisponibilidad() {
		Consola.mostrarCabecera("Consultar diponibilidad");
		Aula aula = Consola.leerAula();
		if (controlador.buscarAula(aula) == null) {
			System.out.println("El aula no esta en el listado");
		} else {
			if (controlador.consultarDisponibilidad(aula, new Permanencia(Consola.leerDia(), Consola.leerTramo()))) {
				System.out.println("ERROR: El aula esta disponible");
			} else {
				System.out.println("ERROR: El aula no esta disponible");
			}
		}

	}
	@Override
	public void setControlador(IControlador controlador) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void terminar() {
		// TODO Auto-generated method stub
		
	}
}

