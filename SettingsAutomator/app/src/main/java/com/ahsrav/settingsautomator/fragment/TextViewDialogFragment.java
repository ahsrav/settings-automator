package com.ahsrav.settingsautomator.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ahsrav.settingsautomator.R;
import com.ahsrav.settingsautomator.activity.AddFilterActivity;

public class TextViewDialogFragment extends DialogFragment {

    private static final String TAG = "TextViewDialogFragment";
    AddFilterActivity activity;

    public static TextViewDialogFragment newInstance(int title, String defaultValue, int viewToModify) {
        TextViewDialogFragment frag = new TextViewDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        args.putInt("viewToModify", viewToModify);
        args.putString("defaultValue", defaultValue);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        activity = (AddFilterActivity) getActivity();
        int title = getArguments().getInt("title");
        final int viewToModify = getArguments().getInt("viewToModify");
        String defaultValue = getArguments().getString("defaultValue");

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_textview, null);
        final EditText enterNameTV = (EditText) view.findViewById(R.id.enterNameTV);
        enterNameTV.setText(defaultValue);

        builder.setTitle(title)
                .setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ((TextView) activity.findViewById(viewToModify))
                                .setText(enterNameTV.getText().toString());
                        activity.setValue(viewToModify, enterNameTV.getText().toString());
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
