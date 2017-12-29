package com.et.model.orderList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.et.model.PageTools;

public class MyOrderList {
	public Integer getOrderListCount(){
		String sql="select count(rowid) as rw from foodorder";
		List<Map> res=dbUtils.DbUtils.query(sql);
		Integer count=Integer.parseInt(res.get(0).get("RW").toString());
		return count;
	}
	public PageTools getOrder(Integer curPage){
		Integer totalCount=getOrderListCount();
		PageTools pt=new PageTools(curPage, null, totalCount);
		String sql="select * from (select g.*,d.dname,rownum rn from foodorder g inner join desk d on g.deskid=d.deskid ) where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex();
		List<Map> list=dbUtils.DbUtils.query(sql);
		pt.setData(list);
		return pt;
	}
	
	/**
	 * 查询详细订单
	 */
	public List getdetail(String orderid){
		String sql="select * from foodorderdetail where orderid="+orderid;
		List<Map> list=dbUtils.DbUtils.query(sql);
		return list;
	}
	
	/*
	 * 总价
	 */
	public Integer getZong(String orderId){
		
			String sql="select sum(gp) from (select gcount*price as gp from FOODORDERDETAIL t inner join food f on t.foodid=f.foodid where orderid="+orderId+") ";
			return dbUtils.DbUtils.execute(sql);
		
	}
	/**
	 * 结账 
	 */
	public void getJie(String orderId){
		String sql="update foodorder set orderstate=1 where orderid="+orderId;
		dbUtils.DbUtils.execute(sql);
	}
}
