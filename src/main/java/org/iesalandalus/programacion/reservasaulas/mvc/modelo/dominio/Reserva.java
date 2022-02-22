package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Reserva {

	Permanencia permanencia;
	Profesor profesor;
	Aula aula;

	public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {
		setProfesor(profesor);
		setAula(aula);
		setPermanencia(permanencia);

	}

	public Reserva(Reserva reserva) {
		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede copiar una reserva nula.");
		}
		setProfesor(reserva.getProfesor());
		setAula(reserva.getAula());
		setPermanencia(reserva.getPermanencia());

	}


	public Permanencia getPermanencia() {

		Permanencia copiaPermanencia = null;
		if (this.permanencia instanceof PermanenciaPorTramo) {
			copiaPermanencia = new PermanenciaPorTramo((PermanenciaPorTramo)this.permanencia);
		} else if (this.permanencia instanceof PermanenciaPorHora) {
			copiaPermanencia = new PermanenciaPorHora((PermanenciaPorHora)this.permanencia);
		}
		return copiaPermanencia;
	}

	public Profesor getProfesor() {

		return profesor;
	}

	public Aula getAula() {

		return aula;
	}

	private void setPermanencia(Permanencia permanencia) {
		if (permanencia == null) {
			throw new NullPointerException("ERROR: La reserva se debe hacer para una permanencia concreta.");
		}
		this.permanencia = permanencia;
	}

	private void setProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: La reserva debe estar a nombre de un profesor.");
		}
		this.profesor = profesor;
	}

	private void setAula(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: La reserva debe ser para un aula concreta.");
		}
		this.aula = aula;
	
	}
	
	public static Reserva getReservaFicticia(Aula aula, Permanencia permanencia) {
		return new Reserva(Profesor.getProfesorFicticio("alexbalwing@hotmail.com"), aula, permanencia);
	}

	public float getPuntos() {
		return permanencia.getPuntos()+ aula.getPuntos();
	}
	@Override
	public int hashCode() {
		return Objects.hash(aula, permanencia, profesor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(aula, other.aula) && Objects.equals(permanencia, other.permanencia)
				&& Objects.equals(profesor, other.profesor);
	}

	@Override
	public String toString() {
		return "Reserva [permanencia=" + permanencia + ", profesor=" + profesor + ", aula=" + aula
				+ ", getPermanencia()=" + getPermanencia() + ", getProfesor()=" + getProfesor() + ", getAula()="
				+ getAula() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}




}
