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
import zeyad.app.livestock.databinding.FragmentDeerBinding
import zeyad.app.livestock.databinding.FragmentDogBinding
import zeyad.app.livestock.databinding.FragmentDoveBinding


class DoveFragment : Fragment() {
    private val doveLiveData = MutableLiveData<String>()
    private val numberLiveData = MutableLiveData<String>()
    private val typeLiveData = MutableLiveData<String>()
    private val simLiveData = MutableLiveData<String>()


    //===================MediatorLiveData is combine tow or more LiveData together=================//
    private val isValidLiveData = MediatorLiveData<Boolean>().apply {
        this.value = false

        addSource(doveLiveData) { dove ->
            val number = numberLiveData.value
            val type = typeLiveData.value
            val sim = simLiveData.value
            //=====this is for isValidLiveData=====//
            this.value = validateform(dove, number, type, sim)

        }
        addSource(numberLiveData) { number ->
            val dove = doveLiveData.value
            val type = typeLiveData.value
            val sim = simLiveData.value
            this.value = validateform(dove, number, type, sim)

        }
        addSource(typeLiveData) { type ->
            val dove = doveLiveData.value
            val number = numberLiveData.value
            val sim = typeLiveData.value
            //=====this is for isValidLiveData=====//
            this.value = validateform(dove, number, type, sim)

        }
        addSource(simLiveData) { sim ->
            val dove = doveLiveData.value
            val number = numberLiveData.value
            val type = typeLiveData.value
            //=====this is for isValidLiveData=====//
            this.value = validateform(dove, number, type, sim)
        }
    }
    lateinit var binding: FragmentDoveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDoveBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backDove.setOnClickListener {
            findNavController().navigate(R.id.action_doveFragment_to_birdsFragment)
        }

        val dove = resources.getStringArray(R.array.dove)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_items,dove)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        val type = resources.getStringArray(R.array.Type)
        val arrayAdapterType = ArrayAdapter(requireContext(), R.layout.dropdown_items, type)
        binding.autoCompleteTextView0.setAdapter(arrayAdapterType)
        binding.doveButton.setOnClickListener {

            Toast.makeText(requireContext(), "تم ارسال طلبك بنجاح", Toast.LENGTH_LONG).show()
        }
        binding.mune.editText?.doOnTextChanged { text, _, _, _ ->
            doveLiveData.value = text.toString()
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

            binding.doveButton.isEnabled = isValid

        }
    }

    private fun validateform(
        dove: String?,
        number: String?,
        type: String?,
        sim: String?,
    ): Boolean {
        val isValidDove = dove != null && dove.isNotBlank()

        val isValidNumber = number != null && number.isNotBlank() && number.length <= 10

        val isValidType = type != null && type.isNotBlank()

        val isValidSim = sim != null && sim.isNotBlank() && sim.length >= 10

        return isValidDove && isValidNumber && isValidType && isValidSim
    }
}