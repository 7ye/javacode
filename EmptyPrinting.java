package com.test.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 打印存在Object里的实体类的字段和值
 * @Explain
 *  为了方便开发中便于调试，写了这个接口，存入实体类，反射遍历出这个实体类里面的所有字段和值，
 *  这里只写出了两个常用的普通字段和值的输出，有其他集合或者更负责的解析可以自己修改代码
 * @author sevennight
 */
public class EmptyPrinting {

	/** 
	 * 输出实体类
	 * @param model
	 */
	public static void print(Object model) {

	Class cls = model.getClass();
        Field[] fields = cls.getDeclaredFields();
        
        System.out.println("实体类："+model.getClass().getName());
        
        for (Field field : fields) {
           
            char[] buffer = field.getName().toCharArray();
            buffer[0] = Character.toUpperCase(buffer[0]);
            String mothodName = "get" + new String(buffer);
            
            try {
            	
		Method method = cls.getDeclaredMethod(mothodName);
        	Object resutl = method.invoke(model, null);
        		
            	System.out.println(field.getName() + " : " + resutl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
	
	/**
     * 输出实体类指定的属性
     * @param model
     * @param fields
     */
	public static void print(Object model, String[] fields) {
      
	Class cls = model.getClass();
		
        System.out.println("实体类："+model.getClass().getName());
        
        for (String field : fields) {
        	
            char[] buffer = field.toCharArray();
            buffer[0] = Character.toUpperCase(buffer[0]);
            String mothodName = "get" + new String(buffer);
            
            try {
                Method method = cls.getDeclaredMethod(mothodName);
                Object resutl = method.invoke(model, null);
                System.out.println(field + ": " + resutl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
	
	//测试
	public static void main(String[] args) throws Exception {
		
		//这是一个实体类
		ClassBean1009 bean1009 = new ClassBean1009();
		bean1009.setNzxHelpMeType(1);
		bean1009.setSzxAuthString("234242342343243");
		//现在是一个未知类型的对象(模拟一下)
		Object obj = bean1009;
		//调用
		print(obj);
	}
}
