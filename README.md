# html_gen
java -jar html_gen-0.0.1-Release.jar application.properties

# application.properties配置文件
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/gen?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC
username=root
password=123456
tableName=user
