package com.device.util;

import com.xiaoleilu.hutool.json.JSONNull;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.util.JavaScriptUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title StringUtils.java
 * @Package com.utils
 * @Description 字符串操作工具类【new】
 * @author liuwu_eva@163.com
 * @date 2015-9-6 上午10:47:43
 */
public class StringUtil {
	
	public interface Re {
		/** 正则表现 : 数字类型 */
		String NUMBER = "(-)?[0-9]+";
		
		/** 正则表现 : 英数字类型 */
		String ALPHA_NUMBER = "[a-zA-Z0-9]+";
	}
	
	/** 正则表现  : 数字类型 */
	private static final Pattern RE_NUMBER = Pattern.compile(Re.NUMBER);
	
	/** 正则表现 : 英数字类型 */
	private static final Pattern RE_ALPHANUMBER = Pattern.compile(Re.ALPHA_NUMBER);

	/**
	 * 字符串为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
	
	/**
	 * 字符串不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
	
	/**
	 * 如果字符串是空或null就返回"",否则返回自身
	 * 返回自身时进行了特殊js字符转义的处理
	 * @param str
	 * @return
	 */
	public static String returnSelf(String str) {
		if (isEmpty(str)) {
			return "";
		}else {
			return strFilter(str);
		}
	}

	/**
	 * 如果字符串是空或null就返回"",否则返回自身
	 * @param str
	 * @return
	 */
	public static String returnSelf2(String str) {
		if (isEmpty(str)) {
			return "";
		}else {
			return str;
		}
	}
	
	/**
	 * 返回自身,如果是null返回“”
	 * 可以判断JSONNull问题
	 * @param str
	 * @return
	 */
	public static String returnSelf3(Object str) {
		if(str instanceof JSONNull){
			//System.out.println("Is empty null");
			return "";
		}else{
			//System.out.println("is String null");
			return str == null ? "" : str.toString();
		}
	}

