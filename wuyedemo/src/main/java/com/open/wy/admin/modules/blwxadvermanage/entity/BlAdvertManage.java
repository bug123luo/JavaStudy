package com.open.wy.admin.modules.blwxadvermanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lcc
 * @since 2019-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlAdvertManage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uuid主键
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 小区外键
     */
    private String regionId;

    /**
     * 栋Id
     */
    private String houseId;

    /**
     * 单元Id
     */
    private String unitId;

    /**
     * 文件url
     */
    private String fileUrl;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

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

    private String regionName;


}
