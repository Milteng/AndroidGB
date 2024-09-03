package com.example.m7_quiz_fragments

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.m7_quiz_fragments.databinding.FragmentWelcomeBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!



    private val rotationX = PropertyValuesHolder.ofFloat(View.ROTATION_X,0f, 360f)
    private val rotation = PropertyValuesHolder.ofFloat(View.ROTATION,0f, 360f)
    private val txtColor = PropertyValuesHolder.ofInt("textColor",
        Color.parseColor("#FFFF0000"),
        Color.parseColor("#FF0000FF")
    ).apply {
        setEvaluator(ArgbEvaluator())
    }

    private val tranlateY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y,-100f, 100f)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    object GradientArgbEvaluator : TypeEvaluator<IntArray>{
        val argbEvaluator=ArgbEvaluator();

        override fun evaluate(
            fraction: Float,
            startValue: IntArray,
            endValue: IntArray
        ): IntArray {
            return startValue.mapIndexed{index, item ->
                argbEvaluator.evaluate(fraction, item, endValue[index]) as Int}.toIntArray()
        }

    }



    //переход на фрагмент с рузультатами и передача параметра количества правильных ответов
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context :Context = requireContext();

        /*val textShader = LinearGradient(0f, 0f,
            binding.textView.paint.measureText(binding.textView.toString()),
            binding.textView.textSize,
            intArrayOf(Color.MAGENTA, Color.BLUE, Color.GREEN),
            null,
            Shader.TileMode.CLAMP
        )

        binding.textView.paint.shader = textShader;
        binding.textView.invalidate();*/

        ValueAnimator.ofObject(GradientArgbEvaluator,
                    intArrayOf(Color.MAGENTA, Color.MAGENTA, Color.MAGENTA),
                    intArrayOf(Color.MAGENTA, Color.MAGENTA, Color.BLUE),
                    intArrayOf(Color.MAGENTA, Color.BLUE, Color.BLACK),
                    intArrayOf(Color.BLUE, Color.BLACK, Color.RED),
                    intArrayOf(Color.BLACK, Color.RED, Color.GREEN)

            ).apply {
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                duration = 3000
            addUpdateListener {
                val shader = LinearGradient(0f, 0f,
                    binding.textView.paint.measureText(binding.textView.toString()),
                    binding.textView.textSize,
                    it.animatedValue as IntArray,
                    null,
                    Shader.TileMode.CLAMP
                )

                binding.textView.paint.shader = shader;
                binding.textView.invalidate();
            }
            start()

        }


        //сет анимаций из xml
        (AnimatorInflater.loadAnimator(context, R.animator.my_animation) as AnimatorSet)
            .apply {
                setTarget(binding.btnContinue)
                start()
            }




       //сет анимаций программно
        /*
        val firstAnimation = ObjectAnimator.ofPropertyValuesHolder(binding.btnContinue, rotationX, txtColor ).apply {
            duration = 3000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = 2;//ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE;
        }

        val secondAnimation = ObjectAnimator.ofPropertyValuesHolder(binding.btnContinue, tranlateY, rotation ).apply {
            duration = 3000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = 2;//ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE;
        }

        AnimatorSet().apply {
            play(secondAnimation).after(firstAnimation)
            start()
        }*/

       //анимация через ObjectAnimator
        /*ObjectAnimator.ofPropertyValuesHolder(binding.btnContinue, rotationX, txtColor ).apply {
            duration = 3000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = 2;//ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE;
            start()
        }*/

       //анимация через xml
        /*val context :Context = requireContext();

        (AnimatorInflater.loadAnimator(context, R.animator.custom_animation) as ObjectAnimator)
            .apply {
                target = binding.btnContinue
                start()
            }*/

        /*ObjectAnimator.ofFloat(binding.mainFonImage,
            View.ROTATION,
            0f, 360f).apply {
                duration = 3000
                interpolator = AccelerateDecelerateInterpolator()
                repeatCount = 0;//ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE;
        }.start();


        ObjectAnimator.ofArgb(binding.btnContinue,
            "textColor",
            Color.parseColor("#FFFF0000"),
            Color.parseColor("#FF0000FF"),
        ).apply {
            duration = 3000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE;
        }.start();

        AnimatorInflater



        binding.btnContinue.translationY=100f
        binding.btnContinue.alpha = 0.3f


        //локальная анимация, сделали и забыли
        binding.btnContinue.animate().apply {
            duration = 2000
            //rotation(360f)
            scaleX(1.5f)
            scaleY(1.5f)
            translationY(-100f)
            alpha(1f)
            interpolator = AccelerateDecelerateInterpolator()
        }*/

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_quizFragment)

            ;
            //val action = QuizFragmentDirections.actionQuizFragmentToResultFragment(number)
            //findNavController().navigate(action)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment welkomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WelcomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}