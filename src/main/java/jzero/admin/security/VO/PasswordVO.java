/**
 * 
 */
package jzero.admin.security.VO;

/**
 * @Description 密码更改的VO
 * @author LiuMing
 * @data 2018年9月27日 上午11:07:53
 */
public class PasswordVO {
	private String oldPw;
	private String newPw;

	public String getOldPw() {
		return oldPw;
	}

	public void setOldPw(String oldPw) {
		this.oldPw = oldPw;
	}

	public String getNewPw() {
		return newPw;
	}

	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}

}
