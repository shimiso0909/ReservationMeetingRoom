package JavaBeans;

import java.io.Serializable;

public class RoomBean implements Serializable {
	
	private static final long serialVersionUID=1L;
	private String id;
	private String name;
	
	public RoomBean() {}
	
	public RoomBean(String id,String name) {
		this.id=id;
		this.name=name;
		
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return "RoomBean[id="+id+",name="+name+"]";
	}

}
