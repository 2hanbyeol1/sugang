package org.techtown.finalproject.mainFrame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.techtown.finalproject.R;
import org.techtown.finalproject.constants.Constants;
import org.techtown.finalproject.control.CUser;
import org.techtown.finalproject.database.UserDatabase;
import org.techtown.finalproject.valueObject.VUser;

import java.util.List;


public class PLoginDialog extends AppCompatActivity {
    EditText idEdit;
    EditText pwEdit;

    private CUser cUser;

    private boolean isPwVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dialog);

        idEdit = findViewById(R.id.loginid_edit);
        pwEdit = findViewById(R.id.loginpw_edit);

        idEdit.setPrivateImeOptions(Constants.ELoginDialog.idEditOption.getText());

        cUser = new CUser(getApplicationContext());
    }

    public void onLoginButtonClicked(View v){
        String id = idEdit.getText().toString();
        String pw = pwEdit.getText().toString();

        boolean idIsNull = id.equals("");
        boolean pwIsNull = pw.equals("");

        //로그인 정보 입력 여부 확인
        if(idIsNull) {
            Toast.makeText(this, Constants.ELoginDialog.idIsNull.getText(), Toast.LENGTH_SHORT).show();
        } else if(pwIsNull) {
            Toast.makeText(this, Constants.ELoginDialog.pwIsNull.getText(), Toast.LENGTH_SHORT).show();
        } else {
            //로그인
            VUser vUser = cUser.getUserById(id);
            if(vUser == null){ //존재하지 않는 아이디
                Toast.makeText(this, Constants.ELoginDialog.idNotFound.getText(), Toast.LENGTH_SHORT).show();
            } else {
                if(vUser.getPw().equals(pw)){ //로그인 성공
                    Toast.makeText(this, Constants.ELoginDialog.loginSuccess.getText(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, PMainFrame.class);
                    String name = vUser.getName();
                    String studentId = vUser.getStudentId();
                    intent.putExtra(Constants.ELoginDialog.intentName.getText(), name);
                    intent.putExtra(Constants.ELoginDialog.intentStudentId.getText(), studentId);
                    intent.putExtra(Constants.ELoginDialog.intentId.getText(), id);
                    intent.putExtra(Constants.ELoginDialog.intentPw.getText(), pw);
                    startActivity(intent);
                }else{ //아이디와 일치하지 않는 비밀번호
                    Toast.makeText(this, Constants.ELoginDialog.pwNotFound.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void onShowLoginPwButtonClicked(View v){
        //비밀번호 보이기
        if(isPwVisible) {
            pwEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isPwVisible = false;
        } else {
            pwEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isPwVisible = true;
        }
    }

    public void onSignUpButtonClicked(View v){
        //회원가입 창으로
        Intent intent = new Intent(this, PRegistration.class);
        startActivity(intent);
    }

    public void onFindInfoButtonClicked(View v){
        //회원 정보 찾기 창으로
        Intent intent = new Intent(this, PFindInfo.class);
        startActivity(intent);
    }

    //'뒤로' 버튼 2번 눌러 종료
    private long lastTimeBackPressed;

    public void onBackPressed(){
        if(System.currentTimeMillis() - lastTimeBackPressed < 1500){
            moveTaskToBack(true);
            return;
        }
        Toast.makeText(this, Constants.ELoginDialog.onBackPressed.getText(), Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }
}