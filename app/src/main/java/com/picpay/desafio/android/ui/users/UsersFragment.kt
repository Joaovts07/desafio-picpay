package com.picpay.desafio.android.ui.users

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.FragmentUsersBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [UsersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val viewModel : UsersViewModel by viewModels()

    private val userAdapter = UsersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setRecyclerViewm()
        observeVMEvents()
        getUsers()
    }

    private fun setListeners() {
        with(binding){
            swipeUsers.setOnRefreshListener {
                getUsers()
            }
        }
    }

    private fun getUsers() {
        viewModel.getUsers()
    }

    private fun setRecyclerViewm() {
        binding.recyclerView.run {
            setHasFixedSize(true)
            adapter = userAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
    private fun observeVMEvents(){
        viewModel.usersData.observe(viewLifecycleOwner){ usersState ->
            binding.swipeUsers.isRefreshing = false
            when(usersState){
                is UsersViewModel.UserState.LOADING ->{
                    showLoading()
                }
                is UsersViewModel.UserState.SUCCESS ->{
                    hideLoadingView()
                    userAdapter.submitList(usersState.usersList)

                }
            }

        }
    }

    private fun showLoading() {
        binding.userListProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoadingView() {
        binding.userListProgressBar.visibility = View.GONE
    }

        override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}