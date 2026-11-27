--SAP-CA3 Forum: database setup
--Run this once as the MySQL root user (e.g. in MySQL Workbench).

CREATE DATABASE IF NOT EXISTS forum;

CREATE USER IF NOT EXISTS 'forum_user'@'localhost' IDENTIFIED BY 'change-me';
GRANT ALL PRIVILEGES ON forum.* TO 'forum_user'@'localhost';
FLUSH PRIVILEGES;