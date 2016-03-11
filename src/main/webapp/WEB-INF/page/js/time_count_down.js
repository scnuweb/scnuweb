function count_to_when(leftTime) {
	var leftsecond = parseInt(leftTime/1000); 
	var day=Math.floor(leftsecond/(60*60*24)); 
	var hour=Math.floor((leftsecond-day*24*60*60)/3600); 
	var minute=Math.floor((leftsecond-day*24*60*60-hour*3600)/60); 
	var second=Math.floor(leftsecond-day*24*60*60-hour*3600-minute*60); 
	var show_time = "";
	if(day>0)show_time+=day+"天";
	show_time+=hour+"小时";
	show_time+=minute+"分钟";
	show_time+=second+"秒";
	$("#show-time-left").text(show_time);
}
function submitAnswer(exam_id,exam_item_id) {
	$.ajax({
		dataType:"text",
		type:"POST",
		url:'../exam/exam_item_submit.do',
		data:{examId:exam_id,examItemId:exam_item_id},
		success:function(data) {
			// do nothing;
		}
	});
}