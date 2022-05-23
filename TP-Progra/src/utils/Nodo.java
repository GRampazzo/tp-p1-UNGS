package utils;

public class Nodo<T> {
	T elemento;
	Nodo<T> siguiente;
	int id;

	public Nodo() {
	}

	public Nodo(T elemento, int id) {
		this.elemento = elemento;
		this.siguiente = null;
		this.id = id;
	}

	public T getElemento() {
		return this.elemento;
	}

	public int getId() {
		return this.id;
	}
}
