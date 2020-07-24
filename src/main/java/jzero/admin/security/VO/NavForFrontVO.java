/**
 * 
 */
package jzero.admin.security.VO;

import java.util.List;

import jzero.admin.model.AsFrontNav;

/**
 * @Description 给前台新闻展示页面的VO
 * @author LiuMing
 * @data 2018年9月29日 上午10:57:13
 */
public class NavForFrontVO {

	private Integer id;
	

	private String name;
	private Boolean isClosed;
	private String url;
	private String backImage;

	private List<AsFrontNav> childs;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBackImage() {
		return backImage;
	}

	public void setBackImage(String backImage) {
		this.backImage = backImage;
	}

	public List<AsFrontNav> getChilds() {
		return childs;
	}

	public void setChilds(List<AsFrontNav> childs) {
		this.childs = childs;
	}

}
