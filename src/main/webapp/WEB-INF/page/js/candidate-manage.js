$(document).ready(function() {
	 $("#chooseAll").click(function() {
		 checkAll();
	 }); 
	 $("#delete-list").click(function() {
		deleteList(); 
	 });
 });
  function openDia(address,name) {
       var diag = new Dialog();
       diag.Width = 900;
       diag.Height = 400;
       diag.Title = name;
       diag.URL = address;
       diag.show();
    }
  function checkAll() {
	  if($("#chooseAll").get(0).checked) {
		  $(".select-checkbox").prop("checked",'true');
	  } else $(".select-checkbox").removeAttr("checked");
  }
  function deleteList() {
	  var delete_list = "-1";
	  var cnt = 0;
	  $(".select-checkbox:checked").each(function() {
		  delete_list+=','+$(this).attr("candidate-id");
		  cnt++;
	  });
	  if(cnt<1) {
		  alert("请先勾选要删除的考生");
		  return;
	  }
	  if(confirm("确定要删除这"+cnt+"个考生？")) {
		  $.ajax({
				dataType:"text",
				url:'delete_candidates.do',
				data:{deleteList:delete_list},
				success:function(data) {
					if(data=='true') {
						alert("删除成功");
						window.location.reload();
					} else {
						alert("删除失败");
					}
				},
			});
	  }
  }