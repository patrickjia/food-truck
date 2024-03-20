package com.patrick.common.reponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public Result<T> success(T date) {
        this.message = "操作成功";
        this.code = 200;
        this.data = date;
        return this;
    }

    public static <T> Result<T> builderSuccess(T data) {
        return (new Result<T>()).success(data);
    }

    public Result<T> fail(T data) {
        this.message = "操作失败";
        this.code = 400;
        this.data = data;
        return this;
    }

    public Result<T> fail(Integer code, String message) {
        this.message = message;
        this.code = code;
        return this;
    }


    public static <T> Result<T> builderFail(T data) {
        return (new Result<T>()).fail(data);
    }

    public static <T> Result<T> builderFail(Integer code, String message) {
        return (new Result<T>()).fail(code, message);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue, SerializerFeature.IgnoreNonFieldGetter);
    }

    public Integer getCode() {
        return this.code;
    }

    public Result<T> setCode(final Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public Result<T> setMessage(final String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public Result<T> setData(final T data) {
        this.data = data;
        return this;
    }
}
