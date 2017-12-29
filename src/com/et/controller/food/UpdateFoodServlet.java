package com.et.controller.food;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.et.model.food.MyFood;

/**
 * Servlet implementation class UpdateFoodServlet
 */
public class UpdateFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    MyFood mf=new MyFood();
    static String SAVE_DIR="F:\\java\\foot_manager\\WebContent\\";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		ServletFileUpload.isMultipartContent(request);
		//���������ļ��ϴ��Ĺ�����
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//����һ���µ��ļ��ϴ��������
		ServletFileUpload upload=new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		//��������,��ȡ�ļ���
		List fileItems;
		try {
			fileItems=upload.parseRequest(request);
			Iterator i=fileItems.iterator();
			String foodid=null;
			String typeid=null;
			String foodname=null;
			String price=null;
			String introduce=null;
			String imageurl=null;
			String image=null;
			ServletContext sc=this.getServletContext();
			//���·����ȡ����·��
			String absPath=sc.getRealPath("/images/");
			//���·��
			String spath="/";
			
			while(i.hasNext()){
				FileItem fi=(FileItem)i.next();
				if(fi.isFormField()){
					if(fi.getFieldName().equals("foodid")){
						foodid=fi.getString("UTF-8");
					}else if(fi.getFieldName().equals("typeid")){
						typeid=fi.getString("UTF-8");
					}else if(fi.getFieldName().equals("foodName")){
						foodname=fi.getString("UTF-8");
					}else if(fi.getFieldName().equals("price")){
						price=fi.getString("UTF-8");
					}else if(fi.getFieldName().equals("introduce")){
						introduce=fi.getString("UTF-8");
					}else if(fi.getFieldName().equals("image")){
						image=fi.getString("UTF-8");
					}
				}else{
					InputStream is=fi.getInputStream();
					String name=fi.getName();
					if(name==null || name.equals("")){
						spath=image;
						break;
					}
					System.out.println(name+"-----name");
					String destPath=absPath+name;
					spath=spath+name;
					FileOutputStream fis=new FileOutputStream(destPath);
					byte[] b=new byte[1024];
					int n=-1;
					while((n=is.read(b))!=-1){
						fis.write(b,0,n);
					}
					fis.close();
					is.close();
				}
				
			}
			mf.updateFood(foodid, typeid, foodname, price, introduce,spath);
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		/*DiskFileItemFactory factory=new DiskFileItemFactory();
		//�������н������ļ�
		ServletFileUpload upload=new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");

		try {
			//�������ļ��ĸ���
			List<FileItem> items=upload.parseRequest(request);
			String typeid = items.get(0).getString("utf-8");
			String foodid = items.get(1).getString("utf-8");
			String foodname = items.get(2).getString("utf-8");
			String price = items.get(3).getString("utf-8");
			String introduce = items.get(4).getString("utf-8");
			String image = items.get(5).getString("utf-8");
			String imageurl = items.get(6).getName();
			String path = "/images/"+imageurl;
			if(imageurl==null || imageurl.equals("")){
				path = image;
			}
			File file = new File(SAVE_DIR+path);
			FileOutputStream fis=new FileOutputStream(file);
			InputStream is=items.get(6).getInputStream();
			byte[] bt=new byte[1024];
			int readN=-1;
			while((readN=is.read(bt))!=-1){
				fis.write(bt,0,readN);
			}
			is.close();
			fis.close();
			mf.updateFood(foodid, typeid, foodname, price,introduce,path);
			request.getRequestDispatcher("FoodServlet").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}*/
		
		/*String foodid=request.getParameter("foodid");
		String typeid=request.getParameter("cid");
		String foodname=request.getParameter("foodName");
		String price=request.getParameter("price");
		String introduce=request.getParameter("introduce");
		mf.updateFood(foodid, typeid, foodname, price,introduce);
		request.getRequestDispatcher("FoodServlet").forward(request, response);*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
