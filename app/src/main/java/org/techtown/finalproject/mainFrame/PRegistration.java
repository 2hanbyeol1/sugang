package org.techtown.finalproject.mainFrame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.finalproject.R;
import org.techtown.finalproject.constants.Constants;
import org.techtown.finalproject.control.CUser;
import org.techtown.finalproject.database.UserDatabase;
import org.techtown.finalproject.valueObject.VUser;

import java.util.List;

public class PRegistration extends AppCompatActivity {
    EditText nameEdit;
    EditText studentIdEdit;
    EditText idEdit;
    EditText pwEdit;
    EditText confirmEdit;

    TextView nameText;
    TextView studentIdText;
    TextView idText;
    TextView pwText;
    TextView confirmText;

    CheckBox checkBox;

    private boolean isPwVisible = false;
    private boolean isConfirmVisible = false;

    private CUser cUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nameEdit = findViewById(R.id.registername_edit);
        studentIdEdit = findViewById(R.id.registerstudentid_edit);
        idEdit = findViewById(R.id.registerid_edit);
        pwEdit = findViewById(R.id.registerpw_edit);
        confirmEdit = findViewById(R.id.registerconfirm_edit);

        nameText = findViewById(R.id.registername_text);
        studentIdText = findViewById(R.id.registerstudentid_text);
        idText = findViewById(R.id.registerid_text);
        pwText = findViewById(R.id.registerpw_text);
        confirmText = findViewById(R.id.registerconfirm_text);

        checkBox = findViewById(R.id.agree_checkbox);

        idEdit.setPrivateImeOptions(Constants.ERegistration.idEditOption.getText());

        cUser = new CUser(getApplicationContext());
    }

    public void onRegisterButtonClicked(View v){
        nameText.setText(Constants.ERegistration.nullEdit.getText());
        studentIdText.setText(Constants.ERegistration.nullEdit.getText());
        idText.setText(Constants.ERegistration.nullEdit.getText());
        pwText.setText(Constants.ERegistration.nullEdit.getText());
        confirmText.setText(Constants.ERegistration.nullEdit.getText());

        String name = nameEdit.getText().toString();
        String studentId = studentIdEdit.getText().toString();
        String id = idEdit.getText().toString();
        String pw = pwEdit.getText().toString();
        String confirm = confirmEdit.getText().toString();

        boolean nameIsNull = name.equals(Constants.ERegistration.nullEdit.getText());
        boolean studentIdIsNull = studentId.equals(Constants.ERegistration.nullEdit.getText());
        boolean idIsNull = id.equals(Constants.ERegistration.nullEdit.getText());
        boolean pwIsNull = pw.equals(Constants.ERegistration.nullEdit.getText());
        boolean confirmDoesntEqual = !pw.equals(confirm);

        //가입정보 입력 여부 확인
        if(nameIsNull) {
            nameText.setText(Constants.ERegistration.name.getText());
        }
        if(studentIdIsNull) {
            studentIdText.setText(Constants.ERegistration.studentId.getText());
        }
        if(idIsNull) {
            idText.setText(Constants.ERegistration.id.getText());
        }
        if(pwIsNull) {
            pwText.setText(Constants.ERegistration.pw.getText());
        }
        //비밀번호 확인
        if(confirmDoesntEqual) {
            confirmText.setText(Constants.ERegistration.confirm.getText());
        }
        if(!nameIsNull && !studentIdIsNull && !idIsNull && !pwIsNull && !confirmDoesntEqual) {
            boolean idDuplication = cUser.checkIdDuplication(id);
            boolean studentIdDuplication = cUser.checkStudentIdDuplication(studentId);
            if(idDuplication) {
                idText.setText(Constants.ERegistration.idDuplication.getText());
            }
            if(studentIdDuplication) {
                studentIdText.setText(Constants.ERegistration.studentIdDuplication.getText());
            }
            if(!idDuplication && !studentIdDuplication) {
                //개인정보 수집 동의 (체크 여부 확인)
                boolean isChecked = checkBox.isChecked();
                if(isChecked){
                    Toast.makeText(this, Constants.ERegistration.registerSuccess.getText(), Toast.LENGTH_SHORT).show();
                    VUser vUser = new VUser();
                    vUser.setName(name);
                    vUser.setStudentId(studentId);
                    vUser.setId(id);
                    vUser.setPw(pw);
                    cUser.save(vUser);
                    Intent intent = new Intent(this, PLoginDialog.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(this, Constants.ERegistration.needAgreement.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void onShowRegisterPwButtonClicked(View v) {
        //비밀번호 보이기
        if(isPwVisible) {
            pwEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isPwVisible = false;
        } else {
            pwEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isPwVisible = true;
        }
    }

    public void onShowRegisterConfirmButtonClicked(View v) {
        //비밀번호 확인 보이기
        if(isConfirmVisible) {
            confirmEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isConfirmVisible = false;
        } else {
            confirmEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isConfirmVisible = true;
        }
    }
}