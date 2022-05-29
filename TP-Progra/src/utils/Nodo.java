package utils;

public class Nodo<T> {
	private T elemento;
	private Nodo<T> siguiente;
	private int id;

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

	public Nodo<T> getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}

	public void setElemento(T elemento) {
		this.elemento = elemento;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
