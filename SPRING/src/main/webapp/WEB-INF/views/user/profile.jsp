<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${empty sessionScope.loginUser.profileImg}">
        <c:set var="img" value="/res/img/noprofile.jpg"/>   <!--pagecontext에서 img라는 키값으로 setattribute한다--> <!--변수(pageContext)에 다가 값 담는 것이다. 그래서 EL식으로 사용 가능 한 것이다.-->
    </c:when>
    <c:otherwise>
        <c:set var="img" value="/img/${sessionScope.loginUser.iuser}/${sessionScope.loginUser.profileImg}"/>      <!--value지만, 이게 src로 가니까, 결국 경로이다. 톰캣의 폴더에서 이미지가 가져와지는 거니까, 경로이다. 이 경로들을 서블릿에서 받아서 보내준다.-->
    </c:otherwise>
</c:choose>

<div>
    <form action="profile" method="post" enctype="multipart/form-data" id="frm" onsubmit="return imgChk();">
        이미지변경 : <input type="file" name="profileImg" accept="image/*">
        <input type="submit" value="이미지 업로드">
    </form>
</div>

<div>
    <div><img src="${img}"></div>
    <div>PK : ${sessionScope.loginUser.iuser}</div>
    <div>ID : ${sessionScope.loginUser.uid}</div>
    <div>Name : ${sessionScope.loginUser.unm}</div>
</div>

