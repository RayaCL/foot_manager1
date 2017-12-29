package com.et.model.foodType;

import java.util.List;
import java.util.Map;

import com.et.model.PageTools;

public class MyFoodType {
	/**
	 * 菜系条数
	 * @param typename
	 * @return
	 */
	public Integer getFoodTypeCount(String typename){
		if(typename==null){
			typename="";
		}
		String sql="select count(rowid) as rw from foodtype where typename like'%"+typename+"%'";
		List<Map> res=dbUtils.DbUtils.query(sql);
		Integer count=Integer.parseInt(res.get(0).get("RW").toString());
		return count;
	}
	/**
	 *  查看菜系
	 * @param typename
	 * @param curPage
	 * @return
	 */
	public PageTools getFoodType(String typename,Integer curPage){
		if(typename==null){
			typename="";
		}
		Integer totalCount=getFoodTypeCount(typename);
		PageTools pt=new PageTools(curPage, null, totalCount);
		List<Map> list=dbUtils.DbUtils.query("select * from (select g.*,rownum rn from foodtype g where typename like '%"+typename+"%') where rn>="+pt.getStartIndex()+" and rn<="+pt.getEndIndex());
		pt.setData(list);
		return pt;
	}
	/**
	 * 添加餐桌的方法
	 */
	public void saveFoodType(String deskName){
		String sql="insert into foodtype values((select nvl(max(typeid),0)+1 from foodtype),'"+deskName+"')";
		dbUtils.DbUtils.execute(sql);
	}
	/**
	 * 删除菜系
	 */
	public void deleteFoodType(String typeId){
		String sql="delete from foodtype where typeid="+typeId;
		dbUtils.DbUtils.execute(sql);
	}
	/**
	 * 更新
	 * 修改菜系
	 */
	public void updateFoodType(String typeId,String typeName){
		String sql="update foodtype set typename='"+typeName+"' where typeid="+typeId;
		dbUtils.DbUtils.execute(sql);
	}
}
