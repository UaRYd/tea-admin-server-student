-- 基于RBAC（Role-Based Access Control：基于角色的访问控制）的权限设计

DROP TABLE IF EXISTS account_user;
CREATE TABLE account_user
(
    id             BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据ID',
    username       VARCHAR(50)         DEFAULT NULL COMMENT '用户名',
    password       CHAR(64)            DEFAULT NULL COMMENT '密码（密文）',
    nickname       VARCHAR(50)         DEFAULT NULL COMMENT '昵称',
    avatar         VARCHAR(255)        DEFAULT NULL COMMENT '头像URL',
    phone          VARCHAR(50)         DEFAULT NULL COMMENT '手机号码',
    email          VARCHAR(50)         DEFAULT NULL COMMENT '电子邮箱',
    description    VARCHAR(255)        DEFAULT NULL COMMENT '简介',
    enable         TINYINT(3) UNSIGNED DEFAULT '0' COMMENT '是否启用，1=启用，0=未启用',
    last_login_ip  VARCHAR(50)         DEFAULT NULL COMMENT '最后登录IP地址（冗余）',
    login_count    INT(10) UNSIGNED    DEFAULT '0' COMMENT '累计登录次数（冗余）',
    gmt_last_login DATETIME            DEFAULT NULL COMMENT '最后登录时间（冗余）',
    gmt_create     DATETIME            DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified   DATETIME            DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4 COMMENT ='用户';

DROP TABLE IF EXISTS account_role;
CREATE TABLE account_role
(
    id           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据id',
    name         VARCHAR(50)         DEFAULT NULL COMMENT '名称',
    description  VARCHAR(255)        DEFAULT NULL COMMENT '简介',
    sort         TINYINT(3) UNSIGNED DEFAULT '0' COMMENT '排序序号',
    gmt_create   DATETIME            DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified DATETIME            DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4 COMMENT ='角色';

DROP TABLE IF EXISTS account_user_role;
CREATE TABLE account_user_role
(
    id           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据ID',
    user_id      BIGINT(20) UNSIGNED DEFAULT NULL COMMENT '用户ID',
    role_id      BIGINT(20) UNSIGNED DEFAULT NULL COMMENT '角色ID',
    gmt_create   DATETIME            DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified DATETIME            DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4 COMMENT ='用户角色关联';

DROP TABLE IF EXISTS account_permission;
CREATE TABLE account_permission
(
    id           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据ID',
    name         VARCHAR(50)         DEFAULT NULL COMMENT '名称',
    value        VARCHAR(255)        DEFAULT NULL COMMENT '值',
    description  VARCHAR(255)        DEFAULT NULL COMMENT '简介',
    sort         TINYINT(3) UNSIGNED DEFAULT '0' COMMENT '排序序号',
    gmt_create   DATETIME            DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified DATETIME            DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4 COMMENT ='权限';

DROP TABLE IF EXISTS account_role_permission;
CREATE TABLE account_role_permission
(
    id            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据id',
    role_id       BIGINT(20) UNSIGNED DEFAULT NULL COMMENT '角色ID',
    permission_id BIGINT(20) UNSIGNED DEFAULT NULL COMMENT '权限ID',
    gmt_create    DATETIME            DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified  DATETIME            DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4 COMMENT ='角色权限关联';

DROP TABLE IF EXISTS account_login_log;
CREATE TABLE account_login_log
(
    id           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据ID',
    user_id      BIGINT(20) UNSIGNED DEFAULT NULL COMMENT '用户ID',
    username     VARCHAR(50)         DEFAULT NULL COMMENT '用户用户名（冗余）',
    nickname     VARCHAR(50)         DEFAULT NULL COMMENT '用户昵称（冗余）',
    ip           VARCHAR(50)         DEFAULT NULL COMMENT '登录IP地址',
    user_agent   VARCHAR(255)        DEFAULT NULL COMMENT '浏览器内核',
    gmt_login    DATETIME            DEFAULT NULL COMMENT '登录时间',
    gmt_create   DATETIME            DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified DATETIME            DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) DEFAULT CHARSET = utf8mb4 COMMENT ='用户登录日志';