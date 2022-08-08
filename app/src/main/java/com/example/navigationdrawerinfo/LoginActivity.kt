package com.example.navigationdrawerinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val TAG : String = "jeongmin"

    //firebase Auth
    private lateinit var firebaseAuth: FirebaseAuth

    private val db = FirebaseFirestore.getInstance()    // Firestore 인스턴스 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

/* 일반 함수 */
    private fun signIn(email : String, password : String) {
        firebaseAuth = FirebaseAuth.getInstance()
        if(email.isNotEmpty() && password.isNotEmpty()) {
            firebaseAuth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) {task ->
                    if(task.isSuccessful) {
                        Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                        readDocumentField()
                    } else {
                        Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "이메일과 비밀번호 입력칸을 모두 채워주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    // 파이어베이스 파이어스토어 다큐먼트 필드 읽어오기
    private fun readDocumentField() {
        db.collection("info") // 작업할 컬렉션
            .get() // 문서 가져오기
            .addOnSuccessListener { result ->
                // 성공할 경우
                for(document in result) { // 가져온 문서들은 result 에 들어감
                    if(document["email"] == loginEmail.text.toString()) {
                        val itemName = document["name"] as String
                        val itemEmail = document["email"] as String
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("name", itemName)
                        intent.putExtra("email", itemEmail)
                        startActivity(intent)
                        Log.d(TAG, "if 문 속 : 이름은 $itemName")
                    }
                }
            }
            .addOnFailureListener { exception ->
                // 실패할 경우
                Log.d(TAG, "Error getting documents : $exception")
            }
    }

/* onClick 함수 */
    fun onClickJoin(view: View) {
        val intent = Intent(this, JoinActivity::class.java)
        startActivity(intent)
    }
    fun onClickLogin(view: View) {
        Log.d(TAG, "로그인")
        signIn(loginEmail.text.toString(), loginPW.text.toString())
    }
}