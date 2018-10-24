package com.example.code.web.page;

import java.util.List;

import com.example.code.constant.web.respones.ResponesConstant;
import com.github.pagehelper.PageInfo;

public class RePage<T> {
	
	// 返回状态
	private Integer code = ResponesConstant.SUCCESS;
	
	// 提示消息
	private String msg;
	
	// 当前页
    private Integer nowPage;
    
    // 每页显示的总条数
    private Integer pageSize;
	
	// 总记录数
	private Long totalNum;
	
	// 总页数
    private Integer totalPage;
    
    // 分页结果
    private List<T> items;
    
    
    public RePage(PageBean page, List<T> items) {
    	this.nowPage = page.getNowPage();
    	this.pageSize = page.getPageSize();
    	PageInfo<T> pageInfo = new PageInfo<T>(items);
    	this.totalNum = pageInfo.getTotal();
    	this.totalPage = pageInfo.getPages();
    	this.items = items;
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


	public Integer getNowPage() {
		return nowPage;
	}


	public void setNowPage(Integer nowPage) {
		this.nowPage = nowPage;
	}


	public Integer getPageSize() {
		return pageSize;
	}


	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	public Long getTotalNum() {
		return totalNum;
	}


	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}


	public Integer getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}


	public List<T> getItems() {
		return items;
	}


	public void setItems(List<T> items) {
		this.items = items;
	}
}
