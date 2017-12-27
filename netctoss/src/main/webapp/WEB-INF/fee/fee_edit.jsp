<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
        <script src="js/jquery-1.11.1.js"></script>
        <script language="javascript" type="text/javascript">
        $(function(){
      	  if(${isExit }){
      		  window.alert("资费名称已存在，请重新输入");
      	  }
     		 });
        //切换资费类型之后判断
        function feeTypeChange(type) {
            //var inputArray = document.getElementById("main").getElementsByTagName("input");
            if (type == 1) {
                $("#input_baseDuration").readOnly = true;
                $("#input_baseDuration").value = "";
                $("#input_baseDuration").className += " readonly";
                $("#input_baseCost").readOnly = false;
                $("#input_baseCost").className = "width100";
                $("#input_unitCost").readOnly = true;
                $("#input_unitCost").className += " readonly";
                $("#input_unitCost").value = "";
            }
            else if (type == 2) {
            	 $("#input_baseDuration").readOnly = false;
            	 $("#input_baseDuration").className = "width100";
            	 $("#input_baseCost").readOnly = false;
            	 $("#input_baseCost").className = "width100";
            	 $("#input_unitCost").readOnly = false;
            	 $("#input_unitCost").className = "width100";
            }
            else if (type == 3) {
            	$("#input_baseDuration").readOnly = true;
                $("#input_baseDuration").value = "";
                $("#input_baseDuration").className += " readonly";
                $("#input_baseCost").readOnly = true;
                $("#input_baseCost").value = "";
                $("#input_baseCost").className += " readonly";
                $("#input_unitCost").readOnly = false;
                $("#input_unitCost").className = "width100";
            };
        }
        </script>
    </head>
    <!--页面加载时先调用多选按钮选中的事件  -->
    <body onload="feeTypeChange(${cost.costType});">
        <!--Logo区域开始-->
        <div id="header">
             <%@include file="../logo.jsp"%>             
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
            <form action="feeEdit.costmain" method="post" class="main_form">
                <div class="text_info clearfix"><span>资费ID：</span></div>
                <div class="input_info"><input type="text" class="readonly" readonly name="costID" value="${cost.costID }" /></div>
                
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info">
                    <input type="text" class="width300" name="name" value="${cost.name }"/>
                    <span class="required">*</span>
                    <div class="validate_msg_short">50长度的字母、数字、汉字和下划线的组合</div>
                </div>
                
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info fee_type">
                    <input type="radio" name="costType" value="1" 
                    <c:if test="${cost.costType==1 }">checked="checked"</c:if>
                    id="monthly" onclick="feeTypeChange(1);" />
                    <label for="monthly">包月</label>
                    <input type="radio" name="costType" value="2" 
                    <c:if test="${cost.costType==2 }">checked="checked"</c:if> 
                    id="package" onclick="feeTypeChange(2);" />
                    <label for="package">套餐</label>
                    <input type="radio" name="costType" value="3" 
                    <c:if test="${cost.costType==3 }">checked="checked"</c:if>
                    id="timeBased" onclick="feeTypeChange(3);" />
                    <label for="timeBased">计时</label>
                </div>
                
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <input type="text" id="input_baseDuration" name="baseDuration" value="${cost.baseDuraction }" class="width100" />
                    <span class="info">小时</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long">1-600之间的整数</div>
                </div>
                
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <input type="text" id="input_baseCost" name="baseCost" value="${cost.baseCost }" class="width100" />
                    <span class="info">元</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long">0-99999.99之间的数值</div>
                </div>
                
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                    <input type="text" id="input_unitCost" name="unitCost" value="${cost.unitCost }" class="width100" />
                    <span class="info">元/小时</span>
                    <span class="required">*</span>
                    <div class="validate_msg_long">0-99999.99之间的数值</div>
                </div>   
                
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <textarea class="width300 height70" name="descr">${cost.descr }</textarea>
                    <div class="validate_msg_short">100长度的字母、数字、汉字和下划线的组合</div>
                </div>   
                                 
                <div class="button_info clearfix">
                    <input type="submit" value="保存" class="btn_save" />
                    <input type="button" value="取消" class="btn_save" onclick="location.href='find.costmain';"/>
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[熟悉MVC模式]</span>
        </div>
    </body>
</html>