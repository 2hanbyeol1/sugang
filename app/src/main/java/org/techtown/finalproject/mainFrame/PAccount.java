package org.techtown.finalproject.mainFrame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.finalproject.R;
import org.techtown.finalproject.constants.Constants;
import org.techtown.finalproject.control.CUser;
import org.techtown.finalproject.database.UserDatabase;

public class PAccount extends AppCompatActivity {
    TextView nameText;
    TextView studentIdText;
    TextView idText;
    EditText pwEdit;
    EditText newPwEdit;
    EditText confirmEdit;

    private String name;
    private String studentId;
    private String id;
    private String pw;

    private boolean isPwVisible = false;
    private boolean isNewPwVisible = false;
    private boolean isConfirmVisible = false;

    private CUser cUser;

    AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Intent intent = getIntent();
        name = intent.getStringExtra(Constants.EAccount.intentName.getText());
        studentId = intent.getStringExtra(Constants.EAccount.intentStudentId.getText());
        id = intent.getStringExtra(Constants.EAccount.intentId.getText());
        pw = intent.getStringExtra(Constants.EAccount.intentPw.getText());

        nameText = findViewById(R.id.myname_text);
        studentIdText = findViewById(R.id.mystudentid_text);
        idText = findViewById(R.id.myid_text);
        pwEdit = findViewById(R.id.myoriginalpw_edit);
        newPwEdit = findViewById(R.id.mynewpw_edit);
        confirmEdit = findViewById(R.id.mynewconfirm_edit);

        nameText.setText(name);
        studentIdText.setText(studentId);
        idText.setText(id);

        cUser = new CUser(getApplicationContext());}

    public void onShowPwButtonClicked(View v) {
        //비밀번호 보이기
        if(isPwVisible) {
            pwEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isPwVisible = false;
        } else {
            pwEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isPwVisible = true;
        }
    }

    public void onShowNewPwButtonClicked(View v) {
        //비밀번호 보이기
        if(isNewPwVisible) {
            newPwEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isNewPwVisible = false;
        } else {
            newPwEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isNewPwVisible = true;
        }
    }

    public void onShowNewConfirmButtonClicked(View v) {
        //비밀번호 보이기
        if(isConfirmVisible) {
            confirmEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            isConfirmVisible = false;
        } else {
            confirmEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isConfirmVisible = true;
        }
    }

    public void onChangePwButtonClicked(View view){
        String originalPw = pwEdit.getText().toString();
        String newPw = newPwEdit.getText().toString();
        String newConfirm = confirmEdit.getText().toString();
        if(originalPw.equals(pw)) {
            if (newPw.equals(newConfirm)) {
                pw = newPw;
                cUser.changePw(newPw, id);
                pwEdit.setText(pw);
                Toast.makeText(this, Constants.EAccount.changeSuccess.getText(), Toast.LENGTH_SHORT).show();
                pwEdit.setText(Constants.EAccount.nullEdit.getText());
                newPwEdit.setText(Constants.EAccount.nullEdit.getText());
                confirmEdit.setText(Constants.EAccount.nullEdit.getText());
            } else {
                Toast.makeText(this, Constants.EAccount.confirmFailed.getText(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, Constants.EAccount.pwFailed.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onDeleteAccountButtonClicked(View view){
        dialog = new AlertDialog.Builder(this);
        dialog.setTitle(Constants.EAccount.deleteTitle.getText());
        dialog.setMessage(Constants.EAccount.deletemessage.getText());
        dialog.setPositiveButton(Constants.EAccount.deletePositiveButton.getText(), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cUser.delete(id);
                Toast.makeText(getApplicationContext(), Constants.EAccount.deleteSuccess.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), PLoginDialog.class);
                startActivity(intent);
            }
        });
        dialog.setNegativeButton(Constants.EAccount.deleteNegativeButton.getText(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        dialog.create();
        dialog.show();
    }
}