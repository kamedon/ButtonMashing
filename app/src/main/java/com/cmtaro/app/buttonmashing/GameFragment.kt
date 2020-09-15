package com.cmtaro.app.buttonmashing


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.cmtaro.app.buttonmashing.databinding.FragmentGameBinding

class GameFragment : Fragment() {

  private val gameViewModel = GameViewModel()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_game, null)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val binding: FragmentGameBinding =
      DataBindingUtil.bind(view)
        ?: throw RuntimeException("FragmentGameBindingでバインディングができない")
    binding.viewModel = gameViewModel
    binding.lifecycleOwner = viewLifecycleOwner
  }

  private val mainViewModel: MainSharedViewModel by activityViewModels()
  override fun onStart() {
    super.onStart()
    gameViewModel.start {
      mainViewModel.history.value?.add(it)
      findNavController().navigate(
        R.id.action_gameFragment_to_resultFragment,
        Bundle().apply {
          putInt("count", it)
        })
    }
  }


}
