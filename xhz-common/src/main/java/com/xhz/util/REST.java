package com.xhz.util;
import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Create by XiaoMo
 * Date : 2017/10/9
 * Time : 14:29
 **/

/**
 * 返回前端数据结构
 *
 * @param <T>
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class REST<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    private REST(int status) {
        this.status = status;
    }

    private REST(RESTStatus status) {
        this.status = status.getCode();
        this.msg = status.getDesc();
    }

    private REST(RESTStatus status, String msg) {
        this.status = status.getCode();
        this.msg = msg;
    }

    private REST(RESTStatus status, T data) {
        this.status = status.getCode();
        this.msg = status.getDesc();
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    private static void setCode(RESTStatus status) {
        HttpServletResponse resp = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        resp.setStatus(status.getCode());
    }

    /**
     * 200 GET/PUT请求返回
     */

    public static <T> REST<T> OK() {
        setCode(RESTStatus.OK);
        return new REST<>(RESTStatus.OK);
    }

    public static <T> REST<T> OK(String msg) {
        setCode(RESTStatus.OK);
        return new REST<>(RESTStatus.OK, msg);
    }

    public static <T> REST<T> OK(T data) {
        setCode(RESTStatus.OK);
        return new REST<>(RESTStatus.OK, data);
    }

    /**
     * 201 POST请求返回
     */

    public static <T> REST<T> CREATED() {
        setCode(RESTStatus.CREATED);
        return new REST<>(RESTStatus.CREATED);
    }

    public static <T> REST<T> CREATED(String msg) {
        setCode(RESTStatus.CREATED);
        return new REST<>(RESTStatus.CREATED, msg);
    }

    public static <T> REST<T> CREATED(T data) {
        setCode(RESTStatus.CREATED);
        return new REST<>(RESTStatus.CREATED, data);
    }

    /**
     * 202 暂时挂起的请求
     */

    public static <T> REST<T> ACCEPTED() {
        setCode(RESTStatus.ACCEPTED);
        return new REST<>(RESTStatus.ACCEPTED);
    }

    public static <T> REST<T> ACCEPTED(String msg) {
        setCode(RESTStatus.ACCEPTED);
        return new REST<>(RESTStatus.ACCEPTED, msg);
    }

    public static <T> REST<T> ACCEPTED(T data) {
        setCode(RESTStatus.ACCEPTED);
        return new REST<>(RESTStatus.ACCEPTED, data);
    }

    /**
     * 204 DELETE请求返回
     */

    public static <T> REST<T> DELETED() {
        setCode(RESTStatus.DELETED);
        return new REST<>(RESTStatus.DELETED);
    }

    public static <T> REST<T> DELETED(String msg) {
        setCode(RESTStatus.DELETED);
        return new REST<>(RESTStatus.DELETED, msg);
    }

    public static <T> REST<T> DELETED(T data) {
        setCode(RESTStatus.DELETED);
        return new REST<>(RESTStatus.DELETED, data);
    }

    /**
     * 304 用于判断缓存是否过期
     */

    public static <T> REST<T> NO_CHANGE() {
        setCode(RESTStatus.NO_CHANGE);
        return new REST<>(RESTStatus.NO_CHANGE);
    }

    public static <T> REST<T> NO_CHANGE(String msg) {
        setCode(RESTStatus.NO_CHANGE);
        return new REST<>(RESTStatus.NO_CHANGE, msg);
    }

    public static <T> REST<T> NO_CHANGE(T data) {
        setCode(RESTStatus.NO_CHANGE);
        return new REST<>(RESTStatus.NO_CHANGE, data);
    }

    /**
     * 400 接口调用失败
     */

    public static <T> REST<T> BAD_REQUEST() {
        setCode(RESTStatus.BAD_REQUEST);
        return new REST<>(RESTStatus.BAD_REQUEST);
    }

    public static <T> REST<T> BAD_REQUEST(String msg) {
        setCode(RESTStatus.BAD_REQUEST);
        return new REST<>(RESTStatus.BAD_REQUEST, msg);
    }

    public static <T> REST<T> BAD_REQUEST(T data) {
        setCode(RESTStatus.BAD_REQUEST);
        return new REST<>(RESTStatus.BAD_REQUEST, data);
    }

    /**
     * 401 无身份信息
     */

    public static <T> REST<T> UNAUTHORIZED() {
        setCode(RESTStatus.UNAUTHORIZED);
        return new REST<>(RESTStatus.UNAUTHORIZED);
    }

    public static <T> REST<T> UNAUTHORIZED(String msg) {
        setCode(RESTStatus.UNAUTHORIZED);
        return new REST<>(RESTStatus.UNAUTHORIZED, msg);
    }

    public static <T> REST<T> UNAUTHORIZED(T data) {
        setCode(RESTStatus.UNAUTHORIZED);
        return new REST<>(RESTStatus.UNAUTHORIZED, data);
    }

    /**
     * 403 有身份信息，但是无权限调用
     */

    public static <T> REST<T> FORBIDDEN() {
        setCode(RESTStatus.FORBIDDEN);
        return new REST<>(RESTStatus.FORBIDDEN);
    }

    public static <T> REST<T> FORBIDDEN(String msg) {
        setCode(RESTStatus.FORBIDDEN);
        return new REST<>(RESTStatus.FORBIDDEN, msg);
    }

    public static <T> REST<T> FORBIDDEN(T data) {
        setCode(RESTStatus.FORBIDDEN);
        return new REST<>(RESTStatus.FORBIDDEN, data);
    }

    /**
     * 404 资源不存在
     */

    public static <T> REST<T> NOT_FOUND() {
        setCode(RESTStatus.NOT_FOUND);
        return new REST<>(RESTStatus.NOT_FOUND);
    }

    public static <T> REST<T> NOT_FOUND(String msg) {
        setCode(RESTStatus.NOT_FOUND);
        return new REST<>(RESTStatus.NOT_FOUND, msg);
    }

    public static <T> REST<T> NOT_FOUND(T data) {
        setCode(RESTStatus.NOT_FOUND);
        return new REST<>(RESTStatus.NOT_FOUND, data);
    }

    /**
     * 429 请求次数过多
     */

    public static <T> REST<T> TOO_MANY() {
        setCode(RESTStatus.TOO_MANY);
        return new REST<>(RESTStatus.TOO_MANY);
    }

    public static <T> REST<T> TOO_MANY(String msg) {
        setCode(RESTStatus.TOO_MANY);
        return new REST<>(RESTStatus.TOO_MANY, msg);
    }

    public static <T> REST<T> TOO_MANY(T data) {
        setCode(RESTStatus.TOO_MANY);
        return new REST<>(RESTStatus.TOO_MANY, data);
    }

    /**
     * 500 服务器错误
     */

    public static <T> REST<T> ERROR() {
        setCode(RESTStatus.ERROR);
        return new REST<>(RESTStatus.ERROR);
    }

    public static <T> REST<T> ERROR(String msg) {
        setCode(RESTStatus.ERROR);
        return new REST<>(RESTStatus.ERROR, msg);
    }

    public static <T> REST<T> ERROR(T data) {
        setCode(RESTStatus.ERROR);
        return new REST<>(RESTStatus.ERROR, data);
    }
}
