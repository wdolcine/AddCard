package com.example.addcard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit
import kotlin.math.max

class MainActivity : AppCompatActivity() {
    private var currentCardDisplayedIndex = 0
    private lateinit var flashcardDatabase: FlashcardDatabase
    private var allFlashcards = mutableListOf<Flashcard>()
    private lateinit var leftOutAnim : Animation
    private lateinit var rightInAnim : Animation
    private lateinit  var countDownTimer: CountDownTimer
    private var remainingTimeInMillis : Long = 1000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = this.findViewById<TextView>(R.id.flashcard_answer)
        val flashcardAnswer1 = findViewById<TextView>(R.id.flashcard_answer1)
        val flashcardAnswer2 = findViewById<TextView>(R.id.flashcard_answer2)
        val timer            = findViewById<TextView>(R.id.counterclock)
        val konfettiView = findViewById<KonfettiView>(R.id.konfettiView)


        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )

        flashcardAnswer.setOnClickListener  {
            flashcardAnswer.setBackgroundColor(resources.getColor(R.color.green,null))
            flashcardAnswer1.setBackgroundColor(resources.getColor(R.color.pinK,null))
            flashcardAnswer2.setBackgroundColor(resources.getColor(R.color.pinK,null))
            konfettiView.start(party)
        }
        flashcardAnswer1.setOnClickListener {
            flashcardAnswer1.setBackgroundColor(resources.getColor(R.color.red,null))
            flashcardAnswer.setBackgroundColor(resources.getColor(R.color.pinK,null))
            flashcardAnswer2.setBackgroundColor(resources.getColor(R.color.pinK,null))
        }
        flashcardAnswer2.setOnClickListener {
            flashcardAnswer2.setBackgroundColor(resources.getColor(R.color.red,null))
            flashcardAnswer.setBackgroundColor(resources.getColor(R.color.pinK,null))
            flashcardAnswer1.setBackgroundColor(resources.getColor(R.color.pinK,null))
        }

        countDownTimer = object:CountDownTimer(16000,remainingTimeInMillis){
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                timer.text = "" + millisUntilFinished / remainingTimeInMillis
            }
            override fun onFinish() {

            }
        }

        leftOutAnim = AnimationUtils.loadAnimation(this, R.anim.left_out)
        rightInAnim = AnimationUtils.loadAnimation(this, R.anim.right_in)


        rightInAnim.setAnimationListener(object : Animation.AnimationListener{

            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
            }
            override fun onAnimationRepeat(animation: Animation?) {}

        })


        leftOutAnim.setAnimationListener(object : Animation.AnimationListener{

            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
            }
            override fun onAnimationRepeat(animation: Animation?) {}

        })


        val crossMain = findViewById<View>(R.id.imageButton3)
        val editbtn   = findViewById<View>(R.id.imageButton)
        val deletebtn   = findViewById<View>(R.id.imageButton4)
        val nextBtn    = findViewById<View>(R.id.imageButton1)

        val timer            = findViewById<TextView>(R.id.counterclock)
        val konfettiView = findViewById<KonfettiView>(R.id.konfettiView)


        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )

        flashcardAnswer.setOnClickListener  {
            flashcardAnswer.setBackgroundColor(resources.getColor(R.color.green,null))
            flashcardAnswer1.setBackgroundColor(resources.getColor(R.color.pinK,null))
            flashcardAnswer2.setBackgroundColor(resources.getColor(R.color.pinK,null))
            konfettiView.start(party)
        }
        flashcardAnswer1.setOnClickListener {
            flashcardAnswer1.setBackgroundColor(resources.getColor(R.color.red,null))
            flashcardAnswer.setBackgroundColor(resources.getColor(R.color.pinK,null))
            flashcardAnswer2.setBackgroundColor(resources.getColor(R.color.pinK,null))
        }
        flashcardAnswer2.setOnClickListener {
            flashcardAnswer2.setBackgroundColor(resources.getColor(R.color.red,null))
            flashcardAnswer.setBackgroundColor(resources.getColor(R.color.pinK,null))
            flashcardAnswer1.setBackgroundColor(resources.getColor(R.color.pinK,null))
        }

        countDownTimer = object:CountDownTimer(16000,remainingTimeInMillis){
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                timer.text = "" + millisUntilFinished / remainingTimeInMillis
            }
            override fun onFinish() {
                nextBtn.performClick()

            }
        }

        leftOutAnim = AnimationUtils.loadAnimation(this, R.anim.left_out)
        rightInAnim = AnimationUtils.loadAnimation(this, R.anim.right_in)


        rightInAnim.setAnimationListener(object : Animation.AnimationListener{

            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
            }
            override fun onAnimationRepeat(animation: Animation?) {}

        })


        leftOutAnim.setAnimationListener(object : Animation.AnimationListener{

            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
            }
            override fun onAnimationRepeat(animation: Animation?) {}

        })


        flashcardDatabase = FlashcardDatabase(this)
        flashcardDatabase.initFirstCard()
        allFlashcards = flashcardDatabase.getAllCards().toMutableList()

        if(allFlashcards.size > 0){

            startTimer()

            flashcardQuestion.text = allFlashcards[0].question
            flashcardAnswer.text = allFlashcards[0].answer
            flashcardAnswer1.text = allFlashcards[0].wrongAnswer1
            flashcardAnswer2.text = allFlashcards[0].wrongAnswer2

        }


        nextBtn.setOnClickListener {
            currentCardDisplayedIndex = getRandomNumber(0,allFlashcards.size-1)

            if (allFlashcards.isEmpty()) {
                return@setOnClickListener
            }
            displayFlashCards(currentCardDisplayedIndex)
            currentCardDisplayedIndex++


            if (currentCardDisplayedIndex >= allFlashcards.size) {

                currentCardDisplayedIndex = 0
                Snackbar.make(findViewById(R.id.imageButton4), "No more cards!!", Snackbar.LENGTH_SHORT).show()

            }


            val (question, answer,wrongAnswer1,wrongAnswer2) = allFlashcards[currentCardDisplayedIndex]

            flashcardQuestion.text = question
            flashcardAnswer.text = answer
            flashcardAnswer1.text = wrongAnswer1
            flashcardAnswer2.text = wrongAnswer2

        }

        deletebtn.setOnClickListener {
            val currentQuestion = flashcardQuestion.text.toString()
            flashcardDatabase.deleteCard(currentQuestion)

            allFlashcards = flashcardDatabase.getAllCards().toMutableList()

            // Vérifier s'il reste des cartes
            if (allFlashcards.isNotEmpty()) {
                // Afficher la carte précédente (si disponible)
                currentCardDisplayedIndex = max(0, currentCardDisplayedIndex - 1)

                val (question, answer,wrongAnswer1,wrongAnswer2) = allFlashcards[currentCardDisplayedIndex]
                flashcardQuestion.text = question
                flashcardAnswer.text = answer
                flashcardAnswer1.text = wrongAnswer1
                flashcardAnswer2.text = wrongAnswer2



            } else {
                // S'il n'y a plus de cartes, afficher un état vide
                flashcardQuestion.text = ""
                flashcardAnswer.text = ""
                flashcardAnswer1.text = ""
                flashcardAnswer2.text = ""


            }
            flashcardAnswer2.setBackgroundColor(resources.getColor(R.color.pinK,null))
            flashcardAnswer.setBackgroundColor(resources.getColor(R.color.pinK,null))
            flashcardAnswer1.setBackgroundColor(resources.getColor(R.color.pinK,null))
        }



        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data: Intent? = result.data

            if (result.resultCode == RESULT_OK && data != null) {
                val question = data.getStringExtra("question")
                val answer = data.getStringExtra("answer")
                val wrongAnswer1 = data.getStringExtra("wrongAnswer1")
                val wrongAnswer2 = data.getStringExtra("wrongAnswer2")

                // Mettre à jour les TextView dans MainActivity avec les nouvelles données
                if (question != null && answer != null) {
                    flashcardDatabase.insertCard(Flashcard(question,answer,wrongAnswer1,wrongAnswer2))

                    flashcardQuestion.text = question
                    flashcardAnswer.text = answer
                    flashcardAnswer1.text = wrongAnswer1
                    flashcardAnswer2.text = wrongAnswer2

                } else {
                    Log.e("TAG", "Missing question or answer to input into database. Question is $question and answer is $answer")
                }

            } else {
                Log.i("AddCardActivity", "Save operation cancelled or no data returned")
            }
        }
        editbtn.setOnClickListener {
            val question = this.findViewById<TextView>(R.id.flashcard_question).text.toString()
            val answer = findViewById<TextView>(R.id.flashcard_answer).text.toString()
            val wrongAnswer1 = this.findViewById<TextView>(R.id.flashcard_answer1).text.toString()
            val wrongAnswer2 = this.findViewById<TextView>(R.id.flashcard_answer2).text.toString()

            val intent = Intent(this, AddCard::class.java)
            intent.putExtra("question", question)
            intent.putExtra("answer", answer)
            intent.putExtra("wrongAnswer1", wrongAnswer1)
            intent.putExtra("wrongAnswer2", wrongAnswer2)
            resultLauncher.launch(intent)
        }

        crossMain.setOnClickListener {
            val intent = Intent(this, AddCard::class.java)
            resultLauncher.launch(intent)
            overridePendingTransition(R.anim.right_in, R.anim.left_out)

        }

    }

    private fun getRandomNumber(minNumber: Int, maxNumber: Int): Int {
        return (minNumber..maxNumber).random() // generated random from 0 to 10 included
    }
    private fun displayFlashCards(index: Int){

        startTimer()

        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = this.findViewById<TextView>(R.id.flashcard_answer)
        val flashcardAnswer1 = findViewById<TextView>(R.id.flashcard_answer1)
        val flashcardAnswer2 = findViewById<TextView>(R.id.flashcard_answer2)

        flashcardAnswer2.setBackgroundColor(resources.getColor(R.color.pinK,null))
        flashcardAnswer.setBackgroundColor(resources.getColor(R.color.pinK,null))
        flashcardAnswer1.setBackgroundColor(resources.getColor(R.color.pinK,null))

        flashcardQuestion.startAnimation(rightInAnim)
        flashcardAnswer.startAnimation(rightInAnim)
        flashcardAnswer1.startAnimation(rightInAnim)
        flashcardAnswer2.startAnimation(rightInAnim)


    }
    private fun startTimer() {
        countDownTimer?.cancel()
        countDownTimer?.start()
    }
}