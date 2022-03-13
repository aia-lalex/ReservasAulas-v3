package org.iesalandalus.programacion.reservasaulas;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.IFuenteDatos;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.IModelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.IVista;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;
import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {


	public static void main(String[] args) {
		IModelo modelo = null;
		modelo = new Modelo(FactoriaFuenteDatos.FICHEROS.crear());
		IVista vista = new Vista();
		IControlador controlador = new Controlador(modelo, vista);
		System.out.println();
		controlador.comenzar();
	}
}
