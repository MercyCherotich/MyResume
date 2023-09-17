package com.example.myresume

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myresume.data.User
import com.example.myresume.data.UserRoomDatabase
import com.example.myresume.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadViews()
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        binding.button.setOnClickListener(){
            addUser()
        }
    }

  fun addUser(){
      val name = binding.editTextTextPersonName.text.toString()
      val slack_username = binding.editTextSlackName.text.toString()
      val github_handle = binding.editTextTextGithubHandle.text.toString()
      val user_bio = binding.editTextTextUserBio.text.toString()
      val user: User = User(uid = 1,name, slack_username, github_handle, user_bio)
      try {
          this.activity?.let {UserRoomDatabase.getDatabase(it.applicationContext,lifecycleScope).userDao().insertUser(user) }
      }
      catch (e: Exception){
          Log.d("adduser",e.toString())
      }
  }
    fun getUser(): User {

        val user: User = UserRoomDatabase.getDatabase(this.requireContext(), lifecycle.coroutineScope).userDao().getUser()

        return user
    }

    fun loadViews(){

        val mUser: User = User(
            1,
            "Mercy Cherotich",
            "Mercy_Cherotich",
            "MercyCherotich",
            "Mobile Developer"
        )
        val user= UserRoomDatabase.getDatabase(this.requireContext(),lifecycleScope)
            .userDao().getUser()?:mUser
        binding.editTextTextPersonName.setText(user.fullName.toString())
        binding.editTextSlackName.setText(user.slackUserName).toString()
        binding.editTextTextGithubHandle.setText(user.githubHandle).toString()
        binding.editTextTextUserBio.setText(user.userBio).toString()

    }

    override fun onResume() {
        super.onResume()
        loadViews()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}