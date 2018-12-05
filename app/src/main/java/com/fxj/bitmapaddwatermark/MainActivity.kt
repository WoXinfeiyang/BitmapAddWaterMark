package com.fxj.bitmapaddwatermark

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import java.lang.Exception

class MainActivity : Activity(), View.OnClickListener {

    companion object {
        const val TAG="MainActivity_fxj";
    }

    private var layout:ViewGroup?=null;

    private var etInputWatchMark:EditText?=null;

    private var btnAddWatchMark:Button?=null;

    private var sourceImg:ImageView?=null;

    private var destinationImg:ImageView?=null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.layout=findViewById(R.id.layout);

        this.etInputWatchMark=findViewById(R.id.et_input_water_mark);

        this.btnAddWatchMark=findViewById(R.id.btn_add_water_mark);
        this.btnAddWatchMark?.setOnClickListener (this)

        this.sourceImg=findViewById(R.id.source_img);
        this.destinationImg=findViewById(R.id.destination_img);
        Log.d(TAG,"**onCreate**,currentThreadName=${Thread.currentThread().getName()}")
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_add_water_mark->{
                var watchMarkStr:String?=etInputWatchMark?.getText().toString()?:null;
                if(!TextUtils.isEmpty(watchMarkStr)){
                    var destBitmap=BitmapUtils.addWaterMar(BitmapFactory.decodeResource(resources,R.drawable.tangwei),watchMarkStr!!,DisplayUtils.dipToPx(this,200f),"#FF4040",1f)
                    Log.d(TAG,"destBitmap=${destBitmap},currentThreadName=${Thread.currentThread().getName()},bitmapCount=${destBitmap?.byteCount}")
                    BitmapUtils.saveBitmap(destBitmap!!,"/sdcard/"+System.currentTimeMillis()+".jpg")
                    destinationImg?.setImageBitmap(destBitmap);
                }else{
                    Toast.makeText(this,"输入文本不能为空!!",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
