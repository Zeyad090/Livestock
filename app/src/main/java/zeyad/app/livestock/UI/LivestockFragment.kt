package zeyad.app.livestock.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import zeyad.app.livestock.R
import zeyad.app.livestock.databinding.FragmentLivestockBinding


class LivestockFragment : Fragment() {
    lateinit var binding: FragmentLivestockBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLivestockBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardCamel.setOnClickListener {
            findNavController().navigate(R.id.action_livestockFragment_to_camelFragment)
        }
        binding.cardBuckDeer.setOnClickListener {
            findNavController().navigate(R.id.action_livestockFragment_to_deerFragment)
        }
        binding.cardCat.setOnClickListener {
            findNavController().navigate(R.id.action_livestockFragment_to_catFragment)
        }
        binding.cardCow.setOnClickListener {
            findNavController().navigate(R.id.action_livestockFragment_to_cowFragment)
        }
        binding.cardDog.setOnClickListener {
            findNavController().navigate(R.id.action_livestockFragment_to_dogFragment)
        }
        binding.cardDonkey.setOnClickListener {
            findNavController().navigate(R.id.action_livestockFragment_to_donkeyFragment)
        }
        binding.cardElephon.setOnClickListener {
            findNavController().navigate(R.id.action_livestockFragment_to_elephantFragment)
        }
        binding.cardGiraffe.setOnClickListener {
            findNavController().navigate(R.id.action_livestockFragment_to_giraffeFragment)
        }
        binding.cardHorse.setOnClickListener {
            findNavController().navigate(R.id.action_livestockFragment_to_horseFragment)
        }
        binding.cardHyena.setOnClickListener {
            findNavController().navigate(R.id.action_livestockFragment_to_hyenaFragment)
        }
        binding.cardLine.setOnClickListener {
            findNavController().navigate(R.id.action_livestockFragment_to_lionFragment)
        }
        binding.cardMonkey.setOnClickListener {
            findNavController().navigate(R.id.action_livestockFragment_to_monkeyFragment)
        }

        binding.cardSheep.setOnClickListener {
            findNavController().navigate(R.id.action_livestockFragment_to_sheepFragment)
        }
        binding.cardTaiger.setOnClickListener {
            findNavController().navigate(R.id.action_livestockFragment_to_tigerFragment)
        }

        binding.backLiveStock.setOnClickListener {
            findNavController().navigate(R.id.action_livestockFragment_to_choosingListAnimalFragment)
        }


    }


}