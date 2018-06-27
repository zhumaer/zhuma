CREATE SCHEMA IF NOT EXISTS `zhuma` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

-- 用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `nickname` varchar(64) NOT NULL COMMENT '昵称',
  `gender` varchar(16) NOT NULL COMMENT '性别',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `type` varchar(32) NOT NULL COMMENT '类型',
  `status` varchar(32) NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

-- 登录凭证
DROP TABLE IF EXISTS `login_credential`;
CREATE TABLE `login_credential` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(128) NOT NULL COMMENT '账号',
  `pwd` varchar(128) NOT NULL COMMENT '登录密码',
  `random_salt` varchar(64) NOT NULL COMMENT '随机盐',
  `user_id` varchar(64) NOT NULL COMMENT '用户ID',
  `type` varchar(64) NOT NULL COMMENT '类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account_type` (`account`,`type`),
  UNIQUE KEY `uk_user_id_type` (`user_id`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='登录凭证';

-- 组织架构表
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '组织架构名称',
  `type` varchar(32) NOT NULL COMMENT '类型',
  `sort` int(11) DEFAULT 0 COMMENT '排序值',
  `parent_id` int(11) NOT NULL COMMENT '父ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX index_parent_id(`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='组织架构';

-- 用户属性表
DROP TABLE IF EXISTS `user_attr`;
CREATE TABLE `user_attr` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `objectId` varchar(64) NOT NULL COMMENT '对象ID',
  `key` varchar(64) NOT NULL COMMENT '键',
  `value` varchar(1024) DEFAULT NULL COMMENT '值',
  `type` varchar(32) NOT NULL COMMENT '类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX uq_object_id_key(`objectId`, `key`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户属性表';