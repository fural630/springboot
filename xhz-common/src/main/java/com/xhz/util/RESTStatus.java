package com.xhz.util;

/**
 * Create by XiaoMo
 * Date : 2018/5/3
 * Time : 10:13
 **/
public enum RESTStatus {
    // 常用，用于GET【查询】/PUT【修改】请求
    OK(200,"成功"),
    // 常用，用于POST【新增】请求
    CREATED(201,"创建成功"),
    // 不常用，暂时挂起时启用，如异步处理某信息
    ACCEPTED(202,"已接受请求"),
    // 常用，用于DELETE【删除】请求
    DELETED(204,"删除成功"),
    // 不常用，用于查询客户端缓存数据是否适用，一般附带时间戳请求
    NO_CHANGE(304,"数据无变化"),
    // 常用，请求的参数不合法，如参数格式不对、参数为空等
    BAD_REQUEST(400,"非法调用"),
    // 常用，用户未登录时某些操作不许可时使用
    UNAUTHORIZED(401,"无身份信息"),
    // 常用，用户登录但权限不足时使用
    FORBIDDEN(403,"无操作权限"),
    // 常用，数据查询为空时使用
    NOT_FOUND(404,"资源不存在"),
    // 常用，用户短时间发送短信等验证
    TOO_MANY(429,"请求次数过多"),
    // 常用，异常捕获
    ERROR(500,"服务器错误"),
    ;

    private final int code;
    private final String desc;

    RESTStatus(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
