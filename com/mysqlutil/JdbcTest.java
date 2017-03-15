package com.mysqlutil;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * JDBC连接测试
 * @author SevenNight
 *
 */
public class JdbcTest {

	public static void main(String[] args) {
		
		JdbcSimpleUtil jdbcUtil = null;
		try {
			//创建工具对象
			jdbcUtil = new JdbcSimpleUtil();
			//获取数据库连接
			jdbcUtil.getConnection();
			
			//执行插入操作
			List<Object> insertParams = new LinkedList<>();
			insertParams.add("王五");
			insertParams.add(20);
			insertParams.add(1);
			String insertSql = "INSERT INTO t_user (name,age,sex) VALUES(?,?,?)";
			boolean insertResult = jdbcUtil.updateByPreparedStatement(insertSql, insertParams);
			System.out.println("插入结果："+insertResult);
			
			//修改操作
			List<Object> updateParams = new LinkedList<>();
			updateParams.add("sevennight");
			updateParams.add("张三");
			String updateSql = "UPDATE t_user SET name=? WHERE name=?";
			boolean updateResult = jdbcUtil.updateByPreparedStatement(updateSql, updateParams);
			System.out.println("修改结果："+updateResult);
			
			//查询表数据
			List<Map<String, Object>> findList = jdbcUtil.findResult("SELECT * FROM t_user", null);
			System.out.println(findList);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jdbcUtil != null){
				//释放资源
				jdbcUtil.releaseConn();
			}
		}
	}
}
