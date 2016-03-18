package com.commons.test;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import com.apache.commons.beanUtils.Stu;
import com.apache.commons.beanUtils.StuForm;

public class ApacheCommonsTest {

	/**
	 * 从一个entity中把属性复制进另外一个entity中
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCopyNewBean() throws Exception {
		StuForm form = new StuForm("lee", 18, 1, new Date(), true);
		Stu stu = new Stu(); 
		BeanUtils.copyProperties(form, stu);
		System.out.println(stu.toString());
		
	}
	
	/**
	 * base64 加密解密
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBase64Code() throws Exception {	
		String name1 = "hello, my name is lee~";
		System.out.println("Before: " + name1);
		
		String name2 = Base64.encodeBase64String(name1.getBytes());
		System.out.println("After encode: " + name2);
		
		String name3 = new String(Base64.decodeBase64(name2));
		System.out.println("After decode: " + name3);
		
		
		String url1 = "www.lee.com.cn";
		System.out.println("URL Before: " + url1);
		
		String url2 = Base64.encodeBase64URLSafeString(url1.getBytes());
		System.out.println("URL After decode: " + url2);
		
		String url3 = new String(Base64.decodeBase64(url2));
		System.out.println("URL After decode: " + url3);
	}
	
	/**
	 * commons 下 collection 工具包
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCollection() throws Exception {
		OrderedMap<String, Object> om = new LinkedMap<String, Object>();
		om.put("one", 1);
		om.put("two", "2");
		om.put("three", "three");
		om.put("fore", 4);
		om.put("five", "5");
		System.out.println(om.firstKey());
		System.out.println(om.nextKey("fore"));
		System.out.println(om.previousKey("five"));
		
		System.out.println("==============================");
		
		BidiMap bm = new TreeBidiMap();
		bm.put("three", "3");
		bm.put("five", "isfive");
		System.out.println(bm.getKey("isfive").toString());
		System.out.println(bm.get("three"));
		
		// 交换key和value
		BidiMap newMap = bm.inverseBidiMap();
		System.out.println(newMap.size());
		
		System.out.println("==============================");
		
		Bag<Object> bag = new HashBag<Object>();
		bag.add("abc");
		bag.add("def", 3);
		bag.add("ghi", 5);
		
		System.out.println(bag.size());
		
		// 过滤重复元素
		Set<Object> onlyU = bag.uniqueSet();
		Iterator<Object> i = onlyU.iterator();
		while(i.hasNext()){
			Object o = i.next();
			System.out.println(o.toString());
		}
	}
	
	/**
	 * Apache Commons Configuration
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConfig() throws Exception {
		PropertiesConfiguration p = new PropertiesConfiguration("test.properties");
		System.out.println(p.getString("boy.name"));
		System.out.println(p.getInt("boy.age"));
		System.out.println(p.getString("boy.birth"));
		
		p.setHeader("##this is a new string##");
		p.setProperty("new.string", "newString");
		// 保存在编译后的目录中
		p.save();
		p.save("newP");
		
	}
	
	/**
	 * Apache Commons Lang
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLang() throws Exception {
		String a1[] = {"1", "2", "3"};
		String a2[] = {"a", "b", "c"};
		// 合并数组
		String a3[] = (String[])ArrayUtils.addAll(a1, a2);
		for (String s : a3) {
			System.out.println(s);
		}
		
		System.out.println("==============================");
		
		String str = "hello, my name is hanmeimei! what's your name? name";
		// 出现第一个和第二个name之间的string
		String s1 = StringUtils.substringBetween(str, "name");
		System.out.println("s1: " + s1);
		// 截取第一次出现的字符串之间的string
		String s2 = StringUtils.substringBetween(str, "name", "your");
		System.out.println("s2: " + s2);
		
//		StringUtils.substringAfter(str, separator)
//		StringUtils.substringBefore(str, separator)
		
		System.out.println("==============================");
		
		// 判断该字符串是不是为数字(0~9)组成，如果是，返回true 但该方法不识别有小数点
	    System.out.println(StringUtils.isNumeric("454534"));
	    
	    System.out.println("==============================");
	    
	    System.out.println(ClassUtils.getShortClassName(Test.class));
	    System.out.println(ClassUtils.getPackageName(Test.class));
	    
	    System.out.println("==============================");
	    
	    // 判断该字符串是不是为数字(0~9)组成，如果是，返回true 可以识别有小数点
	    System.out.println(NumberUtils.isNumber("12334.11"));
	    // 不建议使用，可以使用 Integer.valueOf("[number]")
	    System.out.println(NumberUtils.stringToInt("33"));
	    System.out.println(Integer.valueOf("33"));
	    
	    // 五位的随机字母和数字
	    System.out.println(RandomStringUtils.randomAlphanumeric(5));
	    System.out.println(StringEscapeUtils.escapeHtml("<html>"));
	    System.out.println(StringEscapeUtils.escapeJava("String"));
	    
	    // StringUtils,判断是否是空格字符
	    System.out.println(StringUtils.isBlank("   "));
//	    StringUtils.isEmpty("");
	    // 将数组中的内容以,分隔
	    System.out.println(StringUtils.join(a3, ","));
	    // 在右边加下字符,使之总长度为6
	    System.out.println(StringUtils.rightPad("abc", 6, 'T'));
	    // 首字母大写
	    System.out.println(StringUtils.capitalize("abc"));
	    // Deletes all whitespaces from a String 删除所有空格
	    System.out.println(StringUtils.deleteWhitespace("   ab  c  "));
	    // 判断是否包含这个字符
	    System.out.println(StringUtils.contains("abc", "ba"));
	    // 表示左边两个字符
	    System.out.println(StringUtils.left("abc", 2));
	}
	
}
