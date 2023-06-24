package cn.tedu.tea.admin.server.content.pojo.vo.standard;

import lombok.Data;

@Data
public class TagStandardVO {
    private Long id;
    private String name;
    private Long typeId;
    private Integer enable;
    private Integer sort;
}
