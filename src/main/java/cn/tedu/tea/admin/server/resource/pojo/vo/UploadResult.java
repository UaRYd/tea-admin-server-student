package cn.tedu.tea.admin.server.resource.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UploadResult {
    private String url;
    private Long fileSize;
    private String contentType;
    private String fileName;
}
