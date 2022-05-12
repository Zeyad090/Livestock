package zeyad.app.livestock.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import zeyad.app.livestock.R
import zeyad.app.livestock.databinding.FragmentDogBinding


class DogFragment : Fragment() {
    private val dogLiveData = MutableLiveData<String>()
    private val numberLiveData = MutableLiveData<String>()
    private val typeLiveData = MutableLiveData<String>()
    private val simLiveData = MutableLiveData<String>()



    //===================MediatorLiveData is combine tow or more LiveData together=================//
    private val isValidLiveData = MediatorLiveData<Boolean>().apply {
        this.value = false

        addSource(dogLiveData) { dog ->
            val number = numberLiveData.value
            val type = typeLiveData.value
            val sim = simLiveData.value
            //=====this is for isValidLiveData=====//
            this.value = validateform(dog,number,type,sim)

        }
        addSource(numberLiveData) { number ->
            val dog = dogLiveData.value
            val type = typeLiveData.value
            val sim = simLiveData.value
            this.value = validateform(dog,number,type,sim)

        }
        addSource(typeLiveData) { type ->
            val dog = dogLiveData.value
            val number = numberLiveData.value
            val sim = typeLiveData.value
            //=====this is for isValidLiveData=====//
            this.value = validateform(dog,number,type,sim)

        }
        addSource(simLiveData) { sim ->
            val dog = dogLiveData.value
            val number = numberLiveData.value
            val type = typeLiveData.value
            //=====this is for isValidLiveData=====//
            this.value = validateform(dog,number,type,sim)

        }
    }
    lateinit var binding : FragmentDogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentDogBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backDog.setOnClickListener {
            findNavController().navigate(R.id.action_dogFragment_to_livestockFragment)
        }

        val dog = resources.getStringArray(R.array.dog)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_items, dog)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        val type = resources.getStringArray(R.array.Type)
        val arrayAdapterType = ArrayAdapter(requireContext(), R.layout.dropdown_items,type)
        binding.autoCompleteTextView0.setAdapter(arrayAdapterType)
        binding.dogButton.setOnClickListener {
            Toast.makeText(requireContext(),"تم ارسال طلبك بنجاح", Toast.LENGTH_LONG).show()
        }
    }
    private fun validateform(dog: String?, number: String? , type : String?, sim : String?): Boolean {
        val isValidDog = dog != null && dog.isNotBlank()

        val isValidNumber = number != null && number.isNotBlank() && number.length <= 10

        val isValidType = type != null && type.isNotBlank()

        val isValidSim = sim != null && sim.isNotBlank() && sim.length >= 10

        return isValidDog && isValidNumber && isValidType && isValidSim
    }

}
