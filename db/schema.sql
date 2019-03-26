CREATE USER 'melelee'@'%' IDENTIFIED BY '*';

create database melelee DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

grant all privileges on melelee.* to 'melelee'@'%' identified by '*';
FLUSH PRIVILEGES;


CREATE TABLE user(
  id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  name VARCHAR(100) COMMENT '姓名',
  age INT COMMENT '年龄',
  sex SMALLINT NOT NULL COMMENT '性别：0-男，1-女,3-未知，4-其他',
  email VARCHAR(100) COMMENT '邮箱',
  tel VARCHAR(20) COMMENT '手机',
  adress VARCHAR(100) COMMENT '地址'
)
