package com.erp.distribution;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.erp.distribution.sfa.security_model.FUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.erp.distribution.ActivityLogin.EXTRA_ID";
    public static final String EXTRA_OBJECT = "com.erp.distribution.ActivityLogin.EXTRA_OBJECT";

    protected  FUser itemHeader = new FUser();

    RelativeLayout rellay1, rellay2;


    @BindView(R.id.username)
    EditText editTextUsername;
    @BindView(R.id.password)
    EditText editTextPassword;
    @BindView(R.id.btn_login_now)
    Button btnLoginNow;


    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash

        initialize();

        /**
         * Kebetulan Belum menggunakan Extra Parceable
         */
        Intent intent = getIntent();
//        if (intent.hasExtra(EXTRA_ID)) {
//            setTitle("Add User");
//            FUser note = (FUser) intent.getSerializableExtra(EXTRA_OBJECT);
//            editTextTitle.setText(note.getTitle());
//        } else {
//            setTitle("Edit User");
//        }

    }
    void initialize(){
        btnLoginNow.setOnClickListener( (View v) -> {
            loginNow(v);
        });
    }
    void loginNow(View v) {
        readBinderToItem();

        if (itemHeader.getUsername().toString().isEmpty() || itemHeader.getPassword().trim().isEmpty()) {
            Toast.makeText(this, "Username atau Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, itemHeader.getUsername() + " >> " + itemHeader.getPassword(), Toast.LENGTH_SHORT).show();

        Intent data = new Intent();
        data.putExtra(EXTRA_OBJECT, itemHeader);

        setResult(RESULT_OK, data);
        finish();

    }

    void readBinderToItem(){
        itemHeader.setUsername(editTextUsername.getText().toString());
        itemHeader.setPassword(editTextPassword.getText().toString());
    }


}
