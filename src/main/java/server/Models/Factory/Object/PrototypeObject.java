package server.Models.Factory.Object;

public abstract class PrototypeObject implements PrototypeInterface,Cloneable{
	@Override
	public PrototypeInterface clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (PrototypeInterface) super.clone();
	}
}
