package com.lcclovehww.wuyedemo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lcc
 * @since 2019-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlAdvertManage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 小区外键
     */
    private String regionId;

    /**
     * 公告内容外键
     */
    private String advertId;

    /**
     * 内容
     */
    private String content;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否推荐(0为不是,1为是)
     */
    private Integer priority;

    /**
     * 浏览量
     */
    private Integer scanCount;

    /**
     * 是否可用(0是不可以,1是可以)
     */
    private Integer adState;

    /**
     * 公告类型
     */
    private String adType;

    /**
     * 公告收取费用
     */
    private Integer adMoney;

    /**
     * 公告展示时间
     */
    private String adDays;

    /**
     * 删除标志(0为不是,1为是)
     */
    private Integer delFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 乐观锁标识
     */
    private Integer version;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
