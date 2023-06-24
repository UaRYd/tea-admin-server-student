package cn.tedu.tea.admin.server.content.pojo.vo.standard;

import lombok.Data;

@Data
public class CategoryStandardVO {
    private Long id;
    private String name;
    private Long parentId;
    private Integer depth;
    private String keywords;
    private Integer sort;
    private String icon;
    private Integer enable;
    private Integer isParent;
    private Integer isDisplay;
}
