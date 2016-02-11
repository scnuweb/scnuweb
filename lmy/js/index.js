var first=document.getElementById('first');
var open=document.getElementById('open');
first.onclick=function(){
  if(open.style.display=="none"||open.style.display=="")
  {
    open.style.display="block"
  }
  else
  {
    open.style.display=""
  }
 return false;
}



//Tab标签作用函数
  function addTab(title, url){
  if ($('#tt').tabs('exists', title)){
    $('#tt').tabs('select', title);
  } else {
    var content = '<iframe class="f" frameborder="0"  scrolling="yes" src="'+url+'" style="padding:10px;width:100%;height:3000px;"></iframe>';
    $('#tt').tabs('add',{
      title:title,
      content:content,
      closable:true
    });
  }
}


//左侧菜单栏功能
var bt1=document.getElementById('bt1');
bt1.onclick=function(){

    if(document.getElementById('leftitem').style.display==""||document.getElementById('leftitem').style.display=="none")
    {
    document.getElementById('leftitem').style.display="block";
    document.getElementById('l1').className="glyphicon glyphicon-collapse-down";
    }
    else{
    document.getElementById('leftitem').style.display="none";
    document.getElementById('l1').className="glyphicon glyphicon-expand";
    }
}

$("#s1").click(function(){
  addTab('自然人变更登记','frame/ziranrenbiangeng.html');
  return false;
});
$("#s2").click(function(){
  addTab('自然人登记','frame/ziranrendengji.html');
  return false;
});
$("#s3").click(function(){
  addTab('自然人多证同用登记','frame/ziranrenduozheng.html');
  return false;
});