package com.sabin.anzapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sabin.anzapp.ui.launch.LaunchScreen
import com.sabin.anzapp.ui.theme.AnzAppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class FirstFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                AnzAppTheme {
                    LaunchScreen(onClick = {navigateToSecondScreen()})
                }
            }
        }
    }

    private fun navigateToSecondScreen() {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
}