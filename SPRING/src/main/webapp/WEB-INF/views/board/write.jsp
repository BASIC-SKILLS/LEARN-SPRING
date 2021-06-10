<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<form action="write" method="post">
    <input type="hidden" name="iboard" value="${requestScope.data==null ? 0 : requestScope.data.iboard}">  <!--등록과 수정의 단 하나의 차이--> <!--방법1.value가 없으면 빈칸으로 넘어가는데 int로 받는데 빈칸은 int로 바꿀 수가 없어서 에러가 터진당-->
    <div><input type="text" name="title" placeholder="제목" value="${requestScope.data.title}"></div>
    <div><textarea name="ctnt" placeholder="내용">${requestScope.data.ctnt}</textarea></div>
    <div>
        <input type="submit" value="등록"><input type="reset" value="새로 작성">
    </div>
</form>