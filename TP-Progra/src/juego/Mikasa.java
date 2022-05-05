package juego;

import java.awt.Color;

import entorno.Entorno;

public class Mikasa {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	
	public Mikasa(double x, double y, double ancho, double alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}
	public void dibujar(Entorno e) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.ORANGE);
	}
}
