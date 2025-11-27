console.log("boardDetail.js in");
console.log("bnoValue: " + bnoVal);

document.getElementById('cmtAddBtn').addEventListener('click', () => {
    const cmtWriter = document.getElementById('cmtWriter').value;
    const cmtText = document.getElementById('cmtText').value;

    if (cmtText == null || cmtText == "") {
        alert("댓글 내용을 입력하세요");
        return;
    }

    // JSON 데이터 생성
    const cmtData = {
        bno: bnoVal,
        writer: cmtWriter,
        content: cmtText
    };

    // 서버로 전송 (fetch)
    postCommentToServer(cmtData).then(result => {
        if (result > 0) {
            alert("댓글 등록 성공!");
            document.getElementById('cmtText').value = ""; // 입력창 비우기
            getCommentList(bnoVal); // 댓글 목록 다시 불러오기
        } else {
            alert("댓글 등록 실패 ㅠㅠ");
        }
    });
});

// 댓글 등록 함수 (비동기)
async function postCommentToServer(cmtData) {
    try {
        const url = "/cmt/post";
        const config = {
            method: 'post',
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text(); // 0 또는 1
        return result;
    } catch (error) {
        console.log(error);
    }
}

// 댓글 목록 가져오기 함수
async function getCommentList(bno) {
    try {
        const resp = await fetch('/cmt/list?bno=' + bno);
        const result = await resp.json(); // JSON 리스트 리턴
        console.log(result);
        spreadCommentList(result); // 화면에 뿌리기
    } catch (error) {
        console.log(error);
    }
}

// 화면에 댓글 뿌리기
function spreadCommentList(result) {
    const div = document.getElementById('commentLine');
    div.innerHTML = ""; // 기존 내용 초기화
    
    let html = "";
    for(let i=0; i<result.length; i++) {
        html += `<div>`;
        html += `<div>${result[i].cno}, ${result[i].bno}, ${result[i].writer}, ${result[i].regdate}</div>`;
        html += `<div>`;
        html += `<input type="text" value="${result[i].content}">`;
        html += `<button type="button">수정</button>`;
        html += `<button type="button">삭제</button>`;
        html += `</div></div><hr>`;
    }
    div.innerHTML = html;
}