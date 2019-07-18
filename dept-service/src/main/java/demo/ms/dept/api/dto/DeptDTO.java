package demo.ms.dept.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 * 版      权 :  com.each
 * 包      名 : demo.ms.com.dto
 * 描      述 :  施工单位管理请求参数
 * 创  建  人 :  yanfeng
 *
 * @author yanfeng
 * 创建 时 间:  2019-05-16 15:59:01
 */

@Getter
@Setter
@ToString
public class DeptDTO  {

    /**
     * 施工单位
     */
    private String deptName;


    /**
     * 供电单位
     */
    private String orgNo;


    /**
     * 责任人
     */
    private String sysUserName;


    /**
     * 联系人
     */
    private String concatName;


    /**
     * 日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date concatTime;


    /**
     * 备注
     */
    private String remark;


    /**
     * 删除标志:Y 删除  N 不删
     */
    private String deleteFlag;


}
