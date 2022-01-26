package com.hodgepodge.ums.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 资源表页面实体类
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Data
@ApiModel(description = "资源表页面实体类")
public class ResourceVO {

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