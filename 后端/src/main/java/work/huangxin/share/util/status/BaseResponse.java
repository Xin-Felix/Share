package work.huangxin.share.util.status;

/**
 * 基本响应封装
 */
public class BaseResponse {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;


    protected BaseResponse() {
    }


    protected BaseResponse(CodeEnum code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }


    public static BaseResponse out(CodeEnum code) {
        return new BaseResponse(code);
    }


    public Integer getCode() {
        return code;
    }


    public void setCode(Integer code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }
}

