package edu.misao.login_system;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button showButton,deleteBtn;
    TextView showTextView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        showButton = findViewById( R.id.LshowBtn );
        showTextView = findViewById( R.id.tvShow );
        deleteBtn = findViewById( R.id.LdeleteBtn );

        showButton.setOnClickListener( this );
        deleteBtn.setOnClickListener( this );

        databaseHelper = new DatabaseHelper( this );

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.LshowBtn:


                Cursor cursor = databaseHelper.getAllData();
                if (cursor != null) {
                    cursor.moveToFirst();
                    do {
                        String id = cursor.getString( cursor.getColumnIndexOrThrow( "id" ) );
                        String name = cursor.getString( cursor.getColumnIndexOrThrow( "name" ) );
                        String password = cursor.getString( cursor.getColumnIndexOrThrow( "password" ) );
                        String email = cursor.getString( cursor.getColumnIndexOrThrow( "email" ) );
                        String phone = cursor.getString( cursor.getColumnIndexOrThrow( "phone" ) );

                        showTextView.append( id + " " + name + " " + password + " " + email + " " + phone + "\n" );
                    } while (cursor.moveToNext());
                } else {
                    Toast.makeText( this, "No Data", Toast.LENGTH_SHORT ).show();

                }
                break;

            case R.id.LdeleteBtn:

                 databaseHelper.deleteAll();
                 break;



        }

    }
}
