package model;

/**
 * Created by 90465 on 2018/5/23.
 */
public class User {
    private String userName;
    private char[] passwd;
    private boolean isMale;

    public User(String userName, char[] passwd, boolean isMale) {
        this.userName = userName;
        this.passwd = passwd;
        this.isMale = isMale;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char[] getPasswd() {
        return passwd;
    }

    public void setPasswd(char[] passwd) {
        this.passwd = passwd;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}
