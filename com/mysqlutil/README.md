### ��JDBC����MySql���ݿ�ʹ�÷���

## һ��ʹ��maven����mysql��������������

``` xml
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>5.1.25</version>
</dependency>
```

## �����޸�jdbc.properties�ļ�
�޸�jdbc.properties�ļ���������ݿ������������

```
# ���ݿ��û���
jdbc.username=root
# ���ݿ�����
jdbc.password=root
# ��������
jdbc.driver=com.mysql.jdbc.Driver
# ���ݿ����ӵ�ַ����������(�����ñ�����ܻ��������)
jdbc.url=jdbc:mysql://localhost:3306/sevennight?useUnicode=true&autoReconnect=true&characterEncoding=UTF-8
```


## ������ʼʹ�ã��ο�`com.mysqlutil.jdbcTest`��