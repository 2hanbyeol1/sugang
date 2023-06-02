package org.techtown.finalproject.control;

import android.content.Context;

import org.techtown.finalproject.model.MGangjwa;
import org.techtown.finalproject.valueObject.VGangjwa;

import java.util.List;

public class CGangjwa {
    private Context applicationContext;
    private MGangjwa mGangjwa;

    public CGangjwa(Context applicationContext) {
        this.applicationContext = applicationContext;
        this.mGangjwa = new MGangjwa(applicationContext);
    }

    public List<VGangjwa> getGangjwas(String userId, String id, String type) {
        return this.mGangjwa.getGangjwas(userId, id, type);
    }

    public void save(VGangjwa vGangjwa) {
        this.mGangjwa.save(vGangjwa);
    }

    public List<VGangjwa> getGangjwas(String userId, String type) {
        return this.mGangjwa.getGangjwas(userId, type);
    }

    public void delete(String userId, String id, String type) {
        this.mGangjwa.delete(userId, id, type);
    }
}
