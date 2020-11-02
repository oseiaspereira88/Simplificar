package com.example.vemcar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vemcar.util.FirebaseConnection;
import com.example.vemcar.util.LibraryClass;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseBD;
    private FirebaseUser user;
    private TextView tvEmail, tvNome;
    private Button bLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        initViews();
    }

    private void initViews() {
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvNome = (TextView) findViewById(R.id.tvNome);
        bLogout = (Button) findViewById(R.id.bLogout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initFirebaseAndFacebook();
        mAuth = FirebaseConnection.getFirebaseAuth();
        user = FirebaseConnection.getFirebaseUser();
        checkUser();
    }

    private void initFirebaseAndFacebook(){
        firebaseBD = LibraryClass.getFirebaseDB();
        mAuth = FirebaseConnection.getFirebaseAuth();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }

    private void checkUser() {
        if(user==null){
            finish();
        } else{
            tvEmail.setText("Email: " + user.getEmail());
            tvNome.setText("Nome: " + user.getDisplayName());
            bLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   logout();
                }
            });
        }
    }

    public void logout(){
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        //googleSignInClient.signOut();
        finish();
    }
}