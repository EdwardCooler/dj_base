/**
 * 
 */
package jzero.admin.security.VO;

import java.util.Date;

/**
 * @Description 导航下展示的具体内容VO
 * @author LiuMing
 * @data 2018年9月28日 下午2:31:49
 */
public class ContentVO {

	@Override
	public String toString() {
		return "ContentVO [title=" + title + ", author=" + author + ", updateTime=" + updateTime + ", content="
				+ content + ", newsCatId=" + newsCatId +  ", navId=" + navId + "]";
	}

	private Integer id;
	private String title;
	private String author;
	private Date updateTime;
	private String content;
	private Integer newsCatId;
	private String newsCatName;// 对应新闻分类的名字

	private Integer navId;// 对应的导航的id
	private String navName;// 对应导航的名字

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNewsCatName() {
		return newsCatName;
	}

	public void setNewsCatName(String newsCatName) {
		this.newsCatName = newsCatName;
	}

	public String getNavName() {
		return navName;
	}

	public void setNavName(String navName) {
		this.navName = navName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getNewsCatId() {
		return newsCatId;
	}

	public void setNewsCatId(Integer newsCatId) {
		this.newsCatId = newsCatId;
	}

	public Integer getNavId() {
		return navId;
	}

	public void setNavId(Integer navId) {
		this.navId = navId;
	}

}
