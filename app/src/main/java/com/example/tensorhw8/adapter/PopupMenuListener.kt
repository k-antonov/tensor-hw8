package com.example.tensorhw8.adapter

import android.app.AlertDialog
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.PopupMenu
import com.example.tensorhw8.R

class PopupMenuListener(
    private val deleteAction: (Int) -> Unit,
    private val renameAction: (Int, String) -> Unit,
    private val adapterPosition: Int
) : View.OnClickListener {
    override fun onClick(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.menu.add(
            0,
            ItemsAdapter.ID_RENAME,
            Menu.NONE,
            view.context.getString(R.string.rename)
        )
        popupMenu.menu.add(
            0,
            ItemsAdapter.ID_DELETE,
            Menu.NONE,
            view.context.getString(R.string.delete)
        )

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                ItemsAdapter.ID_RENAME -> {

                    val editTextView = EditText(view.context)
                    val dialog = AlertDialog.Builder(view.context)
                        .setTitle(view.context.getString(R.string.rename))
                        .setView(editTextView)
                        .setPositiveButton(view.context.getString(R.string.rename)) { _, _ ->
                            val newName = editTextView.text.toString()
                            renameAction(adapterPosition, newName)
                        }
                        .setNegativeButton(view.context.getString(R.string.cancel), null)
                        .create()
                    dialog.show()

                }
                ItemsAdapter.ID_DELETE -> {
                    deleteAction(adapterPosition)
                }
            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }
}