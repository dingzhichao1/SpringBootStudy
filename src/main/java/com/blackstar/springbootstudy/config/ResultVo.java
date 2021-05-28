package com.blackstar.springbootstudy.config;

import com.blackstar.springbootstudy.config.constant.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description：Controller返回的统一Json格式
 *
 * @author yu.zhang-it
 * @version 1.0
 * @date 2020-05-19 10:16
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 向前台返回的数据
     */
    private T data;

    /**
     * 向前台返回的消息，一般之后状态码非200的时候使用
     */
    private String message;

    /**
     * 执行成功结果
     *
     * @param message 向前台返回的消息
     * @param data    向前台返回的数据
     * @return 统一的Json对象
     * @author yu.zhang-it
     */
    public static <T> ResultVo<T> ok(String message, T data) {
        return new ResultVo<>(ResponseCode.OK.getCode(), data, message);
    }

    /**
     * 执行成功结果
     *
     * @param data 向前台返回的数据
     * @return 统一的Json对象
     * @author yu.zhang-it
     */
    public static <T> ResultVo<T> ok(T data) {
        return new ResultVo<>(ResponseCode.OK.getCode(), data, "");
    }

    /**
     * 执行成功结果
     *
     * @return 统一的Json对象
     * @author yu.zhang-it
     */
    public static <T> ResultVo<T> ok() {
        return new ResultVo<>(ResponseCode.OK.getCode(), null, null);
    }
    
    /**
     * 执行结果异常（业务异常）
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> error(String message, T data) {
        return new ResultVo<>(ResponseCode.FRIENDLY_ERROR_CODE.getCode(), data, message);
    }
    
    public static <T> ResultVo<T> error(String message) {
        return new ResultVo<>(ResponseCode.FRIENDLY_ERROR_CODE.getCode(), null, message);
    }
    
}
