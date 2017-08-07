package bd.scanner.com.studentmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etUserName, etPassword;
    private static final String USER_NAME = "admin";
    private static final String USER_PASSWORD = "1234";
    private String userName, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etUserPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    userName = etUserName.getText().toString();
                    userPassword = etPassword.getText().toString();

                    if (userName.equals(USER_NAME) && userPassword.equals(USER_PASSWORD)){
                        startActivity(new Intent(SignInActivity.this, AdminActivity.class));
                    }else {
                        Toast.makeText(SignInActivity.this,"Invalid user name and password!",Toast.LENGTH_LONG).show();
                    }

                }catch (NullPointerException e){
                    e.printStackTrace();
                    Toast.makeText(SignInActivity.this,"Please fill all the field!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
