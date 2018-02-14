package edu.misao.login_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText userName, userPass, userEmail, userPhone;
    Button register;
    TextView regiInfo;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );

        userName = findViewById( R.id.RetName );
        userPass = findViewById( R.id.RetPass );
        userEmail = findViewById( R.id.RetEmail );
        userPhone = findViewById( R.id.RetPhone );

        register = findViewById( R.id.RbtnRegister );
        regiInfo = findViewById( R.id.RtvInfo );

        register.setOnClickListener( this );
        regiInfo.setOnClickListener( this );

        databaseHelper = new DatabaseHelper( this );
//        databaseHelper = new DatabaseHelper( getApplicationContext() );//こっちでも良い
    }


    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.RbtnRegister:

                String name = userName.getText().toString();
                String pass = userPass.getText().toString();
                String email = userEmail.getText().toString();
                String phone = userPhone.getText().toString();

                databaseHelper.insertData( name,pass,email,phone );

                Toast.makeText( this, "Data Inserted", Toast.LENGTH_SHORT ).show();

                Intent intent = new Intent( getApplicationContext(),LoginActivity.class );
                startActivity( intent );



                break;



            case R.id.RtvInfo:

                Intent intent1 = new Intent( getApplicationContext(),LoginActivity.class );
                startActivity( intent1 );
                break;

        }
    }
}
