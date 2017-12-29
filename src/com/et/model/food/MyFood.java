package com.et.model.food;

import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import com.et.model.PageTools;

public class MyFood {
	public Integer getFoodCount(String name){
		if(name==null){
			name="";
		}
		String sql="select count(rowid) as rw from food where foodname like '%"+name+"%'";
		List<Map> foodList =dbUtils.DbUtils.query(sql);
		Integer count=Integer.parseInt(foodList.get(0).get("RW").toString());
		return count;
	}
	public PageTools getFood(String name,Integer curPage){
		if(name==null){
			name="";
		}
		Integer totalCount=getFoodCount(name);
		PageTools pt=new PageTools(curPage, null, totalCount);
		String sql="select * from (select g.*,rownum rn from food g where foodname like '%"+name+"%') where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex();
		List<Map> list=dbUtils.DbUtils.query(sql);
		pt.setData(list);
		return pt;
	}
	/**
	 * ��ѯ��ϵ
	 */
	public List<Map> getFoodType(){
		String sql="select * from foodtype";
		List<Map> list=dbUtils.DbUtils.query(sql);
		return list;
	}
	/**
	 * ��Ӳ�Ʒ
	 */
	public void addFood(String typeid,String foodname, String price,String introduce){
		String sql="insert into food values((select nvl(max(foodid),0)+1 from food),'"+typeid+"','"+foodname+"','"+price+"','"+introduce+"')";
		dbUtils.DbUtils.execute(sql);
	}
	/**
	 * ɾ����Ʒ
	 * @param foodId
	 */
	public void deleteFood(String foodId){
		String sql="delete from food where foodid="+foodId;
		dbUtils.DbUtils.execute(sql);
	}
	/**
	 * ��ѯָ����Ʒ
	 */
	public List<Map> getFood(String foodId){
		String sql="select * from food where foodid="+foodId;
		List<Map> list=dbUtils.DbUtils.query(sql);
		return list;
	}
	/**
	 * �޸Ĳ�Ʒ
	 */
	public void updateFood(String foodid, String typeid,String foodname,String price,String introduce,String path){
		String sql="update food set typeid="+typeid+",foodname='"+foodname+"',price="+price+",desce='"+introduce+"',imageurl='"+path+"' where foodid="+foodid;
		dbUtils.DbUtils.execute(sql);
	}

	public void addFood(String typeid, String foodname, String price, String introduce, String imageurl) {
		String sql="insert into food values((select nvl(max(foodid)+1,1) from food),'"+typeid+"','"+foodname+"','"+price+"','"+introduce+"','"+imageurl+"')";
		dbUtils.DbUtils.execute(sql);
	}
	
}
