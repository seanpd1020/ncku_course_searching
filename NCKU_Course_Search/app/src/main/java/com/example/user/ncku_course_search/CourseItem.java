package com.example.user.ncku_course_search;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 2019/5/17.
 */

public class CourseItem implements Parcelable{
    String dep_name;
    String dep_code;
    String no;
    String course_system_code;
    String class_code;
    String class_no;
    String grade;
    String category;
    String group;
    String en_teaching;
    String course_name;
    String compulsory;
    String credit;
    String teacher_name;
    String selected_seats;
    String remain_seats;
    String times;
    String classroom;
    String remarks;
    String restricted_condition;
    String exp_involved;
    String course_attr_code;
    String cross_domain;
    String moocs;
    public CourseItem(String dep_name,String dep_code,String no,String course_system_code,String class_code,String class_no,String grade,String category
            ,String group,String en_teaching,String course_name,String compulsory,String credit,String teacher_name,String selected_seats
            ,String remain_seats,String times,String classroom,String remarks,String restricted_condition,String exp_involved,String course_attr_code
    ,String cross_domain,String moocs){
        this.dep_name = dep_name;this.dep_code = dep_code;this.no = no;this.course_system_code = course_system_code;this.class_code = class_code;
        this.class_no = class_no;this.grade = grade;this.category = category;this.group = group;this.en_teaching = en_teaching;
        this.course_name = course_name;this.compulsory = compulsory;this.credit = credit;this.teacher_name = teacher_name;this.selected_seats = selected_seats;
        this.remain_seats = remain_seats;this.times = times;this.classroom = classroom;this.remarks = remarks;this.restricted_condition = restricted_condition;
        this.exp_involved = exp_involved;this.course_attr_code = course_attr_code;this.cross_domain = cross_domain;this.moocs = moocs;
    }

    protected CourseItem(Parcel in) {
        dep_name = in.readString();
        dep_code = in.readString();
        no = in.readString();
        course_system_code = in.readString();
        class_code = in.readString();
        class_no = in.readString();
        grade = in.readString();
        category = in.readString();
        group = in.readString();
        en_teaching = in.readString();
        course_name = in.readString();
        compulsory = in.readString();
        credit = in.readString();
        teacher_name = in.readString();
        selected_seats = in.readString();
        remain_seats = in.readString();
        times = in.readString();
        classroom = in.readString();
        remarks = in.readString();
        restricted_condition = in.readString();
        exp_involved = in.readString();
        course_attr_code = in.readString();
        cross_domain = in.readString();
        moocs = in.readString();
    }

    public static final Creator<CourseItem> CREATOR = new Creator<CourseItem>() {
        @Override
        public CourseItem createFromParcel(Parcel in) {
            return new CourseItem(in);
        }

        @Override
        public CourseItem[] newArray(int size) {
            return new CourseItem[size];
        }
    };

    public void setDep_name(String dep_name){this.dep_name = dep_name;}
    public void setDep_code(String dep_code){this.dep_code = dep_code;}
    public void setNo(String no){this.no = no;}
    public void setCourse_system_code(String course_system_code){this.course_system_code = course_system_code;}
    public void setClass_code(String class_code){this.class_code = class_code;}
    public void setClass_no(String class_no){this.class_no = class_no;}
    public void setGrade(String grade){this.grade = grade;}
    public void setCategory(String category){this.category = category;}
    public void setGroup(String group){this.group = group;}
    public void setEn_teaching(String en_teaching){this.en_teaching = en_teaching;}
    public void setCourse_name(String course_name){this.course_name = course_name;}
    public void setCompulsory(String compulsory){this.compulsory = compulsory;}
    public void setCredit(String credit){this.credit = credit;}
    public void setTeacher_name(String teacher_name){this.teacher_name = teacher_name;}
    public void setSelected_seats(String selected_seats){this.selected_seats = selected_seats;}
    public void setRemain_seats(String remain_seats){this.remain_seats = remain_seats;}
    public void setTimes(String time){this.times = times;}
    public void setClassroom(String classroom){this.classroom = classroom;}
    public void setRemarks(String remarks){this.remarks = remarks;}
    public void setRestricted_condition(String restricted_condition){this.restricted_condition = restricted_condition;}
    public void setCourse_attr_code(String course_attr_code){this.course_attr_code = course_attr_code;}
    public void setExp_involved(String exp_involved){this.exp_involved = exp_involved;}
    public void setCross_domain(String cross_domain){this.cross_domain = cross_domain;}
    public void setMoocs(String moocs){this.moocs = moocs;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dep_name);
        parcel.writeString(dep_code);
        parcel.writeString(no);
        parcel.writeString(course_system_code);
        parcel.writeString(class_code);
        parcel.writeString(class_no);
        parcel.writeString(grade);
        parcel.writeString(category);
        parcel.writeString(group);
        parcel.writeString(en_teaching);
        parcel.writeString(course_name);
        parcel.writeString(compulsory);
        parcel.writeString(credit);
        parcel.writeString(teacher_name);
        parcel.writeString(selected_seats);
        parcel.writeString(remain_seats);
        parcel.writeString(times);
        parcel.writeString(classroom);
        parcel.writeString(remarks);
        parcel.writeString(restricted_condition);
        parcel.writeString(exp_involved);
        parcel.writeString(course_attr_code);
        parcel.writeString(cross_domain);
        parcel.writeString(moocs);
    }
}
