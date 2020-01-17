<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Wifi Polling Viewer</title>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<meta name="description" content="">
	<meta name="author" content="">

	<!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <!-- JQuery-UI -->
    <link href="${pageContext.request.contextPath}/resources/css/jquery-ui/jquery-ui.structure.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/jquery-ui/jquery-ui.theme.min.css" rel="stylesheet">
    <!-- dataTable -->
	<link href="${pageContext.request.contextPath}/resources/DataTables/datatables.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/blog.css" rel="stylesheet">

	<!-- Core Javascript -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery/jquery-3.3.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-ui/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/popper/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>    
    <!-- dataTable -->
	<script src="${pageContext.request.contextPath}/resources/DataTables/datatables.min.js"></script>
	<!-- Underscore -->
	<script src="${pageContext.request.contextPath}/resources/js/underscore/underscore-min.js"></script>
	<!-- blockUI -->
	<script src="${pageContext.request.contextPath}/resources/js/blockUI/jquery.blockUI.js"></script>
	
</head>

<body>
	<div id="content" class="container-fluid">
		<div class="row">
		  <div class="col-12 refreshTime"> 
		    <span id="systemSpan" style="color: lime"></span>；<span id="refreshSpan"></span>
		  </div>
		</div>
		
		<!-- 查詢結果TABLE區塊 -->
		<div class="row">
		  <div class="col-sm-12 infoTableSection" style="display:none;">
			<table id="infoTable" class="dataTable myTable table-striped table-hover table-sm table-responsive-sm nowrap" style="width:100%;">
			  <thead class="center">
			    <tr>
			      <th scope="col" nowrap="nowrap"><u>用戶 MAC</u></th>
			      <th scope="col" nowrap="nowrap"><u>SLOT ID</u></th>
			      <th scope="col" nowrap="nowrap">用戶 IP</th>
			      <th scope="col" nowrap="nowrap"><u>連線 AP</u></th>
			      <th scope="col" nowrap="nowrap"><u>連線 SSID</u></th>
			      <th scope="col" nowrap="nowrap">頻道</th>
			      <th scope="col" nowrap="nowrap">訊號強度</th>
			      <th scope="col" nowrap="nowrap">雜訊等級</th>
			      <th scope="col" nowrap="nowrap">信躁比 (SNR)</th>
			      <th scope="col" nowrap="nowrap">首次出現時間</th>
			      <th scope="col" nowrap="nowrap">最後出現時間</th>
			      <th scope="col" nowrap="nowrap">傳輸平均 KB/分</th>
			      <th scope="col" nowrap="nowrap">接收平均 KB/分</th>
			    </tr>
			  </thead>
			</table>
		  </div>
		</div>
		
		<!-- 查詢結果TABLE區塊
		<div class="row">
		  <div class="col-sm-12 transferTableSection" style="display:none;">
			<table id="transferTable" class="dataTable myTable table-striped table-hover table-sm table-responsive-sm nowrap" style="width:100%;">
			  <thead class="center">
			    <tr>
			      <th scope="col" nowrap="nowrap">用戶 MAC</th>
			      <th scope="col" nowrap="nowrap">用戶 IP</th>
			      <th scope="col" nowrap="nowrap">連線 AP</th>
			      <th scope="col" nowrap="nowrap">連線 SSID</th>
			      <th scope="col" nowrap="nowrap">首次出現時間</th>
			      <th scope="col" nowrap="nowrap">最後出現時間</th>
			      <th scope="col" nowrap="nowrap">傳輸平均</th>
			      <th scope="col" nowrap="nowrap">接收平均</th>
			    </tr>
			  </thead>
			</table>
		  </div>
		</div>
		 -->
	</div>
	
</body>

