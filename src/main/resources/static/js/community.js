function post(){
    var questionId = $("#question_id").val();
    var commentContent = $("#comment_content").val();
    $.ajax(
        {
            type: "POST",
            url: "/comment",
            contentType: "application/json",
            data: JSON.stringify({
                "parentId": questionId,
                "content": commentContent,
                "type": 1
            }),
            success: function (response) {
                if (response.code == 200)
                {
                    $("#comment_section").hide();
                }else{
                    if (response.code == 2003)
                    {
                        var isAccepted = confirm(response.message);
                        if (isAccepted)
                        {
                               window.open("https://github.com/login/oauth/authorize?client_id=ded66fda0eb3f9660f0a&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                               window.localStorage.setItem("closable", true);
                        }
                    }
                    alert(response.message);
                }
                console.log(response);
            },
            dataType: "json"
        }
    );

}