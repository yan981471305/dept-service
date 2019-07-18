package demo.ms.dept.exception;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * 描      述 :  BaseException
 * <p>
 *
 * @author :
 */
@Getter
@Setter
public class BaseException extends RuntimeException {

    /**z
     * 自定义异常码
     **/
    private String errorCode;

    /**
     * 异常码消息
     **/
    private String errorMsg;


    public BaseException() {
    }


    public BaseException(String errorCode) {
        super(ErrorHandler.getErrMsg(errorCode));
        this.errorCode = errorCode;
        this.errorMsg = ErrorHandler.getErrMsg(errorCode);
    }

    public BaseException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BaseException(Throwable exception) {
        super(exception);
    }

    public BaseException(String errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}
