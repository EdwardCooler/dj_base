/**
 * 
 */
package jzero.admin.security.VO;

/**
 * @Description 具体的导航子菜单实体类
 * @author LiuMing
 * @data 2018年9月27日 下午3:00:07
 */
public class ChildVO {

	private Integer id;

	private String name;
	private Integer parentNav;
	private Boolean isClosed;
	private String url;
	private String backImage;

	public String getBackImage() {
		return backImage;
	}

	public void setBackImage(String backImage) {
		this.backImage = backImage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentNav() {
		return parentNav;
	}

	public void setParentNav(Integer parentNav) {
		this.parentNav = parentNav;
	}

}
