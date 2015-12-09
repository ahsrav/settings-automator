package com.ahsrav.settingsautomator.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ahsrav.settingsautomator.R;
import com.ahsrav.settingsautomator.activity.AddFilterActivity;

public class CustomViewDialogFragment extends DialogFragment {
    private static final String TAG = "CustomViewDialogFragment";
    AddFilterActivity activity;
    private int changedVal;


    public static CustomViewDialogFragment newInstance(int title, int defaultValue, int viewToModify, int viewToInflate) {
        CustomViewDialogFragment frag = new CustomViewDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        args.putInt("viewToModify", viewToModify);
        args.putInt("viewToInflate", viewToInflate);
        args.putInt("defaultValue", defaultValue);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.activity = (AddFilterActivity) getActivity();
        int title = getArguments().getInt("title");
        final int viewToModify = getArguments().getInt("viewToModify");
        final int viewToInflate = getArguments().getInt("viewToInflate");
        int defaultValue = getArguments().getInt("defaultValue");
        setChangedVal(-1);

        LayoutInflater inflater = activity.getLayoutInflater();
        final View view = inflater.inflate(viewToInflate, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle(title)
                .setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (changedVal != -1) {
                            ((TextView) activity.findViewById(viewToModify))
                                    .setText(String.valueOf(changedVal));
                            activity.setValue(viewToModify, changedVal);
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CustomViewDialogFragment.this.getDialog().cancel();
                    }
                });

        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        seekBar.setProgress(defaultValue);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setChangedVal(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




        return builder.create();

    }

    private void setChangedVal(int progress) {
        this.changedVal = progress;
    }
}
