DROP TABLE IF EXISTS content_category;
CREATE TABLE content_category
(
    id           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '数据ID',
    name         VARCHAR(32)  DEFAULT '' COMMENT '类别名称',
    parent_id    BIGINT UNSIGNED DEFAULT 0 COMMENT '父级类别ID，如果无父级，则为0',
    depth        TINYINT UNSIGNED DEFAULT 0 COMMENT '深度，最顶级类别的深度为1，次级为2，以此类推',
    keywords     VARCHAR(256) DEFAULT '' COMMENT '关键词列表，各关键词使用英文的逗号分隔',
    sort         TINYINT UNSIGNED DEFAULT 0 NOT NULL COMMENT '排序序号',
    icon         VARCHAR(256) DEFAULT '' COMMENT '图标图片的URL',
    enable       TINYINT UNSIGNED DEFAULT 0 COMMENT '是否启用，1=启用，0=未启用',
    is_parent    TINYINT UNSIGNED DEFAULT 0 COMMENT '是否为父级（是否包含子级），1=是父级，0=不是父级',
    is_display   TINYINT UNSIGNED DEFAULT 0 COMMENT '是否显示在导航栏中，1=启用，0=未启用',
    gmt_create   DATETIME     DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified DATETIME     DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) COMMENT ='内容-类别' CHARSET = utf8mb4;

DROP TABLE IF EXISTS content_tag;
CREATE TABLE content_tag
(
    id           BIGINT UNSIGNED AUTO_INCREMENT COMMENT '数据ID',
    name         VARCHAR(32) DEFAULT '' NOT NULL COMMENT '标签名',
    parent_id    BIGINT      DEFAULT 0  NOT NULL COMMENT '父级ID，为0的是标签分类，不为0的是标签',
    enable       TINYINT UNSIGNED DEFAULT 0 NOT NULL COMMENT '是否启用，1=启用，0=未启用',
    sort         TINYINT UNSIGNED DEFAULT 0 NOT NULL COMMENT '排序序号',
    gmt_create   DATETIME    DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified DATETIME    DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) COMMENT '内容-标签' CHARSET = utf8mb4;

DROP TABLE IF EXISTS content_article;
CREATE TABLE content_article
(
    id            BIGINT UNSIGNED AUTO_INCREMENT COMMENT '数据ID',
    author_id     BIGINT(20) UNSIGNED NOT NULL COMMENT '作者ID',
    author_name   VARCHAR(32)   DEFAULT '' NOT NULL COMMENT '作者名字',
    category_id   BIGINT UNSIGNED NOT NULL COMMENT '类别ID',
    title         VARCHAR(256)  DEFAULT '' NOT NULL COMMENT '标题',
    brief         VARCHAR(256)  DEFAULT '' NOT NULL COMMENT '摘要',
    tags          VARCHAR(256)  DEFAULT '' NOT NULL COMMENT '标签列表，实际存入JSON数据',
    ip            VARCHAR(32)   DEFAULT '' NOT NULL COMMENT 'IP',
    sort          TINYINT       DEFAULT 0  NOT NULL COMMENT '排序序号',
    cover_url     VARCHAR(256)  DEFAULT '' NOT NULL COMMENT '封面图',
    up_count      INT UNSIGNED DEFAULT 0 NOT NULL COMMENT '顶数量',
    down_count    INT UNSIGNED DEFAULT 0 NOT NULL COMMENT '踩数量',
    click_count   INT UNSIGNED DEFAULT 0 NOT NULL COMMENT '浏览量',
    comment_count INT UNSIGNED DEFAULT 0 NOT NULL COMMENT '评论量',
    check_state   TINYINT       DEFAULT 0  NOT NULL COMMENT '审核状态，0=未审核，1=审核通过，2=拒绝审核',
    check_remarks VARCHAR(1024) DEFAULT '' NULL COMMENT '审核原因',
    display_state TINYINT UNSIGNED DEFAULT 0 NOT NULL COMMENT '显示状态，0=不显示，1=显示',
    gmt_create    DATETIME      DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified  DATETIME      DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) COMMENT '内容-文章' CHARSET = utf8mb4;

DROP TABLE IF EXISTS content_article_detail;
CREATE TABLE content_article_detail
(
    id           BIGINT UNSIGNED AUTO_INCREMENT COMMENT '数据ID',
    article_id   BIGINT UNSIGNED NOT NULL COMMENT '文章ID',
    detail       TEXT NOT NULL COMMENT '详情',
    gmt_create   DATETIME DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified DATETIME DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) COMMENT '内容-文章详情' CHARSET = utf8mb4;

DROP TABLE IF EXISTS content_up_down_log;
CREATE TABLE content_up_down_log
(
    id            BIGINT UNSIGNED AUTO_INCREMENT COMMENT '数据ID',
    user_id       BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
    resource_type TINYINT NOT NULL COMMENT '资源类型，0=文章，1=评论',
    resource_id   BIGINT UNSIGNED NOT NULL COMMENT '资源ID',
    op_type       TINYINT NOT NULL COMMENT '操作类型，0=踩，1=顶',
    gmt_create    DATETIME DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified  DATETIME DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) COMMENT '内容-顶踩日志' CHARSET = utf8mb4;

DROP TABLE IF EXISTS content_comment;
CREATE TABLE content_comment
(
    id            BIGINT UNSIGNED AUTO_INCREMENT COMMENT '数据ID',
    author_id     BIGINT(20) UNSIGNED NOT NULL COMMENT '作者ID',
    author_name   VARCHAR(32)   DEFAULT '' NOT NULL COMMENT '作者名字',
    article_id    BIGINT UNSIGNED DEFAULT 0 NOT NULL COMMENT '文章ID',
    content       VARCHAR(512)  DEFAULT '' NOT NULL COMMENT '评论内容',
    ip            VARCHAR(32)   DEFAULT '' NOT NULL COMMENT '踩数量',
    floor         INT UNSIGNED DEFAULT 0 NOT NULL COMMENT '楼层',
    up_count      INT UNSIGNED DEFAULT 0 NOT NULL COMMENT '顶数量',
    down_count    INT UNSIGNED DEFAULT 0 NOT NULL COMMENT '踩数量',
    check_state   TINYINT       DEFAULT 0  NOT NULL COMMENT '审核状态，0=未审核，1=审核通过，2=拒绝审核',
    check_remarks VARCHAR(1024) DEFAULT '' NULL COMMENT '审核原因',
    reference_ids VARCHAR(255)  DEFAULT '' NOT NULL COMMENT '引用评论ID',
    display_state TINYINT UNSIGNED DEFAULT 0 NOT NULL COMMENT '显示状态，0=不显示，1=显示',
    gmt_create    DATETIME      DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified  DATETIME      DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) COMMENT '内容-评论' CHARSET = utf8mb4;