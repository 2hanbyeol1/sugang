package org.techtown.finalproject.mainFrame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.finalproject.R;
import org.techtown.finalproject.constants.Constants;
import org.techtown.finalproject.control.CGangjwa;
import org.techtown.finalproject.database.GangjwaDatabase;
import org.techtown.finalproject.valueObject.VGangjwa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PMainFrame extends AppCompatActivity {
    TextView welcomeText;

    Spinner campusSpinner;
    Spinner collegeSpinner;
    Spinner departmentSpinner;

    Toolbar toolbar;

    ListView list;

    AlertDialog.Builder dialog;

    private CGangjwa cGangjwa;

    private String userName;
    private String studentId;
    private String userId;
    private String pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);

        Intent intent = getIntent();
        userName = intent.getStringExtra(Constants.EMainFrame.intentName.getText());
        studentId = intent.getStringExtra(Constants.EMainFrame.intentStudentId.getText());
        userId = intent.getStringExtra(Constants.EMainFrame.intentId.getText());
        pw = intent.getStringExtra(Constants.EMainFrame.intentPw.getText());

        welcomeText = findViewById(R.id.welcome_text);
        welcomeText.setText(userName + Constants.EMainFrame.welcomeText.getText());

        campusSpinner = findViewById(R.id.campus_spinner);
        collegeSpinner = findViewById(R.id.college_spinner);
        departmentSpinner = findViewById(R.id.department_spinner);

        campusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                campusSelect(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        collegeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                collegeSelect(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                departmentSelect(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        toolbar = findViewById(R.id.mainframe_toolbar);
        setSupportActionBar(toolbar);

        list = findViewById(R.id.gangjwa_list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gangjwaSelect(position, userId);
            }
        });

        cGangjwa = new CGangjwa(getApplicationContext());
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mainframe, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int menuId = item.getItemId();
        if(menuId == R.id.myaccount){
            Intent intent = new Intent(this, PAccount.class);
            intent.putExtra(Constants.EMainFrame.intentName.getText(), userName);
            intent.putExtra(Constants.EMainFrame.intentStudentId.getText(), studentId);
            intent.putExtra(Constants.EMainFrame.intentId.getText(), userId);
            intent.putExtra(Constants.EMainFrame.intentPw.getText(), pw);
            startActivity(intent);
        } else if(menuId == R.id.myMiri){
            Intent intent = new Intent(this, PMiri.class);
            intent.putExtra(Constants.EMainFrame.intentId.getText(), userId);
            startActivity(intent);
        } else if(menuId == R.id.mySincheong){
            Intent intent = new Intent(this, PSincheong.class);
            intent.putExtra(Constants.EMainFrame.intentId.getText(), userId);
            startActivity(intent);
        } else if(menuId == R.id.logout){
            Intent intent = new Intent(this, PLoginDialog.class);
            startActivity(intent);
            Toast.makeText(this, Constants.EMainFrame.logout.getText(), Toast.LENGTH_SHORT).show();
        } else if(menuId == R.id.contact){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.EMainFrame.contact.getText()));
            startActivity(intent);
        } else if(menuId == R.id.myiweb){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.EMainFrame.myiweb.getText()));
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    //'뒤로' 버튼 2번 눌러 백그라운드로
    private long lastTimeBackPressed;

    public void onBackPressed() {
        if (System.currentTimeMillis() - lastTimeBackPressed < 1500) {
            moveTaskToBack(true);
            return;
        }
        Toast.makeText(this, Constants.EMainFrame.onBackPressed.getText(), Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }

    public void campusSelect(int position){
        if(campusSpinner.getItemAtPosition(position).equals("1 용인 yongin")){
            ArrayAdapter collegeAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.yongin, android.R.layout.simple_spinner_dropdown_item);
            collegeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            collegeSpinner.setAdapter(collegeAdapter);
        }
        if(campusSpinner.getItemAtPosition(position).equals("2 서울 seoul")) {
            ArrayAdapter collegeAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.seoul, android.R.layout.simple_spinner_dropdown_item);
            collegeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            collegeSpinner.setAdapter(collegeAdapter);
        }
    }

    public void collegeSelect(int position){
        if(collegeSpinner.getItemAtPosition(position).equals("10 교양 generalY")) {
            ArrayAdapter departmentAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.generalY, android.R.layout.simple_spinner_dropdown_item);
            departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            departmentSpinner.setAdapter(departmentAdapter);
        }
        if(collegeSpinner.getItemAtPosition(position).equals("11 공과대학 engineering")) {
            ArrayAdapter departmentAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.engineering, android.R.layout.simple_spinner_dropdown_item);
            departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            departmentSpinner.setAdapter(departmentAdapter);
        }
        if(collegeSpinner.getItemAtPosition(position).equals("12 자연과학대학 science")) {
            ArrayAdapter departmentAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.science, android.R.layout.simple_spinner_dropdown_item);
            departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            departmentSpinner.setAdapter(departmentAdapter);
        }
        if(collegeSpinner.getItemAtPosition(position).equals("13 예술체육대학 art")) {
            ArrayAdapter departmentAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.art, android.R.layout.simple_spinner_dropdown_item);
            departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            departmentSpinner.setAdapter(departmentAdapter);
        }
        if(collegeSpinner.getItemAtPosition(position).equals("14 건축대학 architecture")) {
            ArrayAdapter departmentAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.architecture, android.R.layout.simple_spinner_dropdown_item);
            departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            departmentSpinner.setAdapter(departmentAdapter);
        }
        if(collegeSpinner.getItemAtPosition(position).equals("20 교양 generalS")) {
            ArrayAdapter departmentAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.generalS, android.R.layout.simple_spinner_dropdown_item);
            departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            departmentSpinner.setAdapter(departmentAdapter);
        }
        if(collegeSpinner.getItemAtPosition(position).equals("21 ICT융합대학 ict")) {
            ArrayAdapter departmentAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.ict, android.R.layout.simple_spinner_dropdown_item);
            departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            departmentSpinner.setAdapter(departmentAdapter);
        }
        if(collegeSpinner.getItemAtPosition(position).equals("22 경영대학 business")) {
            ArrayAdapter departmentAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.business, android.R.layout.simple_spinner_dropdown_item);
            departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            departmentSpinner.setAdapter(departmentAdapter);
        }
        if(collegeSpinner.getItemAtPosition(position).equals("23 사회과학대학 social")) {
            ArrayAdapter departmentAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.social, android.R.layout.simple_spinner_dropdown_item);
            departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            departmentSpinner.setAdapter(departmentAdapter);
        }
        if(collegeSpinner.getItemAtPosition(position).equals("24 인문대학 human")) {
            ArrayAdapter departmentAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.human, android.R.layout.simple_spinner_dropdown_item);
            departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            departmentSpinner.setAdapter(departmentAdapter);
        }
        if(collegeSpinner.getItemAtPosition(position).equals("25 법학대학 law")) {
            ArrayAdapter departmentAdapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.law, android.R.layout.simple_spinner_dropdown_item);
            departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            departmentSpinner.setAdapter(departmentAdapter);
        }
    }

    public void departmentSelect(int position){
        if(departmentSpinner.getItemAtPosition(position).equals("101 영어교양 englishYG")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.englishYG)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("102 기초교양 basic")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.basic)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("103 선택교양 selective")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.selective)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("111 전기공학과  electricity")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.electricity)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("112 컴퓨터공학과 computer")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.computer)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("120 수학과 math")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.math)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("121 물리학과 physics")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.physics)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("122 화학과 chemistry")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.chemistry)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("123 식품영양학과 food")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.food)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("124 생명과학정보학과 life")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.life)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("130 디자인학부 design")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.design)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("131 체육학부 physical")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.physical)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("132 음악학부 music")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.music)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("133 바둑학과 baduk")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.baduk)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("134 영화/뮤지컬학부 musical")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.musical)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("140 건축학부 architect")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.architect)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("141 공간디자인전공 basicArchitecture")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.basicArchitecture)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("20 교양 generalS")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.generalS)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("21 ICT융합대학 ict")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.ict)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("22 경영대학 business")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.business)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("23 사회과학대학 social")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.social)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("24 인문대학 human")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.human)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("25 법학대학 law")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.law)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("201 영어교양 englishYG")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.englishYG)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("202 기초교양 basic")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.basic)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("203 선택교양 selective")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.selective)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("211 디지털콘텐츠 digitalContents")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.digitalContents)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("212 융합소프트웨어 software")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.software)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("220 경영학과 management")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.management)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("221 국제통상학과 internationalTrade")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.internationalTrade)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("222 경영정보학과 managementInformation")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.managementInformation)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("223 부동산학과 property")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.property)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("224 경영교육혁신센터 managementEducation")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.managementEducation)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("230 행정학과 administration")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.administration)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("231 경제학과 economy")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.economy)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("232 정치외교학과 politics")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.politics)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("233 디지털미디어학과 digitalMedia")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.digitalMedia)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("234 아동학과 children")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.children)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("235 청소년지도학과 teenager")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.teenager)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("236 사회복지학과 welfare")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.welfare)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("240 국어국문학과 korean")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.korean)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("241 중어중문학과 chinese")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.chinese)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("242 일어일문학과 japanese")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.japanese)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("243 영어영문학과 english")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.english)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("244 사학과 history")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.history)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("245 문헌정보학과 library")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.library)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("246 미술사학과 artHistory")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.artHistory)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("247 철학과 philosophy")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.philosophy)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("248 아랍지역학과 arab")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.arab)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("249 문예창작과 writing")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.writing)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("250 법학과 lawC")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.lawC)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        if(departmentSpinner.getItemAtPosition(position).equals("251 법무정책학과 politics")) {
            List<String> data = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.politics)));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
    public void gangjwaSelect(int position, String userId){
        String selectedGangjwa = (String) list.getItemAtPosition(position);
        String subString1 = selectedGangjwa.substring(selectedGangjwa.indexOf(Constants.EMainFrame.space.getText()) + 1);
        String subString2 = subString1.substring(subString1.indexOf(Constants.EMainFrame.space.getText()) + 1);
        String subString3 = subString2.substring(subString2.indexOf(Constants.EMainFrame.space.getText()) + 1);
        String id = selectedGangjwa.substring(0, selectedGangjwa.indexOf(Constants.EMainFrame.space.getText()));
        String name = subString1.substring(0, subString1.indexOf(Constants.EMainFrame.space.getText()));
        String professor = subString2.substring(0, subString2.indexOf(Constants.EMainFrame.space.getText()));
        String score = subString3.substring(0, subString3.indexOf(Constants.EMainFrame.space.getText()));
        String time = subString3.substring(subString3.indexOf(Constants.EMainFrame.space.getText()) + 1);
        dialog = new AlertDialog.Builder(this);
        dialog.setTitle(Constants.EMainFrame.selectTitle.getText());
        dialog.setMessage(Constants.EMainFrame.selectMessage.getText() + selectedGangjwa);
        dialog.setPositiveButton(Constants.EMainFrame.selectPositiveButton.getText(), new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<VGangjwa> duplicatedGangjwas = cGangjwa.getGangjwas(userId, id, Constants.EMainFrame.sincheongType.getText());
                if(duplicatedGangjwas.size() == 0) {
                    VGangjwa vGangjwa = new VGangjwa();
                    vGangjwa.setUserId(userId);
                    vGangjwa.setSincheongType(Constants.EMainFrame.sincheongType.getText());
                    vGangjwa.setId(id);
                    vGangjwa.setName(name);
                    vGangjwa.setProfessor(professor);
                    vGangjwa.setScore(score);
                    vGangjwa.setTime(time);
                    cGangjwa.save(vGangjwa);
                    Toast.makeText(PMainFrame.this, Constants.EMainFrame.sincheongSuccess.getText(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PMainFrame.this, Constants.EMainFrame.sincheongDuplicated.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setNegativeButton(Constants.EMainFrame.selectNegativeButton.getText(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<VGangjwa> duplicatedGangjwa = cGangjwa.getGangjwas(userId, id, Constants.EMainFrame.miriType.getText());
                if(duplicatedGangjwa.size() == 0) {
                    VGangjwa vGangjwa = new VGangjwa();
                    vGangjwa.setUserId(userId);
                    vGangjwa.setSincheongType(Constants.EMainFrame.miriType.getText());
                    vGangjwa.setId(id);
                    vGangjwa.setName(name);
                    vGangjwa.setProfessor(professor);
                    vGangjwa.setScore(score);
                    vGangjwa.setTime(time);
                    cGangjwa.save(vGangjwa);
                    Toast.makeText(PMainFrame.this, Constants.EMainFrame.miriSuccess.getText(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PMainFrame.this, Constants.EMainFrame.miriDuplicated.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.create();
        dialog.show();
    }
}