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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import jzero.base.common.utils.Digests;
import jzero.base.common.utils.EncodeUtils;

/**
 * 用户模型
 * 
 * @author yuqs
 * @since 0.1
 */
public class User extends Model<User> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8781209142247805658L;
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	public static final User dao = new User();

	/**
	 * 
	 * @category 查询当月新增用户
	 * @Description:
	 * @Auother: 宋泽明
	 * @param date
	 * @return
	 * @date 2018年6月14日
	 */
	public List<User> findNewUserForMonth(String start, String end) {
		String sql = " select  DAY(regtime) as time,count(id) num "
				+ " from sec_user su inner join sec_role_user sru on su.id=sru.user_id "
				+ " where sru.role_id=2 and regtime between ? and ? group by time";
		return find(sql, start, end);
	}

	/**
	 * re
	 * 
	 * @category 查询所有用户
	 * @Description:
	 * @Auother: 宋泽明
	 * @return
	 * @date 2018年6月14日
	 */
	public List<User> findAllUser() {
		String sql = "  select su.id,su.username,su.nickname "
				+ " from sec_user su inner join sec_role_user sru on su.id=sru.user_id " + " where sru.role_id=2";
		return find(sql);
	}

	public Page<User> paginate(int pageNumber, int pageSize, User user) {
		StringBuilder from = new StringBuilder("from sec_user u left join sec_org o on u.org=o.id where 1=1 ");
		List<String> params = new ArrayList<String>();
		String username = user.getStr("username");
		String fullname = user.getStr("fullname");
		if (StringUtils.isNotEmpty(username)) {
			from.append(" and u.username=? ");
			params.add(username);
		}
		if (StringUtils.isNotEmpty(fullname)) {
			from.append(" and u.fullname=? ");
			params.add(fullname);
		}
		from.append(" order by id desc");
		return paginate(pageNumber, pageSize, "select u.*,o.name as orgName", from.toString(), params.toArray());
	}

	/***
	 * 查询自己的员工，分页
	 * 
	 * @Description
	 * @author zs
	 * @date 2018年5月18日 下午2:23:02
	 * @param pageNumber
	 * @param pageSize
	 * @param word
	 * @param uid
	 * @return
	 */
	public Page<User> employeesPage(int pageNumber, int pageSize, String word, int uid) {

		String sql = "from sec_user  where puid=?";
		if (word.length() > 0 && !word.isEmpty()) {
			sql += " and (nickname like '%" + word + "%' or username like '%" + word + "%' )";
		}
		return paginate(pageNumber, pageSize, "select * ", sql, uid);
	}

	/***
	 * 查询员工不分页
	 * 
	 * @Description
	 * @author zs
	 * @date 2018年5月18日 下午2:23:58
	 * @param word
	 * @param uid
	 * @return
	 */
	public List<User> employeesList(String word, int uid) {

		String sql = "select * from sec_user  where puid=?";
		if (word.length() > 0 && !word.isEmpty()) {
			sql += " and (nickname like '%" + word + "%' or username like '%" + word + "%' )";
		}
		return find(sql, uid);
	}

	/**
	 * 通过名字找到用户的基本信息
	 * 
	 * @param name
	 * @return
	 */

	public User getBaseInfoByName(String name) {
		return User.dao.findFirst("select * from sec_user where username=? or nickname=?", name, name);
	}

	/**
	 * 
	 * @Description
	 * @author Administrator
	 * @param name
	 * @return
	 */
	public User getBaseInfoByEmail(String email) {
		return User.dao.findFirst("select * from sec_user where email=?", email);
	}

	public User getBaseInfoByRealName(String name) {
		return User.dao.findFirst("select * from sec_user where realname=?", name);
	}

	public User getByName(String name) {
		return User.dao.findFirst(
				"select u.*,o.name as orgName from sec_user u left join sec_org o on u.org=o.id where u.username=?",
				name);
	}

	/**
	 * 
	 * @param id
	 * @return 用户表中org直接存的是班级,以后需要更改 modify by hsongjiang 2015-3-1
	 */
	public User get(Integer id) {
		return User.dao.findFirst(
				"select u.* , o.name as orgName from sec_user u left join sec_org o on u.org=o.id where u.id = ?", id);
		// return dao.findById(id);
	}

	/**
	 * 获取学生所有信息
	 * 
	 * @author hsongjiang
	 * 
	 */

	public User getStudentInfo(Integer id) {
		return User.dao.findFirst(
				"select u.*,o.*,g.name as orgName  from sec_user u left join sec_user_student_ext o on u.id=o.id left join sec_org g on  u.org = g.id where u.id=?",
				id);
	}

	/**
	 * 获取某个部门的学生信息
	 * 
	 * @param orgId
	 * @return
	 * 
	 * @update by hsongjiang 2015-03-23,sec_user中部门
	 */
	public List<User> getStudentByOrg(Integer orgId) {
		String sql = "select u.*,o.name as orgName,e.parentactive,e.nation,s.totalScore,s.avgScore,s.mustAvgScore,s.acAvgScore  from sec_user u left join sec_user_student_ext e on  u.id=e.id left join sec_org o on u.org=o.id left join oa_scores s on u.id = s.uid";
		if (orgId != null && orgId > 0) {
			sql += " where o.id=" + orgId + " and u.grouptype like 'student' ";
		}
		return User.dao.find(sql);
	}

	/**
	 * 获取某个学生信息
	 * 
	 * @param orgId
	 * @return
	 */
	public User getOneStudentByid(Integer Id) {
		String sql = "select u.*,o.name as orgName,e.*,e.id as extid  from sec_user u left join sec_user_student_ext e on  u.id=e.id left join sec_org o on u.org=o.id where u.id="
				+ Id;

		return User.dao.findFirst(sql);
	}

	/**
	 * 手机端，通过某个班级，查找该班级学生姓名列表
	 * 
	 * @param orgId:班级ID
	 * @return
	 */
	public List<User> getStudentNameByOrgMobile(Integer orgId) {
		String sql = "select u.id as id,u.realname as realname,u.gender as gender,o.name as orgName from sec_user u left join sec_org o on u.org=o.id ";
		if (orgId != null && orgId > 0) {
			sql += " where o.id=" + orgId + " and u.grouptype like 'student' ";
		}
		return User.dao.find(sql);
	}

	public List<User> getParentByOrg(Integer orgId) {
		String sql = "select u.*,o.name as orgName from sec_user u left join sec_org o on u.org=o.id ";
		if (orgId != null && orgId > 0) {
			sql += " where o.id=" + orgId + " and u.grouptype like 'parent' ";
		}
		return User.dao.find(sql);
	}

	public List<User> getByOrg(Integer orgId) {
		String sql = "select u.*,o.name as orgName from sec_user u left join sec_org o on u.org=o.id ";
		if (orgId != null && orgId > 0) {
			sql += " where u.org=" + orgId;
		}
		return User.dao.find(sql);
	}

	public List<Role> getRoles(Integer id) {
		return Role.dao.find("select r.* from sec_role r " + "LEFT JOIN sec_role_user ru ON r.id=ru.role_id "
				+ "LEFT JOIN sec_user u ON u.id=ru.user_id " + "WHERE u.id=?", id);
	}

	/**
	 * 
	 * 
	 * 
	 */

	public void insertCascade(Integer id, Integer roleId) {
		Db.update("insert into sec_role_user (user_id, role_id) values (?,?)", id, roleId);
	}

	public void deleteCascade(Integer id) {
		Db.update("delete from sec_role_user where user_id = ?", id);
	}

	/**
	 * 根据用户ID查询该用户所拥有的权限列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getAuthoritiesName(Integer userId) {
		String sql = "select a.name from sec_user u " + " left outer join sec_role_user ru on u.id=ru.user_id "
				+ " left outer join sec_role r on ru.role_id=r.id "
				+ " left outer join sec_role_authority ra on r.id = ra.role_id "
				+ " left outer join sec_authority a on ra.authority_id = a.id " + " where u.id=? ";
		return Db.query(sql, userId);
	}

	/**
	 * 
	 * @category 查询权限列表
	 * @author 9龙
	 */
	public List<String> JzeroGetAuthoritiesName(Integer userId) {
		String sql = "select sm.prems from sec_user u inner join sec_role_user ru on u.id=ru.user_id "
				+ " inner join sec_role_menu rm on rm.role_id=ru.role_id"
				+ " inner join sec_menu sm on sm.id=rm.menu_id" + " where u.id=? and sm.prems is not null";
		return Db.query(sql, userId);
	}

	/**
	 * 根据用户ID查询该用户所拥有的角色列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getRolesName(Integer userId) {
		String sql = "select r.name from sec_user u " + " left outer join sec_role_user ru on u.id=ru.user_id "
				+ " left outer join sec_role r on ru.role_id=r.id " + " where u.id=? ";
		return Db.query(sql, userId);
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	public void entryptPassword(User user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.set("salt", EncodeUtils.hexEncode(salt));

		byte[] hashPassword = Digests.sha1(user.getStr("plainPassword").getBytes(), salt, HASH_INTERATIONS);
		user.set("password", EncodeUtils.hexEncode(hashPassword));
	}

	/**
	 * 用于加密使用,前期使用明文
	 * 
	 * @param user
	 * @param password
	 */
	public void entryptPasswordWithSalt(String password) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		set("salt", EncodeUtils.hexEncode(salt));

		byte[] hashPassword = Digests.sha1(password.getBytes(), salt, HASH_INTERATIONS);
		set("password", EncodeUtils.hexEncode(hashPassword));
	}

	/**
	 * admin查询用户
	 * 
	 * @param realname
	 * @param username
	 * @param orgId
	 * @param orgDepartment
	 * @param pageNum
	 * @param numperPage
	 * @param uid
	 * @return
	 */
	public Page<User> paginateUserList(String username, int grouptype, int pageNum, int numperPage, Integer uid) {
		String SQLlimitStr = ("from sec_user where 1=1");

		if (StringUtils.isNotEmpty(username)) {
			SQLlimitStr += " and username like '%" + username + "%' ";
		}
		if (grouptype != -1) {
			SQLlimitStr += " and grouptype=" + grouptype;
		}

		SQLlimitStr += " and id<>" + uid;

		SQLlimitStr += " order by id asc";
		return dao.paginate(pageNum, numperPage, "select *", SQLlimitStr);
	}

	/**
	 * admin查询用户
	 * 
	 * @param realname
	 * @param username
	 * @param orgId
	 * @param orgDepartment
	 * @param pageNum
	 * @param numperPage
	 * @return
	 */
	public Page<User> paginateUserList(String username, int pageNum, int numperPage) {
		String SQLlimitStr = ("from sec_user where 1=1");

		if (StringUtils.isNotEmpty(username)) {
			SQLlimitStr += " and username like '%" + username + "%' ";
		}
		SQLlimitStr += " order by id asc";
		return dao.paginate(pageNum, numperPage, "select *", SQLlimitStr);
	}
	/**
	 * 学生第一登录，处理流程
	 * 
	 * 
	 */
	// public void insertFirstLoginStudent(ArrayList<String> dataList) {
	// //写入sec_user中
	// Db.update("insert into sec_user (user_id, role_id) values (?,?)", id,
	// roleId);
	//
	// }

	/**
	 * 
	 * @param userId
	 *            用户Id
	 * @return 双亲的Id
	 */
	public List<User> getMyParent(Long userId) {
		User user = dao.findById(userId);
		String sql = "select distinct id from  sec_user  where " + "username=" + "88" + user.getStr("username");
		return dao.find(sql);
	}

	/**
	 * hsongjiang 2015-03-13 家长找到自己的小孩
	 */
	public Integer getMyChildById(Integer parentId) {
		String sql = "select id from sec_user_student_ext where parentid = ? ";
		return dao.findFirst(sql, parentId).getInt("id");
	}

	public Integer getMyChildByName(String username) {
		String sql = "select id from sec_user where username = ? ";
		return dao.findFirst(sql, username).getInt("id");
	}

	/**
	 * 根据某个学生的学号（用户名）找到真实姓名 2015-03-08
	 */
	public String getRealNameByUsername(String userName) {
		return dao.findFirst("select realname from sec_user where username=?", userName).getStr("realname");
	}

	public String getRealNameByUid(Integer uid) {
		return dao.findFirst("select realname from sec_user where id=?", uid).getStr("realname");
	}

	public String getNikeNameByUid(Integer uid) {
		return dao.findFirst("select nickname from sec_user where id=?", uid).getStr("nickname");
	}

	public String getOrgNameByUserName(String userName) {
		return dao.findFirst("select org from sec_user where username=?", userName).getStr("org");
	}

	/**
	 * hsongjiang 2015-03-21 学生查找自己的 班会信息
	 * 
	 * @param userName
	 * @return
	 */
	public Integer getOrgIdByUid(Integer uid) {
		return dao.findFirst("select org from sec_user where id=?", uid).getInt("org");
	}

	/**
	 * 找到家长的ID
	 */
	public Integer getParentIdByChildId(Integer id) {
		return Db.findFirst("select parentid from sec_user_student_ext where id = ?", id).getInt("parentid");
	}

	public Integer getParentIdByChildId(String username) {
		username = "88" + username;
		return Db.findFirst("select id from sec_user where username = ?", username).getInt("id");
	}

	/**
	 * 删除教师的所有依赖关系
	 */
	public void deleteTeacherById(Integer id) {
		// Db.update("delete from oa_table_template where id=?",id);
		// Db.update("delete from oa_table where id=?",id);
		//// Db.update("delete from oa_talking where id=?",id);
	}

	/**
	 * @Description 检测该用户名是否存在
	 * @author inging44
	 * @param name
	 * @return 存在返回false，不存在返回true
	 */
	public User checkNickname(String name) {
		User nickname = User.dao.findFirst("select * from sec_user where nickname=?", name);
		return nickname;

	}

	/**
	 * @Description 根据电话取当前记录
	 * @author inging44
	 * @param tel
	 * @return User
	 */
	public User checkMobile(String tel) {
		User mobile = User.dao.findFirst("select * from sec_user where username=?", tel);
		return mobile;

	}

	/**
	 * @Description 根据电话取当前记录
	 * @author inging44
	 * @param tel
	 * @return User
	 */
	public Page<User> getWorker(int pageNumber, int pageSize, String username, String nickname, String realname) {
		String sql = "from sec_user where grouptype = \"worker\" ";
		if (username != "") {
			sql += " and username like \"%" + username + "%\"";
		}
		if (nickname != "") {
			sql += " and nickname like \"%" + nickname + "%\"";
		}
		if (realname != "") {
			sql += " and realname like \"%" + realname + "%\"";
		}
		return paginate(pageNumber, pageSize, "select *", sql);
	}

	public User getByUid(int uid) {
		String sql = "select * from sec_user where id=" + uid;
		return findFirst(sql);
	}

	/**
	 * 根据uid查找，修理工的详细信息
	 * 
	 * @Description
	 * @author hsongjiang
	 * @date 2015年9月3日 下午4:45:34
	 * @param uid
	 * @return
	 */
	public User findOneFixer(int uid) {
		String sql = "select m.username,m.id,m.avatar,m.gender,m.nickname,m.realname,n.totalpoint,n.evalution from sec_user as m left join edu_user_count as n on m.id=n.uid where m.id="
				+ uid;
		return findFirst(sql);
	}

	/**
	 * @Description 根据电话取当前记录
	 * @author inging44
	 * @param tel
	 * @return User
	 */
	public User getUser(String username) {
		return User.dao.findFirst("select * from sec_user where username=? or nickname=?", username, username);
	}

	/**
	 * 设备管理--》查看好友列表/ 搜索好友
	 * 
	 * @Description
	 * @author GuoMing
	 * @date 2016年6月13日 下午3:52:16
	 */
	public Page<User> findFriends(int pageNumber, int pageSize, Integer uid, String word) {
		String sql = " from sec_user u  INNER JOIN (select id, note, userIdTo  from xc_cool_friend as f where userId=?) as s ON u.id=s.userIdTo "
				+ " where u.id in (select userIdTo from xc_cool_friend where userId=?)";
		if (word.length() > 0 && !word.isEmpty()) {
			sql += "and u.username LIKE '%" + word + "%' or u.nickname LIKE '%" + word + "%'";
		}

		return paginate(pageNumber, pageSize, "select u.username,u.nickname,s.note,u.id as uid,s.id as sid ", sql, uid,
				uid);
	}

	public Page<User> findList(int pageNumber, int pageSize) {
		String sql = " from sec_user u INNER JOIN sec_role_user sru on sru.user_id=u.id INNER JOIN sec_role sr on sr.id=sru.role_id where sru.role_id!=1";
		return User.dao.paginate(pageNumber, pageSize,
				"select u.username,u.nickname,u.smsAlarm,sru.role_id,sr.name as roleName ", sql);
	}

	/**
	 * manager管理员查看/搜索普通用户信息
	 * 
	 * @Description
	 * @author GuoMing
	 * @date 2016年7月4日 下午7:12:18
	 * @return
	 */
	public Page<User> getUsers(Integer pageNumber, Integer pageSize, String word) {
		String select = "SELECT su.id,su.smsAlarm,sr.`description`,su.nickname,su.username,su.notes ";
		String sql = "from sec_user as su INNER JOIN sec_role_user as ru on su.id=ru.user_id"
				+ " INNER JOIN sec_role as sr on sr.id=ru.role_id where 1=1 and sr.id=2 ";
		if (word != null && word.length() > 0) {
			sql += " and su.nickname like '%" + word + "%' or su.username like '%" + word + "%'";
		}
		return paginate(pageNumber, pageSize, select, sql);

	}

	/***
	 * 根据代理商id获得用户表
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @param word
	 * @return
	 */
	public Page<User> getAgentUsers(Integer pageNumber, Integer pageSize, Integer word) {
		String select = "SELECT distinct(su.nickname),su.*";
		String sql = "from xc_cool_agent_device as ad INNER JOIN xc_cool_device as d on ad.deviceId=d.deviceId"
				+ " INNER JOIN sec_user as su on d.userId=su.id where ad.userId=?";
		return paginate(pageNumber, pageSize, select, sql, word);

	}

	/**
	 * manager管理员查看/搜索代理用户信息
	 * 
	 * @Description
	 * @author GuoMing
	 * @date 2016年7月4日 下午7:12:18
	 * @return
	 */
	public Page<User> getAgentUsers(Integer pageNumber, Integer pageSize, String word) {
		String select = "SELECT su.enabled,su.rate,su.isChecked,su.id,su.smsAlarm,sr.`description`,su.nickname,su.username,su.notes ";
		String sql = "from sec_user as su INNER JOIN sec_role_user as ru on su.id=ru.user_id"
				+ " INNER JOIN sec_role as sr on sr.id=ru.role_id where 1=1 and sr.id=4";
		if (word != null && word.length() > 0) {
			sql += " and su.nickname like '%" + word + "%' or su.username like '%" + word + "%'";
		}
		return paginate(pageNumber, pageSize, select, sql);

	}

	/**
	 * manager管理员查看/搜索审核通过的代理用户信息，用于转移代理商设备
	 * 
	 * @Description
	 * @author GuoMing
	 * @date 2016年7月4日 下午7:12:18
	 * @return
	 */
	public Page<User> getAgentUsers(Integer pageNumber, Integer pageSize, String word, Integer aid) {
		String select = "SELECT su.enabled,su.rate,su.isChecked,su.id,su.smsAlarm,sr.`description`,su.nickname,su.username,su.notes ";
		String sql = "from sec_user as su INNER JOIN sec_role_user as ru on su.id=ru.user_id"
				+ " INNER JOIN sec_role as sr on sr.id=ru.role_id where 1=1 and su.isChecked=1 and su.enabled=1 and sr.id=4 and su.id!=?";
		if (word != null && word.length() > 0) {
			sql += " and su.nickname like '%" + word + "%'";
		}
		return paginate(pageNumber, pageSize, select, sql, aid);

	}

	/**
	 * 转移好友 查看自己申请的以及别人发出的好友申请（已确认的好友）
	 * 
	 * @Description
	 * @author GuoMing
	 * @date 2016年8月25日 下午5:02:00
	 * @param uid
	 * @param type
	 * @return
	 */
	public List<User> findFriends(Integer uid, Integer type) {
		String sql;
		if (type == 1) {
			sql = " select u.username,u.nickname,u.id as uid  from sec_user u "
					+ " INNER JOIN xc_cool_friend cf on u.id=cf.userIdTo"
					+ " where (cf.isCheck=1 and cf.userId=? and cf.userIdTo!=?)";
		} else {
			sql = " select u.username,u.nickname,u.id as uid from sec_user u "
					+ " INNER JOIN xc_cool_friend cf on u.id=cf.userId"
					+ " where (cf.isCheck=1 and cf.userId!=? and cf.userIdTo=?)";
		}
		return find(sql, uid, uid);
	}

	public User checkEmail(String email) {
		String sql = "select * from sec_user where email=?";
		return findFirst(sql, email);
	}

	/**
	 * 判断手机号是否已注册
	 * 
	 * @Description
	 * @author GuoMing
	 * @date 2017年2月16日 上午9:56:48
	 * @param tel
	 */
	public User findUserByTel(String tel) {
		return findFirst("select * from sec_user where username=?", tel);
	}

	public User findbyNickName(String word) {

		return findFirst("select * from sec_user where nickname=?", word);
	}

	public User findbyOpenId(String word) {

		return findFirst("select * from sec_user where qqopenid=?", word);
	}

	public User findFirstByToken(String token) {
		return findFirst("select * from sec_user where apiToken=?", token);
	}

	public Integer getId() {
		return this.getInt("id");
	}

}
