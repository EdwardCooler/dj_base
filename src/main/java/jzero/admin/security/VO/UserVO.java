/**
 * 
 */
package jzero.admin.security.VO;

/**
 * @Description 用户基本信息VO
 * @author LiuMing
 * @data 2018年9月27日 上午8:58:16
 */

public class UserVO {
	private String username;
	private String realname;
	private String email;
	private String nickname;
	private String telephone;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
