
$("#logo").click(function(){return false;});
//展示导航栏第一项
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
$("#s1").click(function(){
  window.open("index.html","_blank");
});

//倒计时
function countdown ()
{
var maxTime=60*60;
  if(maxTime>=0)
  {
     var minute=Math.floor(maxTime/60);
     var second=Math.floor(maxTime%60);
     var msg="距离考试结束还有："+minute+"分钟"+second+"秒";
     var temp=document.getElementById("timer").innerHTML=msg;

     if(maxTime==5*60)
     {
        alert("还有5分钟！");
     }
     maxTime--;
  }
  else
  {
    clearInterval(timer);
    alert("考试结束！");
  }
}
timer = setInterval("countdown()",1000); 


 
function hide(type)
   {
    
     var temp=document.getElementById("needH");
      if(type=="needH")
      {
        temp.style.display='block';
      }
      else
      {
        temp.style.display='none';
      }
   }

   function openDia(address,name)
    {
       var diag = new Dialog();
       diag.Width = 900;
       diag.Height = 400;
       diag.Title = name;
       diag.URL = address;
       diag.show();
    }
    function getExplainAddress(temp)
   {
    var t=document.getElementById("cho");
    t.name=temp;
   }
     function openDiaPa(address,name)
    {
       var diag = new Dialog();
       diag.Width = 900;
       diag.Height = 400;
       diag.Title = name;
       diag.URL = address+document.getElementById("cho").name;
       diag.show();
    }
  
  function onclickEnter1(text1,ch1,ch2,ch3,ch4)
  {

    var t1=document.getElementById(ch1);
    var t2=document.getElementById(ch2);
    var t3=document.getElementById(ch3);
    var t4=document.getElementById(ch4);
    if(text1=="part1")
    {
      var c1=document.getElementById("need1");
      t1.value=c1.value;
      c1=document.getElementById("question1");
      t2.value=c1.value;
      c1=document.getElementById("selectcontent");
      t3.value=c1.value;
      c1=document.getElementById("question2");
      t4.value=c1.value;
    }
    if(text1=="part2")
    {
       var c1=document.getElementById("need1");
      t1.value=c1.value;
      c1=document.getElementById("question1");
      t2.value = c1.value;
      c1=document.getElementsByName("select1");
      t3.value=c1[0].value;
      c1=document.getElementById("question2");
      t4.value=c1.value;
    }
    
  }

function onMouseout(need1,need2)
{
  var temp=document.getElementById(need1);
  var chg=document.getElementsByName(need2);
  chg[0].value =temp.value;
  chg[1].value=temp.value;
  chg[2].value=temp.value;

}


