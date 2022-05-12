package zeyad.app.livestock.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import zeyad.app.livestock.R
import zeyad.app.livestock.databinding.FragmentPetCatBinding
import zeyad.app.livestock.databinding.FragmentPetsBinding


class PetsFragment : Fragment() {
    lateinit var binding: FragmentPetsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentPetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.petCat.setOnClickListener {
            findNavController().navigate(R.id.action_petsFragment_to_petCatFragment)
        }
        binding.petDog.setOnClickListener {
            findNavController().navigate(R.id.action_petsFragment_to_petDogFragment)
        }
        binding.petParrot.setOnClickListener {
            findNavController().navigate(R.id.action_petsFragment_to_petParrotFragment)
        }
        binding.petsFish.setOnClickListener {
            findNavController().navigate(R.id.action_petsFragment_to_petsFishFragment)
        }
        binding.backPets.setOnClickListener {
            findNavController().navigate(R.id.action_petsFragment_to_choosingListAnimalFragment)
        }
    }


}