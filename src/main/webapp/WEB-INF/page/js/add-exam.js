$(document).ready(function() {
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
					window.location="edit_exam.html?examId="+data.retId;
				} else {
					alert(data.info);
				}
			},
		});
	});
});

