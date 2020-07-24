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
package jzero.admin.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import jzero.admin.security.model.User;

/**
 * shiro工具类
 * 
 * @author yuqs
 * @since 0.1
 */
public class ShiroUtils{
	/**
	 * 返回当前登录的认证实体ID
	 * 
	 * @return
	 */
	public static Integer getUserId() {
		User user = getuser();
		if (user != null)
			return user.getInt("id");
		return -1;
	}

	/**
	 * 分组
	 * 
	 * @return
	 */
	public static Integer getGroupId() {
		User user = getUser();
		Integer grouptype = user.getInt("grouptype");
		if (user != null && grouptype != null)
			return grouptype;
		return -1;
	}

	public static User getUser() {
		User user = getuser();
		if (user != null)
			return user;
		return null;
	}

	/**
	 * 返回当前登录的认证实体部门ID
	 * 
	 * @return
	 */
	public static Integer getOrgId() {
		User user = getUser();
		Integer org = user.getInt("org");
		if (user != null && org != null)
			return org;
		return -1;
	}

	/**
	 * 获取用户的学院信息
	 * 
	 * @Description
	 * @author hsongjiang
	 * @date 2015年10月6日 下午8:36:39
	 * @return
	 */
	public static Integer getAcademyId() {
		User user = getUser();
		Integer org = user.getInt("academy");
		if (user != null && org != null)
			return org;
		return -1;
	}

	/**
	 * 获取当前登录的认证实体
	 * 
	 * @return
	 */
	public static User getuser() {
		Subject subject = SecurityUtils.getSubject();
		return (User) subject.getPrincipal();
	}


	/**
	 * 获取当前认证实体的中文名称
	 * 
	 * @return
	 */
	public static String getFullname() {
		User user = getuser();
		if (user != null)
			return user.toString();
		return "";
	}

	/**
	 * 获取当前认证的实体部门名称
	 * 
	 * @return
	 */
	public static String getOrgName() {
		User user = getUser();
		if (user != null)
			return user.get("orgName");
		return "";
	}

	/**
	 * 获取当前用户的realname
	 */

	public static String getRealName() {
		User user = getUser();
		if (user != null)
			return user.get("realname");
		return "";
	}

	public static String getNickName() {
		User user = getUser();
		if (user != null)
			return user.get("nickname");
		return "";
	}

	/**
	 * 获取密码
	 * 
	 * @Description
	 * @author hsongjiang
	 * @date 2015年9月19日 上午9:20:34
	 * @return
	 */
	public static String getPlainPassword() {
		User user = getUser();
		if (user != null)
			return user.getStr("plainPassword");
		return "";
	}

	/**
	 * 获取头像
	 * 
	 * @Description
	 * @author hsongjiang
	 * @date 2015年9月19日 上午9:20:34
	 * @return
	 */
	public static String getAvatar() {
		User user = getUser();
		if (user != null)
			return user.getStr("avatar");
		return "";
	}

	public static int getGroupType() {
		User user = getUser();
		if (user != null)
			return user.getInt("grouptype");
		return 0;
	}
}
