package zeyad.app.livestock.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import zeyad.app.livestock.R
import zeyad.app.livestock.databinding.FragmentInformationsBackBinding


class InformationsBackFragment : Fragment() {
    private val idLiveData = MutableLiveData<String>()
    private val phoneLiveData = MutableLiveData<String>()


    //===================MediatorLiveData is combine tow or more LiveData together=================//
    private val isValidLiveData = MediatorLiveData<Boolean>().apply {
        this.value = false

        addSource(idLiveData) { id ->
            val phone = phoneLiveData.value

            //=====this is for isValidLiveData=====//
            this.value = validateform(id, phone)

        }




            addSource(phoneLiveData) { phone ->
                val id = idLiveData.value

                this.value = validateform(id, phone )

            }

        }
 lateinit var binding : FragmentInformationsBackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentInformationsBackBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonInformation.setOnClickListener {
            findNavController().navigate(R.id.action_informationsBackFragment_to_signInFragment)
            Toast.makeText(requireContext(), "تم ارسال كلمة المرور", Toast.LENGTH_LONG).show()
        }

        binding.backInformations.setOnClickListener {
            findNavController().navigate(R.id.action_informationsBackFragment_to_signInFragment)
        }

    binding.id.editText?.doOnTextChanged { text, _, _, _ ->
        idLiveData.value = text?.toString()
    }

        binding.phone.editText?.doOnTextChanged { text, _, _, _ ->
            phoneLiveData.value = text?.toString()
        }

    isValidLiveData.observe(viewLifecycleOwner) { isValid ->
        binding.buttonInformation.isEnabled = isValid


    }

    }

    private fun validateform(
        id: String?,

        phone: String?,
    ): Boolean {
        val isValidId = id != null && id.isNotBlank() && id.length == 10
        val isValidPhone = phone != null && phone.isNotBlank() && phone.length ==10

        return isValidId  && isValidPhone
    }
}