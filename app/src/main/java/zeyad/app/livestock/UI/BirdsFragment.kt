package zeyad.app.livestock.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import zeyad.app.livestock.R
import zeyad.app.livestock.databinding.FragmentBirdsBinding


class BirdsFragment : Fragment() {
    lateinit var binding: FragmentBirdsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBirdsBinding.inflate(inflater,container,false)

return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ostrich0.setOnClickListener {
            findNavController().navigate(R.id.action_birdsFragment_to_ostrichFragment)
        }
        binding.birdCiconia.setOnClickListener {
            findNavController().navigate(R.id.action_birdsFragment_to_ciconiaFragment)
        }
        binding.birdDove.setOnClickListener {
            findNavController().navigate(R.id.action_birdsFragment_to_doveFragment)
        }
        binding.birdEater.setOnClickListener {
            findNavController().navigate(R.id.action_birdsFragment_to_eaterFragment)
        }
        binding.birdFalcon.setOnClickListener {
            findNavController().navigate(R.id.action_birdsFragment_to_falconFragment)
        }
        binding.birdHoopoe.setOnClickListener {
            findNavController().navigate(R.id.action_birdsFragment_to_hoopoeFragment)
        }
        binding.birdOwl.setOnClickListener {
            findNavController().navigate(R.id.action_birdsFragment_to_owlFragment)
        }

        binding.birdQuail.setOnClickListener {
            findNavController().navigate(R.id.action_birdsFragment_to_quailFragment)
        }
        binding.birdRoller.setOnClickListener {
            findNavController().navigate(R.id.action_birdsFragment_to_rollerFragment)
        }
        binding.birdSparrow.setOnClickListener {
            findNavController().navigate(R.id.action_birdsFragment_to_sparrowFragment)
        }
        binding.birdVirgo.setOnClickListener {
            findNavController().navigate(R.id.action_birdsFragment_to_virgoFragment)
        }
        binding.birdTurtleDove.setOnClickListener {
            findNavController().navigate(R.id.action_birdsFragment_to_turtleFragment)
        }
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_birdsFragment_to_choosingListAnimalFragment)
        }
    }



}