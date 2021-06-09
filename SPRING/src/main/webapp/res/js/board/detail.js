
//detail화면이 켜저있는 동안은 계속 쓸 수 있는 상태로 되어 있는 변수들이다.
//from태그를 참조하고 있기 때문에 form태그와 자식태그(input)를 내마음대로 쓰고 바꿀 수 있다.
var cmtFrmElem = document.querySelector('#cmtFrm');
var cmtListElem = document.querySelector('#cmtList');
var cmtModModalElem = document.querySelector('#modal');

function regCmt() {
    var cmtVal = cmtFrmElem.cmt.value;
    var param = { //AJAX에 값을 보내야 하는 값들을 정리해두었다.
        iboard: cmtListElem.dataset.iboard,
        cmt: cmtVal
    };
    regAjax(param);
}

//서버에게 등록해줘~~~
function regAjax(param) {
    const init = {
        method: 'POST', //post는 insert
        body: JSON.stringify(param),
        headers:{
            'accept' : 'application/json',
            'content-type' : 'application/json;charset=UTF-8'
        }

    };

    fetch('cmt', init)
        .then(function(res) {
            return res.json();
        })
        .then(function(myJson) { //통신성공
            console.log(myJson);

            switch(myJson.result) { 
                case 0://등록실패
                    alert('등록 실패!');
                    break;
                case 1://등록성공
                    cmtFrmElem.cmt.value = '';

                    getListAjax();
                    break;
            }
        });
}

//서버에게 댓글 리스트 자료 달라고 요청하는 함수
function getListAjax() {
    var iboard = cmtListElem.dataset.iboard;

    fetch('cmt/' + iboard)
        .then(function(res) {
            return res.json();
        })
        .then(function(myJson) {  //get/iboard 지만 detail이 아니라 list
            console.log(myJson);

            makeCmtElemList(myJson);
        });
}

function makeCmtElemList(data) {

    cmtListElem.innerHTML = '';

    var tableElem = document.createElement('table');
    var trElemTitle = document.createElement('tr');
    var thElemCtnt = document.createElement('th');
    var thElemWriter = document.createElement('th');
    var thElemRegdate = document.createElement('th');
    var thElemBigo = document.createElement('th');

    thElemCtnt.innerText = '내용';
    thElemWriter.innerText = '작성자';
    thElemRegdate.innerText = '작성일';
    thElemBigo.innerText = '비고';

    trElemTitle.append(thElemCtnt);
    trElemTitle.append(thElemWriter);
    trElemTitle.append(thElemRegdate);
    trElemTitle.append(thElemBigo);

    tableElem.append(trElemTitle);
    cmtListElem.append(tableElem);

    var loginUserPk = cmtListElem.dataset.loginUserPk;

    //data의 length만큼 function을 반복해주는데 function에 data의 배열들을 순서대로 function의 인자로 넣어준다 하나하나 순서대로
    //그게 foreach문 이었잖아~~~~용~~~
    data.forEach(function(item) {
        var trElemCtnt = document.createElement('tr');
        var tdElem1 = document.createElement('td');
        var tdElem2 = document.createElement('td');
        var tdElem3 = document.createElement('td');
        var tdElem4 = document.createElement('td');

        tdElem1.append(item.cmt);
        tdElem2.append(item.writeNm);
        tdElem3.append(item.regdate);

        if(parseInt(loginUserPk) === item.iuser) {
            var delBtn = document.createElement('button');
            var modBtn = document.createElement('button');

            //삭제버튼 클릭시
            delBtn.addEventListener('click', function() {
                if(confirm('삭제하시겠습니까?')) {
                    delAjax(item.icmt);
                }
            });

            //수정버튼 클릭시
            modBtn.addEventListener('click', function() {
                //댓글 수정 모달창 띄우기
                openModModal(item);
            });

            delBtn.innerText = '삭제';
            modBtn.innerText = '수정';

            tdElem4.append(delBtn);
            tdElem4.append(modBtn);
        }

        trElemCtnt.append(tdElem1);
        trElemCtnt.append(tdElem2);
        trElemCtnt.append(tdElem3);
        trElemCtnt.append(tdElem4);

        tableElem.append(trElemCtnt);
    });
}

function delAjax(icmt) {
    fetch('cmt/'+icmt,{method:'DELETE'})
        .then(function(res) {
            return res.json();
        })
        .then(function(data) {
            console.log(data);

            switch(data.result) {
                case 0:
                    alert('댓글 삭제를 실패하였습니다.');
                    break;
                case 1:
                    getListAjax();
                    break;
            }
        });
}

function modAjax() {
    var cmtModFrmElem = document.querySelector('#cmtModFrm');
    var param = {
        icmt: cmtModFrmElem.icmt.value,
        cmt: cmtModFrmElem.newCmt.value
    }

    const init = {
        method: 'PUT',
        body: JSON.stringify(param),
        headers:{
            'accept' : 'application/json',
            'content-type' : 'application/json;charset=UTF-8'
        }  //모든요청은 body와 headers가 있다. JASON을 쓸 때는 body와 headers를 이렇게 설정해주고, 서버에서는 @RequestBody로 받아준당
    };

    fetch('cmt', init)
        .then(function(res) {
            return res.json();
        })
        .then(function(myJson) {
            closeModModal();
            switch(myJson.result) {
                case 0:
                    alert('댓글 수정을 실패하였습니다.');
                    break;
                case 1:
                    getListAjax();
                    break;
            }
        });
}

function openModModal({icmt, cmt}) {
    cmtModModalElem.className = '';

    var cmtModFrmElem = document.querySelector('#cmtModFrm');
    cmtModFrmElem.icmt.value = icmt;
    cmtModFrmElem.newCmt.value = cmt;
}

function closeModModal() {
    cmtModModalElem.className = 'displayNone';
}

getListAjax(); //이 파일이 임포트되면 함수 1회 호출!
