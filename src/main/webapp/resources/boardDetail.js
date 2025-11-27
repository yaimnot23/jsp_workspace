/**
 * 
 */
console.log("boardDetail.js in");
console.log(bnoValue);

document.getElementById('cmtAddBtn').addEventListener('click',()=>{
	const cmtWriter = document.getElementById('cmtWriter').value;
	const cmtText = document.getElementById('cmtText').value;
	
	if(cmtText.value==null || cmtText.value==""){
        alert("댓글 내용을 입력하세요");
        return;
    }
    
    
})