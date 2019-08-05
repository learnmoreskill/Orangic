package com.orangicsmarttechnology.orangic.models



class ServerResponse{


    private val status: String? = null
    private val message: String? = null
    private val acount: String? = null
    private val pcount: String? = null
//    private val principal: Principal? = null
//    private val homework: ArrayList<Homework>? = null
//    private val data: ArrayList<ResponseMap>? = null

    private val data: ArrayList<ResponseMapNotesList>? = null
//    private val msg: ArrayList<Message>? = null
//    private val students: ArrayList<Student>? = null
//    private val attendance: ArrayList<Attendance>? = null
//    private val attendancelog: ArrayList<AttendanceLog>? = null
//    private val homeworknd: ArrayList<HomeworkNotDone>? = null


    fun getStatus(): String {
        return status!!
    }

    fun getMessage(): String {
        return message!!
    }

    fun getAcount(): String {
        return acount!!
    }

    fun getPcount(): String {
        return pcount!!
    }

//    fun getPrincipal(): Principal {
//        return principal!!
//    }
//
//    fun getHomework(): ArrayList<Homework> {
//        return homework!!
//    }

//    fun getData(): ArrayList<ResponseMap> {
//        return data!!
//    }
    fun getNotesList(): ArrayList<ResponseMapNotesList> {
        return data!!
    }

//    fun getMsg(): ArrayList<Message> {
//        return msg!!
//    }
//
//    fun getStudents():ArrayList<Student> {
//        return students!!
//    }
//
//    fun getAttendance():ArrayList<Attendance> {
//        return attendance!!
//    }
//
//    fun getAttendanceLog():ArrayList<AttendanceLog> {
//        return attendancelog!!
//    }
//
//    fun getHomeworkNotDone():ArrayList<HomeworkNotDone> {
//        return homeworknd!!
//    }

}