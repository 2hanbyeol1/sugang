package org.techtown.finalproject.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import org.techtown.finalproject.dataAccessObject.DGangjwa;
import org.techtown.finalproject.valueObject.VGangjwa;

@Database(entities = {VGangjwa.class}, version = 2)
public abstract class GangjwaDatabase extends RoomDatabase {
    public abstract DGangjwa dGangjwa();
}
