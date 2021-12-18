package com.hodgepodge.ums.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息表(UmsUserInfo)实体类
 *
 * @author makejava
 * @since 2021-12-18 17:15:22
 */
@Data
@TableName("ums_user_info")
public class UmsUserInfo {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别：0：男，1：女，2：保密
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

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