package utils;

public class Tiempo {
	private boolean inicia;
	private double contar;
	private double timer;

	public Tiempo(boolean inicia, double cont, double timer) {
		this.inicia = inicia;
		this.contar = cont;
		this.timer = timer;
	}

//	getters and setters

	public double getContar() {
		return contar;
	}

	public boolean isInicia() {
		return inicia;
	}

	public void setInicia(boolean inicia) {
		this.inicia = inicia;
	}

	public void setContar(double contar) {
		this.contar += contar;
	}

	public double getTimer() {
		return timer;
	}

	public void setTimer(double timer) {
		this.timer -= timer;
	}

}
