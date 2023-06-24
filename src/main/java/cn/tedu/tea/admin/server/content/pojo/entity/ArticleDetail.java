package cn.tedu.tea.admin.server.content.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("content_article_detail")
public class ArticleDetail implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long articleId;
    private String detail;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}
