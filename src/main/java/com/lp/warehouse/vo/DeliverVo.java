package com.lp.warehouse.vo;

import com.lp.warehouse.domain.Deliver;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @auth lp
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class DeliverVo  extends Deliver {
    private static final long serialVersionUID = 1L;


    private Integer page=1;
    private Integer limit=10;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
