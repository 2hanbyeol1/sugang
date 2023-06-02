package org.techtown.finalproject.dataAccessObject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import org.techtown.finalproject.valueObject.VGangjwa;

import java.util.List;

@Dao
public interface DGangjwa {
    //회원정보 전체 조회
    @Query("SELECT * FROM VGangjwa WHERE USERID = (:userId) AND SINCHEONGTYPE = (:sincheongType)")
    List<VGangjwa> getAll(String userId, String sincheongType);

    @Query("SELECT * FROM VGangjwa WHERE USERID = (:userId) AND ID = (:id) AND SINCHEONGTYPE = (:sincheongType)")
    List<VGangjwa> getByUserIdAndId(String userId, String id, String sincheongType);

    @Insert
    void insert (VGangjwa vGangjwa);

    @Query("DELETE from VGangjwa WHERE USERID = (:userId) AND ID = (:id) AND SINCHEONGTYPE = (:sincheongType) ")
    void deleteByUserIdAndId(String userId, String id, String sincheongType);
}
