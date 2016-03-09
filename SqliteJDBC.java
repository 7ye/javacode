package cc.sevennight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Description Sqlite轻型数据库操作类
 * @author SEVENNIGHT
 * @date 2015-12-11下午1:55:24
 * 		@Revised 无
 *		@Revised content 无
 */
public class SqliteJDBC {

	//sqlite连接JDBC路径
    private static String SQLITE_CONNECTION_PATH="jdbc:sqlite:C://lazytool.db";
    
	//获取数据库连接
	public static final Connection getReadableDatabase(){
	    
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(SQLITE_CONNECTION_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	//创建表
	public static final boolean createTable(String sql){
		boolean rs = false;
		if(sql!=null){
			Connection c = null;
			Statement stmt = null;
			try {
				c = getReadableDatabase();//获取数据库连接
				stmt = c.createStatement();// 实例化Statement对象
				stmt.executeUpdate(sql);// 执行数据库更新操作
				stmt.close();//关闭操作连接
				c.close();//关闭数据库连接
				rs = true;
			} catch ( Exception e ) {
				System.err.println(e.getClass().getName()+":"+e.getMessage());
				//System.exit(0);
				rs = false;
			}
		}
		return rs;
	}
	
	//插入数据
	public static final boolean insertData(String sql){
		boolean rs = false;
		if(sql!=null){
			Connection c = null;
			Statement stmt = null;
			try {
				c = getReadableDatabase();//获取数据库连接
				c.setAutoCommit(false);
				stmt = c.createStatement();// 实例化Statement对象
				stmt.executeUpdate(sql);// 执行数据库更新操作
				stmt.close();//关闭操作连接
				c.commit();//提交操作
				c.close();//关闭数据库连接
			} catch (Exception e) {
				System.err.println(e.getClass().getName()+":"+e.getMessage());
				System.exit(0);
			}
			rs = true;
		}
		return rs; 
	} 
	
	//查询数据
	public static final List<Map<Object, Object>> selectData(String sql){
		ResultSetMetaData md = null;
		//List<Object> list = new ArrayList<Object>();
		List<Map<Object, Object>> list = new ArrayList<Map<Object,Object>>();
		if(sql!=null){
			Connection c = null;
			Statement stmt = null;
			try {
				c = getReadableDatabase();//获取数据库连接
				c.setAutoCommit(false);
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				md = rs.getMetaData();
				int columnCount = md.getColumnCount();//取到查询数据总数
				while (rs.next()){//遍历添加到list
					Map<Object, Object> rowData = new HashMap<Object, Object>();
					for (int i = 1; i <= columnCount; i++) {
						rowData.put(md.getColumnName(i), rs.getObject(i));
					}
					list.add(rowData);
				}
				rs.close();
				stmt.close();
				c.close();
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}
		return list;
	}
	
	//修改数据
	public static final boolean updateData(String sql){
		Connection c = null;
        Statement stmt = null;
        boolean rs = false;
        try {
            c = getReadableDatabase(); //获取数据库连接
            c.setAutoCommit(false);
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
            rs = true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return rs;
	}
	
	//删除数据
	public static final boolean deleteData(String sql){
		Connection c = null;
        Statement stmt = null;
        boolean rs = false;
        try {
        	c = getReadableDatabase(); //获取数据库连接
            c.setAutoCommit(false);
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
            rs = true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return rs;
	}
	
	/**
	 * 判断是否有这张表
	 * @Description Description function
	 * @author LUOYANGYIN
	 * @param tableName
	 * @return boolean
	 */
	public static final boolean tableIsExist(String tableName){
		Connection c = null;
		Statement stmt = null;
		boolean rs = false;
		try {
			c = getReadableDatabase();//获取数据库连接
			stmt = c.createStatement();// 实例化Statement对象
			String sql = "select count(*) as c from "+tableName;
			stmt.executeQuery(sql);
			stmt.close();//关闭操作连接
			c.close();//关闭数据库连接
			rs = true;
		} catch (Exception e) {
			if(e.getMessage().equals("[SQLITE_ERROR] SQL error or missing database (no such table: "+tableName+")")){
				System.out.println("没有找到表："+tableName);
			}
		}
		return rs;
	}
	
	/*测试*/
	public static void main(String[] args) throws Exception {
		
		//创建表
//		String sql = "CREATE TABLE USERINFO (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,AGE INT,ADDRESS	CHAR(50))";
//		System.out.println(createTable(sql));
		
		//插入数据
		
//		for (int i = 0; i < 10000; i++) {
//			String sql = "INSERT INTO USERINFO (NAME,AGE,ADDRESS) VALUES ('第"+i+"个用户', 20, '中国');"; 
//			insertData(sql);
//			System.out.println("循环次数："+i);
//		}
		//System.out.println(insertData(sql));
		
//		//查询数据
//		String sql = "SELECT * FROM USERINFO limit 10 offset 0;";
//		List<Object> list =  selectData(sql);
//		System.out.println(list);
		
		//删除数据
		//System.out.println(deleteData("delete from userinfo WHERE ID=1"));
		
		//修改数据
		//updateData("update userinfo set name='李四' where id=2");
	}
}
