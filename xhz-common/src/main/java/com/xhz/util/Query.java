package com.xhz.util;


import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.xhz.xss.SQLFilter;

/**
 * 查询参数
 *
 * @author lipengjun
 * @date 2017年11月18日 下午13:13:23
 */
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    //当前页码
    private int page = 1;
    //每页条数
    private int limit = 10;

    public Query(Map<String, Object> params) {
        this.putAll(params);
        
        //分页参数
        if (null != params.get("page")) {
        	this.page = Integer.parseInt(params.get("page").toString());
        }
        if (null != params.get("limit")) {
        	this.limit = Integer.parseInt(params.get("limit").toString());
        }
        this.put("offset", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
        
        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        if (null != params.get("order") && StringUtils.isNoneEmpty(params.get("order").toString())) {
        	if (null != params.get("field")) {
        		String field = params.get("field").toString();
        		String order = params.get("order").toString();
        		this.put("field", SQLFilter.sqlInject(field));
        		this.put("order", SQLFilter.sqlInject(order));
        	}
        } else {
        	this.remove("field");
        	this.remove("order");
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
