package com.shsc.plugin.dfs.exception;

/**
 * @author fangxs
 * @className FastDFSException
 * @date 2019/6/27 14:35
 * @description 异常
 **/
public class FastDfsException  extends Exception  {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误消息
     */
    private String message;

    public FastDfsException(){}

    public FastDfsException(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
