$(document).ready(function() {
			$("input").attr("maxlength","16");
			$(".required").blur(function () {
				var item = $.trim($(this).val());
				if(item==null||item=='') {
					$(this).next().html("此项不能为空");
					$(this).parent().parent().addClass("has-error");
					$(this).next().fadeIn();
					$("#edit-candidate-btn").attr("disabled","disabled");
				} else {
					$(this).parent().parent().removeClass("has-error");
					$(this).next().hide();
					$("#edit-candidate-btn").removeAttr("disabled");
				}
			});
			$("#username").blur(function (){
				var _username = $.trim($(this).val());
				if(_username==null||_username=='')return;
				if(_username.length<4) {
					$(this).next().html("用户名长度应为4~16位");
					$(this).parent().parent().addClass("has-error");
					$(this).next().fadeIn();
					$("#edit-candidate-btn").attr("disabled","disabled");
					return;
				} else {
					$(this).parent().parent().removeClass("has-error");
					$(this).next().hide();
					$("#edit-candidate-btn").removeAttr("disabled");
				}
				$.ajax({
					dataType:"text",
					url:'checkIsUsernameValid.do',
					data:{username:_username},
					success:function(data) {
						if(data=='false') {
							$("#username").parent().parent().addClass("has-error");
							$("#username").next().html("此用户名已存在").fadeIn();
							$("#edit-candidate-btn").attr("disabled","disabled");
						} else {
							$("#username").parent().parent().removeClass("has-error");
							$("#username").next().hide();
							$("#edit-candidate-btn").removeAttr("disabled");
						}
					},
				});
			});
			$("#password").blur(function() {
				var _password = $.trim($(this).val());
				if(_password==null||_password=='')return;
				if(_password.length<4) {
					$("#password").parent().parent().addClass("has-error");
					$("#password").next().html("密码长度应为4~16位").fadeIn();
					$("#edit-candidate-btn").attr("disabled","disabled");
				} else {
					$("#password").parent().parent().removeClass("has-error");
					$("#password").next().hide();
					$("#edit-candidate-btn").removeAttr("disabled");
				}
			});
			$("#edit-candidate-btn").click(function() {
				$(".required").each(function() {
					if($(this).val()==null||$.trim($(this).val()).length==0) {
						alert("请完成必填项!");
						$("#edit-candidate-btn").attr("disabled","disabled");
					}
				});
				return true;
			});
		});