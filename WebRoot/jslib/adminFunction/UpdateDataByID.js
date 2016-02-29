
//只要修改url就可以用了
function UpdateDataByID(id,e,url) {
    var td = $(e);
        //1.取出当前td中的文本内容保存起来
    var OriginalText = td.text();
        //2.清空td里面的内容
    td.html("");  
    var input = $("<input>");
    input.attr("value", OriginalText);
    input.css("width","500px").css("height","100px");
        //4.5让文本框可以响应键盘按下并弹起的事件，主要用于处理回车确认
    td.append(input); 

    //把文本框的内容全选
    var inputdom = input.get(0);
    inputdom.select();
    td.removeAttr("onclick");
    
    //失去焦点的时候不要修改值
    input.blur(function() { 
    	var InputNode = $(input);
    	InputNode.remove();
    	td.html(OriginalText);
    	//再设置点击事件
    	var str = "'"+url+"'";
    	if(id==""){
            e.setAttribute("onclick","UpdateDataByID(''"+",this," +str +")");

    	}else{
        e.setAttribute("onclick","UpdateDataByID("+id+",this," +str +")");
    	}
    });
    
    
    //回车的时候修改数据
    input.keydown(function(event) {
        var myEvent = event || window.event;
        var kcode = myEvent.keyCode;
        if (kcode == 13) {
        	var InputNode = $(input);
        	var NowText = InputNode.val();
        	InputNode.remove();
        	
        	//在这里吧数据传到后台数据库
        	//获取修改的属性名
        	var AttrName = td.attr("id");
        	UpdateDataToDB(AttrName,NowText,id,url);
        	td.html(NowText);
            
            //再设置点击事件
        	var str = "'"+url+"'";
        	if(id==""){
                e.setAttribute("onclick","UpdateDataByID(''"+",this," +str +")");

        		
        	}
            e.setAttribute("onclick","UpdateDataByID("+id+",this," +str +")");

        }
    });
    



}


function UpdateDataToDB(AttrName,NowText,id,url) {

	
		var statu = ""; 
	    $.ajax({type:"post",
	                url:"adminFunction/"+url+"?AttrName="+AttrName+"&NowText="+NowText+"&ID="+id,
	                dataType:"text/xml",
	                async:false,               
	 success:function(data) {
	    	if(data=="UpdateSucess"){
	    		
	    		alert("修改成功");
	

	    			
	    	}else if(data=="UpdateFailure"){
	    		alert("修改失败");

	    	}
	             
	                
	 }
	            }
	    )


	}

