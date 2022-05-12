package juego;

import java.awt.Color;

import entorno.Entorno;

public class Mikasa {
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;
	private double velocidad;
	private int estado;

	public Mikasa(double x, double y, double ancho, double alto, double angulo) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.angulo = angulo;
		this.velocidad=1.5;
		this.estado=0;
	}

	public void dibujar(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, this.angulo, Color.ORANGE);
	}

	// movimiento
	public void moverAdelante() {
		if(this.estado==0) {
			this.x += Math.cos(this.angulo) * velocidad;
			this.y += Math.sin(this.angulo) * velocidad;
		}
	}
	
	public void moverDerecha() {
		this.angulo += 0.08;
	}

	public void moverIzquierda() {
		this.angulo -= 0.08;
	}

	public void moverAtras() {
		if (this.estado==0){
			this.x -= Math.cos(this.angulo) * velocidad;
			this.y -= Math.sin(this.angulo) * velocidad;
		}
	}
	public void colisionPantalla() {
		if (this.x <= 20) {
			this.x = this.x + 2;
		}
		if ((this.x + this.ancho) >= 820) {
			this.x = this.x - 2;
		}
		if (this.y >= 580) {
			this.y = this.y - 2;
		}
		if ((this.y - this.alto) <= -20) {
			this.y = this.y + 2;
		}
	}
	
	public void colisionObs(double x, double y, double ancho, double alto) {
		if (this.x <= x+ancho) {
			this.estado = 1;
			this.x=this.x+2;
		}
		if (this.x + this.ancho >= x) {
			this.estado=1;
			this.x = this.x - 2;
		}
		
		if (this.y >= y-alto) {
			this.estado=1;
			this.y = this.y - 2;
		}
		if (this.y - this.alto <= y) {
			this.estado=1;
			this.y = this.y + 2;
		}
		this.estado=0;
	}


	public boolean colision(double xIzq, double xDer, double yAba, double yArr) {
		boolean tocandoX = xIzq <= this.x + this.ancho && this.x <= xDer;
		boolean tocandoY = yAba >= this.y + this.alto && this.y >= yArr;
		return tocandoX && tocandoY;
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

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}

}
