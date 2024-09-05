package com.example.forum;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class) // Only needed when mixing JUnit 3 and 4 tests
@LargeTest // Optional runner annotation
public class LogInTest {

    @Rule
    public ActivityScenarioRule<LogIn> activityScenarioRule =
            new ActivityScenarioRule<>(LogIn.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testLoginAndNavigateToMainPage() {
        // 输入用户名“1”
        onView(withId(R.id.input_account)).perform(typeText("1"));

        // 输入密码“1”
        onView(withId(R.id.input_password)).perform(typeText("1"));

        // 点击登录按钮
        onView(withId(R.id.button_login)).perform(click());

        // 检查是否启动了Main_Page活动
        intended(hasComponent(Main_Page.class.getName()));
    }
}
