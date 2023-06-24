package cn.tedu.tea.admin.server.common.web;

import cn.tedu.tea.admin.server.common.ex.ServiceException;
import lombok.Data;

/**
 * 服务器端的统一响应类型
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Data
public class JsonResult {

    /**
     * 响应的业务状态码值
     */
    private Integer state;
    /**
     * 操作失败时的描述文本
     */
    private String message;
    /**
     * 操作成功时的响应数据
     */
    private Object data;

    public static JsonResult ok() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setState(ServiceCode.OK.getValue());
        return jsonResult;
    }

    public static JsonResult ok(Object data) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setState(ServiceCode.OK.getValue());
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult fail(ServiceException e) {
        // JsonResult jsonResult = new JsonResult();
        // jsonResult.setState(e.getServiceCode().getValue());
        // jsonResult.setMessage(e.getMessage());
        // return jsonResult;
        return fail(e.getServiceCode(), e.getMessage());
    }

    public static JsonResult fail(ServiceCode serviceCode, String message) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setState(serviceCode.getValue());
        jsonResult.setMessage(message);
        return jsonResult;
    }

}
