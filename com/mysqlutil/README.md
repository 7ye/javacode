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

```
# 数据库用户名
jdbc.username=root
# 数据库密码
jdbc.password=root
# 驱动类型
jdbc.driver=com.mysql.jdbc.Driver
# 数据库连接地址及编码设置(不设置编码可能会出现乱码)
jdbc.url=jdbc:mysql://localhost:3306/sevennight?useUnicode=true&autoReconnect=true&characterEncoding=UTF-8
```


## 三、开始使用，参考`com.mysqlutil.jdbcTest`类