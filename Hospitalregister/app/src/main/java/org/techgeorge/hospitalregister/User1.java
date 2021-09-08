package org.techgeorge.hospitalregister;

/**
 * user class to keep track of registered user
 * and their data (profile info)
 */

public class User1 {

    private String email;
    private String fullName;
    private String profession;
    private String workplace;
    private String phone;
    private String password;
    private String sex;
    private String fci;
    private String aci;
    private String doctor;


    public User1() {}

    public User1(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public User1(String fullName, String email, String profession,
                 String workplace, String phone, String fci, String aci, String doctor) {
        this.email = email;
        this.fullName = fullName;
        this.profession = profession;
        this.workplace = workplace;
        this.phone = phone;
        this.aci=aci;
        this.fci=fci;
        this.doctor=doctor;
    }

/*    public HashMap<String, Object> getAsMap(){
        HashMap<String, Object> userAsMap = new HashMap<>();
        userAsMap.put("username",username);
        userAsMap.put("password",password);
        userAsMap.put("age",age);
        userAsMap.put("name",name);

        //Add or remove more key value pair

        return userAsMap;
    }*/

    public String getSex() { return sex; }

    public void setpassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setWorkplace(String wordplace) {
        this.workplace = wordplace;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProfession() {
        return profession;
    }

    public String getWorkplace() {
        return workplace;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setSex(String sex) { this.sex = sex; }

    public String getpassword() {
        return password;
    }
    public String getFci() {
        return fci;
    }

    public void setFci(String fci) {
        this.fci = fci;
    }

    public String getAci() {
        return aci;
    }

    public void setAci(String aci) {
        this.aci = aci;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
