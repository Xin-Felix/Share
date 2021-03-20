package work.huangxin.share.globalException;

import lombok.Data;
import work.huangxin.share.util.status.BaseResponse;


@Data
public class MyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected Integer errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public MyException() {
        super();
    }

    public MyException(BaseResponse errorInfoInterface) {
        super(errorInfoInterface.getCode().toString());
        this.errorCode = errorInfoInterface.getCode();
        this.errorMsg = errorInfoInterface.getMsg();
    }

    public MyException(BaseResponse errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getCode().toString(), cause);
        this.errorCode = errorInfoInterface.getCode();
        this.errorMsg = errorInfoInterface.getMsg();
    }

    public MyException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

}