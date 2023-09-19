package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.myapplication.databinding.ActivityQuizBinding
import com.example.myapplication.modle.Question
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QuizActivity : AppCompatActivity() {
    private  val binding by lazy {
        ActivityQuizBinding.inflate(layoutInflater)
    }
    var currentQuestion=0
    var score=0

    private lateinit var questionList:ArrayList<Question>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        questionList= ArrayList<Question>()
        var image=intent.getIntExtra("categoryimg",0)
        var catText=intent.getStringExtra("questionType")
        Firebase.firestore.collection("Questions").document(catText.toString())
            .collection("question1").get().addOnSuccessListener {
                questionData->
                questionList.clear()
                for (data in questionData.documents){
                    var question:Question?=data.toObject(Question::class.java)
                    questionList.add(question!!)
                }
                //Log.d("MyTag","onCreate: ${questionList[1].question}")
                if (questionList.size>0){
                    binding.question.text=questionList.get(currentQuestion).question
                    binding.option1.text=questionList.get(currentQuestion).option1
                    binding.option2.text=questionList.get(currentQuestion).option2
                    binding.option3.text=questionList.get(currentQuestion).option3
                    binding.option4.text=questionList.get(currentQuestion).option4

                }

            }
        binding.categoryimg.setImageResource(image)

        binding.option1.setOnClickListener{
            nextQuestionAndScoreUpdate(binding.option1.text.toString())
        }
        binding.option2.setOnClickListener{
            nextQuestionAndScoreUpdate(binding.option2.text.toString())
        }
        binding.option3.setOnClickListener{
            nextQuestionAndScoreUpdate(binding.option3.text.toString())
        }
        binding.option4.setOnClickListener{
            nextQuestionAndScoreUpdate(binding.option4.text.toString())
        }
    }

    private fun nextQuestionAndScoreUpdate(s:String) {
        if (s.equals(questionList.get(currentQuestion).answer)){
            score+=10
            //Toast.makeText(this,score.toString(),Toast.LENGTH_SHORT).show()

        }

        currentQuestion++
        if(currentQuestion>=questionList.size){
            if(score>=20){
                binding.winner.visibility= View.VISIBLE
//                var currentChance=0L
               /* Firebase.database.reference.child("PlayChance").child(Firebase.auth.currentUser!!.uid).addValueEventListener(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        currentChance=snapshot.getValue() as Long
                        Toast.makeText(this@QuizActivity,currentChance.toString(),Toast.LENGTH_SHORT).show()
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })*/
               // Firebase.database.reference.child("PlayChance").child(Firebase.auth.currentUser!!.uid).setValue(currentChance+1)
            }else{
                binding.sorry.visibility=View.VISIBLE
            }
        }
        else{
            binding.question.text=questionList.get(currentQuestion).question
            binding.option1.text=questionList.get(currentQuestion).option1
            binding.option2.text=questionList.get(currentQuestion).option2
            binding.option3.text=questionList.get(currentQuestion).option3
            binding.option4.text=questionList.get(currentQuestion).option4
        }




}
}