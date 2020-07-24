/**
 * 
 */
package jzero.admin.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Page;

import jzero.admin.model.base.BaseAsFrontNav;
import jzero.admin.security.VO.NavForFrontVO;
import jzero.base.layui.LayuiRender;

/**
 * @Description 导航模型
 * @author LiuMing
 * @data 2018年9月27日 上午9:59:59
 */
public class AsFrontNav extends BaseAsFrontNav<AsFrontNav>  {
	private static final long serialVersionUID = -2650243291528796327L;
	public static final AsFrontNav dao = new AsFrontNav();

	/**
	 * 获取所有的导航菜单
	 */
	
	
	public Page<AsFrontNav> paginate (int pageNumber, int pageSize, String name) {
		String sql = "from as_front_nav m left join as_front_nav pm on m.upid=pm.id";
		if(StringUtils.isNotEmpty(name)) {
			sql += " where m.name like '%" + name + "%' ";
		}
		sql += " order by m.id asc";
		return paginate(pageNumber, pageSize, "select m.*,pm.name as parentName", sql);
	}

	
	/**
	 * 
	 * @Description 進入新增导航的添加页面，选择父级导航的时候需要回显所有的导航列表
	 * @author LiuMing
	 * @data 2018年9月28日 下午2:00:30
	 */
	public List<AsFrontNav> addNav() {
		return dao.find("select id,name from as_front_nav where upid = 0");
	}

	/**
	 * @Description 保存新增导航
	 * @author LiuMing
	 * @data 2018年9月27日 下午4:33:50
	 */
	public LayuiRender saveNav(AsFrontNav nav) {
		nav.set("dateline", new Date());
		nav.set("isClosed", nav.get("isClosed")==null?false:true);
		nav.set("blank", nav.get("blank")==null?false:true);
		boolean result = nav.save();
		if (result) {
			return LayuiRender.success("保存成功");
		}
		return LayuiRender.error("保存失败");
	}

	/**
	 * 
	 * @Description 删除导航(需要判断是否有所属的下级导航)
	 * @author LiuMing
	 * @data 2018年9月27日 下午5:11:01
	 */
	public LayuiRender delNav(Integer id) {
		AsFrontNav nav = dao.findById(id);
		Integer upid = nav.get("upid");
		if (upid == 0) {// 表示是最上级导航 需要判断有没有下级导航
			List<AsFrontNav> find = dao.find("select id from as_front_nav where upid=?", upid);
			if (find.size() > 0) {
				return LayuiRender.error("请先删除所属下级导航");
			}
		}
		boolean result = dao.deleteById(id);
		if (result) {
			return LayuiRender.success("删除成功");
		}
		return LayuiRender.error("删除失败");
	}

	
	/**
	 * 
	 * @Description 进入编辑导航页面回显该导航信息
	 * @author LiuMing
	 * @data 2018年9月28日 下午2:12:31
	 */
	public AsFrontNav editNav(Integer id) {
		return dao.findById(id);
	}
	/**
	 * 
	 * @Description 更新导航信息
	 * @author LiuMing
	 * @data 2018年9月27日 下午5:30:58
	 */
	public LayuiRender updateNav(AsFrontNav nav) {
		
		nav.set("isClosed", nav.get("isClosed")==null?false:true);
		nav.set("blank", nav.get("blank")==null?false:true);
		nav.set("updateTime", new Date());
		
		boolean result = nav.update();
		if (result) {
			return LayuiRender.success("更新成功");
		}
		return LayuiRender.error("更新失败");
	}
	
	
	/**
	 * 
	 * @Description 给前台页面返回导航列表
	 * @author LiuMing
	 * @return 
	 * @data 2018年9月29日 上午10:50:23
	 */
	public List<NavForFrontVO> navForfront() {
		List<NavForFrontVO> frontVOs=new ArrayList<>();
		
		List<AsFrontNav> one = dao.find("select * from as_front_nav where upid=0");
		one.forEach((o)->{
			Integer pId = o.get("id");
			
			NavForFrontVO frontVO=new NavForFrontVO();
			frontVO.setBackImage(o.get("backImage"));
			frontVO.setIsClosed(o.get("isClosed"));
			frontVO.setId(pId);
			frontVO.setName(o.get("name"));
			frontVO.setUrl(o.get("url"));
			
			List<AsFrontNav> two = dao.find("select * from as_front_nav where upid=?",pId);
			frontVO.setChilds(two);
			
			frontVOs.add(frontVO);
		});
		return frontVOs;
	}	
	public List<AsFrontNav> forContent() {
		return AsFrontNav.dao.find("select id,name from as_front_nav");
	}
	/**
	 * 
	 * @category 获取所有一级菜单
	 * @author MC
	 * @date 2018年10月8日 下午2:21:00
	 */
	public List<AsFrontNav> findFirstNav() {
		return AsFrontNav.dao.find("select * from as_front_nav where upid=0 and isClosed = 1 order by orders");
	}
	/**
	 * 
	 * @category 获取所有菜单
	 * @author MC
	 * @date 2018年10月8日 下午2:21:00
	 */
	public List<AsFrontNav> findAllNav() {
		return AsFrontNav.dao.find("select * from as_front_nav where isClosed = 1 order by orders");
	}
	/**
	 * 
	 * @category 根据upid获取二级菜单
	 * @author MC
	 * @date 2018年10月8日 下午2:21:00
	 */
	public List<AsFrontNav> findSecondNav(int upid) {
		return AsFrontNav.dao.find("select * from as_front_nav where upid=? and isClosed = 1 order by orders",upid);
	}
	
}
