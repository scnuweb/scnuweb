function exam_submit(exam_id,exam_item_id) {
	$.ajax({
		dataType:"text",
		type:"POST",
		url:'../exam/exam_item_submit.do',
		data:{examId:exam_id,examItemId:exam_item_id},
		success:function(data) {
			// do nothing;
			if(data=='true')alert("提交成功");
			else alert("该题目已提交");
			window.location="join_exam.html?examId="+exam_id;
		}
	});
}