package com.yc.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Favorite;
import com.yc.bean.Tag;
import com.yc.biz.FavoriteBiz;
import com.yc.biz.TagBiz;
import com.yc.biz.impl.FavoriteBizImpl;
import com.yc.biz.impl.TagBizImpl;

import net.sf.json.JSONArray;


public class FavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String op=request.getParameter("op");
		if(op.equals("toAdd")){
			addFavorite(request,response);
		}else if(op.equals("findType")){
			findType(request,response);
		}else if(op.startsWith("toList")){
			findFavoriteByType(request,response);
		}
	}
	private void findType(HttpServletRequest request, HttpServletResponse response) {
		TagBiz tagbiz=new TagBizImpl();
		List<Tag> tagList=tagbiz.findType();
		if(tagList!=null && tagList.size()>0){
			try {
				PrintWriter out=response.getWriter();
				JSONArray json=JSONArray.fromObject(tagList);
				out.print(json);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	private void findFavoriteByType(HttpServletRequest request, HttpServletResponse response) {
		FavoriteBiz favbiz=new FavoriteBizImpl();
		String type=request.getParameter("type");
		System.out.println(type);
		Favorite fav=new Favorite();
		if(type.equals("-1")){
			List<Favorite> list=favbiz.findFavoriteByType(fav);
			if(list!=null && list.size()>0){
				try {
					PrintWriter out=response.getWriter();
					JSONArray json=JSONArray.fromObject(list);
					out.print(json);
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			fav.setF_tags("%,"+type+",%");
			List<Favorite> list=favbiz.findFavoriteByType(fav);
			try {
				PrintWriter out=response.getWriter();
			if(list!=null && list.size()>0){
					JSONArray json=JSONArray.fromObject(list);
					out.print(json);
					out.flush();
					out.close();
				
			}else{
				JSONArray json=new JSONArray();
				out.print(json);
				out.flush();
				out.close();
			}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	private void addFavorite(HttpServletRequest request, HttpServletResponse response) {
			try {
				response.getWriter().print("<form action='fav.do' method='post'><input type='hidden' name='op' value='toAdd'/><table><tr><td>名称：<input type='text' name='f_label'></td></tr>"+
						"<tr><td>链接：<input type='text' name='f_url'></td></tr><tr><td>Tag:<input type='text' name='f_tags'><span>可以多个，用','隔开</span></td></tr>"+
						"<tr><td>描述:<textarea name='f_desc'></textarea></td></tr><tr><td><input type='button' value='取消' onclick='close()'><input type='submit' value='提交'/></td></tr></table></form>"+
						"<script type='text/javascript' src='script/jquery-1.11.3.js'></script><script>function close(){window.close()}</script>");
				String f_label=request.getParameter("f_label");
				String f_url=request.getParameter("f_url");
				String f_tags=request.getParameter("f_tags");
				String f_desc=request.getParameter("f_desc");
				FavoriteBiz favbiz=new FavoriteBizImpl();
				String [] param=null;
				if(f_tags!=null && !"".equals(f_tags)){
					param=f_tags.split(",");
					for(int i=0;i<param.length;i++){
						System.out.println(param[i]);
					}
				}
				Favorite fav=null;
				int result=0;
				if( (f_label!=null && !"".equals(f_label))  &&  (f_url!=null && !"".equals(f_url))    ){
					fav=new Favorite(f_label,f_url,","+f_tags+",",f_desc);
					Tag tag=new Tag();
					tag.setTname(param);
					result=favbiz.addFavorite(fav,tag);
					if(result>0){
						response.getWriter().print("<script>window.close();</script>");
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
