package com.redveloper.movies.ui.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.redveloper.movies.MyApp
import com.redveloper.movies.R
import com.redveloper.movies.ui.ViewModelFactory
import javax.inject.Inject

class TestActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var etSearch: EditText
    lateinit var testAdapter: TestAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel: TestViewModel by viewModels { viewModelFactory }

    fun inject(){
        (application as MyApp).applicationComponent.inject(this)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        inject()
        init()

        getData()

        etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH)
                true
            else
                false
        }
    }

    private fun getData(){
        viewModel.getData()
        viewModel.testDataEvent.observe(this){ data ->
            data.contentIfNotHaveBeenHandle?.let {
                testAdapter.setData(it)
                recyclerView.adapter = testAdapter
            }
        }
        
        viewModel.showErrorEvent.observe(this){ data ->
            data.contentIfNotHaveBeenHandle?.let { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun init(){
        testAdapter = TestAdapter()

        recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        etSearch = findViewById<EditText>(R.id.edtSearch)
    }
}