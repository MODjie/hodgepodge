package com.hodgepodge.ums.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 资源表(UmsResource)页面实体类
 *
 * @author makejava
 * @since 2021-12-18 17:17:17
 */
@Data
@ApiModel(description = "资源表页面实体类")
public class UmsResourceVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String name;

    /**
     * 上级资源ID
     */
    @ApiModelProperty("上级资源ID")
    private Long parentId;

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