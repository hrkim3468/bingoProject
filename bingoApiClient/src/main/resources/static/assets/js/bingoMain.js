/**
 * 빙고게임을 위한 공통 스크립트
 * 
 * @param name
 * @returns
 */


// 쿼리스트링에서 원하는 파라메터를 리턴한다.
function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}


// 템플릿을 이용하여 화면을 그린다.
function fnRenderTemplate(templateName, randerKey, json) {
	var templateTag = document.getElementById(templateName);
	var compiled = Handlebars.compile(templateTag.innerHTML);
	
	// JSON 데이터가 Array 형태일 경우 파싱을 위한 Wrapper를 추가해준다. 
	var wrapper = {data: json};
	var html = compiled(wrapper);
	
	document.getElementById(randerKey).innerHTML = html;		
}

Handlebars.registerHelper('toDateFormat', function(options) {
	var date = new Date(this.createTime);
	return date.toString();
});

Handlebars.registerHelper('winnerFormat', function(options) {
	if (this.ranking <= 5) {
		return "class='winner'";
	} else {
		return "class='general'";
	}
});


//API를 조회하여 Template을 이용하여 화면에 리스트를 그린다.
function fnTemplateListRendering(url, templateName, randerKey) {
	$.ajax({
		type: "get",
		cache: false,
		url: url,
		data: "",
		success: function(json) {			
			fnRenderTemplate(templateName, randerKey, json);
		},
		error: function(xhr, info) {
			alert("Api 호출에 실패하였습니다. : fnTemplateListRendering()");
		}
	});	
}


// UUID에 해당하는 사용자 정보를 조회하여 화면에 그린다.
function fnUserInfoRendering(id) {
	var url = "/api/bingo/user/" + getParameterByName('uuid');
	$.ajax({
		type: "get",
		cache: false,
		url: url,
		data: "",
		success: function(json) {
			$("#"+id).html(json.name);
		},
		error: function(xhr, info) {
			alert("Api 호출에 실패하였습니다. : fnUserInfoRendering()");
		}
	});	
}



// 선택한 게임에서 UUID에 해당하는 정보를 조회하여 게임판을 채운다.
function fnGameDataRendering() {
	var url = "/api/bingo/gameData/gameId/" + getParameterByName('gameId') + "/uuid/" + getParameterByName('uuid');
	$.ajax({
		type: "get",
		cache: false,
		url: url,
		data: "",
		success: function(json) {
			for (var i in json) {
				$("#id_gameCell_" + json[i].dataIndex).html(json[i].dataNumber);
				$("#id_checkNumber_" + json[i].dataNumber).val(json[i].dataIndex);
			}
			fnGameCheckNumberRendering();
		},
		error: function(xhr, info) {
			alert("Api 호출에 실패하였습니다. : fnGameDataRendering()");
		}
	});		
}




// 채워진 게임판에 CheckNumber 리스트를 읽어 표시해준다. (화면로드시에만 최초로 그리고 업데이트는 WebSocket을 이용한다.) 
function fnGameCheckNumberRendering() {
	var url = "/api/bingo/gameCheckNumber/gameId/" + getParameterByName('gameId');
	$.ajax({
		type: "get",
		cache: false,
		url: url,
		data: "",
		success: function(json) {
			for (var i in json) {
				var cellKey = $("#id_checkNumber_" + json[i].checkNumber).val();
				$("#id_gameCell_" + cellKey).addClass("clear");
				
				fnGameClearLineCountRendering();
			}
			$("#id_push_gameCheckCount").html(json.length);
		},
		error: function(xhr, info) {
			alert("Api 호출에 실패하였습니다. : fnGameDataRendering()");
		}
	});		
}



// 사용자별로 Clear 라인수를 표시해준다. (WebSocket Push가 들어올때마다 Ajax를 요청한다.)
function fnGameClearLineCountRendering() {
	var url = "/api/bingo/gameClearLineCount/gameId/" + getParameterByName('gameId') + "/uuid/" + getParameterByName('uuid');
	$.ajax({
		type: "get",
		cache: false,
		url: url,
		data: "",
		success: function(json) {
			$("#id_push_gameClearLineCount").html(json.clearLineCount);
			
			if (json.clearLineCount > 4) {
				$(".clear").css("background-color","green");
				$("#id_div_winner").show();
			}
		},
		error: function(xhr, info) {
			alert("Api 호출에 실패하였습니다. : fnGameClearLineCountRendering()");
		}
	});		
}







// 사용자별 게임판 화면으로 이동
function fnGoGame(gameId) {
	location.href = "/apps/bingoGame.html?gameId=" + gameId + "&uuid=" + getParameterByName('uuid');
}







