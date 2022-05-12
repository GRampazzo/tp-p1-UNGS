package juego;

import java.awt.Color;

import entorno.Entorno;

public class Suero {
	private double x;
	private double y;
	private double radio;

	public Suero(double x, double y, double radio) {
		this.x = x;
		this.y = y;
		this.radio = radio;

	}
	public void dibujar(Entorno e) {
		e.dibujarCirculo(this.x, this.y, this.radio, Color.RED);
	}

	public boolean colision(double x1, double y1,double x2, double y2, double dist) {
		return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)< dist*dist;
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
	
}
