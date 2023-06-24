package cn.tedu.tea.admin.server.common.web;

public enum ServiceCode {

    OK(20000),
    ERROR_BAD_REQUEST(40000),

    ERROR_UNAUTHORIZED(40100),
    /**
     * 错误：未认证，因为被禁用
     */
    ERROR_UNAUTHORIZED_DISABLED(40101),
    /**
     * 错误：禁止访问，用于无权限
     */
    ERROR_FORBIDDEN(40300),
    ERROR_NOT_FOUND(40400),
    ERROR_CONFLICT(40900),
    /**
     * 错误：未知的插入数据失败
     */
    ERROR_INSERT(50000),
    /**
     * 错误：未知的删除数据失败
     */
    ERROR_DELETE(50100),
    /**
     * 错误：未知的修改数据失败
     */
    ERROR_UPDATE(50200),
    ERROR_UNKNOWN(99999);


    private Integer value;

    public Integer getValue() {
        return value;
    }

    ServiceCode(Integer value) {
        this.value = value;
    }
}
