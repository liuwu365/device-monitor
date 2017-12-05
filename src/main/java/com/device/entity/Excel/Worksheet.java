package com.device.entity.Excel;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @User: liuwu_eva@163.com
 * @Date: 2017-09-11 17:03
 */
@Data
public class Worksheet {
    private String sheet;

    private int columnNum;

    private int rowNum;

    private List<String> title;

    private List<Row> rows;


}
