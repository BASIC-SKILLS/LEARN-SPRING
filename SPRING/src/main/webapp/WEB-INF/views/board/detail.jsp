<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>DETAIL</title>

    <link rel="stylesheet" href="/res/css/boardDetail.css">
    <link rel="stylesheet" href="/res/css/common.css">  <!--import 순서 때문에 css가 적용 안될 수도 있다 -->
    <script defer src="/res/js/boardDetail.js"></script>
</head>
<body>

    <div><a href="#" onclick="goBack();">돌아가기</a></div>
    
    <h1>${requestScope.data.title}</h1>
    <div>글번호 : ${requestScope.data.iboard}</div>
    <div>작성자 : <c:out value="${requestScope.data.writeNm}"/> | 작성일 : ${requestScope.data.regdt}</div>
    <!--왠만하면 문자열 들어갈 수 있는 곳은 c:out으로 하는 것이 좋다. 자바스크립트 공격 (XSS)를 피할 수 있기 때문이다. 그냥 el만 적으면 스크립트가 실행이 되는데 저렇게 적으면 그냥 값만 찍혀서 스크립트가 실행안된다. 그래서 공격을 피할 수 있다. -->
    <div><c:out value="${requestScope.data.ctnt}"/></div>
    
    <c:if test="${not empty sessionScope.loginUser}">
        <div>
            <form id="cmtFrm" onsubmit="return false;">
                <input type="text" id="cmt">
                <input type="button" value="댓글달기" onclick="regCmt();">
            </form>
        </div>
    </c:if>

    <!--data-set속성 data로 시작하는 애들한테 접근할 수 있어요(data-이름, data-이름-이름 이렇게 하면 js에서 이름이름 이렇게 가져오면 된대요, data-login-user-pk -> loginUserPk)을 이용하여서 div속성에 값을 넣어둬요. js에서 쓸려구요.-->
    <!--html은 순서와 배열이 없고 key value 밖에없당-->
    <div id="cmtList" data-login-user-pk="${sessionScope.loginUser.iuser}" data-iboard="${param.iboard}"></div>


    <div id="modal" class="displayNone">
        <div class="modal_content">
            <form id="cmtModFrm" action="#">
                <input type="hidden" id="icmt">
                <input type="text" id="newCmt">  <!--id는 문서에서 유일해야 한다-->
            </form>
            <input type="button" value="댓글 수정" onclick="modAjax();">
            <input type="button" value="취소" onclick="closeModModal();">
        </div>
    </div>

</div>
</body>
</html>
