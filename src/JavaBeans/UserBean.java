package JavaBeans;

import java.io.Serializable;

public class UserBean implements Serializable {
	
	private static final long serialVersionUID=1L;
	private String address;
	private String id;
	private String name;
	private String password;
	
	public UserBean() {
		
	}
	public UserBean(String id,String password,String name,String address) {
		this.address=address;
		this.id=id;
		this.name=name;
		this.password=password;
		
	}
	
	public String getAddress() {
		return address;
				
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	
	public String toString() {
		return "UserBean[id="+id+",password="+password+",name="+name+",address="+address+"]";
	}
	
	

}
