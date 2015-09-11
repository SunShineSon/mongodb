package com.qiniu;

import java.io.File;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

public class Demo {
	private static String ACCESS_KEY = "Gl2OPxh40L56BD-spQrX4fvxcVaGmqcu53kOOgbm";
	private static String SECRET_KEY = "VIXmdeTHOnBvEoVJfCrMmAj7t4W_kR70S1jZPRFB";
	// 重用 uploadManager。一般地，只需要创建一个 uploadManager 对象
	UploadManager uploadManager = new UploadManager();
	
	public static void main(String[] args) {
		Demo demo = new Demo();
		demo.test();
	}
	
	public void test(){
		Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		String token = auth.uploadToken("sunshine");
		File file = new File("E:/tmp/n.png");
		try {
			Response res = uploadManager.put(file, "sunshinen", token);
			System.out.println(res.bodyString());
			System.out.println(res.statusCode);
			System.out.println(res.toString());
		} catch (QiniuException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 上传数据
	 *
	 * @param data     上传的数据 byte[]、File、filePath
	 * @param key      上传数据保存的文件名
	 * @param token    上传凭证
	 * @param params   自定义参数，如 params.put("x:foo", "foo")
	 * @param mime     指定文件mimetype
	 * @param checkCrc 是否验证crc32
	 * @return
	 * @throws QiniuException
	 */
	public Response put(File data, String key, String token, StringMap params,
			String mime, boolean checkCrc) throws QiniuException {
		return null;
	}
	
	/**
	* 生成上传token
	*
	* @param bucket  空间名
	* @param key     key，可为 null
	* @param expires 有效时长，单位秒。默认3600s
	* @param policy  上传策略的其它参数，如 new StringMap().put("endUser", "uid").putNotEmpty("returnBody", "")。
	*        scope通过 bucket、key间接设置，deadline 通过 expires 间接设置
	* @param strict  是否去除非限定的策略字段，默认true
	* @return 生成的上传token
	*/
	public String uploadToken(String bucket, String key, long expires, StringMap policy, boolean strict){
		return "String";
	}

}
