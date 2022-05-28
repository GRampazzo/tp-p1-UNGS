package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Disparo {
	private double x, y, ancho, alto, angulo;
	private Image image = Herramientas.cargarImagen("Proyectil.png");
	
	public Disparo(double x, double y, double angulo) {
		this.x = x;
		this.y = y;
		this.ancho = 30;
		this.alto = 5;
		this.angulo = angulo;
	}

	public void dibujar(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, Color.WHITE);
	}
	
	public void dibujarSprite(Entorno e) {
		e.dibujarImagen(image, x, y-1, angulo,0.1);
	}

	public void mover() {
		this.x += Math.cos(this.angulo) * 3;
		this.y += Math.sin(this.angulo) * 3;
	}

	public boolean estaEnPantalla() {
		return this.posicionExtremoDerecho() > 0 && this.posicionExtremoIzquierdo() < 800 && this.posicionYArriba() > 0
				&& this.posicionYAbajo() < 600;
	}

	public double posicionExtremoDerecho() {
		return this.x + this.ancho / 2;
	}

	public double posicionExtremoIzquierdo() {
		return this.x - this.ancho / 2;
	}

	public double posicionYArriba() {
		return this.y - this.alto / 2;
	}

	public double posicionYAbajo() {
		return this.y + this.alto / 2;
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

	public double getAlto() {
		return this.alto;
	}

	public void setAlto(double alto) {
		this.alto = alto;
	}

	public double getAncho() {
		return this.ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

}