package com.raju.socialplatform.android.activities

import android.os.Bundle
import com.raju.socialplatform.R
import com.raju.socialplatform.android.activities.base.BaseActivity
import com.raju.socialplatform.android.fragments.PostFragment
import dagger.android.DaggerApplication

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = Bundle()
        val fragment = PostFragment()
        fragment.arguments = bundle
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, fragment)
        ft.commit()
    }
}
