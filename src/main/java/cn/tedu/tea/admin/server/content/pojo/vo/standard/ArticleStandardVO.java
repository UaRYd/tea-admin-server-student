package cn.tedu.tea.admin.server.content.pojo.vo.standard;

import lombok.Data;

@Data
public class ArticleStandardVO {
    private Long id;
    private Long authorId;
    private String authorName;
    private Long categoryId;
    private String title;
    private String brief;
    private String tags;
    private String ip;
    private Byte sort;
    private String coverUrl;
    private Integer upCount;
    private Integer downCount;
    private Integer clickCount;
    private Integer commentCount;
    private Integer checkState;
    private String checkRemarks;
    private Integer displayState;
}
