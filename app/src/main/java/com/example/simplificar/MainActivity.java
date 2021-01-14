package com.example.simplificar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.simplificar.util.FirebaseConnection;
import com.example.simplificar.util.LibraryClass;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
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
        final BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_agenda, R.id.navigation_opcoes)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);
        navView.setItemIconSize(80);
        setStatusBarBorderRadius(this);

        initViews();
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarBorderRadius(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            Drawable background = activity.getResources().getDrawable(R.drawable.border_top_radius);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(activity.getResources().getColor(android.R.color.white));
            window.setBackgroundDrawable(background);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR + View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
    }

    private void initViews() {
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
            //tvEmail.setText("Email: " + user.getEmail());
            //tvNome.setText("Nome: " + user.getDisplayName());
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

    private void animateClick(View view){
        YoYo.with(Techniques.Pulse)
                .duration(300)
                .repeat(0)
                .playOn(view);
    }
}