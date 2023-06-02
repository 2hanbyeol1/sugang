package org.techtown.finalproject.valueObject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Vector;

@Entity(tableName = "VUser")
public class VUser {
    @PrimaryKey(autoGenerate = true)
    private int key;
    private String name;
    private String studentId;
    private String id;
    private String pw;

    public void setKey(int key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    @Override
    public String toString() {
        return "VUser{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", studentId='" + studentId + '\'' +
                ", id='" + id + '\'' +
                ", password='" + pw + '\'' +
                '}';
    }
}
