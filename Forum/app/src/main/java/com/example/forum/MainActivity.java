package com.example.forum;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.appevents.AppEventsLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this.getApplication());

        // 初始化 CallbackManager
        callbackManager = CallbackManager.Factory.create();

        // 检查是否已经有用户登录
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if (isLoggedIn) {
            // 用户已登录
            Toast.makeText(MainActivity.this, "已登录", Toast.LENGTH_SHORT).show();
            // 可以在这里继续处理登录后的逻辑，如跳转到主页面
        } else {
            // 用户未登录，设置登录按钮
            setupLoginButton();
        }
    }

    // 设置登录按钮
    private void setupLoginButton() {
        LoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));

        // 注册回调
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // 登录成功处理
                Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                // 登录取消处理
                Toast.makeText(MainActivity.this, "登录取消", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // 登录错误处理
                Toast.makeText(MainActivity.this, "登录失败：" + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 重写 onActivityResult 方法，传递登录结果到 callbackManager
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    // 自定义按钮点击登录的方法
    public void customLogin(View v) {
        // 通过LoginManager进行登录
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
        // 或者在这里添加其他登录处理逻辑
    }

    // 跳转到登录页面的方法
    public void jump(View v) {
        buttonFun();
        Intent loginIntent = new Intent(this, LogIn.class);
        startActivity(loginIntent);
        finish();
    }

    public void buttonFun() {
        Random random = new Random();
    }
}