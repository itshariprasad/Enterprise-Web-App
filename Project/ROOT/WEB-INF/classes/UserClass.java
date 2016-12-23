
import java.util.HashMap;

public class UserClass implements java.io.Serializable{
	private String userid;
	private String password;
	
	public UserClass(String name,String password){
		this.userid=userid;
		this.password=password;
		
	}
	public UserClass(){
	}
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}