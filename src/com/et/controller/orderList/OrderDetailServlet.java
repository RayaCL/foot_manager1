package com.et.controller.orderList;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.et.model.PageTools;
import com.et.model.orderList.MyOrderDetail;

/**
 * Servlet implementation class OrderDetailServlet
 */
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    MyOrderDetail mo=new MyOrderDetail();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String curPage=request.getParameter("curPage");
		String orderId=request.getParameter("orderId");
		Integer curPageInt=1;
		if(curPage!=null){
			curPageInt=Integer.valueOf(curPage);
			
		}
		PageTools orderList=mo.getOrderDetail(curPageInt,orderId);
		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher("/detail/orderDetail.jsp?orderId="+orderId).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
