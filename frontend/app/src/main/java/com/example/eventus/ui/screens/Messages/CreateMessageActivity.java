package com.example.eventus.ui.screens.Messages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.example.eventus.R;
import com.example.eventus.data.Database;
import com.example.eventus.data.ServerSideException;
import com.example.eventus.data.model.UserDisplay;
import com.example.eventus.data.model.UserMessage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CreateMessageActivity extends AppCompatActivity {

    private UserDisplay user;
    private UserDisplay[] other_users;

    private String defaultTitle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
        if (getIntent() == null) {
            Intent res = new Intent();
            res.putExtra("message", "null intent given");
            setResult(Activity.RESULT_CANCELED, res);
            this.finish();
            return;
        }

        Intent intent = getIntent();

        if (intent.getExtras() == null) {
            Intent res = new Intent();
            res.putExtra("message", "intent missing bundle");
            setResult(Activity.RESULT_CANCELED, res);
            this.finish();
            return;
        }

        Bundle args = intent.getExtras();

        if (!args.containsKey("user") || !args.containsKey("other_users")) {
            Intent res = new Intent();
            res.putExtra("message", "args missing 'user' or 'other_users' fields");
            setResult(Activity.RESULT_CANCELED, res);
            this.finish();
            return;
        }

        this.user = (UserDisplay) args.getSerializable("user");
        this.other_users = (UserDisplay[]) args.getSerializable("other_users");
        this.defaultTitle = args.getString("title", "");

        CreateMessageFragment createMessageFragment = new CreateMessageFragment(this);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_create_message, createMessageFragment)
                .commit();


    }

    public UserDisplay getUser() {
        return user;
    }

    public UserDisplay[] getOtherUsers() {
        return other_users;
    }

    public String getDefaultTitle() {
        return this.defaultTitle;
    }
    public Intent sendMessage(String title, String content){
        Intent res = new Intent();
        try {
            List<String> ids = Arrays.stream(this.other_users).map(UserDisplay::get_id).collect(Collectors.toList());
            Database.sendMessage(this.user.get_id(), ids, title, content);
            res.putExtra("code",Activity.RESULT_OK);

        } catch (ServerSideException e) {
            res.putExtra("code",e.getReturnCode());
            res.putExtra("message",e.getMessage());


        } catch (Exception e) {
            res.putExtra("code",Activity.RESULT_CANCELED);
            res.putExtra("message",e.getMessage());
        }

        return res;
    }

    public void success(){
        this.setResult(Activity.RESULT_OK);
        this.finish();
    }

    public void backButtonClick(View view) {
        // Navigate back
        this.setResult(Activity.RESULT_OK);
        this.finish();
    }
}
