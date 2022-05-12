package zeyad.app.livestock.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import zeyad.app.livestock.R
import zeyad.app.livestock.databinding.FragmentSheepBinding
import zeyad.app.livestock.databinding.FragmentSparrowBinding


class SparrowFragment : Fragment() {
    private val sparrowLiveData = MutableLiveData<String>()
    private val numberLiveData = MutableLiveData<String>()
    private val typeLiveData = MutableLiveData<String>()
    private val simLiveData = MutableLiveData<String>()


    //===================MediatorLiveData is combine tow or more LiveData together=================//
    private val isValidLiveData = MediatorLiveData<Boolean>().apply {
        this.value = false

        addSource(sparrowLiveData) { sparrow ->
            val number = numberLiveData.value
            val type = typeLiveData.value
            val sim = simLiveData.value
            //=====this is for isValidLiveData=====//
            this.value = validateform(sparrow, number, type, sim)

        }
        addSource(numberLiveData) { number ->
            val sparrow = sparrowLiveData.value
            val type = typeLiveData.value
            val sim = simLiveData.value
            this.value = validateform(sparrow, number, type, sim)

        }
        addSource(typeLiveData) { type ->
            val sparrow = sparrowLiveData.value
            val number = numberLiveData.value
            val sim = typeLiveData.value
            //=====this is for isValidLiveData=====//
            this.value = validateform(sparrow, number, type, sim)

        }
        addSource(simLiveData) { sim ->
            val sparrow = sparrowLiveData.value
            val number = numberLiveData.value
            val type = typeLiveData.value
            //=====this is for isValidLiveData=====//
            this.value = validateform(sparrow, number, type, sim)

        }
    }
    lateinit var binding:FragmentSparrowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSparrowBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backSparrow.setOnClickListener {
            findNavController().navigate(R.id.action_sparrowFragment_to_birdsFragment)
        }

        val sparrow = resources.getStringArray(R.array.sparrow)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_items, sparrow)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        val type = resources.getStringArray(R.array.Type)
        val arrayAdapterType = ArrayAdapter(requireContext(), R.layout.dropdown_items, type)
        binding.autoCompleteTextView0.setAdapter(arrayAdapterType)
        binding.sparrowButton.setOnClickListener {

            Toast.makeText(requireContext(), "تم ارسال طلبك بنجاح", Toast.LENGTH_LONG).show()
        }

        binding.mune.editText?.doOnTextChanged { text, _, _, _ ->
            sparrowLiveData.value = text.toString()
        }
        binding.mune2.editText?.doOnTextChanged { text, _, _, _ ->
            numberLiveData.value = text.toString()
        }
        binding.mune3.editText?.doOnTextChanged { text, _, _, _ ->
            typeLiveData.value = text.toString()
        }
        binding.mune4.editText?.doOnTextChanged { text, _, _, _ ->

            simLiveData.value = text.toString()
        }

        isValidLiveData.observe(viewLifecycleOwner) { isValid ->
            binding.sparrowButton.isEnabled = isValid

        }
    }


    private fun validateform(
        sparrow: String?,
        number: String?,
        type: String?,
        sim: String?,
    ): Boolean {
        val isValidSparrow = sparrow != null && sparrow.isNotBlank()

        val isValidNumber = number != null && number.isNotBlank() && number.length <= 10

        val isValidType = type != null && type.isNotBlank()

        val isValidSim = sim != null && sim.isNotBlank() && sim.length >= 10

        return isValidSparrow && isValidNumber && isValidType && isValidSim
    }


}