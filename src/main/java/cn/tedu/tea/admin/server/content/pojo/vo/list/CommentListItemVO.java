package cn.tedu.tea.admin.server.content.pojo.vo.list;

import lombok.Data;

@Data
public class CommentListItemVO {
    private Long id;
    private Long authorId;
    private String authorName;
    private Long articleId;
    private String content;
    private String ip;
    private Integer floor;
    private Integer upCount;
    private Integer downCount;
    private Integer checkState;
    private String checkRemarks;
    private String referenceIds;
    private Integer displayState;
}
