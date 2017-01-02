<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>
    <title>搜藏</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	<script type="text/javascript">
	function add(){
		window.open ('fav.do?op=toAdd', 'newwindow', 
		'height=300, width=400, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no');
	}
	</script>
  </head>
  <body>
    <form name="favForm" method="post" action="/cang/fav.do;jsessionid=FF8C0487A99814B144B07E5633524A0F">
      <input type="hidden" name="op" value="toList" />
    
    <div class="banner" valign="top">
    	 搜藏
    </div>
	<table cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td style="text-align:right;" valign="top">
			<!-- 左边Tag列表 -->
				<div class="left_labels" >
				<table class="labels_table" cellspacing="0" cellpadding="0" border="0">
					<tr><td><a href="#" onclick="add();" style="font-weight:bold;">添加书签</a></td></tr>
					<tr>
						<td class="selected_label">
							<a href="javascript:show()">全部</a>
						</td>
					</tr>
					
				</table>
				</div>
			</td>
			<td>
			<!-- 右边fav内容 -->
				<div class="content_links">
				
				</div>
			</td>
		</tr>
	</table> 
	
    </form>
    <script type="text/javascript" src="script/jquery-1.11.3.js"></script>
<script >
$(function(){
	showType();
	show();

});
function show(){
	$.post('fav.do',{op:'toList',type:'-1'},function(data){
		var str="";
		for(var i=0;i<data.length;i++){
			str+="<div style='padding:6px 10px;'><div><a href='"+data[i].f_url+"' style='color:blue;font-size:18px;' >"+data[i].f_label+"</a></div>"+
			"<div style='color:black;font-size:16px;'>"+data[i].f_desc+"</div><div style='color:green;font-size:14px;'>"+data[i].f_url+"</div></div>	";
		}
		$('.content_links').empty();
		$('.content_links').append(str);
	},'json');
}
function showType(){
	$.ajax({
		url:"fav.do",
		data:{"op":'findType'},
		dataType:'json',
		type:'post',
		traditional:true,
		success:function(data){
			console.info(data);
			
			if(data.length>0){
				var str="";
				for(var i=0;i<data.length;i++){
						str+="<tr><td><a href='#' onclick='showFavorite(this)'>"+data[i].t_name+"</a></td></tr>";
				}
				$('.labels_table').append(str);
				$('.labels_table').append("<tr><td><a style='font-weight:bold;' href='javascript:toCould()'>云图</a></td></tr>");
			}
		}
	});
}
function toCould(){
	$.post('fav.do',{op:'findType'},function(data){
		var str="";
		for(var i=0;i<data.length;i++){
			str+="<a href='#' onclick='showFavorite(this)' style='padding:6px 10px;color:blue;font-size:"+(15+data[i].t_count*2)+"px;' >"+data[i].t_name+"</a>";
		}
		$('.content_links').empty();
		$('.content_links').append(str);
	},'json');
}
function showFavorite(index){
	var param=index.innerHTML;
	$.ajax({
		url:"fav.do",
		data:{"op":'toList',"type":param},
		dataType:'json',
		type:'post',
		traditional:true,
		success:function(data){
			$('.content_links').html("");
			if(data.length>0){
				var str="";
				for(var i=0;i<data.length;i++){
					str+="<div style='padding:6px 10px;'><div><a href='"+data[i].f_url+"' style='color:blue;font-size:18px;' >"+data[i].f_label+"</a></div>"+
					"<div style='color:black;font-size:16px;'>"+data[i].f_desc+"</div><div style='color:green;font-size:14px;'>"+data[i].f_url+"</div></div>	";
				}
				$('.content_links').append(str);
			}
		}
	});
}

</script>
  </body>
</html>