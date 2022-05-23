package utils;

import java.util.function.Function;

public class Lista<T> {
	Nodo<T> primero;
	private int proximoId;

	public Lista() {
		this.primero = null;
		this.proximoId = 0;
	}

	public void mostrar() {
		Nodo<T> actual = this.primero;
		System.out.print("[");
		while (actual != null) {
			System.out.print(actual.id + " ");
			actual = actual.siguiente;
		}
		System.out.println("]");
	}

	public void agregarAtras(T n) {
		Nodo<T> nuevo = new Nodo<T>(n, proximoId);
		this.proximoId += 1;
		if (this.primero == null) {
			this.primero = nuevo;
		} else {
			Nodo<T> actual = this.primero;
			while (actual.siguiente != null) {
				actual = actual.siguiente;
			}
			actual.siguiente = nuevo;
		}
	}

	public void agregarAdelante(T n) {
		Nodo<T> nuevo = new Nodo<T>(n, proximoId);
		this.proximoId += 1;
		if (this.primero == null) {
			this.primero = nuevo;
		} else {
			nuevo.siguiente = this.primero;
			this.primero = nuevo;
		}
	}

	public int largo() {
		Nodo<T> actual = this.primero;
		int cant = 0;
		while (actual != null) {
			cant++;
			actual = actual.siguiente;
		}
		return cant;
	}

	public void quitarDePosicion(int pos) {
		Nodo<T> actual = this.primero;
		if (this.primero != null && pos == 0) {
			this.primero = this.primero.siguiente;
		}
		if (pos >= this.largo())
			return;
		int i = 1;
		while (actual != null && actual.siguiente != null) {
			if (i == pos) {
				actual.siguiente = actual.siguiente.siguiente;
				return;
			}
			actual = actual.siguiente;
			i++;
		}
	}

	public void quitarPorId(int id) {
		Nodo<T> actual = this.primero;
		if (this.primero != null && this.primero.id == id) {
			this.primero = this.primero.siguiente;
		}
		while (actual != null && actual.siguiente != null) {
			if (actual.siguiente.id == id) {
				actual.siguiente = actual.siguiente.siguiente;
				return;
			}
			actual = actual.siguiente;
		}
	}

	public T obtenerDePosicion(int pos) {
		Nodo<T> actual = this.primero;
		int i = 0;
		while (actual != null && actual.siguiente != null) {
			if (i == pos) {
				return actual.elemento;
			}
			actual = actual.siguiente;
			i++;
		}
		return null;
	}

	public T obtenerPorId(int id) {
		Nodo<T> actual = this.primero;
		while (actual != null && actual.siguiente != null) {
			if (actual.id == id) {
				return actual.elemento;
			}
			actual = actual.siguiente;
		}
		return null;
	}

	public void forEachNodo(Function<Nodo<T>, Void> callback) {
		Nodo<T> actual = this.primero;
		while (actual != null) {
			callback.apply(actual);
			actual = actual.siguiente;
		}
	}

	public void forEachElement(Function<T, Void> callback) {
		Nodo<T> actual = this.primero;
		while (actual != null) {
			callback.apply(actual.elemento);
			actual = actual.siguiente;
		}
	}
}
