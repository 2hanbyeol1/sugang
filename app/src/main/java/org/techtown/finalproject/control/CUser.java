package org.techtown.finalproject.control;

import android.content.Context;

import org.techtown.finalproject.model.MUser;
import org.techtown.finalproject.valueObject.VUser;

import java.util.List;

public class CUser {
    private Context applicationContext;
    private MUser mUser;

    public CUser(Context applicationContext) {
        this.applicationContext = applicationContext;
        this.mUser = new MUser(applicationContext);
    }

    public VUser getUserByStudentId(String studentId) {
        List<VUser> vUsers = mUser.getUserByStudentId(studentId);
        if(vUsers.size() == 0){
            return null;
        }
        return vUsers.get(0);
    }

    public VUser getUserById(String id) {
        List<VUser> vUsers = mUser.getUserById(id);
        if(vUsers.size() == 0){
            return null;
        }
        return vUsers.get(0);
    }

    public void save(VUser vUser){
        mUser.save(vUser);
    }

    public void changePw(String newPw, String id) {
        mUser.update(newPw, id);
    }

    public void delete(String id) {
        mUser.delete(id);
    }

    public boolean checkIdDuplication(String id) {
        VUser vUser = getUserById(id);
        if(vUser != null){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkStudentIdDuplication(String studentId) {
        VUser vUser = getUserByStudentId(studentId);
        if(vUser != null){
            return true;
        } else {
            return false;
        }
    }
}
