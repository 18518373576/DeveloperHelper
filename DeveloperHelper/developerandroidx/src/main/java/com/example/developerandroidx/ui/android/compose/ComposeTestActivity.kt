package com.example.developerandroidx.ui.android.compose

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Clip
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.themeTextStyle
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import com.example.developerandroidx.R
import com.example.developerandroidx.base.BaseActivityForKotlin

class ComposeTestActivity : BaseActivityForKotlin() {

//    override fun initStatusBar() {
//        setNativeStatusBar(StateBarType.TRAN)
//    }

    override fun initView() {
        setContent {
            myView()
        }
    }

    @Preview
    @Composable
    fun myView() {
//        var： var是一个可变变量，这是一个可以通过重新分配来更改为另一个值的变量。这种声明变量的方式和Java中声明变量的方式一样。
//        val: val是一个只读变量，这种声明变量的方式相当于java中的final变量。一个val创建的时候必须初始化，因为以后不能被改变。
        val image = +imageResource(R.mipmap.bg_landscape)
        MaterialTheme() {
            Column(
                    crossAxisSize = LayoutSize.Expand,
                    modifier = Spacing(16.dp)
            ) {
                // 放在容器中，设置大小
                Container(expanded = true, height = 180.dp) {
                    Clip(shape = RoundedCornerShape(10.dp)) {
                        // 显示图片
                        DrawImage(image)
                    }
                }

                HeightSpacer(10.dp)
                Text("Hello world!", style = +themeTextStyle { h6 })
                Text("Hello world!")
                Text("Hello world!")
            }
        }
    }
}