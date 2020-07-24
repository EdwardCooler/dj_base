package jzero.admin.common.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

import jzero.base.common.utils.EduImgCompress;
import jzero.base.common.utils.Preference;
import jzero.base.layui.LayuiRender;

/**
 * 
 * @Description 所有上传图片放到这个地方:新闻图片、证件图片。
 * @author Administrator
 * @date 2015年8月15日 下午4:41:14 
 * @version V1.3.1
 */
public class PictureController extends Controller{

	/**
	 * 编辑器
	 */
	public void kindEditorUpload(){

		//用于保存上传错误，暂时用不到
		String err = "";
		
		UploadFile file = getFile();
		System.out.println("上传的文件路劲为:"+file.getFile().getPath());
		String path = getSession().getServletContext().getRealPath(Preference.UPLOAD_KINDEDTOR_DIR);
		
		File newFile;
		String fileName = "";
		if(file.getFile().length() > 5*1024*1024) {
			err = "文件不能大于5M";
			render(LayuiRender.error(err)); //大于多少时，返回错误码
			return;
		}
		else {
			//上传文件
			String type = file.getFileName().substring(file.getFileName().lastIndexOf(".")); // 获取文件的后缀
			
			fileName = System.currentTimeMillis() + type; // 对文件重命名取得的文件名+后缀
			
			newFile = new File(path+"/"+fileName);
			try {
				FileUtils.moveFile(file.getFile(), newFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println("文件重命名为:"+file.getFileName()+"路径是:"+file.getFile().getPath());
			
			if(type.equals(".jpg") || type.equals(".bmp") || type.equals(".jpeg") 
					|| type.equals(".gif") || type.equals(".png")){ //如果是图片就压缩
				
				try {
					new EduImgCompress(path+"/"+fileName,path+"/"+"thumb_"+fileName).compressPic(128, 128);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

		}
		String realPic ="/"+Preference.UPLOAD_KINDEDTOR_DIR+newFile.getName();
		System.out.println(realPic+"=============返回的路径为");
		//URL
		//直接传回完整路径
		String imgInfo="{\"error\":0,\"url\":\""+realPic+"\"}";;
		renderJson(imgInfo);
	}
	

	
	/**
	 * @Description 用于头像上传
	 * @author inging44
	 */
	public void avatarUpload(){
		//用于保存上传错误，暂时用不到
		String err = "0";
		//存储路径
		String path = getSession().getServletContext().getRealPath(Preference.UPLOAD_AVATAR_DIR);
		UploadFile file = getFile("avatar", "upload/avatar/");
		String fileName = "";
		if(file.getFile().length() > 120*1024) {
			err = "-1";
		}
		else {
			//上传文件
			String type = file.getFileName().substring(file.getFileName().lastIndexOf(".")); // 获取文件的后缀
			fileName = System.currentTimeMillis() + type; // 对文件重命名取得的文件名+后缀
			String dest = path + "/" + fileName;
			file.getFile().renameTo(new File(dest));
			
			try {
				new EduImgCompress(dest,path + "/" +"thumb_"+ fileName).compressPic(128, 128);
				
			} catch (IOException e) {
				// TODO Auto-generated catc0h block
				e.printStackTrace();
			}
		}
		
		if(StringUtils.endsWithIgnoreCase(err, "-1")){
			renderJson("{\"error\":\"" + err + "\",\"message\":\""  + "文件大于了120K" + "\"}");
		}else{
			renderJson("{\"error\":\"" + err + "\",\"message\":\""  + Preference.UPLOAD_AVATAR_DIR +  fileName + "\"}");
		}
		//直接传回完整路径
	}

	/**
	 * 
	 */
	public void picUpload(){
		//用于保存上传错误，暂时用不到
		int statusCode = 200;
		String err = "0";
		//存储路径
		String path = getSession().getServletContext().getRealPath(Preference.UPLOAD_MAP_DIR);
		UploadFile file = getFile("file", "upload/map/");
		String fileName = "";
		if(file.getFile().length() > 3*1024*1024) {
			err = "-1";
			statusCode = 300;
		}else {
			//上传文件
			String type = file.getFileName().substring(file.getFileName().lastIndexOf(".")); // 获取文件的后缀
			fileName = System.currentTimeMillis() + type; // 对文件重命名取得的文件名+后缀
			String dest = path + "/" + fileName;
			//file.getFile().renameTo(new File(dest));
			try {
				FileUtils.moveFile(file.getFile(), new File(dest));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(StringUtils.endsWithIgnoreCase(err, "-1")){
			renderJson("{\"error\":\"" + err + "\",\"statusCode\":"+statusCode+",\"message\":\""  + "文件大于了3MB" + "\"}");
		}else{
			renderJson("{\"error\":\"" + err + "\",\"statusCode\":"+statusCode+",\"filename\":\""  + Preference.UPLOAD_MAP_DIR +  fileName + "\"}");
		}
	}
	
	
}
