package com.example.yovo_user.varnatravelguide.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.yovo_user.varnatravelguide.R;

import java.lang.ref.WeakReference;

public class OktaProgressDialog {
    WeakReference<Context> mContext;
    AlertDialog dialog;

    public OktaProgressDialog(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    public void show() {
        show(null);
    }

    public void show(String message) {
        if(this.dialog == null) {
            this.dialog = createAlertDialog(message);
        }
        if(this.dialog != null) {
            dialog.show();
        }
    }

    public void hide() {
        if(this.dialog != null) {
            dialog.cancel();
            dialog = null;
        }
    }

    private AlertDialog createAlertDialog(String message) {
        if(mContext.get() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext.get());
            builder.setCancelable(false); // if you want user to wait for some process to finish,
            LayoutInflater mInflater = (LayoutInflater) mContext.get().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = mInflater.inflate(R.layout.layout_progress_dialog, null, false);
            String textViewMessage = (message == null) ? mContext.get().getString(R.string.progress_dialog_message) : message;
            ((TextView)view.findViewById(R.id.message_textview)).setText(textViewMessage);
            builder.setView(view);
            return builder.create();
        }
        return null;
    }
}