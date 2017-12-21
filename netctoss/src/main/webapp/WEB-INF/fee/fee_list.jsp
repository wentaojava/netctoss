<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>查询资费界面</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
        <script src="js/jquery-1.11.1.js"></script>
        
        <script language="javascript" type="text/javascript">
        function editButton(status,costID){
        	//var status=$(btn).parent().prev().text();
        	//console.log(status);
        	//console.log(costID);
        	if(status==1){
        		//console.log("该资费已开通，不可修改");
        			window.alert("该资费已开通，不可修改");
        	}else{
        		//console.log("该资费已暂停");
        		var r = window.confirm("确定要修改此资费吗？");
        		if(r){
        			location.href='toFeeEdit.costmain?costID='+costID;
        		}
        	} 
        }
            //排序按钮的点击事件
            function sort(btnObj) {
                if (btnObj.className == "sort_desc")
                    btnObj.className = "sort_asc";
                else
                    btnObj.className = "sort_desc";
            }

            //启用
            function startFee() {
                var r = window.confirm("确定要启用此资费吗？资费启用后将不能修改和删除。");
                if(r){
                document.getElementById("operate_result_info").style.display = "block";
            }}
            //删除
            function deleteFee(status,costID) {
            	if(status==1){
            		//console.log("该资费已开通，不可修改");
            			window.alert("该资费已开通，不可删除");
            		}else{
            		//console.log("该资费已暂停");
            			var r = window.confirm("确定要删除此资费吗？");
            			if(r){
            			location.href='FeeDelete.costmain?costID='+costID;
            		}
            	} 
            }
        </script>        
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="images/logo.png" alt="logo" class="left"/>
            <a href="#">[退出]</a>            
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            <ul id="menu">
                <li><a href="../index.html" class="index_off"></a></li>
                <li><a href="../role/role_list.html" class="role_off"></a></li>
                <li><a href="../admin/admin_list.html" class="admin_off"></a></li>
                <li><a href="../fee/fee_list.html" class="fee_off"></a></li>
                <li><a href="../account/account_list.html" class="account_off"></a></li>
                <li><a href="../service/service_list.html" class="service_off"></a></li>
                <li><a href="../bill/bill_list.html" class="bill_off"></a></li>
                <li><a href="../report/report_list.html" class="report_off"></a></li>
                <li><a href="../user/user_info.html" class="information_off"></a></li>
                <li><a href="../user/user_modi_pwd.html" class="password_off"></a></li>
            </ul>            
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="">
                <!--排序-->
                <div class="search_add">
                    <div>
                        <!--<input type="button" value="月租" class="sort_asc" onclick="sort(this);" />
                        <input type="button" value="基费" class="sort_asc" onclick="sort(this);" />
                        <input type="button" value="时长" class="sort_asc" onclick="sort(this);" />-->
                    </div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toFeeAdd.costmain';" />
                </div> 
                <!--启用操作的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="images/close.png" onclick="this.parentNode.style.display='none';" />
                    启用成功！
                </div>    
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>资费ID</th>
                            <th class="width100">资费名称</th>
                            <th>基本时长</th>
                            <th>基本费用</th>
                            <th>单位费用</th>
                            <th>创建时间</th>
                            <th>开通时间</th>
                            <th class="width50">状态</th>
                            <th class="width200"></th>
                        </tr>  
                        <c:forEach items="${costsList }" var="c">                   
                        <tr>
                            <td>${c.costID }</td>
                            <td><a href="fee_detail.html">${c.name }</a></td>
                            <td>${c.baseDuraction }</td>
                            <td>${c.baseCost }</td>
                            <td>${c.unitCost }</td>
                            <td>${c.creatime }</td>
                            <td>${c.startime }</td>
                            <td>
                            	<c:if test="${c.status==0 }">暂停</c:if>
                            	<c:if test="${c.status==1 }">开通</c:if>
                            </td>
                            <td>                                
                                <input type="button" value="启用" class="btn_start" onclick="startFee();" />
                                <input type="button" value="修改" class="btn_modify" onclick="editButton(${c.status},${c.costID });" />
                                <input type="button" value="删除" class="btn_delete" onclick="deleteFee(${c.status},${c.costID });" />
                            </td>
                        </tr>
                        </c:forEach>                    
                    </table>
                    <p>业务说明：<br />
                    1、创建资费时，状态为暂停，记载创建时间；<br />
                    2、暂停状态下，可修改，可删除；<br />
                    3、开通后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br />
                    4、业务账号修改资费时，在下月底统一触发，修改其关联的资费ID（此触发动作由程序处理）
                    </p>
                </div>
                <!--分页-->
                <div id="pages">
        	        <a href="#">上一页</a>
                    <a href="#" class="current_page">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#">4</a>
                    <a href="#">5</a>
                    <a href="#">下一页</a>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[熟悉MVC模式]</p>
           
        </div>
    </body>
</html>
