function logout() {

	var statu = ""; 
    $.ajax({
                type:"post",
                url:"userFunction/RemoveSessionServlet",
                dataType:"text/xml",
                async:false,               
 success:function(data) {
    	if(data=="LogoutSucess"){
    		
        	ShowAlertWindowFromDiv("AlertWindowContent","注销成功");
        	
        	//把头像换回原来的
        	var UserHeadNode = $(".UserHead");
        	
        	UserHeadNode.attr("style","background-image:url('picture/Index/TopNav/UserHead.png')");
    		
        	
        	
        	
        	
    		//如果注销成功，就显示登录按钮
        	var LoginAndLogoutNode = document.getElementById("LoginAndLogout");
        	LoginAndLogoutNode.setAttribute("onclick", "ShowWindowByClass('LoginWindow')")    		

        	LoginAndLogoutNode.innerHTML="登陆";
        	
        	


    			
    	}
             
                
 }
            }
    )


}
