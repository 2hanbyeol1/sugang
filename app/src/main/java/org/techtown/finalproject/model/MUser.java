package org.techtown.finalproject.model;

import android.content.Context;

import androidx.room.Room;

import org.techtown.finalproject.constants.Constants;
import org.techtown.finalproject.database.UserDatabase;
import org.techtown.finalproject.mainFrame.PRegistration;
import org.techtown.finalproject.valueObject.VUser;

import java.util.List;

public class MUser {
    UserDatabase userDatabase;

    public MUser(Context applicationContext) {
        userDatabase = Room.databaseBuilder(applicationContext, UserDatabase.class, Constants.EDatabase.userDatabase.getText()).allowMainThreadQueries().build();
    }

    public void save(VUser vUser){
        userDatabase.dUser().insert(vUser);
    }

    public List<VUser> getUserById(String id) {
        List<VUser> result = userDatabase.dUser().findByUserId(id);
        return result;
    }

    public List<VUser> getUserByStudentId(String studentId) {
        List<VUser> result = userDatabase.dUser().findByStudentId(studentId);
        return result;
    }

    public void update(String newPw, String id) {
        userDatabase.dUser().updateById(newPw, id);
    }

    public void delete(String id) {
        userDatabase.dUser().deleteById(id);
    }
}
