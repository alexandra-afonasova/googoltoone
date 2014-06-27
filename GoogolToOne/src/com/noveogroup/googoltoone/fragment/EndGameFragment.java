package com.noveogroup.googoltoone.fragment;

import android.content.AsyncQueryHandler;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.activity.GameBackgroundFragmentActivity;
import com.noveogroup.googoltoone.activity.StartupActivity;
import com.noveogroup.googoltoone.database.ContentDescriptor;
import com.noveogroup.googoltoone.gamelogic.GameInfo;

public class EndGameFragment extends Fragment {

    GameBackgroundFragmentActivity parentActivity;
    GameInfo gameInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.end_game, container, false);

        parentActivity = (GameBackgroundFragmentActivity) getActivity();

        gameInfo = parentActivity.getGameInfo();

        Button againBtn = (Button) view.findViewById(R.id.again_btn);
        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add new Game to bd
                insertGameToDB( gameInfo );

                Intent intent = new Intent(getActivity(), GameBackgroundFragmentActivity.class);
                startActivity(intent);
            }
        });

        Button enoughBtn = (Button) view.findViewById(R.id.enough_btn);
        enoughBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add new Game to bd
                insertGameToDB( gameInfo );

                Intent intent = new Intent(getActivity(), StartupActivity.class);
                startActivity(intent);
            }
        });

        // check existing Players
        String[] projection = new String[]{ ContentDescriptor.Players.Cols.NAME };
        String selection = ContentDescriptor.Players.Cols.NAME + " = ? OR " + ContentDescriptor.Players.Cols.NAME + " = ?";
        String[] selectionArgs = new String[]{ gameInfo.getPlayerOneName(), gameInfo.getPlayerTwoName() };
        // TODO: how to do this async?
        Cursor cursor = getActivity().getContentResolver().query(ContentDescriptor.Players.TABLE_URI, null, selection, selectionArgs, null);
        if( cursor.getCount() == 0 ){ // both players not existing
            // create raw in table
            // add new Players to db
            insertPlayerToDB(gameInfo.getPlayerOneName());
            insertPlayerToDB(gameInfo.getPlayerTwoName());
        } else if( cursor.getCount() == 1 ){ // only one existing
            // check who
            cursor.moveToFirst();
            if( cursor.getString(1).equals( gameInfo.getPlayerOneName() ) ){
                insertPlayerToDB(gameInfo.getPlayerTwoName());
            } else{
                insertPlayerToDB(gameInfo.getPlayerOneName());
            }
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        String winner;
        TextView congratulations = (TextView) getView().findViewById(R.id.cong_player);
        TextView youWin = (TextView) getView().findViewById(R.id.you_win);
        TextView score = (TextView) getView().findViewById(R.id.score_text_view);

        if(parentActivity.getGameInfo().getCurrentScoreOnePlayer() > parentActivity.getGameInfo().getCurrentScoreTwoPlayer()) {
            winner = parentActivity.getGameInfo().getPlayerOneName();
            congratulations.append(" " + winner);
        }
        else if (parentActivity.getGameInfo().getCurrentScoreOnePlayer() < parentActivity.getGameInfo().getCurrentScoreTwoPlayer()) {
            winner = parentActivity.getGameInfo().getPlayerTwoName();
            congratulations.append(" " + winner);
        }
        else {
            congratulations.setText(R.string.draw_end_game);
            youWin.setText("");
        }

        score.append(" " + parentActivity.getGameInfo().getCurrentScoreOnePlayer() + getString(R.string.colon) + parentActivity.getGameInfo().getCurrentScoreTwoPlayer());

    }

    public static Fragment newInstance() {
        return new EndGameFragment();
    }

    private void insertPlayerToDB(String name){
        ContentValues cvPlayerOne = new ContentValues();
        cvPlayerOne.put(ContentDescriptor.Players.Cols.NAME, name);
        new AsyncQueryHandler(getActivity().getContentResolver()) {
        }.startInsert(1, null, ContentDescriptor.Players.TABLE_URI, cvPlayerOne);
    }

    private void insertGameToDB( GameInfo gameInfo ){
        int idPlayerOne = -1;
        int idPlayerTwo = -1;

        String selection = ContentDescriptor.Players.Cols.NAME + " = ? OR " + ContentDescriptor.Players.Cols.NAME + " = ?";
        String[] selectionArgs = new String[]{ gameInfo.getPlayerOneName(), gameInfo.getPlayerTwoName() };
        // TODO: how to do this async?
        Cursor cursor = getActivity().getContentResolver().query(ContentDescriptor.Players.TABLE_URI, null, selection, selectionArgs, null);

        // definition ids of players
        if( cursor.getCount() != 2 ){
            // TODO: this is bad
        } else{
            cursor.moveToFirst();
            if( cursor.getString(1).equals( gameInfo.getPlayerOneName() ) ) {
                idPlayerOne = Integer.parseInt(cursor.getString(0));
                cursor.moveToNext();
                idPlayerTwo = Integer.parseInt(cursor.getString(0));
            } else{
                idPlayerTwo = Integer.parseInt(cursor.getString(0));
                cursor.moveToNext();
                idPlayerOne = Integer.parseInt(cursor.getString(0));
            }
        }

        // add game to bd
        ContentValues cvNewGame = new ContentValues();
        cvNewGame.put(ContentDescriptor.Games.Cols.ID_PLAYER1, idPlayerOne);
        cvNewGame.put(ContentDescriptor.Games.Cols.ID_PLAYER2, idPlayerTwo);
        cvNewGame.put(ContentDescriptor.Games.Cols.PLAYER1_SCORE, gameInfo.getCurrentScoreOnePlayer() );
        cvNewGame.put(ContentDescriptor.Games.Cols.PLAYER2_SCORE, gameInfo.getCurrentScoreTwoPlayer() );

        new AsyncQueryHandler(getActivity().getContentResolver()) {
        }.startInsert(1, null, ContentDescriptor.Games.TABLE_URI, cvNewGame);
    }
}