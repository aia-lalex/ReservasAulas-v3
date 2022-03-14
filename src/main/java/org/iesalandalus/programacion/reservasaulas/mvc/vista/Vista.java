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
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.ficheros.Reservas;

public class Vista implements IVista {


	private IControlador controlador;

// Constructor por defecto
	public Vista () {

		Opcion.setVista(this);
	}
	public void setControlador(IControlador controlador) {
		if (controlador == null) {
			throw new NullPointerException("ERROR: El controlador no puede ser nulo.");
		}
		this.controlador = controlador;
	}
// Muestra menu, elegir opcion y ejecuta
	public void comenzar() {
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}
// Salir y grabar en archivo
	public void salir() {
		controlador.terminar();
		System.out.println("Aplicación finalizada");;
	}
// inserta aula
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
// Borra aula
	public void borrarAula() {
		Consola.mostrarCabecera("Borrar aula");
		try {
			controlador.borrarAula(Consola.leerAula());
			System.out.println("Aula borrada correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
// Buscar aula
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
// Listar aulas
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
	
   // insertar profesor   
	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar profesor");
		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor insertado correctamente.");
		} catch (OperationNotSupportedException|NullPointerException|IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
// Borrar profesor
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
// Buscar profesor
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
// Listar profesor
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

	
// Realizar reserva
	public void realizarReserva() {
		try {
		System.out.println("Relizar reserva");
		Reserva reserva = Consola.leerReserva();
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
// Anular reserva
	public void anularReserva() {
		try {
			System.out.println("Anular reserva");
			controlador.anularReserva(Consola.leerReserva());
			System.out.println("Reserva eliminda con éxito");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

// Listar reserva
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

// Listar reserva aula
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
// Listar reserva profesor
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
// Consultar disponibilidad	
	public void consultarDisponibilidad() {
		Consola.mostrarCabecera("Consultar diponibilidad");
		Aula aula = Consola.leerAula();
		if (controlador.buscarAula(aula) == null) {
			System.out.println("El aula no esta en el listado");
		} else {
			if (controlador.consultarDisponibilidad(Consola.leerAulaFicticia(), Consola.leerPermanencia())) {
				System.out.println("ERROR: El aula esta disponible");
			} else {
				System.out.println("ERROR: El aula no esta disponible");
			}
		}

}
	@Override
	public void terminar() {
		// TODO Auto-generated method stub
		
	}
}

