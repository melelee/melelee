CREATE USER 'melelee'@'%' IDENTIFIED BY '*';
FLUSH PRIVILEGES;

create database melelee DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
FLUSH PRIVILEGES;

grant all privileges on melelee.* to 'melelee'@'%' identified by '*';
FLUSH PRIVILEGES;
