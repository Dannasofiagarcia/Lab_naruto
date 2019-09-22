package model;

public class ExceptionNombreIgual extends Exception {

	ExceptionNombreIgual(String name){
		super("No pudo ser agregado porque el nombre " + name +  "ya existe");
	}
}
