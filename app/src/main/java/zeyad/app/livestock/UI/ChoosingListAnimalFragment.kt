package zeyad.app.livestock.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import zeyad.app.livestock.R
import zeyad.app.livestock.databinding.FragmentChoosingListAnimalBinding


class ChoosingListAnimalFragment : Fragment() {
    lateinit var binding: FragmentChoosingListAnimalBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentChoosingListAnimalBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backChoosing.setOnClickListener {
            findNavController().navigate(R.id.action_choosingListAnimalFragment_to_signInFragment)
        }

        binding.cardLivestock.setOnClickListener {
            findNavController().navigate(R.id.action_choosingListAnimalFragment_to_livestockFragment)
        }
        binding.cardBirds.setOnClickListener {
            findNavController().navigate(R.id.action_choosingListAnimalFragment_to_birdsFragment)
        }
        binding.cardPet.setOnClickListener {
            findNavController().navigate(R.id.action_choosingListAnimalFragment_to_petsFragment)
        }
    }

}