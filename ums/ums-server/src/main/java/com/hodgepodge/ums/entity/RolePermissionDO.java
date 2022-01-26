package com.hodgepodge.ums.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 角色权限关系表实体类
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Data
@TableName("ums_role_permission")
public class RolePermissionDO {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 用户ID
     */
    private Long permissionId;

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