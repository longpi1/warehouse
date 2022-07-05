package com.lp.sys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * json数据实体
 *
 * @author lp
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView {

    private Integer code = 0;
    private String msg = "";
    private Long count = 0L;
    private Object data;

    public DataGridView(Long count, Object data) {
        super();
        this.count = count;
        this.data = data;
    }

    public DataGridView(Object data) {
        super();
        this.data = data;
    }
}
