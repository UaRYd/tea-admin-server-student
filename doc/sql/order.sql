CREATE TABLE order_address
(
    id             BIGINT UNSIGNED AUTO_INCREMENT COMMENT '数据ID',
    user_id        BIGINT UNSIGNED  DEFAULT 0 COMMENT '用户ID',
    receiver_name  VARCHAR(32)      DEFAULT '' COMMENT '收货人',
    receiver_phone VARCHAR(13)      DEFAULT '' COMMENT '收货电话',
    province       VARCHAR(32)      DEFAULT '' COMMENT '省名称',
    province_code  INT              DEFAULT 0 COMMENT '省编码',
    city           VARCHAR(32)      DEFAULT '' COMMENT '市名称',
    city_code      INT              DEFAULT 0 COMMENT '市编码',
    area           VARCHAR(32)      DEFAULT '' COMMENT '区名称',
    area_code      INT              DEFAULT 0 COMMENT '区编码',
    detail         VARCHAR(128)     DEFAULT '' COMMENT '详细地址',
    is_default     TINYINT UNSIGNED DEFAULT 0 COMMENT '是否默认',
    gmt_create     DATETIME         DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified   DATETIME         DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) COMMENT '收货地址' CHARSET = utf8mb4;

CREATE TABLE order_main
(
    id              BIGINT UNSIGNED AUTO_INCREMENT COMMENT '数据ID',
    buyer_id        BIGINT UNSIGNED COMMENT '用户ID',
    buyer_username  VARCHAR(50)        NULL COMMENT '用户名',
    order_no        VARCHAR(50) COMMENT '订单编号',
    goods_num       INT COMMENT '商品数量',
    total_price     BIGINT   DEFAULT 0 NULL COMMENT '商品销售总价',
    logistics_no    VARCHAR(50)        NULL COMMENT '物流单号',
    pay_channel     INT                NULL COMMENT '支付渠道：1-支付宝，2-微信',
    pay_method      INT                NULL COMMENT '支付方式:1-在线支付，2-货到付款',
    buyer_remark    VARCHAR(100)       NULL COMMENT '买家备注',
    platform_remark VARCHAR(100)       NULL COMMENT '平台备注',
    order_status    INT      DEFAULT 0 COMMENT '订单状态: 0:待支付, 1:已支付,待发货, 2:已发货/待收货, 3:确认收货/已完成, 4: 用户关闭, 5:平台关闭(商家), 6:系统调度关闭',
    paid_time       DATETIME           NULL COMMENT '支付时间',
    gmt_create      DATETIME DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified    DATETIME DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) COMMENT '订单主表' CHARSET = utf8mb4;

CREATE TABLE order_item
(
    id              BIGINT UNSIGNED AUTO_INCREMENT COMMENT '数据ID',
    order_no        VARCHAR(50) COMMENT '订单编号',
    goods_id        BIGINT COMMENT 'SKU_ID',
    goods_cover_url VARCHAR(100)       NULL COMMENT '商品图片',
    goods_title     VARCHAR(100) COMMENT '商品标题',
    goods_code      VARCHAR(50)        NULL COMMENT '商品货号',
    goods_num       INT COMMENT '商品数量',
    sale_unit_price BIGINT   DEFAULT 0 NULL COMMENT '商品单价',
    gmt_create      DATETIME DEFAULT NULL COMMENT '数据创建时间',
    gmt_modified    DATETIME DEFAULT NULL COMMENT '数据最后修改时间',
    PRIMARY KEY (id)
) COMMENT '订单商品明细' CHARSET = utf8mb4;