package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    SwitchCompat stAutoPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stAutoPlay =  findViewById(R.id.switch1);
    }
    final List<Integer> player1 = new ArrayList<>();
    final List<Integer> player2 = new ArrayList<>();
    List<Integer> check = player1;
    int player = 1;
    int count = 0;
    int autoPlay = 0;



    public void buOnClick(View view){
        Button buSelected = (Button) view;
        int cellId = 0;


        //checking which button is selected
        switch (buSelected.getId()){
            case R.id.bu1: cellId = 1; break;
            case R.id.bu2: cellId = 2; break;
            case R.id.bu3: cellId = 3; break;
            case R.id.bu4: cellId = 4; break;
            case R.id.bu5: cellId = 5; break;
            case R.id.bu6: cellId = 6; break;
            case R.id.bu7: cellId = 7; break;
            case R.id.bu8: cellId = 8; break;
            case R.id.bu9: cellId = 9; break;

        }

        //updating the move
        playGame(cellId,buSelected);

        boolean switchState = stAutoPlay.isChecked();
        if(switchState)autoPlay = 1;
        else autoPlay = 0;


        if(autoPlay==0)return;
        if(count>=9)return;
        cellId = autoPlay();
        String bid = "bu"+cellId;
        int resID = getResources().getIdentifier(bid, "id", getPackageName());

        playGame(cellId, findViewById(resID));

    }
    int autoPlay(){
        ArrayList<Integer> emptyCells = new ArrayList<>();
        for(int i = 1;i<10;i++){
            if(player1.contains(i)||player2.contains(i))continue;
            emptyCells.add(i);
        }
        Random r = new Random();
        int randomIndex = r.nextInt(emptyCells.size());
        return emptyCells.get(randomIndex);
    }



    public void playGame(int cellId,Button buSelected){
        buSelected.setClickable(false);  //making button clickable once
        count++;                         //keeping track of number of moves

        //deciding move(x or o) based on player
        switch (player){
            case 1:
                buSelected.setBackgroundColor(Color.RED);
                check = player1;
                buSelected.setText("X");
                player1.add(cellId);
                break;
            case 2:
                buSelected.setBackgroundColor(Color.YELLOW);
                buSelected.setText("O");
                player2.add(cellId);

                break;
        }

        check(); //checking for winner
        player = player%2 +1; //updating player


    }
    void check(){
        int winner = 0;

        //deciding which player moved
        if(player==1)check = player1;
        else check = player2;

        //check for win condition
        if(     (check.contains(1)&&check.contains(2)&&check.contains(3))||
                (check.contains(4)&&check.contains(5)&&check.contains(6))||
                (check.contains(7)&&check.contains(8)&&check.contains(9))||
                (check.contains(1)&&check.contains(4)&&check.contains(7))||
                (check.contains(2)&&check.contains(5)&&check.contains(8))||
                (check.contains(3)&&check.contains(6)&&check.contains(9))||
                (check.contains(1)&&check.contains(5)&&check.contains(9))||
                (check.contains(3)&&check.contains(5)&&check.contains(7))
        ){

            winner = player;
        }

        String msg;


        if(winner==0){
            if(count>=9)msg = "Game is draw";  //if count is 9 without winner game is draw
            else return;
        }else{
            msg = "player "+player+" is winner";  //msg to display if won
        }

        Toast toast = new Toast(MainActivity.this);
        toast.setText(msg);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();





    }

    //button to start a new game
    public void buRestartOnClick(View view){
        recreate();
    }

    public void swichOnClick(View view) {

        boolean switchState = stAutoPlay.isChecked();
        if(switchState)autoPlay = 1;
        else autoPlay = 0;

        if(count%2!=0 && count<9){
            int CellId1 = autoPlay();
            String bid = "bu"+CellId1;
            int resID = getResources().getIdentifier(bid, "id", getPackageName());

            playGame(CellId1,findViewById(resID));
        }



    }
}


