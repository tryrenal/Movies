package com.redveloper.movies.ui.testdetail

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.redveloper.movies.R
import com.redveloper.movies.domain.entity.TestModel

class TestDetailActivity : AppCompatActivity() {

    lateinit var tvUserId : TextView
    lateinit var tvId : TextView
    lateinit var tvTitle: TextView
    lateinit var tvDesc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_detail)

        init()
        getData()

    }

    private fun getData(){
        intent.extras?.let{
            it.getInt(KEY_USER_ID).let { userId ->
                tvUserId.text = "user id: $userId"
            }
            it.getInt(KEY_ID).let { id ->
                tvId.text = "id: $id"
            }
            it.getString(KEY_TITLE).let { title ->
                tvTitle.text = "title: $title"
            }
            it.getString(KEY_DESC).let { body ->
                tvDesc.text = "body: $body"
            }
        }
    }

    private fun init(){
        tvUserId = findViewById(R.id.tvUserId)
        tvId = findViewById(R.id.tvId)
        tvTitle = findViewById(R.id.tvTitle)
        tvDesc = findViewById(R.id.tvDesc)
    }

    companion object{
        const val KEY_USER_ID: String = "KEY_USER_ID"
        const val KEY_ID: String = "KEY_ID"
        const val KEY_TITLE: String = "KEY_TITLE"
        const val KEY_DESC: String = "KEY_DESC"

        fun navigation(activity: Activity, model: TestModel){
            val bundle = Bundle()
            bundle.putInt(KEY_USER_ID, model.userId)
            bundle.putInt(KEY_ID, model.id)
            bundle.putString(KEY_TITLE, model.title)
            bundle.putString(KEY_DESC, model.body)
            val intent = Intent(activity, TestDetailActivity::class.java)
            intent.putExtras(bundle)
            activity.startActivity(intent)
        }
    }
}