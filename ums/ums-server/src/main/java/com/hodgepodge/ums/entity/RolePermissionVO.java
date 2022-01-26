package com.hodgepodge.ums.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 角色权限关系表页面实体类
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Data
@ApiModel(description = "角色权限关系表页面实体类")
public class RolePermissionVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    private Long roleId;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long permissionId;

    /**
     * 是否删除
     */
    @ApiModelProperty("是否删除")
    private Boolean isDelete;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

}