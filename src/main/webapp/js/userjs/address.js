$(function() {

	$("#receiverName").blur(
			function() {
				$("#receiverName_msg").empty();
				var name = $(this).val();
				if (name == "" || name == null) {
					$("#receiverName").after("<span id='receiverName_msg' style='color: red'>收货姓名不能为空</span>");
				}
		});

	$("#receiverPhone").blur(
			function() {
				$("#receiverPhone_msg").empty();
				var name = $(this).val();
				if (name == "" || name == null) {
					$("#receiverPhone").after("<span id='receiverPhone_msg' style='color: red'>收货固定电话不能为空</span>");
				}
		});


    $("#receiverMobile").blur(
        function() {
            $("#receiverMobile_msg").empty();
            var name = $(this).val();
            if (name == "" || name == null) {
                $("#receiverMobile").after("<span id='receiverMobile_msg' style='color: red'>收货移动电话不能为空</span>");
            }
        });

    $("#receiverProvince").blur(
		function() {
			$("#receiverProvince_msg").empty();
			var name = $(this).val();
			if (name == "" || name == null) {
				$("#receiverProvince").after("<span id='receiverProvince_msg' style='color: red'>省份不能为空</span>");
			}
	});

$("#receiverCity").blur(
		function() {
			$("#receiverCity_msg").empty();
			var name = $(this).val();
			if (name == "" || name == null) {
				$("#receiverCity").after("<span id='receiverCity_msg' style='color: red'>城市不能为空</span>");
			}
	});

$("#receiverDistrict").blur(
		function() {
			$("#receiverDistrict_msg").empty();
			var name = $(this).val();
			if (name == "" || name == null) {
				$("#receiverDistrict").after("<span id='receiverDistrict_msg' style='color: red'>区/县不能为空</span>");
			}
	});

$("#receiverAddress").blur(
		function() {
			$("#receiverAddress_msg").empty();
			var name = $(this).val();
			if (name == "" || name == null) {
				$("#receiverAddress").after("<span id='receiverAddress_msg' style='color: red'>详细地址不能为空</span>");
			}
	});


    $("#receiverZip").blur(
        function() {
            $("#receiverZip_msg").empty();
            var name = $(this).val();
            if (name == "" || name == null) {
                $("#receiverZip").after("<span id='receiverZip_msg' style='color: red'>邮编不能为空</span>");
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
