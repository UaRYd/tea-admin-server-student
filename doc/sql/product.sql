DROP TABLE IF EXISTS product_category;
CREATE TABLE product_category
(
    id           BIGINT(20) UNSIGNED        NOT NULL AUTO_INCREMENT COMMENT '数据ID',
    name         VARCHAR(32)      DEFAULT '' COMMENT '类别名称',
    parent_id    BIGINT UNSIGNED  DEFAULT 0 COMMENT '父级类别ID，如果无父级，则为0',
    depth        TINYINT UNSIGNED DEFAULT 0 COMMENT '深度，最顶级类别的深度为1，次级为2，以此类推',
    keywords     VARCHAR(256)     DEFAULT '' COMMENT '关键词列表，各关键词使用英文的逗号分隔',
    sort         TINYINT UNSIGNED DEFAULT 0 NOT NULL COMMENT '排序序号',
    icon         VARCHAR(256)     DEFAULT '' COMMENT '图标图片的URL',
    enable       TINYINT UNSIGNED DEFAULT 0 COMMENT '是否启用，1=启用，0=未启用',
    is_parent    TINYINT UNSIGNED DEFAULT 0 COMMENT '是否为父级（是否包含子级），1=是父级，0=不是父级',
    is_display   TINYINT UNSIGNED DEFAULT 0 COMMENT '是否显示在导航栏中，1=启用，0=未启用',
    gmt_create   DATETIME         DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified DATETIME         DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) COMMENT '商品类别' CHARSET = utf8mb4;

DROP TABLE IF EXISTS product_goods;
CREATE TABLE product_goods
(
    id           BIGINT UNSIGNED AUTO_INCREMENT COMMENT '数据ID',
    category_id  INT UNSIGNED     DEFAULT 0 COMMENT '类别ID',
    bar_code     VARCHAR(32)      DEFAULT '' COMMENT '商品条形码',
    title        VARCHAR(256)     DEFAULT '' COMMENT '标题',
    brief        VARCHAR(512)     DEFAULT '' COMMENT '摘要',
    sale_price   BIGINT(11)       DEFAULT 0 COMMENT '价格',
    keywords     VARCHAR(64)      DEFAULT '' COMMENT '关键词列表',
    sort         INT(10)          DEFAULT 0 COMMENT '排序序号',
    status       TINYINT UNSIGNED DEFAULT 0 COMMENT '状态',
    gmt_create   DATETIME         DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified DATETIME         DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) COMMENT '商品' CHARSET = utf8mb4;

DROP TABLE IF EXISTS product_goods_detail;
CREATE TABLE product_goods_detail
(
    id           BIGINT UNSIGNED AUTO_INCREMENT COMMENT '数据ID',
    detail       TEXT COMMENT '详情',
    gmt_create   DATETIME DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified DATETIME DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) COMMENT '商品详情' CHARSET = utf8mb4;