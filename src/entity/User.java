package entity;

import java.io.Serializable;

public class User implements Serializable {    /////////用户信息类////////////////
    private int user_id;                    //用户id
    private String user_username;            //用户名
    private String user_password;            //用户密码
    private String user_sex;                //用户性别
    private int user_score;                    //用户分数
    private String user_grade;                //用户段位

    public User(int user_id, String user_username, String user_password, String user_sex, int user_score, String user_grade) {
        this.user_id = user_id;
        this.user_username = user_username;
        this.user_password = user_password;
        this.user_sex = user_sex;
        this.user_score = user_score;
        this.user_grade = user_grade;
    }

    public User() {

    }

    /////////////////其他方法//////////////
    public String toString() {
        return " id: " + user_id + " username: " + user_username + " password: " + user_password + " sex:" + user_sex + " score:" + user_score + " grade:" + user_grade;
    }

    //////////////////get方法//////////////////
    public int getUser_id() {
        return this.user_id;
    }

    //////////////set方法///////////////
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_username() {
        return this.user_username;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public String getUser_password() {
        return this.user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_sex() {
        return this.user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public int getUser_score() {
        return this.user_score;
    }

    public void setUser_score(int user_score) {
        this.user_score = user_score;
    }

    public String getUser_grade() {
        return this.user_grade;
    }

    public void setUser_grade(String user_grade) {
        this.user_grade = user_grade;
    }
}
