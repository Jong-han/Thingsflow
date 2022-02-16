package com.jh.thingsflow.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.jh.thingsflow.databinding.DialogInputBinding

class InputDialog(private val updateOrgAndRepo: (String,String)->Unit): DialogFragment() {

    private lateinit var dataBinding: DialogInputBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DialogInputBinding.inflate(requireActivity().layoutInflater)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.btnSearch.setOnClickListener {
            updateOrgAndRepo.invoke(dataBinding.etOrg.text.toString(), dataBinding.etRepo.text.toString())
            dismiss()
        }
    }

}