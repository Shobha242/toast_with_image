package com.example.customtoast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CheckResult;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

public class ToastClass {
    private static boolean allowQueue = true;
    public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;
    private static Toast lastToast = null;
    private static boolean tintIcon = true;
    private static final Typeface LOADED_TOAST_TYPEFACE = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
    private static Typeface currentTypeface = LOADED_TOAST_TYPEFACE;
    private static int textSize = 14; // in SP

    @CheckResult
    public static Toast error(@NonNull Context context, @StringRes int message, int duration, boolean withIcon, String gravity) {
        return custom(context, context.getString(message), ToastyUtils.getDrawable(context, R.drawable.ic_clear_white_24dp),
                ToastyUtils.getColor(context, R.color.errorColor), ToastyUtils.getColor(context, R.color.defaultTextColor),
                duration, withIcon, true, gravity);
    }

    @CheckResult
    public static Toast success(@NonNull Context context, @StringRes int message, int duration, boolean withIcon, String gravity) {
        return custom(context, context.getString(message), ToastyUtils.getDrawable(context, R.drawable.ic_check_white_24dp),
                ToastyUtils.getColor(context, R.color.successColor), ToastyUtils.getColor(context, R.color.defaultTextColor),
                duration, withIcon, true, gravity);
    }

    @CheckResult
    public static Toast info(@NonNull Context context, @NonNull CharSequence message, String gravity) {
        return info(context, message, Toast.LENGTH_SHORT, true, gravity);
    }

    @CheckResult
    public static Toast info(@NonNull Context context, @NonNull CharSequence message, int duration, boolean withIcon, String gravity) {
        return custom(context, message, ToastyUtils.getDrawable(context, R.drawable.ic_info_outline_white_24dp),
                ToastyUtils.getColor(context, R.color.infoColor), ToastyUtils.getColor(context, R.color.defaultTextColor),
                duration, withIcon, true, gravity);
    }

    @CheckResult
    public static Toast info(@NonNull Context context, @StringRes int message, int duration, boolean withIcon, String gravity) {
        return custom(context, context.getString(message), ToastyUtils.getDrawable(context, R.drawable.ic_info_outline_white_24dp),
                ToastyUtils.getColor(context, R.color.infoColor), ToastyUtils.getColor(context, R.color.defaultTextColor),
                duration, withIcon, true, gravity);
    }

    @CheckResult
    public static Toast warning(@NonNull Context context, @StringRes int message, int duration, boolean withIcon, String gravity) {
        return custom(context, context.getString(message), ToastyUtils.getDrawable(context, R.drawable.ic_error_outline_white_24dp),
                ToastyUtils.getColor(context, R.color.warningColor), ToastyUtils.getColor(context, R.color.defaultTextColor),
                duration, withIcon, true, gravity);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @StringRes int message, Drawable icon, String gravity) {
        return normal(context, context.getString(message), Toast.LENGTH_SHORT, icon, true, gravity);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @StringRes int message, String gravity) {
        return normal(context, context.getString(message), Toast.LENGTH_SHORT, null, false, gravity);
    }

    @CheckResult
    public static Toast custom(@NonNull Context context, @StringRes int message, Drawable icon,
                               @ColorRes int tintColorRes, @ColorRes int textColorRes, int duration,
                               boolean withIcon, boolean shouldTint, String gravity) {
        return custom(context, context.getString(message), icon, ToastyUtils.getColor(context, tintColorRes),
                ToastyUtils.getColor(context, textColorRes), duration, withIcon, shouldTint, gravity);
    }

    @CheckResult
    public static Toast normal(@NonNull Context context, @NonNull CharSequence message, int duration,
                               Drawable icon, boolean withIcon, String gravity) {
        return custom(context, message, icon, ToastyUtils.getColor(context, R.color.normalColor),
                ToastyUtils.getColor(context, R.color.defaultTextColor), duration, withIcon, true, gravity);
    }


    @SuppressLint("ShowToast")
    @CheckResult
    public static Toast custom(@NonNull Context context, @NonNull CharSequence message, Drawable icon,
                               @ColorInt int tintColor, @ColorInt int textColor, int duration,
                               boolean withIcon, boolean shouldTint, String gravity) {
        final Toast currentToast = Toast.makeText(context, "", duration);

        if (gravity.equals("Top")) {
            currentToast.setGravity(Gravity.TOP, 0, 0);
            currentToast.setMargin(.1f, 0);

        } else if (gravity.equals("Bottom")) {
            currentToast.setGravity(Gravity.BOTTOM, 0, 0);
        } else {
            currentToast.setGravity(Gravity.CENTER, 0, 0);
        }

        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.toast_layout, null);
        final ImageView toastIcon = toastLayout.findViewById(R.id.toast_icon);
        final TextView toastTextView = toastLayout.findViewById(R.id.toast_text);
        Drawable drawableFrame;

        if (shouldTint)
            drawableFrame = ToastyUtils.tint9PatchDrawableFrame(context, tintColor);
        else
            drawableFrame = ToastyUtils.getDrawable(context, R.drawable.roundede_background);
        ToastyUtils.setBackground(toastLayout, drawableFrame);
        if (withIcon) {
            if (icon == null)
                throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
            ToastyUtils.setBackground(toastIcon, tintIcon ? ToastyUtils.tintIcon(icon, textColor) : icon);
        } else {
            toastIcon.setVisibility(View.GONE);
        }

        toastTextView.setText(message);
        toastTextView.setTextColor(textColor);
        toastTextView.setTypeface(currentTypeface);
        toastTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

        currentToast.setView(toastLayout);

        if (!allowQueue) {
            if (lastToast != null)
                lastToast.cancel();
            lastToast = currentToast;
        }

        return currentToast;
    }

    public static class Config {
        public static void reset() {
            ToastClass.currentTypeface = LOADED_TOAST_TYPEFACE;
            ToastClass.textSize = 16;
            ToastClass.tintIcon = true;
            ToastClass.allowQueue = true;
        }

    }

}
