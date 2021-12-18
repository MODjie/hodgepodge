package com.hodgepodge.ums.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 权限表(UmsResourcePermission)实体类
 *
 * @author makejava
 * @since 2021-12-18 17:17:17
 */
@Data
@TableName("ums_resource_permission")
public class UmsResourcePermission {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 资源ID
     */
    private Long resourceId;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}