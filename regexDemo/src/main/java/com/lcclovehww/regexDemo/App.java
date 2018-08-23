package com.lcclovehww.regexDemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
/*    	String str = "i love you 2 3 48989 _ - = ( )\ni love hww ab a3b a4b a66b a8989b\ni love sex ^^^^^ gaoqi gaoqi2 gaoqi222 2222gaoqi\ni gogo goto toto dodo todo\ni going doing eating\n                 i\n				";

    	// 分割
    	String [] strs = str.split("a\\d+b");
    	for(String str2:strs) {
    		System.out.println(str2);
    	}*/
    	
    	// 表达式对象
    	Pattern p = Pattern.compile("a\\d+b", Pattern.MULTILINE);
    	

    	// 创建 Matcher 对象
    	Matcher m = p.matcher("i love you 2 3 48989 _ - = ( )\ni love hww ab a3b a4b a66b a8989b\ni love sex ^^^^^ gaoqi gaoqi2 gaoqi222 2222gaoqi\ni gogo goto toto dodo todo\ni going doing eating\n                 i\n				");
    	
    	// 是否找到匹配
    	/*boolean found = m.find();*/

    	while (m.find()) {
    		String foundstring = m.group();
    	    int    beginPos    = m.start();
    	    int    endPos      = m.end();

    	    System.out.println(foundstring);
    	    System.out.println(beginPos);
    	    System.out.println(endPos);
			
		}
    	
    	
        String inputLine = "Adams,John Quincy";
        Pattern r = Pattern.compile("(.*),(.*)");
        Matcher m1 = r.matcher(inputLine);
        if (!m1.matches()) {
            throw new IllegalArgumentException("Bad Input");
        }
        // 分组0得到的是整个原字符串
        for (int i = 0; i < m1.groupCount() + 1; i++) {
            System.out.println("分组" + i + ":" + m1.group(i));
        }
    	
    }
}
