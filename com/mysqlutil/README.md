### 简单JDBC连接MySql数据库使用方法

## 一、使用maven导入mysql的驱动包及依赖

``` xml
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>5.1.25</version>
</dependency>
```

## 二、修改jdbc.properties文件
修改jdbc.properties文件里面的数据库连接相关链接


## 三、开始使用，参考com.mysqlutil.dbcTest类