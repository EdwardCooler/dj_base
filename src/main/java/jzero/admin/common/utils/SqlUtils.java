package jzero.admin.common.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.jfinal.ext.kit.DateKit;
import com.jfinal.kit.PathKit;

/**
 * 数据库备份还原
 * @author mc
 *
 */
public class SqlUtils {
	int type = 0; 
	static String systemlinux = "linux";
	static String systemwindos = "windos";
	/**
	 * 
	 * @category 数据库备份
	 * @author MC
	 * @date 2018年8月15日 下午5:18:31
	 */
	public static String backup(String sqlPath,String dataName) {
		String mysqldumpPath = PathKit.getWebRootPath()+"\\static\\sqlexe\\"+systemwindos+"\\mysqldump";
		//sqlPath = "C:\\Users\\Administrator\\Desktop\\ceshi\\test.sql";
		try {
        	System.out.println("开始");
            Runtime rt = Runtime.getRuntime();
 
            // 调用 调用mysql的安装目录的命令
            Process child = rt
                    .exec(mysqldumpPath +"  -h localhost -uroot -pxcxxjs@606 "+dataName);
            // 设置导出编码为utf-8。这里必须是utf-8
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
 
            InputStreamReader xx = new InputStreamReader(in, "utf-8");
            // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
 
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // 组合控制台输出信息字符串
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                //System.out.println(inStr);
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();
 
            // 要用来做导入用的sql目标文件：
            FileOutputStream fout = new FileOutputStream(PathKit.getWebRootPath()+sqlPath+dataName+".sql");
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
            writer.write(outStr);
            writer.flush();
            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();
 
            System.out.println("5555555");
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		return sqlPath;
 
    }
	/**
	 * 
	 * @category 还原
	 * @author MC
	 * @date 2018年8月15日 下午5:18:31
	 */
	public static void restore(String databaseName,String sqlPath) {
		//String mysqlPath = "D:\\java\\cool2.0\\xc-jzero-all\\xc-jzero-admin\\src\\main\\webapp\\static\\sqlexe\\windos\\mysql.exe";
		String mysqlPath = PathKit.getWebRootPath()+"\\static\\sqlexe\\"+systemwindos+"\\mysql.exe";
	       
		try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime
                    .exec(mysqlPath +"  -hlocalhost -uroot -pxcxxjs@606 --default-character-set=utf8 "
                            + databaseName);
        
           OutputStream outputStream = process.getOutputStream();
            
           BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(PathKit.getWebRootPath()+sqlPath), "utf-8"));
            String str = null;
            StringBuffer sb = new StringBuffer();
            while ((str = br.readLine()) != null) {
                sb.append(str + "\r\n");
            }
            str = sb.toString();
       
            OutputStreamWriter writer = new OutputStreamWriter(outputStream,
                    "utf-8");
            writer.write(str);
            writer.flush();
            outputStream.close();
            br.close();
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] args) {
		 //backup();
		 restore("ceshi","C:\\Users\\Administrator\\Desktop\\ceshi\\test.sql");
	}
}
