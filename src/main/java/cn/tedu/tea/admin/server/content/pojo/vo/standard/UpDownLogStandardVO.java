package cn.tedu.tea.admin.server.content.pojo.vo.standard;

import lombok.Data;

@Data
public class UpDownLogStandardVO {
    private Long id;
    private Long userId;
    private Integer resourceType;
    private Long resourceId;
    private Integer opType;
}
