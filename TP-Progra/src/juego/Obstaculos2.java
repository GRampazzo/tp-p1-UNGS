package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Obstaculos2 {
	private double x;
	private double y;
	private double radio;
	private Image image = Herramientas.cargarImagen("obs.png");

	public Obstaculos2(double x, double y, double radio) {
		this.x = x;
		this.y = y;
		this.radio = radio;
	}

	public void dibujarSprite(Entorno e) {
		e.dibujarImagen(image, x + 30, y + 5, 0, 1.2/* 1.2 */);
	}


	// getters and setters
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

	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}