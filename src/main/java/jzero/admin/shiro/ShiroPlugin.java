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

import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.jfinal.plugin.IPlugin;

import jzero.admin.security.model.Menu;
import jzero.base.shiro.ShiroDbFilter;


/**
 * 支持Shiro的插件
 * @author yuqs
 * @since 0.1
 */
public class ShiroPlugin extends ShiroDbFilter implements IPlugin {

	public boolean start() {
		if(manager == null) return false;
		List<Menu> menus = Menu.dao.allMenu();
		for(Menu menu:menus){
			String source = menu.getStr("url");
			String authority = menu.getStr("prems");
			if(StringUtils.isEmpty(source)) {
				continue;
			}
			if(source.indexOf(";") != -1) {
				String[] sources = source.split(";");
				for(String singleSource : sources) {
					createChain(manager, singleSource, authority);
				}
			} else {
				createChain(manager, source, authority);
			}
		}
		manager.createChain("/**", "user");
		return true;
	}

	public boolean stop() {
		return true;
	}
	
}
