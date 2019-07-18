package demo.ms.dept.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 *
 * 描      述 :  Response
 * <p>
 *
 * @author :
 */
@Getter
@Setter
@ToString
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态信息，正确返回OK，否则返回 ERROR，如果返回ERROR则需要填写Message信息
     */
    private Status status = Status.OK;

    /**
     * Status
     **/
    public enum Status {
        /**
         * OK
         **/
        OK,
        /**
         * ERROR
         **/
        ERROR
    }

    /**
     * 返回码
     */
    private String code = PlatformCodeEnum.SUCCESS.getCode();

    /**
     * 提示信息
     */
    private String message = PlatformCodeEnum.SUCCESS.getValue();

    /**
     * 数据对象
     */
    private T data;

    /**
     * 时间戳
     */
    private Long date = System.currentTimeMillis();

    public Response<T> message(String message) {
        this.message = message;
        return this;
    }

    public Response<T> status(Status status) {
        this.status = status;
        return this;
    }

    public Response<T> code(String code) {
        this.code = code;
        return this;
    }

    public static <T> Response<T> ok() {
        return new Response<>();
    }

    public static <T> Response<T> ok(String message) {
        Response<T> result = new Response<>();
        result.setMessage(message);
        return result;
    }

    public static <T> Response<T> ok(T data) {
        Response<T> result = new Response<>();
        result.setData(data);
        return result;
    }

    public static <T> Response<T> ok(String message, T data) {
        Response<T> result = new Response<>();
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> Response<T> error(String message) {
        Response<T> result = new Response<>();
        result.setStatus( Status.ERROR);
        result.setCode(PlatformCodeEnum.SYSTEM_ERROR.getCode());
        result.setMessage(message);
        return result;
    }

    public static <T> Response<T> error(T data) {
        Response<T> result = new Response<>();
        result.setStatus( Status.ERROR);
        result.setCode(PlatformCodeEnum.SYSTEM_ERROR.getCode());
        result.setData(data);
        return result;
    }

    public static <T> Response<T> error(String code, String message) {
        Response<T> result = new Response<>();
        result.setStatus( Status.ERROR);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Response<T> error(String code, String message, T data) {
        Response<T> result = new Response<>();
        result.setStatus( Status.ERROR);
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> Response<T> error(String code, T data) {
        Response<T> result = new Response<>();
        result.setStatus( Status.ERROR);
        result.setCode(code);
        result.setData(data);
        return result;
    }

    public static <T> Response<T> error(PlatformCodeEnum code) {
        Response<T> result = new Response<>();
        result.setStatus( Status.ERROR);
        result.setCode(code.getCode());
        result.setMessage(code.getValue());
        return result;
    }

}
