package org.pursuit.todoapplication

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.EditText
import kotlinx.android.synthetic.main.dialog_new_task.*



class NewTaskDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val rootView = activity!!.layoutInflater.inflate(R.layout.dialog_new_task, null)
        val title = arguments!!.getInt("dialog_title")
        val builder = activity?.let { AlertDialog.Builder(it) }
        builder?.setTitle(title)
        val task = rootView.findViewById<EditText>(R.id.task)

        builder?.setView(rootView)?.setPositiveButton(R.string.save) { dialog, id ->
            newTaskDialogListener?.onDialogPositiveClick(
                this,task.text.toString()
            );
        }?.setNegativeButton(android.R.string.cancel) { dialog,
                                                        id ->
            newTaskDialogListener?.onDialogNegativeClick(this)
        }
        return builder!!.create()

    }
    interface NewTaskDialogListener {

        fun onDialogPositiveClick(dialog: DialogFragment, task: String){

        }
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    var newTaskDialogListener: NewTaskDialogListener? = null

    companion object {
        fun newInstance(title: Int): NewTaskDialogFragment {

            val newTaskDialogFragment = NewTaskDialogFragment()
            val args = Bundle()
            args.putInt("dialog_title", title)
            newTaskDialogFragment.arguments = args
            return newTaskDialogFragment
        }
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            newTaskDialogListener = activity as NewTaskDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement NewTaskDialogListener")
        }

    }
}
