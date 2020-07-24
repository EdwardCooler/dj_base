/*
 *  Copyright 2014-2015 snakerflow.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package jzero.admin.security.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * 部门模型
 * @author yuqs
 * @since 0.1
 */
public class Org extends Model<Org> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5018575558755643041L;
	//根部门ID号默认为0
	public static final Integer ROOT_ORG_ID = 0;
	public static final Org dao = new Org();
	
	public Page<Org> paginateList (int pageNumber, int pageSize, int orgid,String searchOrgName) {
		String sql = "from sec_org o left join sec_org op on o.parent_org=op.id ";
		
		if(!searchOrgName.isEmpty()) {
			sql +=" where o.name like '%"+searchOrgName+"%'";
		} else {
//			sql +="where o.level <> 0 ";

			if(orgid != -1){
				sql+=" and o.parent_org = "+orgid;
			}
		}
		

		sql += " order by o.parent_org,displayorder asc";
		return paginate(pageNumber, pageSize, "select o.*,op.name as parentName", sql);
	}
	
	/**
	 * 用于查找上一级
	 * @Description 
	 * @author hsongjiang
	 * @date 2015年11月13日 下午3:32:33 
	 * @param pageNumber
	 * @param pageSize
	 * @param orgid
	 * @return
	 */
	public Page<Org> paginateFirst (int pageNumber, int pageSize) {
		String sql = "from sec_org o left join sec_org op on o.parent_org=op.id ";
		sql +="where o.level <> 0 ";
		sql+=" and o.parent_org = 0 ";
	
		sql += " order by o.parent_org,displayorder asc";
		return paginate(pageNumber, pageSize, "select o.*,op.name as parentName", sql);
	}
	
	
	public Org get(Integer id) {
		return Org.dao.findFirst("select o.*,po.id as parentId, po.name as parentName from sec_org o left join sec_org po on o.parent_org=po.id where o.id=?", id);
	}
	
	public List<Org> getParentId(Integer id) {
		String sql = "select parent_org from sec_org where id = " + id;
		return find(sql);
	}
	
	public List<Org> getByParent(Integer parentId) {
		String sql = "select o.*,po.id as parentId, po.name as parentName from sec_org o inner join sec_org po on o.parent_org=po.id ";
		if(parentId != null && parentId > 0) {
			sql += " where o.parent_org=" + parentId;
		}
		return find(sql);
	}
	
	/**
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Org> getByParent_fid(Integer parentId) {
		String sql = "select name,id from sec_org";
		if(parentId != null && parentId >= 0) {
			sql += " where parent_org =" + parentId;
		}
		return find(sql);
	}
	public List<Org> getByParent_fname(Integer parentId) {
		String sql = "select parent_org,name,id from sec_org";
		if(parentId != null && parentId >= 0) {
			sql += " where parent_org =" + parentId;
		}
		return find(sql);
	}
	public List<Org> getList(){
        return dao.find("select * from sec_org order by displayorder");
    }
	
	public Org getOrgIdWithName(String name,int pid) {
		return dao.findFirst("select * from sec_org where name=? and parent_org=?",name,pid);
	}
	
	public Org getOrgWithName(String name) {
		return dao.findFirst("select * from sec_org where name like '%" + name + "%' ");
	}
	
	public int getOrgIdWithNameAndParentID(String name) {
		return dao.findFirst("select * from sec_org where name=? ",name).getInt("id");
	}
	
    /**
     * 获得所有学院名单
     * @param integer 
     */
	public List<Org> getAllFirst(Integer id) {
		return dao.find("select * from sec_org where ( parent_org = 1 or parent_org = 0) and id!=? order by displayorder",id);
	}
	
	public List<Org> getAllFirst() {
		return dao.find("select * from sec_org where ( parent_org = 1 or parent_org = 0) order by displayorder");
	}
	/**
	 * 2015-03-07
	 * 根据ID值获取部门名称
	 */
	
	public String getNameById(Integer id){
		return dao.findFirst("select name from sec_org where id =?",id).getStr("name");
	}
	
	
	/** 2015-04-08
	 * select * from sec_org as m left join sec_org as n on m.id = n.parent_org
 where m.name like '%20%' and m.parent_org = 0 order by m.displayorder  ;
 		领导查找所有班级的
	 * @param id
	 * @return
	 */
	public List<Org> getAllGrade() {
		String sql = "select m.name as parentName,n.id,n.name,n.parent_org from sec_org as m left join sec_org as n on m.id = n.parent_org  where m.name like '%20%' and m.parent_org = 0 order by m.displayorder  ";
		return dao.find(sql);
	}
	
	
	public List<Org> getAllFolderGrade() {
		String sql = "select * from sec_org as m where m.name like '%20%' and m.parent_org = 0 order by m.displayorder  ";
		return dao.find(sql);
	}
	
	/**
	 * 
	 * @Description 学院
	 * @author hyang
	 * @date 2015年10月13日 上午10:11:30 
	 * @return
	 */
	public List<Org> getAcademy(){
		String sql="select id,name from sec_org where parent_org=1";
		return find(sql);
	}
	
	/**
	 * 
	 * @Description 注册的时候获取班级信息
	 * @author hyang
	 * @date 2015年10月13日 上午10:11:06 
	 * @param parent_org
	 * @return
	 */
	public List<Org> getSecond(int upId){
		String sql="select * from sec_org where parent_org=?";
		return find(sql,upId);
	}
	/**
	 * 
	 * @Description 
	 * @author hmilysean
	 * @date 2016年1月16日 下午3:59:01 
	 * @param id
	 * @return
	 */
	public Org getLevel(int id){
		String sql="select level from sec_org where id=?";
		return dao.findFirst(sql,id);
	}
	
}
