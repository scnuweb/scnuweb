$(document).ready(function() {
	showCandidateList();
	showExamItemList();
	$("#chooseAll").click(function() {
		 checkAll();
	 }); 
	$("#chooseAll-exam-item").click(function() {
		checkAllExamItem();
	});
	$(".required").blur(function () {
		var item = $.trim($(this).val());
		if(item==null||item=='') {
			$(this).attr("placeholder","此项不能为空");
			$(this).parent().parent().addClass("has-error");
			$("#edit-exam-btn").attr("disabled","disabled");
		} else {
			$(this).parent().parent().removeClass("has-error");
			$(this).attr("placeholder","");
			$("#edit-exam-btn").removeAttr("disabled");
		}
	});
	$("#edit-exam-btn").click(function() {
		var _exam_name = $("#exam-name").val();
		var _start_time = $("#start-time").val();
		var _end_time = $("#end-time").val();
		var _exam_id = $("#exam-id").val();
		$.ajax({
			dataType:"json",
			url:'edit_exam_submit.do',
			data:{examName:_exam_name,startTime:_start_time,endTime:_end_time,examId:_exam_id},
			success:function(data) {
				alert(data.info);
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
		} else alert("请选择需要导入的学生");
	});
	$("#import-exam-item-btn").click(function() {
		var import_list = "-1";
		var examId = $("#exam-id").val();
		var cnt = 0;
		$(".checkbox-exam-item:checked").each(function() {
			  import_list+=','+$(this).attr("exam-item-id");
			  cnt++;
		});
		if(cnt>0) {
			$.ajax({
				dataType:"text",
				url:'import_exam_item_list.do',
				data:{examId:examId,examItemList:import_list},
				success:function(data) {
					if(data=="true") {
						showExamItemList();
					} else alert("选择失败");
				},
			});
		} else alert("请选择试题");
	});
});
function checkAll() {
	  if($("#chooseAll").get(0).checked) {
		  $(".select-checkbox").prop("checked",'true');
	  } else $(".select-checkbox").removeAttr("checked");
}
function checkAllExamItem() {
	if($("#chooseAll-exam-item").get(0).checked) {
		  $(".checkbox-exam-item").prop("checked",'true');
	  } else $(".checkbox-exam-item").removeAttr("checked");
}
function showExamItemList() {
	$("#chooseAll-exam-item").removeAttr("checked");
	var exam_exam_item_arr;
	var exam_id = $("#exam-id").val();
	$.ajax({
		dataType:"json",
		url:'get_exam_exam_item_list.do',
		data:{examId:exam_id},
		success:function(data) {
			exam_exam_item_arr = data;
		},
	});
	$.ajax({
		dataType:"json",
		url:'get_exam_item_list.do',
		data:{},
		success:function(data) {
			$(".exam-item-table-row").remove();
			$.each(data,function(i,obj) {
				var row = '<tr class="exam-item-table-row">'
							+'<td>'+obj.examItemName+'</td>'
								+'<td>'+obj.generateTime+'</td>'
								+'<td>';
								if($.inArray(obj.id,exam_exam_item_arr)<0)row+='<input type="checkbox" class="checkbox-exam-item" exam-item-id="'+obj.id+'"/>';
								else row+='已选择<button class="btn btn-danger" onClick="deleteExamItemFromExam('+obj.id+')">移除</button>';
									+'</td>';
						   row+='</tr>';
				$("#exam-item-table").append(row);
			});
		},
	});
}
function showCandidateList() {
	$("#chooseAll").removeAttr("checked");
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
function deleteExamItemFromExam(examItemId) {
	var exam_id = $("#exam-id").val();
	$.ajax({
		dataType:"text",
		url:'delete_exam_item_from_exam.do',
		data:{examId:exam_id,examItemId:examItemId},
		success:function(data) {
			if(data=="true")showExamItemList();
			else alert("移除失败");
		}
	});
}