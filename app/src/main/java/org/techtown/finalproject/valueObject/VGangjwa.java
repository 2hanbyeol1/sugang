package org.techtown.finalproject.valueObject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "VGangjwa")
public class VGangjwa {
    @PrimaryKey(autoGenerate = true)
    private int key;
    private String userId;
    private String sincheongType;
    private String id;
    private String name;
    private String professor;
    private String score;
    private String time;

    public int getKey() { return key; }

    public void setKey(int key) { this.key = key; }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getSincheongType() { return sincheongType; }

    public void setSincheongType(String sincheongType) { this.sincheongType = sincheongType; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "VGangjwa{" +
                "key=" + key +
                ", userID='" + userId + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", professor='" + professor + '\'' +
                ", score='" + score + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
