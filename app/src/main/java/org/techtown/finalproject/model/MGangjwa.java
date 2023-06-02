package org.techtown.finalproject.model;

import android.content.Context;

import androidx.room.Room;

import org.techtown.finalproject.constants.Constants;
import org.techtown.finalproject.database.GangjwaDatabase;
import org.techtown.finalproject.valueObject.VGangjwa;

import java.util.List;

public class MGangjwa {
    GangjwaDatabase gangjwaDatabase;

    public MGangjwa(Context applicationContext) {
        this.gangjwaDatabase = Room.databaseBuilder(applicationContext, GangjwaDatabase.class, Constants.EDatabase.gangjwaDatabase.getText()).allowMainThreadQueries().build();
    }

    public List<VGangjwa> getGangjwas(String userId, String id, String type){
        return this.gangjwaDatabase.dGangjwa().getByUserIdAndId(userId, id, type);
    }

    public List<VGangjwa> getGangjwas(String userId, String type) {
        return this.gangjwaDatabase.dGangjwa().getAll(userId, type);
    }

    public void save(VGangjwa vGangjwa) {
        this.gangjwaDatabase.dGangjwa().insert(vGangjwa);
    }

    public void delete(String userId, String id, String type) {
        this.gangjwaDatabase.dGangjwa().deleteByUserIdAndId(userId, id, type);
    }
}
