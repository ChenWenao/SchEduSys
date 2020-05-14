function registerCourse(obj) {

    var temp = obj.parentNode.parentNode.id;
    alert(temp);
    $.ajax({
        //请求方式
        type: "GET",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",

        url: "/Register/newRegister/" + temp,

        dataType: "json",
        // dataType后台返回的数据类型
        //请求成功
        success: function (result) {
            alert(result);

        },
    });

}

function exitCourse(obj) {
    var temp = obj.parentNode.parentNode.id;
    alert(temp);

    $.ajax({


        //请求方式
        type: "GET",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",

        url: "/Register/removeRegister/" + temp,

        dataType: "json",
        // dataType后台返回的数据类型
        //请求成功
        success: function (result) {
            alert(result);

        },
    });
}


function courseRegisterfunc() {
    // 点击之后刷新接受数据刷新界面
    $.ajax({
        //请求方式
        type: "GET",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",

        url: "/Schedule/schedulesOn/courseName/0/0/0",
        // data: {key:value}发送参数
        dataType: "json",
        // dataType后台返回的数据类型
        //请求成功
        success: function (result) {

            var temp = JSON.stringify(result);
            var courses = eval(temp);
            $(".main-table").remove();
            $(".main-content").append("<table class=\"table main-table\">\n" +
                "                    <thead>\n" +
                "                      <th scope=\"col\">#</th>\n" +
                "                      <th scope=\"col\">课程名称</th>\n" +
                "                      <th scope=\"col\">课程类别</th>\n" +
                "                      <th scope=\"col\">开课院系</th>\n" +
                "                      <th scope=\"col\">学分</th>\n" +
                "                      <th scope=\"col\">开课时间</th>\n" +
                "                      <th scope=\"col\">开课年级</th>\n" +
                "                      <th scope=\"col\">选必修</th>\n" +
                "                      <th scope=\"col\">授课教师</th>\n" +
                "                      <th scope=\"col\">教师电话</th>\n" +
                "                    </thead>\n" +
                "<tbody></tbody>\n" +
                "                </table>");

            for (var i = 0; i < courses.length; i++){
                $(".main-table tbody").append("<tr id='"+ courses[i].sch_courseId+ "' > \n" +
                    "                        <a href=\"#\"><th scope=\"row\" >" + (i+1) + "</th></a>\n" +
                    "                        <td>" + courses[i].courseName + "</td>\n" +
                    "                        <td>" + courses[i].courseTopicName + "</td>\n" +
                    "                        <td>" + courses[i].courseDepartName + "</td>\n" +
                    "                        <td >" + courses[i].courseCredit + "</td>\n" +
                    "                        <td>" + courses[i].courseStartTime + "</td>\n" +
                    "                        <td>" + courses[i].courseLevel + "</td>\n" +
                    "                        <td>" + courses[i].courseType + "</td>\n" +
                    "                        <td>" + courses[i].teacherRealName + "</td>\n" +
                    "                        <td>" + courses[i].teacherPhoneNumber + "</td>\n" +
                    "<td> <a href=\"#\"  class=\"btn btn-success btn-lg active\" role=\"button\"  aria-pressed=\"true\" onclick='registerCourse(this)'>选课</a>\n</td>" +
                    "<td><a href=\"#\" class=\"btn btn-primary btn-lg active\" role=\"button\" aria-pressed=\"true\" onclick='exitCourse(this)'>退课</a>\n</td>" +
                    "                    </tr>");

            }
        },
    });
}


function courseRegisterAllFunc() {

    $.ajax({
        //请求方式
        type: "GET",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址

        url: "/Register/myRegister",
        // data: {key:value}发送参数
        dataType: "json",
        // dataType后台返回的数据类型
        //请求成功
        success: function (result) {
            var temp = JSON.stringify(result);
            var courses = eval(temp);
            $("tbody tr").remove();
            for (var i = 0; i < courses.length; i++){
                $(".main-table tbody").append("<tr id='"+ courses[i].reg_courseId+ "'> \n" +
                    "                        <a href=\"#\"><th scope=\"row\" >" + (i+1) + "</th></a>\n" +
                    "                        <td>" + courses[i].courseName + "</td>\n" +
                    "                        <td>" + courses[i].courseTopicName + "</td>\n" +
                    "                        <td>" + courses[i].courseDepartName + "</td>\n" +
                    "                        <td >" + courses[i].courseCredit + "</td>\n" +
                    "                        <td>" + courses[i].courseStartTime + "</td>\n" +
                    "                        <td>" + courses[i].courseLevel + "</td>\n" +
                    "                        <td>" + courses[i].courseType + "</td>\n" +
                    "                        <td>" + courses[i].teacherRealName + "</td>\n" +
                    "                        <td>" + courses[i].teacherPhoneNumber + "</td>\n" +
                    "<td><a href=\"#\" class=\"btn btn-primary btn-lg active\" role=\"button\" aria-pressed=\"true\" onclick='exitCourse(this)'>退课</a>\n</td>" +
                    "                    </tr>");
            }

        },
    });
}
function lookgradeFunc() {

    $.ajax({
        //请求方式
        type: "GET",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址

        url: "/Register/myRegister",
        // data: {key:value}发送参数
        dataType: "json",
        // dataType后台返回的数据类型
        //请求成功
        success: function (result) {

            $(".main-table").remove();
            $(".main-content").append("<table class=\"table main-table\">\n" +
                "                    <thead>\n" +
                "                      <th scope=\"col\">#</th>\n" +
                "                      <th scope=\"col\">课程名称</th>\n" +
                "                      <th scope=\"col\">课程类别</th>\n" +
                "                      <th scope=\"col\">开课院系</th>\n" +
                "                      <th scope=\"col\">学分</th>\n" +
                "                      <th scope=\"col\">开课时间</th>\n" +
                "                      <th scope=\"col\">开课年级</th>\n" +
                "                      <th scope=\"col\">选必修</th>\n" +
                "                      <th scope=\"col\">授课教师</th>\n" +
                "                      <th scope=\"col\">教师电话</th>\n" +
                "                      <th scope=\"col\">平时成绩</th>\n" +
                "                      <th scope=\"col\">考试成绩</th>\n" +
                "                      <th scope=\"col\">总成绩</th>\n" +
                "                    </thead>\n" +
                "<tbody></tbody>\n" +
                "                </table>");
            var temp = JSON.stringify(result);
            var courses = eval(temp);
            for (var i = 0; i < courses.length; i++){
                $(".main-table tbody").append("<tr> \n" +
                    "                        <a href=\"#\"><th scope=\"row\" >" + (i+1) + "</th></a>\n" +
                    "                        <td>" + courses[i].courseName + "</td>\n" +
                    "                        <td>" + courses[i].courseTopicName + "</td>\n" +
                    "                        <td>" + courses[i].courseDepartName + "</td>\n" +
                    "                        <td >" + courses[i].courseCredit + "</td>\n" +
                    "                        <td>" + courses[i].courseStartTime + "</td>\n" +
                    "                        <td>" + courses[i].courseLevel + "</td>\n" +
                    "                        <td>" + courses[i].courseType + "</td>\n" +
                    "                        <td>" + courses[i].teacherRealName + "</td>\n" +
                    "                        <td>" + courses[i].teacherPhoneNumber + "</td>\n" +
                    "                        <td>" + courses[i].grade + "</td>\n" +
                    "                        <td>" + courses[i].testScore + "</td>\n" +
                    "                        <td>" + courses[i].finalScore + "</td>\n" +
                    "                    </tr>");
            }
        },
    });
}


