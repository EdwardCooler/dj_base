package jzero.admin.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jfinal.kit.JsonKit;
import com.jfinal.kit.StrKit;

public class ListFilePathKit {

	private static ArrayList<Map<String, Object>> pathes = new ArrayList<>();
	
	private static Map<String, File> filesCache = new HashMap<>();
	
	public static ArrayList<Map<String, Object>> getZtreeNodes(String rootPath) {
		File logdir = new File(rootPath);
		pathes.clear();
		filesCache.clear();
		if (StrKit.isBlank(rootPath) || !logdir.exists() || !logdir.isDirectory()) {
			return pathes;
		}
		pathPath(logdir, "0", 1);
		
		return pathes;
	}
	
	/**
	 * 遍历文件路径
	 * @param path
	 * @param parentid
	 * @param level
	 * @Description TODO
	 * @Author 随风丶小白
	 */
	private static void pathPath(File path, String parentid, int level) {
		File[] files = path.listFiles();
		for (int i = 0; i < files.length; i++) {
			Map<String, Object> map = new HashMap<>();
			
			File currentFile = files[i];
			
			map.put("id", parentid+"_"+i);
			map.put("pId", parentid);
			map.put("name", currentFile.getName());
			
			if (currentFile.isDirectory() && currentFile.listFiles().length==0) {
				map.put("isParent", true);
			}else if (level==1 && currentFile.isDirectory()) {
				map.put("open", true);
			}
			
			//点击判断
			if (currentFile.isFile()) {
				map.put("click", true);
				filesCache.put((String)map.get("id"), currentFile);
			}else {
				map.put("click", false);
			}
			
			pathes.add(map);
			if (currentFile.isDirectory()) {
				pathPath(currentFile, (String)map.get("id"), (level+1));
			}
		}
	}
	
	/**
	 * 获取对应的file对象
	 * @param ztreeID
	 * @return
	 * @Description TODO
	 * @Author 随风丶小白
	 */
	public static File getFile(String ztreeID) {
		if (StrKit.isBlank(ztreeID)) {
			return null;
		}
		return filesCache.get(ztreeID.trim());
	}
	
	public static void main(String[] args) {
		System.out.println(JsonKit.toJson(ListFilePathKit.getZtreeNodes("C:\\Users\\bornjhon\\Desktop\\ztree")));
	}
}
