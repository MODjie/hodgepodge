package com.hodgepodge.ums.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 角色表(UmsRole)页面实体类
 *
 * @author makejava
 * @since 2021-12-18 17:17:17
 */
@Data
@ApiModel(description = "角色表页面实体类")
public class UmsRoleVO {

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
    private Integer isDelete;

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