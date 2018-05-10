package Model;

import java.util.Calendar;

public class Member {
    private String id = null;// 아이디
    private String pw = null;// 비밀번호
    private String name = null;// 이름
    private String gender = null;// 성별
    private String birth = null;// 생년월일
    private String address = null;// 주소
    private String phoneNumber = null;// 연락처

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phoneNumber;
    }

    public void setPhone_number(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public String getcurrentDate() {
        return (Calendar.getInstance().getTime().toString());
    }
}