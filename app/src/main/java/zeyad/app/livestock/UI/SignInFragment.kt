package zeyad.app.livestock.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import zeyad.app.livestock.R
import zeyad.app.livestock.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {
    private val idLiveData = MutableLiveData<String>()
    private val passwordLiveData = MutableLiveData<String>()

    //===================MediatorLiveData is combine tow or more LiveData together=================//
    private val isValidLiveData = MediatorLiveData<Boolean>().apply {
        this.value = false

        addSource(idLiveData) { id ->
            val password = passwordLiveData.value
            //=====this is for isValidLiveData=====//
            this.value = validateform(id, password)

        }
        addSource(passwordLiveData) { password ->
            val id = idLiveData.value
            this.value = validateform(id, password)

        }
    }
    lateinit var binding: FragmentSignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

        ): View? {

        binding = FragmentSignInBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_registerFragment)
        }
        binding.passwordBack.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_informationsBackFragment)
        }

        binding.id.editText?.doOnTextChanged { text, _, _, _ ->
            idLiveData.value = text?.toString()
        }

        binding.password.editText?.doOnTextChanged { text, _, _, _ ->
            passwordLiveData.value = text?.toString()
        }
        isValidLiveData.observe(viewLifecycleOwner) { isValid ->
            binding.materialButton.isEnabled = isValid


        }
        binding.materialButton.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_choosingListAnimalFragment)
        }


    }


    private fun validateform(id: String?, password: String?): Boolean {
        val isValidId = id != null && id.isNotBlank() && id.length == 10

        val isValidPassword = password != null && password.isNotBlank() && password.length >= 8

        return isValidId && isValidPassword
    }
}