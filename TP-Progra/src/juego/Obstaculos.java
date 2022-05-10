package juego;

import java.awt.*;
import entorno.Entorno;


public class Obstaculos {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	
	public Obstaculos(double x, double y, double ancho, double alto) {
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
	}

	public void dibujar(Entorno e) {
		e.dibujarRectangulo(this.x,this.y, this.ancho, this.alto,0, Color.BLUE);
	}
	
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public double getAlto() {
		return alto;
	}

	public void setAlto(double alto) {
		this.alto = alto;
	}
}
