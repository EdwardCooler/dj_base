package gf.utils;

public class StringUtils {

	/**
	 * 输入图片地址，处理为缩略图地址
	 * @Description 
	 * @author hsongjiang
	 * @date 2019年6月28日 下午5:20:38 
	 * @param picture
	 * @return
	 */
	public static String thumbPicture(String picture) {
		if(picture ==null || picture.isEmpty()) {
			return "/static/image/default.png";
		}
		int postion = picture.lastIndexOf('/');
		String thumb = picture.substring(0, postion+1)+"thumb_"+picture.substring(postion+1);
		return thumb;
	}
	///upload/kindeditorthumb_/1561692437062.jpg
	public static void main(String [] args) {
		
		System.out.println(StringUtils.thumbPicture("/upload/kindeditor/1561692437062.jpg"));
	}
}
