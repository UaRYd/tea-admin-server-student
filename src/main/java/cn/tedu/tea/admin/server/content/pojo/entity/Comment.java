package cn.tedu.tea.admin.server.content.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("content_comment")
public class Comment implements Serializable {

    @TableId(type = IdType.AUTO)
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

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}