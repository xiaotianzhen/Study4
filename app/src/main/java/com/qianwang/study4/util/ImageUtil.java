package com.qianwang.study4.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
/**
 * Created by luo on 2017/5/10.
 */

public class ImageUtil {


    public static Bitmap getReverseBtimapById(int resId, Context context) {
        //获取图片资源
        Bitmap sourseBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        Matrix matrix = new Matrix();
        matrix.setScale(1, -1);
        //图片倒影
        Bitmap reverseBitmap = Bitmap.createBitmap(sourseBitmap, 0, sourseBitmap.getHeight() / 2, sourseBitmap.getWidth(), sourseBitmap.getHeight() / 3, matrix, false);
        //原始图片和倒影图片合并
        Bitmap groupBitmap = Bitmap.createBitmap(sourseBitmap.getWidth(), sourseBitmap.getHeight() + sourseBitmap.getHeight() / 3 + 60, sourseBitmap.getConfig());
        //创建图片组画布
        Canvas canvas = new Canvas(groupBitmap);
        //绘制原图
        canvas.drawBitmap(sourseBitmap, 0, 0, null);
        //绘制倒影
        canvas.drawBitmap(reverseBitmap, 0, sourseBitmap.getHeight() + 50, null);
        //添加遮罩
        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, sourseBitmap.getHeight() + 50, 0, groupBitmap.getHeight(), Color.BLACK, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        //这里取的是矩形与图片的交集，所以用的是DST_IN
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, 0, sourseBitmap.getWidth(), groupBitmap.getHeight(), paint);

        return groupBitmap;
    }
}
