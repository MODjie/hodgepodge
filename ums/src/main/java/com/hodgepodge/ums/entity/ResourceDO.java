package com.hodgepodge.ums.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 资源表实体类
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Data
@TableName("ums_resource")
public class ResourceDO {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 上级资源ID
     */
    private Long parentId;

    /**
     * 是否删除
     */
    private Boolean isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}