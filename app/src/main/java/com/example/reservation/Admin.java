package com.example.reservation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Admin extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private GoogleApiClient googleApiClient;
    public LinearLayout LinLayout;
    public GoogleSignInOptions gso;
    public TextView Name11;
    public TextView Email11;
    public ImageView pImage;
    public NavigationView navigationView;
    public Context context = this;
    public FirebaseAuth mAuth;
    public String TAG;
    public DrawerLayout drawer;
    public NavController navController;
    public View headerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Name11 = findViewById(R.id.name);
        Email11 = findViewById(R.id.email);
        pImage = (ImageView) findViewById(R.id.profile_pic);
        Toolbar toolbar = findViewById(R.id.toolbafa);


        setSupportActionBar(toolbar);
        clientGoogleSignInBuilder();
        buildNavControllerView();


    }


    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();

            navigationView = findViewById(R.id.nav_view);
            headerView = navigationView.getHeaderView(0);
            TextView name12 = (TextView) headerView.findViewById(R.id.name);
            TextView email12 = (TextView) headerView.findViewById(R.id.email);
            ImageView pImage = (ImageView) headerView.findViewById(R.id.profile_pic);
            Button btn = (Button) headerView.findViewById(R.id.button3);
            Uri img_url = Objects.requireNonNull(account).getPhotoUrl();
            name12.setText(account.getDisplayName());
            email12.setText(account.getEmail());
            Glide.with(context)
                    .load(img_url)
                    .into(pImage);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                      @Override
                      public void onResult(@NonNull Status status) {
                          if (status.isSuccess()) {
                              gotoMainActivity();
                              Log.v(TAG, "Signing out -- 2");
                          } else {
                              Toast.makeText(context, "Log Out Failed", Toast.LENGTH_SHORT).show();
                          }
                      }
                  });
                }
            });

        } else {
            gotoMainActivity();
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    handleSignInResult(result);
                }
            });
        }
    }

    private void gotoMainActivity() {
        startActivity(new Intent(Admin.this, MainActivity.class));
        finish();
    }
    public void clientGoogleSignInBuilder() {
        gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("1017587694323-v7bco03j2gpllbrbc05nvi4huhf26gsu.apps.googleusercontent.com")
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
    }
    public void buildNavControllerView(){
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_reserve, R.id.nav_slideshow, R.id.nav_maps, R.id.nav_allreserves)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

}