	/**
	 * 去除字符串中重复的字符
	 */
	public static String clearRepeat(String str) {
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < str.length(); i++) {
            String s = str.substring(i, i + 1);
            if (!data.contains(s)) {
                data.add(s);
            }
        }
        String result = "";
        for (String s : data) {
            result += s;
        }
        return result;
	}
	
	 /**
     * 返回字符串长度[中文2个字符，英文1个字符]
     * @param str
     * @return 返回字符串长度
     */
    public static int checkLength(String str) {
		int valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		for (int i = 0; i < str.length(); i++) {
			String temp = str.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
			} else {
				valueLength += 1;
			}
		}
		return valueLength;
	}
	
    /**
	 * @param s
	 * 判断输入的是否为数字;
	 * 是数字返回true否则返回false
	 */
	public static boolean checkNumber(String s) {
	    for (int i = 0; i < s.length(); i++) {
	       if (s.charAt(i) != ' ' && (s.charAt(i) < '0' || s.charAt(i) > '9')) {
	         return false;
	       }
	    }
	     return true;
	 }
    
    /**
	 * 是否是数字类型
	 * @param temp
	 * @return
	 */
	public static Boolean isNum(String temp) {
		if (null == temp) {
			return false;
		}
		Boolean returns = true;
		if (temp.length() == 0) {
			returns = false;
		}
		for (int k = 0; k < temp.length(); k++) {
			int nchar = temp.charAt(k);
			if (nchar < 48 || nchar > 57) {
				returns = false;
			}
		}
		return returns;
	}
	
	/**
	 * 判断字符串是否由字母数字下划线组成
	 * @param str
	 * @return 有中文返回false
	 */
	public static boolean isStr(String str) {
		 Boolean bl = false;
		 Pattern pt = Pattern.compile("^[0-9a-zA-Z_]+$");
		 Matcher mt = pt.matcher(str);
		if (mt.matches()) {
			bl = true;
		}
		return bl;
	}
	
	/**
	 * 数字格式验证
	 * @param str 对象文字
	 * @return 对象文字是数字组合构成的场合  : <code>true</code><br/>
	 *         对象文字是数字组合以外构成的场合 : <code>false</code>
	 */
	public static boolean isNumber(String str) {
		return StringUtils.isNotBlank(str) && RE_NUMBER.matcher(str).matches();
	}
	
	/**
	 * 英数字验证
	 * @param str 对象文字
	 * @return 对象文字是英数字组合构成的场合  : <code>true</code><br/>
	 *         对象文字是英数字组合以外构成的场合  : <code>false</code>
	 */
	public static boolean isAlphaNumber(String str) {
		return StringUtils.isNotBlank(str) && RE_ALPHANUMBER.matcher(str).matches();
	}
	
	 /**  
     * 验证手机号码  
     * @param mobiles  
     * @return  [0-9]{5,9}  
     */  
    public static boolean checkPhone(String mobiles){  
    	boolean flag = false;  
	    try{  
	      Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
	      Matcher m = p.matcher(mobiles);
	      flag = m.matches();  
	    }catch(Exception e){  
	      flag = false;  
	    }  
	    return flag;  
    }  
    
    /**
     * 检验电话号
     * @param tel
     * @return
     */
    public static boolean checkTel(String tel){
	   Pattern p= Pattern.compile("^\\d+(\\d*|-{1})\\d+$");
	   Matcher m=p.matcher(tel);
	   if(m.matches()&&tel.length()>6&&tel.length()<20){
		   return true;
	   }
	   return false;
	 }
    
    /**
     * 检验电话号和手机号
     * @param value
     * @return
     */
    public static boolean checkTelAndPhone(String value){
	   if(checkTel(value)||checkPhone(value)){
		   return true;
	   }
	   return false;
	 }
	
	/**
	 * 判断是否为浮点数或者整数
	 * @param str
	 * @return true Or false
	 */
	public static boolean isNumeric(String str){
          Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
          Matcher isNum = pattern.matcher(str);
          if( !isNum.matches() ){
                return false;
          }
          return true;
    }
    	
	/**
	 * 判断是否为正确的网址
	 * 是域名返回true
	 */
	public static boolean isDomain(String str) {
		Pattern pattern = Pattern.compile("http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*");
		 Matcher isNum = pattern.matcher(str);
         if( !isNum.matches() ){
               return false;
         }
         return true;
	}
	
	/**
	 * 判断是否为正确的邮箱格式
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmail(String str){
		if(isEmpty(str))
			return false;
		return str.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
	}
    	
	/**
	 * 判断字符串是否为合法手机号 11位 13 14 15 18开头
	 * @param str
	 * @return boolean
	 */
	public static boolean isMobile(String str){
		if(isEmpty(str))
			return false;
		return str.matches("^(13|14|15|18)\\d{9}$");
	}
	
	//*************************************************** 特殊字符转义Start *******************************************************************
	/**
	 * html特殊字符转义,一个单引号替换为2个,数据库插入的时候会自动的去掉一个
	 * @param str
	 * @return
	 */
	public static String str2Html(String str) {
		if (str == null) {
			return "";
		}
		str = str.replace("\\", "\\\\");
		str = str.replace("'", "''");
		return str;
	}
	/**
	 * 1.json或数组格式中2个双引号把后一个双引号转义一下,否则取值解析时会出错.
	 * 2.一个单引号替换为2个,数据库插入的时候会自动的去掉一个
	 * @param str
	 * @return
	 */
	public static String str2Html2(String str) {
		if (str == null) {
			return "";
		}
		str = str.replace("\\", "\\\\");
		str = str.replace("'", "''");
		if (str.contains("\"\"")) {
			if (str.contains(":\"\"")) { //双引号前包含冒号,则替换后一个双引号
				str = str.replace("\"\"", "\"\\\"");
			} else if (str.contains("\"\":") || str.contains("\"\"}") || str.contains("\"\"]")) { //双引号后包含}或：,则替换前一个双引号
				str = str.replace("\"\"", "\\\"\"");
			}else { //其余的全部替换(即双引号在文本中间的)
				str = str.replace("\"\"", "\\\"\\\"");
			}
		}
		return str;
	}
	
	/**
	 * js特殊字符过滤
	 * @param sqlStr
	 * @return
	 */
	public static String strFilter(String str) {
		return JavaScriptUtils.javaScriptEscape(str);
	}
	
	/** 
	 * js转义正则特殊字符 （$()*+.[]?\^{},|） 
	 * @param keyword 
	 * @return 
	 */  
	public static String escapeExprSpecialWord(String keyword) {  
	    if (StringUtils.isNotBlank(keyword)) {  
	        String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "'", "^", "{", "}", "|" };  
	        for (String key : fbsArr) {  
	            if (keyword.contains(key)) {  
	                keyword = keyword.replace(key, "\\" + key);  
	            }  
	        }  
	    }  
	    return keyword;  
	} 
	
	/**
	 * 将特殊字符移除
	 * @return 
	 */
	public static String strValidate(String str) {
		try {
			String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(str);
			return m.replaceAll("").trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//************************************************* 特殊字符转义End *********************************************************************
	
	 /**
	 * 剪切文本。如果进行了剪切，则在文本后加上"..."
	 * @param s 剪切对象。
	 * @param len 编码小于256的作为一个字符，大于256的作为两个字符。
	 * @param append 在文本后加上指定的结束标识如...
	 * @return
	 */
	public static String textCut(String s, int len, String append) {
		if (s == null) {
			return null;
		}
		int slen = s.length();
		if (slen <= len) {
			return s;
		}
		// 最大计数（如果全是英文）
		int maxCount = len * 2;
		int count = 0;
		int i = 0;
		for (; count < maxCount && i < slen; i++) {
			if (s.codePointAt(i) < 256) {
				count++;
			} else {
				count += 2;
			}
		}
		if (i < slen) {
			if (count > maxCount) {
				i--;
			}
			if (!StringUtils.isBlank(append)) {
				if (s.codePointAt(i - 1) < 256) {
					i -= 2;
				} else {
					i--;
				}
				return s.substring(0, i) + append;
			} else {
				return s.substring(0, i);
			}
		} else {
			return s;
		}
	}

	//生成随机的字符串
    private static Random randGen = null;
	private static char[] numbersAndLetters = null;
	public static final String randomString(int length) {
         if (length < 1) {
             return null;
         }
         if (randGen == null) {
                randGen = new Random();
                numbersAndLetters = ("abcdefghijklmnopqrstuvwxyz").toCharArray();
                  //numbersAndLetters = ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
                 }
         char [] randBuffer = new char[length];
         for (int i=0; i<randBuffer.length; i++) {
           //randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        	 randBuffer[i] = numbersAndLetters[randGen.nextInt(26)];
         }
         return new String(randBuffer);
	}
	
	/**
	 * 将大小写字母都转换成数字_1
	 * @param input
	 * @return
	 */
	public static String letterConvert(String input) {
		String reg = "[a-zA-Z]";
		StringBuffer strBuf = new StringBuffer();
		input = input.toLowerCase();
		if (null != input && !"".equals(input)) {
			for (char c : input.toCharArray()) {
				if (String.valueOf(c).matches(reg)) {
					strBuf.append(c - 96);
				} else {
					strBuf.append(c);
				}
			}
			return strBuf.toString();
		} else {
			return input;
		}
	}

	/**
	 * 将小写字母转换成数字
	 * @param input
	 */
	public static void letterToNum(String input) {
		for (byte b : input.getBytes()) {
			System.out.print(b - 96);
		}
	}

	/**
	 *  将数字转换成小写字母
	 * @param input
	 */
	public static void numToLetter(String input) {
		for (byte b : input.getBytes()) {
			System.out.print((char) (b + 48));
		}
	}
	
	/**
	 * 字符串转换unicode
	 */
	public static String string2Unicode(String string) {
	    StringBuffer unicode = new StringBuffer();
	    for (int i = 0; i < string.length(); i++) {
	        // 取出每一个字符
	        char c = string.charAt(i);
	        // 转换为unicode
	        unicode.append("\\u" + Integer.toHexString(c));
	    }
	    return unicode.toString();
	}
	
	/**
	 * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {
	    StringBuffer string = new StringBuffer();
	    String[] hex = unicode.split("\\\\u");
	    for (int i = 1; i < hex.length; i++) {
	        // 转换出每一个代码点
	        int data = Integer.parseInt(hex[i], 16);
	        // 追加成string
	        string.append((char) data);
	    }
	    return string.toString();
	}
	
	/**
	 * unicode 转字符串(推荐)
	 */
	public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            value = (value << 4) + aChar - '0';
                            break;
                        case 'a':
                        case 'b':
                        case 'c':
                        case 'd':
                        case 'e':
                        case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                        case 'A':
                        case 'B':
                        case 'C':
                        case 'D':
                        case 'E':
                        case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                        default:
                            throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

	/**
	 * 获取2个字符串中公共的部分
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String getMaxString(String str1, String str2) {
		if (str1.contains("-")) {
			str1 = str1.substring(0, str1.indexOf("-"));
		} else if (str1.contains("(")) {
			str1 = str1.substring(0, str1.indexOf("("));
		}
		String max = null;
		String min = null;
		max = (str1.length() > str2.length() ? str1 : str2);
		min = max.equals(str1) ? str2 : str1;
		for (int i = 0; i < min.length(); i++) {
			for (int start = 0, end = min.length() - i; end != min.length() + 1; start++, end++) {
				String sub = min.substring(start, end);
				if (max.contains(sub)){
					return sub;
				}
			}
		}
		return null;
	}

	/**
	 * 替换一串字符串中的占位符{0},{1},{2}
	 * 为动态字符
	 * 
	 * 注意：从{0}开始
	 * 
	 * @param fill  原字符串  如：http://check.ptlogin2.qq.com/check?regmaster=&uin=448163451&appid={1}&js_ver={2}&js_type=1
	 * @param str   动态填充站位字符
	 * @return 填充完成的新字符串
	 */
	public static String fillString(String fill,String ...str){
		if(null == fill ||null == str)
			return "";
		
		int $i = 0;
		for(String v : str ){
			fill = fill.replace("{"+$i+++"}", v);
		}
		return fill;
	}

	/**
     * 判断一组以逗号分割的字符串中是否存在某个元素
     * @param strS
     * @param ele
     * @return
     */
    public static boolean isContainsEleByStr(String strS,String ele) {
        String[] values = strS.split(",");
        List<String> list = Arrays.asList(values);
        if (list.contains(ele)) {
            return true;
        } else {
            return false;
        }
    }

	 /**
     * 移除一组以逗号分割的字符串中某个元素
     *
     * @param str          以逗号分割的字符串
     * @param removeEle    要移除的字符串
     * @return
     */
    public static String removeEleByStr(String str, String removeEle) {
        String result = "";
        if (str == null || str.equals("")) {
            return "";
        }
        List list = new ArrayList<>();
        String[] array = str.split(",");
        for (String a : array) {
            if (!removeEle.equals(a)){
                list.add(a);
            }
        }
        result = StringUtils.join(list.toArray(),",");
        return result;
    }

	/**
     * 数字转中文
     *
     * @param number
     * @return
     */
    public static String numToHanStr(Integer number) {
        String result = "";
        String[] hanArr = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] unitArr = {"十", "百", "千", "万", "十", "白", "千", "亿", "十", "百", "千"};
        String numStr = number + "";
        int numLen = numStr.length();
        for (int i = 0; i < numLen; i++) {
            int num = numStr.charAt(i) - 48;
            if (i != numLen - 1 && num != 0) {
                result += hanArr[num] + unitArr[numLen - 2 - i];
                if (number >= 10 && number < 20) {
                    result = result.substring(1);
                }
            } else {
                if (!(number >= 10 && number % 10 == 0)) {
                    result += hanArr[num];
                }
            }
        }
        return result;
    }

	/**
     * List集合移除指定元素[equals]
     *
     * @param list
     * @param obj
     */
    public void iteratorRemove(List<T> list, T obj) {
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            T item = it.next();
            if (item.equals(obj)) {
                it.remove();
            }
        }
    }

    /**
     * Map集合移除包含元素[contains]
     *
     * @param map
     * @param ele
     */
    public static void iteratorRemove(Map map, String ele) {
        Iterator<Map.Entry> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Boolean> entry = it.next();
            if (entry.getKey().contains(ele)) {
                map.remove(entry.getKey());
            }
        }
    }

	public static String stackTrace(Throwable t) {
        if (t == null) {
            return null;
        }
        StringWriter errors = new StringWriter();
        t.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }

	public static Map<String, String> map = new HashMap<String, String>() {
		{
			put("A", "1");  
			put("B", "2");  
			put("C", "3");  
		}
	};
	
}
