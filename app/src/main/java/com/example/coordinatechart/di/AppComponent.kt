package com.example.coordinatechart.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.coordinatechart.ui.MainActivity
import com.example.coordinatechart.ui.result_screen.ResultFragment
import com.example.coordinatechart.ui.start_screen.StartFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {

    fun injectMainActivity(activity: MainActivity)
    fun injectStartFragment(fragment: StartFragment)
    fun injectResultFragment(fragment: ResultFragment)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun bindApplicationContext(applicationContext: Context): Builder
    }
}