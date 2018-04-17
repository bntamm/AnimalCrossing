package com.example.tam.animalcrossing;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView view;
    CheckBox checkbox1,checkbox2,checkbox3;
    SeekBar seekbar1,seekbar2,seekbar3;
    ImageButton nutplay;
    int soDiem = 100;   //Tạo 1 biến với 100 score

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        view.setText(soDiem + ""); //Show cái 100 score ra ngoài textview

        final CountDownTimer countDownTimer = new CountDownTimer(50000, 200) {
            @Override
            public void onTick(long millisUntilFinished) {
                int number = 3; //Tạo 1 biến random
                Random random = new Random();
                int one     = random.nextInt(number);   //biến này là để lấy 1 con số random từ 0-3 rồi + vào giá trị seekbar hiện tại
                int two     = random.nextInt(number);
                int three   = random.nextInt(number);

                if(seekbar1.getProgress() >= seekbar1.getMax()){
                    this.cancel();  //Khi seekbar1 chạy tới max thì tất cả seekbar ngừng lại ko chạy nữa
                    Toast.makeText(MainActivity.this, "One Win", Toast.LENGTH_SHORT).show();
                    nutplay.setVisibility(View.VISIBLE);    //Nút play hiện lên khi 1 con nào đó Win
                    //Kiểm tra đặt cược
                    if(checkbox1.isChecked()){
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "+10 Score", Toast.LENGTH_SHORT).show();
                    }else{
                        soDiem -= 10;
                        Toast.makeText(MainActivity.this, "-10 Score", Toast.LENGTH_SHORT).show();
                    }
                    view.setText(soDiem + "");
                    EnableCheckBox();   //Là khi chạy xong + or - điểm rồi thì checkbox sẽ bật lên lại vì checkbox đã bị tắt khi bắt đầu play
                }else{
                    if(seekbar2.getProgress() >= seekbar2.getMax()){
                        this.cancel();  //Khi seekbar2 chạy tới max thì tất cả seekbar ngừng lại ko chạy nữa
                        Toast.makeText(MainActivity.this, "Two Win", Toast.LENGTH_SHORT).show();
                        nutplay.setVisibility(View.VISIBLE);   //Nút play hiện lên khi 1 con nào đó Win
                        if(checkbox2.isChecked()){
                            soDiem += 10;
                            Toast.makeText(MainActivity.this, "+10 Score", Toast.LENGTH_SHORT).show();
                        }else{
                            soDiem -= 10;
                            Toast.makeText(MainActivity.this, "-10 Score", Toast.LENGTH_SHORT).show();
                        }
                        view.setText(soDiem + "");
                        EnableCheckBox();
                    }else{
                        if(seekbar3.getProgress() >= seekbar3.getMax()){
                            this.cancel();  //Khi seekbar3 chạy tới max thì tất cả seekbar ngừng lại ko chạy nữa
                            Toast.makeText(MainActivity.this, "Three Win", Toast.LENGTH_SHORT).show();
                            nutplay.setVisibility(View.VISIBLE);   //Nút play hiện lên khi 1 con nào đó Win
                            if(checkbox3.isChecked()){
                                soDiem += 10;
                                Toast.makeText(MainActivity.this, "+10 Score", Toast.LENGTH_SHORT).show();
                            }else{
                                soDiem -= 10;
                                Toast.makeText(MainActivity.this, "-10 Score", Toast.LENGTH_SHORT).show();
                            }
                            view.setText(soDiem + "");
                            EnableCheckBox();
                        }}}

                seekbar1.setProgress(seekbar1.getProgress() + one);
                seekbar2.setProgress(seekbar2.getProgress() + two);
                seekbar3.setProgress(seekbar3.getProgress() + three);
            }
            @Override
            public void onFinish() {
            }};


        nutplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if(soDiem <= 0){    //Game sẽ ngừng , ko nhấn được nút play ẩn đi khi điểm còn 0
                        Toast.makeText(MainActivity.this, "0 Score", Toast.LENGTH_SHORT).show();
                    }else{
                        if(checkbox1.isChecked() || checkbox2.isChecked() || checkbox3.isChecked()){ //Cái này là để kiểm tra xem phải chọn 1 cái checkbox thì mới chạy đc
                            seekbar1.setProgress(0);    //set cho seekbar về 0 khi nhấn lại vào nút play
                            seekbar2.setProgress(0);
                            seekbar3.setProgress(0);
                            nutplay.setVisibility(View.INVISIBLE); //Làm cho nút play ẩn đi khi nhấn vào
                            countDownTimer.start();
                            DisableCheckBox();  //Lúc nhấn play chạy rồi thì các checkbox sẽ tắt ko nhấn đc nữa
                            DisableSeekBar();   //Vô hiệu hóa thanh Seekbar ko cho tác động vào khi đang chạy
                    }else{
                            Toast.makeText(MainActivity.this, "Please select ", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });
        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //Kiểm tra chọn 1 checkbox trong 3 checkbox, chọn cái này thì cái kia bỏ chọn
                    //Bỏ check thằng checkbox2 và checkbox3
                    checkbox2.setChecked(false);
                    checkbox3.setChecked(false);
                }}});

        checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //Bỏ check thằng checkbox1 và checkbox3
                    checkbox1.setChecked(false);
                    checkbox3.setChecked(false);
                }}});

        checkbox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //Bỏ check thằng checkbox1 và checkbox2
                    checkbox1.setChecked(false);
                    checkbox2.setChecked(false);
                }}});
    }


    private void DisableSeekBar(){  //Vô hiệu hóa seekbar ko cho tác động vào khi đang chạy
        seekbar1.setEnabled(false);
        seekbar2.setEnabled(false);
        seekbar3.setEnabled(false);
    }
    private void EnableCheckBox(){  //Cho các checkbox bật lên lại khi đã chạy xong
        checkbox1.setEnabled(true);
        checkbox2.setEnabled(true);
        checkbox3.setEnabled(true);
    }
    private void DisableCheckBox(){ //Cho các checkbox tắt đi (Vô hiệu hóa checkbox) khi bắt đầu chạy
        checkbox1.setEnabled(false);
        checkbox2.setEnabled(false);
        checkbox3.setEnabled(false);
    }
    private void AnhXa(){
        view        = (TextView) findViewById(R.id.hinh100);
        checkbox1   = (CheckBox) findViewById(R.id.mot);
        checkbox2   = (CheckBox) findViewById(R.id.hai);
        checkbox3   = (CheckBox) findViewById(R.id.ba);
        seekbar1    = (SeekBar) findViewById(R.id.thu1);
        seekbar2    = (SeekBar) findViewById(R.id.thu2);
        seekbar3    = (SeekBar) findViewById(R.id.thu3);
        nutplay     = (ImageButton) findViewById(R.id.buttonplay);
    }
}


