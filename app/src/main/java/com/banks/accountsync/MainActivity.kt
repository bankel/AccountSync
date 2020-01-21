package com.banks.accountsync

import android.accounts.Account
import android.accounts.AccountManager
import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "MainActivity"

    }
    val ACCOUNT_TYPE =
        "com.banks.accountsync.account.type" // TYPE必须与account_preferences.xml中的TYPE保持一致

    private lateinit var mAccountManager: AccountManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (BuildConfig.DEBUG) {
            Log.v(TAG, "onCreate")

        }
        mAccountManager = getSystemService(Context.ACCOUNT_SERVICE) as AccountManager
        val accounts: Array<Account> =
            mAccountManager.getAccountsByType(ACCOUNT_TYPE) // 获取系统帐户列表中已添加的帐户是否存在我们的帐户，用TYPE做为标识

        if (accounts.size > 0) {
            Toast.makeText(this, "已添加当前登录的帐户", Toast.LENGTH_SHORT).show()
        } else {
            val account =
                Account(getString(R.string.app_name), ACCOUNT_TYPE)
            mAccountManager.addAccountExplicitly(account, null, null) // 帐户密码和信息这里用null演示
            Toast.makeText(this, "添加帐户", Toast.LENGTH_SHORT).show()

            // 自动同步
            val bundle = Bundle()
            ContentResolver.setIsSyncable(account, AccountProvider.AUTHORITY, 1)
            ContentResolver.setSyncAutomatically(account, AccountProvider.AUTHORITY,true)
            ContentResolver.addPeriodicSync(account, AccountProvider.AUTHORITY,bundle, 30)

        }
    }
}
