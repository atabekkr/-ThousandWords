package com.example.thousandwords.ui

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.thousandwords.R
import com.example.thousandwords.data.Word
import com.example.thousandwords.data.WordDao
import com.example.thousandwords.data.WordDatabase
import com.example.thousandwords.databinding.FragmentGameBinding
import kotlin.random.Random

class GameFragment: Fragment(R.layout.fragment_game) {
    private lateinit var binding: FragmentGameBinding
    private lateinit var db: WordDatabase
    private lateinit var dao: WordDao
    private var currentQuestionId = 0
    private lateinit var b: Button
    private lateinit var list: MutableList<Button>
    private val navArgs: GameFragmentArgs by navArgs()
    private var check: Int = 0
    private lateinit var sound: MediaPlayer
    private lateinit var questions: List<Word>

    @SuppressLint("DiscouragedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameBinding.bind(view)

        db = WordDatabase.getInstance(requireContext())
        dao = db.getWordDao()

        questions = dao.getAllWords().shuffled()

        list = mutableListOf(binding.btnOption1, binding.btnOption2, binding.btnOption3, binding.btnOption4)

        currentQuestionId = 1
        setQuestions()

        binding.apply {
            b = btnOption4

            music.setOnClickListener {
                sound.start()
            }

            progressLinear.max = navArgs.querity.toInt()

            btnOption1.setOnClickListener {
                optionSelected(btnOption1)
            }
            btnOption2.setOnClickListener {
                optionSelected(btnOption2)
            }
            btnOption3.setOnClickListener {
                optionSelected(btnOption3)
            }
            btnOption4.setOnClickListener {
                optionSelected(btnOption4)
            }

            btnSubmit.setOnClickListener {
                when(btnSubmit.text) {
                    getString(R.string.text_submit) -> checkAnswer(b)
                    getString(R.string.text_continue) -> {
                        currentQuestionId++
                        //progressLinear.progress = currentQuestionId - 1
                        setQuestions()
                    }
                    getString(R.string.text_finish) -> {
                        findNavController().navigate(
                            GameFragmentDirections.actionGameFragmentToResultFragment(navArgs.querity, check.toString())
                        )
                    }
                }
            }
        }

    }

    @SuppressLint("SetTextI18n", "DiscouragedApi")
    private fun setQuestions() {
        binding.apply {

            tvResult.text = currentQuestionId.toString() + "/${navArgs.querity}"

            tvQuestion.text = questions[currentQuestionId].word

            var currentAnswer = questions[currentQuestionId].translation
            val answerList: MutableList<String> = dao.getAllAnswers().toMutableList()

            val music1 = binding.root.context.resources.getIdentifier(
                questions[currentQuestionId].word,
                "raw",
                binding.root.context.packageName
            )

            sound = MediaPlayer.create(requireContext(), music1)
            sound.start()

            btnOption1.isEnabled = true
            btnOption2.isEnabled = true
            btnOption3.isEnabled = true
            btnOption4.isEnabled = true
            btnSubmit.isClickable = false

            list.shuffle()
            list.forEach {
                it.text = currentAnswer
                answerList.remove(currentAnswer)
                currentAnswer = answerList[Random.nextInt(1, answerList.size)]
            }

            btnSubmit.text = getString(R.string.text_submit)

            btnOption1.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(requireActivity(), R.color.transparent)
            )
            btnOption2.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(requireActivity(), R.color.transparent)
            )
            btnOption3.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(requireActivity(), R.color.transparent)
            )
            btnOption4.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(requireActivity(), R.color.transparent)
            )
        }
    }

    private fun optionSelected(button: Button) {
        b.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireActivity(), R.color.transparent)
        )

        button.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(requireActivity(), R.color.color_option_selected)
        )

        b = button

        binding.btnSubmit.isClickable = true

    }

    private fun checkAnswer(button: Button) {
        val currentQuestion = questions[currentQuestionId]

        if (currentQuestion.translation == button.text.toString()) {
            check++
            button.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(requireActivity(), R.color.color_right)
            )
        } else {
            button.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(requireActivity(), R.color.color_error)
            )
        }

        list.forEach {
            if (it.text.toString() == currentQuestion.translation){
                it.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(requireActivity(), R.color.color_right)
                )
            }
        }

        binding.apply {
            btnOption1.isEnabled = false
            btnOption2.isEnabled = false
            btnOption3.isEnabled = false
            btnOption4.isEnabled = false

            progressLinear.progress++
        }

        binding.btnSubmit.text = getString(R.string.text_continue)

        if (currentQuestionId == navArgs.querity.toInt()) {
            binding.btnSubmit.text = getString(R.string.text_finish)
        }

    }
}