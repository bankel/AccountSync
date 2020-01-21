package com.banks.accountsync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AuthenticatorActivity extends AppCompatActivity {
    public static final String ACCOUNT_TYPE = "com.banks.accountsync.account.type";    // TYPE必须与account_preferences.xml中的TYPE保持一致
    private AccountManager mAccountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticator);

        mAccountManager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
        Account[] accounts = mAccountManager.getAccountsByType(ACCOUNT_TYPE);   // 获取系统帐户列表中已添加的帐户是否存在我们的帐户，用TYPE做为标识
        if (accounts.length > 0) {
            Toast.makeText(this, "已添加当前登录的帐户", Toast.LENGTH_SHORT).show();
            finish();
        }

        Button btnAddAccount = findViewById(R.id.btn_add_account);
        btnAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account(getString(R.string.app_name), ACCOUNT_TYPE);
                mAccountManager.addAccountExplicitly(account, null, null);     // 帐户密码和信息这里用null演示

                finish();
            }
        });
    }
}
