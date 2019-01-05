package com.frame.page;

import java.util.Collections;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

public class LayPage<T> implements IPage<T>{
	
	private static final long serialVersionUID = 8545996863226528798L;
	
	/**
     * 查询数据列表
     */
    private List<T> data = Collections.emptyList();
    
    /**
     * 总数，当 count 为 null 或者大于 0 分页插件不在查询总数
     */
    private long count = 0;
    
    /**
     * 每页显示条数，默认 10
     */
    private long limit = 10;
    /**
     * 当前页
     */
    private long page = 1;
    
    
	
	@Override
	public List<T> getRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPage<T> setRecords(List<T> records) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IPage<T> setTotal(long total) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IPage<T> setSize(long size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCurrent() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IPage<T> setCurrent(long current) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
