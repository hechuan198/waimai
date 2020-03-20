$(function() {

	$("#username").blur(
			function() {
				$("#username_msg").empty();
				var name = $(this).val();
				if (name == "" || name == null) {
					$("#username").after("<span id='username_msg' style='color: red'>用户名不能为空</span>");
				}
		});

	$("#password").blur(
			function() {
				$("#password_msg").empty();
				var name = $(this).val();
				if (name == "" || name == null) {
					$("#password").after("<span id='password_msg' style='color: red'>密码不能为空</span>");
				}
		});

    $("#rapassword").blur(
        function() {
            $("#rapassword_msg").empty();
            var name = $(this).val();
            var password = $("#password").val();
            var rapassword = $("#rapassword").val();
            if (name == "" || name == null) {
                $("#rapassword").after("<span id='rapassword_msg' style='color: red'>确认密码不能为空</span>");
            }else if (rapassword != password){
                $("#rapassword").after("<span id='rapassword_msg' style='color: red'>两次密码不相同</span>");
            }
        });


    $("#phone").blur(
		function() {
			$("#phone_msg").empty();
			var name = $(this).val();
			if (name == "" || name == null) {
				$("#phone").after("<span id='phone_msg' style='color: red'>手机号不能为空</span>");
			}
	});

$("#email").blur(
		function() {
			$("#birthday_msg").empty();
			var name = $(this).val();
			if (name == "" || name == null) {
				$("#email").after("<span id='birthday_msg' style='color: red'>邮箱不能为空</span>");
			}
	});

$("#question").blur(
		function() {
			$("#question_msg").empty();
			var name = $(this).val();
			if (name == "" || name == null) {
				$("#question").after("<span id='question_msg' style='color: red'>问题不能为空</span>");
			}
	});

$("#answer").blur(
		function() {
			$("#answer_msg").empty();
			var name = $(this).val();
			if (name == "" || name == null) {
				$("#answer").after("<span id='answer_msg' style='color: red'>答案不能为空</span>");
			}
	});







$('#sub').click(function(){
var username = $("#username").val();
var password = $("#password").val();
var realname = $("#realname").val();
var birthday = $("#birthday").val();
var contact = $("#contact").val();
$("#username_msg").empty();
$("#password_msg").empty();
$("#realname_msg").empty();
$("#birthday_msg").empty();
$("#contact_msg").empty();
if (username == "" || username == null) {
	$("#username").after("<span id='username_msg' style='color: red'>用户名不能为空</span>");
	return false;
}
if (password == "" || password == null) {
	$("#password").after("<span id='password_msg' style='color: red'>密码不能为空</span>");
	return false;
}
if (realname == "" || realname == null) {
	$("#realname").after("<span id='realname_msg' style='color: red'>姓名不能为空</span>");
	return false;
}
if (birthday == "" || birthday == null) {
	$("#birthday").after("<span id='birthday_msg' style='color: red'>出生日期不能为空</span>");
	return false;
}
if (contact == "" || contact == null) {
	$("#contact").after("<span id='contact_msg' style='color: red'>联系方式不能为空</span>");
	return false;
}
});
$('#res').click(function() {
$("#username_msg").empty();
$("#password_msg").empty();
$("#realname_msg").empty();
$("#birthday_msg").empty();
$("#contact_msg").empty();
});


});
