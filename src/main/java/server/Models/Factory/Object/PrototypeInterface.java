package server.Models.Factory.Object;

// Prototype Design Pattern
public interface PrototypeInterface {
	PrototypeInterface clone() throws CloneNotSupportedException;
}
