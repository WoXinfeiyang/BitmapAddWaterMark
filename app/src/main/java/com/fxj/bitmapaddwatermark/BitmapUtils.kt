package com.fxj.bitmapaddwatermark

import android.graphics.*
import android.util.Log
import java.io.File
import java.io.FileOutputStream

class BitmapUtils {

    companion object {
        private val TAG="BitmapUtils";

        fun addWaterMar(sourceBitmap:Bitmap, waterMarkStr:String, textSizeForDip:Float, textColorString:String, scaleValue:Float):Bitmap?{
            var srcWidth:Int=sourceBitmap.width;
            var srcHeight:Int=sourceBitmap.height;

            var resultBitmap:Bitmap?= Bitmap.createBitmap(srcWidth,srcHeight,Bitmap.Config.ARGB_8888);

            var canvas:Canvas= Canvas(resultBitmap);

            var matrix: Matrix= Matrix();
            matrix.postScale(scaleValue,scaleValue,0.5f,0.5f)
            var bitmapPaint:Paint= Paint();
            bitmapPaint.setFilterBitmap(true);

            canvas.drawBitmap(sourceBitmap,matrix,bitmapPaint);

            var destWidth:Int=resultBitmap?.width!!;
            var destHeight:Int=resultBitmap?.height!!;

            var textPaint:Paint= Paint();
            textPaint.setColor(Color.parseColor(textColorString/*"#FF4040"*/));

            var waterMarkStringSize:Float=if(destHeight!!>textSizeForDip)textSizeForDip else destHeight.toFloat();

            textPaint.textSize==waterMarkStringSize;

            val textMarginLeft=10/scaleValue;
            val textMarginTop=10/scaleValue;
            canvas?.drawText(waterMarkStr,textMarginLeft,textMarginTop,textPaint)

            sourceBitmap.recycle();
            return resultBitmap;
        }

        fun saveBitmap(sourceBitmap: Bitmap,path:String){
            var bitmapFile:File= File(path);
            if(!bitmapFile.exists()){
                bitmapFile.createNewFile();
            }

            var fileOutputStream:FileOutputStream?=null;
            try {
                fileOutputStream= FileOutputStream(bitmapFile);
                sourceBitmap.compress(Bitmap.CompressFormat.JPEG,90,fileOutputStream)
                fileOutputStream.flush();
                fileOutputStream.close();
            }catch (e: Exception){
                Log.e(TAG,"Exception Message:${e.message}")
                e.printStackTrace();
            }
            Log.d(TAG,"文件已保存至:${path}");
        }
    }
}