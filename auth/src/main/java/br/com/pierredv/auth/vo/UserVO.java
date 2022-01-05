package br.com.pierredv.auth.vo;

import java.io.Serializable;
import java.util.Objects;

public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	
	public UserVO() {
		
	}
	
	public UserVO(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserVO [userName=" + userName + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVO other = (UserVO) obj;
		return Objects.equals(userName, other.userName);
	}
}
