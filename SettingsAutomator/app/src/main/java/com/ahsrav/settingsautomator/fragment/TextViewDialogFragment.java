package com.ahsrav.settingsautomator.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.ahsrav.settingsautomator.R;
import com.ahsrav.settingsautomator.view.AddFilterActivity;

public class TextViewDialogFragment extends DialogFragment {

    private static final String TAG = "TextViewDialogFragment";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_textview, null);

        builder.setTitle(R.string.enter_filter_name)
                .setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText enterNameTV = (EditText) view.findViewById(R.id.enterNameTV);
                        ((AddFilterActivity) getActivity()).currentFilterInfo.filterName = enterNameTV.getText().toString();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        TextViewDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }
}
