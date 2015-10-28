package demo.email;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckEmail {

	public static void main(String[] args) {
		String mail = "";
		String passWord = "";
		boolean isMail = isMail(mail);
		System.out.println(isMail);
		if (mail.length()==0 || passWord.length()==0 || isMail) {
			System.out.println("no");
		}

	}

	/**
	 * 检测是否为邮箱格式
	 */
	public static boolean isMail(String mail) {
		boolean flag = true;
		Matcher mat = null;
		String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(pattern1);
		try{
			mat = pattern.matcher(mail);
			if (!mat.find()) {
				flag = false;
			}
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
}
