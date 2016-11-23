package com.dolphin.common.vo;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("serial")

public class PageableResult<T> implements DOLValueObject{
	
	/**
	 * 默认的页面显示记录条数
	 */
	public static int DEFAULT_PAGE_SIZE = 20;
	
	/**
	 * 每页的记录数
	 */
	private int pageSize;
	
	/**
	 * 当前页第一条数据在List中的位置,默认从0开始
	 */
	private long start;
	
	/**
	 * 当前页中存放的记录,类型一般为List
	 */
	private List<T> data;
	
	/**
	 * 数据库中记录的总条数
	 */
	private int totalSize;
	
	/**
	 * 构造方法，只构造空页.
	 */
	public PageableResult() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList<T>());
	}
	
	/**
	 * 默认构造方法.
	 *
	 * @param start	 本页数据在数据库中的起始位置
	 * @param totalSize 数据库中总记录条数
	 * @param pageSize  本页容量
	 * @param data	  本页包含的数据
	 */
	public PageableResult(long start, int totalSize, int pageSize, List<T> data) {
		this.start = start;
		this.totalSize = totalSize;
		this.pageSize = pageSize;
		this.data = data;
	}
	
	/**
	 * 取总记录数.
	 */
	public int getTotalSize() {
		return this.totalSize;
	}
	/**
	 * 取总页数
	 */
	public int getTotalPageCount() {
		if (totalSize % pageSize == 0)
			return (int)(totalSize / pageSize);
		else
			return (int)(totalSize / pageSize + 1);
	}
	/**
	 * 取每页数据容量.
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * 取当前页中的记录.
	 */
	public List<T> getdata() {
		return data;
	}
	/**
	 * 取该页当前页码,页码从1开始.
	 */
	public long getCurrentPageNo() {
		return start / pageSize + 1;
	}
	/**
	 * 该页是否有下一页.
	 */
	public boolean hasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount();
	}
	/**
	 * 该页是否有上一页.
	 */
	public boolean hasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}
	
	/**
	 * 获取任一页第一条数据在数据集的位置
	 *
	 * @param pageNo   从1开始的页号
	 * @param pageSize 每页记录条数
	 * @return 该页第一条数据
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
}
