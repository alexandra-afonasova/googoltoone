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
import com.noveogroup.googoltoone.R;
import com.noveogroup.googoltoone.activity.NextActivity;
import com.noveogroup.googoltoone.activity.StartupActivity;
import com.noveogroup.googoltoone.database.ContentDescriptor;
import com.noveogroup.googoltoone.gamelogic.GameInfo;

public class EndGameFragment extends Fragment {
    private NextActivity parentActivity;

    private Button againBtn;
    private Button enoughBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.end_game, container, false);

        parentActivity = (NextActivity) getActivity();

        GameInfo gameInfo = parentActivity.getGameInfo();

        againBtn = (Button) view.findViewById(R.id.againBtn);
        againBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getActivity(), NextActivity.class);
                startActivity(intent);
            }
        });

        enoughBtn = (Button) view.findViewById(R.id.enoughBtn);
        enoughBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    private void insertPlayerToDB(String name){
        ContentValues cvPlayerOne = new ContentValues();
        cvPlayerOne.put(ContentDescriptor.Players.Cols.NAME, name);
        new AsyncQueryHandler(getActivity().getContentResolver()) {
        }.startInsert(1, null, ContentDescriptor.Players.TABLE_URI, cvPlayerOne);
    }
}