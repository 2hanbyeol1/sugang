package org.techtown.finalproject.mainFrame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.techtown.finalproject.R;
import org.techtown.finalproject.constants.Constants;
import org.techtown.finalproject.control.CUser;
import org.techtown.finalproject.database.UserDatabase;
import org.techtown.finalproject.valueObject.VUser;

import java.util.List;

public class PFindInfo extends AppCompatActivity {
    EditText nameEdit;
    EditText studentIdEdit;
    TextView nameText;
    TextView studentIdText;
    TextView infoText;

    private CUser cUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_info);

        nameEdit = findViewById(R.id.findinfoname_edit);
        studentIdEdit = findViewById(R.id.findinfostudentid_edit);

        nameText = findViewById(R.id.findinfoname_text);
        studentIdText = findViewById(R.id.findinfostudentid_text);
        infoText = findViewById(R.id.info_text);

        cUser = new CUser(getApplicationContext());
}

    public void onFindInfoButtonClicked(View v) {
        nameText.setText(Constants.EFindInfo.nullEdit.getText());
        studentIdText.setText(Constants.EFindInfo.nullEdit.getText());

        String name = nameEdit.getText().toString();
        String studentId = studentIdEdit.getText().toString();

        boolean nameIsNull = name.equals(Constants.EFindInfo.nullEdit.getText());
        boolean studentIdIsNull = studentId.equals(Constants.EFindInfo.nullEdit.getText());

        //회원정보 입력 여부 확인
        if(nameIsNull) {
            nameText.setText(Constants.EFindInfo.name.getText());
        }
        if(studentIdIsNull) {
            studentIdText.setText(Constants.EFindInfo.studentId.getText());
        }
        if(!nameIsNull && !studentIdIsNull) {
            //회원정보 찾기
            VUser userInfo = cUser.getUserByStudentId(studentId);
            if(userInfo != null) { //입력한 학번의 회원이 존재하는 경우
                if (userInfo.getName().equals(name)) { //Found (입력한 이름의 회원이 존재하는 경우)
                    infoText.setText(Constants.EFindInfo.id.getText() + userInfo.getId() + Constants.EFindInfo.pw.getText() + userInfo.getPw());
                } else { //입력한 이름의 회원이 존재하지 않는 경우
                    infoText.setText(Constants.EFindInfo.infoNotFound.getText());
                }
            } else { //입력한 학번의 회원이 존재하지 않는 경우
                infoText.setText(Constants.EFindInfo.infoNotFound.getText());
            }
        }
    }
}