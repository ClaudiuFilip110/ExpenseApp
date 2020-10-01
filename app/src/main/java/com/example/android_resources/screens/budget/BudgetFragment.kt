package com.example.android_resources.screens.budget

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.android_resources.screens.main.MainActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import org.koin.core.context.GlobalContext.get
import org.koin.core.parameter.parametersOf
import java.time.LocalDateTime
import java.util.*

class BudgetFragment : Fragment(), KoinComponent {
    private val view: BudgetView by inject { parametersOf(this) }
    private val presenter: BudgetPresenter by inject { parametersOf(view, get(), get(), get()) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = view.layout
        view.setClickListeners(v)
        presenter.initGetBalanceUntilDate(Date())
        return v
    }
}