//初始化layui
var form = null, layer = null;
layui.use(["layer","form"], function(){
	form = layui.form;
	layer = layui.layer;
});

function loadingAjaxHtml(url){
	let htmlStr = "";
	let layer_load = null;
	$.ajax({
		type: "POST",
		dataType: "html",
		cache: false,
		timeout: 10000,
		url: url,
		async: false,
		success: function(data){
			htmlStr=data;
		},
		complete: function(request) {
			setTimeout(function () {
				layer.close(layer_load);//关闭
		    }, 200);
		},
		error: function(data){
			layer.msg('访问服务器失败！', {icon: 5});
		},
		beforeSend: function(data){
			layer_load=layer.load(0,{shade:[0.7,'#F0F0EA']});
		}
	});
	return htmlStr;
}

function loadingAjaxJson(url){
	let jsonObj = "";
	let layer_load = null;
	$.ajax({
		type: "POST",
		dataType: "text",
		cache: false,
		timeout: 10000,
		url: url,
		async: false,
		success: function(data){
			jsonObj=eval(data);
		},
		complete: function(request) {
			setTimeout(function () {
				layer.close(layer_load);//关闭
		    }, 200);
		},
		error: function(data){
			layer.msg('访问服务器失败！', {icon: 5});
		},
		beforeSend: function(data){
			layer_load=layer.load(0,{shade:[0.7,'#F0F0EA']});
		}
	});
	return jsonObj;
}

/**
 * ajax获取json
 * @param type 请求类型：post，get
 * @param url 请求路径
 * @param data json数据
 * @returns
 */
function loadingAjaxJsonData(type,url,data){
	data=JSON.stringify(data);
	let jsonObj = "";
	const index = layer.load(3);
	$.ajax({
		type: type,
		contentType: "application/json;charset=utf-8",
		dataType: "json",
		data:data,
		cache: false,
		timeout: 10000,
		url: url,
		async: false,
		success: function(result){
			jsonObj=result;
		},
		complete: function(request) {
			setTimeout(function () {
				layer.close(index);//关闭
			}, 200);
		},
		error: function(xhr){
			layer.msg('读取数据失败', {icon: 5,area: ['100px', '60px']});
		}
	});
	return jsonObj;
}
