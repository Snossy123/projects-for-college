package com.example.quizapp3;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.view.View;
        import android.widget.Button;
        import android.os.Bundle;
        import android.widget.EditText;
        import android.widget.Toast;

public class Admin extends AppCompatActivity {

    Button button;
    EditText user_name, pass_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        button = findViewById(R.id.button_lod_in);
        user_name = findViewById(R.id.v_user_name);
        pass_word = findViewById(R.id.v_password);

        String username = user_name.getText().toString();
        String password = pass_word.getText().toString();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Enter the User Name", Toast.LENGTH_LONG).show();
                }
                else if(password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Enter the Password", Toast.LENGTH_LONG).show();
                }
                else if(username == "1" && password == "1"){
                    Intent intent = new Intent(Admin.this, AdminPage.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong User Name OR Password", Toast.LENGTH_LONG).show();
                }
            }
        });




    }
}