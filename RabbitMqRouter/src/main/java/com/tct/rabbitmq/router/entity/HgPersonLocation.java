package com.tct.rabbitmq.router.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 人员位置信息表
 * </p>
 *
 * @author lcc
 * @since 2019-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HgPersonLocation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 传输基站id
     */
    private Integer baseStationId;

    /**
     * 数据采集设备id
     */
    private Integer deviceId;

    /**
     * 数据上报时间
     */
    private LocalDateTime reportTime;

    /**
     * 人员id
     */
    private String personId;

    /**
     * on打开？ off关闭
     */
    private String status;


}
