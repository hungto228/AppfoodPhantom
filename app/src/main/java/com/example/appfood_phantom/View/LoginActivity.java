package com.example.appfood_phantom.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfood_phantom.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.rengwuxian.materialedittext.MaterialEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, FirebaseAuth.AuthStateListener {
    TextView mForgotPassword,mResgitterAccount;
    MaterialEditText mEmail, mPassWord;
    Button btnLogin, btnLoginGoogle;
    LoginButton btnLoginFaceBook;
    GoogleApiClient apiClient;
    public static int REQUESTCODE_SIGNIN_GOOGLE = 99;
    public static int CHECK_PROVIDER_SIGNIN = 0;
    CallbackManager mCallbackManagerFaceBook;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
         mCallbackManagerFaceBook = CallbackManager.Factory.create();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Đăng nhập");
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        auth = FirebaseAuth.getInstance();
        auth.signOut();
//LoginManager.getInstance().logOut();
        mEmail = findViewById(R.id.edt_email);
        mPassWord = findViewById(R.id.edt_password);
        mForgotPassword=findViewById(R.id.tv_forGotPassword);
        mResgitterAccount=findViewById(R.id.tv_resgiterAccount);

        btnLogin = findViewById(R.id.btn_login);
        btnLoginFaceBook = findViewById(R.id.btn_signFaceBook);
        btnLoginFaceBook.setReadPermissions("email", "public_profile");
        btnLoginFaceBook.registerCallback(mCallbackManagerFaceBook, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                CHECK_PROVIDER_SIGNIN = 2;
                String tokenId = loginResult.getAccessToken().getToken();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        btnLoginGoogle = findViewById(R.id.btn_signInGoogle);


        createClientGoogle();
        //onclick
        btnLoginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInGoogle(apiClient);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //login acc
                Toast.makeText(LoginActivity.this, "dang nhap", Toast.LENGTH_SHORT).show();
            }
        });
        mResgitterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, ResgiterActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();


        auth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(this);
    }

    private void signInGoogle(GoogleApiClient apiClient) {
        CHECK_PROVIDER_SIGNIN = 1;

        Intent signinGoogle = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(signinGoogle, REQUESTCODE_SIGNIN_GOOGLE);
    }

    private void authencationFireBase(String tokenId) {

        if (CHECK_PROVIDER_SIGNIN == 1) {
            AuthCredential  authCredential = GoogleAuthProvider.getCredential(tokenId, null);
            auth.signInWithCredential(authCredential);
        } else if (CHECK_PROVIDER_SIGNIN == 2) {
            AuthCredential   authCredential = FacebookAuthProvider.getCredential(tokenId);
            auth.signInWithCredential(authCredential);
        }
    }

    private void createClientGoogle() {

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder().
                requestIdToken(getString(R.string.default_web_client_id)).
                requestEmail().build();
        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .build();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE_SIGNIN_GOOGLE) {
            if (resultCode == RESULT_OK) {
                GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount signInAccount = signInResult.getSignInAccount();
                String tokenID = signInAccount.getIdToken();
                authencationFireBase(tokenID);
            }
        }else {
            mCallbackManagerFaceBook.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(LoginActivity.this, HomePage.class);
            startActivity(intent);
        } else {


        }
    }
}