<script>
	var infoTable;					//DataTable
	var transferTable;				//DataTable
	var dataTableHeight;
	
	$(document).ready(function() {
		calHeight();
		findInfoData();
		//findTransferData();
		$("#systemSpan").text("系統時間: " + formatDate());
		$("#refreshSpan").text("更新時間: "+ formatDate() + " (更新頻度: 60 秒)");
		
		setInterval(function() {
			$("#systemSpan").text("系統時間: "+ formatDate());
		}, 1000);
		
		setInterval(function() {
			findInfoData();
			$("#refreshSpan").text("更新時間: "+ formatDate() + " (更新頻度: 60 秒)");
		}, 60000);
		
		//縮放視窗大小時，延遲1秒後重算DataTable區塊高度 & 重繪DataTable
	    $(window).resize(_.debounce(function() {
	    	if (typeof infoTable !== "undefined") {
	    		calHeight();
	    		$('.dataTables_scrollBody').css('max-height', dataTableHeight);
	    		infoTable.clear().draw();
	    		infoTable.ajax.reload();
	    	}
	    }, 500));
	});
	
	function addZero(i) {
	    if (i < 10) {
	        i = "0" + i;
	    }
	    return i;
	}
	
	function formatDate() {
	    var d = new Date(),
	        month = addZero((d.getMonth() + 1)),
	        day = addZero(d.getDate()),
	        year = d.getFullYear(),
	        hour = addZero(d.getHours()),
	        minute = addZero(d.getMinutes()),
	        second = addZero(d.getSeconds());

	    return [year, month, day].join('-').concat(' ').concat([hour, minute, second].join(':'));
	}
	
	//查詢按鈕動作
	function findInfoData() {
		if (typeof infoTable !== "undefined") {
			infoTable.ajax.reload();
			
  		} else {
  			$(".infoTableSection").show();
  			
  			infoTable = $('#infoTable').DataTable(
			{
				"autoWidth" 	: true,
				"paging" 		: true,
				"bFilter" 		: true,
				"ordering" 		: true,
				"info" 			: true,
				"serverSide" 	: false,
				"bLengthChange" : true,
				"pagingType" 	: "full_numbers",
				"processing" 	: true,
				"scrollX"		: true,
				"scrollY"		: dataTableHeight,
				"scrollCollapse": false,
				"pageLength"	: 25,
				"language" : {
		    		"url" : "${pageContext.request.contextPath}/resources/js/dataTable/i18n/Chinese-traditional.json"
		        },
				"ajax" : {
					"url" : '${pageContext.request.contextPath}/servlet/getInfoData.json',
					"type" : 'POST',
					"data" : function ( d ) {
						return d;
					},
					"error" : function(xhr, ajaxOptions, thrownError) {
						ajaxErrorHandler();
					}
				},
				"order": [[11 , 'desc' ]],
				"initComplete": function(settings, json){
					$.fn.dataTable.tables( { visible: true, api: true } ).columns.adjust();
	            },
				"drawCallback" : function(settings) {
					//$.fn.dataTable.tables( { visible: true, api: true } ).columns.adjust();
					$("div.dataTables_length").parent().removeClass('col-sm-12');
					$("div.dataTables_length").parent().addClass('col-sm-6');
					$("div.dataTables_length").parent().parent().addClass('dataTableTitle');
					$("div.dataTables_filter").parent().removeClass('col-sm-12');
					$("div.dataTables_filter").parent().addClass('col-sm-6');
					//$("div.dataTables_filter").addClass('dataTableTitle');
					
					$("div.dataTables_info").parent().removeClass('col-sm-12');
					$("div.dataTables_info").parent().addClass('col-sm-6');
					$("div.dataTables_paginate").parent().removeClass('col-sm-12');
					$("div.dataTables_paginate").parent().addClass('col-sm-6');
				},
				"columns" : [
					{ "data" : "userMacAddr" , "className" : "center" },
					{ "data" : "slotId" , "className" : "center" },
					{ "data" : "ipAddr" , "className" : "center" },
					{ "data" : "apName" , "className" : "center" },
					{ "data" : "ssidName" , "className" : "center" },
					{ "data" : "channel" , "className" : "center" },
					{ "data" : "signalStrenth" , "className" : "center" },
					{ "data" : "noiseLevel" , "className" : "center" },
					{ "data" : "snr" , "className" : "center" },
					{ "data" : "firstConnectTime" , "className" : "center" },
					{ "data" : "lastDisconnectTime" , "className" : "center" },
					{ "data" : "avgSendData" , "className" : "center" },
					{ "data" : "avgReceiveData" , "className" : "center" }
				],
			});
  		}
	}
	
	//計算DataTable可呈顯的區塊高度
	function calHeight() {
		dataTableHeight = window.innerHeight - 170;
	}
	
	/*
	function findTransferData() {
		if (typeof transferTable !== "undefined") {
			transferTable.ajax.reload();
			
  		} else {
  			$(".transferTableSection").show();
  			
  			transferTable = $('#transferTable').DataTable(
			{
				"autoWidth" 	: true,
				"paging" 		: true,
				"bFilter" 		: true,
				"ordering" 		: true,
				"info" 			: true,
				"serverSide" 	: false,
				"bLengthChange" : true,
				"pagingType" 	: "full_numbers",
				"processing" 	: true,
				"scrollX"		: false,
				"scrollY"		: "200px",
				"scrollCollapse": true,
				"pageLength"	: 25,
				"language" : {
		    		"url" : "${pageContext.request.contextPath}/resources/js/dataTable/i18n/Chinese-traditional.json"
		        },
				"ajax" : {
					"url" : '${pageContext.request.contextPath}/servlet/getTransferData.json',
					"type" : 'POST',
					"data" : function ( d ) {
						return d;
					},
					"error" : function(xhr, ajaxOptions, thrownError) {
						ajaxErrorHandler();
					}
				},
				/*"order": [[6 , 'desc' ]],
				/*
				"initComplete": function(settings, json){
	            },
				"drawCallback" : function(settings) {
					/*
					$.fn.dataTable.tables( { visible: true, api: true } ).columns.adjust();
					$("div.dataTables_length").parent().removeClass('col-sm-12');
					$("div.dataTables_length").parent().addClass('col-sm-6');
					$("div.dataTables_filter").parent().removeClass('col-sm-12');
					$("div.dataTables_filter").parent().addClass('col-sm-6');
					
					$("div.dataTables_info").parent().removeClass('col-sm-12');
					$("div.dataTables_info").parent().addClass('col-sm-6');
					$("div.dataTables_paginate").parent().removeClass('col-sm-12');
					$("div.dataTables_paginate").parent().addClass('col-sm-6');
				},
				"columns" : [
					{ "data" : "userMacAddr" , "className" : "center" },
					{ "data" : "ipAddr" , "className" : "center" },
					{ "data" : "apName" , "className" : "center" },
					{ "data" : "ssidName" , "className" : "center" },
					{ "data" : "firstConnectTime" , "className" : "center" },
					{ "data" : "lastDisconnectTime" , "className" : "center" },
					{ "data" : "avgSendData" , "className" : "center" },
					{ "data" : "avgReceiveData" , "className" : "center" }
				],
			});
  		}
	}
	*/
	
	function ajaxErrorHandler() {
		alert('連線逾時，頁面將重新導向');
		location.reload();
	}

</script>

</html>