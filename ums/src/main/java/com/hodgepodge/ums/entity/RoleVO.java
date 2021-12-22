package com.hodgepodge.ums.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 角色表页面实体类
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Data
@ApiModel(description = "角色表页面实体类")
public class RoleVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 角色名
     */
    @ApiModelProperty("角色名")
    private String name;

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