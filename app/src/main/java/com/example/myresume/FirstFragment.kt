package com.example.myresume

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myresume.data.User
import com.example.myresume.data.UserApplication
import com.example.myresume.data.UserRoomDatabase
import com.example.myresume.data.UserDao
import com.example.myresume.databinding.FragmentFirstBinding
import com.example.myresume.viewmodel.UserViewModel
import com.example.myresume.viewmodel.UserViewModelFactory
import kotlinx.coroutines.flow.Flow

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var dao: UserDao

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
//        userViewModel.user.observe(this, Observer { user ->
//            // Update the cached copy of the words in the adapter.
//            //user?.let { adapter.submitList(it) }
//        })
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        loadViews()
//        val name = binding.textviewName.text.toString()
//        val slack_username = binding.textViewSlack.text.toString()
//        val github_handle = binding.textViewGithub.text.toString()
//        val user_bio = binding.textViewBio.text.toString()
//        val user: User = User(uid = 1,name, slack_username, github_handle, user_bio)
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
        val user= getUser()?:mUser

        binding.textviewName.text = user.fullName.toString()
         binding.textViewSlack.setText(user.slackUserName).toString()
         binding.textViewGithub.setText(user.githubHandle).toString()
         binding.textViewBio.setText(user.userBio).toString()

    }

    override fun onResume() {
        super.onResume()
        loadViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//    private val userViewModel: UserViewModel by viewModels {
//        UserViewModelFactory((application as UserApplication).repository)
//    }
}