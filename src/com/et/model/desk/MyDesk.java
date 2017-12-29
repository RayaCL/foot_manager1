package com.et.model.desk;

import java.util.List;
import java.util.Map;

import com.et.model.PageTools;

public class MyDesk {
	public Integer getTableListCount(String name){
		if(name==null){
			name="";
		}
		String sql="select count(rowid) as rw from desk where dname like'%"+name+"%'";
		List<Map> res=dbUtils.DbUtils.query(sql);
		Integer count=Integer.parseInt(res.get(0).get("RW").toString());
		return count;
	}
	public PageTools getDesk(String name,Integer curPage){
		if(name==null){
			name="";
		}
		Integer totalCount=getTableListCount(name);
		PageTools pt=new PageTools(curPage, null, totalCount);
		List<Map> list=dbUtils.DbUtils.query("select * from (select g.*,rownum rn from desk g where dname like '%"+name+"%') where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex());
		pt.setData(list);
		return pt;
	}
	/**
	 * 添加餐桌的方法
	 */
	public void saveDesk(String deskName){
		String sql="insert into desk values((select max(deskid)+1 from desk),'"+deskName+"',0,'')";
		dbUtils.DbUtils.execute(sql);
	}
	/**
	 * 删除桌的方法
	 */
	public void deleteDesk(String deskId){
		String sql="delete from desk where deskid="+deskId;
		dbUtils.DbUtils.execute(sql);
	}
	/**
	 * 退桌方法
	 */
	public void tuiDesk(String deskId,String datate){
		String sql;
		if(datate.equals("1")){
			sql="update desk set dstate=0 where deskid="+deskId;
		}else{
			sql="update desk set dstate=1 where deskid="+deskId;
		}
		
		dbUtils.DbUtils.execute(sql);
	}
}
