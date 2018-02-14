package edu.misao.login_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText loginEmail, loginPass;
    Button loginBtn;
    TextView logInfo;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        loginEmail = findViewById( R.id.LetEmail );
        loginPass = findViewById( R.id.LetPass );
        loginBtn = findViewById( R.id.LbtnLogin );
        logInfo = findViewById( R.id.LtvInfo );

        loginBtn.setOnClickListener( this );
        logInfo.setOnClickListener( this );
        databaseHelper = new DatabaseHelper( this );
//      databaseHelper = new DatabaseHelper( getApplicationContext() );//こっちでも良い

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.LbtnLogin:

              String email = loginEmail.getText().toString();
              String pass = loginPass.getText().toString();

              if(databaseHelper.login( email,pass )!=null)
              {
                  if(databaseHelper.login( email,pass ).contentEquals( "OK" ))
                  {
                      Intent intent = new Intent( this,MainActivity.class );
                      startActivity( intent );
                      Toast.makeText( this, "Welcome in Home Page", Toast.LENGTH_SHORT ).show();
                  }
              }
              else {
                  Toast.makeText( this, "User Name and Password is incorrect", Toast.LENGTH_SHORT ).show();
              }




                break;
            case R.id.LtvInfo:

                Intent intent = new Intent(this,RegisterActivity.class );
                startActivity( intent );
                break;
        }
    }

}
