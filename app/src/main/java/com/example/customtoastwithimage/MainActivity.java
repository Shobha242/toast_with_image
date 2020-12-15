package com.example.customtoastwithimage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.view.View;

import com.example.customtoast.ToastClass;

import static android.graphics.Typeface.BOLD_ITALIC;

public class MainActivity extends AppCompatActivity {
    ToastClass toastClass;

    private int clickcount, clickcount2, clickcount3, clickcount4, clickcount5, clickcount6, clickcount7, clickcount8 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toastClass = new ToastClass();

        findViewById(R.id.button_error_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickcount == 0) {
                    clickcount = 1;
                    toastClass.error(MainActivity.this, R.string.error_message, ToastClass.LENGTH_SHORT, true, "Top").show();
                } else if (clickcount == 1) {
                    toastClass.error(MainActivity.this, R.string.error_message, ToastClass.LENGTH_SHORT, true, "Bottom").show();
                    clickcount = 2;
                } else {
                    toastClass.error(MainActivity.this, R.string.error_message, ToastClass.LENGTH_SHORT, true, "Center").show();
                    clickcount = 0;
                }
            }

        });
        findViewById(R.id.button_success_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickcount2 == 0) {
                    clickcount2 = 1;
                    toastClass.success(MainActivity.this, R.string.success_message, ToastClass.LENGTH_SHORT, true, "Top").show();
                } else if (clickcount2 == 1) {
                    toastClass.success(MainActivity.this, R.string.success_message, ToastClass.LENGTH_SHORT, true, "Bottom").show();
                    clickcount2 = 2;
                } else {
                    toastClass.success(MainActivity.this, R.string.success_message, ToastClass.LENGTH_SHORT, true, "Center").show();
                    clickcount2 = 0;
                }
            }
        });
        findViewById(R.id.button_info_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickcount3 == 0) {
                    clickcount3 = 1;
                    toastClass.info(MainActivity.this, R.string.info_message, ToastClass.LENGTH_SHORT, true, "Top").show();

                } else if (clickcount3 == 1) {
                    clickcount3 = 2;
                    toastClass.info(MainActivity.this, R.string.info_message, ToastClass.LENGTH_SHORT, true, "Bottom").show();
                } else {
                    toastClass.info(MainActivity.this, R.string.info_message, ToastClass.LENGTH_SHORT, true, "Center").show();
                    clickcount3 = 0;
                }
            }

        });
        findViewById(R.id.button_warning_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickcount4 == 0) {
                    clickcount4 = 1;
                    toastClass.warning(MainActivity.this, R.string.warning_message, ToastClass.LENGTH_SHORT, true, "Top").show();
                } else if (clickcount4 == 1) {
                    toastClass.warning(MainActivity.this, R.string.warning_message, ToastClass.LENGTH_SHORT, true, "Bottom").show();
                    clickcount4 = 2;
                } else {
                    toastClass.warning(MainActivity.this, R.string.warning_message, ToastClass.LENGTH_SHORT, true, "Center").show();
                    clickcount4 = 0;
                }
            }
        });
        findViewById(R.id.button_normal_toast_wo_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickcount5 == 0) {
                    clickcount5 = 1;
                    toastClass.normal(MainActivity.this, R.string.normal_message_without_icon, "Top").show();
                } else if (clickcount5 == 1) {
                    clickcount5 = 2;
                    toastClass.normal(MainActivity.this, R.string.normal_message_without_icon, "Bottom").show();

                } else {
                    toastClass.normal(MainActivity.this, R.string.normal_message_without_icon, "Center").show();
                    clickcount5 = 0;
                }
            }
        });
        findViewById(R.id.button_normal_toast_w_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickcount6 == 0) {
                    clickcount6 = 1;
                    Drawable icon = getResources().getDrawable(R.drawable.ic_pets_white_48dp);
                    toastClass.normal(MainActivity.this, R.string.normal_message_with_icon, icon, "Top").show();

                } else if (clickcount6 == 1) {
                    clickcount6 = 2;
                    Drawable icon = getResources().getDrawable(R.drawable.ic_pets_white_48dp);
                    toastClass.normal(MainActivity.this, R.string.normal_message_with_icon, icon, "Bottom").show();

                } else {
                    Drawable icon = getResources().getDrawable(R.drawable.ic_pets_white_48dp);
                    toastClass.normal(MainActivity.this, R.string.normal_message_with_icon, icon, "Center").show();
                    clickcount6 = 0;
                }
            }
        });
        findViewById(R.id.button_info_toast_with_formatting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickcount7 == 0) {
                    clickcount7 = 1;
                    toastClass.info(MainActivity.this, getFormattedMessage(), "Top").show();

                } else if (clickcount7 == 1) {
                    clickcount7 = 2;
                    toastClass.info(MainActivity.this, getFormattedMessage(), "Bottom").show();

                } else {
                    toastClass.info(MainActivity.this, getFormattedMessage(), "Center").show();

                    clickcount7 = 0;
                }
            }
        });

        findViewById(R.id.button_custom_config).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if (clickcount8 == 0) {
                    clickcount8 = 1;
                    toastClass.custom(MainActivity.this, R.string.custom_message, getResources().getDrawable(R.drawable.laptop512),
                            android.R.color.black, android.R.color.holo_green_light, ToastClass.LENGTH_SHORT, true, true, "Top").show();
                    ToastClass.Config.reset(); // Use this if you want to use the configuration above only once

                } else if (clickcount8 == 1) {
                    clickcount8 = 2;
                    toastClass.custom(MainActivity.this, R.string.custom_message, getResources().getDrawable(R.drawable.laptop512),
                            android.R.color.black, android.R.color.holo_green_light, ToastClass.LENGTH_SHORT, true, true, "Bottom").show();
                    ToastClass.Config.reset(); // Use this if you want to use the configuration above only once

                } else {
                    toastClass.custom(MainActivity.this, R.string.custom_message, getResources().getDrawable(R.drawable.laptop512),
                            android.R.color.black, android.R.color.holo_green_light, ToastClass.LENGTH_SHORT, true, true, "Center").show();
                    ToastClass.Config.reset(); // Use this if you want to use the configuration above only once

                    clickcount8 = 0;
                }

            }
        });
    }

    private CharSequence getFormattedMessage() {
        final String prefix = "Formatted ";
        final String highlight = "bold italic";
        final String suffix = " text";
        SpannableStringBuilder ssb = new SpannableStringBuilder(prefix).append(highlight).append(suffix);
        int prefixLen = prefix.length();
        ssb.setSpan(new StyleSpan(BOLD_ITALIC),
                prefixLen, prefixLen + highlight.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }
}