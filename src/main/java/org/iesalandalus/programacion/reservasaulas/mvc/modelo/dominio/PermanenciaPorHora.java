package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class PermanenciaPorHora extends Permanencia {
	private static final int PUNTOS=10;
	private static final LocalTime HORA_INICIO= LocalTime.of(8, 0);;
	private static final LocalTime HORA_FIN = LocalTime.of(22, 0);;
	private static final String FORMATO_FECHA_HORA = "dd/MM/yyyy HH:mm";
	private LocalTime hora;
	
	public PermanenciaPorHora(LocalDate dia,LocalTime hora) {
		super(dia);
		setHora(hora);
	}

	public PermanenciaPorHora(PermanenciaPorHora permanencia) {
		super(permanencia);
		setHora(permanencia.getHora());
	}

	public int getPuntos() {
		return PUNTOS;
	}

	public LocalTime getHora() {
		return hora;
	}

	private void setHora(LocalTime hora) {
		if (hora == null) {
			throw new NullPointerException("ERROR: La hora no pude ser nula.")
		}
		this.hora = hora;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(hora);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PermanenciaPorHora other = (PermanenciaPorHora) obj;
		return Objects.equals(hora, other.hora);
	}

	@Override
	public String toString() {
		return "PermanenciaPorHora [hora=" + hora + "]";
	}
	
	
}
