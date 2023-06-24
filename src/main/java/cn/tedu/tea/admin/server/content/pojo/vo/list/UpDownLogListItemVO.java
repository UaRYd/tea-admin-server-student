package cn.tedu.tea.admin.server.content.pojo.vo.list;

import lombok.Data;

@Data
public class UpDownLogListItemVO {
    private Long id;
    private Long userId;
    private Integer resourceType;
    private Long resourceId;
    private Integer opType;
}
