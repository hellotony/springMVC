package com.Action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 与具体ORM实现无关的分页查询结果封装.
 * 
 * @param <T> Page中记录的类型.
 * 
 * @author calvin
 * @author badqiu
 */
public class Page<T> extends PageRequest implements Iterable<T>, Serializable {

	private static final long serialVersionUID = 4043760283128754382L;

	protected List<T> result = null;
	protected long totalItems = -1;
	private Integer sliderCount=0;

	public Page() {
	}

	public Page(PageRequest request) {
		this.pageNo = request.pageNo;
		this.pageSize = request.pageSize;
		this.countTotal = request.countTotal;
		this.orderBy = request.orderBy;
		this.orderDir = request.orderDir;
	}

	/**
	 * 获得页内的记录列表.
	 */
	public List<T> getResult() {
		return this.result;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * 获得总记录数, 默认值为-1.
	 */
	public long getTotalItems() {
		return this.totalItems;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalItems(final long totalItems) {
		this.totalItems = totalItems;
	}

	/**
	 * 实现Iterable接口, 可以for(Object item : page)遍历使用
	 */

	@Override
	public Iterator<T> iterator() {
		return this.result.iterator();
	}

	/**
	 * 根据pageSize与totalItems计算总页数.
	 */
	public int getTotalPages() {
		return (int) Math.ceil((double) this.totalItems / (double) this.getPageSize());

	}

	/**
	 * 是否还有下一页.
	 */
	public boolean hasNextPage() {
		return ((this.getPageNo() + 1) <= this.getTotalPages());
	}

	/**
	 * 是否最后一页.
	 */
	public boolean isLastPage() {
		return !this.hasNextPage();
	}
	
	public Integer getSliderCount() {
		return sliderCount;
	}

	
	public void setSliderCount(Integer sliderCount) {
		this.sliderCount = sliderCount;
	}

	/**
	 * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (this.hasNextPage())
			return this.getPageNo() + 1;
		else
			return this.getPageNo();
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean hasPrePage() {
		return (this.getPageNo() > 1);
	}

	/**
	 * 是否第一页.
	 */
	public boolean isFirstPage() {
		return !this.hasPrePage();
	}

	/**
	 * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (this.hasPrePage())
			return this.getPageNo() - 1;
		else
			return this.getPageNo();
	}

	/**
	 * 计算以当前页为中心的页面列表,如"首页,23,24,25,26,27,末页"
	 * 
	 * @param count 需要计算的列表大小
	 * @return pageNo列表
	 */
	public List<Integer> getSlider(int count) {
		final int halfSize = count / 2;
		//是否是偶数
		//final boolean isOwe = count%2==0?true:false; 
		final int totalPage = this.getTotalPages();
		int startPageNo = Math.max(this.getPageNo() - halfSize, 1);
		final int endPageNo = Math.min((startPageNo + count) - 1, totalPage);

		if ((endPageNo - startPageNo+1) < count) {
			startPageNo = Math.max(endPageNo - count, 1);
		}
                                                            
		final List<Integer> result = new ArrayList<Integer>();
		for (int i = startPageNo; i <= endPageNo; i++) {
			result.add(i);
		}
		return result;
	}
	
	public List<Integer> getAjaxSlider() {
		final int halfSize = this.sliderCount / 2;
		//是否是偶数
		//final boolean isOwe = count%2==0?true:false; 
		final int totalPage = this.getTotalPages();
		int startPageNo = Math.max(this.getPageNo() - halfSize, 1);
		final int endPageNo = Math.min((startPageNo + this.sliderCount) - 1, totalPage);

		/*if ((endPageNo - startPageNo+1) < count) {
			startPageNo = Math.max(endPageNo - count, 1);
		}*/
                                                            
		final List<Integer> result = new ArrayList<Integer>();
		for (int i = startPageNo; i <= endPageNo; i++) {
			result.add(i);
		}
		return result;
	}
	
	public Integer getCurrentPageNo() {
		return pageNo;
	}
	
}
