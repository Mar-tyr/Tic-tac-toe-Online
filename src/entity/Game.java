package entity;

import java.io.Serializable;

/**
 * Created by 90465 on 2018/6/1.
 */
public class Game implements Serializable {
    private int id;                             //id
    private int user1Id;                        //先手玩家id
    private int user2Id;                        //后手玩家id
    private String boardcode;                   //棋局码
    private int currentUser;                    //当前应下子的玩家  1代表user1  2代表user2

    public Game() {

    }

    public Game(int id, int user1Id, int user2Id, String boardcode, int currentUser) {
        this.id = id;
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.boardcode = boardcode;
        this.currentUser = currentUser;
    }

    @Override
    public String toString() {
        return "user1:" + user1Id + " user2:" + user2Id + " boardcode:" + boardcode + " currentUser" + currentUser;
    }

    public int getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(int currentUser) {
        this.currentUser = currentUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(int user1Id) {
        this.user1Id = user1Id;
    }

    public int getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(int user2Id) {
        this.user2Id = user2Id;
    }

    public String getBoardcode() {
        return boardcode;
    }

    public void setBoardcode(String boardcode) {
        this.boardcode = boardcode;
    }

}
