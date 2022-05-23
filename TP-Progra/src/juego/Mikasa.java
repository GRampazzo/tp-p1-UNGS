package juego;

import java.awt.Color;
import java.awt.Rectangle;

import entorno.Entorno;
import utils.Lista;

public class Mikasa {
	private double x;
	private double y;
	private double radio;
	private double angulo;
	private double distancia;
	private Lista<Disparo> disparos;
	private int ultimoDisparo;


	public Mikasa(double x, double y, double radio, double angulo) {
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.angulo = angulo;
		this.disparos = new Lista<Disparo>();

	}

	public void dibujar(Entorno e) {
		e.dibujarCirculo(this.x, this.y, this.radio, Color.ORANGE);
	}

	// movimiento
	public void moverAdelante() {
		if (this.x > 20 && ((this.x) < 820) && this.y < 590 && ((this.y) > -30)) {
			this.x += Math.cos(this.angulo) * 1.5;
			this.y += Math.sin(this.angulo) * 1.5;
		}
		if (this.x <= 20) {
			this.x = this.x + 5;
		}
		if ((this.x) >= 800) {
			this.x = this.x - 5;
		}
		if (this.y >= 580) {
			this.y = this.y - 5;
		}
		if ((this.y) <= 0) {
			this.y = this.y + 5;
		}
	}

	public void moverDerecha() {
		this.angulo += 0.08;
	}

	public void moverIzquierda() {
		this.angulo -= 0.08;
	}

	public void moverAtras() {
		this.x -= Math.cos(this.angulo) * 1.5;
		this.y -= Math.sin(this.angulo) * 1.5;

	}

	public void distancia(double x1, double y1, double x2, double y2) {
		this.distancia = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}
	
	public void disparar(int contadorTicks) {
		this.disparos.agregarAtras(new Disparo(this.x, this.y, this.angulo));
		this.ultimoDisparo = contadorTicks;
	}
	
	public int getUltimoDisparo() {
		return this.ultimoDisparo;
	}
	public Lista<Disparo> getDisparos() {
		return this.disparos;
	}

	public void setRayos(Lista<Disparo> disparos) {
		this.disparos = disparos;
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

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

}