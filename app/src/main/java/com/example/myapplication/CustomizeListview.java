package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomizeListview extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    int country_img[] = {R.drawable.national_flag_bd, R.drawable.national_flag_cina,
            R.drawable.national_flag_eu, R.drawable.national_flag_in, R.drawable.national_flag_nz,
            R.drawable.national_flag_us, R.drawable.national_flag_england, R.drawable.national_flag_gerogia, R.drawable.national_flag_hongkong};

    String country_name[] = {"Bangladesh", "China", "EU", "India", "Newziland", "US", "England", "Gerogia", "Hongkong"};

    CustomizeListview(Context context){
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return country_img.length;
    }

    @Override
    public Object getItem(int i) {
        return country_img[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.coustomize_list_view, null);

        ImageView imageView = view.findViewById(R.id.iv_flag_image);
        final TextView textView = view.findViewById(R.id.tv_country_name);

        imageView.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(), country_img[i], 30, 30));
        textView.setText(country_name[i]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


}
