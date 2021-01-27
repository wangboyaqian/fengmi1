package com.dl.code.utils;

public class PageUtil {
	private int pageSize;//每页显示多少条 4
	private int pageCount;//一共多少页   5+1
	private int dataCount;//总数据数   21
	private int pageNo;//当前页码  1 2 3

	public PageUtil() {
	}

	public PageUtil(int pageSize, int dataCount) {
		this.pageSize = pageSize;
		this.dataCount = dataCount;
		this.pageCount = (dataCount % pageSize == 0) ? dataCount / pageSize : dataCount / pageSize + 1;
	}

	public int getPageSize() {

		return pageSize;
	}
	public void setPageSize(int pageSize) {

		this.pageSize = pageSize;
	}
	public int getPageCount() {

		return pageCount;
	}
	public void setPageCount(int pageCount) {

		this.pageCount = pageCount;
	}
	public int getDataCount() {

		return dataCount;
	}
	public void setDataCount(int dataCount)
	{
		this.dataCount = dataCount;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	
}
