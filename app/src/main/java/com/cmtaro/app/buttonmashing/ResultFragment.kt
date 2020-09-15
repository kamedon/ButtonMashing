package com.cmtaro.app.buttonmashing


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cmtaro.app.buttonmashing.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

  val viewModel = ResultViewModel()
  private val mainViewModel: MainSharedViewModel by activityViewModels()
  val adapter = HistoryAdapter()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_result, null)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding: FragmentResultBinding =
      DataBindingUtil.bind(view) ?: throw RuntimeException("FragmentResultBindingでバインドできない")
    binding.lifecycleOwner = viewLifecycleOwner
    binding.viewModel = viewModel

    binding.historyRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    binding.historyRecyclerView.adapter = adapter

    mainViewModel.history.observe(viewLifecycleOwner, Observer {
      adapter.update(it ?: listOf())
    })
  }

  override fun onStart() {
    super.onStart()
    val count = arguments?.getInt("count", 0) ?: 0
    viewModel.count.value = count

    Log.d("count:", mainViewModel.history.value?.toString())
  }
}
