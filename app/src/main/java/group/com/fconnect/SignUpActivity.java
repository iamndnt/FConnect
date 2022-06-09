package group.com.fconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    EditText edtUSER,edtPW,edtRPW,edtName;
    Button btnSignUp;
    HashMap<String, String> list_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findID();
        createEvent();
        getAccount();
    }

    private void createEvent() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(list_account.containsKey(edtUSER.getText().toString())==true)
                {
                    Toast.makeText(SignUpActivity.this,"This username has used by another people!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(edtPW.getText().toString().compareTo(edtRPW.getText().toString())==0)
                    {
                        Account account =new Account();
                        account.setName(edtName.getText().toString());
                        account.setUsername(edtUSER.getText().toString());
                        account.setPassword(edtPW.getText().toString());
                        pushAccount(account);

                        Toast.makeText(SignUpActivity.this,"Sign up successfully!",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(SignUpActivity.this,"The repassword and password are not the same!",Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        });
    }

    private void pushAccount(Account account)
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("List_account");
        myRef.child(String.valueOf(account.getUsername())).setValue(account);
    }

    private void getAccount()
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("List_account");

        list_account = new HashMap<String, String>();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot :snapshot.getChildren())
                {
                    Account account=dataSnapshot.getValue(Account.class);
                    if(list_account.containsKey(account.getUsername())==false)
                    {

                        list_account.put(account.getUsername(),account.password);
                    }

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void findID(){
        edtUSER = findViewById(R.id.edt_usernameDK);
        edtPW = findViewById(R.id.edt_PW);
        edtRPW = findViewById(R.id.edt_rePW);
        edtName = findViewById(R.id.edt_name);
        btnSignUp = findViewById(R.id.btn_signup);
    }
}