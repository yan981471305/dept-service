package demo.ms.dept.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Copyright (c) 2018-present, Sinovatio Corporation.
 * All rights reserved.
 * <p/>Created by yanfeng on 2019-05-16 15:59:01.
 *
 * @author yanfeng
 * @since 2019-05-16 15:59:01
 */
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dept")
public class DeptEntity implements Serializable {
    /**
     * 记录标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Excel( name = "id",orderNum = "0")
    private Integer deptId;

    /**
     * 施工单位
     */
    @Excel( name = "施工单位",orderNum = "1")
    private String deptName;

    /**
     * 供电单位
     */
    @Excel( name = "供电单位",orderNum = "2")
    private String orgNo;

    /**
     * 责任人
     */
    @Excel( name = "责任人",replace = {"男_1","女_2"},orderNum = "3")
    private String sysUserName;

    /**
     * 联系人
     */
    @Excel( name = "联系人",orderNum = "4")
    private String concatName;

    /**
     * 联系电话
     */
    @Excel( name = "日期",orderNum = "5")
    private Date concatTime;

    /**
     * 备注
     */
    @Excel( name = "备注",orderNum = "6")
    private String remark;

    /**
     * 删除标志:Y 删除  N 不删
     */
    @Excel( name = "删除标志",orderNum = "7")
    private String deleteFlag;

    private List<DeptEntity> children;
}
