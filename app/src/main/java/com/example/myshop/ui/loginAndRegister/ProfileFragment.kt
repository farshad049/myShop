package com.example.myshop.ui.loginAndRegister

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
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
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.permissionx.guolindev.PermissionX
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var currentImageUri: Uri? = null
    private var uploadedImageUrl: String = ""
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


    }//FUN

    private fun setImage() {
        val pictureDialog = AlertDialog.Builder(requireContext())
        pictureDialog.setTitle("Select Action")
        val pictureDialogItem = arrayOf("select photo from gallery", "capture photo from camera")
        pictureDialog.setItems(pictureDialogItem) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallery()
                //    1 -> openCamera()
            }
        }
        pictureDialog.show()
    }

    //region take image from camera
//    private fun openCamera() {
//        PermissionX.init(activity)
//            .permissions(Manifest.permission.CAMERA)
//            .onExplainRequestReason { scope, deniedList ->
//                scope.showRequestReasonDialog(
//                    deniedList,
//                    "Core fundamental are based on these permissions",
//                    "OK",
//                    "Cancel"
//                )
//            }
//            .onForwardToSettings { scope, deniedList ->
//                scope.showForwardToSettingsDialog(
//                    deniedList,
//                    "You need to allow necessary permissions in Settings manually",
//                    "OK",
//                    "Cancel"
//                )
//            }
//            .request { allGranted, grantedList, deniedList ->
//                if (allGranted) {
//                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                    cameraLauncher.launch(intent)
//                } else {
//                    Toast.makeText(
//                        requireContext(),
//                        "These permissions are denied: $deniedList",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//    }

//    private val cameraLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                try {
//                    showProgressBar()
//                    lifecycleScope.launch {
//                        val bitmap: Bitmap=result!!.data!!.extras!!.get("data") as Bitmap
//                        //currentImageUri=saveBitmapFile(bitmap)
//                        binding.ivProfile.load(currentImageUri)
//                        dismissProgressBar()
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    Toast.makeText(
//                        requireContext(),
//                        "Failed to load image from camera",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//        }

    //endregion take image from camera

    //region take image from gallery
    private fun choosePhotoFromGallery() {
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

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                try {
                    showProgressBar()
                    lifecycleScope.launch {
                        currentImageUri = result!!.data!!.data
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

    //endregion take image from gallery


    private suspend fun saveBitmapFile(mBitmap: Bitmap?): Uri {
        var result = ""
        withContext(Dispatchers.IO) {
            if (mBitmap != null) {
                try {
                    val bytes = ByteArrayOutputStream()
                    mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
                    val f = File(
                        requireContext().externalCacheDir?.absoluteFile.toString() +
                                File.separator + "myShop_" + System.currentTimeMillis() / 1000 + ".jpeg"
                    )
                    val fo = FileOutputStream(f)
                    fo.write(bytes.toByteArray())
                    fo.close()
                    result = f.absolutePath
                } catch (e: Exception) {
                    result = ""
                    e.printStackTrace()
                }
            }
        }
        return Uri.parse(result)
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
            binding.etPhone.error = "* please enter a valid phone number"
            binding.etEditPhone.addTextChangedListener { binding.etPhone.error = null }
            return
        }


            //take the uri extension like .jpg or .png
            val fileExtension = Constants.getFileExtension(requireActivity(), currentImageUri)
            FireBaseRepository().uploadImageToCloud(currentImageUri, fileExtension)
                .addOnSuccessListener { taskSnapshot ->
                    taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { task ->
                        userHashMap[Constants.IMAGE]=task.toString()
                    }
                }


            showProgressBar()

            //in User definitions , mobile is of type long
            userHashMap[Constants.MOBILE] = mobile.toLong()
            userHashMap[Constants.GENDER] = gender

            lifecycleScope.launch {
                viewModel.updateUserDetail(userHashMap)
                    .addOnSuccessListener {
                        dismissProgressBar()
                        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToDashboardFragment())
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "failed update user", Toast.LENGTH_LONG)
                    }
            }

    }



    fun uploadedImage(image: String) {
        uploadedImageUrl = image
    }







    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}