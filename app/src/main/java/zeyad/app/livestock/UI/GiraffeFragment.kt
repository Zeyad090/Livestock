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
import zeyad.app.livestock.databinding.FragmentGiraffeBinding


class GiraffeFragment : Fragment() {
    private val giraffeLiveData = MutableLiveData<String>()
    private val numberLiveData = MutableLiveData<String>()
    private val typeLiveData = MutableLiveData<String>()
    private val simLiveData = MutableLiveData<String>()



    //===================MediatorLiveData is combine tow or more LiveData together=================//
    private val isValidLiveData = MediatorLiveData<Boolean>().apply {
        this.value = false

        addSource(giraffeLiveData) { giraffe ->
            val number = numberLiveData.value
            val type = typeLiveData.value
            val sim = simLiveData.value
            //=====this is for isValidLiveData=====//
            this.value = validateform(giraffe,number,type,sim)

        }
        addSource(numberLiveData) { number ->
            val giraffe = giraffeLiveData.value
            val type = typeLiveData.value
            val sim = simLiveData.value
            this.value = validateform(giraffe,number,type,sim)

        }
        addSource(typeLiveData) { type ->
            val giraffe = giraffeLiveData.value
            val number = numberLiveData.value
            val sim = typeLiveData.value
            //=====this is for isValidLiveData=====//
            this.value = validateform(giraffe,number,type,sim)

        }
        addSource(simLiveData) { sim ->
            val giraffe = giraffeLiveData.value
            val number = numberLiveData.value
            val type = typeLiveData.value
            //=====this is for isValidLiveData=====//
            this.value = validateform(giraffe,number,type,sim)

        }
    }
    lateinit var binding: FragmentGiraffeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
      binding = FragmentGiraffeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backGiraffe.setOnClickListener {
            findNavController().navigate(R.id.action_giraffeFragment_to_livestockFragment)
        }

        val giraffe = resources.getStringArray(R.array.Giraffe)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_items, giraffe)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        val type = resources.getStringArray(R.array.Type)
        val arrayAdapterType = ArrayAdapter(requireContext(), R.layout.dropdown_items, type)
        binding.autoCompleteTextView0.setAdapter(arrayAdapterType)
        binding.giraffeButton.setOnClickListener {
            Toast.makeText(requireContext(), "???? ?????????? ???????? ??????????", Toast.LENGTH_LONG).show()
        }
        binding.mune.editText?.doOnTextChanged { text, _, _, _ ->
            giraffeLiveData.value = text.toString()
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

            binding.giraffeButton.isEnabled = isValid


        }
    }
    private fun validateform(giraffe: String?, number: String? , type : String?, sim : String?): Boolean {
        val isValidGiraffe = giraffe != null && giraffe.isNotBlank()

        val isValidNumber = number != null && number.isNotBlank() && number.length <= 10

        val isValidType = type != null && type.isNotBlank()

        val isValidSim = sim != null && sim.isNotBlank() && sim.length >= 10

        return isValidGiraffe && isValidNumber && isValidType && isValidSim
    }

}