package com.orangicsmarttechnology.orangic.models


class ServerRequest{

    private var operation:String?=null
    private var type:String?=null



//    private var principal:Principal?=null
//    private var homework:Homework?=null
//    private var menulist:MenuList?=null
//    private var message:Message?=null
    private var fetchType:Int?= null
//    private var student:Student?=null
//    private var attendance:Attendance?=null
//    private var attendancelog:AttendanceLog?=null

    fun setOperation(operation:String){
        this.operation=operation
    }

    fun setType(type:String){
        this.type=type
    }

//    fun setPrincipal(principal: Principal){
//        this.principal=principal
//    }
//
//    fun setHomework(homework: Homework){
//        this.homework=homework
//    }
//
//    fun setMenuList(menuList: MenuList){
//        this.menulist=menulist
//    }
//
//    fun setMessage(message: Message){
//        this.message=message
//    }

    fun setFetchType(fetchType:Int){
        this.fetchType=fetchType
    }

//    fun setStudent(student: Student){
//        this.student=student
//    }
//
//    fun setAttendance(attendance: Attendance){
//        this.attendance=attendance
//    }
//
//    fun setAttendanceLog(attendancelog: AttendanceLog){
//        this.attendancelog=attendancelog
//    }

}