package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Disparo {
	private double x, y, radio, angulo, distancia;
	private Image image = Herramientas.cargarImagen("Proyectil.png");
	private boolean impacto = false;

	public Disparo(double x, double y, double radio, double angulo) {
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.angulo = angulo;
	}

	public void dibujarSprite(Entorno e) {
		e.dibujarImagen(this.image, this.x, this.y - 1, this.angulo, 0.1);
	}

	public void mover() {
		this.x += Math.cos(this.angulo) * 4;
		this.y += Math.sin(this.angulo) * 4;
	}

	public boolean colisiona(double radio1, double radio2, double dist) {
		return (radio1 + radio2) > dist;
	}

	public void distancia(double x1, double y1, double x2, double y2) {
		this.distancia = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}

	public boolean estaEnPantalla() {
		return (this.x < 800 && this.x > 0 && this.y < 600 && this.y > 0);
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

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

	public double getDistancia() {
		return distancia;
	}

	public boolean getImpacto() {
		return impacto;
	}

	public void setImpacto(boolean a) {
		this.impacto = a;
	}
}
