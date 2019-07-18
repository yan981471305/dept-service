package demo.ms.dept.config;

import lombok.Getter;
import lombok.Setter;

/**
 * 版      权 :  jariec.com
 * 包      名 : com.jariec.zmo.frame.core.enums
 * 描      述 :  PlatformCodeEnum
 * 创建 时 间:  2018/08/05
 * <p>
 *
 * @author :
 */
@Getter
public enum PlatformCodeEnum {
    /**
     * 成功响应
     **/
    SUCCESS("10000", "成功响应"),
    /**
     * 结果为空
     **/
    RESULT_IS_NULL("90002", "结果为空"),
    /**
     * 保存失败
     **/
    SAVE_ERROR("10001", "保存失败"),
    /**
     * 删除失败
     **/
    DELETE_ERROR("10002", "删除失败"),
    /**
     * 系统错误
     **/
    SYSTEM_ERROR("90000", "系统错误"),
    /**
     * 参数为空
     **/
    PARAM_IS_NULL("10001", "参数为空"),
    /**
     * 非法参数
     **/
    PARAM_IS_INVALID("90001", "非法参数"),
    /**
     * 没有认证
     **/
    SC_UNAUTHORIZED("401", "没有认证"),
    /**
     * 没有权限
     **/
    SC_FORBIDDEN("403", "没有权限");

    /**
     * code
     **/
    @Setter
    private String code;
    /**
     * value
     **/
    @Setter
    private String value;

    PlatformCodeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
