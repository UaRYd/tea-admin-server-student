package cn.tedu.tea.admin.server.content.pojo.vo.list;

import lombok.Data;

import java.io.Serializable;

/**
 * 列表项VO类：内容-标签
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Data
public class TagListItemVO implements Serializable {

    /**
     * 数据ID
     */
    private Long id;
    /**
     * 标签名称
     */
    private String name;
    /**
     * 标签类别ID
     */
    private Long typeId;
    /**
     * 标签类别名称
     */
    private String typeName;
    /**
     * 是否启用，1=启用，0=未启用
     */
    private Integer enable;
    /**
     * 排序序号
     */
    private Integer sort;

}