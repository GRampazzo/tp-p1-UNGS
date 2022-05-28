package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Suero {
	private double x;
	private double y;
	private double radio;
	private double distancia;
	private double distancia2;
	private Image image = Herramientas.cargarImagen("Suero.png");
	private Image aux = this.image;

	public Suero(double radio) {
		this.radio = radio;
	}

//	public void dibujar(Entorno e) {
//		e.dibujarCirculo(this.x, this.y, this.radio, Color.CYAN);
//	}

	public void dibujarSprite(Entorno e) {
		if (this.image != null) {
			e.dibujarImagen(image, x, y, 0, 0.1);
		} else if (this.aux != null) {
			e.dibujarImagen(aux, x, y, 0, 0.1);
		} else {
			return;
		}

	}

	public boolean colisiona(double radio1, double radio2, double dist) {
		return (radio1 + radio2) > dist;
	}

	public void distancia(double x1, double y1, double x2, double y2) {
		this.distancia = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

	public void distancia2(double x1, double y1, double x2, double y2) {
		this.distancia2 = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

//	getters and setters
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

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getAux() {
		return aux;
	}

	public void setAux(Image aux) {
		this.aux = aux;
	}

	public double getDistancia2() {
		return distancia2;
	}

	public void setDistancia2(double distancia2) {
		this.distancia2 = distancia2;
	}

	
}