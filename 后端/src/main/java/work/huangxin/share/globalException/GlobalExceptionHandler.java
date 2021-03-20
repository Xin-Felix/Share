package work.huangxin.share.globalException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import work.huangxin.share.util.status.BaseResponse;
import work.huangxin.share.util.status.CodeEnum;
import work.huangxin.share.util.status.ResponseData;


import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public BaseResponse bizExceptionHandler(HttpServletRequest req, MyException e) {
        logger.error("发生业务异常！原因是：{}", e.getErrorMsg());
        return ResponseData.error(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResponseData exceptionHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("发生空指针异常！原因是:", e);
        return ResponseData.error(CodeEnum.BODY_NOT_MATCH);
    }


    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseData exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("未知异常！原因是:", e);
        return ResponseData.error(CodeEnum.INTERNAL_SERVER_ERROR.getCode(), CodeEnum.INTERNAL_SERVER_ERROR.getMsg());
    }
}