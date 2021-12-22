package com.hodgepodge.ums.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息表页面实体类
 *
 * @author makejava
 * @since 2021-12-22 13:07:49
 */
@Data
@ApiModel(description = "用户信息表页面实体类")
public class UserInfoVO {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 用户编号
     */
    @ApiModelProperty("用户编号")
    private Long userId;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String realName;

    /**
     * 性别：0：男，1：女，2：保密
     */
    @ApiModelProperty("性别：0：男，1：女，2：保密")
    private Integer sex;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Integer age;

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