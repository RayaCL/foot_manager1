package com.et.model.orderList;

import java.util.List;
import java.util.Map;

import com.et.model.PageTools;

public class MyOrderDetail {
	
	public Integer getOrderListCount(String orderId){
		String sql="select count(rowid) as rw from FOODORDERDETAIL where orderId = "+orderId;
		List<Map> res=dbUtils.DbUtils.query(sql);
		Integer count=Integer.parseInt(res.get(0).get("RW").toString());
		return count;
	}
	public PageTools getOrderDetail(Integer curPage,String orderId){
		Integer totalCount=getOrderListCount(orderId);
		PageTools pt=new PageTools(curPage, null, totalCount);
		String sql="select * from (select t.*,f.price,f.foodname, rownum rn from FOODORDERDETAIL t inner join food f on t.foodid=f.foodid) where orderid="+orderId+" and rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex();
		
		List<Map> list=dbUtils.DbUtils.query(sql);
		pt.setData(list);
		return pt;
	}
}
