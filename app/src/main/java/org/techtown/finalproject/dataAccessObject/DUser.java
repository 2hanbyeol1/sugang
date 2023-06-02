package org.techtown.finalproject.dataAccessObject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.techtown.finalproject.valueObject.VUser;

import java.util.List;
@Dao
public interface DUser {
    //회원정보 전체 조회
    @Query("SELECT * FROM VUser")
    List<VUser> getAll();
    //id를 통해 회원정보 조회(0명/1명)
    @Query("SELECT * FROM VUser WHERE id IN (:id)")
    List<VUser> findByUserId(String id);
    //studentId를 통해 회원정보 조회(0명/1명)
    @Query("SELECT * FROM VUser WHERE studentId IN (:studentId)")
    List<VUser> findByStudentId(String studentId);
    //key를 통해 회원정보 조회(0명/1명)
    @Query("SELECT * FROM VUser WHERE `key` IN (:key)")
    List<VUser> findByKey(int key);
    //회원정보 넣기
    @Insert
    void insert (VUser vUser);
    //회원정보 전체 수정하기
    @Update
    void update (VUser vUser);
    @Query("UPDATE VUser SET pw = (:pw) WHERE id = (:id)")
    void updateById(String pw, String id);
    //회원정보 전체 삭제하기
    @Delete
    void delete (VUser vUser);
    @Query("DELETE from VUser WHERE id = (:id)")
    void deleteById(String id);
}
