package group.com.fconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class ForgotPass extends AppCompatActivity {

    EditText txtTDN ;
    EditText txtMKM ;
    EditText txtRMK ;
    Button confirm ;
    HashMap<String, String> list_account;
    HashMap<String, String> list_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        getControl();
        addActivity();
        getAccount();
    }

    private void addActivity() {
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list_account.containsKey(txtTDN.getText().toString())==false)
                {
                    Toast.makeText(ForgotPass.this,"This username is not available!!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                        if(txtMKM.getText().toString().compareTo(txtRMK.getText().toString())==0)
                        {
                            Account account =new Account();
                            account.setUsername(txtTDN.getText().toString());
                            account.setPassword(txtMKM.getText().toString());
                            account.setName(list_name.get(txtTDN.getText().toString()));
                            pushAccount(account);
                            Toast.makeText(ForgotPass.this,"Your password is reset!",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(ForgotPass.this,"The password and repassword are not the same!",Toast.LENGTH_SHORT).show();


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

    private void getControl() {
         txtTDN = findViewById(R.id.txtTenDN);
         txtMKM = findViewById(R.id.txtMKMoi);
         txtRMK = findViewById(R.id.txtReMK);
         confirm = findViewById(R.id.confirm);
    }

    private void getAccount()
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getReference("List_account");

        list_account = new HashMap<String, String>();
        list_name=new HashMap<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot :snapshot.getChildren())
                {
                    Account account=dataSnapshot.getValue(Account.class);
                    if(list_account.containsKey(account.getUsername())==false)
                    {
                        list_name.put(account.getUsername(),account.getName());
                        list_account.put(account.getUsername(),account.password);
                    }

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}