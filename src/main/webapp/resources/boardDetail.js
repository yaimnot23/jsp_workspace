console.log("boardDetail.js in");
console.log("bnoValue: " + bnoVal);

getCommentList(bnoVal);

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

// 화면에 댓글 뿌리기 (디자인 개선)
function spreadCommentList(result) {
    const div = document.getElementById('commentLine');
    div.innerHTML = ""; // 기존 내용 초기화
    
    let html = "";
    for(let i=0; i<result.length; i++) {
        html += `<div class="comment-box" style="border: 1px solid #ccc; padding: 10px; margin-bottom: 10px; border-radius: 5px;">`;
        
        // 1. 작성자 및 날짜 영역
        html += `<div style="margin-bottom: 5px;">`;
        html +=     `<strong>${result[i].writer}</strong> `; // 작성자 진하게
        html +=     `<span style="font-size: 12px; color: gray;">${result[i].regdate}</span>`; // 날짜는 작고 회색으로
        html += `</div>`;
        
        // 2. 댓글 내용 (수정 가능하도록 input 사용)
        html += `<div style="margin-bottom: 5px;">`;
        html +=     `<input type="text" class="cmtText" value="${result[i].content}" style="width: 100%; padding: 5px;">`;
        html += `</div>`;
        
        // 3. 버튼 영역
        html += `<div>`;
        html +=     `<button type="button" data-cno="${result[i].cno}" class="modBtn">수정</button> `;
        html +=     `<button type="button" data-cno="${result[i].cno}" class="delBtn">삭제</button>`;
        html += `</div>`;
        
        html += `</div>`; // comment-box 닫기
    }
    div.innerHTML = html;
}

// 수정, 삭제 버튼 클릭 이벤트 감지 (이벤트 위임)
document.addEventListener('click', (e) => {
    // 1. 수정 버튼 클릭 시
    if(e.target.classList.contains('modBtn')){
        // 버튼에 있는 data-cno 값 가져오기
        let cnoVal = e.target.dataset.cno;
        // 내 버튼이 속한 div를 찾고, 그 안의 input 값을 찾음
        let div = e.target.closest('div').parentElement; // comment-box div
        let cmtText = div.querySelector(".cmtText").value;
        
        console.log("update cno:", cnoVal, "content:", cmtText);
        
        // 서버로 전송
        updateCommentToServer(cnoVal, cmtText).then(result => {
            if(result > 0){
                alert("댓글 수정 성공");
                getCommentList(bnoVal);
            } else {
                alert("수정 실패");
            }
        });
    }
    
    // 2. 삭제 버튼 클릭 시
    if(e.target.classList.contains('delBtn')){
        let cnoVal = e.target.dataset.cno;
        console.log("delete cno:", cnoVal);
        
        // 삭제는 되돌릴 수 없으니 확인창 띄우기
        if(confirm('정말 삭제하시겠습니까?')) {
            removeCommentToServer(cnoVal).then(result => {
                if(result > 0){
                    alert("댓글 삭제 성공");
                    getCommentList(bnoVal);
                } else {
                    alert("삭제 실패");
                }
            });
        }
    }
});

// 서버에 수정 요청 (JSON)
async function updateCommentToServer(cno, content) {
    try {
        const url = "/cmt/modify";
        const config = {
            method: 'post', // put or patch도 가능하지만 여기선 post로 처리
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify({cno: cno, content: content})
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

// 서버에 삭제 요청 (Parameter)
async function removeCommentToServer(cno) {
    try {
        const url = "/cmt/remove?cno=" + cno;
        const config = {
            method: 'post' // get으로도 가능하지만 post로 처리
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}