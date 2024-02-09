For stating u can connect every ur empty database and don't forget to change settings on file 'application.proporties'
I am using app XAMPP.

spring.datasource.url=jdbc:mysql://localhost:3306/[your database name]?useUnicode=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8088
