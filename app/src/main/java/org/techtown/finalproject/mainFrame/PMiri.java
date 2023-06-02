package org.techtown.finalproject.mainFrame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.techtown.finalproject.R;
import org.techtown.finalproject.constants.Constants;
import org.techtown.finalproject.control.CGangjwa;
import org.techtown.finalproject.database.GangjwaDatabase;
import org.techtown.finalproject.valueObject.VGangjwa;

import java.util.ArrayList;
import java.util.List;

public class PMiri extends AppCompatActivity {
    ListView list;

    AlertDialog.Builder dialog;

    private List<String> data;
    private ArrayAdapter<String> adapter;
    private String userId;

    private CGangjwa cGangjwa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miri);

        Intent intent = getIntent();
        this.userId = intent.getStringExtra(Constants.EMiri.intentId.getText());

        this.list = findViewById(R.id.miri_list);

        this.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                miriSelect(position, userId);
            }
        });

        this.cGangjwa = new CGangjwa(getApplicationContext());

        this.data = new ArrayList<>();
        this.adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.data);
        this.list.setAdapter(this.adapter);
        update();
    }

    public void update(){
        this.data.clear();
        List<VGangjwa> gangjwa = this.cGangjwa.getGangjwas(this.userId, Constants.EMiri.miriType.getText());
        for(int i = 0; i < gangjwa.size(); i++){
            this.data.add(
                    gangjwa.get(i).getId() + Constants.EMiri.space.getText()
                            + gangjwa.get(i).getName() + Constants.EMiri.space.getText()
                            + gangjwa.get(i).getProfessor() + Constants.EMiri.space.getText()
                            + gangjwa.get(i).getScore() + Constants.EMiri.space.getText()
                            + gangjwa.get(i).getTime());
        }
        this.adapter.notifyDataSetChanged();
    }

    public void miriSelect(int position, String userId){
        String selectedGangjwa = (String) list.getItemAtPosition(position);
        String subString1 = selectedGangjwa.substring(selectedGangjwa.indexOf(Constants.EMiri.space.getText()) + 1);
        String subString2 = subString1.substring(subString1.indexOf(Constants.EMiri.space.getText()) + 1);
        String subString3 = subString2.substring(subString2.indexOf(Constants.EMiri.space.getText()) + 1);
        String id = selectedGangjwa.substring(0, selectedGangjwa.indexOf(Constants.EMiri.space.getText()));
        String name = subString1.substring(0, subString1.indexOf(Constants.EMiri.space.getText()));
        String professor = subString2.substring(0, subString2.indexOf(Constants.EMiri.space.getText()));
        String score = subString3.substring(0, subString3.indexOf(Constants.EMiri.space.getText()));
        String time = subString3.substring(subString3.indexOf(Constants.EMiri.space.getText()) + 1);
        this.dialog = new AlertDialog.Builder(this);
        this.dialog.setTitle(Constants.EMiri.selectTitle.getText());
        this.dialog.setMessage(Constants.EMiri.selectMessage.getText() + selectedGangjwa);
        this.dialog.setPositiveButton(Constants.EMiri.selectPositiveButton.getText(), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                VGangjwa vGangjwa = new VGangjwa();
                vGangjwa.setUserId(userId);
                vGangjwa.setSincheongType(Constants.EMiri.sincheongType.getText());
                vGangjwa.setId(id);
                List<VGangjwa> duplicatedGangjwa = cGangjwa.getGangjwas(userId, id, Constants.EMiri.sincheongType.getText());
                if(duplicatedGangjwa.size() == 0){
                    vGangjwa.setName(name);
                    vGangjwa.setProfessor(professor);
                    vGangjwa.setScore(score);
                    vGangjwa.setTime(time);
                    cGangjwa.save(vGangjwa);
                    cGangjwa.delete(userId, id, Constants.EMiri.miriType.getText());
                    update();
                    Toast.makeText(PMiri.this, Constants.EMiri.sincheongSuccess.getText(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PMiri.this, Constants.EMiri.sincheongDuplicated.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setNegativeButton(Constants.EMiri.selectNegativeButton.getText(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cGangjwa.delete(userId, id, Constants.EMiri.miriType.getText());
                update();
                Toast.makeText(PMiri.this, Constants.EMiri.deleteSuccess.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        this.dialog.create();
        this.dialog.show();
    }
}