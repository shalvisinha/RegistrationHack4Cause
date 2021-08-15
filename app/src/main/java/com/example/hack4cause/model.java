package com.example.hack4cause;

public class model {
    String student_name,Reg_no,vit_mail;

    model(){

    }

    public model(String student_name, String Reg_no, String vit_mail) {
        this.student_name = student_name;
        this.Reg_no = Reg_no;
        this.vit_mail = vit_mail;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getReg_no() {

        return Reg_no;
    }

    public String getVit_mail() {
        return vit_mail;
    }
}
