package com.ahsrav.settingsautomator.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

public class ListViewDialogFragment extends DialogFragment {

    private static final String TAG = "ListViewDialogFragment";
    Activity activity;

    public static ListViewDialogFragment newInstance(int title, int defaultValue, int viewToModify, int itemsArray) {
        ListViewDialogFragment frag = new ListViewDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        args.putInt("viewToModify", viewToModify);
        args.putInt("itemsArray", itemsArray);
        args.putInt("defaultValue", defaultValue);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.activity = getActivity();
        int title = getArguments().getInt("title");
        final int viewToModify = getArguments().getInt("viewToModify");
        final int itemsArray = getArguments().getInt("itemsArray");
        int defaultValue = getArguments().getInt("defaultValue");

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle(title)
                .setSingleChoiceItems(itemsArray, defaultValue, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((TextView) activity.findViewById(viewToModify))
                                .setText(activity.getResources().getStringArray(itemsArray)[which]);
                        dialog.dismiss();
                    }
                });

        return builder.create();

    }
}
