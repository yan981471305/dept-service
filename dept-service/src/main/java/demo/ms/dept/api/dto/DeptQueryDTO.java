package demo.ms.dept.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (c) 2018-present, Sinovatio Corporation.
 * All rights reserved.
 * <p/>Created by yanfeng on 2019-05-16 15:59:01.
 *
 * @author yanfeng
 * @since 2019-05-16 15:59:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "施工单位管理")
public class DeptQueryDTO {

    @ApiModelProperty(value = "日期")
    private String concatTime;

}
