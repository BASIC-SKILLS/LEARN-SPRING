<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <h1>로그인</h1>

    ${errMsg}

    <div>
        <div>
            <form action="login" method="post">
                <div><input type="text" name="uid" placeholder="아이디"></div>
                <div><input type="text" name="upw" placeholder="비밀번호"></div>
                <div>
                    <input type="submit" value="로그인">
                </div>
            </form>
        </div>
    </div>
