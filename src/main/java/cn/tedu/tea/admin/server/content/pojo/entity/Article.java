package cn.tedu.tea.admin.server.content.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import jdk.jfr.Unsigned;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("content_article")
public class Article implements Serializable {

    @TableId(type = IdType.AUTO)
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

    @TableField(fill = FieldFill.INSERT)
    LocalDateTime gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    LocalDateTime gmtModified;
}
