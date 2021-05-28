package com.blackstar.springbootstudy.config.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：返回值枚举类
 *
 * @author zhichao.ding
 * @version 1.0
 * @date 2020/6/24 10:35
 */
public enum ResponseCode {

    OK(200, "OK"),
    SYSTEM_ERROR_CODE(100, "系统异常"),
    FRIENDLY_ERROR_CODE(301, "业务异常"),
    AUTH_ERROR_CODE(401, "权限异常"), NO_FOUND_ERROR_CODE(404, "请求不存在"),
    INTERNAL_ERROR_CODE(500, "服务异常"), ARGS_ERROR_CODE(501, "参数异常");


    private static Map<Integer, String> messageMap = new HashMap();

    static {
        ResponseCode[] values = ResponseCode.values();
        int length = values.length;
        for (int i = 0; i < length; i++) {
            ResponseCode responseCodeEnum = values[i];
            int code = responseCodeEnum.getCode();
            String msg = responseCodeEnum.getMsg();
            messageMap.put(code, msg);
        }

    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResponseCode(int code) {
        this.code = code;
    }

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 通过异常类获取默认的异常消息
     *
     * @param code
     * @return
     */
    public static String getMessage(int code){
        return messageMap.get(code);
    }
}
