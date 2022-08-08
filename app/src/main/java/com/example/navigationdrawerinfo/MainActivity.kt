package com.example.navigationdrawerinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.appbar.*
import kotlinx.android.synthetic.main.header.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG : String = "jeongmin"

    lateinit var navigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onCreateToolbar()
    }

/* override 함수 */
    // 툴바 메뉴 버튼이 클릭 됐을 때 실행하는 함수
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // 클릭한 툴바 메뉴 아이템 id 마다 다르게 실행하도록 설정
        when(item!!.itemId){
            android.R.id.home->{
                // 햄버거 버튼 클릭시 네비게이션 드로어 열기
                drawerLayout.openDrawer(GravityCompat.START)
                changeText()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 드로어 내 아이템 클릭 이벤트 처리하는 함수
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_item1-> {
                Toast.makeText(this, "click menu_item1", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_item2-> {
                Toast.makeText(this, "click menu_item2", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_item3-> {
                Toast.makeText(this, "click menu_item3", Toast.LENGTH_SHORT).show()
            }
        }
        return false
    }
/* 일반 함수*/
    // 툴바 생성
    private fun onCreateToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.navi_menu) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게

        // 네비게이션 드로어 생성
        drawerLayout = findViewById(R.id.drawer_layout)

        // 네비게이션 드로어 내에있는 화면의 이벤트를 처리하기 위해 생성
        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this) //navigation 리스너
    }
    // navigation drawer 글자 변경 함수
    private fun changeText() {
        Log.d(TAG, "이름은 ${intent.getStringExtra("name")}")
        Log.d(TAG, "계정은 ${intent.getStringExtra("email")}")

        headerName.text = "${intent.getStringExtra("name")}"
        headerEmail.text = "${intent.getStringExtra("email")}"
    }
}