package com.feed.ecp.common.pager;

/**
 * @author 金雷
 *  date : 2013-5-15 下午04:31:10 
 *  description ：分页静态属性类
 */ 
public class SystemContext {
	
	private static ThreadLocal<Integer> pagenum=new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pagesize=new ThreadLocal<Integer>();
	
	public static int getPagenum(){
		Integer pn=(Integer)pagenum.get();
		if(pn==null){
			return 1;
		}
		return pn;
	}
	
	public static void setPagenum(int pagenumvalue){
		pagenum.set(pagenumvalue);
	}
	
	public static void removePagenum(){
		pagenum.remove();
	}
	
	public static int getPagesize(){
		Integer ps=(Integer)pagesize.get();
		if(ps==null){
			return Integer.MAX_VALUE;
		}
		return ps;
	}
	
	public static void setPagesize(int pagesizevalue){
		pagesize.set(pagesizevalue);
	}
	
	public static void removePagesize(){
		pagesize.remove();
	}
}
