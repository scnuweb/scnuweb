$(document).ready(function() {
	$("#chooseAll").click(function() {
		 checkAll();
	 }); 
	$(".required").blur(function () {
		var item = $.trim($(this).val());
		if(item==null||item=='') {
			$(this).attr("placeholder","此项不能为空");
			$(this).parent().parent().addClass("has-error");
			$("#add-exam-btn").attr("disabled","disabled");
		} else {
			$(this).parent().parent().removeClass("has-error");
			$(this).attr("placeholder","");
			$("#add-exam-btn").removeAttr("disabled");
		}
	});
	$("#add-exam-btn").click(function() {
		var _exam_name = $("#exam-name").val();
		var _start_time = $("#start-time").val();
		var _end_time = $("#end-time").val();
		$.ajax({
			dataType:"json",
			url:'add_exam_submit.do',
			data:{examName:_exam_name,startTime:_start_time,endTime:_end_time},
			success:function(data) {
				if(data.status) {
					alert(data.info);
					$("#choose-candidate").fadeIn();
					$("#choose-exam-item").fadeIn();
					$("#exam-id").val(data.retId);
					showCandidateList();
				} else {
					alert(data.info);
				}
			},
		});
	});
	$("#search-btn").click(function() {
		showCandidateList();
	});
	$("#import-candidate-btn").click(function() {
		var import_list = "-1";
		var examId = $("#exam-id").val();
		var cnt = 0;
		$(".select-checkbox:checked").each(function() {
			  import_list+=','+$(this).attr("candidate-id");
			  cnt++;
		});
		if(cnt>0) {
			$.ajax({
				dataType:"text",
				url:'import_candidate_list.do',
				data:{examId:examId,candidateList:import_list},
				success:function(data) {
					if(data=="true") {
						showCandidateList();
					} else alert("导入失败");
				},
			});
		}
	});
});
function checkAll() {
	  if($("#chooseAll").get(0).checked) {
		  $(".select-checkbox").prop("checked",'true');
	  } else $(".select-checkbox").removeAttr("checked");
}
function showCandidateList() {
	var _no = $("#search-no").val();
	var _username = $("#search-username").val();
	var _truename = $("#search-truename").val();
	var _unit = $("#search-unit").val();
	var exam_id = $("#exam-id").val();
	var exam_candidate_arr;
	$.ajax({
		dataType:"json",
		url:'get_exam_candidate_list.do',
		data:{examId:exam_id},
		success:function(data) {
			exam_candidate_arr = data;
		},
	});
	$.ajax({
		dataType:"json",
		url:'get_candidate_list.do',
		data:{no:_no,username:_username,trueName:_truename,unit:_unit},
		success:function(data) {
			$(".candidate-table-row").remove();
			$.each(data,function(i,obj) {
				var row = '<tr class="candidate-table-row">'
						 +'<td class="ini_t" >'+obj.no+'</td>'
						 +'<td class="ini_t" >'+obj.username+'</td>'
						 +'<td class="ini_t" >'+obj.trueName+'</td>'
						 +'<td class="ini_t" >'+obj.unit+'</td>'
						 +'<td class="ini_t" >'+obj.position+'</td>';
				if($.inArray(obj.id,exam_candidate_arr)<0)
					row+='<td class="ini_t" ><input type="checkbox" class="select-checkbox" candidate-id="'+obj.id+'" /></td>'
						+'</tr>';
				else row+='<td class="ini_t" >已导入<button class="btn btn-danger" onClick="deleteFromExam('+obj.id+');">移除</button></td></tr>';
				$("#candidate-table").append(row);
			});
		},
	});
}
function deleteFromExam(candidateId) {
	var exam_id = $("#exam-id").val();
	$.ajax({
		dataType:"text",
		url:'delete_candidate_from_exam.do',
		data:{examId:exam_id,candidateId:candidateId},
		success:function(data) {
			if(data=="true")showCandidateList();
			else alert("移除失败");
		}
	});
}