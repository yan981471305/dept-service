package demo.ms.dept.exception;


import org.apache.commons.lang3.StringUtils;

import java.util.Properties;


/**
 *
 * 描      述 :  ErrorHandler
 * <p>
 *
 * @author :
 */
public class ErrorHandler {

    /**
     * 配置文件
     **/
    private static Properties errorCodesProps;


    private ErrorHandler() {
    }


    /**
     * 获取错误消息<br/>
     * <p>
     * 创建人：
     * 创建日期：   2018-08-05
     * 描述    :    获取错误消息
     *
     * @param errId
     * @return String
     * @throws BaseException
     */
    public static String getErrMsg(String errId) {

        String errMsg = "";

        if (errorCodesProps != null) {
            errMsg = errorCodesProps.getProperty(errId);
        }

        if (StringUtils.isBlank(errMsg)) {
            errMsg = errId;
        }

        return errMsg;
    }

    /**
     * 获取错误消息<br/>
     * <p>
     * 创建人：
     * 创建日期：   2018-08-05
     * 描述    :    获取错误消息
     *
     * @param errId
     * @return String
     * @throws BaseException
     */
    public static String getErrMsg(String errId, Object... args) {

        String errMsg = "";

        if (errorCodesProps != null) {
            errMsg = errorCodesProps.getProperty(errId);
        }

        if (StringUtils.isBlank(errMsg)) {
            errMsg = errId;
        } else {
            errMsg = String.format(errMsg, args);
        }

        return errMsg;
    }

    /**
     * 加载配置文件<br/>
     * <p>
     * 创建人：
     * 创建日期：   2018-08-05
     * 描述    :    获取错误消息
     *
     * @param props
     * @return
     * @throws BaseException
     */
    public static void loadErrorCodes(Properties props) {
        errorCodesProps = props;
    }
}
