package com.example.myshop.ui.loginAndRegister

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.myshop.BaseFragment
import com.example.myshop.Constants
import com.example.myshop.R
import com.example.myshop.databinding.FragmentProfileBinding
import com.permissionx.guolindev.PermissionX
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.launch

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var currentImageUri: Any
    private val viewModel: FireBaseViewModel by viewModels()

    private val safeArgs: ProfileFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        binding.etEditFirstName.isEnabled = false
        binding.etEditFirstName.setText(safeArgs.userDetailInfo?.firstName ?: "")
        binding.etEditLastName.isEnabled = false
        binding.etEditLastName.setText(safeArgs.userDetailInfo?.lastName ?: "")
        binding.etEditEmail.isEnabled = false
        binding.etEditEmail.setText(safeArgs.userDetailInfo?.email ?: "")

        binding.ivProfile.setOnClickListener {
            setImage()
        }

        binding.BtnSave.setOnClickListener {
            onSave()
        }


        //start activity for result for camera
//        val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                if (result.data != null){
//                    try {
//                        showProgressBar()
//                        lifecycleScope.launch {
//                            //currentImageUri=result!!.data!!.extras!!.get("data")
//                            binding.ivProfile.load(currentImageUri)
//                            dismissProgressBar()
//                        }
//                    }catch (e:Exception){
//                        e.printStackTrace()
//                        Toast.makeText(requireContext(),"Failed to load image from camera", Toast.LENGTH_LONG).show()
//                    }
//                }
//            }
//        }

        //start activity for result for gallery
//         val  galleryLauncher =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//                if (result.resultCode == Activity.RESULT_OK) {
//                    val value= result.data?.getStringExtra("input")
//                    binding.ivProfile.load(value)
////                    if (result.data != null){
////                        try {
////                            showProgressBar()
////                            lifecycleScope.launch {
////                                binding.ivProfile.load(result!!.data!!.data)
////                                        dismissProgressBar()
////                            }
////                        }catch (e:Exception){
////                            e.printStackTrace()
////                            Toast.makeText(requireContext(),"Failed to load image from gallery", Toast.LENGTH_LONG).show()
////                        }
////                    }
//                }
//            }


    }//FUN

    private fun setImage() {
        val pictureDialog = AlertDialog.Builder(requireContext())
        pictureDialog.setTitle("Select Action")
        val pictureDialogItem = arrayOf("select photo from gallery", "capture photo from camera")
        pictureDialog.setItems(pictureDialogItem) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallery()
                1 -> openCamera()
            }
        }
        pictureDialog.show()
    }

    fun openCamera() {
        PermissionX.init(activity)
            .permissions(Manifest.permission.CAMERA)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(
                    deniedList,
                    "Core fundamental are based on these permissions",
                    "OK",
                    "Cancel"
                )
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    deniedList,
                    "You need to allow necessary permissions in Settings manually",
                    "OK",
                    "Cancel"
                )
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    cameraLauncher.launch(intent)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "These permissions are denied: $deniedList",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    fun choosePhotoFromGallery() {
        PermissionX.init(activity)
            .permissions(Manifest.permission.READ_EXTERNAL_STORAGE)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(
                    deniedList,
                    "Core fundamental are based on these permissions",
                    "OK",
                    "Cancel"
                )
            }
            .onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    deniedList,
                    "You need to allow necessary permissions in Settings manually",
                    "OK",
                    "Cancel"
                )
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    val intent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    galleryLauncher.launch(intent)

                } else {
                    Toast.makeText(
                        requireContext(),
                        "These permissions are denied: $deniedList",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    showProgressBar()
                    lifecycleScope.launch {
                        binding.ivProfile.load(result!!.data!!.extras!!.get("data"))
                        dismissProgressBar()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(
                        requireContext(),
                        "Failed to load image from camera",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        }

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                binding.ivProfile.load(result!!.data!!.data)
                try {
                    showProgressBar()
                    lifecycleScope.launch {
                        binding.ivProfile.load(result!!.data!!.data)
                        dismissProgressBar()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(
                        requireContext(),
                        "Failed to load image from gallery",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }


    private fun onSave() {
        val userHashMap = HashMap<String, Any>()
        val mobile = binding.etEditPhone.text.toString().trim()

        val gender = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radioButtonFemale -> "Female"
            R.id.radioButtonMale -> "Male"
            else -> 0
        }

        if (mobile.isEmpty() || !(Patterns.PHONE.matcher(mobile).matches())) {
            binding.etEmail.error = "* please enter a valid email address"
            binding.etEditPhone.addTextChangedListener { binding.etPhone.error = null }
            return
        }

        //in User definitions , mobile is of type long
        userHashMap[Constants.MOBILE] = mobile.toLong()
        userHashMap[Constants.GENDER] = gender

        lifecycleScope.launch {
            viewModel.updateUserDetail(userHashMap)
                .addOnSuccessListener {
                    findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToDashboardFragment())
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(),"failed update user",Toast.LENGTH_LONG)
                }
        }

    }











    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}