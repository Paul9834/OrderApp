package com.paul9834.orderapp_mvvm

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.paul9834.orderapp_mvvm.R
import com.paul9834.orderapp_mvvm.data.model.Test
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        navController = findNavController(R.id.nav_host_fragment)
        bottom_navigation.setupWithNavController(navController)

        testMock ()

    }

    fun testMock () {

        val test = Test(application)

        Log.e("List", "${test.generateMockList()}")


    }

}