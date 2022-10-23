package com.example.memeshare

import android.app.Activity
import android.app.Activity.RESULT_CANCELED
import android.content.ContentProvider
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.media.Image
import android.net.Uri
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.memeshare.databinding.FragmentMemeBinding
import java.io.File
import java.io.FileOutputStream

class MemeFragment : Fragment() {
    private val memeViewModel: MemeViewModel by viewModels()
    private var _binding: FragmentMemeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = memeViewModel
            memeFragment = this@MemeFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun nextmeme() {
        memeViewModel.getMemePhoto()

//        bindImage(imgView, viewModel.meme.value?.url)
    }

    fun sharememe() {
        val status: MemeApiStatus? = memeViewModel.status.value
        if (status == MemeApiStatus.ERROR) {
            //show Toast Message
            Toast.makeText(
                this.requireContext(),
                getString(R.string.network_error),
                Toast.LENGTH_LONG
            ).show()
        } else {
            val imageUri: Uri = Uri.parse(memeViewModel.meme.value!!.url)
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/png"
            val sharingText = getString(R.string.sharingText)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.putExtra(Intent.EXTRA_STREAM, imageUri)
            intent.putExtra(Intent.EXTRA_TEXT, sharingText)
            val title = resources.getString(R.string.sharememe)
            startActivity(Intent.createChooser(intent, title))
        }
    }
//    private fun getContentUri(): Uri? {
//        val bitmap: Bitmap
//        //get bitmap from Uri
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            val source = ImageDecoder.createSource(this.requireContext().contentResolver, imageUri!!)
//            bitmap = ImageDecoder.decodeBitmap(source)
//        }else {
//            bitmap = MediaStore.Images.Media.getBitmap(this.requireContext().contentResolver, imageUri)
//        }
//
//        val imagesFolder = File(this.requireContext().cacheDir, "images")
//        var contentUri: Uri? = null
//        try {
//            imagesFolder.mkdirs()
//            val file = File(imagesFolder, "shared_images.png")
//            val stream = FileOutputStream(file)
//            bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream)
//            stream.flush()
//            stream.close()
//            contentUri = FileProvider.getUriForFile(this.requireContext(), "com.example.memeshare.fileprovider", file)
//        } catch (e: Exception) {
//            Toast.makeText(this.requireContext(), "${e.message}", Toast.LENGTH_SHORT).show()
//        }
//        return contentUri
//    }
}