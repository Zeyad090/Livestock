package zeyad.app.livestock.UI

import android.os.Bundle
import android.text.TextUtils
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
import zeyad.app.livestock.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private val idLiveData = MutableLiveData<String>()
    private val dateLiveData = MutableLiveData<String>()
    private val passwordRLiveData = MutableLiveData<String>()
  //  private val confirmPasswordLiveData = MutableLiveData<String>()


    //===================MediatorLiveData is combine tow or more LiveData together=================//
    private val isValidLiveData = MediatorLiveData<Boolean>().apply {
        this.value = false

        addSource(idLiveData) { id ->
           // val confirmPassword = confirmPasswordLiveData.value
            val date = dateLiveData.value
            val password = passwordRLiveData.value

            //=====this is for isValidLiveData=====//
            this.value = validateform(id, date, password //confirmPassword)
            )

        }
       // addSource(confirmPasswordLiveData) { confirmPassword ->
        //    val id = idLiveData.value
         //   val date = dateLiveData.value
          //  val password = passwordRLiveData.value

            //=====this is for isValidLiveData=====//
          //  this.value = validateform(id, date, password, confirmPassword)

            addSource(passwordRLiveData) { password ->
              //  val confirmPassword = confirmPasswordLiveData.value
                val id = idLiveData.value
                val date = dateLiveData.value

                this.value = validateform(id, password, date )

            }
            addSource(dateLiveData) { date ->
              //  val confirmPassword = confirmPasswordLiveData.value
                val id = idLiveData.value
                val password = passwordRLiveData.value

                this.value = validateform(id, date, password)

            }
        }

    lateinit var binding: FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRegist.setOnClickListener {
            Toast.makeText(requireContext(), "سجل الدخول الآن", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_registerFragment_to_signInFragment)
        }

        binding.backRegister.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_signInFragment)
        }

        binding.id.editText?.doOnTextChanged { text, _, _, _ ->
            idLiveData.value = text?.toString()
        }
        binding.password.editText?.doOnTextChanged { text, _, _, _ ->
            passwordRLiveData.value = text?.toString()
        }

//        binding.confirmPassword.editText?.doOnTextChanged { text, _, _, _ ->
//            confirmPasswordLiveData.value = text?.toString()
//        }
        binding.date.editText?.doOnTextChanged { text, _, _, _ ->
            dateLiveData.value = text?.toString()
        }
        isValidLiveData.observe(viewLifecycleOwner) { isValid ->
            binding.buttonRegist.isEnabled = isValid


        }


    }


    private fun validateform(
        id: String?,
        date: String?,
        password: String?,
     //   confirmPassword: String?,
    ): Boolean {
        val isValidId = id != null && id.isNotBlank() && id.length == 10
        val isValidPassword = password != null && password.isNotBlank() && password.length >= 8
      //  val isValidConfirmPassword = confirmPassword != null && confirmPassword.isNotBlank() && confirmPassword.length >= 8
        val isValidDate = date != null && date.isNotBlank()

        return isValidId && isValidDate && isValidPassword  //isValidConfirmPassword
    }
}