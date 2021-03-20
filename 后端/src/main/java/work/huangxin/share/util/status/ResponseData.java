package work.huangxin.share.util.status;

import com.alibaba.fastjson.JSONObject;
import work.huangxin.share.globalException.MyException;


public class ResponseData<T> extends BaseResponse {

    private T data;


    private ResponseData() {
    }


    private ResponseData(CodeEnum code, T data) {
        super(code);
        this.data = data;
    }

    public static <T> ResponseData<T> out(CodeEnum code, T data) {
        return new ResponseData<T>(code, data);
    }


    public T getData() {
        return data;
    }


    public void setData(T data) {
        this.data = data;
    }


    /**
     * 成功
     *
     * @return
     */
    public static ResponseData success() {
        return success(null);
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static ResponseData success(Object data) {
        ResponseData rs = new ResponseData();
        rs.setCode(CodeEnum.SUCCESS.getCode());
        rs.setMsg(CodeEnum.SUCCESS.getMsg());
        rs.setData(data);
        return rs;
    }

    /**
     * 失败
     */
    public static ResponseData error(MyException errorInfo) {
        ResponseData rs = new ResponseData();
        rs.setCode(CodeEnum.SUCCESS.getCode());
        rs.setMsg(CodeEnum.SUCCESS.getMsg());
        rs.setData(null);
        return rs;
    }

    /**
     * 失败
     */
    public static ResponseData error(CodeEnum codeEnum) {
        ResponseData rs = new ResponseData();
        rs.setCode(codeEnum.getCode());
        rs.setMsg(codeEnum.getMsg());
        rs.setData(null);
        return rs;
    }

    /**
     * 失败
     */
    public static ResponseData error(Integer code, String msg) {
        ResponseData rs = new ResponseData();
        rs.setCode(code);
        rs.setMsg(msg);
        rs.setData(null);
        return rs;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }


}