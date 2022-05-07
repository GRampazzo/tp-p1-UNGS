package juego;

import java.awt.Color;

import entorno.Entorno;

public class Mikasa {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;

	public Mikasa(double x, double y, double ancho, double alto, double angulo) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.angulo = angulo;
	}

	public void dibujar(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, Color.ORANGE);
	}

	// movimiento
	public void moverAdelante() {
		this.x += Math.cos(this.angulo)*2;
		this.y += Math.sin(this.angulo)*2;
		if (this.x > 900) {
			this.x = -100;
		}
		if (this.x < -100) {
			this.x = 900;
		}
		if (this.y > 650) {
			this.y = -50;
		}
		if (this.y < -50) {
			this.y = 650;
		}

	}


	public void moverDerecha() {
		this.angulo += 0.08;
	}

	public void moverIzquierda() {
		this.angulo -= 0.08;
	}
}
