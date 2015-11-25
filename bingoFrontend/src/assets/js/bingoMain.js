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


// 게임목록을 조회하여 화면에 그린다.
function fnGameListRendering() {
	var url = "http://bingo.navercorp.com:9601/api/game";
	$.ajax({
		type: "get",
		cache: false,
		url: url,
		data: "",
		success: function(json) {
			
			for (var i in json) {
				alert(json[i].id);
			}

		},
		error: function(xhr, info) {
			alert("Api 호출에 실패하였습니다. : fnGameListRendering()");
		}
	});	
}

