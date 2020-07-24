package jzero.admin.security.bean;
/**
 * 菜单Json数据组装
 * @author mc
 *
 */
public class MenuJson { 
	
     private String id = ""; 
     private String name = "";
     private String target = "navtab";
     private String url = "";
     
     
    public String getId() {
 		return id;
 	}
 	public void setId(String id) {
 		this.id = id;
 	}
 	public String getName() {
 		return name;
 	}
 	public void setName(String name) {
 		this.name = name;
 	}
 	public String getTarget() {
 		return target;
 	}
 	public void setTarget(String target) {
 		this.target = target;
 	}
 	public String getUrl() {
 		return url;
 	}
 	public void setUrl(String url) {
 		this.url = url;
 	}
}
